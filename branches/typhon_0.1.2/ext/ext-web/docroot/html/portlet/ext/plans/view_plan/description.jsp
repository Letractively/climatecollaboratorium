
<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
*
* File responsible for presenting plan description.
*
* version 1.1 : add short description summary and edit description link
* version 1.2 : add in place editors
*
* @author janusz.p,jintrone
*
* @version 1.2
* @since 1.0
*/
%>
<%@ include file="/html/portlet/ext/plans/init.jsp"%>
<%
String planShortContentForEdit = UnicodeFormatter.toString(planSummary);
String planContentForEdit = UnicodeFormatter.toString(planContent);
%>

<!-- Include tabs panel -->
<%@ include file="/html/portlet/ext/plans/view_plan/view_plan_tabs.jspf"%>
<!-- prepare link for editing a plan  -->
<script type="text/javascript">
  /**
  Function called to hide existing content and display rich content FCKEditor
  */

  function <portlet:namespace />showRichContentEditor(show) {
    if (show) {
      jQuery("#<portlet:namespace />edit-long-description").show();
      jQuery("#<portlet:namespace />view-long-description").hide();
    } else {
      jQuery("#<portlet:namespace />edit-long-description").hide();
      jQuery("#<portlet:namespace />view-long-description").show();
    }
  }


  /**
  Function called to hide existing content and display short content FCKEditor
  */

  function <portlet:namespace />showShortContentEditor(show) {
    if (show) {
      jQuery("#<portlet:namespace />edit-short-description").show();
      jQuery("#<portlet:namespace />view-short-description").hide();
    } else {
      jQuery("#<portlet:namespace />edit-short-description").hide();
      jQuery("#<portlet:namespace />view-short-description").show();
    }
  }

  /**
  Function called to initialize Rich Content WYSIWYG editor. Returned value will be used to fill
  editor's textarea.

  @return String that should be used to initialize WYSIWYG content editor.

  */
  function <portlet:namespace />initRichContentEditor() {
    return "<%= planContentForEdit %>";
  }

  /**
  Function called to initialize Reduced Content WYSIWYG editor. Returned value will be used to fill
  editor's textarea.

  @return String that should be used to initialize WYSIWYG content editor.

  */
  function <portlet:namespace />initShortContentEditor() {
    return "<%= planShortContentForEdit %>";
  }

  /**
  Function responsible for retrieval of WYSIWYG short content editor content and assigning it to
  appropriate plan edit form field.

  */
  function <portlet:namespace />getShortHtmlValue() {


    var shortcontent = window.<portlet:namespace />shortContentEditor.getHTML();
    document.<portlet:namespace />shortfm.<portlet:namespace />plan_short_content.value = shortcontent;

    return true;
  }

  /**
  Function responsible for retrieval of WYSIWYG long content editor content and assigning it to
  appropriate plan edit form field.

  */
  function <portlet:namespace />getLongHtmlValue() {


    var longcontent = window.<portlet:namespace />richContentEditor.getHTML();
    document.<portlet:namespace />longfm.<portlet:namespace />plan_content.value = longcontent;
    return true;
  }
</script>

<%
PortletResponse presponse = (PortletResponse)request.getAttribute(JavaConstants.JAVAX_PORTLET_RESPONSE);
String namespace = presponse.getNamespace();
%>


<!-- prepare form submit action URL -->
<liferay-portlet:actionURL windowState="<%= WindowState.NORMAL.toString() %>" var="submitActionURLdesc">
  <portlet:param name="struts_action" value="/ext/plans/edit_plan" />
  <portlet:param name="<%= PlanConstants.PLAN_ID %>" value="<%=planId %>" />
  <portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
  <portlet:param name="<%= PlanConstants.REDIRECT %>" value="<%= currentURL %>" />
</liferay-portlet:actionURL>

<form 
    action="${submitActionURLdesc}"
    method="post"
    name="<portlet:namespace />shortfm"
    onsubmit="return <portlet:namespace />getShortHtmlValue()">


  <!--
    <c:if test="<%= isPlanMember && ! planPublished %>" > <div class="portlet-climateplan-add-plan
    portlet-plans-action-link"> <liferay-ui:icon image="edit" url="${editURL}" /> <a
    href="${editURL}"><liferay-ui:message key="edit-description" /></a> </div> </c:if> <br /><br /> <br>
  -->

  <!-- short plan description (first paragraph) -->
  <h2 class="shortDescription">
    <strong class="blue">
      Short Description
    </strong>
    <c:if test="<%=canEditPlan%>">
      <span class="editButton">
        <a 
            href="#"
            onClick="<portlet:namespace />showShortContentEditor(true);<portlet:namespace/>showRichContentEditor(false)">


          edit
        </a>
      </span>
    </c:if>
  </h2>
  <div class="clear">
  </div>
  <div class="planDescriptionText" id='<portlet:namespace />view-short-description'>
    <%= planSummary %>
  </div>
  <div class="portlet-plans-wrapper" id='<portlet:namespace />edit-short-description' style='display: none'>
    <div 'portlet-plans-short-editor'>
      <liferay-ui:input-editor 
          height="300px"
          name="<%=namespace+\"shortContentEditor\"%>"
          initMethod="<%=namespace+\"initShortContentEditor\"%>"
          toolbarSet="small-editor"
          width="100%"
      />
    </div>
    <input type="hidden" name="<portlet:namespace />plan_short_content" />
    <input type="hidden" name="<portlet:namespace /><%=PlanConstants.UPDATE_TYPE%>" value="<%=PlanConstants.UPDATE_SHORT_DESCRIPTION%>" />
  </br>
</br>
<input type="submit" value="<liferay-ui:message key="save" />" />
<input 
    type="button"
    value="<liferay-ui:message key="cancel" />"
    onClick="<portlet:namespace />showShortContentEditor(false)"
/>
</div>
<br />
<br />

</form>

<form 
    action="${submitActionURLdesc}"
    method="post"
    name="<portlet:namespace />longfm"
    onsubmit="return <portlet:namespace />getLongHtmlValue()">


  <!-- long description (rich content) -->

  <h2 class="detail">
    <strong class="blue">
      Extended Description
    </strong>
    <c:if test="<%=canEditPlan %>">
      <span class="editButton">
        <a 
            href="#"
            onClick="<portlet:namespace />showRichContentEditor(true);<portlet:namespace />showShortContentEditor(false)">


          edit
        </a>
      </span>
    </c:if>
  </h2>
  <div class="clear">
  </div>
  <div class="portlet-plans-wrapper" id='<portlet:namespace />view-long-description'>
    <%= planContent %>
  </div>

<div class="portlet-plans-wrapper" id='<portlet:namespace />edit-long-description' style='display: none'>
  <div class='portlet-plans-rich-editor'>
    <liferay-ui:input-editor 
        width="100%"
        height="600px"
        name="<%=namespace+\"richContentEditor\"%>"
        initMethod="<%=namespace+\"initRichContentEditor\"%>"/>
  </div>
 	<input type="hidden" name="<portlet:namespace /><%=PlanConstants.UPDATE_TYPE%>" value="<%=PlanConstants.UPDATE_DESCRIPTION%>" />
 	<input type="hidden" name="<portlet:namespace />plan_content" />
	</br>
	</br>
<input type="submit" value="<liferay-ui:message key="save" />" />
<input 
    type="button"
    value="<liferay-ui:message key="cancel" />"
    onClick="<portlet:namespace />showRichContentEditor(false)"/>
</div>
<br />
<br />
</form>
</div>
<%@ include file="/html/portlet/ext/plans/view_plan/bottom.jspf"%>
