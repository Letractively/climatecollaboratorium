<%@ include file="/html/portlet/ext/mass_messaging/init.jsp"%>
<%@page import="com.ext.portlet.mass_messaging.service.MessagingIgnoredRecipientsLocalServiceUtil"%>
<%@page import="com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients"%>
<%@ page import="java.util.Comparator" %>
<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
  List<MessagingIgnoredRecipients> recipients = 
      MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientses(0, 
          MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientsesCount());

  List<User> users = Collections.emptyList();

  users = UserLocalServiceUtil.getUsers(0, Short.MAX_VALUE);
%>

<script type="text/javascript">
  function addDeletedRecipientId(recipientId) {
	  jQuery("#<portlet:namespace />fm").append("<input type='hidden' name='recipientId' value='" + recipientId + "' />");
  }

</script>

<script type="text/javascript">

    var prevJQuery;

    if (jQuery) {
        prevJQuery = jQuery;
        jQuery = null;
    }

</script>

<script type="text/javascript" src="/html/portlet/ext/js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="/html/portlet/ext/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="/html/portlet/ext/js/ui.core.js"></script>
<script type="text/javascript" src="/html/portlet/ext/js/jquery.autocomplete.js"></script>


<link type="text/css" href="/html/portlet/ext/js/jquery.autocomplete.css" rel="stylesheet" />

<script type="text/javascript">

    var <portlet:namespace/>users = [
        <c:forEach items="<%=users%>" var="item" varStatus="status">
          "${item.screenName}", "${item.emailAddress}"
          <c:if test="${not status.last}">, </c:if>
          
        </c:forEach>
        
    ];

    <portlet:namespace/>users.sort();


    $("document").ready(function() {
        var input = $("#<portlet:namespace/>userSelectorInput").autocomplete(<portlet:namespace/>users);//, {selectedItem: "username", searchObj: "username", startText: 'Begin typing for a list' });
        

    });
</script>


<script type="text/javascript">
    jQuery = prevJQuery;
</script>

<portlet:actionURL windowState="MAXIMIZED" var="submitActionURL">
    <portlet:param name="struts_action" value="/ext/mass_messaging/manage_ignored_recipients" />
</portlet:actionURL>

<h1>"do not call list" management</h1>
<form method="post" action="${submitActionURL}" id="<portlet:namespace />fm" name="<portlet:namespace />fm">
  <input name="redirect" value="<%= currentURL %>" type="hidden" />
  <table>
    <tr>
      <td>
        <label for="name">Screen name or email that should be ignored when sending messages with mass messaging tool</label>
        <input name="name" type="text" size="60" id="<portlet:namespace/>userSelectorInput" />
      </td>
      <td>
        <input type="submit" name="op" value="Add" />
      </td>
    </tr>
  </table>

  <h2>Ignored recipients</h2>
  
  <liferay-ui:search-container emptyResultsMessage="There are no messages">
    <liferay-ui:search-container-results 
            results="<%= MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientses(searchContainer.getStart(), searchContainer.getEnd()) %>"
            total="<%= MessagingIgnoredRecipientsLocalServiceUtil.getMessagingIgnoredRecipientsesCount() %>"
    />
    
    <liferay-ui:search-container-row
        className="com.ext.portlet.mass_messaging.model.MessagingIgnoredRecipients"
        keyProperty="ignoredRecipientId"
        modelVar="recipient"
    >
       <liferay-ui:search-container-column-text
            name="Name"
            property="name"
        />
        
        <liferay-ui:search-container-column-text
            name="Email"
            property="email"
        />
        
        
        <liferay-ui:search-container-column-text
            name="Action"
            value='<input type="submit" name="op" value="Delete" onclick="addDeletedRecipientId(${recipient.ignoredRecipientId})" />' 
        />
            
    </liferay-ui:search-container-row>
    
    <liferay-ui:search-iterator />

</liferay-ui:search-container>
  
  
  
  
  
  
  
  
  
  
  
</form>