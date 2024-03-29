
<%@page import="com.tls.lms.util.LiferaylmsUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.tls.liferaylms.util.MailConstants"%>
<%@page import="com.tls.liferaylms.mail.service.MailJobLocalServiceUtil"%>
<%@page import="com.tls.liferaylms.util.MailStringPool"%>
<%@page import="com.tls.liferaylms.mail.model.MailJob"%>
<%@page import="com.liferay.portal.kernel.util.PrefsPropsUtil"%>
<%@page import="com.tls.liferaylms.mail.service.MailRelationLocalServiceUtil"%>
<%@ include file="/init.jsp"%>

<%boolean calendar = (Boolean) request.getAttribute("calendar"); %>
<c:choose>
		<c:when test="${empty mailjob}">
			<liferay-portlet:actionURL var="saveURL" name="save"/>
		</c:when>
		<c:otherwise>
			<liferay-portlet:actionURL var="saveURL" name="update">
				<liferay-portlet:param name="idJob" value="${mailjob.idJob}"/>
			</liferay-portlet:actionURL>
		</c:otherwise>
	</c:choose>

<portlet:renderURL var="cancel" />

<script type="text/javascript">

 	$( document ).ready(function() {
		showModule();
		showReferenceModule();
		showDate();
	}); 

	var hash = {};
	<c:forEach items="${modules}" var="module" >
		hash['${module.moduleId}']= [
		<c:forEach items="${activities[module.moduleId]}" var="activity" varStatus="loop">
			{id : "${activity.actId}", title : "${activity.getTitle(themeDisplay.locale)}"} ${!loop.last ? ',' : ''}
		</c:forEach>
		];
	</c:forEach>

	function changeModule(pre,att){
		var sel = document.getElementById(pre+"_module");
		var act = document.getElementById(pre+"_activity");
		
		var val = sel.options[sel.selectedIndex].value;
		
		act.options.length = 0;
		
		var arr = hash[val];
		
		var arrayLength = arr.length;
		
		for (var i = 0; i < arrayLength; i++) {
		    var obj = arr[i];
		    var opt = document.createElement('option');
		    opt.value = obj.id;
		    opt.innerHTML = obj.title;
		    act.appendChild(opt);
		}
		
		if(att!=undefined){
			var selatt = document.getElementById(att+"_module");
			selatt.selectedIndex = sel.selectedIndex;
			
			changeModule(att);
		}
	}
	
	function changeActivity(pre,att){
		var sel = document.getElementById(pre+"_activity");
		var act = document.getElementById(att+"_activity");
		
		act.selectedIndex = sel.selectedIndex;
	}
	
	function validate(){
		var sendDateDia = document.getElementById('<portlet:namespace />sendDateDia').value;
		var sendDateMes = document.getElementById('<portlet:namespace />sendDateMes').value ;
		var sendDateAno = document.getElementById('<portlet:namespace />sendDateAno').value;
		
		var send = new Date(sendDateAno,sendDateMes,sendDateDia,0,0);	
		var now = new Date();
		now.setHours(0,0,0,0);
 		if (send.getTime()>=now){
			document.getElementById('<portlet:namespace />fm').submit();
		}else{
			alert("The date must be greater than today");
		}
	}
	
	function showModule(){
		var className = document.getElementById('conditionClassName').value
		if (className=="ActivityCondition"){
			document.getElementById('module').style.display = 'block';
		}else{
			document.getElementById('module').style.display = 'none';
		}
		if (className=="InscriptionCondition"){
			document.getElementById('reference').style.display = 'none';
			document.getElementById('state').style.display = 'none';
			document.getElementById('calendario').style.display = 'none';			
		}else{
			document.getElementById('reference').style.display = 'block';
			document.getElementById('state').style.display = 'block';
			document.getElementById('calendario').style.display = 'block';	
		}
	}
	
	function showReferenceModule(){
		var className = document.getElementById('referenceClassName').value
		if (className=="ActivityCondition"){
			document.getElementById('refmodule').style.display = 'block';
			$("#ref_state option:[value='2']").attr("disabled","disabled");
		}else{
			document.getElementById('refmodule').style.display = 'none';
			$("#ref_state option:[value='2']").removeAttr("disabled");
		}
	}

	function showReference(){
		var div = document.getElementById('reference');
		var divSend = document.getElementById('dateToSend');
		if(div.style.display&&div.style.display=='none'){
			div.style.display='block';
			divSend.style.display='none';
		}else{
			div.style.display='none';
			divSend.style.display='block';
		}
	}

	function showDate(){
		var className = document.getElementById('ref_state').value
		if (className=="2"){
			document.getElementById('mod_date').style.display = 'none';
		}else{
			document.getElementById('mod_date').style.display = 'block';
		}
		
	}
