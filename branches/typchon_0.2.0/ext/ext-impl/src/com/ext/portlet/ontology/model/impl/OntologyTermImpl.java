package com.ext.portlet.ontology.model.impl;

import java.util.List;

import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class OntologyTermImpl extends OntologyTermModelImpl
    implements OntologyTerm {
    public OntologyTermImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            OntologyTermLocalServiceUtil.addOntologyTerm(this);
        }
        else {
            OntologyTermLocalServiceUtil.updateOntologyTerm(this);
        }
    }
    
    public OntologyTerm getParent() throws PortalException, SystemException {
        if (getParentId() != null) {
            return OntologyTermLocalServiceUtil.getOntologyTerm(getParentId());
        }
        
        return null;
    }
    
    public int getChildTermsCount() throws SystemException {
        return OntologyTermLocalServiceUtil.countChildTerms(getId());
        
    }
    
    public List<OntologyTerm> getChildTerms() throws SystemException {
        return OntologyTermLocalServiceUtil.findByParentId(getId());
        
    }
}
