package com.ext.portlet.models.service;


/**
 * <a href="ModelPositionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelPositionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelPositionLocalService
 *
 */
public class ModelPositionLocalServiceUtil {
    private static ModelPositionLocalService _service;

    public static com.ext.portlet.models.model.ModelPosition addModelPosition(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.SystemException {
        return getService().addModelPosition(modelPosition);
    }

    public static com.ext.portlet.models.model.ModelPosition createModelPosition(
        java.lang.Long id) {
        return getService().createModelPosition(id);
    }

    public static void deleteModelPosition(java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelPosition(id);
    }

    public static void deleteModelPosition(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.SystemException {
        getService().deleteModelPosition(modelPosition);
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

    public static com.ext.portlet.models.model.ModelPosition getModelPosition(
        java.lang.Long id)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelPosition(id);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> getModelPositions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelPositions(start, end);
    }

    public static int getModelPositionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelPositionsCount();
    }

    public static com.ext.portlet.models.model.ModelPosition updateModelPosition(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.SystemException {
        return getService().updateModelPosition(modelPosition);
    }

    public static com.ext.portlet.models.model.ModelPosition updateModelPosition(
        com.ext.portlet.models.model.ModelPosition modelPosition, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updateModelPosition(modelPosition, merge);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> getModelPositionsByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException {
        return getService().getModelPositionsByModelId(modelId);
    }

    public static void setModelPositions(java.lang.Long modelId,
        java.util.List<Long> positionIds)
        throws com.liferay.portal.SystemException {
        getService().setModelPositions(modelId, positionIds);
    }

    public static ModelPositionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ModelPositionLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelPositionLocalService service) {
        _service = service;
    }
}
