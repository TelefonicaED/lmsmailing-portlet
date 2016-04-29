<%@ include file="/init.jsp"%>
<portlet:actionURL var="processURL" name="executeMailJobs"/>

<aui:form name="fm" action="<%=processURL %>" method="POST">
	<aui:button type="submit" value="executeMailJobs"/>	
</aui:form>