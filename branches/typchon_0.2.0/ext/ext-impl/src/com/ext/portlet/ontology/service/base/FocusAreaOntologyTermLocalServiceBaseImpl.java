package com.ext.portlet.ontology.service.base;

import com.ext.portlet.ontology.model.FocusAreaOntologyTerm;
import com.ext.portlet.ontology.service.FocusAreaLocalService;
import com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalService;
import com.ext.portlet.ontology.service.FocusAreaOntologyTermService;
import com.ext.portlet.ontology.service.FocusAreaService;
import com.ext.portlet.ontology.service.OntologySpaceLocalService;
import com.ext.portlet.ontology.service.OntologySpaceService;
import com.ext.portlet.ontology.service.OntologyTermEntityLocalService;
import com.ext.portlet.ontology.service.OntologyTermEntityService;
import com.ext.portlet.ontology.service.OntologyTermLocalService;
import com.ext.portlet.ontology.service.OntologyTermService;
import com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK;
import com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPersistence;
import com.ext.portlet.ontology.service.persistence.FocusAreaPersistence;
import com.ext.portlet.ontology.service.persistence.OntologySpacePersistence;
import com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence;
import com.ext.portlet.ontology.service.persistence.OntologyTermPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class FocusAreaOntologyTermLocalServiceBaseImpl
    implements FocusAreaOntologyTermLocalService {
    @BeanReference(name = "com.ext.portlet.ontology.service.OntologySpaceLocalService.impl")
    protected OntologySpaceLocalService ontologySpaceLocalService;
    @BeanReference(name = "com.ext.portlet.ontology.service.OntologySpaceService.impl")
    protected OntologySpaceService ontologySpaceService;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.OntologySpacePersistence.impl")
    protected OntologySpacePersistence ontologySpacePersistence;
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
    @BeanReference(name = "com.ext.portlet.ontology.service.FocusAreaLocalService.impl")
    protected FocusAreaLocalService focusAreaLocalService;
    @BeanReference(name = "com.ext.portlet.ontology.service.FocusAreaService.impl")
    protected FocusAreaService focusAreaService;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.FocusAreaPersistence.impl")
    protected FocusAreaPersistence focusAreaPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.FocusAreaOntologyTermLocalService.impl")
    protected FocusAreaOntologyTermLocalService focusAreaOntologyTermLocalService;
    @BeanReference(name = "com.ext.portlet.ontology.service.FocusAreaOntologyTermService.impl")
    protected FocusAreaOntologyTermService focusAreaOntologyTermService;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPersistence.impl")
    protected FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence;

    public FocusAreaOntologyTerm addFocusAreaOntologyTerm(
        FocusAreaOntologyTerm focusAreaOntologyTerm) throws SystemException {
        focusAreaOntologyTerm.setNew(true);

        return focusAreaOntologyTermPersistence.update(focusAreaOntologyTerm,
            false);
    }

    public FocusAreaOntologyTerm createFocusAreaOntologyTerm(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK) {
        return focusAreaOntologyTermPersistence.create(focusAreaOntologyTermPK);
    }

    public void deleteFocusAreaOntologyTerm(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws PortalException, SystemException {
        focusAreaOntologyTermPersistence.remove(focusAreaOntologyTermPK);
    }

    public void deleteFocusAreaOntologyTerm(
        FocusAreaOntologyTerm focusAreaOntologyTerm) throws SystemException {
        focusAreaOntologyTermPersistence.remove(focusAreaOntologyTerm);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return focusAreaOntologyTermPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return focusAreaOntologyTermPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public FocusAreaOntologyTerm getFocusAreaOntologyTerm(
        FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws PortalException, SystemException {
        return focusAreaOntologyTermPersistence.findByPrimaryKey(focusAreaOntologyTermPK);
    }

    public List<FocusAreaOntologyTerm> getFocusAreaOntologyTerms(int start,
        int end) throws SystemException {
        return focusAreaOntologyTermPersistence.findAll(start, end);
    }

    public int getFocusAreaOntologyTermsCount() throws SystemException {
        return focusAreaOntologyTermPersistence.countAll();
    }

    public FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        FocusAreaOntologyTerm focusAreaOntologyTerm) throws SystemException {
        focusAreaOntologyTerm.setNew(false);

        return focusAreaOntologyTermPersistence.update(focusAreaOntologyTerm,
            true);
    }

    public FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        FocusAreaOntologyTerm focusAreaOntologyTerm, boolean merge)
        throws SystemException {
        focusAreaOntologyTerm.setNew(false);

        return focusAreaOntologyTermPersistence.update(focusAreaOntologyTerm,
            merge);
    }

    public OntologySpaceLocalService getOntologySpaceLocalService() {
        return ontologySpaceLocalService;
    }

    public void setOntologySpaceLocalService(
        OntologySpaceLocalService ontologySpaceLocalService) {
        this.ontologySpaceLocalService = ontologySpaceLocalService;
    }

    public OntologySpaceService getOntologySpaceService() {
        return ontologySpaceService;
    }

    public void setOntologySpaceService(
        OntologySpaceService ontologySpaceService) {
        this.ontologySpaceService = ontologySpaceService;
    }

    public OntologySpacePersistence getOntologySpacePersistence() {
        return ontologySpacePersistence;
    }

    public void setOntologySpacePersistence(
        OntologySpacePersistence ontologySpacePersistence) {
        this.ontologySpacePersistence = ontologySpacePersistence;
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

    public FocusAreaLocalService getFocusAreaLocalService() {
        return focusAreaLocalService;
    }

    public void setFocusAreaLocalService(
        FocusAreaLocalService focusAreaLocalService) {
        this.focusAreaLocalService = focusAreaLocalService;
    }

    public FocusAreaService getFocusAreaService() {
        return focusAreaService;
    }

    public void setFocusAreaService(FocusAreaService focusAreaService) {
        this.focusAreaService = focusAreaService;
    }

    public FocusAreaPersistence getFocusAreaPersistence() {
        return focusAreaPersistence;
    }

    public void setFocusAreaPersistence(
        FocusAreaPersistence focusAreaPersistence) {
        this.focusAreaPersistence = focusAreaPersistence;
    }

    public FocusAreaOntologyTermLocalService getFocusAreaOntologyTermLocalService() {
        return focusAreaOntologyTermLocalService;
    }

    public void setFocusAreaOntologyTermLocalService(
        FocusAreaOntologyTermLocalService focusAreaOntologyTermLocalService) {
        this.focusAreaOntologyTermLocalService = focusAreaOntologyTermLocalService;
    }

    public FocusAreaOntologyTermService getFocusAreaOntologyTermService() {
        return focusAreaOntologyTermService;
    }

    public void setFocusAreaOntologyTermService(
        FocusAreaOntologyTermService focusAreaOntologyTermService) {
        this.focusAreaOntologyTermService = focusAreaOntologyTermService;
    }

    public FocusAreaOntologyTermPersistence getFocusAreaOntologyTermPersistence() {
        return focusAreaOntologyTermPersistence;
    }

    public void setFocusAreaOntologyTermPersistence(
        FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence) {
        this.focusAreaOntologyTermPersistence = focusAreaOntologyTermPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
