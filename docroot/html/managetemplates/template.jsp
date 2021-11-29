<%@page import="java.io.File"%>
<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="com.liferay.portal.kernel.util.PropsUtil"%>
<%@page import="org.apache.commons.io.filefilter.TrueFileFilter"%>
<%@page import="com.liferay.portal.kernel.exception.SystemException"%>
<%@page import="com.liferay.portal.kernel.util.PrefsPropsUtil"%>
<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@page import="com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil"%>
<%@page import="com.tls.liferaylms.util.MailConstants"%>
<%@page import="com.tls.liferaylms.util.JavaScriptUtil"%>

<%@ include file="/init.jsp" %>
<%
	String templateId = ParamUtil.getString(request, "templateId");
	
	String subject="", message="", value="";
	boolean editing = false;
 	boolean hasAttach = false;
 	List<File> files = null;
	if(!templateId.equals("")){
		MailTemplate template = MailTemplateLocalServiceUtil.getMailTemplate(Long.parseLong(templateId));
		subject = template.getSubject();
		message = template.getBody();
		editing = true;
		File atachDir = new File (PropsUtil.get("liferay.home")+"/data/mailtemplate/"+template.getIdTemplate());
		if (atachDir.exists()){
			hasAttach = true;
			files = (List<File>) FileUtils.listFiles(atachDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		}
	}
%>

<div class="managetemplates">
	
	<portlet:renderURL var="cancel" />
	<a href="<%=cancel.toString()%>"><liferay-ui:message key="back" /></a>

	<liferay-portlet:actionURL name="saveTemplate" var="saveTemplateURL">
	</liferay-portlet:actionURL>
	<aui:form action="<%=saveTemplateURL %>" method="post" name="fm"  role="form" enctype="multipart/form-data">
		<aui:input name="subject" label="subject" value="<%=subject %>"  size="120">
			<aui:validator name="maxLength">120</aui:validator>
			<aui:validator name="required"></aui:validator>
		</aui:input>
		<aui:input id="editing" name="editing" type="hidden" value="<%=editing %>"></aui:input>
		<aui:input id="idTemplate" name="idTemplate" type="hidden" value="<%=templateId %>"></aui:input>
				<!-- UPLOAD ATTACHMENTS -->
		<% 
		int maxSize = MailConstants.ATTACHMENTS_DEFAULT_MAX_SIZE;
		try {
			maxSize = PrefsPropsUtil.getInteger(themeDisplay.getCompanyId(), MailConstants.ATTACHMENTS_MAX_SIZE_KEY, MailConstants.ATTACHMENTS_DEFAULT_MAX_SIZE);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		String acceptFiles = MailConstants.ATTACHMENTS_DEFAULT_ACCEPTED_FILES;
		try {
			acceptFiles = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), MailConstants.ATTACHMENTS_ACCEPTED_FILES_KEY, MailConstants.ATTACHMENTS_DEFAULT_ACCEPTED_FILES);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		%>
	<script src="/lmsmailing-portlet/js/jquery.MultiFile.js"></script>
 	<div class="col-md-4 file-wrap">
 		<input name="maxFile" id="maxFile" type="hidden" value="<%= maxSize%>"/>
       <input type="file" multiple="multiple" class="multi" name="MultipleFile1"  accept="<%= acceptFiles%>" maxsize="<%= maxSize%>" value="<%=value%>"/>
     </div>
     <%if (editing && hasAttach){ %>
	     <div class="MultiFile-list" id="old_list">
	     	<%
	     	int i =1;
	     	for (File file: files){ 
	     		String fileNumber= "file"+i;
	     		String fileName = file.getName();
	     	%>
		     	<div class="<%=fileNumber%>">
		     		<a class="old-remove" href="#" onclick="deleteFile('<%=fileNumber%>','<%=fileName %>');return false;">x</a> 
		     		<span>
		     			<span class="MultiFile-label" title="<%=fileName%>">
		     				<span class="MultiFile-title"><%=fileName%></span>
		     			</span>
		     		</span>
		     	</div>
	     	<%
	     		i++;
	     	} %>
	     </div>
	     <input type="hidden" name="borrarFile" id="borrarFile" value=""/>
     	<%} %>
	<!-- END UPLOAD ATTACHMENTS -->
	
		<aui:field-wrapper label="message">
			<liferay-ui:input-editor name="message" />

			<script type="text/javascript">
		    <!--
			
			    function <portlet:namespace />initEditor()
			    {
			    	return "<%=JavaScriptUtil.markupToStringLiteral(message)%>";
			    }
			
			    function deleteFile(clas, name){
			    	var d = '#old_list .'+clas;
			    	$(d).css('display','none');
			    	var valor = $('#borrarFile').val();
			    	if (valor ==''){
			    		valor=name;
			    	}else{
			    		valor+="##"+name;
			    	}
			    	$('#borrarFile').val(valor);
			    }
		        //-->
		    </script>
		    <div class="tokens">
		    	<p><%=LanguageUtil.get(pageContext,"groupmailing.messages.explain")%></p>
		    	<ul>
		    		<li>[$PORTAL$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.portal")%></li>
		    		<li>[$TITLE_COURSE$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.course")%></li>
		    		<li>[$USER_FULLNAME$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.student")%></li>
		    		<li>[$USER_SCREENNAME$] - <%=LanguageUtil.get(pageContext,"the-user-screen-name")%></li>
		    		<li>[$TEACHER$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.teacher")%></li>
		    		<li>[$USER_SENDER$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.user-sender")%></li>
		    		<li>[$PAGE_URL$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.urlcourse")%></li>
		    		<li>[$URL$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.url")%></li>
		    		<li>[$START_DATE$] - <%=LanguageUtil.get(pageContext,"start-execution-date")%></li>
		    		<li>[$END_DATE$] - <%=LanguageUtil.get(pageContext,"end-execution-date")%></li>
		    	</ul>

		    </div>
		</aui:field-wrapper>
		
		<aui:button-row>
			<%
			if(editing){
				
			%>	
				<aui:button type="submit" value="update"></aui:button>
			<%
			}else{
			%>
				<aui:button type="submit" value="save"></aui:button>
			<%
			}
			%>
		</aui:button-row>
	</aui:form>
</div>