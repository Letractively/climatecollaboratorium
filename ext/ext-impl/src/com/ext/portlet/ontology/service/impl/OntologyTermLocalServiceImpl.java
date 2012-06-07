package com.ext.portlet.ontology.service.impl;

import java.util.List;

import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.model.OntologyTermEntity;
import com.ext.portlet.ontology.service.base.OntologyTermLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;


public class OntologyTermLocalServiceImpl
    extends OntologyTermLocalServiceBaseImpl {
    
    public List<OntologyTerm> findByParentId(Long parentId) throws SystemException {
        return ontologyTermPersistence.findByParentId(parentId);
    }

    
    public List<OntologyTerm> findByParentIdSpaceId(Long parentId, Long spaceId) throws SystemException {
        return ontologyTermPersistence.findByParentIdSpaceId(parentId, spaceId);
    }
    
    
    public OntologyTerm createTerm(Long parentId, String name, Long spaceId, String descriptionUrl) throws SystemException {
        Long termId = CounterLocalServiceUtil.increment(OntologyTerm.class.getName());
        
        OntologyTerm t = createOntologyTerm(termId);
        t.setName(name);
        t.setParentId(parentId);
        t.setOntologySpaceId(spaceId);
        t.setDescriptionUrl(descriptionUrl);
        
        t.store();
        
        return t;
        
    }

    public int countChildTerms(Long parentId) throws SystemException {
        return ontologyTermPersistence.countByParentId(parentId);
    }
    
    public void clearClassTags(Class clasz, Long id) throws SystemException {
        
        Long classNameId = ClassNameLocalServiceUtil.getClassNameId(clasz);
        
        for (OntologyTermEntity ote: ontologyTermEntityPersistence.findByClassNameIdClassPk(classNameId, id)) {
            ote.remove();
        }
    }
}
