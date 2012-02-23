package com.ext.portlet.ontology.service;


/**
 * <a href="CategoryOntologyTermLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.ontology.service.CategoryOntologyTermLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.CategoryOntologyTermLocalService
 *
 */
public class CategoryOntologyTermLocalServiceUtil {
    private static CategoryOntologyTermLocalService _service;

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm addCategoryOntologyTerm(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm)
        throws com.liferay.portal.SystemException {
        return getService().addCategoryOntologyTerm(categoryOntologyTerm);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm createCategoryOntologyTerm(
        com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPK categoryOntologyTermPK) {
        return getService().createCategoryOntologyTerm(categoryOntologyTermPK);
    }

    public static void deleteCategoryOntologyTerm(
        com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPK categoryOntologyTermPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteCategoryOntologyTerm(categoryOntologyTermPK);
    }

    public static void deleteCategoryOntologyTerm(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm)
        throws com.liferay.portal.SystemException {
        getService().deleteCategoryOntologyTerm(categoryOntologyTerm);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm getCategoryOntologyTerm(
        com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPK categoryOntologyTermPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getCategoryOntologyTerm(categoryOntologyTermPK);
    }

    public static java.util.List<com.ext.portlet.ontology.model.CategoryOntologyTerm> getCategoryOntologyTerms(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getCategoryOntologyTerms(start, end);
    }

    public static int getCategoryOntologyTermsCount()
        throws com.liferay.portal.SystemException {
        return getService().getCategoryOntologyTermsCount();
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm updateCategoryOntologyTerm(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm)
        throws com.liferay.portal.SystemException {
        return getService().updateCategoryOntologyTerm(categoryOntologyTerm);
    }

    public static com.ext.portlet.ontology.model.CategoryOntologyTerm updateCategoryOntologyTerm(
        com.ext.portlet.ontology.model.CategoryOntologyTerm categoryOntologyTerm,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateCategoryOntologyTerm(categoryOntologyTerm, merge);
    }

    public static CategoryOntologyTermLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "CategoryOntologyTermLocalService is not set");
        }

        return _service;
    }

    public void setService(CategoryOntologyTermLocalService service) {
        _service = service;
    }
}
