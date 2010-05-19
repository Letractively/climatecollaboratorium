<%@ page import="java.util.List" %>
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

<%@ include file="/html/portlet/ext/wall/init.jsp" %>

<portlet:renderURL windowState="NORMAL" var="manageSubsURL">
    <portlet:param name="struts_action" value="/ext/activities/manage_subscriptions"/>
    <portlet:param name="<%=ActivityConstants.REDIRECT%>" value="<%=currentURL%>"/>
</portlet:renderURL>

<%
    long userId = (Long) renderRequest.getAttribute(ActivityConstants.SUBSCRIPTIONS_USER_PARAMETER);
    int activitiesCount = (Integer) renderRequest.getAttribute(ActivityConstants.ACTIVITY_COUNT_PARAMETER);
    List<SocialActivity> activities = (List<SocialActivity>) renderRequest.getAttribute(ActivityConstants.ACTIVITIES_PARAMETER);
    String pageIteratorStrutsPath = "/ext/activities/view_activities";
    DateFormat dateFormatDate = new SimpleDateFormat("MMMM d", Locale.getDefault());
    dateFormatDate.setTimeZone(TimeZone.getDefault());
    ActivityConstants.SubscriptionMode mode = (ActivityConstants.SubscriptionMode) renderRequest.getAttribute(ActivityConstants.SUBSCRIPTION_MODE);

%>
<div id="Wall" class="activities-wall single-col">
    <div class="PortletBox">
        <div class="content">

           
            <div class="hd">
                 <c:if test="<%=mode==ActivityConstants.SubscriptionMode.PERSONAL%>">

                    <div class="toolbar">
                    <a href="${manageSubsURL}">
                        Manage subcriptions
                    </a>
                    </div>

                </c:if>
                <div class="name">
                    <h2>
                    <c:choose>
                        <c:when test="<%=mode==ActivityConstants.SubscriptionMode.PERSONAL%>">
                            Subscribed activities
                        </c:when>
                        <c:when test="<%=mode==ActivityConstants.SubscriptionMode.USER%>">
                            My activities
                        </c:when>
                        <c:otherwise>
                            Community activities
                        </c:otherwise>
                    </c:choose>
                    </h2>
                </div>

            </div>
		 
	        <div class="bd">
	
	            <%@ include file="/html/portlet/ext/wall/view_renderer.jspf" %>
	        </div>
			<div class="t"></div>
        </div>
        <div class="b">
            <div></div>
        </div>
        </div>
</div>
