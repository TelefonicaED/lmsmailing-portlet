<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@page import="com.tls.liferaylms.mail.service.*"%>
<%@page import="java.util.Iterator"%>
<%@ include file="/init.jsp" %>

<div class="groupmailing">

	<portlet:renderURL var="sendNewMailURL">
		<portlet:param name="jspPage" value="/html/groupmailing/newMail.jsp"></portlet:param>
	</portlet:renderURL>
	
	<div class="sendnewmail">
		<span class="newitem2">
			<liferay-ui:icon image="add_article" label="<%=true%>" message="groupmailing.send.newmail" url='<%=sendNewMailURL%>' />
		</span>
	</div>

	<div class="select_template" >			
		<liferay-ui:search-container emptyResultsMessage="there-are-no-templates" delta="5">
			<liferay-ui:search-container-results>
				<%
				//List<MailTemplate> templates = MailTemplateLocalServiceUtil.getMailTemplates(0, MailTemplateLocalServiceUtil.getMailTemplatesCount());
				
				List<MailTemplate> templates = MailTemplateLocalServiceUtil.getMailTemplateByGroupIdAndGlobalGroupId(themeDisplay.getScopeGroupId());
				
				//MailTemplateLocalServiceUtil.
				
				results = ListUtil.subList(templates, searchContainer.getStart(),
				searchContainer.getEnd());
				total = templates.size();
				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
				%>
			</liferay-ui:search-container-results>
			
			<liferay-ui:search-container-row className="com.tls.liferaylms.mail.model.MailTemplate" keyProperty="idTemplate" modelVar="template">
				<liferay-ui:search-container-column-text name="subject" property="subject"/>
				<liferay-ui:param name="idTemplate" value="<%=String.valueOf(template.getIdTemplate()) %>"></liferay-ui:param>
				<liferay-ui:search-container-column-jsp path="/html/groupmailing/actions.jsp" align="right" />
			</liferay-ui:search-container-row>
			
			<liferay-ui:search-iterator />
			
		</liferay-ui:search-container>
	</div>
</div>