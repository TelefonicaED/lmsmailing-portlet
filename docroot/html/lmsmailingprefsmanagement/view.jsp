<%@include file="/init.jsp" %>

<liferay-ui:success key="update-ok" message="mail.preferences.ok"/>
<liferay-ui:error key="update-ko" message="mail.preferences.error"/>

<aui:form name='fm' method="POST" action="${updateURL}">
	<aui:fieldset label="mail.preferences.internal-messaging" >
		<aui:input type="checkbox" name="internalMessagingActive" label="mail.preferences.activate-internal-messaging" value="${internalMessagingActive}" checked="${internalMessagingActive}"/>
	</aui:fieldset>
	<aui:fieldset label="mail.preferences.send-always-message" >
		<aui:input type="checkbox" name="sendAlwaysMessage" label="mail.preferences.send-always-message-info" value="${sendAlwaysMessage}" checked="${sendAlwaysMessage}"/>
	</aui:fieldset>
	<aui:fieldset label="mail.preferences.configure-deregister-expando" >
		<aui:input name="deregisterMailExpando" label="mail.preferences.deregister-mail-expando" value="${deregisterMailExpando}"/>
	</aui:fieldset>
	<aui:button-row>
		<aui:button type="submit" value="save" />
	</aui:button-row>
</aui:form>