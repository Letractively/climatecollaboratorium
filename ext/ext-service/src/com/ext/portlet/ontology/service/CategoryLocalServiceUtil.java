package com.ext.portlet.ontology.service;


/**
 * <a href="CategoryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.ontology.service.CategoryLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.CategoryLocalService
 *
 */
public class CategoryLocalServiceUtil {
    private static CategoryLocalService _service;

    public static com.ext.portlet.ontology.model.Category addCategory(
        com.ext.portlet.ontology.model.Category category)
        throws com.liferay.portal.SystemException {
        return getService().addCategory(category);
    }

    public static com.ext.portlet.ontology.model.Category createCategory(
        java.lang.Long id) {
        return getService().createCategory(id);
    }

    public static void deleteCategory(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteCategory(id);
    }

    public static void deleteCategory(
        com.ext.portlet.ontology.model.Category category)
        throws com.liferay.portal.SystemException {
        getService().deleteCategory(category);
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

    public static com.ext.portlet.ontology.model.Category getCategory(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getCategory(id);
    }

    public static java.util.List<com.ext.portlet.ontology.model.Category> getCategories(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getCategories(start, end);
    }

    public static int getCategoriesCount()
        throws com.liferay.portal.SystemException {
        return getService().getCategoriesCount();
    }

    public static com.ext.portlet.ontology.model.Category updateCategory(
        com.ext.portlet.ontology.model.Category category)
        throws com.liferay.portal.SystemException {
        return getService().updateCategory(category);
    }

    public static com.ext.portlet.ontology.model.Category updateCategory(
        com.ext.portlet.ontology.model.Category category, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateCategory(category, merge);
    }

    public static CategoryLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("CategoryLocalService is not set");
        }

        return _service;
    }

    public void setService(CategoryLocalService service) {
        _service = service;
    }
}
