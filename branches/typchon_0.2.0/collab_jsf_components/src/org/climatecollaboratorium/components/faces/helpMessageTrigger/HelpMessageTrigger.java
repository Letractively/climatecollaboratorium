package org.climatecollaboratorium.components.faces.helpMessageTrigger;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;

import org.climatecollaboratorium.components.Helper;

import com.icesoft.faces.context.effects.JavascriptContext;
import org.climatecollaboratorium.components.faces.helpMessage.HelpMessage;

public class HelpMessageTrigger extends UIComponentBase {
    public static final String COMPONENT_TYPE = "org.climatecollaboratorium.faces.HelpMessageTrigger";
    public static final String DEFAULT_RENDERER_TYPE = "org.climatecollaboratorium.faces.HelpMessageTriggerRenderer";
    public static final String COMPONENT_FAMILY = "org.climatecollaboratorium.faces.HelpMessageTriggerFamily";
    private String styleClass;
    private String messageId;
    private HelpMessage message;

    public HelpMessageTrigger() {
        super();
        JavascriptContext.includeLib(JavascriptContext.ICE_EXTRAS, FacesContext.getCurrentInstance());
    }
    
    /**
     * @return the component type of the tree component.
     */
    public String getComponentType() {
        return COMPONENT_TYPE;
    }
    
    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }
    
    @Override
    public String getRendererType() {
        return DEFAULT_RENDERER_TYPE;
    }

    public HelpMessage getMessageComponent() {
        return message;
    }

    public void setMessageComponent(HelpMessage msg) {
        this.message = msg;
    }

    /**
     * <p>Set the value of the <code>styleClass</code> property.</p>
     *
     * @param styleClass
     */
    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }
    
    /**
     * <p>Set the value of the <code>messageId</code> property.</p>
     *
     * @param messageId
     */
    public void setMessageId(String messageId) {
        String portletId = Helper.getThemeDisplay().getPortletDisplay().getRootPortletId();
        this.messageId = portletId + messageId;
    }
    
    @Override
    public boolean getRendersChildren() {
        return true;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getStyleClass() {
        return styleClass;
    }
    
    
}