</script>

<aui:form method="POST" name="fm" id="fm" action="${ saveURL}">
<aui:input name="idJob" type="hidden"/>
	<div>
		<liferay-ui:message key="template" />
		<select name="idTemplate" id="idTemplate"  >
		 	<c:forEach items="${templates}" var="item" >
	  			<option <c:if test="${mailjob.idTemplate eq item.idTemplate}"> selected="selected"</c:if> value="${item.idTemplate}">${item.subject}</option>
	  		</c:forEach>
		</select>
	</div>
	 
<div class="editMailJob">
	<h2><liferay-ui:message key="condition" /></h2>
	<div class="aui-fieldset">
		<p><liferay-ui:message key="groupmailing.condition" /></p>
		<span class="aui-field-content">
			<label class="aui-field-label"><liferay-ui:message key="groupmailing.condition-class" /></label>
			<select name="conditionClassName" id="conditionClassName" onchange="showModule()">
			 	<c:forEach items="${conditions}" var="contition" >
		  			<option <c:if test="${mailjob.conditionClassName eq contition.className}"> selected="selected"</c:if> value="${contition.className}">${contition.getName(themeDisplay.locale)}</option>
		  		</c:forEach>
			</select>
		</span>

		<div id="module" style="display:block">
			<span class="aui-field-content">
				<label class="aui-field-label"><liferay-ui:message key="module" /></label>
				<select name="con_module" id="con_module" onchange="changeModule('con','ref')">
				 	<c:forEach items="${modules}" var="module" >
			  			<option <c:if test="${condition.modConditionPK eq module.moduleId}"> selected="selected"</c:if> value="${module.moduleId}">${module.getTitle(themeDisplay.locale)}</option>
			  		</c:forEach>
				</select>
			</span>
			<span class="aui-field-content">
				<label class="aui-field-label"><liferay-ui:message key="activity" /></label>
				<select name="con_activity" id="con_activity"  onchange="changeActivity('con','ref')">
				 	<c:forEach items="${activitiestemp}" var="activity" >
			  			<option <c:if test="${condition.actConditionPK eq activity.actId}"> selected="selected"</c:if> value="${activity.actId}">${activity.getTitle(themeDisplay.locale)}</option>
			  		</c:forEach>
				</select>
			</span>
		</div>
		<div id="state">
			<span class="aui-field-content">
				<label class="aui-field-label"><liferay-ui:message key="state" /></label>
				<select multiple="multiple" name="con_state" id="con_state">
					<option
					<c:forEach items="${conditionStatus}" var="conditionSta">
						<c:if test="${conditionSta eq '0'}">selected="selected"</c:if>
					</c:forEach>
					 value="0"><liferay-ui:message key="groupmailing.not-started" /></option>
					<option 
					<c:forEach items="${conditionStatus}" var="conditionSta">
						<c:if test="${conditionSta eq '1'}">selected="selected"</c:if>
					</c:forEach>
					 value="1"><liferay-ui:message key="groupmailing.started" /></option>
					<option 
					<c:forEach items="${conditionStatus}" var="conditionSta">
						<c:if test="${conditionSta eq '2'}">selected="selected"</c:if>
					</c:forEach>
					 value="2"><liferay-ui:message key="not-passed" /></option>
					<option 
					<c:forEach items="${conditionStatus}" var="conditionSta">
						<c:if test="${conditionSta eq '3'}">selected="selected"</c:if>
					</c:forEach>
					 value="3"><liferay-ui:message key="passed" /></option>
			
				</select>
			</span>
		</div>
	</div>
	<div id="calendario">
