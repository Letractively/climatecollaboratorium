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
<%
    String redirect = PortalUtil.getCurrentURL(request);
	List<Role> userRoles = RoleServiceUtil.getUserRoles(user.getUserId());
	boolean isAdmin = false;
	
	for(Role role: userRoles) {
	    if( role.getName().equals("Administrator")) {
	        
	        isAdmin = true;
	    }
	}

	PasswordPolicy passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());

	Map<String, String> loginErrors = new HashMap<String, String>();
	loginErrors.put(AuthException.class.getName(), "authentication-failed");
	loginErrors.put(CookieNotSupportedException.class.getName(), "authentication-failed-please-enable-browser-cookies");
	loginErrors.put(NoSuchUserException.class.getName(), "please-enter-a-valid-login");
	loginErrors.put(PasswordExpiredException.class.getName(), "your-password-has-expired");
	loginErrors.put(UserEmailAddressException.class.getName(), "please-enter-a-valid-login");
	loginErrors.put(UserLockoutException.class.getName(), "this-account-has-been-locked");
	loginErrors.put(UserPasswordException.class.getName(), "please-enter-a-valid-password");
	loginErrors.put(UserScreenNameException.class.getName(), "please-enter-a-valid-screen-name");
	loginErrors.put(CaptchaTextException.class.getName(), "text-verification-failed");
	loginErrors.put(ContactFirstNameException.class.getName(), "please-enter-a-valid-first-name");
	loginErrors.put(ContactLastNameException.class.getName(), "please-enter-a-valid-last-name");
	loginErrors.put(DuplicateUserEmailAddressException.class.getName(), "the-email-address-you-requested-is-already-taken");
	loginErrors.put(DuplicateUserIdException.class.getName(), "the-user-id-you-requested-is-already-taken");
	loginErrors.put(DuplicateUserScreenNameException.class.getName(), "the-screen-name-you-requested-is-already-taken");
	loginErrors.put(ReservedUserEmailAddressException.class.getName(), "the-email-address-you-requested-is-reserved");
	loginErrors.put(ReservedUserIdException.class.getName(), "the-user-id-you-requested-is-reserved");
	loginErrors.put(ReservedUserScreenNameException.class.getName(), "the-screen-name-you-requested-is-reserved");
	loginErrors.put(UserEmailAddressException.class.getName(), "please-enter-a-valid-email-address");
	loginErrors.put(UserIdException.class.getName(), "please-enter-a-valid-user-id");
	loginErrors.put(UserPasswordException.class.getName() + UserPasswordException.PASSWORD_CONTAINS_TRIVIAL_WORDS, 
	    "that-password-uses-common-words-please-enter-in-a-password-that-is-harder-to-guess-i-e-contains-a-mix-of-numbers-and-letters");
	loginErrors.put(UserPasswordException.class.getName() + UserPasswordException.PASSWORD_LENGTH, 
	    LanguageUtil.format(pageContext, "that-password-is-too-short-or-too-long-please-make-sure-your-password-is-between-x-and-512-characters", String.valueOf(passwordPolicy.getMinLength()), false));
	loginErrors.put(UserPasswordException.class.getName() + UserPasswordException.PASSWORD_INVALID, 
	    "that-password-is-invalid-please-enter-in-a-different-password");
	loginErrors.put(UserPasswordException.class.getName() + UserPasswordException.PASSWORDS_DO_NOT_MATCH, 
    	"the-passwords-you-entered-do-not-match-each-other-please-re-enter-your-password");
	loginErrors.put(UserReminderQueryException.class.getName(), "provided-answer-is-incorrect");
	loginErrors.put(SendPasswordException.class.getName(), "an-error-occurred-when-sending-remainder-email");
    
	
	loginErrors.put(UserScreenNameException.class.getName(), "please-enter-a-valid-screen-name");
	
	
	boolean isLoggingIn = ParamUtil.getBoolean(request, "isLoggingIn");
	boolean isRegistering = ParamUtil.getBoolean(request, "isRegistering");
    boolean isForgotPassword = ParamUtil.getBoolean(request, "isForgotPassword");
	String error = null;
	boolean hasError = false;
    for(String loginError: loginErrors.keySet()) {
        if (SessionErrors.contains(renderRequest, loginError)) {
            error = loginError;
            hasError = true;
            Object errorException = SessionErrors.get(renderRequest, loginError);
            if (errorException instanceof UserPasswordException) {
                UserPasswordException err = (UserPasswordException) errorException;
                error = loginError + err.getType();
            }
        }
    }
 %>
