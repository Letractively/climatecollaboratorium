package com.ext.portlet.ontology.service.impl;

import java.util.List;

import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.base.OntologyTermLocalServiceBaseImpl;
import com.ext.portlet.plans.model.PlanItem;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class OntologyTermLocalServiceImpl
    extends OntologyTermLocalServiceBaseImpl {
    
    public List<OntologyTerm> findByParentId(Long parentId) throws SystemException {
        return ontologyTermPersistence.findByParentId(parentId);
    }
    
    public OntologyTerm createTerm(Long parentId, String name) throws SystemException {
        Long termId = CounterLocalServiceUtil.increment(OntologyTerm.class.getName());
        
        OntologyTerm t = createOntologyTerm(termId);
        t.setName(name);
        t.setParentId(parentId);
        
        t.store();
        
        return t;
        
    }

    public int countChildTerms(Long parentId) throws SystemException {
        return ontologyTermPersistence.countByParentId(parentId);
    }
    
    
}
