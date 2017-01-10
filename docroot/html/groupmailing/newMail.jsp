<%@page import="com.liferay.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="com.liferay.portal.service.TeamLocalServiceUtil"%>
<%@page import="com.liferay.portal.model.Team"%>
<%@page import="com.liferay.lms.model.LmsPrefs"%>
<%@page import="com.liferay.portal.service.RoleLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.CustomSQLParam"%>
<%@page import="com.liferay.portal.util.comparator.UserLastNameComparator"%>
<%@page import="com.liferay.lms.service.CourseLocalServiceUtil"%>
<%@page import="com.liferay.lms.model.Course"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="com.liferay.portal.kernel.dao.orm.QueryUtil"%>
<%@page import="com.liferay.lms.service.LmsPrefsLocalServiceUtil"%>
<%@page import="com.tls.liferaylms.util.JavaScriptUtil"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
<%@page import="com.liferay.portal.util.comparator.UserFirstNameComparator"%>
<%@page import="com.liferay.portal.kernel.util.OrderByComparator"%>
<%@ include file="/init.jsp" %>

<%
	LmsPrefs prefs=LmsPrefsLocalServiceUtil.getLmsPrefs(themeDisplay.getCompanyId());

	String body=""; 
	String extractCodeFromEditor = renderResponse.getNamespace() + "extractCodeFromEditor()";
	String criteria = request.getParameter("criteria");
	
	boolean backToEdit = ParamUtil.getBoolean(request, "backToEdit");
	String redirectOfEdit = ParamUtil.getString(request, "redirectOfEdit");
	String firstName = ParamUtil.getString(request,"firstName");
	String lastName = ParamUtil.getString(request,"lastName");
	String screenName = ParamUtil.getString(request,"screenName");	
	String emailAddress = ParamUtil.getString(request,"emailAddress");
	boolean andSearch = ParamUtil.getBoolean(request,"andSearch",true);
	boolean searchForm = ParamUtil.getBoolean(request,"searchForm",false);
	long courseId=0;
	Course course=null;
	
	try{
		course=CourseLocalServiceUtil.getCourseByGroupCreatedId(themeDisplay.getScopeGroupId());
		courseId=course.getCourseId();
	}catch(Exception e){}
	
	if (criteria == null) criteria = "";
	if (firstName == null) firstName = "";
	if (lastName == null) lastName = "";
	if (screenName == null) screenName = "";
	if (emailAddress == null) emailAddress = "";
	
	PortletURL portletURL = renderResponse.createRenderURL();
	portletURL.setParameter("jspPage","/html/groupmailing/newMail.jsp");
	portletURL.setParameter("criteria", criteria); 
// 	String name = ParamUtil.getString(request, "name",null);
	portletURL.setParameter("firstName", firstName); 
	portletURL.setParameter("lastName", lastName);
	portletURL.setParameter("screenName", screenName);
	portletURL.setParameter("emailAddress", emailAddress);
	portletURL.setParameter("andSearch",Boolean.toString(andSearch));
 	portletURL.setParameter("courseId",Long.toString(courseId));
// 	portletURL.setParameter("roleId",Long.toString(roleId));
	portletURL.setParameter("backToEdit",Boolean.toString(backToEdit));
	if(backToEdit) {
		portletURL.setParameter("backToEdit",redirectOfEdit);
	}
%>

<%
PortletPreferences preferences = null;
String portletResource = ParamUtil.getString(request, "portletResource");
if (Validator.isNotNull(portletResource)) {
	preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
}else{
	preferences = renderRequest.getPreferences();
}
boolean ownTeam = (preferences.getValue("ownTeam", "false")).compareTo("true") == 0;
java.util.List<Team> userTeams= new ArrayList<Team>();
if (ownTeam && !permissionChecker.isOmniadmin())
	userTeams=TeamLocalServiceUtil.getUserTeams(themeDisplay.getUserId(), themeDisplay.getScopeGroupId());
