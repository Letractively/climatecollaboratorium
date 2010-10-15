<%@ include file="/html/portlet/ext/migration/init.jsp"%>
<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
	List<QuestionEntity> questions = (List<QuestionEntity>) request.getAttribute("questions");
	String migrationId = ParamUtil.getString(request, "migrationId");
%>

<script type="text/javascript">
	function <portlet:namespace />selectCategory(categoryId, questionId) {
		jQuery("#" + questionId + "parentCategoryId").val(categoryId);
	}

</script>

<portlet:actionURL var="actionURL">
	<portlet:param name="struts_action" value="/ext/migration/debates" />
	<portlet:param name="migrationId" value="<%= migrationId %>" />
</portlet:actionURL>

<form name="<portlet:namespace />fm" method="POST" action="${ actionURL }">
<table class="taglib-search-iterator">
	<thead>
		<tr class="portlet-section-header results-header">
			<th>Debate question</th>
			<th>Should be migrated</th>
			<th>Migrate as</th>
			<th>Parent category</th>
			<th>Already migrated</th>
		</tr>
	</thead>
	
	<c:forEach var="question" items="<%= questions %>" varStatus="status">
	    <jsp:useBean id="status" type="javax.servlet.jsp.jstl.core.LoopTagStatus" />
	    <jsp:useBean id="question" type="com.ext.portlet.migration.entities.QuestionEntity" />
	    <% boolean hasMapping = MigrationHelper.hasMapping(question.NAME, question.getId()); %>
		<tr class="portlet-section-body results-row <%= status.getCount()%2 == 0 ? "alt" : "" %>">
			<td>${question.information}</td>
			<td>
				<c:choose>
					<c:when test="<%= ! hasMapping %>">
						<select name="${ question.id }migrate">
							<option value="true">Yes</option>
							<option value="false">No</option>
						</select>
					</c:when>
					<c:otherwise>
						<select name="${ question.id}update">
							<option value="true">Update</option>
							<option value="false">Don't update</option>
						</select>
					</c:otherwise>
				</c:choose>
				
			</td>
			<td>
				<c:if test="<%= ! hasMapping %>">
					<select name="${ question.id }migrationType">
						<option value="debate">Debate</option>
						<option value="discussion">Discussion</option>
						<option value="thread">Simple thread</option>
					</select>
				</c:if>
			</td>
			<td width="110px">
				<c:if test="<%= ! hasMapping %>">
					<input name="${ question.id }parentCategoryId" id="${ status.count }parentCategoryId" type="text" style="width: 40px" value="11701" />
					<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>" var="selectCategoryURL">
						<portlet:param name="struts_action" value="/ext/migration/select_category" />
						<portlet:param name="questionId" value="${ status.count }" />
					</portlet:renderURL>
					
					<input type="button" value="Select" 
							onClick="var categoryWindow = window.open('${ selectCategoryURL }', 'category', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=650'); void(''); categoryWindow.focus();" />
				</c:if>
			</td>
			<td>
				<c:choose>
					<c:when test="<%= hasMapping %>">
						YES
					</c:when>
					<c:otherwise>
						NO
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	<tr><td colspan="5" align="center"><input type="submit" value="Migrate" /></td></tr>
</table>


</form>