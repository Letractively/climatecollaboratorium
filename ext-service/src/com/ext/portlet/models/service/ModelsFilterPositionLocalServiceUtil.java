/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service;


/**
 * <a href="ModelsFilterPositionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelsFilterPositionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelsFilterPositionLocalService
 *
 */
public class ModelsFilterPositionLocalServiceUtil {
    private static ModelsFilterPositionLocalService _service;

    public static com.ext.portlet.models.model.ModelsFilterPosition addModelsFilterPosition(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition)
        throws com.liferay.portal.SystemException {
        return getService().addModelsFilterPosition(modelsFilterPosition);
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition createModelsFilterPosition(
        com.ext.portlet.models.service.persistence.ModelsFilterPositionPK modelsFilterPositionPK) {
        return getService().createModelsFilterPosition(modelsFilterPositionPK);
    }

    public static void deleteModelsFilterPosition(
        com.ext.portlet.models.service.persistence.ModelsFilterPositionPK modelsFilterPositionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelsFilterPosition(modelsFilterPositionPK);
    }

    public static void deleteModelsFilterPosition(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition)
        throws com.liferay.portal.SystemException {
        getService().deleteModelsFilterPosition(modelsFilterPosition);
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

    public static com.ext.portlet.models.model.ModelsFilterPosition getModelsFilterPosition(
        com.ext.portlet.models.service.persistence.ModelsFilterPositionPK modelsFilterPositionPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelsFilterPosition(modelsFilterPositionPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelsFilterPosition> getModelsFilterPositions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelsFilterPositions(start, end);
    }

    public static int getModelsFilterPositionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelsFilterPositionsCount();
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition updateModelsFilterPosition(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition)
        throws com.liferay.portal.SystemException {
        return getService().updateModelsFilterPosition(modelsFilterPosition);
    }

    public static com.ext.portlet.models.model.ModelsFilterPosition updateModelsFilterPosition(
        com.ext.portlet.models.model.ModelsFilterPosition modelsFilterPosition,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateModelsFilterPosition(modelsFilterPosition, merge);
    }

    public static ModelsFilterPositionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ModelsFilterPositionLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelsFilterPositionLocalService service) {
        _service = service;
    }
}
