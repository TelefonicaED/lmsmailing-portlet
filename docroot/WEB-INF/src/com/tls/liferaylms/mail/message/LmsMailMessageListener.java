package com.tls.liferaylms.mail.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.lms.model.Course;
import com.liferay.lms.model.LmsPrefs;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.LmsPrefsLocalServiceUtil;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Team;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.TeamLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.comparator.UserLastNameComparator;
import com.liferay.util.mail.MailEngine;
import com.tls.liferaylms.mail.model.AuditReceiverMail;
import com.tls.liferaylms.mail.model.AuditSendMails;
import com.tls.liferaylms.mail.service.AuditReceiverMailLocalServiceUtil;
import com.tls.liferaylms.mail.service.AuditSendMailsLocalServiceUtil;
import com.tls.liferaylms.util.MailStringPool;


public class LmsMailMessageListener implements MessageListener {
	private static Log log = LogFactoryUtil.getLog(LmsMailMessageListener.class);
	public static int STATUS_OK = 1;
	public static int STATUS_KO = 0;
	public static final String TYPE_INSCRIPTION = "COURSE_INSCRIPTION";
	public static final String TYPE_MAILJOB = "MAIL_JOB";
	public static final String TYPE_MASSIVE = "MASS_MAILING";
	
			
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
				auditSendMails.setType_(TYPE_INSCRIPTION);
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
		String userName = message.getString("userName");
		boolean ownTeam = message.getBoolean("ownTeam");
		boolean isOmniadmin = message.getBoolean("isOmniadmin");
		long numUsersSender = 0;
		boolean deregisterMail;
		String type_ = message.getString("type");
		
		if (_log.isDebugEnabled())
			_log.debug("type_::"+type_);

		
		String fromName = PrefsPropsUtil.getString(companyId, PropsKeys.ADMIN_EMAIL_FROM_NAME);
		String fromAddress = PrefsPropsUtil.getString(companyId, PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);
		
		long nUsers = 0,millis = 0;
		String numberUsers = PrefsPropsUtil.getString("lmsmailing.sendmails.number.users");
		String milliseconds = PrefsPropsUtil.getString("lmsmailing.sendmails.wating.time.milliseconds");
		
		if(Validator.isNotNull(numberUsers)){
			nUsers = Long.valueOf(numberUsers);
		}
		
		if(milliseconds != null && !milliseconds.equals("")){
			millis = Long.valueOf(milliseconds);
		}
		
