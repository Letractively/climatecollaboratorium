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
<%
    themeDisplay.setIncludeServiceJs(true);

    MBMessageDisplay messageDisplay = (MBMessageDisplay) request.getAttribute(WebKeys.MESSAGE_BOARDS_MESSAGE);

    MBMessage message = messageDisplay.getMessage();
    MBCategory category = messageDisplay.getCategory();

    MBThread thread = messageDisplay.getThread();

    MBThread previousThread = messageDisplay.getPreviousThread();
    MBThread nextThread = messageDisplay.getNextThread();

    PortalPreferences portalPrefs = PortletPreferencesFactoryUtil.getPortalPreferences(request);

    String threadView = messageDisplay.getThreadView();

    MBMessage debate_message = null;
    String debate_message_type = null;

    try {
        if (DebatesUtil.getMessageType(message.getThread().getRootMessageId()) > -1) {
            debate_message_type = DebatesUtil.getMessageTypeName(message.getThread().getRootMessageId());
            debate_message = MBMessageLocalServiceUtil.getMessage(message.getThread().getRootMessageId());

        }

    } catch (Exception e) {
        e.printStackTrace();
        //nothing to worry about
    }
    boolean canComment = debate_message!=null && permissionChecker.hasPermission(themeDisplay.getPortletGroupId(), portletDisplay.getRootPortletId(),portletDisplay.getResourcePK(), DebatesConstants.COMMENT_ON_DEBATE);
    

%>


<%@ include file="/html/portlet/ext/debates/tabs.jspf" %>

<liferay-portlet:renderURL var="debateSummaryURL">
    <portlet:param name="struts_action" value="/ext/debates/view_debate"/>
    <portlet:param name="<%= DebatesConstants.BASE_CATEGORY_ID %>" value="<%= String.valueOf(baseCategoryId) %>"/>
</liferay-portlet:renderURL>

<c:if test="<%=debate_message!=null%>">
<portlet:renderURL var="replyURL">
    <portlet:param name="struts_action" value="/ext/debates/edit_message"/>
    <portlet:param name="redirect" value="<%= currentURL%>"/>
    <portlet:param name="categoryId" value="<%= String.valueOf(debate_message.getCategoryId())%>"/>
    <portlet:param name="threadId" value="<%= String.valueOf(debate_message.getThreadId())%>"/>
    <portlet:param name="parentMessageId" value="<%= String.valueOf(debate_message.getMessageId())%>"/>
    <portlet:param name="<%= DebatesConstants.VIEW_DEBATES_INDEX_TABS%>" value="<%= debatesIndexTab%>"/>
</portlet:renderURL>
    <div class="debate-back-link">
        <a href="<%=debateSummaryURL%>"><-- Back to debate summary</a>
    </div>
</c:if>

<%@ include file="/html/portlet/ext/debates/message_boards/issue_plain.jspf" %>




<script type="text/javascript">
    function <portlet:namespace />addAnswerFlag(messageId) {
        Liferay.Service.MB.MBMessageFlag.addAnswerFlag(
        {
            messageId: messageId
        }
                );

        var addAnswerFlagDiv = jQuery('#<portlet:namespace />addAnswerFlagDiv').clone();

        var html = addAnswerFlagDiv.html();

        html = '<div class="answer" id="<portlet:namespace />deleteAnswerFlag_' + messageId + '">' + html + '</div>';
        html = html.replace(/@MESSAGE_ID@/g, messageId);

        jQuery('#<portlet:namespace />message_' + messageId).find('div.tags:first').html(html);

        jQuery('#<portlet:namespace />addAnswerFlag_' + messageId).remove();
    }

    function <portlet:namespace />deleteAnswerFlag(messageId) {
        Liferay.Service.MB.MBMessageFlag.deleteAnswerFlag(
        {
            messageId: messageId
        }
                );

        var deleteAnswerFlagDiv = jQuery('#<portlet:namespace />deleteAnswerFlagDiv').clone();

        var html = deleteAnswerFlagDiv.html();

        html = '<li id="<portlet:namespace />addAnswerFlag_' + messageId + '">' + html + '</li>';
        html = html.replace(/@MESSAGE_ID@/g, messageId);

        jQuery('#<portlet:namespace />message_' + messageId).find('ul.edit-controls:first').prepend(html);

        jQuery('#<portlet:namespace />deleteAnswerFlag_' + messageId).remove();
    }

    <c:if test="<%= thread.getRootMessageId() != message.getMessageId() %>">
    jQuery(
            function() {
                document.getElementById("<portlet:namespace />message_" + <%= message.getMessageId() %>).scrollIntoView(true);
            }
            );
    </c:if>
