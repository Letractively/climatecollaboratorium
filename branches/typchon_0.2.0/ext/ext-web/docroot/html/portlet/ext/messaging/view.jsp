<%@ page import="java.util.*" %>
<%@ page import="com.ext.portlet.messaging.model.MessageRecipientStatus" %>
<%@ page import="com.ext.portlet.messaging.service.MessageRecipientStatusLocalServiceUtil" %>
<%@ page import="com.ext.portlet.messaging.NoSuchMessageRecipientStatusException" %>
<%@ include file="/html/portlet/ext/messaging/init.jsp" %>





<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%

    int messageCount = (Integer) renderRequest.getAttribute(MessageConstants.MESSAGE_COUNT);
    Message selectedMessage = (Message) renderRequest.getAttribute(MessageConstants.MESSAGE);
    List<Message> messageList = (List<Message>) renderRequest.getAttribute(MessageConstants.MESSAGES);

    int pagerMax = MessageConstants.PAGER_MAX_NUMBER;
    int pagerStart = ParamUtil.getInteger(request, MessageConstants.PAGER_START, 0);
    int pagerNext = pagerStart + pagerMax;
    int pagerFirst = 0;
    int pagerLast = Math.max(messageCount - (messageCount % pagerMax), 0);
    if (pagerStart > pagerLast) pagerStart = 0;

    boolean enableNext = pagerNext < messageCount;
    boolean enablePrev = pagerStart > 0;
    boolean enableLast = pagerStart < pagerLast;
    boolean enableFirst = pagerFirst < pagerStart;



    boolean isReceiverOfSelected = false;
    boolean isSelectedArchived = false;

    if (selectedMessage !=null) {
        try {
            isSelectedArchived = selectedMessage.isArchived(user.getUserId());
            isReceiverOfSelected = true;
        } catch (NoSuchMessageRecipientStatusException e) {
            //do nothing
        }

    }

    DateFormat dateformat = DateFormats.getDate(locale, timeZone);


    String selectedTab = ParamUtil.getString(renderRequest, MessageConstants.MESSAGE_TYPE, MessageConstants.INBOX);
    String currentURL = PortalUtil.getCurrentURL(request);
    boolean showMessage = selectedMessage != null;

    List<User> users = Collections.emptyList();

    try {
        users = UserLocalServiceUtil.getUsers(0, Short.MAX_VALUE);
        Collections.sort(users, new Comparator<User>() {

            public int compare(User o1, User o2) {
                return o1.getScreenName().compareToIgnoreCase(o2.getScreenName());
            }
        });

    } catch (Exception e) {
        e.printStackTrace();
    }


%>


<%!

    static String param = "_messaging_" + MessageConstants.MESSAGE_ID + "=";

    public static String reloadURL(long id, String currentURL, String reloadURL) {

        String result = currentURL;
        if (!currentURL.contains("?")) {
            result = reloadURL + "&" + param + id;
        } else if (!currentURL.contains(param)) {
            result += ("&" + param + id);
        } else result = currentURL.replaceFirst(param + "[^&]*", param + id);
        return result;
    }
%>


<portlet:renderURL windowState="NORMAL" var="firstPageURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_VIEW_MESSAGES%>"/>
    <portlet:param name="<%= MessageConstants.PAGER_START %>" value="<%= String.valueOf(pagerFirst) %>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=selectedTab%>"/>
</portlet:renderURL>

<portlet:renderURL windowState="NORMAL" var="lastPageURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_VIEW_MESSAGES%>"/>
    <portlet:param name="<%= MessageConstants.PAGER_START %>" value="<%= String.valueOf(pagerLast) %>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=selectedTab%>"/>
</portlet:renderURL>

<portlet:renderURL windowState="NORMAL" var="nextPageURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_VIEW_MESSAGES%>"/>
    <portlet:param name="<%= MessageConstants.PAGER_START %>" value="<%= String.valueOf(pagerNext) %>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=selectedTab%>"/>
</portlet:renderURL>

