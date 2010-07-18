<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File contains css definitions for plans portlet.
 *
 * version 1.1: css definitions for selecting positions, edit actions form and plan summary
 * version 1.2: css definitions for highlighting a plan
 *
 * @author janusz.p, janusz.p
 * @version 1.2
 * @since 1.0
 */
 %>
<%@ include file="/html/portlet/css_init.jsp" %>

.portlet-plans-action-link a {
	text-decoration: none;
	font-size: 1.1em;
	font-weight: bold;
}

.portlet-plans-edit-form-label {
	color: #12558E;
}

.portlet-plans-edit-form-label-cell {
	width: 100px;
	height: 25px;
	border: 1px;
}

.portlet-plans-rich-editor {
	height: 600px;
	margin-left: 10px;
	margin-right: 10px;

}

.portlet-plans-short-editor {
	height: 300px;
	margin-left: 10px;
	margin-right: 10px;

}

.portlet-plans-info-box {
	border: 1px solid #555;
}

.portlet-plans-info-box-title {
	border-bottom: 1px solid #999;
	background-color: #ddd;
	font-weight: bold;
}

.portlet-plans-info-box-title .portlet-plans-wrapper {
	padding-left: 10px;
}

.portlet-plans-info-box-contents .portlet-plans-wrapper {
	padding: 2px;
	padding-left: 5px;
}

.portlet-plans-pick-a-model {
	color: #aaa;
}


.portlet-plans-plan-info-box {
	margin-left: 5%;
	padding: 7px;
	border: 1px solid black;
	float: left;
	height: 38px;
	width: 25%;
	margin-bottom: 15px;
	text-align: center;
}

.portlet-plans-clear {
	clear: both;
}

.portlet-plans-plan-summary-table {
	float: left;
	margin-right: 20px;
	width: 45%;
}

.portlet-plans-plan-summary-table table td {
	padding: 3px;
	border-spacing: 5px;
}

#portlet-plans-inner-portlet {
	overflow: hidden;
}

#portlet-plans-inner-portlet #content-wrapper {
	width: 100%;
}

.portlet-plans-plan-name {
	float: left;
}

.portlet-plans-back-link {
	float: right;
	width: 100%;
}

.actionsData {
	width: 100%;
}

.actionsData td {
	border: 1px solid black;
	padding: 5px;
}

.actionsData th {
	text-align: center;
}

.action-val {
	width: 20%;
}

#actions-edit {
	display: none;
}

#actions-edit.visible {
	display: block;
}


.portlet-plans-positions-tree {

}

.portlet-plans-positions-visibility {
	font-weight: bold;
	font-size: 2em;
	width: 15px;
	display: block;
	float: left;
}

.portlet-plans-positions-tree a {
	text-decoration: none;
}

.portlet-plans-question-positions {
	padding: 10px;
	margin-left: 20px;
}

.portlet-plans-plan-content-missing {
	color: #aa0000;
	font-weight: bold;
	display: block;
	margin-left: 5px;
	margin-right: 5px;
}

.portlet-plans-list-plan-name {
	font-weight: bold;
	font-size: 1.2em;
	text-align: center;
}

.portlet-plans-list-plan-creator {
	font-size: 0.8em;
	text-align: center;
}

ul.portlet-plans-columns-list li {
	list-style-type: none;
}
table.portlet-plans-columns {
	border-collapse: separate;
	border-spacing: 5px;

}
table.portlet-plans-columns td {
	text-align: left;
	vertical-align: top;
	background: #D3DADD;
	padding: 5px 10px;
}

.tabs.ui-tabs {
	padding: 0;
}

tr.highlight td, .results-row.even.highlight td, .comparsion .tableOutlay table tr.even.highlight td,
.comparsion .tableOutlay table tr.even.highlight td.first, .comparsion .tableOutlay table tr.even.highlight td.last {
	background: #FFEFBB;
	font-weight: normal;
}

.plans-portlet-filter-slider {
	position: relative;
}

.ui-slider-range {
	background: #eeaa00;
}

a.ui-dialog-titlebar-close span {
	display: none;
}

div.ui-dialog div.ui-dialog-container a.ui-dialog-titlebar-close span
	display: none;
}

.plansTable td .errors {
    display: none;
    position: relative;   
}