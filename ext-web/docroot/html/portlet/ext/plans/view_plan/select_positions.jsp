<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * Form allowing user to select plan positions.
 *
 * @author janusz.p
 * @version 1.1
 * @since 1.1
 */
%>
<%@ include file="/html/portlet/ext/plans/init.jsp" %>
<%
	// indicates if window should be closed (this should happen when update 
	// positions request has been processed)
	boolean closeWindow = ParamUtil.getBoolean(request, "closeWindow");
%>

<script type="text/javascript">
	// positions are mutually exclusive within a question, thus this table alows for 
	// tracing if user has selected only one position from given question
	var selectedQuestionPositions = new Array();

	/**
	 * Selects position. If there has been position choosen for given question then
	 * it is be deselected.
	 *
	 * @param questionId id of a question
	 * @param positionId id of a position to be selected
	 */
	function <portlet:namespace />selectPosition(questionId, positionId) {
		selectedPosition = selectedQuestionPositions[questionId];

		if (positionId == selectedPosition) {
			if (! jQuery("#<portlet:namespace />position" + positionId).attr("checked")) {
				selectedQuestionPositions.splice(questionId, 1);
			}
		} 
		else if (selectedPosition > 0) {
			jQuery("#<portlet:namespace />position" + selectedPosition).removeAttr("checked");
			selectedQuestionPositions[questionId] = positionId;
		} 
		else {
			selectedQuestionPositions[questionId] = positionId;
		}
	}

	/**
	 * Updates positions for a plan with selected values.
	 */
	function <portlet:namespace />updatePositions() {
		var positionsString = "";
		var positions = new Array();
		var first = true;
		
		for (var question in selectedQuestionPositions) {
			if (! first) {
				positionsString += ",";
			}
			positionsString += selectedQuestionPositions[question];
			first = false;
		}
		document.<portlet:namespace />fm.<portlet:namespace /><%= PlanConstants.POSITIONS %>.value = positionsString;
		document.<portlet:namespace />fm.submit();
	}

	/**
	 * Toggles positions visibility for given question.
	 *
	 * @param questionid question id which positions are to be shown/hidden
	 */
	function <portlet:namespace />togglePositionsVisibility(questionId) {
		jQuery("#<portlet:namespace />expandPositionsExpanded" + questionId).toggle();
		jQuery("#<portlet:namespace />expandPositionsHidden" + questionId).toggle();
		jQuery("#<portlet:namespace />positions" + questionId).toggle()
	}

	/**
	 * Ready event for this page. First it checks if window should be closed,
	 * if that's the case then it passes collection of selected positions to 
	 * opener window and closes itself.
	 *
	 * If window shouldn't be closed, it get's initialized with plan positions.
	 */ 
	jQuery(document).ready(function() {
		<!-- check if window should be closed -->
		<c:if test="<%= closeWindow %>">
			var positions = Array();
			<c:forEach var="position" items="<%= positions %>">
				var position = new Object();
				position.name = '${position.subject}';
				position.debateCategory = ${position.categoryId};
				position.id = ${position.messageId};
				positions.push(position);
			</c:forEach>
			opener.<portlet:namespace />updatePositions(positions);
			window.close();
		</c:if>

		jQuery(".portlet-plans-position").removeAttr("checked");
		<c:forEach var="position" items="<%= positions %>">
			jQuery("#<portlet:namespace />position${position.messageId}").attr("checked", true);
			selectedQuestionPositions[${position.categoryId}] = ${position.messageId};
		</c:forEach>
		
	});
	

</script>

<h2><liferay-ui:message key="select-positions" /></h2>

<!-- submit form url -->
<portlet:actionURL windowState="NORMAL" var="submitActionURL">
	<portlet:param name="struts_action" value="/ext/plans/select_positions" />
	<portlet:param name="<%= PlanConstants.UPDATE_TYPE %>" value="<%= PlanConstants.UPDATE_POSITIONS %>" />
	<portlet:param name="<%= PlanConstants.PLAN_ID %>" value="<%= String.valueOf(plan.getPlanId()) %>" />
	<portlet:param name="closeWindow" value="true" />
</portlet:actionURL>
	
<form method="post" name="<portlet:namespace />fm" action="<%= submitActionURL %>">
	<input type="hidden" name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
	<input type="hidden" name="<%= PlanConstants.UPDATE_TYPE %>" value="<%= PlanConstants.UPDATE_POSITIONS %>" />
	<input name="<portlet:namespace /><%= PlanConstants.POSITIONS %>" type="hidden" />
	<input type="hidden" name="<portlet:namespace /><%= PlanConstants.PLAN_ID %>" value="<%= plan.getPlanId() %>" />
</form>

<!-- tree of questions and their positions -->
<div class="portlet-plans-positions-tree" id="<portlet:namespace />positions">
	<c:forEach var="question" items="<%= questions %>">
		<div>
			<a href="javascript:<portlet:namespace />togglePositionsVisibility(${question.categoryId})">
				<span class="portlet-plans-positions-visibility" id="<portlet:namespace />expandPositionsExpanded${question.categoryId}">
					+
				</span>
				<span class="portlet-plans-positions-visibility" id="<portlet:namespace />expandPositionsHidden${question.categoryId}" style="display: none">
					-
				</span>
			</a>
			<h4>${question.subject}</h4>
			<div class="portlet-plans-clear"></div>
			<div class="portlet-plans-question-positions" id="<portlet:namespace />positions${question.categoryId}">
				<c:forEach var="position" items="${questionPositions[question]}">
					<span>
						<input id="<portlet:namespace />position${position.messageId}" 
							type="checkbox" 
							class="portlet-plans-position"
							name="<portlet:namespace />question${question.categoryId}" 
							onClick="<portlet:namespace />selectPosition(${question.categoryId}, ${position.messageId})"
						>
							${position.subject}
						</input>
					</span><br />
				</c:forEach>
			</div>
		</div>
	</c:forEach>
</div>
<br />
<br />
<button type="button" onClick="<portlet:namespace />updatePositions()">Update</button>
<button type="button" onClick="window.close()">Cancel</button>

