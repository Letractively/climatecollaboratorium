<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * File contains javascript for plans portlet.
 *
 * Contained functions are responsible for adjusting display of embedded portlet.
 *
 * @author janusz.p
 * @version 1.0
 */
 %>

/**
  Function responsible for modifying iframe and iframe content in which portlet
  is displayed. 
  
  Following steps are taken in order to tune portlet apperance:
  1. Iframe height is adjusted so there is no embedded scrollbar.
  2. For each link in embedded portlet if it directs not inside the same portlet then it
     is modified to make whole page change location when they are clicked. This prevents
     from embedding content other than portlet itself (for example other web pages etc).
  3. Resizes portlet's content-wrapper thus now portlet takse entire available space.
  4. Removes border surrounding portlet content.
  5. Removes portlet title bar.
  */
function portletPlansModifyInnerPortlet() {
	// update iframe height to wrap entire document, this prevents from displaying additional scrollbar
	var frame = document.getElementById("portlet-plans-inner-portlet");
	
	// analyze inner portlet links
	var frameContent =  (frame.contentDocument) ? frame.contentDocument : frame.contentWindow.document; 
	var contentDocument = jQuery(frameContent);
	
	/*
	contentDocument.find("a").each(function(tmp) {
	
		// portlet's link target is within a portlet if it contains a "widget" phrase
		if (jQuery.trim(this.href) != '' && this.href.search(/widget/i) == -1) {
			// modify each external link to force main document to change it's location when pressed
			var link = jQuery(this);
			link.click(function() {
				document.location = this.href;
			})
		}
	});
	*/
	
	// resize content wrapper
	var contentWrapper = contentDocument.find("#content-wrapper");
	contentWrapper.css("width", "100%");
	
	// disable portlet's border
	contentWrapper.find(".portlet").css("border", "0");
	
	// hide portlet's title
	contentWrapper.find(".portlet-topper").remove();
	
	frame.height = frameContent.body.scrollHeight + 50;
	jQuery("#portlet-plans-inner-portlet").load(portletPlansModifyInnerPortlet);
} 

/**
   Function responsible for attaching portletPlansModifyInnerPortlet to iframe's onload event 
 */ 
jQuery(document).ready(function() {
	
	var frame = jQuery("#portlet-plans-inner-portlet").load(portletPlansModifyInnerPortlet);
	frame.attr("frameborder", 0);
	frame.attr("SCROLLING", "NO");
});


function _plans_showDialog(dialogId, options) {
     //, dialogClass: 'collab-dialog' });
	jQuery("#" + dialogId).dialog({ height: options.height, width: options.width, modal: true, resizable: false, draggable: false, dialogClass: 'collab-dialog' });
	jQuery("#" + dialogId).show();
}

function _plans_showCopyDialog(dialogId, options) {
	if($("#PLANID").val()=="")
		{
			alert('Select a plan to copy by clicking on any row');
			
		}
	else{	
		$("#addplan-form").attr("action",$("#addplan-form").attr("action")+"&planId="+$("#PLANID").val());
		$("#addplan-form #COMMAND").val("copyplan");
		$("#addplan-form #UPDATE_TYPE").val("copyplan");
		jQuery("#" + dialogId).dialog({ height: options.height, width: options.width, modal: true, resizable: false, draggable: false, dialogClass: 'collab-dialog' });
		jQuery("#" + dialogId).show();
	}
}



function _plans_closeDialog(dialogId) {
	jQuery("#" + dialogId).dialog("close");
}
