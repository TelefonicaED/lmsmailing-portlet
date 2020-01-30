package com.tls.liferaylms.mail;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.ProcessAction;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.MailRelationLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;
import com.tls.liferaylms.util.MailStringPool;
import com.tls.liferaylms.util.MailUtil;

/**
 * Portlet implementation class GroupMailing
 */
public class GroupMailing extends MVCPortlet{
	
	private static Log _log = LogFactoryUtil.getLog(GroupMailing.class);
	
	public void sendMails(ActionRequest actionRequest, ActionResponse actionResponse)
	throws Exception 
	{	
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		int idTemplate=ParamUtil.getInteger(actionRequest, "template");
		String testing = ParamUtil.getString(actionRequest, "testing", "false");
		
		MailTemplate template = MailTemplateLocalServiceUtil.getMailTemplate(idTemplate);

		if(template != null){
			PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
			PortletPreferences preferences = null;
			String portletResource = ParamUtil.getString(actionRequest, "portletResource");
			if (Validator.isNotNull(portletResource)) {
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
			}else{
				preferences = actionRequest.getPreferences();
			}
			boolean ownTeam = (preferences.getValue("ownTeam", "false")).compareTo("true") == 0;
			
			String tutors = MailUtil.getTutors(themeDisplay.getScopeGroupId());
			
			long entryId = -1;
			boolean internalMessagingActive = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), MailStringPool.INTERNAL_MESSAGING_KEY);
			
			if(internalMessagingActive){
				_log.debug("Sending internal message ");
				String content =  MailUtil.replaceMessageConstants(changeToURL(template.getBody(), themeDisplay.getURLPortal()), themeDisplay.getCompany().getName(), 
						themeDisplay.getScopeGroupName(), themeDisplay.getUser().getFullName(), themeDisplay.getUser().getScreenName(),  tutors, 
						MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest), MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL(),
						MailUtil.getCourseStartDate(themeDisplay.getScopeGroupId(), themeDisplay.getLocale(), themeDisplay.getTimeZone()), MailUtil.getCourseEndDate(themeDisplay.getScopeGroupId(), themeDisplay.getLocale(), themeDisplay.getTimeZone()),themeDisplay.getUser().getFullName());
			
