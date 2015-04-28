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
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil;

/**
 * Portlet implementation class ManageTemplates
 */
public class ManageTemplates extends MVCPortlet {
	
	private static Log _log = LogFactoryUtil.getLog(ManageTemplates.class);

	public void saveTemplate(ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		String subject = ParamUtil.getString(actionRequest, "subject", "");
		String body = ParamUtil.getString(actionRequest, "message", "");
		boolean editing = ParamUtil.getBoolean(actionRequest, "editing", true);
		String idTemplate = ParamUtil.getString(actionRequest, "idTemplate", "");
			
		if(editing)
		{
			MailTemplate template = MailTemplateLocalServiceUtil.getMailTemplate(Long.parseLong(idTemplate));
			template.setSubject(subject);
			template.setBody(changeToURL(body, themeDisplay.getURLPortal()));
			template.setGroupId(themeDisplay.getScopeGroupId());
			template.setCompanyId(themeDisplay.getCompanyId());
			template.setUserId(themeDisplay.getUserId());
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
	
	public void deleteTemplate(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		
		String idTemplate = ParamUtil.getString(actionRequest, "templateId", "");

		if(!idTemplate.equals("")){
			MailTemplateLocalServiceUtil.deleteMailTemplate(Long.parseLong(idTemplate));
		}else{
			SessionErrors.add(actionRequest, "error-delete");
		}
	}
	
	private String changeToURL(String text, String url){
		String res ="";

		//Para imágenes
		res = text.replaceAll("src=\"/image/image_gallery", "src=\""+url+"/image/image_gallery");
		
		return res;
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
	        
	        String[] row = new String[2];
	        
	        row[0] = "Subject";
	        row[1] = "Body";
	        
	        writer.writeNext(row);
	        
	        List<MailTemplate> mailTemplates = MailTemplateLocalServiceUtil.getMailTemplateByGroupId(themeDisplay.getScopeGroupId());
	        
	        for(MailTemplate template:mailTemplates){
	        	row[0] = template.getSubject();
		        row[1] = template.getBody();
		        
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
						
						System.out.println(" currLine : " + currLine.toString() );
						
						String subject="", body="";
						
						if(currLine[0] != null){
							subject = currLine[0];
						}
						
						if(currLine[1] != null){
							body = currLine[1];
						}
						
						System.out.println(" subject : " + subject );
						System.out.println(" body : " + body );
						
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
