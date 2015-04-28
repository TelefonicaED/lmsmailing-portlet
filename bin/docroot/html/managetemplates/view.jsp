<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.Template"%>
<%@page import="com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil"%>
<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ include file="/init.jsp" %>

<div class="managetemplates">

	<liferay-ui:icon-menu showWhenSingleIcon="<%=true %>">
	
		<portlet:renderURL var="newTemplateURL">
			<portlet:param name="jspPage" value="/html/managetemplates/template.jsp"></portlet:param>
		</portlet:renderURL>
		<liferay-ui:icon image="add" label="<%=true%>" message="new-mail-template" url='<%=newTemplateURL%>' />
		
		<liferay-portlet:resourceURL var="exportURL" >
			<portlet:param name="action" value="export-templates"/>
		</liferay-portlet:resourceURL>
		<liferay-ui:icon image="export" label="<%=true%>" message="export" url='<%=exportURL%>' />
		
		<portlet:renderURL var="importURL">
			<portlet:param name="jspPage" value="/html/managetemplates/import.jsp" />
		</portlet:renderURL>
		<liferay-ui:icon image="post" message="import" url="<%=importURL %>" />
		
	</liferay-ui:icon-menu>

	<liferay-ui:error key="problem-occurred" message="managetemplates.error.delete"></liferay-ui:error>
	
	<liferay-ui:search-container emptyResultsMessage="there-are-no-templates" delta="10">
		<liferay-ui:search-container-results>
			<%
				//List<MailTemplate> templates = MailTemplateLocalServiceUtil.getMailTemplates(0, MailTemplateLocalServiceUtil.getMailTemplatesCount());
				List<MailTemplate> templates = MailTemplateLocalServiceUtil.getMailTemplateByGroupId(themeDisplay.getScopeGroupId());
				results = ListUtil.subList(templates, searchContainer.getStart(), searchContainer.getEnd());
				total = templates.size();
				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
			%>
		</liferay-ui:search-container-results>
		
		<liferay-ui:search-container-row className="com.tls.liferaylms.mail.model.MailTemplate" keyProperty="idTemplate" modelVar="template">
			<liferay-ui:search-container-column-text name="managetemplates.template">
				<%=template.getSubject()%>
			</liferay-ui:search-container-column-text>

			<%if(permissionChecker.hasPermission(
					template.getGroupId(), MailTemplate.class.getName(), template.getGroupId(), ActionKeys.UPDATE) 
					|| permissionChecker.hasPermission(template.getGroupId(), MailTemplate.class.getName(), template.getGroupId(), ActionKeys.DELETE)
					|| permissionChecker.hasPermission(template.getGroupId(), MailTemplate.class.getName(), template.getGroupId(), ActionKeys.PERMISSIONS))
			{
				
			%>			
				<liferay-ui:search-container-column-jsp path="/html/managetemplates/admin_actions.jsp" align="right" />
			<%
			}
			%>			

		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	
	</liferay-ui:search-container>
</div>