<script type="text/javascript">
     <%@ include file="/html/js/collaboratorium/cookies.js" %>

     jQuery(document).ready(function() {
          var cookie =Get_Cookie(LOCKED_LOGIN_NAME); 
         if ( !cookie) {
              jQuery("#login_fx").show();
         } else {
              if (<%= themeDisplay.isSignedIn() %> && cookie!= <%= "'"+user.getScreenName()+"'" %>) {
                   document.location = "<%= HtmlUtil.escape(themeDisplay.getURLSignOut())%>"; 
             } else if (!<%= themeDisplay.isSignedIn()%>) {
                  var password = Get_Cookie(LOCKED_LOGIN_PASSWORD);
                  document.<portlet:namespace />login.<portlet:namespace />login.value = cookie;
                  document.<portlet:namespace />login.<portlet:namespace />password.value = password;
                  document.<portlet:namespace />login.submit(); 
             }
             
         }
     });

	function _user_info_showDialog(dialogId, options) {
		jQuery("#" + dialogId).dialog({ height: options.height, width: options.width, modal: true, resizable: false, draggable: false, dialogClass: 'collab-dialog' });
		jQuery("#" + dialogId).show();
	}

	function _user_info_closeDialog(dialogId) {
		jQuery("#" + dialogId).dialog("close");
	}

    function _user_info_showLogin() {
        $('#login').slideDown();
		$('#register').slideUp();
		$('#login_link').addClass('current');
    }

    function _user_info_showLoginPopup() {
        _user_info_showDialog("loginPopupDialog",{width:455,height:400});
    }
	
</script>

<c:if test="<%= error != null && isLoggingIn %>">
	<div id="userInfoErrorMessages" style="display: none">
		<span><%= LanguageUtil.get(pageContext, loginErrors.get(error)) %></span>
	</div>
	
	<script type="text/javascript">
		jQuery(document).ready(function() {
			jQuery("#userInfoErrorMessages").show();
			setTimeout('jQuery("#userInfoErrorMessages").fadeOut()', 3000);
		});
	</script>
	
</c:if>
<!-- end #loginBox -->
<div class="topLink">
    <span id="login_fx" class="hidden">
	<c:choose>
		<c:when test="<%= themeDisplay.isSignedIn() %>">
			<a href="<%= HtmlUtil.escape(themeDisplay.getURLSignOut()) %>" id="logout_link">log out</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:;" id="login_link">log in</a>
		</c:otherwise>
	</c:choose>
	|</span>
	<a href="/web/guest/resources/-/wiki/Main/About+the+Climate+Collaboratorium">about</a>
	<span>|</span>
	<a href="/web/guest/resources/-/wiki/Main/Climate+Collaboratorium+Help">help</a>
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
				<p>New User?</p>
				<p><a href="javascript:;" onClick="pageTracker._trackPageview('/user/registerBegin');<portlet:namespace />showDialog('registerDialog', {width: 440, height: 600})" id="registerLink">Register</a> here</p>

			</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>


<!-- end .topLink -->
<div id="login" class="hidden">
	<div class="inner">
		<%@ include file="/html/portlet/ext/user_info/login.jspf" %>
	</div>
</div>
<!-- end login -->
<div id="registerDialog" class="hidden">
	<div class="inner">
		<%@ include file="/html/portlet/ext/user_info/register.jspf" %>
	</div>
</div>
<!-- end register -->

<div id="loginPopupDialog" class="hidden">
    <div class="inner">
		<%@ include file="/html/portlet/ext/user_info/login_popup.jspf" %>
	</div>
</div>

<div id="forgotPasswordDialog" class="hidden">
    <div class="inner">
        <%@ include file="/html/portlet/ext/user_info/forgot_password.jsp" %>
    </div>
</div>
<!-- end forgot password -->             


<script type="text/javascript">
	<c:if test='<%= isLoggingIn %>'>
		$('#login').show();
		$('#login_link').addClass('current');
	</c:if>
	<c:if test='<%= isRegistering %>'>
	</c:if>
	
</script>   
<c:if test='<%= isForgotPassword %>'>
        <script type="text/javascript">
        jQuery(document).ready(function() {
            <portlet:namespace/>showDialog('forgotPasswordDialog', {width: 555, height: 600});
            jQuery("#userInfoErrorMessages").show();
            setTimeout('jQuery("#userInfoErrorMessages").fadeOut()', 3000);
        });
        </script>
    </c:if>        
    
            <c:if test='<%= isRegistering %>'>
            <script type="text/javascript">
            jQuery(document).ready(function() {
            <portlet:namespace/>showDialog('registerDialog', {width: 440, height: 6000});
            jQuery("#userInfoErrorMessages").show();
            setTimeout('jQuery("#userInfoErrorMessages").fadeOut()', 3000);
        });
            </script>
        </c:if>