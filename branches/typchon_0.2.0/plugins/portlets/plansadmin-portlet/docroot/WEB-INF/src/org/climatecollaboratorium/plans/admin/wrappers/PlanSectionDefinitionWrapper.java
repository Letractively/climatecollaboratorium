package org.climatecollaboratorium.plans.admin.wrappers;

import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.liferay.portal.SystemException;

public class PlanSectionDefinitionWrapper {

    private PlanSectionDefinition definition;

    public PlanSectionDefinitionWrapper(PlanSectionDefinition definition) {
        this.definition = definition;
        
        
        
    }

    public void setDefinition(PlanSectionDefinition definition) {
        this.definition = definition;
    }

    public PlanSectionDefinition getDefinition() {
        return definition;
    }
    
    public String save() throws SystemException {
        definition.store();
        
        return "backToIndex";
    }
    
    
}
