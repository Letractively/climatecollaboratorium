package com.ext.portlet.ontology.service.impl;

import com.ext.portlet.ontology.model.OntologySpace;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.base.OntologySpaceLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class OntologySpaceLocalServiceImpl
    extends OntologySpaceLocalServiceBaseImpl {
    
    public OntologySpace createSpace(String name, String description) throws SystemException  {
        OntologySpace space = createOntologySpace(CounterLocalServiceUtil.increment(OntologySpace.class.getName()));
        
        space.setName(name);
        space.setDescription(description);
        space.store();
        
        // create parent term for new space
        OntologyTerm t = OntologyTermLocalServiceUtil.createTerm(null, "all", space.getId(), null);
        
        return space;
    }
}
