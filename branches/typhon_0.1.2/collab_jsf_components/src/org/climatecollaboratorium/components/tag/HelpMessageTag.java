package org.climatecollaboratorium.components.tag;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.webapp.UIComponentTag;

import com.icesoft.util.pooling.ELPool;

public class HelpMessageTag extends UIComponentTag {
    public static final String COMPONENT_TYPE = "org.climatecollaboratorium.faces.HelpMessage";
    public static final String DEFAULT_RENDERER_TYPE = "org.climatecollaboratorium.faces.HelpMessageRenderer";
    public static final String COMPONENT_FAMILY = "org.climatecollaboratorium.faces.HelpMessageFamily";

    /**
     * <p>Return the requested component type.</p>
     */
    public String getComponentType() {
        return COMPONENT_TYPE;
    }

    /**
     * <p>Return the requested renderer type.</p>
     */
    public String getRendererType() {
        return DEFAULT_RENDERER_TYPE;
    }

    /**
     * <p>Release any allocated tag handler attributes.</p>
     */
    public void release() {
        super.release();
    }

    /**
     * <p>Transfer tag attributes to component properties.</p>
     */
    protected void setProperties(UIComponent component) {
        System.out.println("set property!!!!!******************************************************");
        try{
            System.out.println("mam set properties");
            super.setProperties(component);

            System.out.println("mam set properties");
            if (messageId != null) {
                System.out.println("mam set properties messageId");
                if (isValueReference(messageId))
                {
                  FacesContext context = FacesContext.getCurrentInstance();
                  MethodBinding mb = getFacesContext().getApplication().createMethodBinding(ELPool.get(messageId), actionArgs);
                  component.getAttributes().put("messageId", mb);             
                }
                else 
                  component.getAttributes().put("messageId", messageId);
              }
            if (defaultState != null) {
                System.out.println("mam set properties defaultState");
                if (isValueReference(defaultState))
                {
                  FacesContext context = FacesContext.getCurrentInstance();
                  MethodBinding mb = getFacesContext().getApplication().createMethodBinding(ELPool.get(defaultState), actionArgs);
                  component.getAttributes().put("defaultState", mb);
                }
                else
                  component.getAttributes().put("defaultState", defaultState);
              }
        }
        catch(Exception e1) {
            e1.printStackTrace();throw new RuntimeException(e1);
        }
    }

    private static Class actionArgs[] = new Class[0];
    private static Class actionListenerArgs[] = { javax.faces.event.ActionEvent.class };
    private static Class validatorArgs[] = { FacesContext.class, UIComponent.class, Object.class };
    private static Class valueChangeListenerArgs[] = { javax.faces.event.ValueChangeEvent.class };


    // 
    // Methods From TagSupport
    // 
    
    private String messageId = null;



    private String defaultState = "open";

    public void setMessageId(String messageId) {
        System.out.println("messageId: " + messageId);
        this.messageId = messageId;
    }

    public void setDefaultState(String state) {
        System.out.println("default state : " + state);
       this.defaultState = "closed".equals(state)?"closed":"open";
    }

    


}