<portlet:renderURL windowState="NORMAL" var="prevPageURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_VIEW_MESSAGES%>"/>
    <portlet:param name="<%= MessageConstants.PAGER_START %>"
                   value="<%= String.valueOf(pagerStart-MessageConstants.PAGER_MAX_NUMBER) %>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=selectedTab%>"/>
</portlet:renderURL>

<portlet:renderURL windowState="NORMAL" var="inboxTabURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_VIEW_MESSAGES%>"/>
    <portlet:param name="<%= MessageConstants.PAGER_START %>" value="<%= String.valueOf(pagerFirst) %>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=MessageConstants.INBOX%>"/>

</portlet:renderURL>

<portlet:renderURL windowState="NORMAL" var="archivedTabURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_VIEW_MESSAGES%>"/>
    <portlet:param name="<%= MessageConstants.PAGER_START %>" value="<%= String.valueOf(pagerFirst) %>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=MessageConstants.ARCHIVED%>"/>

</portlet:renderURL>

<portlet:renderURL windowState="NORMAL" var="sentTabURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_VIEW_MESSAGES%>"/>
    <portlet:param name="<%= MessageConstants.PAGER_START %>" value="<%= String.valueOf(pagerFirst) %>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=MessageConstants.SENT%>"/>
</portlet:renderURL>

<portlet:actionURL windowState="NORMAL" var="moveURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_MOVE_MESSAGES%>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=MessageConstants.SENT%>"/>
    <portlet:param name="<%=MessageConstants.REDIRECT%>" value="<%=currentURL%>"/>
</portlet:actionURL>


<portlet:actionURL windowState="NORMAL" var="sendURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_SEND_MESSAGE%>"/>
    <portlet:param name="<%=MessageConstants.REDIRECT%>" value="<%=currentURL%>"/>
</portlet:actionURL>


<portlet:renderURL windowState="NORMAL" var="prefsURL">
    <portlet:param name="struts_action" value="/ext/messaging/view_preferences"/>
    <portlet:param name="<%=MessageConstants.REDIRECT%>" value="<%=currentURL%>"/>
</portlet:renderURL>

<portlet:renderURL windowState="NORMAL" var="reloadURL">
    <portlet:param name="struts_action" value="<%=MessageConstants.ACTION_VIEW_MESSAGES%>"/>
    <portlet:param name="<%= MessageConstants.PAGER_START %>" value="<%= String.valueOf(pagerStart) %>"/>
    <portlet:param name="<%=MessageConstants.MESSAGE_TYPE%>" value="<%=selectedTab%>"/>
</portlet:renderURL>


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

<script type="text/javascript" src="/html/portlet/ext/js/jquery.autoSuggest.js">
</script>


