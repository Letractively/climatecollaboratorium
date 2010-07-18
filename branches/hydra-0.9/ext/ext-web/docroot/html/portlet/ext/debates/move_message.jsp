<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Form for changing message parent. 
 * 
 * @author janusz.p
 * @version 1.0
 */
 %>
<%@ include file="/html/portlet/ext/debates/init.jsp" %>
<%
	MBMessage message = (MBMessage) request.getAttribute(DebatesConstants.MESSAGE);
	MBMessage parentMessage = (MBMessage) request.getAttribute(DebatesConstants.PARENT_MESSAGE);

	List<MBMessage> breadcrumb = (List<MBMessage>)request.getAttribute(DebatesConstants.BREADCRUMB);
	String redirect = ParamUtil.getString(request, DebatesConstants.REDIRECT);
	MBMessage debateMessage = (MBMessage)request.getAttribute(DebatesConstants.DEBATE_MESSAGE);
	List<MBMessage> parentMessages = (List<MBMessage>)request.getAttribute(DebatesConstants.PARENT_MESSAGES);
	int messageType = DebatesUtil.getMessageType(message.getMessageId());
	String messageTypeName = DebatesUtil.getMessageTypeName(message.getMessageId());
%>


<script type="text/javascript">

	/**
	 * Shows error message to the user.
	 * @param message error message that should be shown to the user
	 */
	function <portlet:namespace />showError(message) {
		jQuery("#<portlet:namespace />messages").append("<span class=\"portlet-msg-error\">" + message + "</span>");
	}

	/**
	 * Validates form.
	 * @return true if form is valid, false otherwise
	 */
	function <portlet:namespace />validate() {
		// clear all error messages
		jQuery("span.portlet-msg-error").remove();
		var valid = true;

		parentMsgId = document.<portlet:namespace />fm.<portlet:namespace /><%= DebatesConstants.PARENT_MESSAGE_ID %>.value;

		// check if user has selected message type	
		var messageTypeRadios = document.<portlet:namespace />fm.<portlet:namespace /><%= DebatesConstants.MESSAGE_TYPE %>;
		var messageTypeSelected = false;
		for (var i=0; i < messageTypeRadios.length; i++) {
			if (messageTypeRadios[i].checked) {
				messageTypeSelected = true;
			}
		}
		if (parentMsgId != <%= debateMessage.getMessageId() %> && ! messageTypeSelected) {
			<portlet:namespace />showError('<liferay-ui:message key="select-argument-type" />');
			valid = false;
		}
		return valid;
	}

	/**
	 * Function executed when user selects one of categories. 
	 * 
	 * @param parentMessage message selected by user
	 * @return false if form isn't valid
	 *
	 */
	function <portlet:namespace />chooseParentMessage(parentMessage) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= DebatesConstants.PARENT_MESSAGE_ID %>.value = parentMessage;
		if (! <portlet:namespace />validate()) {
			alert("<liferay-ui:message key="you-have-entered-invalid-data" />");
			return false;
		} 
		document.<portlet:namespace />fm.submit();
	}
</script>

<div id="<portlet:namespace />messages"></div>

<!-- back link -->
<div class="portlet-debates-links portlet-debates-back-link">
	<liferay-ui:icon image="back" url="<%= redirect %>" />
	<a href="<%= redirect %>"><liferay-ui:message key="back" /></a>
</div>
<div class="portlet-debates-clear"></div>

<!-- Page title -->
<h1><liferay-ui:message key='<%= "move-" + messageTypeName %>' /></h1>
<h3><liferay-ui:message key='<%= messageTypeName + "-title" %>' />: <i><%= message.getSubject() %></i></h3>
<br />
	
	
<portlet:actionURL var="submitURL">
	<portlet:param name="struts_action" value="/ext/debates/move_message" />
	<portlet:param name="<%=DebatesConstants.BASE_CATEGORY_ID%>" value="<%=ParamUtil.getString(request, DebatesConstants.BASE_CATEGORY_ID) %>" />
