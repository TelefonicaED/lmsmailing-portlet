<%@ include file="/init.jsp" %>
<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@page import="com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil"%>
<%@page import=" com.tls.liferaylms.util.JavaScriptUtil"%>

<%
	String templateId = ParamUtil.getString(request, "templateId");
	
	String subject="", message="";
	boolean editing = false;

	if(!templateId.equals("")){
		MailTemplate template = MailTemplateLocalServiceUtil.getMailTemplate(Long.parseLong(templateId));
		subject = template.getSubject();
		message = template.getBody();
		editing = true;
	}
%>

<div class="managetemplates">
	
	<portlet:renderURL var="cancel" />
	<a href="<%=cancel.toString()%>"><liferay-ui:message key="back" /></a>

	<liferay-portlet:actionURL name="saveTemplate" var="saveTemplateURL">
	</liferay-portlet:actionURL>
	<aui:form name="fm" action="<%=saveTemplateURL %>" method="POST">
		<aui:input name="subject" label="subject" value="<%=subject %>"  size="120">
			<aui:validator name="maxLength">120</aui:validator>
			<aui:validator name="required"></aui:validator>
		</aui:input>
		<aui:input id="editing" name="editing" type="hidden" value="<%=editing %>"></aui:input>
		<aui:input id="idTemplate" name="idTemplate" type="hidden" value="<%=templateId %>"></aui:input>
		<aui:field-wrapper label="message">
			<liferay-ui:input-editor name="message" />

			<script type="text/javascript">
		    <!--
			
			    function <portlet:namespace />initEditor()
			    {
			    	return "<%=JavaScriptUtil.markupToStringLiteral(message)%>";
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
		    		<li>[$PAGE_URL$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.urlcourse")%></li>
		    		<li>[$URL$] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.url")%></li>
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