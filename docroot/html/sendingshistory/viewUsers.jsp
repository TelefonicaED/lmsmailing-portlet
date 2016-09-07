<%@page import="com.tls.liferaylms.mail.model.AuditReceiverMail"%>
<%@page import="com.tls.liferaylms.mail.service.AuditReceiverMailLocalServiceUtil"%>
<%@page import="com.tls.liferaylms.mail.service.AuditReceiverMailLocalService"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@page import="com.liferay.lms.NoSuchCourseException"%>
<%@page import="com.liferay.lms.model.Course"%>
<%@page import="com.liferay.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.tls.liferaylms.mail.NoSuchMailTemplateException"%>
<%@page import="com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil"%>
<%@page import="com.tls.liferaylms.mail.model.MailTemplate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.tls.liferaylms.mail.service.AuditSendMailsLocalServiceUtil"%>
<%@page import="com.tls.liferaylms.mail.model.AuditSendMails"%>
<%@ include file="/init.jsp" %>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setParameter("jspPage","/html/sendingshistory/viewUsers.jsp");
	long auditSendMailId = ParamUtil.getLong(renderRequest,"auditSendMailId");
	portletURL.setParameter("auditSendMailId",String.valueOf(auditSendMailId));
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
%>

<liferay-ui:search-container emptyResultsMessage="there-are-no-mailjobs" delta="10" deltaConfigurable="true" iteratorURL="<%=portletURL%>" >
	<liferay-ui:search-container-results>
<%
	List<AuditReceiverMail> receivers =  AuditReceiverMailLocalServiceUtil.getRecieverMailsBySendMail(auditSendMailId,searchContainer.getStart(),searchContainer.getEnd());
		
	results = receivers;
	total = AuditReceiverMailLocalServiceUtil.countRecieverMailsBySendMail(auditSendMailId);
	
	pageContext.setAttribute("results", results);
	pageContext.setAttribute("total", total);
%>
	</liferay-ui:search-container-results>
	
	<liferay-ui:search-container-row className="com.tls.liferaylms.mail.model.AuditReceiverMail" keyProperty="auditReceiverMailId" modelVar="receiver">
		
		<liferay-ui:search-container-column-text name="to">
			<c:out value="<%=receiver.getTo()%>"/>
		</liferay-ui:search-container-column-text>
		
		<liferay-ui:search-container-column-text name="status">	
			<c:choose>
				<c:when test="<%=receiver.getStatus() == 0 %>">
					<liferay-ui:message key="error" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="sent" />
				</c:otherwise>
			</c:choose>
		</liferay-ui:search-container-column-text>		
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />
	
</liferay-ui:search-container>

<%
	PortletURL backURL = renderResponse.createRenderURL();
	portletURL.setParameter("jspPage","/html/sendingshistory/view.jsp");
%>
<aui:form name="backFm" action="<%= backURL %>">
	<aui:button type="submit" value="back" name="back"/>
</aui:form>