				AnnouncementsEntry entry = MailUtil.createInternalMessageNotification(template.getSubject(),content, themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), themeDisplay.getCompanyId());
				if(entry!=null){
					entryId = entry.getEntryId();
				}
			}
			
			
			Message message=new Message();
			
			message.put("templateId",idTemplate);
			message.put("entryId", entryId);
			message.put("to", "all");
			message.put("tutors", tutors);
			message.put("ownTeam", ownTeam);
			message.put("isOmniadmin", permissionChecker.isOmniadmin());
			
			message.put("subject", 	template.getSubject());
			message.put("body", 	changeToURL(template.getBody(), themeDisplay.getURLPortal()));
			message.put("groupId", 	themeDisplay.getScopeGroupId());
			message.put("userId",  	themeDisplay.getUserId());
			message.put("testing", 	testing);
			
			message.put("portal", 	themeDisplay.getCompany().getName());
			message.put("community",themeDisplay.getScopeGroupName());
			
			message.put("url", 	MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest));
			message.put("urlcourse",MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
				
			MessageBusUtil.sendMessage("lms/mailing", message);
			
			if(_log.isInfoEnabled())
				_log.trace("ManageTemplates: addMailTemplate " + template.getIdTemplate());

		}
		actionResponse.setRenderParameter("jspPage", "/html/groupmailing/view.jsp");
		
	}

	@ProcessAction(name = "sendNewMail")
	public void sendNewMail(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception 
	{	
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String subject 	= ParamUtil.getString(actionRequest, "subject", "");
		
		_log.debug("SUBJECT "+subject);
		
		subject = SanitizerUtil.sanitize(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), StringPool.BLANK, 0, ContentTypes.TEXT_PLAIN, subject);
	    _log.debug("SUBJECT AFTER SANITIZE "+subject);
		
		
		String body 	= ParamUtil.getString(actionRequest, "body", "");
		body = SanitizerUtil.sanitize(themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), StringPool.BLANK, 0, ContentTypes.TEXT_HTML, body);
		 _log.debug("BODY AFTER SANITIZE "+body);	
		String testing 	= ParamUtil.getString(actionRequest, "testing", "false");
		boolean testMessage = testing.equals(StringPool.TRUE);
		
		String to 		= ParamUtil.getString(actionRequest, "to", "");
		String tutors 	= MailUtil.getTutors(themeDisplay.getScopeGroupId());
		
		long entryId = -1;
		boolean internalMessagingActive = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), MailStringPool.INTERNAL_MESSAGING_KEY);
		
		if (_log.isDebugEnabled()) _log.debug("To: " + to);
		if (_log.isDebugEnabled()) _log.debug("to.isEmpty(): " + to.isEmpty());
		if (_log.isDebugEnabled()) _log.debug("to.containsteam_: " + to.contains("team_"));
		
		//Si no se ha introducido el asunto o el email.
		if(body.equals("") || subject.equals("")){
			SessionErrors.add(actionRequest, "campos-necesarios-vacios");
			actionResponse.setRenderParameter("jspPage","/html/groupmailing/view.jsp");
			return;
		}
		
		//Envío de copia a usuarios relacionados
		List<Integer> mailRelationTypeIds = MailRelationLocalServiceUtil.findRelationTypeIdsByCompanyId(themeDisplay.getCompanyId());
		List<Integer> sendCopyToTypeIds = new ArrayList<Integer>();
		if(Validator.isNotNull(mailRelationTypeIds) && mailRelationTypeIds.size()>0){
			String sendMailToRelationType = StringPool.BLANK;
			boolean isActiveSendMailToRelationType = Boolean.FALSE;
			for(int mailRelationTypeId:mailRelationTypeIds){
				sendMailToRelationType = "sendMailToType_"+mailRelationTypeId;
				isActiveSendMailToRelationType = ParamUtil.getBoolean(actionRequest, sendMailToRelationType, Boolean.FALSE);
				if(isActiveSendMailToRelationType)
					sendCopyToTypeIds.add(mailRelationTypeId);
			}
		}
		
		_log.debug("::::sendCopyToTypeIds "+sendCopyToTypeIds.size());
		//Enviar email a usuarios relacionados
		boolean sendCopyToSocialRelation = sendCopyToTypeIds.size()>0;
		if(_log.isDebugEnabled())
			_log.debug(":::sendCopyToSocialRelation:: " + sendCopyToSocialRelation); 
	
		if(internalMessagingActive){
			_log.debug("Sending internal message ");
			
			String content =  MailUtil.replaceMessageConstants(changeToURL(body, themeDisplay.getURLPortal()), themeDisplay.getCompany().getName(), 
					themeDisplay.getScopeGroupName(), themeDisplay.getUser().getFullName(), themeDisplay.getUser().getScreenName(),  tutors, 
					MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest), MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL(), 
					MailUtil.getCourseStartDate(themeDisplay.getScopeGroupId(), themeDisplay.getLocale(), themeDisplay.getTimeZone()), MailUtil.getCourseEndDate(themeDisplay.getScopeGroupId(), themeDisplay.getLocale(), themeDisplay.getTimeZone()),themeDisplay.getUser().getFullName());
			
			AnnouncementsEntry entry = MailUtil.createInternalMessageNotification(subject, content, themeDisplay.getScopeGroupId(), themeDisplay.getUserId(), themeDisplay.getCompanyId());
			if(entry!=null){
				entryId = entry.getEntryId();
			}
		}
		
		if(testMessage){
			if (_log.isDebugEnabled()) _log.debug("Testing Mode: " + themeDisplay.getUser().getEmailAddress());
			
			Message message=new Message();
			
			message.put("templateId",-1);
			message.put("entryId", entryId);
			message.put("to", themeDisplay.getUser().getEmailAddress());
			message.put("tutors", tutors);
			message.put("userName", themeDisplay.getUser().getFullName());
			message.put("subject", 	subject);
			message.put("body", 	changeToURL(body, themeDisplay.getURLPortal()));
			message.put("groupId", 	themeDisplay.getScopeGroupId());
			message.put("userId",  	themeDisplay.getUserId());
			message.put("testing", 	testing);
			message.put("sendToRelatedUsers", sendCopyToSocialRelation);
			
			message.put("portal", 	themeDisplay.getCompany().getName());
			message.put("community",themeDisplay.getScopeGroupName());
			
			message.put("url", 		MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest));
			message.put("urlcourse",MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
			MessageBusUtil.sendMessage("lms/mailing", message);
		}else{
			if(to.isEmpty()){
				
				if (_log.isDebugEnabled()) _log.debug("Enviamos a todos los usuarios");
				
				PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
				PortletPreferences preferences = null;
				String portletResource = ParamUtil.getString(actionRequest, "portletResource");
				if (Validator.isNotNull(portletResource)) {
					preferences = PortletPreferencesFactoryUtil.getPortletSetup(actionRequest, portletResource);
				}else{
					preferences = actionRequest.getPreferences();
				}
				boolean ownTeam = (preferences.getValue("ownTeam", "false")).compareTo("true") == 0;

				Message message=new Message();
				message.put("templateId",-1);
				message.put("to", "all");
				message.put("tutors", tutors);
				message.put("ownTeam", ownTeam);
				message.put("isOmniadmin", permissionChecker.isOmniadmin());
				message.put("entryId", entryId);
				message.put("subject", 	subject);
				message.put("body", 	changeToURL(body, themeDisplay.getURLPortal()));
				message.put("groupId", 	themeDisplay.getScopeGroupId());
				message.put("userId",  	themeDisplay.getUserId());
				message.put("testing", 	testing);
				message.put("sendToRelatedUsers", sendCopyToSocialRelation);
				message.put("mailRelationTypeIds", sendCopyToTypeIds);
				
				message.put("portal", 	themeDisplay.getCompany().getName());
				message.put("community",themeDisplay.getScopeGroupName());
				
				message.put("url", 		MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest));
				message.put("urlcourse",MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
				
				MessageBusUtil.sendMessage("lms/mailing", message);
				
			}else if (to.contains("team_")){
				
				String teamId = to.replace("team_", "");
				if (_log.isDebugEnabled()) _log.debug("Enviamos al grupo de usuarios " + teamId);
				LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();
				userParams.put("usersTeams", Long.parseLong(teamId));
				OrderByComparator obc = new UserFirstNameComparator(true);
				List<User> teamUsers = UserLocalServiceUtil.search(themeDisplay.getCompanyId(), "", 0, userParams, QueryUtil.ALL_POS, QueryUtil.ALL_POS, obc);	
				List<User> socialRelationUsers = new ArrayList<User>();
				
				for (User user : teamUsers) {
					if (_log.isDebugEnabled()) _log.debug("user: " + user.getEmailAddress());
					
					//Si corresponde se buscan los usuarios con relaciones sociales con los usuarios del equipo
					socialRelationUsers = sendCopyToSocialRelation ? MailUtil.getSocialRelationUsers(user, sendCopyToTypeIds, socialRelationUsers, themeDisplay.getCompanyId()) : new ArrayList<User>();
					
					Message message=new Message();
					
					message.put("templateId",-1);
					message.put("entryId", entryId);
					message.put("to", user.getEmailAddress());
					message.put("tutors", tutors);
					message.put("userName", user.getFullName());
					message.put("subject", 	subject);
					message.put("body", 	changeToURL(body, themeDisplay.getURLPortal()));
					message.put("groupId", 	themeDisplay.getScopeGroupId());
					message.put("userId",  	themeDisplay.getUserId());
					message.put("testing", 	testing);
					message.put("sendToRelatedUsers", sendCopyToSocialRelation);
					message.put("mailRelationTypeIds", sendCopyToTypeIds);
					message.put("isUserRelated", false);
					
					message.put("portal", 	themeDisplay.getCompany().getName());
					message.put("community",themeDisplay.getScopeGroupName());
					
					message.put("url", 		MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest));
					message.put("urlcourse",MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
					MessageBusUtil.sendMessage("lms/mailing", message);
				}
				
				//Envio de emails a usuarios relacionados si corresponde
				_log.debug(":::socialRelationUsers.size():: " + socialRelationUsers.size());
				if(socialRelationUsers.size()>0){
					for(User socialRelatedUser:socialRelationUsers){
						
						Message message=new Message();
						
						message.put("templateId",-1);
						message.put("entryId", entryId);
						message.put("to", socialRelatedUser.getEmailAddress());
						message.put("tutors", tutors);
						message.put("userName", socialRelatedUser.getFullName());
						message.put("subject", 	subject);
						message.put("body", 	changeToURL(body, themeDisplay.getURLPortal()));
						message.put("groupId", 	themeDisplay.getScopeGroupId());
						message.put("userId",  	themeDisplay.getUserId());
						message.put("testing", 	testing);
						message.put("sendToRelatedUsers", sendCopyToSocialRelation);
						message.put("isUserRelated", true);
						message.put("mailRelationTypeIds", sendCopyToTypeIds);
						message.put("emailSentToUsersList", teamUsers);
						
						message.put("portal", 	themeDisplay.getCompany().getName());
						message.put("community",themeDisplay.getScopeGroupName());
						
						message.put("url", 		MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest));
						message.put("urlcourse",MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
						MessageBusUtil.sendMessage("lms/mailing", message);
					}
				}
				
			}else {
				
				if (_log.isDebugEnabled()) _log.debug("Enviamos a los usuarios seleccionados");
				
				String userIds[] = to.split(",");
				List<User> sentToUsersList = new ArrayList<User>();
				
				List<User> socialRelationUsers = new ArrayList<User>();
				
				for(String id:userIds){
					
					if(!id.trim().isEmpty()){
						
						User user = UserLocalServiceUtil.getUser(Long.valueOf(id));
						if (_log.isDebugEnabled()) _log.debug("user: " + user.getEmailAddress());
						sentToUsersList.add(user);
						
						//Si corresponde se buscan los usuarios con relaciones sociales con los usuarios seleccionados
						socialRelationUsers = sendCopyToSocialRelation ? MailUtil.getSocialRelationUsers(user, sendCopyToTypeIds, socialRelationUsers, themeDisplay.getCompanyId()) : new ArrayList<User>();
						
						Message message=new Message();
						
						message.put("templateId",-1);
						message.put("entryId", entryId);
						message.put("to", user.getEmailAddress());
						message.put("tutors", tutors);
						message.put("userName", user.getFullName());
						message.put("subject", 	subject);
						message.put("body", 	changeToURL(body, themeDisplay.getURLPortal()));
						message.put("groupId", 	themeDisplay.getScopeGroupId());
						message.put("userId",  	themeDisplay.getUserId());
						message.put("testing", 	testing);
						message.put("sendToRelatedUsers", sendCopyToSocialRelation);
						message.put("isUserRelated", false);
						message.put("mailRelationTypeIds", sendCopyToTypeIds);

						message.put("portal", 	themeDisplay.getCompany().getName());
						message.put("community",themeDisplay.getScopeGroupName());
						
						message.put("url", 	MailUtil.getURLPortal(themeDisplay.getCompany(), actionRequest));
						message.put("urlcourse", MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
							
						MessageBusUtil.sendMessage("lms/mailing", message);
					}
				}
				//Envio de emails a usuarios relacionados si corresponde
				_log.debug(":::socialRelationUsers.size():: " + socialRelationUsers.size());
				if(socialRelationUsers.size()>0){
					for(User socialRelatedUser:socialRelationUsers){
						
						Message message=new Message();
						
						message.put("templateId",-1);
						message.put("entryId", entryId);
						message.put("to", socialRelatedUser.getEmailAddress());
						message.put("tutors", tutors);
						message.put("userName", socialRelatedUser.getFullName());
						message.put("subject", 	subject);
						message.put("body", 	changeToURL(body, themeDisplay.getURLPortal()));
						message.put("groupId", 	themeDisplay.getScopeGroupId());
						message.put("userId",  	themeDisplay.getUserId());
						message.put("testing", 	testing);
						message.put("sendToRelatedUsers", sendCopyToSocialRelation);
						message.put("isUserRelated", true);
						message.put("mailRelationTypeIds", sendCopyToTypeIds);
						message.put("emailSentToUsersList", sentToUsersList);
						
						message.put("portal", 	themeDisplay.getCompany().getName());
						message.put("community",themeDisplay.getScopeGroupName());
						
						message.put("url", 		MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest));
						message.put("urlcourse",MailUtil.getURLPortal(themeDisplay.getCompany(),actionRequest)+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
						MessageBusUtil.sendMessage("lms/mailing", message);
					}
				}
			}
		}
		
	
		if(_log.isInfoEnabled()){
			_log.trace("ManageTemplates: sendNewMail\nTo: "+to+"\ntutors: "+tutors+"\nSubject:\n" + subject +"\nBody:\n"+body);
		}
		
		actionResponse.setRenderParameter("jspPage", "/html/groupmailing/view.jsp");
	}
	
	//Para imagenes
	private String changeToURL(String text, String url){
		
		text =  text.contains("img") ? 
				text.replace("src=\"/", "src=\"" + url + StringPool.SLASH) : 
				text;
		
		return text;
	}

}
