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
	portletURL.setParameter("jspPage","/html/sendingshistory/view.jsp");
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	User autor = null;
	MailTemplate template = null;
	Course course = null;
%>

<liferay-ui:search-container emptyResultsMessage="there-are-no-mailjobs" delta="10" deltaConfigurable="true" iteratorURL="<%=portletURL%>" >
	<liferay-ui:search-container-results>
<%
	List<AuditSendMails> sendings = AuditSendMailsLocalServiceUtil.getHistoryByCompanyId(themeDisplay.getCompanyId());
	List<AuditSendMails> ordererSendings = new ArrayList<AuditSendMails>(sendings);

	Collections.sort(ordererSendings, new Comparator <AuditSendMails>() {
	    @Override
	    public int compare(final AuditSendMails object1, final AuditSendMails object2) {
	    	if(object1.getSendDate()==null){
	    		return 1;
	    	}else if(object2.getSendDate()==null){
	    		return -1;
	    	}else{
	    		return (object1.getSendDate().before(object2.getSendDate())) ? 1 : -1;
	    	}
	    }
	});
	
	results = ListUtil.subList(ordererSendings, searchContainer.getStart(),searchContainer.getEnd());
	total = sendings.size();
	
	pageContext.setAttribute("results", results);
	pageContext.setAttribute("total", total);
%>
	</liferay-ui:search-container-results>
	
	<liferay-ui:search-container-row className="com.tls.liferaylms.mail.model.AuditSendMails" keyProperty="auditSendMailsId" modelVar="send">
		
		<liferay-ui:search-container-column-text name="send.date">
			<c:out value="<%=sdf.format(send.getSendDate())%>"/>
		</liferay-ui:search-container-column-text>
		
		<%
		try{
			autor = UserLocalServiceUtil.getUser(send.getUserId());
		}catch(NoSuchUserException e){}
		%>
		<liferay-ui:search-container-column-text name="author">
			<c:out value="<%=autor!=null ? autor.getScreenName() : \"-\"%>"/>
		</liferay-ui:search-container-column-text>
		
		<%
		try{
			course = CourseLocalServiceUtil.getCourseByGroupCreatedId(send.getGroupId());
		}catch(Exception e){}
		%>
		<liferay-ui:search-container-column-text name="course">	
			<c:out value="<%=course != null ? course.getTitle(themeDisplay.getLocale()) : \"-\"%>"/>
		</liferay-ui:search-container-column-text>
		
		<%
		try{
			template = MailTemplateLocalServiceUtil.getMailTemplate(send.getTemplateId());
		}catch(NoSuchMailTemplateException e){}
		%>
		<c:choose>
			<c:when test="<%= template!=null %>">
				<liferay-ui:search-container-column-text name="subject">
					<c:out value="<%=template!=null ? template.getSubject() : \"-\" %>"/>
				</liferay-ui:search-container-column-text>
				<liferay-ui:search-container-column-text name="body">
					<c:out value="<%=template!=null ? HtmlUtil.extractText(template.getBody()) : \"-\" %>"/>
				</liferay-ui:search-container-column-text>				
			</c:when>
			<c:otherwise>
					<liferay-ui:search-container-column-text name="subject">
						<c:out value="<%= send.getSubject() %>"/>
					</liferay-ui:search-container-column-text>
					<liferay-ui:search-container-column-text name="body">
						<c:out value="<%= HtmlUtil.extractText(send.getBody()) %>"/>
					</liferay-ui:search-container-column-text>
			</c:otherwise>	
		</c:choose>
		<liferay-ui:search-container-column-text name="mail.sends" property="numberOfPost" />
	
		<liferay-ui:search-container-column-text name="detail">
				<c:if test="<%= send.getNumberOfPost()>0 %>">
					<aui:button name="viewSends" type="button" value="view" onclick="javascript:${renderResponse.getNamespace()}goToViewReceiver('${send.auditSendMailsId}')"/>
				</c:if>	
		</liferay-ui:search-container-column-text>	
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />
	
</liferay-ui:search-container>


<portlet:renderURL var="goToViewReceiversURL">
	<portlet:param name="jspPage" value="/html/sendingshistory/viewUsers.jsp" />
</portlet:renderURL>

<aui:form name="fm" action="${goToViewReceiversURL }" method="POST">
	<aui:input name="auditSendMailId" value="" type="hidden"/>
</aui:form>

<script>
	function <portlet:namespace />goToViewReceiver(auditSendMailId){
		$('#<portlet:namespace />auditSendMailId').val(auditSendMailId);
		$('#<portlet:namespace />fm').submit();
	}
</script>
	