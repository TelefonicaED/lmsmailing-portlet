
<%@ include file="/init.jsp"%>


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
			<select name="conditionClassName" id="conditionClassName">
			 	<c:forEach items="${conditions}" var="contition" >
		  			<option <c:if test="${mailjob.conditionClassName eq contition.className}"> selected="selected"</c:if> value="${contition.className}">${contition.getName(themeDisplay.locale)}</option>
		  		</c:forEach>
			</select>
		</span>
		
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
		<span class="aui-field-content">
			<label class="aui-field-label"><liferay-ui:message key="state" /></label>
			<select multiple="multiple" name="con_state" id="con_state" required="required">
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
	<h2><liferay-ui:message key="reference" /></h2>
	<div class="aui-fieldset">
		<p><liferay-ui:message key="groupmailing.reference" /></p>
		<span class="aui-field-content">
			<label class="aui-field-label"><liferay-ui:message key="groupmailing.reference-class" /></label>
			<select name="referenceClassName" id="referenceClassName" class="aui-field-input aui-field-input-select aui-field-input-menu">
			 	<c:forEach items="${conditions}" var="contition" >
		  			<option <c:if test="${mailjob.dateClassName eq contition.className}"> selected="selected"</c:if> value="${contition.className}">${contition.getName(themeDisplay.locale)}</option>
		  		</c:forEach>
			</select>
		</span>
	
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
		<span class="aui-field-content">
			<label class="aui-field-label"><liferay-ui:message key="state" /></label>
			<select name="ref_state" id="ref_state">
				<option <c:if test="${mailjob.dateReferenceDate eq 0}"> selected="selected"</c:if> value="0"><liferay-ui:message key="groupmailing.init-date" /></option>
				<option <c:if test="${mailjob.dateReferenceDate eq 1}"> selected="selected"</c:if> value="1"><liferay-ui:message key="groupmailing.end-date" /></option>
				<option <c:if test="${mailjob.dateReferenceDate eq 2}"> selected="selected"</c:if> value="2"><liferay-ui:message key="groupmailing.inscription-date" /></option>
			</select>
		</span>
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
</div>
<aui:button-row>
		<aui:button type="submit" />
	<aui:button onClick="${cancel}" type="cancel" />
</aui:button-row>
</aui:form>


