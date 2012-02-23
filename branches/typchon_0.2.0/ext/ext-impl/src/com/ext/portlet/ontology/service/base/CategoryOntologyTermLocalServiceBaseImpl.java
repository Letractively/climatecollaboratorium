package com.ext.portlet.ontology.service.base;

import com.ext.portlet.ontology.model.CategoryOntologyTerm;
import com.ext.portlet.ontology.service.CategoryLocalService;
import com.ext.portlet.ontology.service.CategoryOntologyTermLocalService;
import com.ext.portlet.ontology.service.CategoryOntologyTermService;
import com.ext.portlet.ontology.service.CategoryService;
import com.ext.portlet.ontology.service.OntologyTermEntityLocalService;
import com.ext.portlet.ontology.service.OntologyTermEntityService;
import com.ext.portlet.ontology.service.OntologyTermLocalService;
import com.ext.portlet.ontology.service.OntologyTermService;
import com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPK;
import com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence;
import com.ext.portlet.ontology.service.persistence.CategoryPersistence;
import com.ext.portlet.ontology.service.persistence.OntologyTermEntityPersistence;
import com.ext.portlet.ontology.service.persistence.OntologyTermPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class CategoryOntologyTermLocalServiceBaseImpl
    implements CategoryOntologyTermLocalService {
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
    @BeanReference(name = "com.ext.portlet.ontology.service.CategoryLocalService.impl")
    protected CategoryLocalService categoryLocalService;
    @BeanReference(name = "com.ext.portlet.ontology.service.CategoryService.impl")
    protected CategoryService categoryService;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryPersistence.impl")
    protected CategoryPersistence categoryPersistence;
    @BeanReference(name = "com.ext.portlet.ontology.service.CategoryOntologyTermLocalService.impl")
    protected CategoryOntologyTermLocalService categoryOntologyTermLocalService;
    @BeanReference(name = "com.ext.portlet.ontology.service.CategoryOntologyTermService.impl")
    protected CategoryOntologyTermService categoryOntologyTermService;
    @BeanReference(name = "com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPersistence.impl")
    protected CategoryOntologyTermPersistence categoryOntologyTermPersistence;

    public CategoryOntologyTerm addCategoryOntologyTerm(
        CategoryOntologyTerm categoryOntologyTerm) throws SystemException {
        categoryOntologyTerm.setNew(true);

        return categoryOntologyTermPersistence.update(categoryOntologyTerm,
            false);
    }

    public CategoryOntologyTerm createCategoryOntologyTerm(
        CategoryOntologyTermPK categoryOntologyTermPK) {
        return categoryOntologyTermPersistence.create(categoryOntologyTermPK);
    }

    public void deleteCategoryOntologyTerm(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws PortalException, SystemException {
        categoryOntologyTermPersistence.remove(categoryOntologyTermPK);
    }

    public void deleteCategoryOntologyTerm(
        CategoryOntologyTerm categoryOntologyTerm) throws SystemException {
        categoryOntologyTermPersistence.remove(categoryOntologyTerm);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return categoryOntologyTermPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return categoryOntologyTermPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public CategoryOntologyTerm getCategoryOntologyTerm(
        CategoryOntologyTermPK categoryOntologyTermPK)
        throws PortalException, SystemException {
        return categoryOntologyTermPersistence.findByPrimaryKey(categoryOntologyTermPK);
    }

    public List<CategoryOntologyTerm> getCategoryOntologyTerms(int start,
        int end) throws SystemException {
        return categoryOntologyTermPersistence.findAll(start, end);
    }

    public int getCategoryOntologyTermsCount() throws SystemException {
        return categoryOntologyTermPersistence.countAll();
    }

    public CategoryOntologyTerm updateCategoryOntologyTerm(
        CategoryOntologyTerm categoryOntologyTerm) throws SystemException {
        categoryOntologyTerm.setNew(false);

        return categoryOntologyTermPersistence.update(categoryOntologyTerm, true);
    }

    public CategoryOntologyTerm updateCategoryOntologyTerm(
        CategoryOntologyTerm categoryOntologyTerm, boolean merge)
        throws SystemException {
        categoryOntologyTerm.setNew(false);

        return categoryOntologyTermPersistence.update(categoryOntologyTerm,
            merge);
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

    public CategoryLocalService getCategoryLocalService() {
        return categoryLocalService;
    }

    public void setCategoryLocalService(
        CategoryLocalService categoryLocalService) {
        this.categoryLocalService = categoryLocalService;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public CategoryPersistence getCategoryPersistence() {
        return categoryPersistence;
    }

    public void setCategoryPersistence(CategoryPersistence categoryPersistence) {
        this.categoryPersistence = categoryPersistence;
    }

    public CategoryOntologyTermLocalService getCategoryOntologyTermLocalService() {
        return categoryOntologyTermLocalService;
    }

    public void setCategoryOntologyTermLocalService(
        CategoryOntologyTermLocalService categoryOntologyTermLocalService) {
        this.categoryOntologyTermLocalService = categoryOntologyTermLocalService;
    }

    public CategoryOntologyTermService getCategoryOntologyTermService() {
        return categoryOntologyTermService;
    }

    public void setCategoryOntologyTermService(
        CategoryOntologyTermService categoryOntologyTermService) {
        this.categoryOntologyTermService = categoryOntologyTermService;
    }

    public CategoryOntologyTermPersistence getCategoryOntologyTermPersistence() {
        return categoryOntologyTermPersistence;
    }

    public void setCategoryOntologyTermPersistence(
        CategoryOntologyTermPersistence categoryOntologyTermPersistence) {
        this.categoryOntologyTermPersistence = categoryOntologyTermPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
