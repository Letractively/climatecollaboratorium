package com.ext.portlet.models.service;


/**
 * <a href="ModelCategoryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelCategoryLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelCategoryLocalService
 *
 */
public class ModelCategoryLocalServiceUtil {
    private static ModelCategoryLocalService _service;

    public static com.ext.portlet.models.model.ModelCategory addModelCategory(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.SystemException {
        return getService().addModelCategory(modelCategory);
    }

    public static com.ext.portlet.models.model.ModelCategory createModelCategory(
        java.lang.Long modelCategoryPK) {
        return getService().createModelCategory(modelCategoryPK);
    }

    public static void deleteModelCategory(java.lang.Long modelCategoryPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelCategory(modelCategoryPK);
    }

    public static void deleteModelCategory(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.SystemException {
        getService().deleteModelCategory(modelCategory);
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

    public static com.ext.portlet.models.model.ModelCategory getModelCategory(
        java.lang.Long modelCategoryPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelCategory(modelCategoryPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelCategory> getModelCategories(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelCategories(start, end);
    }

    public static int getModelCategoriesCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelCategoriesCount();
    }

    public static com.ext.portlet.models.model.ModelCategory updateModelCategory(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.SystemException {
        return getService().updateModelCategory(modelCategory);
    }

    public static com.ext.portlet.models.model.ModelCategory updateModelCategory(
        com.ext.portlet.models.model.ModelCategory modelCategory, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateModelCategory(modelCategory, merge);
    }

    public static com.ext.portlet.models.model.ModelCategory addCategory(
        java.lang.String name, java.lang.String description)
        throws com.liferay.portal.SystemException {
        return getService().addCategory(name, description);
    }

    public static ModelCategoryLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ModelCategoryLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelCategoryLocalService service) {
        _service = service;
    }
}
