<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/ext/debates/message_boards/init.jsp" %>

<%@ include file="/html/portlet/ext/debates/tabs.jspf" %>

<%@ include file="/html/portlet/ext/debates/message_boards/issue_plain.jspf"%>

 <div class="tabContent_bottom">
                        <div id="discussionTabContent" class="tabContent <%= selectedTab %>">


<%@page import="com.ext.portlet.debates.DebatesConstants"%>


<%


String redirect = ParamUtil.getString(request, "redirect");

MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);

long categoryId = BeanParamUtil.getLong(category, request, "categoryId", MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID);

MBCategoryDisplay categoryDisplay = new MBCategoryDisplayImpl(scopeGroupId, categoryId);

Set<Long> categorySubscriptionClassPKs = null;
Set<Long> threadSubscriptionClassPKs = null;

if (themeDisplay.isSignedIn()) {
	List<Subscription> categorySubscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), MBCategory.class.getName());

	categorySubscriptionClassPKs = new HashSet<Long>(categorySubscriptions.size());

	for (Subscription subscription : categorySubscriptions) {
		categorySubscriptionClassPKs.add(subscription.getClassPK());
	}

	threadSubscriptionClassPKs = new HashSet<Long>();

	List<Subscription> threadSubscriptions = SubscriptionLocalServiceUtil.getUserSubscriptions(user.getUserId(), MBThread.class.getName());

	threadSubscriptionClassPKs = new HashSet<Long>(threadSubscriptions.size());

	for (Subscription subscription : threadSubscriptions) {
		threadSubscriptionClassPKs.add(subscription.getClassPK());
	}
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("struts_action", "/ext/debates/view");
portletURL.setParameter(DebatesConstants.VIEW_DEBATES_INDEX_TABS, debatesIndexTab);
portletURL.setParameter("categoryId", String.valueOf(categoryId));
%>

<liferay-portlet:renderURL varImpl="searchURL">
	<portlet:param name="struts_action" value="/ext/debates/search" />
	<portlet:param name="<%= DebatesConstants.VIEW_DEBATES_INDEX_TABS %>" value="<%= debatesIndexTab %>" />