else
	userTeams=TeamLocalServiceUtil.getGroupTeams(themeDisplay.getScopeGroupId());
%>
<script type="text/javascript">

	YUI.add('<portlet:namespace />user-model', function(A) {
	    A.<portlet:namespace />UserModel = A.Base.create('<portlet:namespace />UserModel', A.Model, [], {
	    }, {
	        ATTRS: {
	            name: {
	                value: ''
	            }
	        }
	    });

	    A.<portlet:namespace />UserModelList = A.Base.create('<portlet:namespace />UserModelList', A.ModelList, [], {
	        comparator: function (model) {
	            return model.get('name');
	        },
	        model: A.<portlet:namespace />UserModel
	    });
	
	}, '' ,{requires:['model-list']});

	function <portlet:namespace />addUser(userId, userName){
		AUI().use('node-base','<portlet:namespace />user-model', function(A) {
			var existingUser=window.<portlet:namespace />selectedUsers.getById(userId);
			if(existingUser!=null){
				window.<portlet:namespace />selectedUsers.remove(existingUser);
			}	
			window.<portlet:namespace />selectedUsers.add(
					new A.<portlet:namespace />UserModel({id:userId,name:'<li class="yui3-widget aui-component aui-textboxlistentry aui-textboxlistentry-focused" tabindex="0"><span class="aui-textboxlistentry-content"><span class="aui-textboxlistentry-text">'+userName+'<span class="aui-icon aui-icon-close aui-textboxlistentry-close" onClick="<portlet:namespace />deleteUser('+userId+')" /></span></span></li>'}));
			
			var selectedUsers = '';
			window.<portlet:namespace />selectedUsers.each(function(value){selectedUsers+=value.get('name');});
			A.one('#<portlet:namespace />selected_users').setContent(selectedUsers);	
			A.one('#<portlet:namespace />to').val(window.<portlet:namespace />selectedUsers.get('id').toString());
			A.all('#<portlet:namespace />addUser_'+userId).each(function(addUserDiv){ addUserDiv.hide(); });  
			A.all('#<portlet:namespace />deleteUser_'+userId).each(function(deleteUserDiv){ deleteUserDiv.show(); });  
			
			var addUserElement = A.one('#_groupmailing_WAR_lmsmailingportlet_addUser_'+userId);
			if (addUserElement != null) {
				addUserElement.ancestor('tr').addClass('taglib-search-iterator-highlighted');
			}
		});			
	}
	
	function <portlet:namespace />deleteUser(userId){
		AUI().use('node-base','<portlet:namespace />user-model', function(A) {
			var existingUser=window.<portlet:namespace />selectedUsers.getById(userId);
			if(existingUser!=null){
				window.<portlet:namespace />selectedUsers.remove(existingUser);
			}	
			
			var selectedUsers = '';
			window.<portlet:namespace />selectedUsers.each(function(value){selectedUsers+=value.get('name');});
			A.one('#<portlet:namespace />selected_users').setContent(selectedUsers);	
			A.one('#<portlet:namespace />to').val(window.<portlet:namespace />selectedUsers.get('id').toString());
			A.all('#<portlet:namespace />addUser_'+userId).each(function(addUserDiv){ addUserDiv.show(); });  
			A.all('#<portlet:namespace />deleteUser_'+userId).each(function(deleteUserDiv){ deleteUserDiv.hide(); });
			
			var addUserElement = A.one('#_groupmailing_WAR_lmsmailingportlet_addUser_'+userId);
			if (addUserElement != null) {
				addUserElement.ancestor('tr').removeClass('taglib-search-iterator-highlighted');
			}
		});		
	}
	
	function <portlet:namespace />changeSelection(){
		AUI().use('node-base','<portlet:namespace />user-model', function(A) {
			if (A.one('input:radio[name=<portlet:namespace />radio_to]:checked').get('value')=='all') {
				window.<portlet:namespace />selectedUsers.each(
					function(userModel){
						A.all('#<portlet:namespace />addUser_'+userModel.get('id')).each(function(addUserDiv){ addUserDiv.show(); });  
						A.all('#<portlet:namespace />deleteUser_'+userModel.get('id')).each(function(deleteUserDiv){ deleteUserDiv.hide(); });  
					}
				);
				window.<portlet:namespace />selectedUsers.reset();
				if (document.getElementById('team_selector'))
					document.getElementById('team_selector').style.display='none';
				A.one('#<portlet:namespace />to').val ('');
				A.one('#<portlet:namespace />selected_users').setContent('<liferay-ui:message key="mailing.all-users"/>');
				A.one('#<portlet:namespace />student_search').hide();
				A.one('#<portlet:namespace />search_box').hide();
			}
			else if (A.one('input:radio[name=<portlet:namespace />radio_to]:checked').get('value')=='student') {
				if (document.getElementById('team_selector'))
					document.getElementById('team_selector').style.display='none';
				A.one('#<portlet:namespace />selected_users').setContent('');
				A.one('#<portlet:namespace />student_search').show();
				A.one('#<portlet:namespace />search_box').show();

			}else if (A.one('input:radio[name=<portlet:namespace />radio_to]:checked').get('value')=='teams') {
				if (document.getElementById('team_selector')){
					document.getElementById('team_selector').style.display='block';
					A.one('#<portlet:namespace />to').val("team_" + A.one('#<portlet:namespace />teamId').get('value'));
				}
				A.one('#<portlet:namespace />selected_users').setContent('');
				A.one('#<portlet:namespace />student_search').hide();
				A.one('#<portlet:namespace />search_box').hide();
			}			
		});		
	}
	
	function changeTeam(){
		var selects = document.getElementById("<portlet:namespace />teamId");
		var selectedValue = selects.options[selects.selectedIndex].value;
		var to = document.getElementById("<portlet:namespace />to");
		to.value = "team_" + selectedValue;
	}

	AUI().ready('node-base','<portlet:namespace />user-model', function(A) {
		window.<portlet:namespace />selectedUsers = new A.<portlet:namespace />UserModelList();
		
		var searchContainer = A.one('#<%=renderResponse.getNamespace() %>usersSearchContainerSearchContainer').ancestor('.lfr-search-container');
		searchContainer.on('ajaxLoaded',function(){
			window.<portlet:namespace />selectedUsers.each(
				function(userModel){
					A.all('#<portlet:namespace />addUser_'+userModel.get('id')).each(function(addUserDiv){ addUserDiv.hide(); });  
					A.all('#<portlet:namespace />deleteUser_'+userModel.get('id')).each(function(deleteUserDiv){ deleteUserDiv.show(); });  
				}
			);
		});

		A.one('#<%=renderResponse.getNamespace() %>form_mail').on('submit', function(evt) {			
	         if((A.one('input:radio[name=<portlet:namespace />radio_to]:checked').get('value')=='student')&& 
	    	    (window.<portlet:namespace />selectedUsers.isEmpty ())) {
	             evt.preventDefault();
	             evt.halt();             
	         }
	    });
		
	});

