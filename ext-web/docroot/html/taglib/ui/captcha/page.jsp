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

<%@ include file="/html/taglib/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.captcha.CaptchaUtil" %>

<%
String url = (String)request.getAttribute("liferay-ui:captcha:url");

boolean captchaEnabled = false;

if (portletRequest != null) {
	captchaEnabled = CaptchaUtil.isEnabled(portletRequest);
}
else {
	captchaEnabled = CaptchaUtil.isEnabled(request);
}
%>

<script type="text/javascript">
jQuery("span.why-necessary").click(function() {
	jQuery(this).parent().find(".why-necessary-dialog").fadeIn();
    jQuery(this).parent().find(".why-necessary-dialog").click(function() { jQuery(this).fadeOut() });
    
});
</script>

<c:if test="<%= captchaEnabled %>">
	<div class="taglib-captcha">
		<img alt="captcha" class="captcha" src="<%= url %>" />

		<table class="lfr-table">
		<tr>
			<td>
				Please type the characters you see in the box:<br />
				<span class="why-necessary">(Why is this necessary?)</span>
				<div class="hidden why-necessary-dialog">
		          To prevent automated computer "bots" from pretending to be people and registering on this site, 
				  we ask you to show that you are a real person by typing the characters you see. The characters are 
				  distorted in a way that makes it almost impossible for automated computer "bots" to do this correctly.
				</div>
			</td>
			<td>
				<input name="<%= namespace %>captchaText" size="10" type="text" value="" />
			</td>
		</tr>
		</table>
	</div>
</c:if>