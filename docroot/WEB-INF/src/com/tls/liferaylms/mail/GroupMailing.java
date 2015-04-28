package com.tls.liferaylms.mail;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ProcessAction;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;

/**
 * Portlet implementation class GroupMailing
 */
public class GroupMailing extends MVCPortlet{
	
	private static Log _log = LogFactoryUtil.getLog(GroupMailing.class);
	
	public void preview(ActionRequest actionRequest, ActionResponse actionResponse)
			throws Exception 
			{	
		int idTemplate=ParamUtil.getInteger(actionRequest, "template");
		
		actionResponse.setRenderParameter("idTemplate", String.valueOf(idTemplate));
				
		actionResponse.setRenderParameter("jspPage", "/html/groupmailing/preview.jsp");
	}
	
	public void sendMails(ActionRequest actionRequest, ActionResponse actionResponse)
	throws Exception 
	{	
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		int idTemplate=ParamUtil.getInteger(actionRequest, "template");
		String testing = ParamUtil.getString(actionRequest, "testing", "false");
		
		MailTemplate template = MailTemplateLocalServiceUtil.getMailTemplate(idTemplate);

		if(template != null){
			
			Message message=new Message();
			
			message.put("templateId",idTemplate);
			
			message.put("to", "all");
			
			message.put("subject", 	template.getSubject());
			message.put("body", 	template.getBody());
			message.put("groupId", 	themeDisplay.getScopeGroupId());
			message.put("userId",  	themeDisplay.getUserId());
			message.put("testing", 	testing);
			
			message.put("portal", 	themeDisplay.getCompany().getName());
			message.put("community",themeDisplay.getScopeGroupName());
			
			message.put("url", 		themeDisplay.getURLPortal());
			message.put("urlcourse",themeDisplay.getURLPortal()+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
				
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
		String body 	= ParamUtil.getString(actionRequest, "body", "");
		String testing 	= ParamUtil.getString(actionRequest, "testing", "false");
		
		String to 	= ParamUtil.getString(actionRequest, "to", "");
		
		//System.out.println(subject+" "+body+" "+testing+" "+to+"\n");
		
		//Si no se ha introducido el asunto o el email.
		if(body.equals("") || subject.equals("")){
			SessionErrors.add(actionRequest, "campos-necesarios-vacios");
			actionResponse.setRenderParameter("jspPage","/html/groupmailing/view.jsp");
			return;
		}
	
		if(to.isEmpty()){

			Message message=new Message();
			
			message.put("templateId",-1);
			
			message.put("to", "all");
			
			message.put("subject", 	subject);
			message.put("body", 	body);
			message.put("groupId", 	themeDisplay.getScopeGroupId());
			message.put("userId",  	themeDisplay.getUserId());
			message.put("testing", 	testing);
			
			message.put("portal", 	themeDisplay.getCompany().getName());
			message.put("community",themeDisplay.getScopeGroupName());
			
			message.put("url", 		themeDisplay.getURLPortal());
			message.put("urlcourse",themeDisplay.getURLPortal()+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
				
			MessageBusUtil.sendMessage("lms/mailing", message);
			

		} else {
			
			String userIds[] = to.split(",");
			
			for(String id:userIds){

				if(!id.trim().isEmpty()){
					
					User user = UserLocalServiceUtil.getUser(Long.valueOf(id));
					
					Message message=new Message();
					
					message.put("templateId",-1);
					
					message.put("to", user.getEmailAddress());
					message.put("userName", user.getFullName());
					message.put("subject", 	subject);
					message.put("body", 	body);
					message.put("groupId", 	themeDisplay.getScopeGroupId());
					message.put("userId",  	themeDisplay.getUserId());
					message.put("testing", 	testing);
					
					message.put("portal", 	themeDisplay.getCompany().getName());
					message.put("community",themeDisplay.getScopeGroupName());
					
					message.put("url", 		themeDisplay.getURLPortal());
					message.put("urlcourse",themeDisplay.getURLPortal()+themeDisplay.getPathFriendlyURLPublic()+themeDisplay.getScopeGroup().getFriendlyURL());
						
					MessageBusUtil.sendMessage("lms/mailing", message);
					
				}
				
			}
			
		}
	
		if(_log.isInfoEnabled()){
			_log.trace("ManageTemplates: sendNewMail\nTo: "+to+"\nSubject:\n" + subject +"\nBody:\n"+body);
		}
		
		actionResponse.setRenderParameter("jspPage", "/html/groupmailing/view.jsp");
	}
	
}