</script>


<c:if test="<%=debate_message!=null && debate_message_type != null%>">
<div class="headerDebatePost">
    <div class="modelDiscussionSummary_outlay">
        <div class="listBox">
            <%@ include file="/html/portlet/ext/debates/message_boards/view_thread_debate_message.jspf" %>
        </div>
    </div>
</div>
</c:if>
<div class="comments-titlebox">
    <div class="floatLeft">
    <h3>Comments</h3>
        </div>

<div class="floatRight">
    <c:if test="<%=canComment%>">
        <span class="add-comment comment-function"><a href="${replyURL}">Add comment</a></span>
    </c:if>
    <c:if test="<%= PropsValues.MESSAGE_BOARDS_THREAD_VIEWS.length > 1 %>">

             <span class="comment-function">
                 Thread&nbsp;View:
        <c:if test="<%= ArrayUtil.contains(PropsValues.MESSAGE_BOARDS_THREAD_VIEWS, MBThreadImpl.THREAD_VIEW_COMBINATION) %>">


            <%
                currentURLObj.setParameter("threadView", MBThreadImpl.THREAD_VIEW_COMBINATION);
            %>

            <liferay-ui:icon
                    image="../message_boards/thread_view_combination"
                    message="combination-view"
                    url="<%= currentURLObj.toString() %>"
                    method="get"
                    />

        </c:if>

        <c:if test="<%= ArrayUtil.contains(PropsValues.MESSAGE_BOARDS_THREAD_VIEWS, MBThreadImpl.THREAD_VIEW_FLAT) %>">


            <%
                currentURLObj.setParameter("threadView", MBThreadImpl.THREAD_VIEW_FLAT);
            %>

            <liferay-ui:icon
                    image="../message_boards/thread_view_flat"
                    message="flat-view"
                    url="<%= currentURLObj.toString() %>"
                    method="get"
                    />

        </c:if>

        <c:if test="<%= ArrayUtil.contains(PropsValues.MESSAGE_BOARDS_THREAD_VIEWS, MBThreadImpl.THREAD_VIEW_TREE) %>">


            <%
                currentURLObj.setParameter("threadView", MBThreadImpl.THREAD_VIEW_TREE);
            %>

            <liferay-ui:icon
                    image="../message_boards/thread_view_tree"
                    message="tree-view"
                    url="<%= currentURLObj.toString() %>"
                    method="get"
                    />

        </c:if>
            </span>
    </c:if>
    <c:if test="<%= MBMessagePermission.contains(permissionChecker, message, ActionKeys.SUBSCRIBE) %>">
        <span class="comment-function">
        <c:choose>
            <c:when test="<%= SubscriptionLocalServiceUtil.isSubscribed(user.getCompanyId(), user.getUserId(), MBThread.class.getName(), message.getThreadId()) %>">
                <portlet:actionURL var="unsubscribeURL">
                    <portlet:param name="struts_action" value="/ext/debates/edit_message"/>
                    <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UNSUBSCRIBE %>"/>
                    <portlet:param name="redirect" value="<%= currentURL %>"/>
                    <portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>"/>
                </portlet:actionURL>

                <liferay-ui:icon image="unsubscribe" url="<%= unsubscribeURL %>" label="<%= true %>"/>
            </c:when>
            <c:otherwise>
                <portlet:actionURL var="subscribeURL">
                    <portlet:param name="struts_action" value="/ext/debates/edit_message"/>
                    <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.SUBSCRIBE %>"/>
                    <portlet:param name="redirect" value="<%= currentURL %>"/>
                    <portlet:param name="messageId" value="<%= String.valueOf(message.getMessageId()) %>"/>
                </portlet:actionURL>

                <liferay-ui:icon image="subscribe" url="<%= subscribeURL %>" label="<%= true %>"/>
            </c:otherwise>
        </c:choose>
      </span>
    </c:if>

</div>
    <div class="clear"></div>