<script type="text/javascript">
    var <portlet:namespace/>previousView = null;
    var <portlet:namespace/>company = <%=company.getCompanyId()%>;
    var <portlet:namespace/>inputobject = null;

    var <portlet:namespace/>users = [
        <c:forEach items="<%=users%>" var="item" varStatus="status">
        <%
            User curr_user = (User)pageContext.getAttribute("item");
        %>

        { "username":"<%=curr_user.getScreenName()%>", "value":"<%=curr_user.getUserId()%>"}
        <c:if test="${!status.last}">,
        </c:if>

        </c:forEach>
    ];


    $("document").ready(function() {

        if (<%=showMessage%>) {
            setMessageVisible(true);
        }

        $('#<portlet:namespace/>userSelectorInput').focus(function() {
            $("#please_choose_from_list").hide();
        });
            
        $('#<portlet:namespace/>userSelectorInput').blur(function() {
            var val = $.trim($(this).val());
            if (val != "") {
                $("#please_choose_from_list").show();
            }
            $(this).val(""); 
        });
        var input = $("#<portlet:namespace/>userSelectorInput").autoSuggest(<portlet:namespace/>users, {selectedItem: "username", searchObj: "username", startText: 'Begin typing for a list' });
        <portlet:namespace/>inputobject = input.get(0);


        jQuery("#composeMessageForm").validate();
                
                
            
    });



    function setMessageVisible(b) {
        if (b) {

            jQuery("#<portlet:namespace/>MessageList").hide();
            jQuery("#<portlet:namespace/>pager").hide();

           jQuery("#<portlet:namespace/>ViewMessage").show();


        } else {
            document.location.href = "<%=reloadURL%>";
            <%--jQuery("#<portlet:namespace/>ViewMessage").hide();--%>
            <%--jQuery("#<portlet:namespace/>MessageList").show();--%>
            <%--jQuery("#<portlet:namespace/>pager").show();--%>

        }
    }

    function <portlet:namespace/>composeMessage(replyId) {

        jQuery("input:text[name='<portlet:namespace/><%=MessageConstants.COMPOSE_REPLY_TO%>']").val(replyId==null?"":replyId);
        jQuery("input[name='<portlet:namespace/><%=MessageConstants.COMPOSE_RECIPIENTS%>']").val("");
        jQuery("input[name='<portlet:namespace/><%=MessageConstants.COMPOSE_SUBJECT%>']").val("");
        jQuery("#composecontent textarea").text("");
        jQuery("#composeheader ul.as-selections li.as-selection-item").remove();
        <c:if test="<%=selectedMessage!=null%>">
        if (replyId) {
            <portlet:namespace/>manualAddRecipient('<%=selectedMessage.getFromId()%>');
            jQuery("input[name='<portlet:namespace/><%=MessageConstants.COMPOSE_SUBJECT%>']").val("<%="Re:"+selectedMessage.getSubject()%>");
        }
        </c:if>
        jQuery("#<portlet:namespace/>pager").hide();
        jQuery("#<portlet:namespace/>MessageReading").hide();
        jQuery("#<portlet:namespace/>ComposeMessage").show();

    }

    function <portlet:namespace/>cancelComposition() {
        jQuery("#<portlet:namespace/>ComposeMessage").hide();
        jQuery("#<portlet:namespace/>pager").show();
        jQuery("#<portlet:namespace/>MessageReading").show();
        jQuery("#composeMessageForm input[type='text'], #composeMessageForm textarea").val('');
    }

    function <portlet:namespace/>submitSendForm() {
        var values = jQuery(".as-values").val();
        jQuery("input:hidden[name='<portlet:namespace/><%=MessageConstants.COMPOSE_RECIPIENTS%>']").val(values);
        if (jQuery("#composeMessageForm").valid()) {
            document.<portlet:namespace/>composeFm.submit();
        }
        
    }

    <c:if test="<%=selectedMessage!=null%>">
    function <portlet:namespace/>moveSelectedMessage() {
        jQuery("input:hidden[name:'<portlet:namespace/><%=MessageConstants.MOVE_MESSAGE_IDS%>']").val('<%=selectedMessage.getMessageId()%>');
        document.<portlet:namespace/>moveFm.submit();
    }
    </c:if>

    function <portlet:namespace/>moveSelectedMessages() {
        var ids = jQuery("#<portlet:namespace/>MessageList input:checkbox[checked]").map(function() {
            return $(this).attr("name");
        }).get().join(",");
        jQuery("input:hidden[name:'<portlet:namespace/><%=MessageConstants.MOVE_MESSAGE_IDS%>']").val(ids);
        document.<portlet:namespace/>moveFm.submit();
    }

    function <portlet:namespace/>manualAddRecipient(userid) {
        jQuery.each(<portlet:namespace/>users,function(i,val) {
           if (val.value == userid) {
               <portlet:namespace/>inputobject.textFx(val,i);
               return false;
           }
        });

    }


</script>

<script type="text/javascript">
    jQuery = prevJQuery;
</script>


