package com.ext.portlet.ontology.service.base;

import com.ext.portlet.ontology.model.OntologyTermEntity;
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


public abstract class OntologyTermEntityLocalServiceBaseImpl
    implements OntologyTermEntityLocalService {
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

    public OntologyTermEntity addOntologyTermEntity(
        OntologyTermEntity ontologyTermEntity) throws SystemException {
        ontologyTermEntity.setNew(true);

        return ontologyTermEntityPersistence.update(ontologyTermEntity, false);
    }

    public OntologyTermEntity createOntologyTermEntity(Long id) {
        return ontologyTermEntityPersistence.create(id);
    }

    public void deleteOntologyTermEntity(Long id)
        throws PortalException, SystemException {
        ontologyTermEntityPersistence.remove(id);
    }

    public void deleteOntologyTermEntity(OntologyTermEntity ontologyTermEntity)
        throws SystemException {
        ontologyTermEntityPersistence.remove(ontologyTermEntity);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return ontologyTermEntityPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return ontologyTermEntityPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public OntologyTermEntity getOntologyTermEntity(Long id)
        throws PortalException, SystemException {
        return ontologyTermEntityPersistence.findByPrimaryKey(id);
    }

    public List<OntologyTermEntity> getOntologyTermEntities(int start, int end)
        throws SystemException {
        return ontologyTermEntityPersistence.findAll(start, end);
    }

    public int getOntologyTermEntitiesCount() throws SystemException {
        return ontologyTermEntityPersistence.countAll();
    }

    public OntologyTermEntity updateOntologyTermEntity(
        OntologyTermEntity ontologyTermEntity) throws SystemException {
        ontologyTermEntity.setNew(false);

        return ontologyTermEntityPersistence.update(ontologyTermEntity, true);
    }

    public OntologyTermEntity updateOntologyTermEntity(
        OntologyTermEntity ontologyTermEntity, boolean merge)
        throws SystemException {
        ontologyTermEntity.setNew(false);

        return ontologyTermEntityPersistence.update(ontologyTermEntity, merge);
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