<%-- 	<input type="checkbox" id="<portlet:namespace />calendar" name="<portlet:namespace />calendar" onclick="showReference()" checked="<%=calendar%>"><label><liferay-ui:message key="mailjob.preferences.calendar"/></label> --%>
	<aui:input type="checkbox" name="calendar" label="mailjob.preferences.calendar" value='<%=calendar %>' onChange='showReference()'/>
	<%
	Date dateToSend = (Date) request.getAttribute("dateToSend");
	
	//if (calendar && dateToSend!=null){ 
	
				SimpleDateFormat formatAno = new SimpleDateFormat("yyyy");
				Date now = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateToSend);
	%>
			<div id="dateToSend" style='display:<%=calendar?"block":"none"%>'>
				<p><liferay-ui:message key="groupmailing.release-date" /></p>
					<liferay-ui:input-date  yearRangeEnd="<%=LiferaylmsUtil.defaultEndYear %>" yearRangeStart="<%=Integer.parseInt(formatAno.format(now)) %>"
						dayParam="sendDateDia" dayValue="<%=cal.get(Calendar.DAY_OF_MONTH) %>"
						monthParam="sendDateMes" monthValue="<%=cal.get(Calendar.MONTH) %>"
						yearParam="sendDateAno" yearValue="<%=cal.get(Calendar.YEAR) %>"  yearNullable="false" 
						dayNullable="false" monthNullable="false"></liferay-ui:input-date>
			</div>
		</div>
	<%//}else{ %>
		<div id ="reference" style='display:<%=calendar?"none":"block"%>'>
			<h2><liferay-ui:message key="reference" /></h2>
			<div class="aui-fieldset">
				<p><liferay-ui:message key="groupmailing.reference" /></p>
				<span class="aui-field-content">
					<label class="aui-field-label"><liferay-ui:message key="groupmailing.reference-class" /></label>
					<select name="referenceClassName" id="referenceClassName" class="aui-field-input aui-field-input-select aui-field-input-menu" onchange="showReferenceModule()">
					 	<c:forEach items="${conditions}" var="contition" >
					 		<c:if test="${contition.className ne 'InscriptionCondition'}">
				  				<option <c:if test="${mailjob.dateClassName eq contition.className}"> selected="selected"</c:if> value="${contition.className}">${contition.getName(themeDisplay.locale)}</option>
				  			</c:if>
				  		</c:forEach>
					</select>
				</span>

				<div id="refmodule" style="display:block">
					<span class="aui-field-content">
						<label class="aui-field-label"><liferay-ui:message key="module" /></label>
						<select name="ref_module" id="ref_module" onchange="changeModule('ref')">
						 	<c:forEach items="${modules}" var="module" >
					  			<option <c:if test="${reference.modReferencePK eq module.moduleId}"> selected="selected"</c:if> value="${module.moduleId}">${module.getTitle(themeDisplay.locale)}</option>
					  		</c:forEach>
						</select>
					</span>
					<span class="aui-field-content">
						<label class="aui-field-label"><liferay-ui:message key="activity" /></label>
						<select name="ref_activity" id="ref_activity">
						 	<c:forEach items="${activitiestempref}" var="activity" >
					  			<option <c:if test="${reference.actReferencePK eq activity.actId}"> selected="selected"</c:if> value="${activity.actId}">${activity.getTitle(themeDisplay.locale)}</option>
					  		</c:forEach>
						</select>
					</span>
				</div>
				<span class="aui-field-content">
					<label class="aui-field-label"><liferay-ui:message key="state" />   <span style="font-style:italic;font-size:0.8em;">(*Si se usa por fecha de inscripci�n, el mail programado se enviar� todos los d�as)</span></label>
					
					<select name="ref_state" id="ref_state" onchange="showDate()">
						<option <c:if test="${mailjob.dateReferenceDate eq 0}"> selected="selected"</c:if> value="0"><liferay-ui:message key="groupmailing.init-date" /></option>
						<option <c:if test="${mailjob.dateReferenceDate eq 1}"> selected="selected"</c:if> value="1"><liferay-ui:message key="groupmailing.end-date" /></option>
						<option <c:if test="${mailjob.dateReferenceDate eq 2}"> selected="selected"</c:if> value="2"><liferay-ui:message key="groupmailing.inscription-date" /></option>
					</select>
					
				</span>
				<div id="mod_date">
					<span class="aui-field-content">
						<aui:input value="${days}" name="days" title="days"  >
							<aui:validator name="number"/>
						</aui:input>									
						<select id="dateShift" name="dateShift">						
							<option value="-1"><liferay-ui:message key="before" /></option>						
							<option <c:if test="${time eq 1}"> selected="selected"</c:if> value="1"><liferay-ui:message key="after" /></option>
						</select>
					</span>
				</div>
				<%
			List<Integer> mailRelationTypeIds = MailRelationLocalServiceUtil.findRelationTypeIdsByCompanyId(themeDisplay.getCompanyId());
			Long id = ParamUtil.getLong(renderRequest, MailStringPool.MAIL_JOB, 0);
			MailJob mailJob = MailJobLocalServiceUtil.fetchMailJob(id);
			
			List<Integer> selectedMailRelations = new ArrayList<Integer>();
			if(mailJob!=null){
				JSONArray selectedRelations = mailJob.getExtraDataJSON().getJSONArray(MailConstants.EXTRA_DATA_RELATION_ARRAY);
				if(selectedRelations!=null && selectedRelations.length()>0){
					for(int i = 0;i<selectedRelations.length(); i++){
						selectedMailRelations.add(selectedRelations.getInt(i));
						System.out.println("ADDING RELATION "+selectedRelations.getInt(i));
					}
				}
			}
			
			if(Validator.isNotNull(mailRelationTypeIds) && mailRelationTypeIds.size()>0){
				String mailRelationTypePref = StringPool.BLANK;
				String sendMailToRelationType = StringPool.BLANK;
				String labelSendMailToType = StringPool.BLANK;
				for(int mailRelationTypeId:mailRelationTypeIds){
					mailRelationTypePref = "mailType_"+mailRelationTypeId;
					if(PrefsPropsUtil.getBoolean(themeDisplay.getCompanyId(), mailRelationTypePref)){
						sendMailToRelationType = "sendMailToType_"+mailRelationTypeId;
						labelSendMailToType = "groupmailing.messages.send-copy-to-"+mailRelationTypeId;
						System.out.println("RELATION SELECTED " + selectedMailRelations.contains(mailRelationTypeId));
		%>
						<aui:input type="checkbox" label="<%=labelSendMailToType %>" name="<%=sendMailToRelationType %>" checked="<%=selectedMailRelations.contains(mailRelationTypeId) %>"/>
		<%				
					}
				}
			}
		%>			
			</div>
		</div>
	<%//} %>
</div>

<aui:button-row>
	<%if (calendar){ %>
		<aui:button type="button" onclick="javascript:validate()" value="save"/>
	<%}else{ %>
		<aui:button type="submit"/> 
	<%} %>
	<aui:button onClick="${cancel}" type="cancel" />
</aui:button-row>
</aui:form>


