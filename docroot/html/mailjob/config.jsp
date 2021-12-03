<%@page import="com.liferay.portal.kernel.servlet.SessionMessages"%>
<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.liferay.portlet.PortletPreferencesFactoryUtil"%>
<%@include file="/init.jsp" %>

<%

	PortletPreferences preferences = null;
	String portletResource = ParamUtil.getString(request, "portletResource");
	if (Validator.isNotNull(portletResource)) {
		preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
	}else{
		preferences = renderRequest.getPreferences();
	}

	boolean calendar = (preferences.getValue("calendar", "false")).compareTo("true") == 0;
%>
<liferay-ui:success key="success" message="configuration.ok" />
<liferay-portlet:actionURL var="saveConfigurationURL"  portletConfiguration="true"/>
<aui:form action="<%=saveConfigurationURL %>" >
	<aui:input type="hidden" name="<%=Constants.CMD %>" value="<%=Constants.UPDATE %>" />
	<aui:input type="checkbox" name="calendar" label="mailjob.preferences.calendar" value="<%=calendar %>" checked="<%=calendar %>"/>

	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>