</liferay-portlet:renderURL>
		<c:if test="<%= category != null %>">
			<br />

			<form action="<%= searchURL %>" method="get" name="<portlet:namespace />fm2" onSubmit="submitForm(this); return false;">
			<liferay-portlet:renderURLParams varImpl="searchURL" />
			<input name="<portlet:namespace />redirect" type="hidden" value="<%= currentURL %>" />
			<input name="<portlet:namespace />breadcrumbsCategoryId" type="hidden" value="<%= categoryId %>" />
			<input name="<portlet:namespace />searchCategoryId" type="hidden" value="<%= categoryId %>" />

			<%
			List<String> headerNames = new ArrayList();

			headerNames.add("thread");
			headerNames.add("status");
			headerNames.add("started-by");
			headerNames.add("posts");
			headerNames.add("views");
			headerNames.add("last-post");
			headerNames.add(StringPool.BLANK);

			SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, "cur2", SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);

			int total = MBThreadLocalServiceUtil.getThreadsCount(categoryId);

			searchContainer.setTotal(total);

			List results = MBThreadLocalServiceUtil.getThreads(categoryId, searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);

			List<ResultRow> resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				MBThread thread = (MBThread)results.get(i);

				MBMessage message = MBMessageLocalServiceUtil.getMessage(thread.getRootMessageId());

				message = message.toEscapedModel();

				boolean readThread = MBMessageFlagLocalServiceUtil.hasReadFlag(themeDisplay.getUserId(), thread);

				ResultRow row = new ResultRow(new Object[] {message, threadSubscriptionClassPKs}, thread.getThreadId(), i, !readThread);

				row.setRestricted(!MBMessagePermission.contains(permissionChecker, message, ActionKeys.VIEW));

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("struts_action", "/ext/debates/view_message");
				rowURL.setParameter("messageId", String.valueOf(message.getMessageId()));
				rowURL.setParameter(DebatesConstants.VIEW_DEBATES_INDEX_TABS, debatesIndexTab);

				// Thread

				StringBuilder sb = new StringBuilder();

				String[] threadPriority = MBUtil.getThreadPriority(preferences, themeDisplay.getLanguageId(), thread.getPriority(), themeDisplay);

				if ((threadPriority != null) && (thread.getPriority() > 0)) {
					sb.append("<img align=\"left\" alt=\"");
					sb.append(threadPriority[0]);
					sb.append("\" border=\"0\" src=\"");
					sb.append(threadPriority[1]);
					sb.append("\" title=\"");
					sb.append(threadPriority[0]);
					sb.append("\" />");
				}

				sb.append(message.getSubject());

				row.addText(sb.toString(), rowURL);

				// Status

				sb = new StringBuilder();

				if (MBMessageFlagLocalServiceUtil.hasQuestionFlag(message.getMessageId())) {
					sb.append(LanguageUtil.get(pageContext, "waiting-for-an-answer"));
				}
				if (MBMessageFlagLocalServiceUtil.hasAnswerFlag(message.getMessageId())) {
					sb.append(LanguageUtil.get(pageContext, "resolved"));
				}

				row.addText(sb.toString(), rowURL);

				// Started by

				if (message.isAnonymous()) {
					row.addText(LanguageUtil.get(pageContext, "anonymous"), rowURL);
				}
				else {
					row.addText(PortalUtil.getUserName(message.getUserId(), message.getUserName()), rowURL);
				}

				// Number of posts

				row.addText(String.valueOf(thread.getMessageCount()), rowURL);

				// Number of views

				row.addText(String.valueOf(thread.getViewCount()), rowURL);

				// Last post

				if (thread.getLastPostDate() == null) {
					row.addText(LanguageUtil.get(pageContext, "none"), rowURL);
				}
				else {
					sb = new StringBuilder();

					sb.append(LanguageUtil.get(pageContext, "date"));
					sb.append(": ");
					sb.append(dateFormatDateTime.format(thread.getLastPostDate()));

					String lastPostByUserName = PortalUtil.getUserName(thread.getLastPostByUserId(), StringPool.BLANK);

					if (Validator.isNotNull(lastPostByUserName)) {
						sb.append("<br />");
						sb.append(LanguageUtil.get(pageContext, "by"));
						sb.append(": ");
						sb.append(lastPostByUserName);
					}

					row.addText(sb.toString(), rowURL);
				}

				// Action

				row.addJSP("right", SearchEntry.DEFAULT_VALIGN, "/html/portlet/ext/debates/message_boards/message_action.jsp");

				// Add result row

				resultRows.add(row);
			}

			boolean showAddMessageButton = MBCategoryPermission.contains(permissionChecker, category, ActionKeys.ADD_MESSAGE);

			if (showAddMessageButton && !themeDisplay.isSignedIn()) {
				if (!allowAnonymousPosting) {
					showAddMessageButton = false;
				}
			}

			showSearchThread = showSearchThread && (results.size() > 0);
			%>

			<c:if test="<%= showAddMessageButton || showSearchThread %>">
				<div>
					<c:if test="<%= showSearchThread %>">
						<label for="<portlet:namespace />keywords2"><liferay-ui:message key="search" /></label>

						<input id="<portlet:namespace />keywords2" name="<portlet:namespace />keywords" size="30" type="text" />

						<input type="submit" value="<liferay-ui:message key="search" />" />
					</c:if>

					<c:if test="<%= showAddMessageButton %>">
						<input type="button" value="<liferay-ui:message key="post-new-thread" />" onClick="<portlet:namespace />addMessage();" />
					</c:if>
				</div>

				<br />
			</c:if>

			<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />

			</form>

			<script type="text/javascript">
				function <portlet:namespace />addMessage() {
					<portlet:renderURL var="postThreadURL">
						<portlet:param name="struts_action" value="/ext/debates/edit_message" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="categoryId" value="<%= String.valueOf(categoryId) %>" />
						<portlet:param name="<%= DebatesConstants.VIEW_DEBATES_INDEX_TABS %>" value="<%= debatesIndexTab %>" />
					</portlet:renderURL>
					var url = '<%= postThreadURL %>';

					if (document.<portlet:namespace />fm2.<portlet:namespace />keywords) {
						url += '&<portlet:namespace />subject=' + document.<portlet:namespace />fm2.<portlet:namespace />keywords.value;
					}

					submitForm(document.hrefFm, url);
				}

				<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) && !themeDisplay.isFacebook() %>">
					Liferay.Util.focusFormField(document.<portlet:namespace />fm2.<portlet:namespace />keywords);
					Liferay.Util.focusFormField(document.<portlet:namespace />fm1.<portlet:namespace />keywords);
				</c:if>
			</script>

			<%
			PortalUtil.setPageSubtitle(category.getName(), request);
			PortalUtil.setPageDescription(category.getDescription(), request);
			%>

		</c:if>