		InternetAddress from = new InternetAddress(fromAddress, fromName);
		User userSender = UserLocalServiceUtil.getUserById(userId);
		if ("true".equals(testing)) {
			if(_log.isDebugEnabled()) {
				_log.debug("Se entra en modo testing");
			}
			InternetAddress to = new InternetAddress(userSender.getEmailAddress(), userSender.getFullName());
			deregisterMail = false;
			if(userSender.getExpandoBridge().getAttribute(MailStringPool.DEREGISTER_USER_EXPANDO)!=null){
				deregisterMail = (Boolean)userSender.getExpandoBridge().getAttribute(MailStringPool.DEREGISTER_USER_EXPANDO);
			}
			
			if(!deregisterMail){
				body = createMessage(body, portal, community, userSender.getFullName(), UserLocalServiceUtil.getUserById(userId).getFullName(), url, urlcourse);

				String calculatedBody = LanguageUtil.get(Locale.getDefault(),"mail.header");
				calculatedBody += body;
				calculatedBody += LanguageUtil.get(Locale.getDefault(),"mail.footer");
				
				subject = createMessage(subject, portal, community, userSender.getFullName(), userSender.getFullName(),url,urlcourse);
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
				
				try{
					MailMessage mailm = new MailMessage(from, to, subject, calculatedBody, true);
					MailServiceUtil.sendEmail(mailm);
					addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), userSender.getEmailAddress(), STATUS_OK, false);
				}catch(Exception e){
					addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), userSender.getEmailAddress(), STATUS_KO, false);
					e.printStackTrace();
				}
				//Guardar una auditoria del envio de emails.
				auditSendMails.setSendDate(new Date(System.currentTimeMillis()));
				auditSendMails.setNumberOfPost(numUsersSender);
				AuditSendMailsLocalServiceUtil.updateAuditSendMails(auditSendMails); 
			}
			
			
		}else if(toMail != null && !toMail.equals("all")) {
			if(_log.isDebugEnabled()) {
				_log.debug("Se entra en el envio individual de correos.");
			}
			
			User student = UserLocalServiceUtil.fetchUserByEmailAddress(userSender.getCompanyId(), toMail);
			
			if(student != null && student.isActive() && Validator.isEmailAddress(student.getEmailAddress())) {
				deregisterMail = false;
				if(student.getExpandoBridge().getAttribute(MailStringPool.DEREGISTER_USER_EXPANDO)!=null){
					deregisterMail = (Boolean)student.getExpandoBridge().getAttribute(MailStringPool.DEREGISTER_USER_EXPANDO);
				}
				
				if(!deregisterMail){
					InternetAddress to = new InternetAddress(toMail, student.getFullName());

					String calculatedBody = LanguageUtil.get(student.getLocale(),"mail.header");
					calculatedBody += createMessage(body, portal, community, student.getFullName(), userSender.getFullName(),url,urlcourse);
					calculatedBody += LanguageUtil.get(student.getLocale(),"mail.footer");
					
					String calculatedsubject = createMessage(subject, portal, community, student.getFullName(), userSender.getFullName(),url,urlcourse);
					
					if(log.isDebugEnabled()) {
						log.debug("Se envia el siguiente correo...");
						log.debug("De: " + from);
						log.debug("A: " + toMail + " " + student.getFullName());
						log.debug("Asunto: " + calculatedsubject);
						log.debug("Cuerpo: " + calculatedBody);
					}
					
					//Guardar una auditoria del envio de emails.
					boolean hasDate = false;
					
					//Miramos el tipo de env�o
					if (type_.equals(TYPE_INSCRIPTION)){

						auditSendMails = AuditSendMailsLocalServiceUtil.getInscriptionHistory(groupId, companyId);
						hasDate = true;
					
					}else{
						type_ = TYPE_MAILJOB;
						
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
						MailEngine.send(mailm);
						addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), toMail, STATUS_OK, hasDate);
					}catch(Exception e){
						addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), toMail, STATUS_KO, hasDate);
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
				List<Team> userTeams = TeamLocalServiceUtil.getUserTeams(userId);
				Course course = CourseLocalServiceUtil.getCourseByGroupCreatedId(groupId);
				LmsPrefs prefs = LmsPrefsLocalServiceUtil.getLmsPrefs(companyId);
				
				OrderByComparator obc = new   UserLastNameComparator(true);			
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
				users = UserLocalServiceUtil.getGroupUsers(groupId);
			}
			
			if(_log.isDebugEnabled()) {
				_log.debug("Se envia a " + users.size() + " usuarios.");
			}
			
			numUsersSender = users.size();
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
			

			String bodyTemplate = createTemplateMessage(body, portal, community, userSender.getFullName(), url, urlcourse);
			String subjectTemplate = createTemplateMessage(subject, portal, community, userSender.getFullName(), url, urlcourse);
			String calculatedBody, calculatedSubject;
			
			Transport transport = session.getTransport(protocol);
			
			//Guardar una auditoria del envio de emails.
			auditSendMails = AuditSendMailsLocalServiceUtil.createAuditSendMails(CounterLocalServiceUtil.increment(AuditSendMails.class.getName()));
			auditSendMails.setUserId(userId);
			auditSendMails.setGroupId(groupId);
			auditSendMails.setTemplateId(Long.parseLong(templateId));
			auditSendMails.setType_(TYPE_MASSIVE);
			
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
			
			// Se envían los correos a todos los alumnos.
			for (User student : users) {
				if (student.isActive() && Validator.isEmailAddress(student.getEmailAddress())) {
					deregisterMail = false;
					if(student.getExpandoBridge().getAttribute(MailStringPool.DEREGISTER_USER_EXPANDO)!=null){
						deregisterMail = (Boolean)student.getExpandoBridge().getAttribute(MailStringPool.DEREGISTER_USER_EXPANDO);
					}
					
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
							
							calculatedSubject = createMessage(subjectTemplate, student.getFullName());
							
							calculatedBody = LanguageUtil.get(student.getLocale(),"mail.header");
							calculatedBody += createMessage(bodyTemplate, student.getFullName());
							calculatedBody += LanguageUtil.get(student.getLocale(),"mail.footer");
							
							if(log.isDebugEnabled()) {
								log.debug("Se envia el siguiente correo...");
								log.debug("De: " + from);
								log.debug("A: " + toMail + " " + userName);
								log.debug("Asunto: " + calculatedSubject);
								log.debug("Cuerpo: " + calculatedBody);
							}
							
							MailMessage mailm = new MailMessage(from, to, calculatedSubject, calculatedBody ,true);
							//MailEngine.send(mailm);
							
							try{
								sendMail(mailm,transport,session);
								addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), student.getEmailAddress(), STATUS_OK, false);
							}catch(MessagingException ex){
								ex.printStackTrace();
								addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), student.getEmailAddress(), STATUS_KO, false);
								log.error("*****************ERROR al enviar mail["+student.getEmailAddress()+"]*****************");
								if(!transport.isConnected()){
									log.debug("TRANSPORT NOT CONNECTED. RECONECTAMOS");
									transport.connect(smtpHost, smtpPort, smtpuser, password);
									log.debug("***Reenviando el mail que no se pudo enviar["+student.getEmailAddress()+"]");
									sendMail(mailm,transport,session);
	
									addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), student.getEmailAddress(), STATUS_OK, false);
								}
							}
						}
						catch(Exception meEx){
	
							addAuditReceiverMail(auditSendMails.getAuditSendMailsId(), student.getEmailAddress(), STATUS_KO, false);
							meEx.printStackTrace();
						}
					
						sendMails++;
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
		if (mailm.isHTMLFormat()) {
			message.setContent(mailm.getBody(), "text/html;charset=\"UTF-8\"");
		}
		else {
			message.setContent(mailm.getBody(),"text/plain;charset=\"UTF-8\"");
		}
		transport.sendMessage(message, mailm.getTo());
		
	}
	
	/*
	 * Método que crea una plantilla de mensaje enviado para evitar tantos replaces. Se sustituyen todas las variables excepto las relacionadas con el usuario.
	 */
	private String createTemplateMessage (String text, String portal, String community, String teacher, String url, String urlcourse){
		String res = "";
		res = text.replace("[@portal]", 	portal);
		res = res.replace ("[@course]", 	community);
		res = res.replace ("[@teacher]", 	teacher);
		res = res.replace ("[@url]", 		"<a href=\""+url+"\">"+portal+"</a>");
		res = res.replace ("[@urlcourse]", 	"<a href=\""+urlcourse+"\">"+community+"</a>");	

		//Se cambiala URL des.
		res = changeToURL(res, url);
		
		return res;
	}
	
	/*
	 * Método que cambia cambia el nombre del usuario de la plantilla.
	 */
	private String createMessage(String text, String student) {
		if(text != null) {
			return text.replace ("[@student]", 	student);
		}
		else {
			return "";
		}
	}

	private String createMessage(String text, String portal, String community, String student, String teacher, String url, String urlcourse){
		String res = "";
		res = text.replace("[@portal]", 	portal);
		res = res.replace ("[@course]", 	community);
		res = res.replace ("[@student]", 	student);
		res = res.replace ("[@teacher]", 	teacher);
		res = res.replace ("[@url]", 		"<a href=\""+url+"\">"+portal+"</a>");
		res = res.replace ("[@urlcourse]", 	"<a href=\""+urlcourse+"\">"+community+"</a>");	

		//Para poner la url desde la pï¿½gina para que se vean los correos.
		res = changeToURL(res, url);
		
		return res;
	}

	private String changeToURL(String text, String url){
		String res ="";

		//Para imï¿½genes
		res = text.replaceAll("src=\"/image/image_gallery", "src=\""+url+"/image/image_gallery");
		
		return res;
	}

	private static Log _log = LogFactoryUtil.getLog(LmsMailMessageListener.class);

}