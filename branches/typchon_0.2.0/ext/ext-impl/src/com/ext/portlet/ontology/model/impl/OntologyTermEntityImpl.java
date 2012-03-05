package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.OntologyTermEntity;
import com.ext.portlet.ontology.service.OntologyTermEntityLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class OntologyTermEntityImpl extends OntologyTermEntityModelImpl
    implements OntologyTermEntity {
    public OntologyTermEntityImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            if (getId() == null) {
                setId(CounterLocalServiceUtil.increment(OntologyTermEntity.class.getName()));
            }
            OntologyTermEntityLocalServiceUtil.addOntologyTermEntity(this);
        }
        else {
            OntologyTermEntityLocalServiceUtil.updateOntologyTermEntity(this);
        }
    }
    
    public void remove() throws SystemException {
        OntologyTermEntityLocalServiceUtil.deleteOntologyTermEntity(this);
    }
}
