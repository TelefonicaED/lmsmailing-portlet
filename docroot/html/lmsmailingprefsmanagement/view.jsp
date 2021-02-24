<%@page import="java.util.Locale"%>
<%@page import="com.liferay.portal.kernel.util.PrefsPropsUtil"%>
<%@page import="com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil"%>
<%@page import="com.liferay.portlet.expando.model.ExpandoColumn"%>
<%@page import="com.liferay.portlet.expando.model.ExpandoTableConstants"%>
<%@page import="com.tls.liferaylms.util.MailConstants"%>
<%@page import="com.liferay.lms.model.Course"%>
<%@page import="com.liferay.portal.model.User"%>
<%@page import=" com.tls.liferaylms.util.JavaScriptUtil"%>
<%@include file="/init.jsp" %>

<%
String header = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), MailConstants.HEADER_PREFS, LanguageUtil.get(Locale.getDefault(),"mail.header"));
String footer = PrefsPropsUtil.getString(themeDisplay.getCompanyId(), MailConstants.FOOTER_PREFS, LanguageUtil.get(Locale.getDefault(),"mail.footer"));
%>

<liferay-ui:success key="update-ok" message="mail.preferences.ok"/>
<liferay-ui:error key="update-ko" message="mail.preferences.error"/>

<aui:form name='fm' method="POST" action="${updateURL}">
	<aui:fieldset label="mail.preferences.internal-messaging" >
		<aui:input type="checkbox" name="internalMessagingActive" label="mail.preferences.activate-internal-messaging" value="${internalMessagingActive}" checked="${internalMessagingActive}"/>
	</aui:fieldset>
	<aui:fieldset label="mail.preferences.send-always-message" >
		<aui:input type="checkbox" name="sendAlwaysMessage" label="mail.preferences.send-always-message-info" value="${sendAlwaysMessage}" checked="${sendAlwaysMessage}"/>
	</aui:fieldset>
	
	<aui:fieldset label="mail.preferences.send-mail-tutor" >
		<aui:input type="checkbox" name="sendMailsToTutors" label="mail.preferences.send-mail-to-tutors" value="${sendMailsToTutors}" checked="${sendMailsToTutors}"/>
	</aui:fieldset>
	
	<aui:fieldset label="mail.preferences.configure-deregister-expando" >
		<aui:input name="deregisterMailExpando" label="mail.preferences.deregister-mail-expando" value="${deregisterMailExpando}"/>
	</aui:fieldset>
	<c:if test="${not empty mailRelationTypeHash}">
		<aui:fieldset label="mail.preferences.activate-send-mail-to-social-relations">
			<c:forEach items="${mailRelationTypeHash }" var="isActiveMailRelationTypeId">
				<c:set var="mailRelationTypePref" value="mailType_${isActiveMailRelationTypeId.key }" />
				<c:set var="labelActivateMailto" value="mail.preferences.activate-mail-to-${isActiveMailRelationTypeId.key }" />
				<aui:input type="checkbox" name="${mailRelationTypePref }" label="${labelActivateMailto }" value="${isActiveMailRelationTypeId.value }"/>
			</c:forEach>
		</aui:fieldset>
	</c:if>
	
	<aui:fieldset label="mail.preferences.show-user-expandos">
		<aui:input type="checkbox" name="showExpandosUser" label="mail.preferences.show-user-expando-fields" value="${showExpandosUser }" checked="${showExpandosUser }" 
			onchange="javascript:${renderResponse.getNamespace()}changeUserCustomAttributesToShow()"/>
		
		<div class="lfr-panel lfr-collapsible lfr-panel-basic ${showExpandosUser ? '' : 'aui-helper-hidden'}" id="${renderResponse.getNamespace()}selectUserCustomAttributesToShow">
			<%List<ExpandoColumn> listUserExpandos = ExpandoColumnLocalServiceUtil.getColumns(themeDisplay.getCompanyId(), User.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			String nameExpandoUser = "";
			boolean expandoUserChecked = false;
			for(ExpandoColumn expandoUserColumn: listUserExpandos){
				expandoUserChecked = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), MailConstants.USER_EXPANDO_TO_SHOW+String.valueOf(expandoUserColumn.getColumnId()), false);
				nameExpandoUser = "showExpandosUser" + expandoUserColumn.getColumnId();%>
				<aui:input type="checkbox" name="<%=nameExpandoUser%>" label="<%=expandoUserColumn.getName() %>" value="<%=expandoUserChecked %>" checked="<%=expandoUserChecked %>" />
			<%} %>
		</div>
	</aui:fieldset>
	
	<aui:fieldset label="mail.preferences.show-course-expandos">
		<aui:input type="checkbox" name="showExpandosCourse" label="mail.preferences.show-course-expando-fields" value="${showExpandosCourse }" checked="${showExpandosCourse }" 
			onchange="javascript:${renderResponse.getNamespace()}changeCourseCustomAttributesToShow()"/>
		
		<div class="lfr-panel lfr-collapsible lfr-panel-basic ${showExpandosCourse ? '' : 'aui-helper-hidden'}" id="${renderResponse.getNamespace()}selectCourseCustomAttributesToShow">
			<%List<ExpandoColumn> listCourseExpandos = ExpandoColumnLocalServiceUtil.getColumns(themeDisplay.getCompanyId(), Course.class.getName(), ExpandoTableConstants.DEFAULT_TABLE_NAME);
			String nameExpandoCourse = "";
			boolean expandoCourseChecked = false;
			for(ExpandoColumn expandoCourseColumn: listCourseExpandos){
				expandoCourseChecked = PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), MailConstants.COURSE_EXPANDO_TO_SHOW+String.valueOf(expandoCourseColumn.getColumnId()), false);
				nameExpandoCourse = "showExpandosCourse" + expandoCourseColumn.getColumnId();%>
				<aui:input type="checkbox" name="<%=nameExpandoCourse%>" label="<%=expandoCourseColumn.getName() %>" value="<%=expandoCourseChecked %>" checked="<%=expandoCourseChecked %>" />
			<%} %>
		</div>
	</aui:fieldset>
	<aui:fieldset label="mail.preferences.header-footer">
		<aui:field-wrapper label="header">
			<liferay-ui:input-editor name="header" initMethod="initEditorHeader"/>
		</aui:field-wrapper>
		<aui:field-wrapper label="footer">
			<liferay-ui:input-editor name="footer" initMethod="initEditorFooter"/>
		</aui:field-wrapper>
		<script type="text/javascript">
	    <!--
		    function <portlet:namespace />initEditorHeader()
		    {
		    	return "<%=JavaScriptUtil.markupToStringLiteral(header)%>";
		    }
		    function <portlet:namespace />initEditorFooter()
		    {
		    	return "<%=JavaScriptUtil.markupToStringLiteral(footer)%>";
		    }
	        //-->
	    </script>
	</aui:fieldset>
	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>

<script>
	function <portlet:namespace />changeUserCustomAttributesToShow(){
		var checked = document.getElementById('<portlet:namespace />showExpandosUser').value;
		if(checked == 'true'){
			document.getElementById('<portlet:namespace />selectUserCustomAttributesToShow').className = "lfr-panel lfr-collapsible lfr-panel-basic";
		}else{
			document.getElementById('<portlet:namespace />selectUserCustomAttributesToShow').className = "lfr-panel lfr-collapsible lfr-panel-basic aui-helper-hidden";
		}
	}
	function <portlet:namespace />changeCourseCustomAttributesToShow(){
		var checked = document.getElementById('<portlet:namespace />showExpandosCourse').value;
		if(checked == 'true'){
			document.getElementById('<portlet:namespace />selectCourseCustomAttributesToShow').className = "lfr-panel lfr-collapsible lfr-panel-basic";
		}else{
			document.getElementById('<portlet:namespace />selectCourseCustomAttributesToShow').className = "lfr-panel lfr-collapsible lfr-panel-basic aui-helper-hidden";
		}
	}
</script>