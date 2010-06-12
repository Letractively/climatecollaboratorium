<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File responsible for presentation of plan's discussion forum. Goal is achived by embedding message board
 * portlet from plans home community page.
 *
 * After each page load inside iframe it's contents is little modified in order to remove scrollbars, portlet
 * border and title bar.
 *
 * @author janusz.p
 * @version 1.0
 */
 %>
<%@ include file="/html/portlet/ext/plans/init.jsp" %>

<%
	String messageId = ParamUtil.getString(request, "messageId");
	final String PLAN_DISCUSSION_MESSAGE_URL_SUFFIX = "message/MESSAGE_ID#_19_message_MESSAGE_ID";
	final String MESSAGE_ID = "MESSAGE_ID";
    if (!messageId.trim().equals("")) {
        planDiscussionURL = planDiscussionURL.replaceAll("category.*$", PLAN_DISCUSSION_MESSAGE_URL_SUFFIX);
        planDiscussionURL = planDiscussionURL.replaceAll(MESSAGE_ID, messageId);
    }

%>

<!-- Tabs panel -->
<%@ include file="/html/portlet/ext/plans/view_plan/view_plan_tabs.jspf" %>

<!-- Embedding plan's message board. -->
<script src="/html/js/liferay/widget.js" type="text/javascript"></script>
<script type="text/javascript">

	function portletPlansModifyMessageBoardsInnerPortlet() {
		// update iframe height to wrap entire document, this prevents from displaying additional scrollbar
		var frame = document.getElementById("portlet-plans-inner-portlet");
		var frameContent =  (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document;
		jQuery(frame).attr("scrolling", "no");
		var contentDocument = jQuery(frameContent);
		
		// remove browsing categories etc from discussion
		jQuery(frameContent._19_fm1).remove();
		contentDocument.find(".portlet-borderless-bar").remove();
		contentDocument.find(".tabs.ui-tabs").remove();

		// add search box like in the prototype
		searchBoxContainer = contentDocument.find("#_19_keywords2").parent();
		searchBoxContainer.html('<div class="searchInputBox"><input class="text" type="text"/>' + 
				'<a class="searchBtn" href="javascript:;" onClick="document._19_fm2.submit()" ><span class="hidden">Search</span></a></div>' + 
				'<a class="newThread" href="javascript:;" onClick="iframe_DeferUntilLogin(function () {_19_addMessage(); });"><span class="hidden">Post New Thread</span></a><div class="clear"/>');

		contentDocument.find("th.col-1").addClass("first");
		contentDocument.find("th.col-6").addClass("last");
		contentDocument.find("th.col-7").remove();
		contentDocument.find("th.col-6").attr("colspan", "2");

		contentDocument.find(".breadcrumbs .first").remove();

		var actionTags = contentDocument.find(".lfr-trigger"); 
		actionTags.find("strong").hide();
		actionTags.append('<img align="middle" alt="action" src="/collaboratorium-theme/images/aciont_icon.png"/><div>Action</div>');
		
		
		contentDocument.find(".page-selector").remove();
		contentDocument.find(".delta-selector").remove();
		contentDocument.find(".taglib-search-iterator-page-iterator-top").remove();
		

		
		frame.height = frameContent.body.scrollHeight + 50;
		frame.css("overflow", "hidden");
		jQuery("#portlet-plans-inner-portlet").load(portletPlansModifyMessageBoardsInnerPortlet);
	}

	
	/**
	Function responsible for attaching portletPlansModifyInnerPortlet to iframe's onload event 
	*/ 
	jQuery(document).ready(function() {
		var frame = jQuery("#portlet-plans-inner-portlet").load(portletPlansModifyMessageBoardsInnerPortlet);
	});
	Liferay.Widget({ url: "<%= planDiscussionURL %>" , id: "portlet-plans-inner-portlet", scrolling: "no", FRAMEBORDER: 0});
</script>
<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf" %>



