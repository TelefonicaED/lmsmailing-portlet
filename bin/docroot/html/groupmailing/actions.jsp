<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@ include file="/init.jsp" %>
<%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
	MailTemplate template = (MailTemplate)row.getObject();
%>
<liferay-ui:icon-menu>
	<portlet:renderURL  var="sendURL">
		<portlet:param name="idTemplate" value="<%=String.valueOf(template.getIdTemplate()) %>" />
		<portlet:param name="jspPage" value="/html/groupmailing/preview.jsp"></portlet:param>
	</portlet:renderURL>
	<liferay-ui:icon image="edit" message='<%=LanguageUtil.get(pageContext,"groupmailing.messages.send")%>' url="<%=sendURL.toString() %>" />
</liferay-ui:icon-menu>