</portlet:actionURL>
<form name="<portlet:namespace />fm" enctype="multipart/form-data" action="<%= submitURL %>" method="post" />
	<input type="hidden" name="<portlet:namespace /><%= DebatesConstants.PARENT_MESSAGE_ID %>" />
	<input type="hidden" name="<%= DebatesConstants.REDIRECT %>" value="<%= HtmlUtil.escape(redirect) %>" />
	<input type="hidden" name="<%= DebatesConstants.MESSAGE_ID %>" value="<%= String.valueOf(message.getMessageId()) %>" />

	<c:if test="<%= messageType != DebatesConstants.POSITION_MSG_TYPE %>">
		<!-- Make message a position - move it to the top level -->
		<div class="portlet-debates-move-message-option position">
			<table width="100%">
				<tr>
					<td>
						<h3><liferay-ui:message key='<%= "make-" + messageTypeName + "-a-position" %>' />:</h3>
					</td>
					<td align="right">
						<input 
							type="button" 
							value="<liferay-ui:message key="choose" />" 
							onclick="<portlet:namespace />chooseParentMessage(<%= debateMessage.getMessageId() %>)" 
						/>
					</td>
				</tr>
			</table>
		</div>

		<br /><br />
	</c:if>
	<!-- Chose parent message for moved message (make it an argument) -->
	<div class="portlet-debates-move-message-option argument">
	<h3><liferay-ui:message key='<%= "make-" + messageTypeName + "-an-argument" %>' />:</h3>
	<liferay-ui:message key="debate-tree" />: 
	<c:choose>
		<c:when test="<%= breadcrumb != null %>">
			<c:forEach var="parent" items="<%= breadcrumb %>">
				<liferay-portlet:renderURL var="parentURL">
					<portlet:param name="struts_action" value="/ext/debates/move_message" />
					<portlet:param name="<%= DebatesConstants.MESSAGE_ID %>" value="<%= String.valueOf(message.getMessageId()) %>" />
					<portlet:param name="<%= DebatesConstants.PARENT_MESSAGE_ID %>" value="${parent.messageId}" />
					<portlet:param name="<%= DebatesConstants.REDIRECT %>" value="<%= redirect %>" />
				</liferay-portlet:renderURL>
				<a href="<%= parentURL %>">${parent.subject}</a> &raquo;
			</c:forEach>
			<%= parentMessage.getSubject() %>
		</c:when>
	</c:choose>
	<br />
	<br />

	<table width="100%">
		<tr>
			<td width=170px">
				<h4><liferay-ui:message key="choose-argument-type" />:</h4>
			</td>
			<td>
				<input type="radio" name="<portlet:namespace /><%= DebatesConstants.MESSAGE_TYPE %>" value="<%= DebatesConstants.ARGUMENT_PRO_MSG_TYPE %>">
					<liferay-ui:message key="argument-pro" />
				</input>
				<input type="radio" name="<portlet:namespace /><%= DebatesConstants.MESSAGE_TYPE %>" value="<%= DebatesConstants.ARGUMENT_CON_MSG_TYPE %>">
					<liferay-ui:message key="argument-con" />
				</input>
			</td>
		</tr>
		<tr><td colspan="2"><br /></td></tr>
		<tr>
			<td>
				<h4><liferay-ui:message key="choose-parent-message" />:</h4>
			</td>
			<td>
				<liferay-ui:search-container delta="20" emptyResultsMessage="there-are-no-child-messages">
					<liferay-ui:search-container-results total="<%= parentMessages.size() %>" results="<%= parentMessages %>" />
					<liferay-ui:search-container-row
						className="com.liferay.portlet.messageboards.model.MBMessage"
						keyProperty="messageId"
						modelVar="msg"
					>
						<liferay-portlet:renderURL var="msgURL">
							<portlet:param name="struts_action" value="/ext/debates/move_message" />
							<portlet:param name="<%= DebatesConstants.MESSAGE_ID %>" value="<%= String.valueOf(message.getMessageId()) %>" />
							<portlet:param name="<%= DebatesConstants.PARENT_MESSAGE_ID %>" value="<%= String.valueOf(msg.getMessageId()) %>" />
							<portlet:param name="<%= DebatesConstants.REDIRECT %>" value="<%= redirect %>" />
						</liferay-portlet:renderURL>
						<liferay-ui:search-container-column-text
							name="message"
							property="subject"
							href="<%= msgURL %>"
						/>
						<liferay-ui:search-container-column-text
							name="type"
							value="<%= LanguageUtil.get(pageContext, DebatesUtil.getMessageTypeName(msg.getMessageId())) %>"
							href="<%= msgURL %>"
						/>
						<liferay-ui:search-container-column-button
							name="Choose"
							align="right"
							href='<%= renderResponse.getNamespace() + "chooseParentMessage(" + msg.getMessageId() + ")" %>'
						/>
					</liferay-ui:search-container-row>
					<liferay-ui:search-iterator/>
				</liferay-ui:search-container>
			</td>
		</tr>
	</table></div>
</form>