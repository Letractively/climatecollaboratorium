package com.ext.portlet.models.service;


/**
 * <a href="ModelOutputItemModifierLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelOutputItemModifierLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelOutputItemModifierLocalService
 *
 */
public class ModelOutputItemModifierLocalServiceUtil {
    private static ModelOutputItemModifierLocalService _service;

    public static com.ext.portlet.models.model.ModelOutputItemModifier addModelOutputItemModifier(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier)
        throws com.liferay.portal.SystemException {
        return getService().addModelOutputItemModifier(modelOutputItemModifier);
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier createModelOutputItemModifier(
        java.lang.Long modelOutputItemModifierPK) {
        return getService()
                   .createModelOutputItemModifier(modelOutputItemModifierPK);
    }

    public static void deleteModelOutputItemModifier(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelOutputItemModifier(modelOutputItemModifierPK);
    }

    public static void deleteModelOutputItemModifier(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier)
        throws com.liferay.portal.SystemException {
        getService().deleteModelOutputItemModifier(modelOutputItemModifier);
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

    public static com.ext.portlet.models.model.ModelOutputItemModifier getModelOutputItemModifier(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelOutputItemModifier(modelOutputItemModifierPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelOutputItemModifier> getModelOutputItemModifiers(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelOutputItemModifiers(start, end);
    }

    public static int getModelOutputItemModifiersCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelOutputItemModifiersCount();
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier updateModelOutputItemModifier(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier)
        throws com.liferay.portal.SystemException {
        return getService()
                   .updateModelOutputItemModifier(modelOutputItemModifier);
    }

    public static com.ext.portlet.models.model.ModelOutputItemModifier updateModelOutputItemModifier(
        com.ext.portlet.models.model.ModelOutputItemModifier modelOutputItemModifier,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateModelOutputItemModifier(modelOutputItemModifier, merge);
    }

    public static ModelOutputItemModifierLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ModelOutputItemModifierLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelOutputItemModifierLocalService service) {
        _service = service;
    }
}
