package com.tls.liferaylms.mail.portlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.PortletURL;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

import com.liferay.lms.model.Course;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.service.PortalPreferencesLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.PortletPreferences;

import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailRelationLocalServiceUtil;
import com.tls.liferaylms.util.MailConstants;
import com.tls.liferaylms.util.MailStringPool;



/**
 * Portlet implementation class LmsprefsManagement
 */
public class LmsMailingPreferencesManagement extends MVCPortlet {
	private static Log log = LogFactoryUtil.getLog(LmsMailingPreferencesManagement.class);
	protected String viewJSP;
		
	public void init() throws PortletException {
		// View Mode Pages
		viewJSP = getInitParameter("view-template");
	
	}
	protected void include(String path, RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher(path);

		if (portletRequestDispatcher == null) {
			// do nothing
			// _log.error(path + " is not a valid include");
		} else {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}
	
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
		String jsp = renderRequest.getParameter("view");
		log.debug("VIEW "+jsp);
		try{
			if(jsp == null || jsp.equals("")){
				showViewDefault(renderRequest, renderResponse);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	public void showViewDefault(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {	
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		boolean internalMessagingActive = false;
		boolean sendAlwaysMessage = false;
		String deregisterMailExpando = "deregister-mail";
		try {
			internalMessagingActive = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), MailStringPool.INTERNAL_MESSAGING_KEY);
			sendAlwaysMessage = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), MailStringPool.SEND_ALWAYS_MESSAGE_KEY);
			deregisterMailExpando = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), MailStringPool.DEREGISTER_MAIL_KEY);
			if(log.isDebugEnabled()){
				log.debug("Company Id "+themeDisplay.getCompanyId());
				log.debug("Initial Value 1 " +  PropsUtil.get(MailStringPool.INTERNAL_MESSAGING_KEY));
				log.debug("Initial Value 2 " +  PropsUtil.get(MailStringPool.DEREGISTER_MAIL_KEY));
				log.debug("Initial Value 3 " +  PropsUtil.get(MailStringPool.SEND_ALWAYS_MESSAGE_KEY));
				log.debug("Real Value 1 " +  PrefsPropsUtil.getString(MailStringPool.INTERNAL_MESSAGING_KEY));
				log.debug("Real Value 2 " +  PrefsPropsUtil.getString(MailStringPool.DEREGISTER_MAIL_KEY));
				log.debug("Real Value 3 " +  PrefsPropsUtil.getString(MailStringPool.SEND_ALWAYS_MESSAGE_KEY));
				log.debug("Company Value 1 " +  internalMessagingActive);
				log.debug("Company Value 2 " +  deregisterMailExpando);
				log.debug("Company Value 3 " +  sendAlwaysMessage);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}	
		renderRequest.setAttribute("internalMessagingActive",internalMessagingActive);
		renderRequest.setAttribute("sendAlwaysMessage",sendAlwaysMessage);
		renderRequest.setAttribute("deregisterMailExpando", deregisterMailExpando);
		
