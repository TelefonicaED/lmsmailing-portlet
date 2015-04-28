<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@page import="com.tls.liferaylms.mail.service.*"%>
<%@page import="java.util.Iterator"%>
<%@ include file="/init.jsp" %>

<%
	Long idTemplate = ParamUtil.getLong(request,"idTemplate",0);

	if(idTemplate > 0)
	{
		MailTemplate template = MailTemplateLocalServiceUtil.getMailTemplate(idTemplate);
%>

<aui:script>
	function showConfirm(){
		AUI().use(function(A) {
			var r = confirm("<%=LanguageUtil.get(pageContext,"groupmailing.messages.confirm.popup")%>");
			if (r == true){
					alert("<%=LanguageUtil.get(pageContext,"groupmailing.messages.confirm.popup.acept")%>");
					A.one('#<portlet:namespace/>form_mail').submit();
			}else{
					alert("<%=LanguageUtil.get(pageContext,"groupmailing.messages.confirm.popup.cancel")%>");
				}
			return r;
		});
	}
</aui:script>

<div class="groupmailing">
	<liferay-portlet:actionURL name="sendMails" var="sendMailsURL">
	</liferay-portlet:actionURL>
	
	<liferay-portlet:renderURL var="returnURL">
		<liferay-portlet:param name="jspPage" value="/html/groupmailing/view.jsp"></liferay-portlet:param>
	</liferay-portlet:renderURL>
	
	<div class="portlet-msg-alert">
		<%=LanguageUtil.get(pageContext,"groupmailing.messages.confirm")%>
	</div>
	
	<aui:form action="<%=sendMailsURL %>" method="POST" name="form_mail" id="form_mail" >
	
		<h2><%=LanguageUtil.get(pageContext,"groupmailing.messages.subject")%></h2>
		<div class="mail_subject" ><%=template.getSubject() %></div>
		
		<h2><%=LanguageUtil.get(pageContext,"groupmailing.messages.body")%></h2>
		<div class="mail_content" >
			<p><%=template.getBody() %></p>
		</div>
		
		<div class="check_testing" >
			<aui:input name="testing" label="send-test" type="checkbox"></aui:input>
			<p><%=LanguageUtil.get(pageContext,"groupmailing.messages.test.help")%></p>
		</div>
		
		<aui:input name="template" label="send-test" type="hidden" value="<%=idTemplate %>"></aui:input>
		<aui:button-row>
			<aui:button type="button" value="send" label="send" class="submit" onClick="javascript:showConfirm()" ></aui:button>
			<aui:button onClick="<%=returnURL.toString() %>" type="cancel" ></aui:button>
		</aui:button-row>
	</aui:form>

</div>
<%
	}
%>