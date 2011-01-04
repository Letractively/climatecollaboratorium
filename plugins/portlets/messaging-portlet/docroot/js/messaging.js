jQuery(document).ready(function() {
    initShowMessage();
});

function initShowMessage() {
    jQuery(".showContent").unbind("click");
    jQuery(".showContent").click(function() {
        var link = jQuery(this);
        if (! link.hasClass('processed')) {
            var content = link.parent().find(".msgContent");
            // append content after current row
            var contentRow = jQuery("<tr class='contentRow hidden'><td colspan='3'>" + content.html() + "</td></tr>");
            link.parent().parent().after(contentRow); 
            contentRow.show();
            
            link.addClass("processed");
        }
        else {
            var contentRow = link.parent().parent().next();
            if (contentRow.is(":visible")) {
                contentRow.hide();
            }
            else {
                contentRow.show();
            }
        
        }
         
        //jQuery(this).parent().find(".msgContent").show();
    });
}

var usersMap;

function initSendMessageForm(users, usersMapParam) {
    usersMap = usersMapParam;
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
        