<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@page import="com.liferay.portal.model.RoleConstants"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.portal.model.Role"%>
<%@page import="com.liferay.portal.service.RoleLocalServiceUtil"%>
<%@ include file="/init.jsp" %>
<%
	ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);	 
	
	MailTemplate template = (MailTemplate)row.getObject();
	String name = template.getClass().getName();
	String primKey = String.valueOf(template.getIdTemplate());
%>
<liferay-ui:icon-menu>
<portlet:renderURL var="editURL">
	<portlet:param name="templateId" value="<%=primKey %>" />
	<portlet:param name="jspPage" value="/html/managetemplates/template.jsp" />
</portlet:renderURL>

<%
if( permissionChecker.hasPermission(themeDisplay.getScopeGroupId(),MailTemplate.class.getName(),primKey,ActionKeys.UPDATE))
{
%>
	<liferay-ui:icon image="edit" message="edit" url="<%=editURL.toString() %>" />
<%
}
%>

<portlet:actionURL name="deleteTemplate" var="deleteURL">
	<portlet:param name="templateId" value="<%= primKey %>" />
</portlet:actionURL>

<%
if( permissionChecker.hasPermission(themeDisplay.getScopeGroupId(),MailTemplate.class.getName(),primKey,ActionKeys.DELETE))
{
%>
	<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
<%
}
%>

</liferay-ui:icon-menu>
