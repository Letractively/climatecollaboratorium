jQuery(document).ready(function() {
    initShowMessage();
});


function showMessageByElem(elem, highlight) {
    var link = jQuery(elem);
    var contentRow;
    if (! link.hasClass('processed')) {
        var content = link.parent().find(".msgContent");
        // append content after current row
        contentRow = jQuery("<tr class='contentRow hidden'><td colspan='4'>" + content.html() + "</td></tr>");
        link.parent().parent().after(contentRow); 
        contentRow.show();
        
        link.addClass("processed");
    }
    else {
        contentRow = link.parent().parent().next();
        if (contentRow.is(":visible")) {
            contentRow.hide();
        }
        else {
            contentRow.show();
        }
    
    }	
    if (highlight) {
        contentRow.effect("highlight", {}, 3000);
        link.parent().parent().effect("highlight", {}, 3000);
    }
}

function showMessageById(id) {
	showMessageByElem(document.getElementById(id), true);
}

function initShowMessage() {
    jQuery(".showContent").unbind("click");
    jQuery(".showContent").click(function() {
    	showMessageByElem(this);
    });
}


var usersMap;

function initSendMessageForm(users, usersMapParam) {
    usersMap = usersMapParam;
    
    jQuery('#userSelectorInput').focus(function() {
        jQuery("#please_choose_from_list").hide();
    });
        
    jQuery('#userSelectorInput').blur(function() {
        var val = jQuery.trim(jQuery(this).val());
        if (val != "") {
            jQuery("#please_choose_from_list").show();
        }
        jQuery(this).val(""); 
    });
    
    var input = jQuery("#userSelectorInput").autoSuggest(users, {selectedItem: "username", searchObj: "username", startText: 'Begin typing for a list' });
    jQuery(".composeMessageForm").validate();
    
}

function updateReceipients() {
    var receipients = [];
    jQuery(".as-selections li").each(function() {
        receipients.push(usersMap[jQuery(this).text().substring(1)]);
    });
    receipients.sort();
    jQuery(".messageReceipientsInput").val(receipients);
}

function sendMessage() {
    if (jQuery(".composeMessageForm").valid() && receipientsValid()) {
        jQuery(".sendMessageLink").click();
    }
}

function receipientsValid() {
    updateReceipients();
    jQuery(".messageReceipientsError").remove();
    //alert(jQuery(".messageReceipientsInput").val());
    if (jQuery.trim(jQuery(".messageReceipientsInput").val()) == '') {
        jQuery(".messageReceipientsInput").after("<label class='error messageReceipientsError'>This field is required.</label>");
        return false;
    }
    return true;
}
        