</script>

<aui:form name="form_to" >
	<aui:field-wrapper name="mailto">
		<aui:input checked="<%= !searchForm %>" inlineLabel="true" name="radio_to" type="radio" value="all" label="mailing.all-users"  onClick="<%=renderResponse.getNamespace()+\"changeSelection()\" %>" />
		<aui:input checked="<%= searchForm %>"  inlineLabel="true" name="radio_to" type="radio" value="student" label="student" onClick="<%=renderResponse.getNamespace()+\"changeSelection()\" %>"  />
	<%if(userTeams!=null&& userTeams.size()>0){%>
		<aui:input inlineLabel="teams" name="radio_to" type="radio" value="teams" label="teams" onClick="<%=renderResponse.getNamespace()+\"changeSelection()\" %>"  />
		<div id="team_selector" style="display: none">
			<aui:select name="teamId" label="team" onChange="javascript:changeTeam();">
			<%for(Team team:userTeams){ %>
				<aui:option label="<%=team.getName() %>" value="<%=team.getTeamId() %>"></aui:option>
			<%}%>	
			</aui:select>
		</div>	
	<%}%> 
	</aui:field-wrapper>
</aui:form>

<!-- Buscador a medias. Por eso está comentado. -->
<%-- <div id="<portlet:namespace />search_box" class="aui-helper-hidden" >
	<portlet:renderURL var="searchUserURL">
	    <portlet:param name="jspPage" value="/html/groupmailing/newMail.jsp" />
	</portlet:renderURL>
	
	<aui:form action="<%=searchUserURL%>">
		<aui:fieldset>
			<aui:column>
				<aui:input inlineField="true" name="name" label="" value="<%=name %>"></aui:input>
			</aui:column>
			<aui:column>
				<aui:button name="searchUsers" value="search" type="submit"></aui:button>
			</aui:column>
		</aui:fieldset>
	</aui:form>
