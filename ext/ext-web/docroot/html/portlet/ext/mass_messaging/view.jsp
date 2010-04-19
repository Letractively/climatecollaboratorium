<%@ include file="/html/portlet/ext/mass_messaging/init.jsp"%>

<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<portlet:renderURL var="addMessageURL">
    <portlet:param name="struts_action" value="/ext/mass_messaging/edit_message" />
</portlet:renderURL>

<portlet:renderURL var="ignoredRecipientsURL">
    <portlet:param name="struts_action" value="/ext/mass_messaging/manage_ignored_recipients" />
</portlet:renderURL>

<div class="action-link">
    <liferay-ui:icon image="add" url="${addMessageURL}" /><a href="${addMessageURL}">Add new message</a><br />
    <liferay-ui:icon image="manage_task" url="${addMessageURL}" /><a href="${ignoredRecipientsURL}">Manage "do not call list"</a><br /><br />
</div>


<h2>Messages already sent</h2>

<liferay-ui:search-container emptyResultsMessage="There are no messages">
    <liferay-ui:search-container-results 
            results="<%= MessagingMessageLocalServiceUtil.getMessagingMessages(searchContainer.getStart(), searchContainer.getEnd()) %>"
            total="<%= MessagingMessageLocalServiceUtil.getMessagingMessagesCount() %>"
    />
    
    <liferay-ui:search-container-row
        className="com.ext.portlet.mass_messaging.model.MessagingMessage"
        keyProperty="messageId"
        modelVar="data"
    >
        <portlet:renderURL var="migrateDebatesURL">
            <portlet:param name="struts_action" value="/ext/mass_messaging/view_message" />
            <portlet:param name="messageId" value="${ data.messageId }" />
        </portlet:renderURL>
        
        <liferay-ui:search-container-column-text
            name="Name"
            property="name"
            href="${ migrateDebatesURL }"
        />
        
        <liferay-ui:search-container-column-text
            name="Message subject"
            property="subject"
            href="${ migrateDebatesURL }"
        />
        
         <liferay-ui:search-container-column-text
            name="# Conversion"
            property="conversionCount"
            href="${ migrateDebatesURL }"
        />
         <liferay-ui:search-container-column-text
            name="Created date"
            property="createDate"
            href="${ migrateDebatesURL }"
        />
        <liferay-ui:search-container-column-text
            name="Modified date"
            property="modifiedDate"
            href="${ migrateDebatesURL }"
        />
            
    </liferay-ui:search-container-row>
    
    <liferay-ui:search-iterator />

</liferay-ui:search-container>