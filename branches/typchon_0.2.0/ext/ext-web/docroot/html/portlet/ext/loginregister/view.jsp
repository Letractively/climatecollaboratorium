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
    boolean isLoggingIn = ParamUtil.getBoolean(renderRequest, "isLoggingIn", false);
    boolean isRegistering = ParamUtil.getBoolean(renderRequest, "isRegistering", false);
    boolean isForgotPassword = ParamUtil.getBoolean(request, "isForgotPassword");
    pageContext.setAttribute("isLoggingIn", isLoggingIn);
    pageContext.setAttribute("isRegistering", isRegistering);
    
    

    String redirect = (String) renderRequest.getAttribute("redirect");
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
    
    String key = (String)request.getAttribute("liferay-ui:error:key");
    String message = (String)request.getAttribute("liferay-ui:error:message");
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
	
</script>
    <c:if test="<%= ! themeDisplay.isSignedIn() %>">
        <%@ include file="/html/portlet/ext/loginregister/register.jspf" %>
    </c:if>
    <c:if test="<%= themeDisplay.isSignedIn() %>">
        You are logged in. <a href="<%= HtmlUtil.escape(themeDisplay.getURLSignOut()) %>" id="logout_link">Sign Out</a>
    </c:if>
    
<div class="clearfix"></div>