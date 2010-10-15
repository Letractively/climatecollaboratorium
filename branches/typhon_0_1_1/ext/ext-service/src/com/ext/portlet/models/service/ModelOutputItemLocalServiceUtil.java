package com.ext.portlet.models.service;


/**
 * <a href="ModelOutputItemLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelOutputItemLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelOutputItemLocalService
 *
 */
public class ModelOutputItemLocalServiceUtil {
    private static ModelOutputItemLocalService _service;

    public static com.ext.portlet.models.model.ModelOutputItem addModelOutputItem(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.SystemException {
        return getService().addModelOutputItem(modelOutputItem);
    }

    public static com.ext.portlet.models.model.ModelOutputItem createModelOutputItem(
        java.lang.Long modelOutputItemModifierPK) {
        return getService().createModelOutputItem(modelOutputItemModifierPK);
    }

    public static void deleteModelOutputItem(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelOutputItem(modelOutputItemModifierPK);
    }

    public static void deleteModelOutputItem(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.SystemException {
        getService().deleteModelOutputItem(modelOutputItem);
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

    public static com.ext.portlet.models.model.ModelOutputItem getModelOutputItem(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelOutputItem(modelOutputItemModifierPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItem> getModelOutputItems(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelOutputItems(start, end);
    }

    public static int getModelOutputItemsCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelOutputItemsCount();
    }

    public static com.ext.portlet.models.model.ModelOutputItem updateModelOutputItem(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.SystemException {
        return getService().updateModelOutputItem(modelOutputItem);
    }

    public static com.ext.portlet.models.model.ModelOutputItem updateModelOutputItem(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateModelOutputItem(modelOutputItem, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputItem getOutputItem(
        mit.simulation.climate.client.MetaData md)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.SystemException {
        return getService().getOutputItem(md);
    }

    public static ModelOutputItemLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ModelOutputItemLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelOutputItemLocalService service) {
        _service = service;
    }
}
