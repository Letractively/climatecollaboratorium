<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 * Debate index page. 
 *
 * version 1.1: themed to be consistent with collaboratorium theme
 *
 * @author janusz.p
 * @version 1.1
 */
 %>
<%@ include file="/html/portlet/ext/debates/init.jsp" %>
<%@ include file="/html/portlet/ext/debates/tabs.jspf" %>

 <div class="tabContent_bottom">
                        <div id="discussionTabContent" class="tabContent <%= selectedTab %>">


<%
	List<MBCategory> topics = DebatesUtil.getTopics(renderRequest);
	MBCategory topicsCategory = (MBCategory) request.getAttribute(DebatesConstants.TOPICS_CATEGORY);
	MBCategory discussionCategory = (MBCategory) request.getAttribute(DebatesConstants.DISCUSSION_CATEGORY);
	long categoryId = topicsCategory.getCategoryId();
	
	boolean showSuggestTopicButton = 
		MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_MESSAGE);
	
	boolean showAddTopicButton = 
		MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY);
	
	boolean showAddIssueButton = 
		MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_CATEGORY) && 
		MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.ADD_MESSAGE);
	
	boolean showEditTopicButton = 
		MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.UPDATE);

	boolean showDeleteTopicButton = 
		MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId, ActionKeys.DELETE);

%>
<!-- Main actions panel -->
<portlet:renderURL var="suggestURL">
	<portlet:param name="struts_action" value="/ext/debates/edit_message" />
	<portlet:param name="redirect" value="<%= currentURL %>" />
	<portlet:param name="categoryId" value="<%= String.valueOf(discussionCategory.getCategoryId()) %>" />
	<portlet:param name="<%= DebatesConstants.VIEW_DEBATES_INDEX_TABS %>" value="<%= DebatesConstants.DISCUSSION_TAB %>" />
</portlet:renderURL>
<liferay-portlet:renderURL var="addTopicURL">
	<portlet:param name="struts_action" value="/ext/debates/edit_topic" />
	<portlet:param name="<%= DebatesConstants.REDIRECT %>" value="<%= currentURL %>" />
	<portlet:param name="<%= DebatesConstants.PARENT_CATEGORY_ID %>" value="<%= Long.toString(topicsCategory.getCategoryId()) %>" />
</liferay-portlet:renderURL>
<div class="page-context-help-container"> 
<a href='/web/guest/resources/-/wiki/Main/Debates'>What are debates?</a>
</div>

<div class="portlet-debates-topics-actions portlet-debates-links">
	<c:if test="<%= showSuggestTopicButton %>">
		<a href="${suggestURL}">
			<span class="portlet-debates-link">
				<liferay-ui:icon image="post" /><liferay-ui:message key="suggest-topic-or-question" />
			</span>
		</a>
	</c:if>
	<c:if test="<%= showAddTopicButton %>">
		<a href="${addTopicURL}">
			<span class="portlet-debates-link">
				<liferay-ui:icon image="add" /><liferay-ui:message key="add-topic" />
			</span>
		</a>
	</c:if>
</div>

<div class="portlet-debates-clear"></div>

