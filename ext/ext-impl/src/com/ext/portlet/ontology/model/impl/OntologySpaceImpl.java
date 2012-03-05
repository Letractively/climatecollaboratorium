package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.OntologySpace;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.SystemException;


public class OntologySpaceImpl extends OntologySpaceModelImpl
    implements OntologySpace {
    public OntologySpaceImpl() {
        
    }

    public void store() throws SystemException {
        if (isNew()) {
            OntologySpaceLocalServiceUtil.addOntologySpace(this);
        }
        else {
            OntologySpaceLocalServiceUtil.updateOntologySpace(this);
        }
    }
    
    public OntologyTerm getTopTerm() throws SystemException {
        return OntologyTermLocalServiceUtil.findByParentIdSpaceId(null, getId()).get(0);
    }
}
