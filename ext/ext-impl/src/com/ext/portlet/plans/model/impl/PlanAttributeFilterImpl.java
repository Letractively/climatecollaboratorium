package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.TypedValueConverter;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.PlanAttributeFilter;


public class PlanAttributeFilterImpl extends PlanAttributeFilterModelImpl
    implements PlanAttributeFilter {

    private Object typedValue;
    
    public PlanAttributeFilterImpl() {
    }
    
    public Object getTypedValue() {
        if (typedValue != null) {
            return typedValue;
        }
        Attribute attribute = Attribute.valueOf(getAttributeName());
        typedValue = TypedValueConverter.getValue(attribute.getAttributeClass(), getStringVal());
        return typedValue;
    }
    
    @Override
    public void setStringVal(String stringVal) {
        typedValue = null;
        super.setStringVal(stringVal);
    }
}