</div>
<div class="tabContent_bottom">
<div id="discussionTabContent" class="tabContent <%= selectedTab %>">
<div id="<portlet:namespace />addAnswerFlagDiv" style="display: none;">
    <liferay-ui:icon
            image="checked"
            message="answer"
            label="<%= true %>"
            />

    (<a href="javascript: <portlet:namespace />deleteAnswerFlag('@MESSAGE_ID@');"><liferay-ui:message key="unmark"/></a>)
</div>

<div id="<portlet:namespace />deleteAnswerFlagDiv" style="display: none;">

    <%
        String taglibMarkAsAnAnswerURL = "javascript: " + renderResponse.getNamespace() + "addAnswerFlag('@MESSAGE_ID@');";
    %>

    <liferay-ui:icon
            image="checked"
            message="mark-as-an-answer"
            url="<%= taglibMarkAsAnAnswerURL %>"
            label="<%= true %>"
            />
</div>

<c:if test="<%= includeFormTag %>">
<form>
    <input name="<portlet:namespace />breadcrumbsCategoryId" type="hidden" value="<%= category.getCategoryId() %>"/>
    <input name="<portlet:namespace />breadcrumbsMessageId" type="hidden" value="<%= message.getMessageId() %>"/>
    <input name="<portlet:namespace />threadId" type="hidden" value="<%= message.getThreadId() %>"/>
    </c:if>


    <div class="clear"></div>


    <div>

        <%
            MBTreeWalker treeWalker = messageDisplay.getTreeWalker();

            List<MBMessage> messages = null;

            if (treeWalker != null) {
                messages = new ArrayList<MBMessage>();

                messages.addAll(treeWalker.getMessages());

                messages = ListUtil.sort(messages, new MessageCreateDateComparator(true));
            }

            TagsUtil.addLayoutTagsEntries(request, TagsEntryLocalServiceUtil.getEntries(MBMessage.class.getName(), thread.getRootMessageId(), true));
        %>

        <div class="message-scroll" id="<portlet:namespace />message_0"></div>

        <c:if test='<%= threadView.equals(MBThreadImpl.THREAD_VIEW_COMBINATION) && (messages.size() > 1) %>'>
            <liferay-ui:toggle-area id="toggle_id_message_boards_view_message_thread">
                <table class="toggle_id_message_boards_view_message_thread">

                    <%
                        request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER, treeWalker);
                        request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_SEL_MESSAGE, message);
                        request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CUR_MESSAGE, treeWalker.getRoot());
                        request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CATEGORY, category);
                        request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD, thread);
                        request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_LAST_NODE, Boolean.valueOf(false));
                        request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_DEPTH, new Integer(0));
                    %>

                    <liferay-util:include page="/html/portlet/ext/debates/message_boards/view_thread_shortcut.jsp"/>

                </table>
            </liferay-ui:toggle-area>
        </c:if>

        <c:choose>
            <c:when test='<%= threadView.equals(MBThreadImpl.THREAD_VIEW_TREE) %>'>

                <%
                    request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER, treeWalker);
                    request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_SEL_MESSAGE, message);
                    request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CUR_MESSAGE, treeWalker.getRoot());
                    request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_CATEGORY, category);
                    request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_THREAD, thread);
                    request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_LAST_NODE, Boolean.valueOf(false));
                    request.setAttribute(WebKeys.MESSAGE_BOARDS_TREE_WALKER_DEPTH, new Integer(0));
                %>

                <liferay-util:include page="/html/portlet/ext/debates/message_boards/view_thread_tree.jsp"/>
            </c:when>
            <c:otherwise>
                <%@ include file="/html/portlet/ext/debates/message_boards/view_thread_flat.jspf" %>
            </c:otherwise>
        </c:choose>
    </div>

    <c:if test="<%= includeFormTag %>">
</form>
</c:if>

<%
    MBMessageFlagLocalServiceUtil.addReadFlags(themeDisplay.getUserId(), thread);

    message = messageDisplay.getMessage();

    PortalUtil.setPageSubtitle(message.getSubject(), request);
    PortalUtil.setPageDescription(message.getSubject(), request);

    List<TagsEntry> tagsEntries = TagsEntryLocalServiceUtil.getEntries(MBMessage.class.getName(), message.getMessageId(), true);

    PortalUtil.setPageKeywords(ListUtil.toString(tagsEntries, "name"), request);
%>

 </div></div> </div></div>  </div></div></div> 