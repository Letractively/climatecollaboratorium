package org.climatecollaboratorium.components.faces.helpMessageTrigger;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.portlet.PortletSession;

import org.climatecollaboratorium.components.Helper;
import org.climatecollaboratorium.components.faces.helpMessage.HelpMessage;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ext.inlinehelp.service.HelpUserSettingHelper;
import com.icesoft.faces.context.DOMContext;
import com.icesoft.faces.renderkit.dom_html_basic.DomBasicRenderer;
import com.icesoft.faces.renderkit.dom_html_basic.HTML;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.theme.ThemeDisplay;

/**
 * Renderer responsible for generating HTML code for help message trigger. 
 * 
 * @author janusz
 *
 */
public class HelpMessageTriggerRenderer extends DomBasicRenderer {

    /**
     * CSS style class name of trigger for message that is visible.
     */
    private static final String CSS_VISIBLE = "helpVisible";
    
    /**
     * CSS style class name of trigger for message that is hidden.
     */
    private static final String CSS_HIDDEN = "helpHidden";
    
    /**
     * Prefix for hidden input field that denotes which trigger has been clicked. 
     */
    private static final String MESSAGE_TRIGGER = "MESSAGE_TRIGGER_";
    
    /**
     * Default CSS style class name for help message trigger.
     */
    private static final String DEFAULT_CLASS_NAME = "helpMessageTrigger";
    
    /**
     * ID of a message that is currently processed. 
     */
    private String messageId;

    /**
     * Starts HTML anchor element that will wrap everything between &lt;helpMessageTrigger&gt; and &lt;helpMessageTrigger&gt;
     */
    public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {

        DOMContext domContext = DOMContext.attachDOMContext(facesContext, uiComponent);
        HelpMessageTrigger hmt = (HelpMessageTrigger) uiComponent;
        messageId = hmt.getMessageId();

        HtmlForm myForm = (HtmlForm) findForm(uiComponent);
        String formId = myForm.getClientId(facesContext);
        
        Element hiddenInput = domContext.createElement(HTML.INPUT_ELEM);
        hiddenInput.setAttribute(HTML.TYPE_ATTR, "hidden");
        hiddenInput.setAttribute(HTML.NAME_ATTR, MESSAGE_TRIGGER + uiComponent.getClientId(facesContext));
        domContext.getCursorParent().appendChild(hiddenInput);
        
        Element hideThisAnchor = domContext.createElement(HTML.ANCHOR_ELEM);
        hideThisAnchor.setAttribute(HTML.HREF_ATTR, "javascript:;");
        String onclickString = "document.forms['" + formId + "']['" + MESSAGE_TRIGGER + uiComponent.getClientId(facesContext) + "'].value = 'true';" + 
            "iceSubmitPartial(document.forms['" + formId + "']," + " this,event); " + "return false;";
        
        String className = DEFAULT_CLASS_NAME;
        if (hmt.getStyleClass() != null && !hmt.getStyleClass().trim().equals("")) {
            className += " " + hmt.getStyleClass();
        }
        if (isHelpVisible(facesContext)) {
            className += " " + CSS_VISIBLE;
        }
        else {
            className += " " + CSS_HIDDEN;
        }
        
        

        hideThisAnchor.setAttribute(HTML.CLASS_ATTR, className);
        hideThisAnchor.setAttribute(HTML.ONCLICK_ATTR, onclickString);
        
        domContext.getCursorParent().appendChild(hideThisAnchor);
        domContext.setCursorParent(hideThisAnchor);

    }
    
    /**
     * Encodes end of an element (closes HTML anhor).
     */
    public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {
        DOMContext domContext = DOMContext.attachDOMContext(facesContext, uiComponent);
        domContext.setCursorParent(domContext.getCursorParent().getParentNode());
    }
    
    /**
     * Returns true if help message is currently visible
     * @param facesContext
     * @return
     */
    private boolean isHelpVisible(FacesContext facesContext) {
        PortletSession session = (PortletSession) facesContext.getExternalContext().getSession(true);
        boolean helpVisible = true;
        ThemeDisplay td = Helper.getThemeDisplay();

        try {
            helpVisible = HelpUserSettingHelper.getHelpUserSetting(td.getUserId(), messageId, session);
        } catch (PortalException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return helpVisible;
    }

    /**
     * Method called when wrapping form is being submitted. It checks which message visibility
     * has to be triggered and does what was requested.
     */
    public void decode(FacesContext facesContext, UIComponent uiComponent) {
        // toogle visibility
        if (uiComponent instanceof HelpMessageTrigger) {
            ThemeDisplay td = Helper.getThemeDisplay();
            PortletSession session = (PortletSession) facesContext.getExternalContext().getSession(true);
            messageId = ((HelpMessageTrigger) uiComponent).getMessageId();

            if (facesContext.getExternalContext().getRequestParameterMap().get(MESSAGE_TRIGGER + uiComponent.getClientId(facesContext)).toString().trim().equals("true")) {
            try {
                HelpUserSettingHelper.toogleHelpUserSetting(td.getUserId(), messageId, session);
            } catch (PortalException e) {
                e.printStackTrace();
            } catch (SystemException e) {
                e.printStackTrace();
            }
            }
        }
    }
    
    
}
