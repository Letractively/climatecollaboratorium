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
<%
/**
 *
 * Configuration form for Debates portlet. It allows user to select category that should be used as a parent 
 * category for Debates portlet.
 *
 * File based on message_boards/select_category.jsp
 *
 * @author janusz.p
 * @version 1.0
 */
 %>
<%@ include file="/html/portlet/ext/debates/init.jsp" %>

<%
	MBCategory category = (MBCategory)request.getAttribute(WebKeys.MESSAGE_BOARDS_CATEGORY);
	long categoryId = MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID;
	MBCategoryDisplay categoryDisplay = new MBCategoryDisplayImpl(scopeGroupId, MBCategoryImpl.DEFAULT_PARENT_CATEGORY_ID);

%>

<script type="text/javascript">

	function <portlet:namespace />selectCategory(categoryId) {
		document.<portlet:namespace />fm.<portlet:namespace /><%=DebatesConstants.CATEGORY_ID %>.value = categoryId;
		document.<portlet:namespace />fm.submit();
	}

	function <portlet:namespace />addCategory() {
		var url = '<portlet:renderURL><portlet:param name="struts_action" value="/ext/debates/edit_category" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="parentCategoryId" value="<%= String.valueOf(categoryId) %>" /></portlet:renderURL>';
	
		if (document.<portlet:namespace />fm1.<portlet:namespace />keywords) {
			url += '&<portlet:namespace />name=' + document.<portlet:namespace />fm1.<portlet:namespace />keywords.value;
		}
	
		submitForm(document.hrefFm, url);
	}
	
</script>

<liferay-portlet:actionURL portletConfiguration="true" var="submitURL"/>

<form action="<%= submitURL %>" method="post" name="<portlet:namespace />fm" >
	<input type="hidden" name="<portlet:namespace /><%= DebatesConstants.CATEGORY_ID %>" />
	<input type="hidden" name="<portlet:namespace /><%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />


	<strong><liferay-ui:message key="category" />:</strong> 
	<c:if test="<%= category != null %>">
		<%= category.getName() %>
	</c:if>

	<%
		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter("struts_action", "/ext/debates/select_category");
		portletURL.setParameter("categoryId", String.valueOf(categoryId));

		List<String> headerNames = new ArrayList<String>();
	
		headerNames.add("category");
		headerNames.add("num-of-categories");
		headerNames.add("num-of-threads");
		headerNames.add("num-of-posts");
		headerNames.add(StringPool.BLANK);

		SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, 
		        SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, null);
		
		int total = MBCategoryLocalServiceUtil.getCategoriesCount(scopeGroupId, categoryId);

		searchContainer.setTotal(total);

		List results = MBCategoryLocalServiceUtil.getCategories(scopeGroupId, categoryId, searchContainer.getStart(), searchContainer.getEnd());

		searchContainer.setResults(results);

		List resultRows = searchContainer.getResultRows();

		for (int i = 0; i < results.size(); i++) {
			MBCategory curCategory = (MBCategory)results.get(i);

			curCategory = curCategory.toEscapedModel();
			
			ResultRow row = new ResultRow(curCategory, curCategory.getCategoryId(), i);

			PortletURL rowURL = renderResponse.createRenderURL();

			rowURL.setParameter("struts_action", "/portlet_configuration/edit_configuration");
			rowURL.setParameter("categoryId", String.valueOf(curCategory.getCategoryId()));
				
			// Name and description

			StringBuilder sb = new StringBuilder();

			sb.append(curCategory.getName());

			if (Validator.isNotNull(curCategory.getDescription())) {
				sb.append("<br />");
				sb.append(curCategory.getDescription());
			}
	
			row.addText(sb.toString());

			// Statistics

			int categoriesCount = categoryDisplay.getSubcategoriesCount(curCategory);
			int threadsCount = categoryDisplay.getSubcategoriesThreadsCount(curCategory);
			int messagesCount = categoryDisplay.getSubcategoriesMessagesCount(curCategory);

			row.addText(String.valueOf(categoriesCount));
			row.addText(String.valueOf(threadsCount));
			row.addText(String.valueOf(messagesCount));

			// Action

			sb = new StringBuilder();

			sb.append(renderResponse.getNamespace());
			sb.append("selectCategory('");
			sb.append(curCategory.getCategoryId());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(curCategory.getName()));
			sb.append("'); window.close();");

			row.addButton("right", SearchEntry.DEFAULT_VALIGN, LanguageUtil.get(pageContext, "choose"), sb.toString());

			// Add result row
	
			resultRows.add(row);
		}
	%>
	<liferay-ui:search-iterator searchContainer="<%= searchContainer %>" />
</form>