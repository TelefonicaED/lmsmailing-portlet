<%@ page import="com.liferay.portal.LARFileException" %>
<%@ page import="com.liferay.portal.LARTypeException" %>
<%@ page import="com.liferay.portal.LayoutImportException" %>

<%@ page import="com.liferay.portal.kernel.lar.PortletDataException" %>
<%@ page import="com.liferay.portal.kernel.lar.PortletDataHandler" %>
<%@ page import="com.liferay.portal.kernel.lar.PortletDataHandlerBoolean" %>
<%@ page import="com.liferay.portal.kernel.lar.PortletDataHandlerChoice" %>
<%@ page import="com.liferay.portal.kernel.lar.PortletDataHandlerControl" %>
<%@ page import="com.liferay.portal.kernel.lar.PortletDataHandlerKeys" %>
<%@ page import="com.liferay.portal.kernel.lar.UserIdStrategy" %>

<%@ include file="/init.jsp" %>	

<portlet:actionURL name="importTemplates" var="importURL" />
	
<aui:form name="form" action="<%=importURL%>" method="post" enctype="multipart/form-data">
	
	<aui:input label="import" name="importFileName" size="50" type="file" />
			
	<aui:button-row>
		<aui:button type="submit" value="import" />
	</aui:button-row>
</aui:form>