<!-- topics with list of debates -->
<c:forEach var="topic" items="<%= topics %>">
	<%
		MBCategory topic = (MBCategory) pageContext.getAttribute("topic");
	%>
	<c:if test="<%= showEditTopicButton %>">
		<liferay-portlet:renderURL var="editTopicURL">
			<portlet:param name="struts_action" value="/ext/debates/edit_topic" />
			<portlet:param name="categoryId" value="${topic.categoryId}" />
			<portlet:param name="<%= DebatesConstants.REDIRECT %>" value="<%= currentURL %>" />
		</liferay-portlet:renderURL>
	</c:if>
	
	<c:if test="<%= showDeleteTopicButton %>">
		<liferay-portlet:actionURL var="deleteTopicURL">
			<portlet:param name="struts_action" value="/ext/debates/edit_topic" />
			<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="categoryId" value="${topic.categoryId}" />
		</liferay-portlet:actionURL>
	</c:if>
	
	<c:if test="<%= showAddIssueButton %>">
		<liferay-portlet:renderURL var="addIssueURL">
			<portlet:param name="struts_action" value="/ext/debates/edit_issue" />
			<portlet:param name="<%= DebatesConstants.PARENT_CATEGORY_ID %>" value="${topic.categoryId}" />
			<portlet:param name="<%= DebatesConstants.REDIRECT %>" value="<%= currentURL %>" />
		</liferay-portlet:renderURL>
	</c:if>
	
	<div class="portlet-debates-topic">
		<!-- topic name and actions -->
		<div class="portlet-debates-topic-header">
			<div class="portlet-debates-topic-title">
				<span class="portlet-debates-topic-title">${topic.name}</span>
			</div>
			<div class="portlet-debates-topic-actions portlet-debates-links">
				<c:if test="<%= showAddIssueButton %>">
					<a href="${addIssueURL}">
						<span class="portlet-debates-link">
							<liferay-ui:icon image="add" message="add-question" /><liferay-ui:message key="add-question" />
						</span>
					</a>
				</c:if>
					
				<c:if test="<%= showEditTopicButton %>">
					<a href="${editTopicURL}">
						<span class="portlet-debates-link">
							<liferay-ui:icon image="edit" /><liferay-ui:message key="update-topic" />
						</span>
					</a>
				</c:if>

				<c:if test="<%= showDeleteTopicButton %>">
					<liferay-ui:icon-delete url="${deleteTopicURL}" label="true" />
				</c:if>
				
			</div>
			<div class="portlet-debates-clear"></div>
		</div>
		
		<!-- List of topic's debates -->
		<liferay-ui:search-container delta="10" deltaParam="delta${topic.categoryId}" curParam="cur${topic.categoryId}">
			<liferay-ui:search-container-results>
			<%
				results = DebatesUtil.getIssues(renderRequest, topic.getCategoryId(), searchContainer.getStart(), 
				        searchContainer.getEnd());
				total = DebatesUtil.getIssuesCount(renderRequest, topic.getCategoryId());
				pageContext.setAttribute("results", results);
				pageContext.setAttribute("total", total);
			%>
			</liferay-ui:search-container-results>
			<liferay-ui:search-container-row
				className="com.liferay.portlet.messageboards.model.MBCategory"
				keyProperty="categoryId"
				modelVar="issue"
			>
				<%
					Map<String, Object> debateStatistics = DebatesUtil.getDebateStatistics(issue);
					
					//List<MBMessage> positionmessages= getChildMessages(issue); 
					
		            double totalQuestionVotes=0;
		            double maxVotes=0;
		            MBCategory debateCategory = DebatesUtil.getSubcategory(issue.getCategoryId(),
		                    DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
		            MBMessage question = DebatesUtil.getDebateMessage(debateCategory.getCategoryId());
		            List < MBMessage > positions = DebatesUtil.getChildMessages(question);

		            //List<MBCategory> positions = MBCategoryLocalServiceUtil.getCategories(issue.getGroupId());
		            MBMessage bestPosition = null;
		            String bestpositionURL=(String)pageContext.getAttribute("debateURL");
		            String voteString = "No votes";
					for(MBMessage position: positions)
					{
													
		            	double positionVotes = RatingsStatsLocalServiceUtil.getStats(MBMessage.class.getName(),position.getMessageId()).getTotalScore();
		            	
		            	totalQuestionVotes+=positionVotes;
		            		if (positionVotes> maxVotes)
		            		{
		            			maxVotes=positionVotes;
		            			bestPosition = position;
		            		}
		            	
					}
					
					
				   boolean showActions = MBCategoryPermission.contains(permissionChecker, scopeGroupId,categoryId,DebatesConstants.DELETE_DEBATE_QUESTION) || MBCategoryPermission.contains(permissionChecker, scopeGroupId, categoryId,DebatesConstants.UPDATE_DEBATE_QUESTION);
	             
					if (bestPosition!=null) {
					     long percentage= new Double((maxVotes/totalQuestionVotes)*100).longValue();
					     voteString=bestPosition.getSubject()+" ("+String.valueOf(percentage)+"%)";
					     bestpositionURL+="#"+renderResponse.getNamespace()+"message_"+bestPosition.getMessageId();
					    
					  }
				%>
				<portlet:renderURL var="debateURL">
					<portlet:param name="struts_action" value="/ext/debates/view_debate" />
					<portlet:param name="<%= DebatesConstants.BASE_CATEGORY_ID %>" value="<%= String.valueOf(issue.getCategoryId()) %>" />
				</portlet:renderURL>
				
				<portlet:renderURL var="discussionURL">
					<portlet:param name="struts_action" value="/ext/debates/view_debate" />
					<portlet:param name="<%= DebatesConstants.BASE_CATEGORY_ID %>" value="<%= String.valueOf(issue.getCategoryId()) %>" />
					<portlet:param name="<%= DebatesConstants.VIEW_DEBATES_INDEX_TABS %>" value="<%= DebatesConstants.DISCUSSION_TAB %>" />
				</portlet:renderURL>
				
				<liferay-ui:search-container-column-text
					name="question"
					property="name"
					href="<%= debateURL %>"
				/>
				<liferay-ui:search-container-column-text
					name="best-position-votes"
					value="<%=voteString%>"
					href='<%= bestpositionURL%>'
				/>
				
			     <liferay-ui:search-container-column-text
					name="posts"
					value="<%=String.valueOf(DebatesUtil.getAllChildren(question).size()-1)%>"
					href='<%= debateURL%>'
				/>
								
				<liferay-ui:search-container-column-text
					name="last-post"
					value='<%= debateStatistics.get(DebatesConstants.DEBATE_LAST_POST_DATE) != null ? dateFormatDateTime.format((Date) debateStatistics.get(DebatesConstants.DEBATE_LAST_POST_DATE)) : LanguageUtil.get(pageContext, "none") %>'
					href='<%= debateURL + "#" + renderResponse.getNamespace() + "message_" + debateStatistics.get(DebatesConstants.MESSAGE_ID) %>'
				/>
                <c:if test="<%=showActions%>">
				<liferay-ui:search-container-column-jsp
					name=""
					path="/html/portlet/ext/debates/issue_actions.jsp"
				/>
				</c:if>
			</liferay-ui:search-container-row>
			<liferay-ui:search-iterator />
	
		</liferay-ui:search-container>
	</div>
</c:forEach>

<div class="bottomBtn">
	<div class="floatRight">
    	<a href="javascript:;" onClick="<portlet:namespace />showDialog('suggestTopicDialog', {width: 480, height: 300})" class="suggestTopicBtn"><span class="hidden">Suggest a New Issue</span></a>
	</div>
</div>
<div class="clear"></div>

<div id="suggestTopicDialog" style="display: none">
	<div class="suggestion">
		<div class="suggestion_top">
	    	<div class="title"><h1><span class="hidden">your Suggestion</span></h1></div>
	    </div>
	    <div class="suggestion_bg">
	    	<div class="inner">
	            <textarea></textarea>
	            <div class="selectBox">
	                <label class="label"><span class="hidden">In</span></label>
	                <select class="emossion">
	                	<c:forEach var="topic" items="<%= topics %>">
	                		<option>${topic.name}</option>
	                	</c:forEach>
	                </select>
	            </div>
	        </div>
	    </div>
	    <div class="suggestion_bottom">
	    	<div class="btnBox">
	        	<a href="#" class="checkBtn first closeModal"><span class="hidden">Check</span></a>
	            <a href="#" onClick="<portlet:namespace />closeDialog('suggestTopicDialog')" class="cancelBtn closeModal"><span class="hidden">Cancel</span></a>
	        </div>
	    </div>
	</div>
</div>

<%@ include file="/html/portlet/ext/debates/bottom.jspf" %>