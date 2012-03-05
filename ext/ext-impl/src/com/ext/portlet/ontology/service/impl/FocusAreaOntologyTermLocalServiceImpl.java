package com.ext.portlet.ontology.service.impl;

import java.util.List;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.ontology.service.base.FocusAreaOntologyTermLocalServiceBaseImpl;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;


public class FocusAreaOntologyTermLocalServiceImpl
    extends FocusAreaOntologyTermLocalServiceBaseImpl {
    
    public List<FocusAreaOntologyTerm> findTermsByFocusArea(Long focusAreaId) throws SystemException {
        return focusAreaOntologyTermPersistence.findByFocusAreaId(focusAreaId);
    }
    
    public void addAreaTerm(Long focusAreaId, Long termId) throws PortalException, SystemException {
        FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(focusAreaId);
        fa.addTerm(termId);
    }
    
    public void removeAreaTerm(Long focusAreaId, Long termId) throws PortalException, SystemException {
        FocusArea fa = FocusAreaLocalServiceUtil.getFocusArea(focusAreaId);
        fa.removeTerm(termId);
        
    }
    
}
