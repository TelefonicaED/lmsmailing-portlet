package com.tls.liferaylms.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;
import com.tls.liferaylms.util.MailConstants;

/**
 * Portlet implementation class ManageTemplates
 */
public class ManageTemplates extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(ManageTemplates.class);

	public void saveTemplate(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
	
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(actionRequest);
		PortletSession session = actionRequest.getPortletSession(Boolean.TRUE);
		
		String subject = ParamUtil.getString(uploadRequest, "subject", "");
		String body = ParamUtil.getString(uploadRequest, "message", "");
		boolean editing = ParamUtil.getBoolean(uploadRequest, "editing", true);
		String idTemplate = ParamUtil.getString(uploadRequest, "idTemplate", "");
		File[] attachments = uploadRequest.getFiles("MultipleFile1");
		String[] attachmentNames = uploadRequest.getFileNames("MultipleFile1");
		_log.info("subject "+subject+"attachments "+attachments+" attachmentNames "+attachmentNames);
		boolean error = false;
		if (attachments!=null && attachments.length>0 && attachments[0] != null){
			String attachmentVerification = checkAttachments(attachmentNames, attachments, themeDisplay.getCompanyId());

			if(!attachmentVerification.equals("OK")){
				error = true;
				actionResponse.setRenderParameter("jspPage","/html/managetemplates/view.jsp");
				SessionErrors.add(actionRequest, attachmentVerification);
			}
		}
	
		if(editing)
		{
			MailTemplate template = MailTemplateLocalServiceUtil.getMailTemplate(Long.parseLong(idTemplate));
			template.setSubject(subject);
			template.setBody(changeToURL(body, themeDisplay.getURLPortal()));
			template.setGroupId(themeDisplay.getScopeGroupId());
			template.setCompanyId(themeDisplay.getCompanyId());
			template.setUserId(themeDisplay.getUserId());
			String borrarFile = ParamUtil.getString(uploadRequest, "borrarFile", "");
			if (!borrarFile.isEmpty()){
				String[] fileNames=borrarFile.split("##");
				File atachDir = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+template.getIdTemplate());
				if (atachDir.exists()){
					List<File> files = (List<File>) FileUtils.listFiles(atachDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
					for (File fil: files){
						if (ArrayUtil.contains(fileNames, fil.getName())){
							_log.info("Borro fichero "+fil.getName());
							fil.delete();
						}
					}
				}
			}
			if(!error){
				if(attachments!=null && attachments.length>0 && attachments[0] != null){
					_log.info(">>>> "+attachments.length);
					for(int i=0;i<attachments.length;i++){
						File newFile = new File(PropsUtil.get("liferay.home")+"/data/mailtemplate/"+idTemplate+"/"+attachmentNames[i]);
						FileUtils.copyFile(attachments[i], newFile);
					}
				}
			}
			
			MailTemplateLocalServiceUtil.updateMailTemplate(template);
			
			if(_log.isInfoEnabled())
				_log.trace("ManageTemplates: updateMailTemplate " + template.getSubject());
		}	
		else
		{
			MailTemplate mailTemplate = MailTemplateLocalServiceUtil.createMailTemplate(CounterLocalServiceUtil.increment(MailTemplate.class.getName()));
			mailTemplate.setSubject(subject);
			mailTemplate.setBody(changeToURL(body, themeDisplay.getURLPortal()));
			mailTemplate.setGroupId(themeDisplay.getScopeGroupId());
			mailTemplate.setCompanyId(themeDisplay.getCompanyId());
			mailTemplate.setUserId(themeDisplay.getUserId());
			MailTemplateLocalServiceUtil.addMailTemplate(mailTemplate);
			
			if(_log.isInfoEnabled())
				_log.trace("ManageTemplates: addMailTemplate " + mailTemplate.getSubject());
		}		
	}
	
	private String checkAttachments(String[] fileNames, File[] attachments, long companyId){
		int maxSize = MailConstants.ATTACHMENTS_DEFAULT_MAX_SIZE;
		try {
			maxSize = PrefsPropsUtil.getInteger(companyId, MailConstants.ATTACHMENTS_MAX_SIZE_KEY, MailConstants.ATTACHMENTS_DEFAULT_MAX_SIZE);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		maxSize = maxSize * 1024;
		String acceptFiles = MailConstants.ATTACHMENTS_DEFAULT_ACCEPTED_FILES;
		try {
			acceptFiles = PrefsPropsUtil.getString(companyId, MailConstants.ATTACHMENTS_ACCEPTED_FILES_KEY, MailConstants.ATTACHMENTS_DEFAULT_ACCEPTED_FILES);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		List<String> extensions = ListUtil.fromArray(acceptFiles.split("|"));
		int i=0;
		String error="OK";
		
		if(attachments != null && attachments.length>0){
			while(i<attachments.length && error.equals("OK")){
				if(attachments[i]!=null){
					if(attachments[i].length()>maxSize){
						_log.debug("error-max-size" + attachments[i].length());
						error="error-max-size";
					}
					
					String extension = FileUtil.getExtension(fileNames[i]);
					try{
						if(extensions.contains(extension.toLowerCase())){
							_log.debug("error-file-type-incorrect" + extension);
							error="error-file-type-incorrect";
						}
					}catch(Exception e){
						_log.debug(e);
						error="error-file-type-incorrect";
					}
				}
				i++;
			}
		}
		
		return error;
	}
	
	public void deleteTemplate(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		String idTemplate = ParamUtil.getString(actionRequest, "templateId", "");

		if(!idTemplate.equals("")){
			MailTemplateLocalServiceUtil.deleteMailTemplate(Long.parseLong(idTemplate));
			File attachDir = new File(PropsUtil.get("liferay.home")+"/data/mailtemplate/"+idTemplate);
			
			try {
				if (attachDir.exists()){
					FileUtils.forceDelete( attachDir);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			SessionErrors.add(actionRequest, "error-delete");
		}
	}
	
	//Para im�genes
	private String changeToURL(String text, String url){
	
		text =  text.contains("img") ? 
				text.replace("src=\"/", "src=\"" + url + StringPool.SLASH) : 
				text;
				
		return text;
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest,ResourceResponse resourceResponse) throws IOException,PortletException {
		
		String action = ParamUtil.getString(resourceRequest, "action");

		if(action.equals("export-templates")){
			try {
				
				exportTemplates(resourceRequest,resourceResponse);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void exportTemplates(ResourceRequest request, ResourceResponse response) throws IOException {
		
		ThemeDisplay themeDisplay  =(ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {
			
			//Necesario para crear el fichero csv.
			response.setCharacterEncoding("ISO-8859-1");
			response.setContentType("text/csv;charset=ISO-8859-1");
			response.addProperty(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=data.csv");
	        byte b[] = {(byte)0xEF, (byte)0xBB, (byte)0xBF};
	        
	        response.getPortletOutputStream().write(b);
	        
	        CSVWriter writer = new CSVWriter(new OutputStreamWriter(response.getPortletOutputStream(),"ISO-8859-1"),';');
	        
	        String[] row = new String[3];
	        
	        row[0] = "Subject";
	        row[1] = "Body";
	        row[2] = "Attach";
	        writer.writeNext(row);
	        
	        List<MailTemplate> mailTemplates = MailTemplateLocalServiceUtil.getMailTemplateByGroupId(themeDisplay.getScopeGroupId());
	        
	        for(MailTemplate template:mailTemplates){
	        	row[0] = template.getSubject();
		        row[1] = template.getBody();
		        File attachDir = new File(PropsUtil.get("liferay.home")+"/data/mailtemplate/"+template.getIdTemplate());
		        if (attachDir.exists()){
		        	row[2] = ""+template.getIdTemplate();
		        }else{
		        	row[2] = "No";
		        }
		        
		        writer.writeNext(row);
	        }
	        
			writer.flush();
			writer.close();
			
			response.getPortletOutputStream().flush();
	        response.getPortletOutputStream().close();
	        
		} catch (Exception e){
			e.printStackTrace();
		}finally{
			response.getPortletOutputStream().flush();
	        response.getPortletOutputStream().close();
		}
		
	}
	
	public void importTemplates(ActionRequest actionRequest, ActionResponse actionResponse)	throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		UploadPortletRequest request = PortalUtil.getUploadPortletRequest(actionRequest);
		String fileName = request.getFileName("importFileName");

		if(fileName==null || StringPool.BLANK.equals(fileName)){
			SessionErrors.add(actionRequest, "managetemplates.import.error.fileRequired");
		}
		else{ 
			if (!fileName.endsWith(".csv")) {
				SessionErrors.add(actionRequest, "managetemplates.import.error.badFormat");
			}
			else {
				CSVReader reader = null;
				try {
					File file = request.getFile("importFileName");
					reader = new CSVReader(new InputStreamReader(new FileInputStream(file), "ISO-8859-1"), ';');

					boolean isHeader = true;
					String[] currLine;
					while ((currLine = reader.readNext()) != null){
						
						if(isHeader){isHeader=false;continue;}
						
						if(_log.isDebugEnabled()) _log.debug("currLine : " + currLine.toString() );
						
						String subject="", body="", atach="";
						
						if(currLine[0] != null){
							subject = currLine[0];
						}
						
						if(currLine[1] != null){
							body = currLine[1];
						}
						
						if(currLine[2] != null){
							atach = currLine[2];
						}
						if(_log.isDebugEnabled()) _log.debug("subject : " + subject );
						if(_log.isDebugEnabled()) _log.debug("body : " + body );
						
						if (!"".equals(subject) || !"".equals(body) ){
							
							try {
								
								//Insertar el template
								MailTemplate template = MailTemplateLocalServiceUtil.createMailTemplate(CounterLocalServiceUtil.increment(MailTemplate.class.getName()));
								
								template.setSubject(subject);
								template.setBody(body);
								template.setCompanyId(themeDisplay.getCompanyId());
								template.setGroupId(themeDisplay.getScopeGroupId());
								template.setUserId(themeDisplay.getUserId());
								
								MailTemplateLocalServiceUtil.updateMailTemplate(template);
								
								if (!atach.equals("No")){
									File origin = new File(PropsUtil.get("liferay.home")+"/data/mailtemplate/"+atach);
									File dest = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+template.getIdTemplate());
									FileUtils.copyDirectoryToDirectory(origin, dest);
								}
							} catch (Exception e){
								e.printStackTrace();
							}
						}
						
					}

				}catch(Exception e){
					e.printStackTrace();
				} finally {
					if (reader != null){
						reader.close();
					}
				}
			}	
		}
	}
	
}
