package com.tls.liferaylms.mail.message;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.portlet.PortletPreferences;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.lms.model.Course;
import com.liferay.lms.model.LmsPrefs;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.LmsPrefsLocalServiceUtil;
import com.liferay.mail.model.FileAttachment;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.dao.orm.CustomSQLParam;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.Account;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.PortalPreferencesLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
import com.liferay.portal.util.comparator.UserLastNameComparator;
import com.liferay.util.mail.MailEngine;
import com.tls.liferaylms.mail.model.AuditReceiverMail;
import com.tls.liferaylms.mail.model.AuditSendMails;
import com.tls.liferaylms.mail.service.AuditReceiverMailLocalServiceUtil;
import com.tls.liferaylms.mail.service.AuditSendMailsLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailRelationLocalServiceUtil;
import com.tls.liferaylms.util.MailConstants;
import com.tls.liferaylms.util.MailStringPool;
import com.tls.liferaylms.util.MailUtil;


public class LmsMailMessageListener implements MessageListener {
	private static Log log = LogFactoryUtil.getLog(LmsMailMessageListener.class);
			
	@Override
	public void receive(Message message) {
		try {
			doReceive(message);
		} catch (Exception e) {
			_log.error("Unable to process message " + message, e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void doReceive(Message message) throws Exception {
	
		if (_log.isDebugEnabled())
			_log.debug("LmsMailMessageListener doReceive");
		
		String auditing = message.getString("auditing");
		long groupId 	= message.getLong("groupId");
		String subject 	= message.getString("subject");
		String body 	= message.getString("body");
		
		Group scopeGroup = GroupLocalServiceUtil.getGroup(groupId);
		long companyId 	 = scopeGroup.getCompanyId();
		
		
		boolean internalMessagingActive = PrefsPropsUtil.getBoolean(companyId, MailStringPool.INTERNAL_MESSAGING_KEY);
		boolean sendAlwaysMessage = PrefsPropsUtil.getBoolean(companyId, MailStringPool.SEND_ALWAYS_MESSAGE_KEY);
		String deregisterMailExpando = PrefsPropsUtil.getString(companyId, MailStringPool.DEREGISTER_MAIL_KEY);
		if(Validator.isNull(deregisterMailExpando)){
			deregisterMailExpando=MailStringPool.DEREGISTER_USER_EXPANDO;
		}
		
		log.debug("--- Internal Messaging Active: "+internalMessagingActive);
		log.debug("--- Deregister Mail Expando: "+deregisterMailExpando);
		
		
		
		AuditSendMails auditSendMails = null;
		
		if(Validator.isNotNull(auditing)){
			
			if (_log.isDebugEnabled())
				_log.debug("Auditando");
			
			Message responseMessage = MessageBusUtil.createResponseMessage(message);
			responseMessage.setPayload("RECEIVED");
			
			auditSendMails = AuditSendMailsLocalServiceUtil.getInscriptionHistory(groupId, companyId);
			
			if (Validator.isNull(auditSendMails)) {
				
				if (_log.isDebugEnabled())
					_log.debug("Se crea auditoria de tipo inscripcion");

				auditSendMails = AuditSendMailsLocalServiceUtil
						.createAuditSendMails(CounterLocalServiceUtil.increment(AuditSendMails.class.getName()));
				auditSendMails.setType_(MailConstants.TYPE_INSCRIPTION);
				auditSendMails.setCompanyId(companyId);
				auditSendMails.setGroupId(groupId);
				auditSendMails.setSubject(subject);
				auditSendMails.setBody(body);

				AuditSendMailsLocalServiceUtil.addAuditSendMails(auditSendMails);
			}
			
			MessageBusUtil.sendMessage(responseMessage.getDestinationName(), responseMessage);
			return;
		}
		
		// Se recogen las variables.
		long userId = message.getLong("userId");
		String testing = message.getString("testing");
		String portal = message.getString("portal");
		String community = message.getString("community");
		String url = message.getString("url");
		String urlcourse = message.getString("urlcourse");
		String templateId = Validator.isNull(message.getString("templateId")) ? "-1" : message.getString("templateId") ;
		String toMail = message.getString("to");
		boolean sendToRelatedUsers = Validator.isNotNull(message.getBoolean("sendToRelatedUsers")) && message.getBoolean("sendToRelatedUsers");
		boolean isUserRelated = sendToRelatedUsers && Validator.isNotNull(message.getBoolean("isUserRelated")) && message.getBoolean("isUserRelated");
		List<Integer> sendToRelationTypeIds = (List<Integer>) message.get("mailRelationTypeIds");
		List<User> emailSentToUsersList = (List<User>) message.get("emailSentToUsersList");
		String tutors = message.getString("tutors");
		String userName = message.getString("userName");
		boolean ownTeam = message.getBoolean("ownTeam");
		boolean isOmniadmin = message.getBoolean("isOmniadmin");
		long numUsersSender = 0;
		Long entryId = message.getLong("entryId");
		boolean deregisterMail;
		String type_ = message.getString("type");
		File[] attachments = (File[]) message.get("attachments");
		String[] attachmentNames = (String[]) message.get("attachmentNames");
		
		if (_log.isDebugEnabled())
				_log.debug("Attachments: "+attachments);
			_log.debug("Attachment Names: "+attachmentNames);
			
			if(attachments!= null && attachmentNames!= null){
				_log.debug("Attachments length: "+attachments.length);
				_log.debug("Attachment Names length: "+attachmentNames.length);
			}
		
			_log.debug("type_::"+type_);

		
		String fromName = message.getString("fromName");
		String fromAddress = message.getString("fromAddress");
		if(Validator.isNull(fromName)){
			fromName = PrefsPropsUtil.getString(companyId, PropsKeys.ADMIN_EMAIL_FROM_NAME);
		}
		if(Validator.isNull(fromAddress)){
			fromAddress = PrefsPropsUtil.getString(companyId, PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		}
		
		long nUsers = 0,millis = 0;
		String numberUsers = PrefsPropsUtil.getString("lmsmailing.sendmails.number.users");
		String milliseconds = PrefsPropsUtil.getString("lmsmailing.sendmails.wating.time.milliseconds");
		
		if(Validator.isNotNull(numberUsers)){
			nUsers = Long.valueOf(numberUsers);
		}
		
		if(milliseconds != null && !milliseconds.equals("")){
			millis = Long.valueOf(milliseconds);
		}
		
		boolean showExpandosUser = PrefsPropsUtil.getBoolean(companyId, MailConstants.USER_EXPANDOS_TO_SHOW, Boolean.FALSE);
		boolean showExpandosCourse = PrefsPropsUtil.getBoolean(companyId, MailConstants.COURSE_EXPANDOS_TO_SHOW, Boolean.FALSE);
		if(log.isDebugEnabled()){
			log.debug("::::showExpandosUser:::: " + showExpandosUser);
			log.debug("::::showExpandosCourse:::: " + showExpandosCourse);
		}
			
		InternetAddress from = new InternetAddress(fromAddress, fromName);
		User userSender = UserLocalServiceUtil.getUserById(userId);
		if ("true".equals(testing)) {
			if(_log.isDebugEnabled()) {
				_log.debug("Se entra en modo testing");
			}
			InternetAddress to = new InternetAddress(userSender.getEmailAddress(), userSender.getFullName());
			deregisterMail = false;
			if(!sendAlwaysMessage){
				if(userSender.getExpandoBridge().getAttribute(deregisterMailExpando,false)!=null){
					deregisterMail = Boolean.parseBoolean(String.valueOf(userSender.getExpandoBridge().getAttribute(deregisterMailExpando,false)));
				}
			}
			
			
			log.debug("---STUDENT MAIL 1: "+userSender.getEmailAddress());
			log.debug("---STUDENT ID 1: "+userSender.getUserId());
			log.debug("---DEREGISTER MAIL 1: "+deregisterMail);
			log.debug("--- EXPANDO 1: "+userSender.getExpandoBridge().getAttribute(deregisterMailExpando,false));
			
			if(!deregisterMail){
				
				body = MailUtil.replaceMessageConstants(body, portal, community, userSender.getFullName(), userSender.getScreenName(), userSender.getFirstName(), tutors, url, urlcourse,
						MailUtil.getCourseStartDate(groupId, userSender.getLocale(), userSender.getTimeZone()), MailUtil.getCourseEndDate(groupId, userSender.getLocale(), userSender.getTimeZone(),userSender), userSender.getFullName());
				
				body = MailUtil.replaceStudent(body, userSender.getFullName(), userSender.getScreenName(), userSender.getFirstName());
				//Sustituir expandos
				if(showExpandosUser)
					body = MailUtil.replaceExpandosUser(body, companyId, userSender, userSender.getLocale());
				if(showExpandosCourse)
					body = MailUtil.replaceExpandosCourse(body, companyId, groupId, userSender.getLocale());
				
				String calculatedBody = PrefsPropsUtil.getString(companyId, MailConstants.HEADER_PREFS, LanguageUtil.get(Locale.getDefault(),"mail.header"));
				calculatedBody += body;
				calculatedBody += PrefsPropsUtil.getString(companyId, MailConstants.FOOTER_PREFS, LanguageUtil.get(Locale.getDefault(),"mail.footer"));
				
				subject = MailUtil.replaceMessageConstants(subject, portal, community, userSender.getFullName(), userSender.getScreenName(), userSender.getFirstName(), tutors, url, urlcourse,
						MailUtil.getCourseStartDate(groupId, userSender.getLocale(), userSender.getTimeZone()), MailUtil.getCourseEndDate(groupId, userSender.getLocale(), userSender.getTimeZone(),userSender), userSender.getFullName());
				//Sustituir expandos
				if(showExpandosUser)
					subject = MailUtil.replaceExpandosUser(subject, companyId, userSender, userSender.getLocale());
				if(showExpandosCourse)
					subject = MailUtil.replaceExpandosCourse(subject, companyId, groupId, userSender.getLocale());
				
				//Guardar una auditoria del envio de emails.
				auditSendMails = AuditSendMailsLocalServiceUtil.createAuditSendMails(CounterLocalServiceUtil.increment(AuditSendMails.class.getName()));
				auditSendMails.setUserId(userId);
				auditSendMails.setGroupId(groupId);
				auditSendMails.setTemplateId(Long.parseLong(templateId));
				if(Long.parseLong(templateId)<0){
					auditSendMails.setBody(body);
					auditSendMails.setSubject(subject);
				}
				auditSendMails.setCompanyId(companyId);
				AuditSendMailsLocalServiceUtil.addAuditSendMails(auditSendMails);
				
				log.debug("calculatedBody: " + calculatedBody);
				
				try{
					MailMessage mailm = new MailMessage(from, to, subject, calculatedBody, true);
					if(attachments!=null && attachments.length>0 && attachmentNames!=null && attachmentNames.length>0 && attachments.length == attachmentNames.length){
						for(int i = 0; i<attachments.length ; i++){
							mailm.addFileAttachment(attachments[i], attachmentNames[i]);
						}
					}
					if(internalMessagingActive){
						MailUtil.sendInternalMessageNotification(entryId,subject, body, groupId, userId, userSender.getUserId(), companyId, attachments, attachmentNames);
					}
					MailServiceUtil.sendEmail(mailm);
					addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), userSender.getEmailAddress(), MailConstants.STATUS_OK, false);
				}catch(Exception e){
					addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), userSender.getEmailAddress(), MailConstants.STATUS_KO, false);
					e.printStackTrace();
				}
				//Guardar una auditoria del envio de emails.
				auditSendMails.setSendDate(new Date(System.currentTimeMillis()));
				auditSendMails.setNumberOfPost(numUsersSender);
				AuditSendMailsLocalServiceUtil.updateAuditSendMails(auditSendMails); 
			}
			
			
		}else if(toMail != null && !toMail.equals("all")) {
			if(_log.isDebugEnabled()) {
				_log.debug("Se entra en el envio individual de correos. -- isUserRelated -- " + isUserRelated);
			}
			User student = UserLocalServiceUtil.fetchUserByEmailAddress(userSender.getCompanyId(), toMail);
			
			if(student != null && student.getStatus() != com.liferay.portal.kernel.workflow.WorkflowConstants.STATUS_INACTIVE && Validator.isEmailAddress(student.getEmailAddress())) {
			
				deregisterMail = false;
				if(!sendAlwaysMessage){
					if(student.getExpandoBridge().getAttribute(deregisterMailExpando,false)!=null){
						deregisterMail = Boolean.parseBoolean(String.valueOf(student.getExpandoBridge().getAttribute(deregisterMailExpando,false)));
					}
				}
				
				if(log.isDebugEnabled()){
					log.debug("---STUDENT MAIL 2: "+userSender.getEmailAddress());
					log.debug("---STUDENT ID 2: "+userSender.getUserId());
					log.debug("---DEREGISTER MAIL 2: "+deregisterMail);
					log.debug("--- EXPANDO 2: "+userSender.getExpandoBridge().getAttribute(deregisterMailExpando,false));
				}
				
				if(!deregisterMail){
					
					String toFullName = (isUserRelated) ? LanguageUtil.get(student.getLocale(),"groupmailing.messages.student-full-name") : student.getFullName();
					String toScreenName = (isUserRelated) ? LanguageUtil.get(student.getLocale(),"groupmailing.messages.student-screen-name") : student.getScreenName();
					String toFirstName = (isUserRelated) ? LanguageUtil.get(student.getLocale(),"groupmailing.messages.student-name") : student.getFirstName();
					
					InternetAddress to = new InternetAddress(toMail, student.getFullName());
					String content = MailUtil.replaceMessageConstants(body, portal, community, toFullName, toScreenName, toFirstName, tutors,url,urlcourse,
							MailUtil.getCourseStartDate(groupId, student.getLocale(), student.getTimeZone()), MailUtil.getCourseEndDate(groupId, student.getLocale(), student.getTimeZone(),student), userSender.getFullName());
					//Sustituir expandos
					if(showExpandosUser)
						content = MailUtil.replaceExpandosUser(content, companyId, isUserRelated?null:student, student.getLocale());
					if(showExpandosCourse)
						content = MailUtil.replaceExpandosCourse(content, companyId, groupId, student.getLocale());
					
					String calculatedBody = StringPool.BLANK;
					if(isUserRelated)
						calculatedBody += MailUtil.getExtraContentSocialRelationHeader(student) + MailUtil.getExtraContentSocialRelation(emailSentToUsersList, student, sendToRelationTypeIds);
					calculatedBody += PrefsPropsUtil.getString(companyId, MailConstants.HEADER_PREFS, LanguageUtil.get(student.getLocale(),"mail.header"));
					calculatedBody += MailUtil.replaceMessageConstants(body, portal, community, toFullName, toScreenName, toFirstName, tutors,url,urlcourse, MailUtil.getCourseStartDate(groupId, student.getLocale(), student.getTimeZone()), MailUtil.getCourseEndDate(groupId, student.getLocale(), student.getTimeZone(), student), userSender.getFullName());
					//Sustituir expandos
					if(showExpandosUser)
						calculatedBody = MailUtil.replaceExpandosUser(calculatedBody, companyId, isUserRelated?null:student, student.getLocale());
					if(showExpandosCourse)
						calculatedBody = MailUtil.replaceExpandosCourse(calculatedBody, companyId, groupId, student.getLocale());
					
					calculatedBody += PrefsPropsUtil.getString(companyId, MailConstants.FOOTER_PREFS, LanguageUtil.get(student.getLocale(),"mail.footer"));
					
					String calculatedsubject = MailUtil.replaceMessageConstants(subject, portal, community, toFullName, toScreenName, toFirstName, tutors, url, urlcourse, MailUtil.getCourseStartDate(groupId, student.getLocale(), student.getTimeZone()), MailUtil.getCourseEndDate(groupId, student.getLocale(), student.getTimeZone(),student), userSender.getFullName());
					
					//Sustituir expandos
					if(showExpandosUser)
						calculatedsubject = MailUtil.replaceExpandosUser(calculatedsubject, companyId, isUserRelated?null:student, student.getLocale());
					if(showExpandosCourse)
						calculatedsubject = MailUtil.replaceExpandosCourse(calculatedsubject, companyId, groupId, student.getLocale());
					
					if(log.isDebugEnabled()) {
						log.debug("Se envia el siguiente correo...");
						log.debug("De: " + from);
						log.debug("A: " + toMail + " " + student.getFullName());
						log.debug("Profesores: " + tutors);
						log.debug("Asunto: " + calculatedsubject);
						log.debug("Cuerpo: " + calculatedBody);
					}
					
					//Guardar una auditoria del envio de emails.
					boolean hasDate = false;
					
					//Miramos el tipo de env�o
					if (type_.equals(MailConstants.TYPE_INSCRIPTION)){

						auditSendMails = AuditSendMailsLocalServiceUtil.getInscriptionHistory(groupId, companyId);
						hasDate = true;
					
					}else{
						if(type_!=null && !type_.equals(MailConstants.TYPE_MAIL_TO_TUTOR)){
							type_ = MailConstants.TYPE_MAILJOB;
						}
						
						auditSendMails = AuditSendMailsLocalServiceUtil.createAuditSendMails(CounterLocalServiceUtil.increment(AuditSendMails.class.getName()));
						
						auditSendMails.setUserId(userId);
						auditSendMails.setGroupId(groupId);
						auditSendMails.setTemplateId(Long.parseLong(templateId));
						if(Long.parseLong(templateId)<0){
							auditSendMails.setBody(calculatedBody);
							auditSendMails.setSubject(calculatedsubject);
						}
						auditSendMails.setCompanyId(companyId);
						auditSendMails.setSendDate(new Date(System.currentTimeMillis()));
						auditSendMails.setNumberOfPost(1);
						auditSendMails.setType_(type_);
						
						AuditSendMailsLocalServiceUtil.addAuditSendMails(auditSendMails);
					}
					
					try{
						MailMessage mailm = new MailMessage(from, to, calculatedsubject, calculatedBody ,true);
						if(attachments!=null && attachments.length>0 && attachmentNames!=null && attachmentNames.length>0 && attachments.length == attachmentNames.length){
							for(int i = 0; i<attachments.length ; i++){
								mailm.addFileAttachment(attachments[i], attachmentNames[i]);
							}
						}
						
						if(internalMessagingActive){
							if(isUserRelated)
								content = MailUtil.getExtraContentSocialRelationHeader(student) + MailUtil.getExtraContentSocialRelation(emailSentToUsersList, student, sendToRelationTypeIds) + content;
							MailUtil.sendInternalMessageNotification(entryId,calculatedsubject, content, groupId,userId, student.getUserId(), companyId, attachments, attachmentNames);
						}
						MailEngine.send(mailm);
						addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), toMail, MailConstants.STATUS_OK, hasDate);
					}catch(Exception e){
						addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), toMail, MailConstants.STATUS_KO, hasDate);
						e.printStackTrace();
					}
				}
			}
		}else if(toMail.equals("all")) {
			if(_log.isDebugEnabled()) {
				_log.debug("Se envia un correo a todos..." + ownTeam);
			}
			List<User> users = new ArrayList<User>();
			if (ownTeam) {
				List<Team> userTeams = TeamLocalServiceUtil.getUserTeams(userId, groupId);
				Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(groupId);
				LmsPrefs prefs = LmsPrefsLocalServiceUtil.getLmsPrefs(companyId);
				
				OrderByComparator obc = null;		
				PortletPreferences portalPreferences = PortalPreferencesLocalServiceUtil.getPreferences(companyId, companyId, 1);
				if(Boolean.parseBoolean(portalPreferences.getValue("users.first.last.name", "false"))){
					obc = new UserLastNameComparator(true);
				}else{
					obc = new UserFirstNameComparator(true);
				}
				LinkedHashMap userParams = new LinkedHashMap();

				if (Validator.isNotNull(course)){
					userParams.put("notInCourseRoleTeach", new CustomSQLParam("WHERE User_.userId NOT IN "
				              + " (SELECT UserGroupRole.userId " + "  FROM UserGroupRole "
				              + "  WHERE  (UserGroupRole.groupId = ?) AND (UserGroupRole.roleId = ?))", new Long[] {
				              course.getGroupCreatedId(),
				              RoleLocalServiceUtil.getRole(prefs.getTeacherRole()).getRoleId() }));
				           
				  	userParams.put("notInCourseRoleEdit", new CustomSQLParam("WHERE User_.userId NOT IN "
				              + " (SELECT UserGroupRole.userId " + "  FROM UserGroupRole "
				              + "  WHERE  (UserGroupRole.groupId = ?) AND (UserGroupRole.roleId = ?))", new Long[] {
				              course.getGroupCreatedId(),
				              RoleLocalServiceUtil.getRole(prefs.getEditorRole()).getRoleId() }));
				  	
				  	if (ownTeam && !isOmniadmin && (userTeams!=null) && (userTeams.size()>0)){
				  		StringBuffer teamIds = new StringBuffer();
				  		teamIds.append(userTeams.get(0).getTeamId());
				  		if (userTeams.size() > 1) {
					  		for(int i = 1; i<userTeams.size(); i++){
					  			teamIds.append(",");
					  			teamIds.append(userTeams.get(i).getTeamId());
					  		}
				  		}
				  		
				  		userParams.put("inMyTeams", new CustomSQLParam("WHERE User_.userId IN "
				  				+ " (SELECT distinct(Users_Teams.userId) FROM Users_Teams WHERE Users_Teams.teamId in ("+teamIds.toString()+ "))",null ));
				  	}
				  	
				  	userParams.put("usersGroups", groupId);
				}
				
				users  = UserLocalServiceUtil.search(companyId, null, 0, userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);
				
			}else {
				Course course = CourseLocalServiceUtil.fetchByGroupCreatedId(groupId);
				boolean sendToTutors = PrefsPropsUtil.getBoolean(companyId, MailStringPool.SEND_TO_TUTORS_KEY, true);
				log.debug("Send to tutos "+sendToTutors);
				if(!sendToTutors && course!=null){
					users =  CourseLocalServiceUtil.getStudentsFromCourse(course);
				}else{
					users = UserLocalServiceUtil.getGroupUsers(groupId);
				}
				
				   
			}
			
			//Env�o de correos a usuarios relacionados
			boolean sendCopyToSocialRelation = sendToRelatedUsers && Validator.isNotNull(sendToRelationTypeIds) && sendToRelationTypeIds.size()>0;
			List<User> sendToSocialRelationUsers = MailRelationLocalServiceUtil.findUsersByCompanyIdSocialRelationTypeIdsToUsers(users, sendToRelationTypeIds, companyId);
			sendCopyToSocialRelation = sendCopyToSocialRelation && Validator.isNotNull(sendToSocialRelationUsers) && sendToSocialRelationUsers.size()>0;
			
			if(_log.isDebugEnabled()) {
				_log.debug("Se envia a " + users.size() + " usuarios.");
				_log.debug("Se envia a (relaciones sociales)" + sendToSocialRelationUsers.size() + " usuarios.");
			}
			
			numUsersSender = users.size() + sendToSocialRelationUsers.size();
			int sendMails = 0;
			Session session = MailEngine.getSession();
			String smtpHost = _getSMTPProperty(session, "host");
			int smtpPort = GetterUtil.getInteger(_getSMTPProperty(session, "port"), Account.PORT_SMTP);
			String smtpuser = _getSMTPProperty(session, "user");
			String password = _getSMTPProperty(session, "password");
			String protocol = GetterUtil.getString(session.getProperty("mail.transport.protocol"), Account.PROTOCOL_SMTP);

			if(_log.isDebugEnabled()) {
				log.debug("Conectando con el servidor SMTP");
				log.debug("Protocolo: " + protocol);
				log.debug("Usuario: " + smtpuser);
				log.debug("Contrasena: " + password);
			}
			

			String bodyTemplate = MailUtil.replaceMessageConstants(body, portal, community, null, null, null, tutors, url, urlcourse,
					MailUtil.getCourseStartDate(groupId, userSender.getLocale(), userSender.getTimeZone()), MailUtil.getCourseEndDate(groupId, userSender.getLocale(), userSender.getTimeZone(),userSender), userSender.getFullName());
			
			//Sustituir expandos
			if(showExpandosCourse)
				bodyTemplate = MailUtil.replaceExpandosCourse(bodyTemplate, companyId, groupId, userSender.getLocale());
			
			String subjectTemplate = MailUtil.replaceMessageConstants(subject, portal, community, null, null, null, tutors, url, urlcourse,
					MailUtil.getCourseStartDate(groupId, userSender.getLocale(), userSender.getTimeZone()), MailUtil.getCourseEndDate(groupId, userSender.getLocale(), userSender.getTimeZone(),userSender), userSender.getFullName());
			
			//Sustituir expandos
			if(showExpandosCourse)
				subjectTemplate = MailUtil.replaceExpandosCourse(subjectTemplate, companyId, groupId, userSender.getLocale());
			
			String calculatedBody, calculatedSubject;
			
			if(_log.isDebugEnabled()) {
				log.debug("bodyTemplate: " + bodyTemplate);
				log.debug("subjectTemplate: " + subjectTemplate);
			}
			
			Transport transport = session.getTransport(protocol);
			
			//Guardar una auditoria del envio de emails.
			auditSendMails = AuditSendMailsLocalServiceUtil.createAuditSendMails(CounterLocalServiceUtil.increment(AuditSendMails.class.getName()));
			auditSendMails.setUserId(userId);
			auditSendMails.setGroupId(groupId);
			auditSendMails.setTemplateId(Long.parseLong(templateId));
			auditSendMails.setType_(MailConstants.TYPE_MASSIVE);
			
			if(Long.parseLong(templateId)<0){
				auditSendMails.setBody(bodyTemplate);
				auditSendMails.setSubject(subjectTemplate);
			}
			
			auditSendMails.setCompanyId(companyId);
			AuditSendMailsLocalServiceUtil.addAuditSendMails(auditSendMails); 
			
			
			try {
				transport.connect(smtpHost, smtpPort, smtpuser, password);
				_log.debug("Conectado al servidor SMTP");
			}
			catch(MessagingException me) {
				//Guardar una auditoria del envio de emails.
				auditSendMails.setSendDate(new Date(System.currentTimeMillis()));
				auditSendMails.setNumberOfPost(numUsersSender);
				AuditSendMailsLocalServiceUtil.updateAuditSendMails(auditSendMails); 
				
				me.printStackTrace();
				throw new Exception(me);
			}
			
			log.debug("users: " + users.size());
			
			// Se envían los correos a todos los alumnos.
			for (User student : users) {
				if (student.isActive() && Validator.isEmailAddress(student.getEmailAddress())) {
					deregisterMail = false;
					if(!sendAlwaysMessage){
						if(student.getExpandoBridge().getAttribute(deregisterMailExpando,false)!=null){
							deregisterMail = Boolean.parseBoolean(String.valueOf(student.getExpandoBridge().getAttribute(deregisterMailExpando,false)));
						}
					}
					
					log.debug("---STUDENT MAIL 3: "+userSender.getEmailAddress());
					log.debug("---STUDENT ID 3: "+userSender.getUserId());
					log.debug("---DEREGISTER MAIL 3: "+deregisterMail);
					log.debug("--- EXPANDO 3: "+userSender.getExpandoBridge().getAttribute(deregisterMailExpando,false));
					
					
					if(!deregisterMail){
						if (nUsers > 0 && sendMails == nUsers) {
							try {
								if(_log.isDebugEnabled())
									_log.debug("Se ha llegado al numero maximo de envios en el bloque, se para " + millis + " milisegundos.");
							    Thread.sleep(millis);
							}
							catch(InterruptedException ex) {
							    Thread.currentThread().interrupt();
							}
							sendMails = 0;
						}
						
						try {
							InternetAddress to = new InternetAddress(student.getEmailAddress(), student.getFullName());
							if(_log.isDebugEnabled()) {
								_log.debug("Se envia un correo electronico al siguiente usuario: " + student.getEmailAddress());
							}
							
							calculatedSubject = MailUtil.replaceStudent(subjectTemplate, student.getFullName(), student.getScreenName(), student.getFirstName());
							//Sustituir expandos de usuario
							if(showExpandosUser)
								calculatedSubject = MailUtil.replaceExpandosUser(calculatedSubject, companyId, student, student.getLocale());
							String content = MailUtil.replaceStudent(bodyTemplate, student.getFullName(), student.getScreenName(), student.getFirstName());
							//Sustituir expandos de usuario
							if(showExpandosUser)
								content = MailUtil.replaceExpandosUser(content, companyId, student, student.getLocale());
							calculatedBody = PrefsPropsUtil.getString(companyId, MailConstants.HEADER_PREFS, LanguageUtil.get(student.getLocale(),"mail.header"));
							calculatedBody += content;
							calculatedBody += PrefsPropsUtil.getString(companyId, MailConstants.FOOTER_PREFS, LanguageUtil.get(student.getLocale(),"mail.footer"));
							
							if(log.isDebugEnabled()) {
								log.debug("Se envia el siguiente correo...");
								log.debug("De: " + from);
								log.debug("A: " + toMail + " " + userName);
								log.debug("Profesores: " + tutors);
								log.debug("Asunto: " + calculatedSubject);
								log.debug("Cuerpo: " + calculatedBody);
							}
							
							MailMessage mailm = new MailMessage(from, to, calculatedSubject, calculatedBody ,true);
							//MailEngine.send(mailm);
							if(attachments!=null && attachments.length>0 && attachmentNames!=null && attachmentNames.length>0 && attachments.length == attachmentNames.length){
								for(int i = 0; i<attachments.length ; i++){
									mailm.addFileAttachment(attachments[i], attachmentNames[i]);
								}
							}
							try{
								if(internalMessagingActive){
									MailUtil.sendInternalMessageNotification(entryId,calculatedSubject,content, groupId, userId, student.getUserId(), companyId, attachments, attachmentNames);
								}
								sendMail(mailm,transport,session);
								addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), student.getEmailAddress(), MailConstants.STATUS_OK, false);
							}catch(MessagingException ex){
								ex.printStackTrace();
								addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), student.getEmailAddress(), MailConstants.STATUS_KO, false);
								log.error("*****************ERROR al enviar mail["+student.getEmailAddress()+"]*****************");
								if(!transport.isConnected()){
									log.debug("TRANSPORT NOT CONNECTED. RECONECTAMOS");
									transport.connect(smtpHost, smtpPort, smtpuser, password);
									log.debug("***Reenviando el mail que no se pudo enviar["+student.getEmailAddress()+"]");
									sendMail(mailm,transport,session);
	
									addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), student.getEmailAddress(), MailConstants.STATUS_OK, false);
								}
							}
						}
						catch(Exception meEx){
	
							addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), student.getEmailAddress(), MailConstants.STATUS_KO, false);
							meEx.printStackTrace();
						}
					
						sendMails++;
					}
				}
			}
			
			// Se envían los correos a las relaciones sociales si corresponde
			if(sendCopyToSocialRelation){
				for (User socialRelatedUser : sendToSocialRelationUsers) {
					if (socialRelatedUser.isActive() && Validator.isEmailAddress(socialRelatedUser.getEmailAddress())) {
						deregisterMail = false;
						if(!sendAlwaysMessage){
							if(socialRelatedUser.getExpandoBridge().getAttribute(deregisterMailExpando,false)!=null){
								deregisterMail = Boolean.parseBoolean(String.valueOf(socialRelatedUser.getExpandoBridge().getAttribute(deregisterMailExpando,false)));
							}
						}
						
						log.debug("---STUDENT MAIL 3: "+userSender.getEmailAddress());
						log.debug("---STUDENT ID 3: "+userSender.getUserId());
						log.debug("---DEREGISTER MAIL 3: "+deregisterMail);
						log.debug("--- EXPANDO 3: "+userSender.getExpandoBridge().getAttribute(deregisterMailExpando,false));
						
						
						if(!deregisterMail){
							if (nUsers > 0 && sendMails == nUsers) {
								try {
									if(_log.isDebugEnabled())
										_log.debug("Se ha llegado al numero maximo de envios en el bloque, se para " + millis + " milisegundos.");
								    Thread.sleep(millis);
								}
								catch(InterruptedException ex) {
								    Thread.currentThread().interrupt();
								}
								sendMails = 0;
							}
							
							try {
								InternetAddress to = new InternetAddress(socialRelatedUser.getEmailAddress(), socialRelatedUser.getFullName());
								if(_log.isDebugEnabled()) {
									_log.debug("Se envia un correo electronico al siguiente usuario: " + socialRelatedUser.getEmailAddress());
								}
								String toFullName = LanguageUtil.get(socialRelatedUser.getLocale(),"groupmailing.messages.student-full-name");
								String toScreenName = LanguageUtil.get(socialRelatedUser.getLocale(),"groupmailing.messages.student-screen-name");
								String toFirstName = LanguageUtil.get(socialRelatedUser.getLocale(),"groupmailing.messages.student-name");
								
								calculatedSubject = MailUtil.replaceStudent(subjectTemplate, toFullName, toScreenName, toFirstName);
								//Sustituir expandos
								if(showExpandosUser)
									calculatedSubject = MailUtil.replaceExpandosUser(calculatedSubject, companyId, null, socialRelatedUser.getLocale());
								String content = MailUtil.replaceStudent(bodyTemplate, toFullName, toScreenName, toFirstName);
								//Sustituir expandos
								if(showExpandosUser)
									content = MailUtil.replaceExpandosUser(content, companyId, null, socialRelatedUser.getLocale());
								
								String extraContentSocialRelation = MailUtil.getExtraContentSocialRelation(users, socialRelatedUser, sendToRelationTypeIds);
								calculatedBody = MailUtil.getExtraContentSocialRelationHeader(socialRelatedUser) + extraContentSocialRelation;
								//calculatedBody += MailUtil.getExtraContentSocialRelation(users, socialRelatedUser, sendToRelationTypeIds);
								calculatedBody += PrefsPropsUtil.getString(companyId, MailConstants.HEADER_PREFS, LanguageUtil.get(socialRelatedUser.getLocale(),"mail.header"));
								calculatedBody += content;
								calculatedBody += PrefsPropsUtil.getString(companyId, MailConstants.FOOTER_PREFS, LanguageUtil.get(socialRelatedUser.getLocale(),"mail.footer"));
								
								if(log.isDebugEnabled()) {
									log.debug("Se envia el siguiente correo...");
									log.debug("De: " + from);
									log.debug("A: " + toMail + " " + userName);
									log.debug("Profesores: " + tutors);
									log.debug("Asunto: " + calculatedSubject);
									log.debug("Cuerpo: " + calculatedBody);
								}
								
								MailMessage mailm = new MailMessage(from, to, calculatedSubject, calculatedBody ,true);
								//MailEngine.send(mailm);
								if(attachments!=null && attachments.length>0 && attachmentNames!=null && attachmentNames.length>0 && attachments.length == attachmentNames.length){
									for(int i = 0; i<attachments.length ; i++){
										mailm.addFileAttachment(attachments[i], attachmentNames[i]);
									}
								}
								
								try{
									if(internalMessagingActive){
										content = MailUtil.getExtraContentSocialRelationHeader(socialRelatedUser) + extraContentSocialRelation + content;
										MailUtil.sendInternalMessageNotification(entryId,calculatedSubject,content, groupId, userId, socialRelatedUser.getUserId(), companyId, attachments, attachmentNames);
									}
									sendMail(mailm,transport,session);
									addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), socialRelatedUser.getEmailAddress(), MailConstants.STATUS_OK, false);
								}catch(MessagingException ex){
									ex.printStackTrace();
									addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), socialRelatedUser.getEmailAddress(), MailConstants.STATUS_KO, false);
									log.error("*****************ERROR al enviar mail["+socialRelatedUser.getEmailAddress()+"]*****************");
									if(!transport.isConnected()){
										log.debug("TRANSPORT NOT CONNECTED. RECONECTAMOS");
										transport.connect(smtpHost, smtpPort, smtpuser, password);
										log.debug("***Reenviando el mail que no se pudo enviar["+socialRelatedUser.getEmailAddress()+"]");
										sendMail(mailm,transport,session);
		
										addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), socialRelatedUser.getEmailAddress(), MailConstants.STATUS_OK, false);
									}
								}
							}
							catch(Exception meEx){
		
								addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), socialRelatedUser.getEmailAddress(), MailConstants.STATUS_KO, false);
								meEx.printStackTrace();
							}
						
							sendMails++;
						}
					}
				}
			}
			
			transport.close();
			
			_log.debug("Se finaliza el envio de correos electronicos");
		
			//Guardar una auditoria del envio de emails.
			auditSendMails.setSendDate(new Date(System.currentTimeMillis()));
			auditSendMails.setNumberOfPost(numUsersSender);
			AuditSendMailsLocalServiceUtil.updateAuditSendMails(auditSendMails); 
		}
	}
		
	public static void addAuditReceiverMail(long auditSendMailsId, String toMail, int status, boolean hasDate){
		try {
			AuditReceiverMail auditReceiverMail = AuditReceiverMailLocalServiceUtil.createAuditReceiverMail(CounterLocalServiceUtil.increment(AuditReceiverMail.class.getName()));
			auditReceiverMail.setAuditSendMailsId(auditSendMailsId);
			auditReceiverMail.setTo(toMail);
			auditReceiverMail.setStatus(status);
			auditReceiverMail.setSendDate(hasDate ? new Date(System.currentTimeMillis()) : null);
			AuditReceiverMailLocalServiceUtil.addAuditReceiverMail(auditReceiverMail);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String _getSMTPProperty(Session session, String suffix) {
		String protocol = GetterUtil.getString(
			session.getProperty("mail.transport.protocol"));

		if (protocol.equals(Account.PROTOCOL_SMTPS)) {
			return session.getProperty("mail.smtps." + suffix);
		}
		else {
			return session.getProperty("mail.smtp." + suffix);
		}
	}
	
	private void sendMail(MailMessage mailm, Transport transport,Session session) throws MessagingException {
		javax.mail.Message message = new MimeMessage(session);
		message.setFrom(mailm.getFrom());
		message.setRecipients(javax.mail.Message.RecipientType.TO,mailm.getTo());
		message.setSubject(mailm.getSubject());
		
		  // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();
        if (mailm.isHTMLFormat()) {
        	messageBodyPart.setContent(mailm.getBody(), "text/html;charset=\"UTF-8\"");
		}
		else {
			messageBodyPart.setContent(mailm.getBody(),"text/plain;charset=\"UTF-8\"");
		}
       
        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);
        DataSource source = null;
        // Part two is attachment
        for(FileAttachment attachment : mailm.getFileAttachments()){
        	 messageBodyPart = new MimeBodyPart();
             String filename = attachment.getFileName();
             source = new FileDataSource(attachment.getFile());	 
             messageBodyPart.setDataHandler(new DataHandler(source));
             messageBodyPart.setFileName(filename);
             multipart.addBodyPart(messageBodyPart);
        }
        // Send the complete message parts
        message.setContent(multipart);
		transport.sendMessage(message, mailm.getTo());
		
	}
	
	private static Log _log = LogFactoryUtil.getLog(LmsMailMessageListener.class);

}
