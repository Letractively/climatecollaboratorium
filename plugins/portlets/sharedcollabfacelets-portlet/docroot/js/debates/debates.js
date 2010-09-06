    function showAddLinkDialog() {
        try {
            jQuery('#addLinkDialog').slideDown();
            var text = jQuery('#debateItemLinkText').val('');
            var url = jQuery('#debateItemLinkUrl').val('http://');
            //                    jQuery('#addLinkDialog').dialog({resizable: false, title: "Add a link", modal: true, width: 230, height: 120});
            //                    jQuery('#addLinkDialog').show();
        }
        catch (err) {
            alert('error: ' + err);
        }

    }

    function closeAddLinkDialog(caller) {
        //jQuery(caller).parent().parent().parent().parent().parent().dialog('destroy');
        jQuery('#addLinkDialog').slideUp();
    }

    function addLink(caller) {
        var text = jQuery('#debateItemLinkText').val();
        var url = jQuery('#debateItemLinkUrl').val();
        var isValid = !jQuery.trim(text) == '' && !jQuery.trim(url) == '';
        if (isValid) {
            var prevVal = jQuery('.debateItemContent').val();
            var linkCode = "&lt;a href=\"" + url + "\"&gt;" + text + "&lt;/a&gt; ";

            jQuery('.debateItemContent').val(prevVal + linkCode);
            //jQuery(caller).parent().parent().parent().parent().parent().dialog('destroy');
            jQuery('#addLinkDialog').hide();
        }
        else {
            alert('Provided values are invalid, all fields should contain values');
        }
    }

    function checkAddDebateItemReference() {

        jQuery('#referenceTextInput .validationError, #referenceUrlInput .validationError ').hide();

        var valid = true;
        var text = jQuery('#referenceTextInput input').val();
        var url = jQuery('#referenceUrlInput input').val();

        if (jQuery.trim(text) == "") {
            valid = false;
            jQuery('#referenceTextInput .validationError').show();
        }

        if (jQuery.trim(url) == "") {
            valid = false;
            jQuery('#referenceUrlInput .validationError').show();
        }

        return valid;
    }