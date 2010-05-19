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

<%@ include file="/html/portlet/css_init.jsp" %>

.portlet-message-boards .code {
	background: #fff;
	border: 1px solid #777;
	font-family: monospace;
	overflow-x: auto;
	white-space: pre;
}

.ie6 .portlet-message-boards .code {
	width: 100%;
}

.portlet-message-boards .code-lines {
	border-right: 1px solid #ccc;
	color: #000;
	margin-right: 5px;
	padding: 0px 5px 0px 5px;
}

.portlet-message-boards .quote {
	background: #fff url(<%= themeImagesPath %>/ext/debates/message_boards/quoteleft.png) left top no-repeat;
	border: 1px solid #777;
	padding: 5px 0px 0px 5px;
}

.portlet-message-boards .quote-content {
	background: transparent url(<%= themeImagesPath %>/ext/debates/message_boards/quoteright.png) right bottom no-repeat;
	padding: 5px 30px 10px 30px;
}

.portlet-message-boards .quote-title {
	font-weight: bold;
	padding: 5px 0px 5px 0px;
}

.portlet-message-boards .title {
	border-bottom: 1px solid #ccc;
	font-size: large;
	font-weight: normal;
	padding: 5px;
}

.portlet-message-boards .message-container {
	border: 1px solid #ccc;
	margin: 5px 0 0 0;
}

.ie .portlet-message-boards .message-container {
	width: 100%;
}

.portlet-message-boards .message-container table {
	border-collapse: collapse;
	table-layout: fixed;
}

.portlet-message-boards .message-container td {
	border: none;
}

.portlet-message-boards .thread-top {
	border-bottom: 1px solid #ccc;
	padding: 3px 5px;
}

.portlet-message-boards .thread-bottom {
	padding: 3px 5px;
}

.portlet-message-boards .taglib-ratings.thumbs .total-rating {
	padding: 0 5px 0 10px;
}

td.user-info {
	border-right: 1px solid #ccc;
	width: 150px;
}

.portlet-message-boards .subject {
	float: left;
}

.portlet-message-boards .edit-controls {
	float: right;
}

.portlet-message-boards .edit-controls li {
	float: left;
	margin-right: 10px;
}

.portlet-message-boards .thread-body {
	padding: 15px;
}

.ie .portlet-message-boards .message-container .thread-body table {
	table-layout: auto;
}

.portlet-message-boards .message-container .user-info {
	border-right: 1px solid #ccc;
	padding: 5px;
}

.portlet-message-boards .clear {
	clear: both;
}

.portlet-message-boards .toggle_id_message_boards_view_message_thread {
	border: 1px solid #ccc;
	margin: 5px 0px 0px 0px;
}

.portlet-message-boards .thread-controls {
	border: 1px solid #ccc;
	margin-bottom: 5px;
	padding: 3px 5px;
}

.portlet-message-boards .thread-navigation {
	float: left;
}

.portlet-message-boards .thread-actions {
	float: right;
}

.portlet-message-boards .thread-user-rank {
	display: block;
}

.portlet-message-boards .emoticons {
	border: 1px solid #ccc;
	margin-left: 10px;
}

.portlet-message-boards .tree {
	vertical-align: middle;
}

.portlet-message-boards .message-scroll {
	margin: 5px 0px 0px 0px;
}

.portlet-message-boards .lfr-textarea.message-edit {
	height: 378px;
	min-height: 100%;
	width: 100%;
}

.portlet-message-boards .message-edit-body {
	width: 750px;
}

.portlet-message-boards .subcategories {
	text-decoration: underline;
}

.portlet-message-boards .taglib-flags {
	float: left;
	margin-left: 20px;
}

/* debates */
.portlet-debates-topic-actions {
	float: right;
}

.portlet-debates-clear {
	clear: both;
}

.portlet-debates-links a {
	text-decoration: none;
	font-size: 1.0em;
}

.portlet-debates-link {
	margin: 10px 5px 5px 5px;
}

.portlet-debates-topic {
	margin-top: 20px;
}

.portlet-debates-topic-title {
	float: left;
}

.portlet-debates-topic-header {
	background: #fafafa;
	padding: 5px 5px 5px 20px;
}

span.portlet-debates-topic-title {
	font-size: 1.6em;
	font-weight: bold;
}

.portlet-debates-debate-title {
	float: left;
}

.portlet-debates-debate-actions {
	right: 0px;
	top: 0px;
}

.ie6 .portlet-debates-debate-actions {
	right: 10px;
}

.debates-actions-container {
	background: gray;
	position: relative;
	
}