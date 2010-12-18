package com.ext.portlet.plans.service;


/**
 * <a href="PlansFilterLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlansFilterLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlansFilterLocalService
 *
 */
public class PlansFilterLocalServiceUtil {
    private static PlansFilterLocalService _service;

    public static com.ext.portlet.plans.model.PlansFilter addPlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.SystemException {
        return getService().addPlansFilter(plansFilter);
    }

    public static com.ext.portlet.plans.model.PlansFilter createPlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK) {
        return getService().createPlansFilter(plansFilterPK);
    }

    public static void deletePlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlansFilter(plansFilterPK);
    }

    public static void deletePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.SystemException {
        getService().deletePlansFilter(plansFilter);
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

    public static com.ext.portlet.plans.model.PlansFilter getPlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlansFilter(plansFilterPK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlansFilter> getPlansFilters(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlansFilters(start, end);
    }

    public static int getPlansFiltersCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlansFiltersCount();
    }

    public static com.ext.portlet.plans.model.PlansFilter updatePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.SystemException {
        return getService().updatePlansFilter(plansFilter);
    }

    public static com.ext.portlet.plans.model.PlansFilter updatePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlansFilter(plansFilter, merge);
    }

    public static PlansFilterLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlansFilterLocalService is not set");
        }

        return _service;
    }

    public void setService(PlansFilterLocalService service) {
        _service = service;
    }
}
