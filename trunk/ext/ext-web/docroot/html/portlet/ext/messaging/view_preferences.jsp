<%@ page import="java.util.*" %>
<%@ page import="com.ext.portlet.messaging.model.MessageRecipientStatus" %>
<%@ page import="com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil" %>
<%@ page import="com.ext.portlet.messaging.NoSuchMessageRecipientStatusException" %>
<%@ page import="com.ext.portlet.messaging.model.MessagingUserPreferences" %>
<%@ include file="/html/portlet/ext/messaging/init.jsp" %>

 <%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%

    String redirect = ParamUtil.get(request,MessageConstants.REDIRECT,"/");
     MessagingUserPreferences prefs = (MessagingUserPreferences) renderRequest.getAttribute(MessageConstants.MESSAGE_PREFERENCES);
    boolean b = prefs.getEmailOnReceipt();

%>

<portlet:actionURL windowState="NORMAL" var="udpatePrefs">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_UPDATE_PREFERENCES%>"/>
    <portlet:param name="<%=MessageConstants.REDIRECT%>" value="<%=redirect%>"/>
</portlet:actionURL>



<script type="text/javascript">
    function <portlet:namespace/>cancel() {
        document.location.href = "<%=redirect%>";
    }

    function <portlet:namespace/>submit() {
      document.<portlet:namespace/>submitFm.submit();
    }

</script>


<div id="Messaging" class="preferences single-col">
    <div class="PortletBox">
        <div class="content">

            <div class="t"></div>
            <div class="hd">
                <div class="name">
                    <h2>Messages - preferences</h2>
                </div>
            </div>
            <div class="bd">
                <form name="<portlet:namespace/>submitFm" action="${udpatePrefs}" method="post">
                    <label>Send me an email copy of messages I receive:</label>&nbsp;<input type="checkbox" name="<portlet:namespace/><%=MessageConstants.MESSAGING_PREF_COPY_ON_RECEIPT%>" <%=b?"checked":""%>/>                  
                </form>

            </div>


            <div class="ft">
                <div id="buttonactions" class="floatRight">
                    <a href="javascript:<portlet:namespace/>submit()"><span class="smallbutton">Submit</span></a>
                    <a href="javascript:<portlet:namespace/>cancel()"><span class="smallbutton">Cancel</span></a>
                </div>
               <div class="clear"></div>


            </div>

        </div>
        <div class="b">
            <div></div>
        </div>
    </div>
</div>