</div> --%>
<!-- Fin de comentario de buscador a medias -->	

<div id="<portlet:namespace />student_search" class="aui-helper-hidden" >

	<jsp:include page="/html/groupmailing/search_form.jsp" />
	
	<liferay-ui:search-container iteratorURL="<%=portletURL%>" emptyResultsMessage="there-are-no-results" delta="5" deltaConfigurable="true" >

	   	<liferay-ui:search-container-results>
			<%
			List<User> userListPage  = null;
			String middleName = null;
			OrderByComparator obc = new   UserLastNameComparator(true);			
			LinkedHashMap userParams = new LinkedHashMap();
			int userCount = 0;

			if (Validator.isNotNull(course)){
				userParams.put("notInCourseRoleTeach", new CustomSQLParam("WHERE User_.userId NOT IN "
			              + " (SELECT UserGroupRole.userId " + "  FROM UserGroupRole "
			              + "  WHERE  (UserGroupRole.groupId = ?) AND (UserGroupRole.roleId = ?))", new Long[] {
			              course.getGroupCreatedId(),
			              RoleLocalServiceUtil.getRole(prefs.getTeacherRole()).getRoleId() }));
			           
			  	userParams.put("notInCourseRoleEdit", new CustomSQLParam("WHERE User_.userId NOT IN "
			              + " (SELECT UserGroupRole.userId " + "  FROM UserGroupRole "
			              + "  WHERE  (UserGroupRole.groupId = ?) AND (UserGroupRole.roleId = ?))", new Long[] {
			              course.getGroupCreatedId(),
			              RoleLocalServiceUtil.getRole(prefs.getEditorRole()).getRoleId() }));
			  	
			  	if (ownTeam && !permissionChecker.isOmniadmin() && (userTeams!=null) && (userTeams.size()>0)){
			  		
			  		StringBuffer teamIds = new StringBuffer();
			  		teamIds.append(userTeams.get(0).getTeamId());
			  		if (userTeams.size() > 1){
				  		for(int i = 1; i<userTeams.size(); i++){
				  			teamIds.append(",");
				  			teamIds.append(userTeams.get(i).getTeamId());
				  		}
			  		}
			  		
			  		userParams.put("inMyTeams", new CustomSQLParam("WHERE User_.userId IN "
				              + " (SELECT distinct(Users_Teams.userId) FROM Users_Teams WHERE Users_Teams.teamId in ("+teamIds.toString()+ "))",null ));
			  	}
			  	
			  	userParams.put("usersGroups", new Long(themeDisplay.getScopeGroupId()));
			}
			
			if ((firstName.trim().length()==0) || (lastName.trim().length()==0) ||
				(screenName.trim().length()==0)|| (emailAddress.trim().length()==0)){
				
				userListPage  = UserLocalServiceUtil.search(themeDisplay.getCompanyId(), firstName, StringPool.BLANK, 
															lastName, screenName, emailAddress, 0, userParams, true, 
															searchContainer.getStart(), searchContainer.getEnd(), obc);
				
				userCount	  = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), firstName, StringPool.BLANK,
																 lastName, screenName, emailAddress, 0, userParams, true);
				
			}else{
				userListPage  = UserLocalServiceUtil.search(themeDisplay.getCompanyId(), criteria, 0, userParams, 
															searchContainer.getStart(), searchContainer.getEnd(), obc);
				userCount 	  = UserLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), criteria, 0, userParams);
			}
				
			pageContext.setAttribute("results", userListPage);
			pageContext.setAttribute("total", userCount);
	
			%>
		</liferay-ui:search-container-results>
		
		<liferay-ui:search-container-row className="com.liferay.portal.model.User" keyProperty="userId" modelVar="user">
			<liferay-ui:search-container-column-text name="studentsearch.user.firstName" title="studentsearch.user.firstName"><%=user.getFullName() %></liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text>
				<a id="<portlet:namespace />addUser_<%=user.getUserId() %>" onClick="<portlet:namespace />addUser(<%=user.getUserId() %>, '<%=user.getFullName() %>')" style="Cursor:pointer;" >
				<liferay-ui:message key="select" /></a>
				<a id="<portlet:namespace />deleteUser_<%=user.getUserId() %>" class="aui-helper-hidden" onClick="<portlet:namespace />deleteUser(<%=user.getUserId() %>)" style="Cursor:pointer;" >
				<liferay-ui:message key="groupmailing.deselect" /></a>			
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

	 	<liferay-ui:search-iterator />
		
		<script type="text/javascript">
		<!--
			function <portlet:namespace />ajaxMode<%= searchContainer.getId(request, renderResponse.getNamespace()) %>SearchContainer(A) {

				var searchContainer = A.one('#<%=renderResponse.getNamespace() %><%= searchContainer.getId(request, renderResponse.getNamespace()) %>SearchContainer').ancestor('.lfr-search-container');
				
				function <portlet:namespace />reload<%= searchContainer.getId(request, renderResponse.getNamespace()) %>SearchContainer(url){

					var params = {};
					var urlPieces = url.split('?');
					if (urlPieces.length > 1) {
						params = A.QueryString.parse(urlPieces[1]);
						params.p_p_state='<%=LiferayWindowState.EXCLUSIVE.toString() %>';
						url = urlPieces[0];
					}
	
					A.io.request(
						url,
						{
							data: params,
							dataType: 'html',
							on: {
								failure: function(event, id, obj) {
									var portlet = A.one('#p_p_id<portlet:namespace />');
									portlet.hide();
									portlet.placeAfter('<div class="portlet-msg-error">'+Liferay.Language.get("there-was-an-unexpected-error.-please-refresh-the-current-page")+'</div>');
								},
								success: function(event, id, obj) {
									searchContainer.setContent(A.Node.create(this.get('responseData')).one('#<%=renderResponse.getNamespace() %><%= searchContainer.getId(request, renderResponse.getNamespace()) %>SearchContainer').ancestor('.lfr-search-container').getContent ());
									<portlet:namespace />ajaxMode<%= searchContainer.getId(request, renderResponse.getNamespace()) %>SearchContainer(A);
									searchContainer.fire('ajaxLoaded');
								}
							}
						}
					);
				}

				
				<portlet:namespace /><%= searchContainer.getCurParam() %>updateCur = function(box){
					<portlet:namespace />reload<%= searchContainer.getId(request, renderResponse.getNamespace()) 
					%>SearchContainer('<%=HttpUtil.removeParameter(searchContainer.getIteratorURL().toString(), renderResponse.getNamespace() + searchContainer.getCurParam()) 
						%>&<%= renderResponse.getNamespace() + searchContainer.getCurParam() %>=' + A.one(box).val());
				};

				<portlet:namespace /><%= searchContainer.getDeltaParam() %>updateDelta = function(box){
					<portlet:namespace />reload<%= searchContainer.getId(request, renderResponse.getNamespace()) 
						%>SearchContainer('<%=HttpUtil.removeParameter(searchContainer.getIteratorURL().toString(), renderResponse.getNamespace() + searchContainer.getDeltaParam()) 
							%>&<%= renderResponse.getNamespace() + searchContainer.getDeltaParam() %>=' + A.one(box).val());
				};

				searchContainer.all('.taglib-page-iterator').each(
					function(pageIterator){
						pageIterator.all('a').each(
							function(anchor){
								var url=anchor.get('href');
								anchor.set('href','#');
							    anchor.on('click',
									function(){
							    		<portlet:namespace />reload<%= searchContainer.getId(request, renderResponse.getNamespace()) %>SearchContainer(url);
								    }
							    );
							}
						);
					}
				);

			};

			AUI().ready('aui-io-request','querystring-parse','aui-parse-content',<portlet:namespace />ajaxMode<%= searchContainer.getId(request, renderResponse.getNamespace()) %>SearchContainer);
			AUI().ready( function() { if(<%= searchForm %>){ <portlet:namespace />changeSelection(); } } );
		//-->
		</script>

	</liferay-ui:search-container>
			
	
	<div class="to">
		<liferay-ui:message key="groupmailing.messages.to" />
		<div class="aui-helper-clearfix aui-textboxlistentry-holder" id="<portlet:namespace />selected_users" ><liferay-ui:message key="mailing.all-users"/></div>
		<%-- <ul class="aui-helper-clearfix aui-textboxlistentry-holder" id="<portlet:namespace />selected_users"> --%>
	</div>
	
</div>



<div class="newmail">

	<liferay-portlet:actionURL name="sendNewMail" var="sendNewMailURL" >
	</liferay-portlet:actionURL>
	
	<liferay-portlet:renderURL var="returnURL">
		<liferay-portlet:param name="jspPage" value="/html/groupmailing/view.jsp"></liferay-portlet:param>
	</liferay-portlet:renderURL>
		
	<aui:form action="<%=sendNewMailURL %>" method="POST" name="form_mail">
	
		<aui:input type="hidden" name="to" id="to" value="" />
			
		<div class="mail_subject" >
			<aui:input name="subject" title="groupmailing.messages.subject" size="120">
				<aui:validator name="maxLength">120</aui:validator>
				<aui:validator name="required"></aui:validator>
			</aui:input>
		</div>
		
		<div class="mail_content" >
			<aui:field-wrapper label="body">
				<liferay-ui:input-editor name="body" />
				<script type="text/javascript">
			    <!--				
				    function <portlet:namespace />initEditor()
				    {
				    	return "<%=JavaScriptUtil.markupToStringLiteral(body)%>";
				    }
			        //-->
			    </script>
		    </aui:field-wrapper>
		</div>
				
		<div class="check_testing" >
			<aui:input name="testing" label="send-test" type="checkbox"></aui:input>
			<p><%=LanguageUtil.get(pageContext,"groupmailing.messages.test.help")%></p>
		</div>

    	<p><%=LanguageUtil.get(pageContext,"groupmailing.messages.explain")%></p>
    	<ul>
    		<li>[@portal] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.portal")%></li>
    		<li>[@course] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.course")%></li>
    		<li>[@student] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.student")%></li>
    		<li>[@teacher] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.teacher")%></li>
    		<li>[@urlcourse] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.urlcourse")%></li>
    		<li>[@url] - <%=LanguageUtil.get(pageContext,"groupmailing.messages.url")%></li>
    	</ul>

		<aui:button-row>
			<aui:button type="submit" value="send" label="send" class="submit"></aui:button>
			<aui:button onClick="<%=returnURL.toString() %>" type="cancel" ></aui:button>
		</aui:button-row>
	</aui:form>

</div>

<%!
	public static String addWildcards(String value)
	{
		if (value == null) return null;
		if (value.length() == 1) return "%" + value + "%";
		if (value.charAt(0) != '%') value = "%" + value;
		if (value.charAt(value.length() - 1) != '%') value = value + "%";
		return value;
	}
%>