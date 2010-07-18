package com.ext.portlet.models.service;


/**
 * <a href="ModelOutputItemOrderLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelOutputItemOrderLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelOutputItemOrderLocalService
 *
 */
public class ModelOutputItemOrderLocalServiceUtil {
    private static ModelOutputItemOrderLocalService _service;

    public static com.ext.portlet.models.model.ModelOutputItemOrder addModelOutputItemOrder(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder)
        throws com.liferay.portal.SystemException {
        return getService().addModelOutputItemOrder(modelOutputItemOrder);
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder createModelOutputItemOrder(
        java.lang.Long modelOutputItemModifierPK) {
        return getService().createModelOutputItemOrder(modelOutputItemModifierPK);
    }

    public static void deleteModelOutputItemOrder(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelOutputItemOrder(modelOutputItemModifierPK);
    }

    public static void deleteModelOutputItemOrder(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder)
        throws com.liferay.portal.SystemException {
        getService().deleteModelOutputItemOrder(modelOutputItemOrder);
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

    public static com.ext.portlet.models.model.ModelOutputItemOrder getModelOutputItemOrder(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelOutputItemOrder(modelOutputItemModifierPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItemOrder> getModelOutputItemOrders(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelOutputItemOrders(start, end);
    }

    public static int getModelOutputItemOrdersCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelOutputItemOrdersCount();
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder updateModelOutputItemOrder(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder)
        throws com.liferay.portal.SystemException {
        return getService().updateModelOutputItemOrder(modelOutputItemOrder);
    }

    public static com.ext.portlet.models.model.ModelOutputItemOrder updateModelOutputItemOrder(
        com.ext.portlet.models.model.ModelOutputItemOrder modelOutputItemOrder,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateModelOutputItemOrder(modelOutputItemOrder, merge);
    }

    public static ModelOutputItemOrderLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ModelOutputItemOrderLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelOutputItemOrderLocalService service) {
        _service = service;
    }
}
