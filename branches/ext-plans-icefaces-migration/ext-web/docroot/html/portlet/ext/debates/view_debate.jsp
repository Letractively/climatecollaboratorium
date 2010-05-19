<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 * Debate summary page.
 *
 * version 1.1: themed to be consistent with collaboratorium theme
 *
 * @author janusz.p
 * @version 1.1
 */
 %>
<%@ include file="/html/portlet/ext/debates/init.jsp" %>
<%@ include file="/html/portlet/ext/debates/tabs.jspf" %>
	<div class="page-context-help-container debate-summary">
		     <a href="/web/guest/resources/-/wiki/Main/How+are+debates+edited">
		     How are debates edited?
		     </a>
		</div>

<%@ include file="/html/portlet/ext/debates/issue_add_comment.jspf"%>
 <div class="tabContent_bottom">
                        <div id="discussionTabContent" class="tabContent <%= selectedTab %>">

<%
	MBCategory mainCategory = (MBCategory)request.getAttribute(DebatesConstants.MAIN_CATEGORY);
	MBCategory debateCategory = (MBCategory)request.getAttribute(DebatesConstants.DEBATE_CATEGORY);
	MBCategory discussionCategory = (MBCategory)request.getAttribute(DebatesConstants.DISCUSSION_CATEGORY);
	MBMessage debateMessage = (MBMessage)request.getAttribute(DebatesConstants.DEBATE_MESSAGE);
	List<MBMessageDisplay> positions = (List<MBMessageDisplay>)request.getAttribute(DebatesConstants.POSITIONS);
	
	boolean showAddPositionButton = permissionChecker.hasPermission (themeDisplay.getPortletGroupId(),portletDisplay.getRootPortletId(),portletDisplay.getResourcePK(),DebatesConstants.ADD_DEBATE_POSITION);
			
	boolean showSubscription = permissionChecker.hasPermission (themeDisplay.getPortletGroupId(),portletDisplay.getRootPortletId(),portletDisplay.getResourcePK(),DebatesConstants.SUBSCRIBE);
	
%>

<a id="<portlet:namespace />message_0"></a>
<liferay-portlet:renderURL var="addPositionURL">
	<portlet:param name="struts_action" value="/ext/debates/edit_message" />
	<portlet:param name="<%= DebatesConstants.REDIRECT %>" value="<%= currentURL %>" />
	<portlet:param name="<%= DebatesConstants.PARENT_MESSAGE_ID %>" value="<%= Long.toString(debateMessage.getMessageId()) %>" />
	<portlet:param name="<%= DebatesConstants.MESSAGE_TYPE_NAME %>" value="<%= DebatesConstants.POSITION_MSG_TYPE_NAME %>" />
	<portlet:param name="<%= DebatesConstants.CATEGORY_ID %>" value="<%= String.valueOf(debateMessage.getCategoryId()) %>" />
	<portlet:param name="<%= DebatesConstants.BASE_CATEGORY_ID %>" value="<%=ParamUtil.getString(request, DebatesConstants.BASE_CATEGORY_ID) %>" />
	<portlet:param name="threadId" value="<%= String.valueOf(debateMessage.getThreadId()) %>" />
</liferay-portlet:renderURL>


	<liferay-portlet:actionURL var="subscribeIssueURL">
		<portlet:param name="struts_action" value="/ext/debates/edit_issue" />
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>" />
		<portlet:param name="<%= DebatesConstants.TOPIC_ID %>" value="<%= String.valueOf(mainCategory.getParentCategoryId()) %>" />
		<portlet:param name="categoryId" value="<%= String.valueOf(mainCategory.getCategoryId()) %>" />
	</liferay-portlet:actionURL>

<script type="text/javascript">
	function <portlet:namespace />deleteMessage(url) {
 	 	if (confirm("Are you sure you want to delete selected message?")) {
 			submitForm(document.hrefFm, url);
 		} else {
 			self.focus();
 		}
 	}
 	
 	function <portlet:namespace/>subscribe() {
 	     var base = "<%=subscribeIssueURL%>";
 	     base+="&<portlet:namespace/><%=DebatesConstants.REDIRECT%>="+escape(document.location.href);
 	     submitForm(document.hrefFm,base);
 	
 	}

</script>
	
<!-- Page title -->

<c:if test="<%=showSubscription || showAddPositionButton%>">
	<div class="debates-actions-container">
		<div class="portlet-debates-debate-actions portlet-debates-links">
			<c:if test="<%= showAddPositionButton %>">
			<a href="${addPositionURL}">
				<span class="portlet-debates-link">
					add position
				</span>
			</a>
				&nbsp;|&nbsp;
			</c:if>
			<c:if test="<%= showSubscription %>">
				<a href='javascript:<portlet:namespace/>subscribe()'>subscribe</a>
			</c:if>
		</div>
	</div>
</c:if>


<div class="clear"></div>
<div class="modelDiscussionSummary_outlay">
	

	<!-- Show navigation panel -->
	<div class="floatLeft totalBox">
		<%@ include file="/html/portlet/ext/debates/view_debate_summary_navigation.jspf" %>
	</div>
	<div class="floatRight listBox">
	<%
		for (MBMessageDisplay messageDisplay: positions) {
		    MBMessage message = messageDisplay.getMessage();
	
			MBCategory category = messageDisplay.getCategory();
	
			MBThread thread = messageDisplay.getThread();
			MBTreeWalker treeWalker = messageDisplay.getTreeWalker();
	
			List<MBMessage> messages = null;
	
			if (treeWalker != null) {
				messages = new ArrayList<MBMessage>();
	
				messages.addAll(treeWalker.getMessages());
	
				messages = ListUtil.sort(messages, new MessageCreateDateComparator(true));
			}
	
			TagsUtil.addLayoutTagsEntries(request, TagsEntryLocalServiceUtil.getEntries(MBMessage.class.getName(), thread.getRootMessageId(), true));
		
		
			request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER, treeWalker);
			request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_SEL_MESSAGE, message);
			request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CUR_MESSAGE, message);
			request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CATEGORY, category);
			request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD, thread);
			request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_LAST_NODE, Boolean.valueOf(false));
			request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_DEPTH, new Integer(1));
		%>

			<liferay-util:include page="/html/portlet/ext/debates/view_debate_summary_tree.jsp" />
		<%
		}
		%>
	</div>
	<div class="clear"></div>
</div>
<%@ include file="/html/portlet/ext/debates/bottom.jspf" %>
