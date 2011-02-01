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

<%@ include file="/html/portlet/login/init.jsp" %>

<%
User user2 = (User)request.getAttribute(ForgotPasswordAction.class.getName());

if (Validator.isNull(authType)) {
	authType = company.getAuthType();
}
%>
 <div class="popup_reg">
      <div class="popupreg_shade">
        <div class="popupreg_form">
          <div class="popupreg_head"><h3>Forgotten password reminder</h3></div>
          
<form action="<portlet:actionURL><portlet:param name="saveLastPath" value="0" /><portlet:param name="struts_action" value="/ext/loginregister/forgotpassword" /></portlet:actionURL>" class="uni-form" method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace />redirect" type="hidden" value="<portlet:renderURL />" />

<liferay-ui:error exception="<%= CaptchaTextException.class %>" message="Invalid captcha value" />

<liferay-ui:error exception="<%= NoSuchUserException.class %>" message='<%= "the-" + TextFormatter.format(authType, TextFormatter.K) + "-you-requested-is-not-registered-in-our-database" %>' />
<liferay-ui:error exception="<%= SendPasswordException.class %>" message="your-password-can-only-be-sent-to-an-external-email-address" />
<liferay-ui:error exception="<%= UserEmailAddressException.class %>" message="please-enter-a-valid-email-address" />
<liferay-ui:error exception="<%= UserReminderQueryException.class %>" message="your-answer-does-not-match-what-is-in-our-database" />

<fieldset class="block-labels">
	<c:choose>
		<c:when test="<%= user2 == null %>">

			<%
			String loginParameter = null;
			String loginLabel = null;

			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				loginParameter = "emailAddress";
				loginLabel = "email-address";
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				loginParameter = "screenName";
				loginLabel = "screen-name";
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				loginParameter = "userId";
				loginLabel = "id";
			}

			String loginValue = ParamUtil.getString(request, loginParameter);
			%>

			<input name="<portlet:namespace />step" type="hidden" value="1" />
            <p>
                <div class="popupreg_txt req"><liferay-ui:message key="<%= loginLabel %>" /></div>
                <input class="popupreg_input" name="<portlet:namespace /><%= loginParameter %>" size="30" type="text" value="<%= HtmlUtil.escape(loginValue) %>" />
            </p>
    
    

			<c:if test="<%= PropsValues.CAPTCHA_CHECK_PORTAL_SEND_PASSWORD && !PropsValues.USERS_REMINDER_QUERIES_ENABLED %>">
				<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="captchaURL">
					<portlet:param name="struts_action" value="/ext/loginregister/captcha" />
				</portlet:actionURL>

				<liferay-ui:captcha url="<%= captchaURL %>" />
			</c:if>

            <div class="popupreg_login_wrap"><input type="submit" name="submit" class="popupreg_send" value="" /></div>

		</c:when>
		<c:when test="<%= (user2 != null) && Validator.isNotNull(user2.getEmailAddress()) %>">
			<input name="<portlet:namespace />step" type="hidden" value="2" />
			<input name="<portlet:namespace />emailAddress" type="hidden" value="<%= user2.getEmailAddress() %>" />

			<c:if test="<%= Validator.isNotNull(user2.getReminderQueryQuestion()) && Validator.isNotNull(user2.getReminderQueryAnswer()) %>">
				<div class="portlet-msg-info">
					<%= LanguageUtil.format(pageContext, "a-new-password-will-be-sent-to-x-if-you-can-correctly-answer-the-following-question", user2.getEmailAddress()) %>
				</div>

				<div class="ctrl-holder">
					<label for="<portlet:namespace />answer"><liferay-ui:message key="<%= user2.getReminderQueryQuestion() %>" /></label>

					<input name="<portlet:namespace />answer" type="text" />
				</div>
			</c:if>

<!--
			<c:if test="<%= PropsValues.CAPTCHA_CHECK_PORTAL_SEND_PASSWORD %>">
				<portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="captchaURL">
					<portlet:param name="struts_action" value="/ext/loginregister/captcha" />
				</portlet:actionURL>

				<liferay-ui:captcha url="<%= captchaURL %>" />
			</c:if>
			-->
			
			 <!-- recaptcha -->
             <!-- 
                        <div>
                            <liferay-ui:error exception="<%= CaptchaTextException.class %>" message="Invalid captcha value" />
                        </div>
                        
                        <div>
                            <script type="text/javascript">
                               var RecaptchaOptions = { lang : 'en'};
                           </script>
                           <script type="text/javascript" src="<%= PropsUtil.get("captcha.engine.recaptcha.url.script") %><%= PropsUtil.get("captcha.engine.recaptcha.key.public") %>">
                           </script>
                           <noscript>
                           
                               <iframe src="<%= PropsUtil.get("captcha.engine.recaptcha.url.noscript") %><%= PropsUtil.get("captcha.engine.recaptcha.key.public") %>"
                                   height="300" width="500" frameborder="0"></iframe><br />
                               <textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea>
                               <input type="hidden" name="recaptcha_response_field" value="manual_challenge" />
                           </noscript>
                           </div>
                            -->

            <div class="popupreg_login_wrap"><input type="submit" name="submit" class="popupreg_login" value="" /></div>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-alert">
				<liferay-ui:message key="the-system-cannot-send-you-a-new-password-because-you-have-not-provided-an-email-address" />
			</div>
		</c:otherwise>
	</c:choose>
</fieldset>

</form>
</div>
</div>
<div class="clear"></div>
</div>
<div class="clear"></div>

<%@ include file="/html/portlet/login/navigation.jspf" %>

<script type="text/javascript">
	Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace /><%= (user2 == null) ? "emailAddress" : "answer" %>);
</script>