package com.tls.liferaylms.job;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tls.liferaylms.job.condition.Condition;
import com.tls.liferaylms.job.condition.ConditionUtil;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;
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
						reference = ConditionUtil.instance(mailJob.getDateClassName(), mailJob);
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
								if(condition!=null){
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
								if(reference!=null){
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
				
				renderRequest.setAttribute(MailStringPool.CONDITION_STATUS, conditionStatus);
				renderRequest.setAttribute(MailStringPool.CONDITION, condition);
				renderRequest.setAttribute(MailStringPool.REFERENCE, reference);
				renderRequest.setAttribute(MailStringPool.DAYS, days);
				renderRequest.setAttribute(MailStringPool.TIME, time);
				renderRequest.setAttribute(MailStringPool.MAIL_JOB, mailJob); 
				renderRequest.setAttribute(MailStringPool.ACTIVITIES_TEMP_REF, tempActivitiesReference);
				renderRequest.setAttribute(MailStringPool.ACTIVITIES_TEMP, tempActivities);
				renderRequest.setAttribute(MailStringPool.ACTIVITIES, activities);
				renderRequest.setAttribute(MailStringPool.MODULES, modules); 
				renderRequest.setAttribute(MailStringPool.COURSE, course); 
				renderRequest.setAttribute(MailStringPool.CONDITIONS, conditions);
				renderRequest.setAttribute(MailStringPool.TEMPLATES, templates);
								
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
		String conditionClassName = ParamUtil.getString(request, MailStringPool.CONDITION_CLASSNAME, StringPool.BLANK);
		Long conditionModule = ParamUtil.getLong(request, MailStringPool.CONDITION_MODULE, 0);
		Long conditionActivity = ParamUtil.getLong(request, MailStringPool.CONDITION_ACTIVITY, 0);
		long[] alConditionState = ParamUtil.getLongValues(request, MailStringPool.CONDITION_STATE, null);
		Long idJob = ParamUtil.getLong(request, MailStringPool.ID_JOB, 0);
		Long days = ParamUtil.getLong(request, MailStringPool.DAYS, 0);
		
		StringBuffer conditionState = new StringBuffer();
		
		for(long lconditionState : alConditionState){
			conditionState.append(lconditionState);
			conditionState.append(StringPool.COMMA);
		}
		
		String referenceClassName = ParamUtil.getString(request, MailStringPool.REFERENCE_CLASSNAME, StringPool.BLANK);
		Long referenceModule = ParamUtil.getLong(request, MailStringPool.REFERENCE_MODULE, 0);
		Long referenceActivity = ParamUtil.getLong(request, MailStringPool.REFERENCE_ACTIVITY, 0);
		Long referenceState = ParamUtil.getLong(request, MailStringPool.REFERENCE_STATE, 0);
		Long dateShift = ParamUtil.getLong(request, MailStringPool.DATE_SHIFT, 0);
		Long template = ParamUtil.getLong(request, MailStringPool.ID_TEMPLATE, 0);
		
		
		if(log.isDebugEnabled()){
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

	@ProcessAction(name = "save")
	public void save(ActionRequest request, ActionResponse response) {		
		String conditionClassName = ParamUtil.getString(request, MailStringPool.CONDITION_CLASSNAME, StringPool.BLANK);
		Long conditionModule = ParamUtil.getLong(request, MailStringPool.CONDITION_MODULE, 0);
		Long conditionActivity = ParamUtil.getLong(request, MailStringPool.CONDITION_ACTIVITY, 0);
		long[] alConditionState = ParamUtil.getLongValues(request, MailStringPool.CONDITION_STATE, null);
		Long days = ParamUtil.getLong(request, MailStringPool.DAYS, 0);
		
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
		
		
		if(log.isDebugEnabled()){
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
			MailJobLocalServiceUtil.addMailJob(template, conditionClassName, conditionActivity, conditionState.toString(), referenceClassName, referenceActivity, days*dateShift, referenceState, serviceContext);
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
