package org.climatecollaboratorium.components.faces.helpMessage;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.portlet.PortletSession;

import org.climatecollaboratorium.components.Helper;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.ext.inlinehelp.service.HelpUserSettingHelper;
import com.icesoft.faces.context.DOMContext;
import com.icesoft.faces.renderkit.dom_html_basic.DomBasicRenderer;
import com.icesoft.faces.renderkit.dom_html_basic.HTML;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.theme.ThemeDisplay;

public class HelpMessageRenderer extends DomBasicRenderer {

    private static final String CSS_VISIBLE = "helpVisible";
    private static final String CSS_HIDDEN = "helpHidden";
    private static final String CSS_STYLE_NAME = "inlineHelp";
    private static final String CSS_CLASS_HIDE_THIS = "hideThisTrigger";
    private static final String MESSAGE = "MESSAGE_";
    private static final String DEFAULT_HIDE_THIS_MESSAGE = "hide this";

    private String messageId;

    public void encodeBegin(FacesContext facesContext, UIComponent uiComponent) throws IOException {
        DOMContext domContext = DOMContext.attachDOMContext(facesContext, uiComponent);
        messageId = ((HelpMessage) uiComponent).getMessageId();
        String className = CSS_STYLE_NAME + " " + ((HelpMessage) uiComponent).getStyleClass();
        HtmlForm myForm = (HtmlForm) findForm(uiComponent);
        String formId = myForm.getClientId(facesContext);
        
        Element hiddenInput = domContext.createElement(HTML.INPUT_ELEM);
        hiddenInput.setAttribute(HTML.TYPE_ATTR, "hidden");
        hiddenInput.setAttribute(HTML.NAME_ATTR, MESSAGE + uiComponent.getClientId(facesContext));
        domContext.getCursorParent().appendChild(hiddenInput);


        if (isHelpVisible(facesContext)) {
            Element divElement = domContext.createElement(HTML.DIV_ELEM);
            domContext.getCursorParent().appendChild(divElement);

            if (isHelpVisible(facesContext)) {
                className = className + " " + CSS_VISIBLE;
            } else {
                className = className + " " + CSS_HIDDEN;
            }
            divElement.setAttribute(HTML.CLASS_ATTR, className);

            domContext.setCursorParent(divElement);
        }

    }

    public void encodeChildren(FacesContext facesContext, UIComponent uiComponent) throws IOException {
        if (isHelpVisible(facesContext)) {
            super.encodeChildren(facesContext, uiComponent);
        }
    }

    public boolean isHelpVisible(FacesContext facesContext) {
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

    public void encodeEnd(FacesContext facesContext, UIComponent uiComponent) throws IOException {
        if (isHelpVisible(facesContext)) {
            HelpMessage msg = (HelpMessage) uiComponent;
            HtmlForm myForm = (HtmlForm) findForm(uiComponent);
            String formId = myForm.getClientId(facesContext);
            DOMContext domContext = DOMContext.attachDOMContext(facesContext, uiComponent);
            Node n = domContext.getCursorParent();
            Element divElement = domContext.createElement(HTML.DIV_ELEM);
            divElement.setAttribute(HTML.CLASS_ATTR, CSS_CLASS_HIDE_THIS);
            Element hideThisAnchor = domContext.createElement(HTML.ANCHOR_ELEM);
            hideThisAnchor.setAttribute(HTML.HREF_ATTR, "javascript:;");
            String onclickString = "document.forms['" + formId + "']['" + MESSAGE + uiComponent.getClientId(facesContext) + "'].value = 'true';" + 
                "iceSubmitPartial(document.forms['" + formId + "']," + " this,event); return false;";

            hideThisAnchor.setAttribute(HTML.ONCLICK_ATTR, onclickString);
            
            String hideThisMessage = DEFAULT_HIDE_THIS_MESSAGE;
            if (msg.getHideThisMessage() != null && !msg.getHideThisMessage().trim().equals("")) {
                hideThisMessage = msg.getHideThisMessage();
            }
            
            hideThisAnchor.appendChild(domContext.createTextNode(hideThisMessage));

            divElement.appendChild(hideThisAnchor);
            domContext.getCursorParent().appendChild(divElement);
            
            domContext.setCursorParent(domContext.getCursorParent().getParentNode());
        }
    }

    public void decode(FacesContext facesContext, UIComponent uiComponent) {
        // toogle visibility
        messageId = ((HelpMessage) uiComponent).getMessageId();
        ThemeDisplay td = Helper.getThemeDisplay();
        PortletSession session = (PortletSession) facesContext.getExternalContext().getSession(true);
        if (facesContext.getExternalContext().getRequestParameterMap().get(MESSAGE + uiComponent.getClientId(facesContext)).equals("true")) {

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
