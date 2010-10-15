<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * User info panel that should be displayed in top right corner of the page.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
%>
<%@ include file="/html/portlet/login/init.jsp" %>

<portlet:defineObjects />
<script type="text/javascript">
    function <portlet:namespace />modifyLoginRegisterURL(anchor) {
        var location = anchor.href;
        if (window.location.toString().indexOf("loginregister") < 0) {
            // modify url only when not on login/register page
            anchor.href = location + "&redirect=" + escape(window.location);
        }
    }

</script>

<!-- end #loginBox -->
<div class="topLink">
    <span>
	<c:choose>
		<c:when test="<%= themeDisplay.isSignedIn() %>">
			<a href="<%= HtmlUtil.escape(themeDisplay.getURLSignOut()) %>" id="logout_link">log out</a>
		</c:when>
		<c:otherwise>
			<a href="/web/guest/loginregister?p_p_id=loginregister" onclick="<portlet:namespace />modifyLoginRegisterURL(this);">log in</a>
		</c:otherwise>
	</c:choose>
	|</span>
	<a href="/web/guest/resources/-/wiki/Main/About%20the%20Climate%20CoLab">about</a>
	<span>|</span>
	<a href="/web/guest/resources/-/wiki/Main/Help">help</a>
	<span>|</span>
	<a href="/web/guest/feedback">feedback</a>
</div>

<div id="loginBox">
	<c:choose>
		<c:when test="<%= themeDisplay.isSignedIn() %>">
			<div id="logined_tip">
				<div class="userImg"><img src="<%= themeDisplay.getPathImage() %>/user_portrait?img_id=<%= user.getPortraitId() %>" alt="userFace" /></div>
				
			</div>
		</c:when>
		<c:otherwise>	
			<div id="register_tip">
				<div class="registerImg"><span></span></div>
        		<div class="register">
				    <p>
                        New user?
                        <br />
				        <a href="/web/guest/loginregister?p_p_id=loginregister" onclick="pageTracker._trackPageview('/user/registerBegin'); <portlet:namespace />modifyLoginRegisterURL(this)">Register</a> here
				    </p>

    			</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<div id="loginPopupDialog" class="hidden">
    <div class="inner">
                <%@ include file="/html/portlet/ext/user_info/login_popup.jspf" %>
        </div>
</div>