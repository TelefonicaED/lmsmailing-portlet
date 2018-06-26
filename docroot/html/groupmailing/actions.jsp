<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@ include file="/init.jsp" %>
<%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	MailTemplate template = (MailTemplate)row.getObject();
%>

<liferay-ui:icon-menu>
	<portlet:renderURL var="sendNewMailURL">
		<portlet:param name="jspPage" value="/html/groupmailing/newMail.jsp"/>
		<portlet:param name="idTemplate" value="<%=String.valueOf(template.getIdTemplate()) %>" />
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message='<%=LanguageUtil.get(pageContext,"groupmailing.messages.send")%>' url="<%=sendNewMailURL.toString() %>" />
</liferay-ui:icon-menu>