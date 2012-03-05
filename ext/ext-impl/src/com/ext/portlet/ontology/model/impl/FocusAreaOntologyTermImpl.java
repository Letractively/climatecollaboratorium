package com.ext.portlet.ontology.model.impl;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class FocusAreaOntologyTermImpl extends FocusAreaOntologyTermModelImpl
    implements FocusAreaOntologyTerm {
    public FocusAreaOntologyTermImpl() {
    }
    
    public void store() throws SystemException {
        if (isNew()) {
            FocusAreaOntologyTermLocalServiceUtil.addFocusAreaOntologyTerm(this);
        }
        else {
            FocusAreaOntologyTermLocalServiceUtil.updateFocusAreaOntologyTerm(this);
        }
    }
    
    public OntologyTerm getTerm() throws PortalException, SystemException {
        return OntologyTermLocalServiceUtil.getOntologyTerm(getOntologyTermId());
    }
    
    public FocusArea getArea() throws PortalException, SystemException {
        return FocusAreaLocalServiceUtil.getFocusArea(getFocusAreaId());
    }
}
