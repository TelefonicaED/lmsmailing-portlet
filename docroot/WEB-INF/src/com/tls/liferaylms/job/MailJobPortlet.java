package com.tls.liferaylms.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.lms.model.Course;
import com.liferay.lms.model.LearningActivity;
import com.liferay.lms.model.Module;
import com.liferay.lms.service.CourseLocalServiceUtil;
import com.liferay.lms.service.LearningActivityLocalServiceUtil;
import com.liferay.lms.service.ModuleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tls.liferaylms.job.condition.Condition;
import com.tls.liferaylms.job.condition.ConditionUtil;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailRelationLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;
import com.tls.liferaylms.util.MailConstants;
import com.tls.liferaylms.util.MailStringPool;

/**
 * Portlet implementation class MailJobPortlet
 */
public class MailJobPortlet extends MVCPortlet {
	Log log = LogFactoryUtil.getLog(MailJobPortlet.class);
 
	protected String viewJSP;
	protected String editJSP;

	public void init() throws PortletException { 
		this.viewJSP = getInitParameter("view-template");
		this.editJSP = getInitParameter("edit-template");
	}

	public void doView(RenderRequest renderRequest,RenderResponse renderResponse) throws IOException, PortletException {
		String view = ParamUtil.getString(renderRequest, MailStringPool.VIEW, StringPool.BLANK);
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
				
		Course course = null;
		try {
			course = CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}
		
		if(course==null)
			return;
		
		//PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
		PortletPreferences preferences = null;
		String portletResource = ParamUtil.getString(renderRequest, "portletResource");
		if (Validator.isNotNull(portletResource)) {
			try {
				preferences = PortletPreferencesFactoryUtil.getPortletSetup(renderRequest, portletResource);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else{
			preferences = renderRequest.getPreferences();
		}
		boolean calendar = false;
		if(!view.equals(StringPool.BLANK)){
			if(view.equals(MailStringPool.EDIT)){
				
				Long id = ParamUtil.getLong(renderRequest, MailStringPool.MAIL_JOB, 0);
				
				MailJob mailJob = null;
				Condition condition = null;
				Condition reference = null;
				String[] conditionStatus = null; 
				if(id>0){
					try {
						mailJob = MailJobLocalServiceUtil.getMailJob(id);
						conditionStatus = mailJob.getConditionStatus().split(StringPool.COMMA);
						condition = ConditionUtil.instance(mailJob.getConditionClassName(), mailJob);
						calendar = mailJob.getDateClassName().equals(StringPool.BLANK) || mailJob.getDateToSend()!= null?true:false;
						log.info("calendar "+calendar);
						if (!calendar){
							reference = ConditionUtil.instance(mailJob.getDateClassName(), mailJob);
						}
					} catch (PortalException e) {
						if(log.isDebugEnabled())e.printStackTrace();
						if(log.isErrorEnabled())log.error(e.getMessage());
					} catch (SystemException e) {
						if(log.isDebugEnabled())e.printStackTrace();
						if(log.isErrorEnabled())log.error(e.getMessage());
					} catch (ClassNotFoundException e){
						if(log.isDebugEnabled())e.printStackTrace();
						if(log.isErrorEnabled())log.error(e.getMessage());
					}
				}
				
				List<MailTemplate> templates = null;
				try {
					templates = MailTemplateLocalServiceUtil.getMailTemplateByGroupIdAndGlobalGroupId(themeDisplay.getScopeGroupId());
				} catch (SystemException e) {
					if(log.isDebugEnabled())e.printStackTrace();
					if(log.isErrorEnabled())log.error(e.getMessage()); 
				}

				Set<Condition> conditions = ConditionUtil.getAllConditions();				
				
				List<Module> modules = null;
				List<LearningActivity> tempActivities = null;
				List<LearningActivity> tempActivitiesReference = null;
				HashMap<Long, List<LearningActivity>> activities = new HashMap<Long, List<LearningActivity>>();
				try {
					modules = ModuleLocalServiceUtil.findAllInGroup(themeDisplay.getScopeGroupId());
					
					if(modules!=null){
						for(Module module : modules){
							List<LearningActivity> learningActivities = LearningActivityLocalServiceUtil.getLearningActivitiesOfModule(module.getModuleId());
							if(tempActivities==null){
								if(condition!=null && condition.getConditionName().equals(LanguageUtil.format(Locale.getDefault(), "com.liferay.lms.model.Activity", ""))){
									for(LearningActivity la : learningActivities){
										if(la.getPrimaryKey()==condition.getActConditionPK()){
											tempActivities = learningActivities;
											break;
										}
									}
								}else{
									tempActivities = learningActivities;
								}
							}

							if(tempActivitiesReference==null){
								if(reference!=null && reference.getConditionName().equals(LanguageUtil.format(Locale.getDefault(), "com.liferay.lms.model.Activity", ""))){
									for(LearningActivity la : learningActivities){
										if(la.getPrimaryKey()==reference.getActReferencePK()){
											tempActivitiesReference = learningActivities;
											break;
										}
									}
								}else{
									tempActivitiesReference = learningActivities;
								}
							}
							
							activities.put(module.getModuleId(), learningActivities);
						}
					}
				} catch (SystemException e) {
					if(log.isDebugEnabled())e.printStackTrace();
					if(log.isErrorEnabled())log.error(e.getMessage());
				}
				
				Date dateToSend = new Date();
				if (mailJob != null && mailJob.getDateToSend()!= null){
					dateToSend = mailJob.getDateToSend();
				}

				renderRequest.setAttribute(MailStringPool.DATE_TOSEND, dateToSend);
								
				Integer days = 0;
				Integer time = -1;
				if(mailJob!=null){
					if(mailJob.getDateShift()>=0){
							days = ((Long)mailJob.getDateShift()).intValue();
							time = 1;
					}else{
							days = ((Long)(mailJob.getDateShift()*-1)).intValue();
					}
				}
				renderRequest.setAttribute(MailStringPool.DAYS, days);
				renderRequest.setAttribute(MailStringPool.TIME, time);
				
				
				renderRequest.setAttribute(MailStringPool.CONDITION_STATUS, conditionStatus);
				renderRequest.setAttribute(MailStringPool.CONDITION, condition);
				renderRequest.setAttribute(MailStringPool.REFERENCE, reference);
				renderRequest.setAttribute(MailStringPool.MAIL_JOB, mailJob); 
				renderRequest.setAttribute(MailStringPool.ACTIVITIES_TEMP_REF, tempActivitiesReference);
				renderRequest.setAttribute(MailStringPool.ACTIVITIES_TEMP, tempActivities);
				renderRequest.setAttribute(MailStringPool.ACTIVITIES, activities);
				renderRequest.setAttribute(MailStringPool.MODULES, modules); 
				renderRequest.setAttribute(MailStringPool.COURSE, course); 
				renderRequest.setAttribute(MailStringPool.CONDITIONS, conditions);
				renderRequest.setAttribute(MailStringPool.TEMPLATES, templates);
				renderRequest.setAttribute("calendar", calendar);
								
				include(editJSP, renderRequest, renderResponse);
			}
		}else{
			/*ProcessMailJob pmj = new ProcessMailJob();
			try {
				pmj.receive(null);
			} catch (MessageListenerException e) {
				e.printStackTrace();
			}*/
			
			Integer delta = ParamUtil.getInteger(renderRequest, MailStringPool.DELTA_MAIL_JOB_PAG, 10);
			Integer pag = ParamUtil.getInteger(renderRequest, MailStringPool.MAIL_JOB_PAG, 1);
			String tab = ParamUtil.getString(renderRequest, MailStringPool.TAB, MailStringPool.PROCESSED_PLURAL);
			
			List<MailJob> mailJobs = MailJobLocalServiceUtil.getMailJobsInGroupIdAndProcessed(themeDisplay.getScopeGroupId(), true, (pag-1)*delta, ((pag-1)*delta)+delta);
			Integer count = MailJobLocalServiceUtil.countByGroupAndProcessed(themeDisplay.getScopeGroupId(),true);

			renderRequest.setAttribute(MailStringPool.MAIL_JOBS, mailJobs);
			renderRequest.setAttribute(MailStringPool.COUNT, count);
			
			delta = ParamUtil.getInteger(renderRequest, MailStringPool.DELTA_MAIL_JOB_PENDING_PAG, 10);
			pag = ParamUtil.getInteger(renderRequest, MailStringPool.MAIL_JOB_PENDING_PAG, 1);

			mailJobs = MailJobLocalServiceUtil.getMailJobsInGroupIdAndProcessed(themeDisplay.getScopeGroupId(), false, (pag-1)*delta, ((pag-1)*delta)+delta);
			count = MailJobLocalServiceUtil.countByGroupAndProcessed(themeDisplay.getScopeGroupId(),false);

			renderRequest.setAttribute(MailStringPool.PENDING_MAIL_JOBS, mailJobs);
			renderRequest.setAttribute(MailStringPool.PENDING_COUNT, count);
			
			renderRequest.setAttribute(MailStringPool.TAB, tab);
			
			
			include(viewJSP, renderRequest, renderResponse);
		}
	}

	@ProcessAction(name = "newMailJob")
	public void newMailJob(ActionRequest request, ActionResponse response) {

		response.setRenderParameter(MailStringPool.VIEW, MailStringPool.EDIT);
	}

	@ProcessAction(name = "viewMailJob")
	public void viewMailJob(ActionRequest request, ActionResponse response) {
		
		response.setRenderParameter(MailStringPool.VIEW, MailStringPool.EDIT);
		response.setRenderParameter(MailStringPool.MAIL_JOB, ParamUtil.getString(request, MailStringPool.MAIL_JOB,null));
	}

	@ProcessAction(name = "update")
	public void update(ActionRequest request, ActionResponse response) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		String conditionClassName = ParamUtil.getString(request, MailStringPool.CONDITION_CLASSNAME, StringPool.BLANK);
		Long conditionModule = ParamUtil.getLong(request, MailStringPool.CONDITION_MODULE, 0);
		Long conditionActivity = ParamUtil.getLong(request, MailStringPool.CONDITION_ACTIVITY, 0);
		long[] alConditionState = ParamUtil.getLongValues(request, MailStringPool.CONDITION_STATE, null);
		Long idJob = ParamUtil.getLong(request, MailStringPool.ID_JOB, 0);
		Long days = ParamUtil.getLong(request, MailStringPool.DAYS, 0);
		response.setRenderParameter("tab","non-processed-plural"); 
		StringBuffer conditionState = new StringBuffer();
		
		if(alConditionState!=null){
			for(long lconditionState : alConditionState){
				conditionState.append(lconditionState);
				conditionState.append(StringPool.COMMA);
			}
		}
		
		String referenceClassName = ParamUtil.getString(request, MailStringPool.REFERENCE_CLASSNAME, StringPool.BLANK);
		Long referenceModule = ParamUtil.getLong(request, MailStringPool.REFERENCE_MODULE, 0);
		Long referenceActivity = ParamUtil.getLong(request, MailStringPool.REFERENCE_ACTIVITY, 0);
		Long referenceState = ParamUtil.getLong(request, MailStringPool.REFERENCE_STATE, 0);
		Long dateShift = ParamUtil.getLong(request, MailStringPool.DATE_SHIFT, 0);
		Long template = ParamUtil.getLong(request, MailStringPool.ID_TEMPLATE, 0);
		
		
		//Envío de copia a usuarios relacionados
		List<Integer> mailRelationTypeIds = MailRelationLocalServiceUtil.findRelationTypeIdsByCompanyId(themeDisplay.getCompanyId());
		JSONArray sendCopyToTypeIds = JSONFactoryUtil.createJSONArray();
		if(Validator.isNotNull(mailRelationTypeIds) && mailRelationTypeIds.size()>0){
			String sendMailToRelationType = StringPool.BLANK;
			boolean isActiveSendMailToRelationType = Boolean.FALSE;
			for(int mailRelationTypeId:mailRelationTypeIds){
				sendMailToRelationType = "sendMailToType_"+mailRelationTypeId;
				isActiveSendMailToRelationType = ParamUtil.getBoolean(request, sendMailToRelationType, Boolean.FALSE);
				if(isActiveSendMailToRelationType){
					sendCopyToTypeIds.put(mailRelationTypeId);
				}
			}
		}
		//Enviar email a usuarios relacionados
		boolean sendCopyToSocialRelation = sendCopyToTypeIds.length()>0;
		Date dateToSend = null;
		boolean isCalendar = ParamUtil.getBoolean(request, "calendar",false);
		if (isCalendar){
			int sendDateAno = ParamUtil.getInteger(request, "sendDateAno",0);
			int sendDateMes = ParamUtil.getInteger(request, "sendDateMes",0);
			int sendDateDia = ParamUtil.getInteger(request, "sendDateDia",0);
			
			if (sendDateAno>0){
				Calendar calendar = Calendar.getInstance();
				calendar.set(sendDateAno, sendDateMes, sendDateDia);
				calendar.set(Calendar.HOUR_OF_DAY,0);
				calendar.set(Calendar.MINUTE,0);
				calendar.set(Calendar.SECOND,0);
				calendar.set(Calendar.MILLISECOND,0);
				dateToSend = calendar.getTime();
			}
			referenceClassName=StringPool.BLANK;
		}
		if (conditionClassName.startsWith("Inscription")){
			referenceClassName=StringPool.BLANK;
		}
		log.info("dateTosend "+dateToSend);
		if(log.isDebugEnabled()){
			log.debug("UPDATE");
			log.debug(template);
			log.debug(conditionClassName);
			log.debug(conditionModule);
			log.debug(conditionActivity);
			log.debug(conditionState);
			log.debug(referenceModule);
			log.debug(referenceClassName);
			log.debug(referenceActivity);
			log.debug(referenceState);
			log.debug(dateShift);
			log.debug(days);
			log.debug(days*dateShift);
			log.debug("::::sendCopyToTypeIds "+sendCopyToTypeIds.length());
			log.debug(":::sendCopyToSocialRelation:: " + sendCopyToSocialRelation); 
		}	
		
		try {
			MailJob mailJob = MailJobLocalServiceUtil.getMailJob(idJob);
			
			if(mailJob!=null){

				ServiceContext serviceContext = null;
				try {
					serviceContext = ServiceContextFactory.getInstance(request);
				} catch (PortalException e) {
					if(log.isDebugEnabled())e.printStackTrace();
					if(log.isErrorEnabled())log.error(e.getMessage());
				} catch (SystemException e) {
					if(log.isDebugEnabled())e.printStackTrace();
					if(log.isErrorEnabled())log.error(e.getMessage());
				}

				mailJob.setIdTemplate(template);
				mailJob.setUserId(serviceContext.getUserId());
				mailJob.setCompanyId(serviceContext.getCompanyId());
				mailJob.setGroupId(serviceContext.getScopeGroupId());
				mailJob.setConditionClassName(conditionClassName);
				mailJob.setConditionClassPK(conditionActivity);
				mailJob.setConditionStatus(conditionState.toString());
				mailJob.setDateClassName(referenceClassName);
				mailJob.setDateClassPK(referenceActivity);
				mailJob.setDateShift(days*dateShift);
				mailJob.setDateReferenceDate(referenceState);
				mailJob.setDateToSend(dateToSend);
				
				JSONObject extraData = JSONFactoryUtil.createJSONObject();
				extraData.put(MailConstants.EXTRA_DATA_SEND_COPY, sendCopyToSocialRelation);
				extraData.put(MailConstants.EXTRA_DATA_RELATION_ARRAY, sendCopyToTypeIds);
				mailJob.setExtraData(extraData.toString());
				
				
				MailJobLocalServiceUtil.updateMailJob(mailJob);
			}
			
		} catch (PortalException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}
		
	}
	
	@ProcessAction(name = "deleteMailJob")
	public void deleteMailJob(ActionRequest request, ActionResponse response) {
		long mailJobId = ParamUtil.getLong(request, "mailJobId", 0);
		response.setRenderParameter("tab","non-processed-plural");  
		try{
			if(mailJobId>0){
					MailJobLocalServiceUtil.deleteMailJob(mailJobId);
					SessionMessages.add(request, "delete-mailjob-ok");
			}else{
				SessionErrors.add(request, "delete-mailjob-ko");
			}
		
		} catch (Exception e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
			SessionErrors.add(request, "delete-mailjob-ko");
		}
		
	}

	@ProcessAction(name = "save")
	public void save(ActionRequest request, ActionResponse response) {	
		log.info("Guardando");
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		String conditionClassName = ParamUtil.getString(request, MailStringPool.CONDITION_CLASSNAME, StringPool.BLANK);
		Long conditionModule = ParamUtil.getLong(request, MailStringPool.CONDITION_MODULE, 0);
		Long conditionActivity = ParamUtil.getLong(request, MailStringPool.CONDITION_ACTIVITY, 0);
		long[] alConditionState = ParamUtil.getLongValues(request, MailStringPool.CONDITION_STATE, null);
		Long days = ParamUtil.getLong(request, MailStringPool.DAYS, 0);
		response.setRenderParameter("tab","non-processed-plural"); 
		StringBuffer conditionState = new StringBuffer();
		
		if(alConditionState!=null){
			for(long lconditionState : alConditionState){
				conditionState.append(lconditionState);
				conditionState.append(StringPool.COMMA);
			}
		}
		
		String referenceClassName = ParamUtil.getString(request, MailStringPool.REFERENCE_CLASSNAME, StringPool.BLANK);
		Long referenceModule = ParamUtil.getLong(request, MailStringPool.REFERENCE_MODULE, 0);
		Long referenceActivity = ParamUtil.getLong(request, MailStringPool.REFERENCE_ACTIVITY, 0);
		Long referenceState = ParamUtil.getLong(request, MailStringPool.REFERENCE_STATE, 0);
		Long dateShift = ParamUtil.getLong(request, MailStringPool.DATE_SHIFT, 0);
		Long template = ParamUtil.getLong(request, MailStringPool.ID_TEMPLATE, 0);
		Date dateToSend = null;
		
		boolean isCalendar = ParamUtil.getBoolean(request, "calendar",false);
		if (isCalendar){
			int sendDateAno = ParamUtil.getInteger(request, "sendDateAno",0);
			int sendDateMes = ParamUtil.getInteger(request, "sendDateMes",0);
			int sendDateDia = ParamUtil.getInteger(request, "sendDateDia",0);
		
			if (sendDateAno>0){
				Calendar calendar = Calendar.getInstance();
				calendar.set(sendDateAno, sendDateMes, sendDateDia);
				calendar.set(Calendar.HOUR_OF_DAY,0);
				calendar.set(Calendar.MINUTE,0);
				calendar.set(Calendar.SECOND,0);
				calendar.set(Calendar.MILLISECOND,0);
				dateToSend = calendar.getTime();
			}
			referenceClassName=StringPool.BLANK;
		}
		if (conditionClassName.startsWith("Inscription")){
			referenceClassName=StringPool.BLANK;
		}
		log.info("dateTosend "+dateToSend);
		
		
		//Envío de copia a usuarios relacionados
		List<Integer> mailRelationTypeIds = MailRelationLocalServiceUtil.findRelationTypeIdsByCompanyId(themeDisplay.getCompanyId());
		JSONArray sendCopyToTypeIds = JSONFactoryUtil.createJSONArray();
		if(Validator.isNotNull(mailRelationTypeIds) && mailRelationTypeIds.size()>0){
			String sendMailToRelationType = StringPool.BLANK;
			boolean isActiveSendMailToRelationType = Boolean.FALSE;
			for(int mailRelationTypeId:mailRelationTypeIds){
				sendMailToRelationType = "sendMailToType_"+mailRelationTypeId;
				isActiveSendMailToRelationType = ParamUtil.getBoolean(request, sendMailToRelationType, Boolean.FALSE);
				if(isActiveSendMailToRelationType){
					sendCopyToTypeIds.put(mailRelationTypeId);
				}
			}
		}
		

		//Enviar email a usuarios relacionados
		boolean sendCopyToSocialRelation = sendCopyToTypeIds.length()>0;

		
		if(log.isDebugEnabled()){
			log.debug("SAVE");
			log.debug(template);
			log.debug(conditionClassName);
			log.debug(conditionModule);
			log.debug(conditionActivity);
			log.debug(conditionState);
			log.debug(referenceModule);
			log.debug(referenceClassName);
			log.debug(referenceActivity);
			log.debug(referenceState);
			log.debug(dateShift);
			log.debug(days);
			log.debug(days*dateShift);
			log.debug("::::sendCopyToTypeIds "+sendCopyToTypeIds.length());
			log.debug(":::sendCopyToSocialRelation:: " + sendCopyToSocialRelation); 
		}
		
		ServiceContext serviceContext = null;
		try {
			serviceContext = ServiceContextFactory.getInstance(request);
		} catch (PortalException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}
		
		try {
			MailJob mailJob =  MailJobLocalServiceUtil.addMailJob(template, conditionClassName, conditionActivity, conditionState.toString(), referenceClassName, referenceActivity, days*dateShift, dateToSend, referenceState, serviceContext);
			JSONObject extraData = JSONFactoryUtil.createJSONObject();
			extraData.put(MailConstants.EXTRA_DATA_SEND_COPY, sendCopyToSocialRelation);
			extraData.put(MailConstants.EXTRA_DATA_RELATION_ARRAY, sendCopyToTypeIds);
			mailJob.setExtraData(extraData.toString());
			MailJobLocalServiceUtil.updateMailJob(mailJob);
		
		} catch (PortalException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		} catch (SystemException e) {
			if(log.isDebugEnabled())e.printStackTrace();
			if(log.isErrorEnabled())log.error(e.getMessage());
		}
	}

	protected void include(String path, RenderRequest renderRequest,RenderResponse renderResponse) throws IOException, PortletException {
		PortletRequestDispatcher portletRequestDispatcher = getPortletContext().getRequestDispatcher(path);

		if (portletRequestDispatcher != null) {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		}
	}
}
