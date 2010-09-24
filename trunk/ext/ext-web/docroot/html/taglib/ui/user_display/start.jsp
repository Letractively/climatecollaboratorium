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

<%@ include file="/html/taglib/ui/user_display/init.jsp" %>
<%@ page import="com.ext.portlet.community.CommunityUtil" %>

<%
long portraitId = 0;
String tokenId = StringPool.BLANK;

if (userDisplay != null) {
	portraitId = userDisplay.getPortraitId();
	tokenId = ImageServletTokenUtil.getToken(userDisplay.getPortraitId());
}

if (Validator.isNull(url) && (userDisplay != null)) {
	url = CommunityUtil.generateUserHref(userDisplay.getUserId());
}

List<Presence> presences = null;

if (themeDisplay.isSignedIn() && (userDisplay != null) && (userId != user.getUserId())) {
	presences = (List<Presence>)request.getAttribute(_REQUEST_ATTRIBUTE_PREFIX + userId);

	if (presences == null) {
		presences = RUONUtil.getPresences(userId, locale);

		if (presences == null) {
			presences = new ArrayList<Presence>();
		}

		request.setAttribute(_REQUEST_ATTRIBUTE_PREFIX + userId, presences);
	}
}

boolean online = false;

if ((presences != null) && (presences.size() > 0)) {
	online = true;
}
%>

<div class="taglib-user-display">
	<c:if test="<%= displayStyle == 1 %>">
		<table class="lfr-table">
		<tr>
			<td valign="top">
	</c:if>

	<c:if test="<%= displayStyle == 2 %>">
		<%--<div style="white-space: nowrap;">--%>
		<div>
	</c:if>

	<div class="user-profile-image">

		<%
		boolean urlIsNotNull = Validator.isNotNull(url);
		%>

		<c:if test="<%= urlIsNotNull %>"><a href="<%= url %>"></c:if>

		<img alt="<%= (userDisplay != null) ? userDisplay.getFullName() : LanguageUtil.get(pageContext, "generic-portrait") %>" class="avatar" src="<%= themeDisplay.getPathImage() %>/user_<%= (userDisplay != null) && userDisplay.isFemale() ? "female" : "male" %>_portrait?img_id=<%= portraitId %>&t=<%= tokenId %>" width="65" />

		<c:if test="<%= urlIsNotNull %>"></a></c:if>
	</div>

	<c:if test="<%= displayStyle == 1 %>">
		</td>
		<td valign="top">
	</c:if>

	<c:if test="<%= displayStyle == 2 %>">
	</c:if>

	<div class="user-details <%= online ? "user-online" : "user-offline" %>">
		<c:choose>
			<c:when test="<%= userDisplay != null %>">
				<c:if test="<%= urlIsNotNull %>"><a class="user-name" href="<%= url %>"></c:if>

				<%= userDisplay.getScreenName() %>

				<c:if test="<%= urlIsNotNull %>"></a></c:if>

				<c:if test="<%= online %>">
					<ul class="lfr-component network-list">

						<%
						for (Presence presence : presences) {
						%>

							<li><%= presence.getOutput() %></li>

						<%
						}
						%>

					</ul>
				</c:if>
			</c:when>
			<c:otherwise>
				<%= userName %>
			</c:otherwise>
		</c:choose>

<%!
private static final String _REQUEST_ATTRIBUTE_PREFIX = "taglib:ui:user_display:";
%>