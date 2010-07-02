/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import com.ext.portlet.plans.TypedValueConverter;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.PlanAttribute;


public class PlanAttributeImpl extends PlanAttributeModelImpl
    implements PlanAttribute {
    
    private Object typedValue;
    
    public PlanAttributeImpl() {
    }
    
    public Object getTypedValue() {
        if (typedValue != null) {
            return typedValue;
        }
        Attribute attribute = Attribute.valueOf(getAttributeName());
        typedValue = TypedValueConverter.getValue(attribute.getAttributeClass(), getAttributeValue());
        return typedValue;
    }
    
    @Override
    public void setAttributeValue(String attributeValue) {
        typedValue = null;
        super.setAttributeValue(attributeValue);
    }

}
