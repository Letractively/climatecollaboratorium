<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>
  
<%
User forgotUser = (User)request.getAttribute(ForgotPasswordAction.class.getName());
%>

<portlet:actionURL var="submitURL">
    <portlet:param name="saveLastPath" value="1"/>
    <portlet:param name="struts_action" value="/ext/user_info/forgot_password"/>
    <portlet:param name="isForgotPassword" value="true"/>
</portlet:actionURL>

   <script type="tezt/javascript">
        jQuery(document).ready(function() {
            jQuery("div.taglib-captcha table tr:last").append("<td><span class='required'>*required</span></td>");
        });
    </script>

<form action="${submitURL}" class="uni-form" method="post" name="<portlet:namespace />forgotPassword">
    
        <c:if test="<%= error != null && isForgotPassword %>">
        <div id="userInfoErrorMessages" style="display: none">
            <span><%= LanguageUtil.get(pageContext, loginErrors.get(error)) %></span>
        </div>
    </c:if>
    

    <input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.ADD %>"/>
    <input name="<portlet:namespace />openId" type="hidden" value="<%= HtmlUtil.escape(openId) %>"/>
    <input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escape(redirect) %>"/>

    <!--mark-->

    <div class="DialogBox forgotPassword">
        <div class="DialogNWCorner">
        </div>
        <div class="DialogNEdge">
            <div class="content">
                <h4>Password reminder</h4>
            </div>
        </div>
        <div class="DialogNECorner">
        </div>
        <br class="clearfloat"/>

        <div class="DialogWEdge">
        </div>
        <div class="DialogBody">
            <div class="content">
                <table>
                    <c:choose>
                        <c:when test="<%= forgotUser == null %>">
                            <input name="<portlet:namespace />step" type="hidden" value="1" />
                            <tr>
                                <td colspan=3>
                                    <div class="portlet-msg-info">
                                        Please enter your screen name in order to restore your password.
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td><label class="lable"><liferay-ui:message key="screen-name"/></label></td>
                                <td><input class="text" type="text" name="<portlet:namespace />screenName" /></td>
                                <td><span class="required">*required</span></td>
                            </tr>
                        </c:when>
                        <c:when test="<%= (forgotUser != null) && Validator.isNotNull(forgotUser.getEmailAddress()) %>">
                            <input name="<portlet:namespace />step" type="hidden" value="2" />
                            <input name="<portlet:namespace />emailAddress" type="hidden" value="<%= forgotUser.getEmailAddress() %>" />
                            
                            <c:if test="<%= Validator.isNotNull(forgotUser.getReminderQueryQuestion()) && Validator.isNotNull(forgotUser.getReminderQueryAnswer()) %>">
                                <tr>
                                    <td colspan=3>
                                        <div class="portlet-msg-info">
                                            <%= LanguageUtil.format(pageContext, "a-new-password-will-be-sent-to-x-if-you-can-correctly-answer-the-following-question", "your email address") %>
                                        </div>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td><label for="<portlet:namespace />answer"><liferay-ui:message key="<%= forgotUser.getReminderQueryQuestion() %>" /></label></td>
                                    <td><input name="<portlet:namespace />answer" type="text" /></td>
                                    <td><span class="required">*required</span></td>
                                </tr>
                            </c:if>
                            <c:if test="<%= PropsValues.CAPTCHA_CHECK_PORTAL_SEND_PASSWORD %>">
                                <tr>
                                    <td colspan="3">
                                        <portlet:actionURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>" var="captchaURL">
                                        <portlet:param name="struts_action" value="/login/captcha" />
                                        </portlet:actionURL>
                                        <liferay-ui:captcha url="<%= captchaURL %>" />
                                    </td>
                                </tr>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <tr><td>
                                <div class="portlet-msg-alert">
                                    <liferay-ui:message key="the-system-cannot-send-you-a-new-password-because-you-have-not-provided-an-email-address" />
                                </div>
                            </td></tr>
                        </c:otherwise>
                    </c:choose>
                
                </table>
            </div>

        </div>
        <div class="DialogEEdge">
        </div>
        <br class="clearfloat"/>

        <div class="DialogSWCorner">
        </div>
        <div class="DialogSEdge">
            <div class="content">
                <a href="javascript:;" onClick="document.<portlet:namespace />forgotPassword.submit();"
                   class="checkIcon"><span class="hidden">OK</span></a>
                <a href="javascript:;"
                   onClick="<portlet:namespace />closeDialog('forgotPasswordDialog');"
                   class="cancelIcon"><span class="hidden">CANCEL</span></a>


            </div>
        </div>
        <div class="DialogSECorner">
        </div>
        <br class="clearfloat"/>
    </div>

</form>