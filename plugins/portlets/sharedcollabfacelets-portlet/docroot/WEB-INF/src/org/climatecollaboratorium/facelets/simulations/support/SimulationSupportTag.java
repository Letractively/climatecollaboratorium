package org.climatecollaboratorium.facelets.simulations.support;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import org.climatecollaboratorium.facelets.simulations.SimulationBean;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.FaceletException;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class SimulationSupportTag extends TagHandler{
    private final TagAttribute simulationBeanParam;
    private final TagAttribute simulationIdParam;
    private final TagAttribute scenarioIdParam;
    
    private static Log _log = LogFactoryUtil.getLog(SimulationSupportTag.class);

    public SimulationSupportTag(TagConfig config) {
        super(config);
        simulationBeanParam = this.getRequiredAttribute("simulationBean");
        simulationIdParam = this.getAttribute("simulationId");
        scenarioIdParam = this.getAttribute("scenarioId");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException,
            ELException {
        
        SimulationBean simulationBean = (SimulationBean) simulationBeanParam.getObject(ctx);
        
        Long scenarioId = (Long) scenarioIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        Long simulationId = (Long) simulationIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        try {
            simulationBean.init(simulationId, scenarioId);
        } catch (Exception e) {
            _log.error("Can't initialize simulationBean with parameters - simulationId " + 
                    simulationId + "\tscenarioId: " + scenarioId, e);
        }
        nextHandler.apply(ctx, parent);
    }
    
    

}
