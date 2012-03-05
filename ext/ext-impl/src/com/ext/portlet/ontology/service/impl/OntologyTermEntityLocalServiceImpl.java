package com.ext.portlet.ontology.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ext.portlet.ontology.model.OntologyTermEntity;
import com.ext.portlet.ontology.service.base.OntologyTermEntityLocalServiceBaseImpl;
import com.liferay.portal.SystemException;
import com.liferay.portal.service.ClassNameLocalServiceUtil;


public class OntologyTermEntityLocalServiceImpl
    extends OntologyTermEntityLocalServiceBaseImpl {
    
    public List<Long> findTagedIdsForClass(Long termId, Class clasz) throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        
        for (OntologyTermEntity ote: ontologyTermEntityPersistence.findByTermIdClassNameId(termId, ClassNameLocalServiceUtil.getClassNameId(clasz))) {
            ret.add(ote.getClassPK());
        }
        
        return ret;
    }
}
