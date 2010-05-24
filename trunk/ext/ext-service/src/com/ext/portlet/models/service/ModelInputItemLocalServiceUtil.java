package com.ext.portlet.models.service;


/**
 * <a href="ModelInputItemLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelInputItemLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelInputItemLocalService
 *
 */
public class ModelInputItemLocalServiceUtil {
    private static ModelInputItemLocalService _service;

    public static com.ext.portlet.models.model.ModelInputItem addModelInputItem(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.SystemException {
        return getService().addModelInputItem(modelInputItem);
    }

    public static com.ext.portlet.models.model.ModelInputItem createModelInputItem(
        java.lang.Long modelInputItemPK) {
        return getService().createModelInputItem(modelInputItemPK);
    }

    public static void deleteModelInputItem(java.lang.Long modelInputItemPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelInputItem(modelInputItemPK);
    }

    public static void deleteModelInputItem(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.SystemException {
        getService().deleteModelInputItem(modelInputItem);
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

    public static com.ext.portlet.models.model.ModelInputItem getModelInputItem(
        java.lang.Long modelInputItemPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelInputItem(modelInputItemPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> getModelInputItems(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelInputItems(start, end);
    }

    public static int getModelInputItemsCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelInputItemsCount();
    }

    public static com.ext.portlet.models.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.SystemException {
        return getService().updateModelInputItem(modelInputItem);
    }

    public static com.ext.portlet.models.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.models.model.ModelInputItem modelInputItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateModelInputItem(modelInputItem, merge);
    }

    public static ModelInputItemLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ModelInputItemLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelInputItemLocalService service) {
        _service = service;
    }
}
