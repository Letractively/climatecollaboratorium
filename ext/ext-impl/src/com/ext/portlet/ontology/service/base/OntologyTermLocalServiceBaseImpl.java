package com.ext.portlet.ontology.service.base;

import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologyTermEntityLocalService;
import com.ext.portlet.ontology.service.OntologyTermEntityService;
import com.ext.portlet.ontology.service.OntologyTermLocalService;
import com.ext.portlet.ontology.service.OntologyTermService;
import com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence;
import com.ext.portlet.ontology.service.persistence.OntologyTermPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class OntologyTermLocalServiceBaseImpl
    implements OntologyTermLocalService {
    @BeanReference(name = "com.ext.portlet.ontology.service.OntologyTermLocalService.impl")
    protected OntologyTermLocalService ontologyTermLocalService;
    @BeanReference(name = "com.ext.portlet.ontology.service.OntologyTermService.impl")
    protected OntologyTermService ontologyTermService;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermPersistence.impl")
    protected OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.OntologyTermEntityLocalService.impl")
    protected OntologyTermEntityLocalService ontologyTermEntityLocalService;
    @BeanReference(name = "com.ext.portlet.ontology.service.OntologyTermEntityService.impl")
    protected OntologyTermEntityService ontologyTermEntityService;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence.impl")
    protected OntologyTermEntityPersistence ontologyTermEntityPersistence;

    public OntologyTerm addOntologyTerm(OntologyTerm ontologyTerm)
        throws SystemException {
        ontologyTerm.setNew(true);

        return ontologyTermPersistence.update(ontologyTerm, false);
    }

    public OntologyTerm createOntologyTerm(Long id) {
        return ontologyTermPersistence.create(id);
    }

    public void deleteOntologyTerm(Long id)
        throws PortalException, SystemException {
        ontologyTermPersistence.remove(id);
    }

    public void deleteOntologyTerm(OntologyTerm ontologyTerm)
        throws SystemException {
        ontologyTermPersistence.remove(ontologyTerm);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return ontologyTermPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return ontologyTermPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public OntologyTerm getOntologyTerm(Long id)
        throws PortalException, SystemException {
        return ontologyTermPersistence.findByPrimaryKey(id);
    }

    public List<OntologyTerm> getOntologyTerms(int start, int end)
        throws SystemException {
        return ontologyTermPersistence.findAll(start, end);
    }

    public int getOntologyTermsCount() throws SystemException {
        return ontologyTermPersistence.countAll();
    }

    public OntologyTerm updateOntologyTerm(OntologyTerm ontologyTerm)
        throws SystemException {
        ontologyTerm.setNew(false);

        return ontologyTermPersistence.update(ontologyTerm, true);
    }

    public OntologyTerm updateOntologyTerm(OntologyTerm ontologyTerm,
        boolean merge) throws SystemException {
        ontologyTerm.setNew(false);

        return ontologyTermPersistence.update(ontologyTerm, merge);
    }

    public OntologyTermLocalService getOntologyTermLocalService() {
        return ontologyTermLocalService;
    }

    public void setOntologyTermLocalService(
        OntologyTermLocalService ontologyTermLocalService) {
        this.ontologyTermLocalService = ontologyTermLocalService;
    }

    public OntologyTermService getOntologyTermService() {
        return ontologyTermService;
    }

    public void setOntologyTermService(OntologyTermService ontologyTermService) {
        this.ontologyTermService = ontologyTermService;
    }

    public OntologyTermPersistence getOntologyTermPersistence() {
        return ontologyTermPersistence;
    }

    public void setOntologyTermPersistence(
        OntologyTermPersistence ontologyTermPersistence) {
        this.ontologyTermPersistence = ontologyTermPersistence;
    }

    public OntologyTermEntityLocalService getOntologyTermEntityLocalService() {
        return ontologyTermEntityLocalService;
    }

    public void setOntologyTermEntityLocalService(
        OntologyTermEntityLocalService ontologyTermEntityLocalService) {
        this.ontologyTermEntityLocalService = ontologyTermEntityLocalService;
    }

    public OntologyTermEntityService getOntologyTermEntityService() {
        return ontologyTermEntityService;
    }

    public void setOntologyTermEntityService(
        OntologyTermEntityService ontologyTermEntityService) {
        this.ontologyTermEntityService = ontologyTermEntityService;
    }

    public OntologyTermEntityPersistence getOntologyTermEntityPersistence() {
        return ontologyTermEntityPersistence;
    }

    public void setOntologyTermEntityPersistence(
        OntologyTermEntityPersistence ontologyTermEntityPersistence) {
        this.ontologyTermEntityPersistence = ontologyTermEntityPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
