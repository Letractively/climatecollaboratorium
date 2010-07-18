package org.climatecollaboratorium.debates;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.icesoft.faces.context.effects.JavascriptContext;

public class KeepAlivePhaseListener implements PhaseListener {

     /** script to keep the liferay session alive while only ajax requests are being performed
      */
     private final static String KEEP_LIFERAY_ALIVE_SCRIPT =
             "if (Liferay.Session._stateCheck) {\n" +
             "   window.clearTimeout(Liferay.Session._stateCheck);\n" +
             "   Liferay.Session._stateCheck = null;\n" +
             "}\n" +
             "Liferay.Session.init({\n" +
             "   autoExtend: false,\n" +
             "   timeout: Liferay.Session._timeout,\n" +
             "   timeoutWarning:  Liferay.Session._warning\n" +
             "});\n" +
             "jQuery.ajax( " +
             "   {\n" +
             "       url: Liferay.Session._sessionUrls.extend\n" +
             "   }\n" +
             ");\n";


     public void afterPhase(PhaseEvent event) {
                 JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), KEEP_LIFERAY_ALIVE_SCRIPT);
     }

     public void beforePhase(PhaseEvent event) {
         //not implemented
     }

     public PhaseId getPhaseId() {
         return PhaseId.RENDER_RESPONSE;
     }
 }
