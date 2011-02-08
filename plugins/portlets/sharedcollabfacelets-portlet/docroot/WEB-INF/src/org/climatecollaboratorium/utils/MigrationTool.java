package org.climatecollaboratorium.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.ext.portlet.models.model.ModelOutputItem;
import com.ext.portlet.models.service.ModelOutputItemLocalServiceUtil;
import com.liferay.portal.SystemException;

public class MigrationTool {
    
    public void removeQuestionFromLabels(ActionEvent e) throws SystemException {
        Pattern questionIconPattern = Pattern.compile("<img[^>]*qustion_icon\\.png[^>]*>");
        Pattern popupInfoBoxStart = Pattern.compile("<div[^>]*popup-info-box[^>]*>");
        Pattern popupInfoBoxEnd = Pattern.compile("</div>");
        
        for (ModelOutputItem item: ModelOutputItemLocalServiceUtil.getModelOutputItems(0, Integer.MAX_VALUE)) {
            
            String itemLabelFormat = item.getModelItemLabelFormat();
            String newLabelFormat = itemLabelFormat;
            // remove question marks
            Matcher questionMark = questionIconPattern.matcher(newLabelFormat);
            if (questionMark.find()) {
                newLabelFormat = itemLabelFormat.substring(0, questionMark.start()) + itemLabelFormat.substring(questionMark.end());
            }
            
            // replace popup info boxes with proper notifications
            Matcher popupInfoBoxStartMatcher = popupInfoBoxStart.matcher(newLabelFormat);
            
            if (popupInfoBoxStartMatcher.find()) {
                newLabelFormat = newLabelFormat.substring(0, popupInfoBoxStartMatcher.start()) + 
                    "<div class=\"act_tooltip_label\" style=\"display: none;\"><div class=\"act_tt-wrap_label\"><div class=\"act_tt-txt_label\">" + 
                    newLabelFormat.substring(popupInfoBoxStartMatcher.end());
                

                // replace popup info boxes with proper notifications
                Matcher popupInfoBoxEndMatcher = popupInfoBoxEnd.matcher(newLabelFormat);
                if (popupInfoBoxEndMatcher.find()) {
                    newLabelFormat = newLabelFormat.substring(0, popupInfoBoxEndMatcher.start()) +
                    "</div></div><div class=\"act_tt-bot_label\"></div></div>" + 
                    newLabelFormat.substring(popupInfoBoxEndMatcher.end());
                }
            }
            if (! itemLabelFormat.equals(newLabelFormat)) {
                item.setModelItemLabelFormat(newLabelFormat);
                ModelOutputItemLocalServiceUtil.updateModelOutputItem(item);
            }
            
            
        }
        FacesContext.getCurrentInstance().addMessage("Label updated", getMsg("Labels updated"));
    }
    
    private FacesMessage getMsg(String msg) {
        return new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
    }

}