<div id="Messaging" class="single-col">
    <div class="PortletBox">
        <div class="content">

        <div class="t"></div>
            <div class="hd">
                <div class="toolbar">
                   <a href="${prefsURL}"><span class="smalllink">preferences</span></a>
                </div>
                <div class="name">
                    <h2>Messages</h2>
                    </div>
        </div>
            <div class="bd">
                <div id="<portlet:namespace/>MessageReading">
                    <div class="smalltabs">
                        <div class="tabhead">
                            <a id="messagecomposebutton" href="javascript:" onclick="<portlet:namespace/>composeMessage(null);">
                                 <span class="smallbutton">Compose Message</span></a>
                            <ul>
                            <li class="<%= selectedTab.equals(MessageConstants.INBOX) ? "current" : "" %>"><a
                                    href="${inboxTabURL}">Inbox</a></li>
                            <li class="<%= selectedTab.equals(MessageConstants.ARCHIVED) ? "current" : "" %>"><a
                                    href="${archivedTabURL}">Archived</a></li>
                            <li class="<%= selectedTab.equals(MessageConstants.SENT) ? "current" : "" %>"><a
                                    href="${sentTabURL}">Sent</a></li>
                        </ul>

                        <div class="clear"></div>
                    </div>

                    <div id="<portlet:namespace/>MessageList" class="tabbody">
                        <c:if test="<%=selectedTab.equals(MessageConstants.INBOX) && messageList!=null && messageList.size() > 0%>">
                        <div id="messagelistactions">
                            <div class="archivearrow">
                            <a href="javascript:" onclick="javascript:<portlet:namespace/>moveSelectedMessages();"><span
                                    id="movemessages">Archive selected messages</span></a>
                            </div>

                        </div>
                        </c:if>
                        <form action="${moveURL}" name="<portlet:namespace/>moveFm" method="post">
                           <input type="hidden" name="<portlet:namespace/><%=MessageConstants.MOVE_MESSAGE_IDS%>"/>
                         </form>
                            <display:table name='<%=messageList%>' id="row" htmlId="messagelisttable">
                                <%
                                    Message curr_msg = (Message) pageContext.getAttribute("row");
                                    String htmlclass= "unopened";
                                    if (curr_msg != null && (selectedTab.equals(MessageConstants.SENT) || curr_msg.isOpened(user.getUserId()))) {
                                        htmlclass = "opened";
                                    }

                                %>
                                <c:if test="<%=selectedTab.equals(MessageConstants.INBOX)%>">
                                    <display:column title="">
                                        <input type="checkbox"
                                               name="<%=curr_msg.getMessageId()%>"/>
                                    </display:column>
                                </c:if>
                                <display:column title="From" class="<%=htmlclass%>"><%=UserLocalServiceUtil.getUser(curr_msg.getFromId()).getScreenName()%></display:column>
                                <display:column title="Date" class="<%=htmlclass%>"><%= curr_msg.getCreateDate()==null?"<i>--no date--</i>":dateformat.format(curr_msg.getCreateDate())%>
                                </display:column>
                                <display:column title="Subject" class="<%=htmlclass+\" stretch\"%>"><a
                                        href="<%=reloadURL(curr_msg.getMessageId(),currentURL, reloadURL)%>"><%=curr_msg.getSubject()%>
                                </a></display:column>
                            </display:table>



                    </div>
                    <c:if test="<%=selectedMessage!=null%>">
                        <div id="<portlet:namespace/>ViewMessage" class="tabbody" style="display:none">
                            <a href="javascript:setMessageVisible(false);"><span
                                                                class="smallbutton">&lt;-Return to index</span></a>



                                <table id="messageViewheader">
                            <tr>
                                <td><label>From:</label></td>
                                <td class="stretch"><mit:climate-user userId="<%=String.valueOf(selectedMessage.getFromId())%>"/></td>
                                <td><label>Sent:</label>&nbsp;<%=selectedMessage.getCreateDate()!=null?dateformat.format(selectedMessage.getCreateDate()):"No date"%></td>
                            </tr>
                            <tr>
                                <td><label>To:</label></td>
                                <td class="stretch" colspan="2">
                                    <c:forEach items="<%=selectedMessage.getRecipients()%>" var="elt" begin="0" end="9" varStatus="status">
                                        <%
                                            MessageRecipientStatus recipient = (MessageRecipientStatus)pageContext.getAttribute("elt");

                                        %>
                                        <mit:climate-user userId="<%=String.valueOf(recipient.getUserId())%>"/><c:if test="${!status.last}">, </c:if>
                                    </c:forEach>
                                    <c:if test="<%=selectedMessage.getRecipients().size()>10%>">
                                        <i>(and <%=selectedMessage.getRecipients().size()-10%> more)</i>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Subject:</label></td>
                                <td class="stretch" colspan="2"><%=selectedMessage.getSubject()%></td>
                            </tr>

                        </table>
                            <div id="messagebody">
                                <%=HtmlUtil.stripHtml(selectedMessage.getContent()).replaceAll("\n","<br/>")%>
                            </div>
                            <div id="messagelowerpanel">
                                <ul class="horizontal">
                                    <li>
                                        <a href="javascript:<portlet:namespace/>composeMessage('<%=selectedMessage.getMessageId()%>')">
                                            <span class="smallbutton">Reply</span>
                                        </a>
                                    </li>
                                    <c:if test="<%=isReceiverOfSelected && !isSelectedArchived%>">
                                    <li><a href="javascript:<portlet:namespace/>moveSelectedMessage();"><span class="smallbutton">Archive</span></a></li>
                                    </c:if>
                                    <div class="clear"></div>
                                </ul>
                            </div>
                        </div>
                    </c:if>
                </div>
                </div>
                <div id="<portlet:namespace/>ComposeMessage" style="display:none">

                    <form action="${sendURL}" name="<portlet:namespace/>composeFm" method="post" id="composeMessageForm">
                        <table id="composeheader">
                            <tr>
                                <td><label>To:</label></td>
                                <td class="stretch">
                                    <input id="<portlet:namespace/>userSelectorInput" type="text" >
                                    <input type="hidden" class="required" name="<portlet:namespace/><%=MessageConstants.COMPOSE_RECIPIENTS%>" />
                                
                                           <div class="hidden" id="please_choose_from_list">
                                             <span class="error">
                                               Please choose user from the list.
                                             </span>
                                           </div> 
                                </td>
                            </tr>
                            <tr>
                                <td><label>Subject:</label></td>
                                <td class="stretch"><input  class="required" name="<portlet:namespace/><%=MessageConstants.COMPOSE_SUBJECT%>"
                                           type="text"/>

                                </td>
                            </tr>
                        </table>
                        <div id="composemessage">
                            <textarea rows='10' class="required"
                                      name="<portlet:namespace/><%=MessageConstants.COMPOSE_CONTENT%>"></textarea>
                        </div>
                        <div id="composeactions">
                            <ul class="horizontal">
                                <li><a href="javascript:<portlet:namespace/>submitSendForm();">
                                    <span
                                        class="smallbutton">Send</span></a></li>
                                <li><a href="javascript:<portlet:namespace/>cancelComposition();"><span
                                        class="smallbutton">Cancel</span></a>
                                </li>
                            </ul>
                        </div>
                    </form>

                </div>


            </div>


        <div class="ft">


            <div id="<portlet:namespace/>pager"class="pageNum">
                <div class="totalPage">Displaying
                    <strong><%= pagerStart + 1 %> - <%= Math.min(pagerNext, messageCount) %>
                    </strong>
                    of
                    <strong><%= messageCount %>
                    </strong> Messages
                </div>
                <div class="turnPage">
                    <c:choose>
                        <c:when test="<%= enableFirst %>">
                            <a class="firstPage black" href="${firstPageURL}">First Page</a>
                        </c:when>
                        <c:otherwise>
                            <span class="firstPage">FirstPage</span>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="<%= enablePrev %>">
                            <a class="previous black" href="${prevPageURL}">Previous</a>
                        </c:when>
                        <c:otherwise>
                            <span class="previous">Previous</span>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="<%= enableNext %>">
                            <a class="next black" href="${nextPageURL}">Next</a>
                        </c:when>
                        <c:otherwise>
                            <span class="next">Next</span>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="<%= enableLast %>">
                            <a class="lastPage black" href="${lastPageURL}">Last Page</a>
                        </c:when>
                        <c:otherwise>
                            <span class="lastPage">Last Page</span>
                        </c:otherwise>
                    </c:choose>

                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>


        </div>

    </div>
        <div class="b"><div></div></div>
</div>
</div>