package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateCategoryMapLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateCategoryMapLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateCategoryMapLocalService
 *
 */
public class DebateCategoryMapLocalServiceUtil {
    private static DebateCategoryMapLocalService _service;

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap addDebateCategoryMap(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap)
        throws com.liferay.portal.SystemException {
        return getService().addDebateCategoryMap(debateCategoryMap);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap createDebateCategoryMap(
        java.lang.Long debateCategoryMapPK) {
        return getService().createDebateCategoryMap(debateCategoryMapPK);
    }

    public static void deleteDebateCategoryMap(
        java.lang.Long debateCategoryMapPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateCategoryMap(debateCategoryMapPK);
    }

    public static void deleteDebateCategoryMap(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateCategoryMap(debateCategoryMap);
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

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap getDebateCategoryMap(
        java.lang.Long debateCategoryMapPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateCategoryMap(debateCategoryMapPK);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateCategoryMap> getDebateCategoryMaps(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateCategoryMaps(start, end);
    }

    public static int getDebateCategoryMapsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateCategoryMapsCount();
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap updateDebateCategoryMap(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateCategoryMap(debateCategoryMap);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap updateDebateCategoryMap(
        com.ext.portlet.debaterevision.model.DebateCategoryMap debateCategoryMap,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateCategoryMap(debateCategoryMap, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategoryMap getMapping(
        java.lang.Long categoryPK, java.lang.Long debateId)
        throws com.liferay.portal.SystemException {
        return getService().getMapping(categoryPK, debateId);
    }

    public static DebateCategoryMapLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateCategoryMapLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateCategoryMapLocalService service) {
        _service = service;
    }
}
