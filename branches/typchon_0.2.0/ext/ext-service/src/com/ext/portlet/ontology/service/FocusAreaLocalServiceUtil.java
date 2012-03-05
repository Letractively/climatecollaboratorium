package com.ext.portlet.ontology.service;


/**
 * <a href="FocusAreaLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.ontology.service.FocusAreaLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.FocusAreaLocalService
 *
 */
public class FocusAreaLocalServiceUtil {
    private static FocusAreaLocalService _service;

    public static com.ext.portlet.ontology.model.FocusArea addFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.SystemException {
        return getService().addFocusArea(focusArea);
    }

    public static com.ext.portlet.ontology.model.FocusArea createFocusArea(
        java.lang.Long id) {
        return getService().createFocusArea(id);
    }

    public static void deleteFocusArea(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteFocusArea(id);
    }

    public static void deleteFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.SystemException {
        getService().deleteFocusArea(focusArea);
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

    public static com.ext.portlet.ontology.model.FocusArea getFocusArea(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getFocusArea(id);
    }

    public static java.util.List<com.ext.portlet.ontology.model.FocusArea> getFocusAreas(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getFocusAreas(start, end);
    }

    public static int getFocusAreasCount()
        throws com.liferay.portal.SystemException {
        return getService().getFocusAreasCount();
    }

    public static com.ext.portlet.ontology.model.FocusArea updateFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.SystemException {
        return getService().updateFocusArea(focusArea);
    }

    public static com.ext.portlet.ontology.model.FocusArea updateFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateFocusArea(focusArea, merge);
    }

    public static FocusAreaLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("FocusAreaLocalService is not set");
        }

        return _service;
    }

    public void setService(FocusAreaLocalService service) {
        _service = service;
    }
}
