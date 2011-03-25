package com.ext.portlet.models.service;


/**
 * <a href="ModelOutputChartOrderLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelOutputChartOrderLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelOutputChartOrderLocalService
 *
 */
public class ModelOutputChartOrderLocalServiceUtil {
    private static ModelOutputChartOrderLocalService _service;

    public static com.ext.portlet.models.model.ModelOutputChartOrder addModelOutputChartOrder(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.SystemException {
        return getService().addModelOutputChartOrder(modelOutputChartOrder);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder createModelOutputChartOrder(
        java.lang.Long modelOutputChartOrderPK) {
        return getService().createModelOutputChartOrder(modelOutputChartOrderPK);
    }

    public static void deleteModelOutputChartOrder(
        java.lang.Long modelOutputChartOrderPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelOutputChartOrder(modelOutputChartOrderPK);
    }

    public static void deleteModelOutputChartOrder(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.SystemException {
        getService().deleteModelOutputChartOrder(modelOutputChartOrder);
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

    public static com.ext.portlet.models.model.ModelOutputChartOrder getModelOutputChartOrder(
        java.lang.Long modelOutputChartOrderPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelOutputChartOrder(modelOutputChartOrderPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> getModelOutputChartOrders(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelOutputChartOrders(start, end);
    }

    public static int getModelOutputChartOrdersCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelOutputChartOrdersCount();
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder updateModelOutputChartOrder(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.SystemException {
        return getService().updateModelOutputChartOrder(modelOutputChartOrder);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder updateModelOutputChartOrder(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateModelOutputChartOrder(modelOutputChartOrder, merge);
    }

    public static com.ext.portlet.models.model.ModelOutputChartOrder getChartOrder(
        edu.mit.cci.simulation.client.Simulation sim, java.lang.String label)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.SystemException {
        return getService().getChartOrder(sim, label);
    }

    public static ModelOutputChartOrderLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ModelOutputChartOrderLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelOutputChartOrderLocalService service) {
        _service = service;
    }
}
