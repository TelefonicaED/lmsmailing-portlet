<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.liferay.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="com.liferay.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.liferay.lms.model.Course"%>
<%@include file="/init.jsp" %>

<%

	PortletPreferences preferences = null;
	String portletResource = ParamUtil.getString(request, "portletResource");
	Course course=CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
	if (Validator.isNotNull(portletResource)) {
		preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
	}else{
		preferences = renderRequest.getPreferences();
	}
	boolean ownTeam = (preferences.getValue("ownTeam", "false")).compareTo("true") == 0;
	boolean filterByCommunity = false;
	if(course==null)
		filterByCommunity = preferences.getValue("filterByCommunity", "false").compareTo("true") == 0;
%>
<liferay-ui:success key="success" message="configuration.ok" />
<liferay-portlet:actionURL var="saveConfigurationURL"  portletConfiguration="true"/>
<aui:form action="<%=saveConfigurationURL %>" >
	<aui:input type="hidden" name="<%=Constants.CMD %>" value="<%=Constants.UPDATE %>" />
	<aui:input type="checkbox" name="ownTeam" label="ownTeam" value="<%=ownTeam %>" checked="<%=ownTeam %>"/>
	<%if(course==null) {%>
		<aui:input type="checkbox" name="filterByCommunity" label="groupmailing.config.filter-by-community" value="<%=filterByCommunity %>" checked="<%=filterByCommunity %>" helpMessage="groupmailing.config.help.only-when-no-course"/>
	<%} %>
	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>