		//Envio de copia de email a relaci�n social
		try {
			List<Integer> mailRelationTypeIds = MailRelationLocalServiceUtil.findRelationTypeIdsByCompanyId(themeDisplay.getCompanyId());
			HashMap<Integer, Boolean> mailRelationTypeActiveHash = new HashMap<Integer, Boolean>();
			if(Validator.isNotNull(mailRelationTypeIds) && mailRelationTypeIds.size()>0){
				String mailRelationTypePref = StringPool.BLANK;
				for(int mailRelationTypeId:mailRelationTypeIds){
					mailRelationTypePref = "mailType_"+mailRelationTypeId;
					mailRelationTypeActiveHash.put(mailRelationTypeId, PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), mailRelationTypePref));
				}
			}
			renderRequest.setAttribute("mailRelationTypeHash", mailRelationTypeActiveHash);
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		PortletURL updateURL = renderResponse.createActionURL();
		updateURL.setParameter("javax.portlet.action", "updatePrefs");
		renderRequest.setAttribute("updateURL", updateURL.toString());
		
		//Mostrar expando usuario y curso en mailing masivo
		boolean showExpandosUser = Boolean.FALSE;
		boolean showExpandosCourse = Boolean.FALSE;
		try{
			showExpandosUser = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), MailConstants.USER_EXPANDOS_TO_SHOW, Boolean.FALSE);
			showExpandosCourse = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), MailConstants.COURSE_EXPANDOS_TO_SHOW, Boolean.FALSE);
		} catch (SystemException e) {
			log.error(e.getLocalizedMessage());
		}
		renderRequest.setAttribute("showExpandosUser", showExpandosUser);
		renderRequest.setAttribute("showExpandosCourse", showExpandosCourse);
		
		include(viewJSP, renderRequest, renderResponse);
	}
	
	public void updatePrefs(ActionRequest actionRequest, ActionResponse actionResponse) throws SystemException {
		try{
			log.debug(":::updatePrefs:::");
			ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
			long companyId = themeDisplay.getCompanyId();
			boolean internalMessagingActive = ParamUtil.getBoolean(actionRequest,"internalMessagingActive");
			boolean sendAlwaysMessage = ParamUtil.getBoolean(actionRequest,"sendAlwaysMessage");
			String deregisterMailExpando = ParamUtil.getString(actionRequest,"deregisterMailExpando");
			boolean errorDeregisterExpando = savePreference(MailStringPool.DEREGISTER_MAIL_KEY,deregisterMailExpando , companyId);
			boolean errorInternalMessaging = savePreference(MailStringPool.INTERNAL_MESSAGING_KEY ,String.valueOf(internalMessagingActive) , companyId);
			boolean errorSendAlwaysMessage = savePreference(MailStringPool.SEND_ALWAYS_MESSAGE_KEY ,String.valueOf(sendAlwaysMessage) , companyId);
			boolean errorActiveMailRelations = false;
			List<Integer> mailRelationTypeIds = MailRelationLocalServiceUtil.findRelationTypeIdsByCompanyId(companyId);
			if(Validator.isNotNull(mailRelationTypeIds) && mailRelationTypeIds.size()>0){
				String mailRelationTypePref = StringPool.BLANK;
				for(int mailRelationTypeId:mailRelationTypeIds){
					mailRelationTypePref = "mailType_"+mailRelationTypeId;
					errorActiveMailRelations = errorActiveMailRelations || savePreference(mailRelationTypePref, String.valueOf(ParamUtil.getBoolean(actionRequest, mailRelationTypePref)), companyId);
				}
			}
			boolean showExpandosUser = ParamUtil.getBoolean(actionRequest, "showExpandosUser", Boolean.FALSE);
			boolean errorShowExpandosUser = savePreference(MailConstants.USER_EXPANDOS_TO_SHOW, String.valueOf(showExpandosUser), companyId);
			if(showExpandosUser && !errorShowExpandosUser){
				boolean expandoUserValue = Boolean.FALSE;
				List<ExpandoColumn> listUserExpandos = ExpandoColumnLocalServiceUtil.getColumns(themeDisplay.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);		
				String nameExpandoUser = StringPool.BLANK;
				int i=0;
				while(i<listUserExpandos.size() && !errorShowExpandosUser){
					nameExpandoUser = "showExpandosUser" + listUserExpandos.get(i).getColumnId();
					expandoUserValue = ParamUtil.getBoolean(actionRequest, nameExpandoUser, Boolean.FALSE);
					errorShowExpandosUser = savePreference(MailConstants.USER_EXPANDO_TO_SHOW+String.valueOf(listUserExpandos.get(i).getColumnId()), String.valueOf(expandoUserValue), companyId);
					i++;
				} 
			}
			boolean showExpandosCourse = ParamUtil.getBoolean(actionRequest, "showExpandosCourse", Boolean.FALSE);
			boolean errorShowExpandosCourse = savePreference(MailConstants.COURSE_EXPANDOS_TO_SHOW, String.valueOf(showExpandosCourse), companyId);
			if(showExpandosCourse && !errorShowExpandosCourse){
				boolean expandoCourseValue = Boolean.FALSE;
				List<ExpandoColumn> listCourseExpandos = ExpandoColumnLocalServiceUtil.getColumns(themeDisplay.getCompanyId(), Course.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);		
				String nameExpandoCourse = StringPool.BLANK;
				int i=0;
				while(i<listCourseExpandos.size() && !errorShowExpandosCourse){
					nameExpandoCourse = "showExpandosCourse" + listCourseExpandos.get(i).getColumnId();
					expandoCourseValue = ParamUtil.getBoolean(actionRequest, nameExpandoCourse, Boolean.FALSE);
					errorShowExpandosCourse = savePreference(MailConstants.COURSE_EXPANDO_TO_SHOW+String.valueOf(listCourseExpandos.get(i).getColumnId()), String.valueOf(expandoCourseValue), companyId);
					i++;
				} 
			}
			
			if(errorDeregisterExpando || errorInternalMessaging || errorSendAlwaysMessage || errorActiveMailRelations || errorShowExpandosUser || errorShowExpandosCourse){
				SessionErrors.add(actionRequest, "update-ko");
			}else{
				SessionMessages.add(actionRequest, "update-ok");
			}
		}catch(Exception e){ 
			e.printStackTrace();
			SessionErrors.add(actionRequest, "update-ko");
		}
		
	}

	private boolean savePreference(String key, String value, long companyId) throws SystemException {
		
		if(log.isDebugEnabled()){
			log.debug(":::savePreference::: key :: " + key);
			log.debug(":::savePreference::: value :: " + value);
			log.debug(":::savePreference::: companyId :: " + companyId);
		}
		
		PortletPreferences prefs= PortalPreferencesLocalServiceUtil.getPreferences(companyId, companyId, 1);
		boolean error = false;
		if(!"".equals(key)&&!prefs.isReadOnly(key))
		{
			try {
				prefs.setValue(key, value);
			} catch (ReadOnlyException e) {
				e.printStackTrace();
				error=true;
			}
			try {
				prefs.store();
			} catch (ValidatorException e) {
				e.printStackTrace();
				error=true;
			} catch (IOException e) {
				e.printStackTrace();
				error=true;
			}
		}
		else
		{
			error=true;
		}
		return error;
	}
}
