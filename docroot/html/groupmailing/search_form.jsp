<%@include file="/init.jsp" %>
<%
// long courseId=ParamUtil.getLong(request, "courseId",0);
// long roleId=ParamUtil.getLong(request, "roleId",0);
%>

<script type="text/javascript">
	function <portlet:namespace />searchMailUsers(){
		var to = document.getElementById("<portlet:namespace />to").value;
		$('#<portlet:namespace />currentTo').val(to);
		$('#<portlet:namespace />emailSubject').val($('#<portlet:namespace />subject').val());
		$('#<portlet:namespace />emailContent').val(window.<portlet:namespace />body.getHTML());
		$('#<portlet:namespace />busqusu').submit();
		
	}
</script>

<liferay-portlet:renderURL var="buscarURL">
	<liferay-portlet:param name="jspPage" value="/html/groupmailing/newMail.jsp" />
<%-- 	<portlet:param name="courseId" value="<%=Long.toString(courseId) %>" /> --%>
<%-- 	<portlet:param name="roleId" value="<%=Long.toString(roleId) %>" /> --%>
</liferay-portlet:renderURL>

<div class="npa_search_user"> 
<aui:form name="busqusu" action="<%=buscarURL %>" method="post" >
	<aui:fieldset>
		
		<aui:input name="searchForm" type="hidden" value="true" />	
		<aui:input name="currentTo" type="hidden" />
		<aui:input name="emailSubject" type="hidden" />
		<aui:input name="emailContent" type="hidden" />
		
		<aui:column>
			<aui:input label="misc.user.firstName" name="firstName" size="20" value="" />
			<aui:input label="misc.user.screenName" name="screenName" size="20" value="" />	
		</aui:column>	
					
		<aui:column>			
			<aui:input label="misc.user.lastName" name="lastName" size="20" value="" />				
			<aui:input label="misc.user.emailAddress" name="emailAddress" size="20" value="" />
		</aui:column>
	
		<aui:column>	
			<aui:select label="misc.search.allFields" name="andSearch">
				<aui:option label="misc.search.all" selected="true" value="true" />
				<aui:option label="misc.search.any" value="false" />
			</aui:select>		
		</aui:column>
		
		<aui:button-row>
			<aui:button name="searchUsers" value="search" onClick="javascript:${renderResponse.getNamespace()}searchMailUsers()" />
		</aui:button-row>
	</aui:fieldset>
</aui:form>
	
</div>
