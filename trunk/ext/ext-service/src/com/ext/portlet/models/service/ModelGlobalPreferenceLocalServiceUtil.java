package com.ext.portlet.models.service;


/**
 * <a href="ModelGlobalPreferenceLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelGlobalPreferenceLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelGlobalPreferenceLocalService
 *
 */
public class ModelGlobalPreferenceLocalServiceUtil {
    private static ModelGlobalPreferenceLocalService _service;

    public static com.ext.portlet.models.model.ModelGlobalPreference addModelGlobalPreference(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException {
        return getService().addModelGlobalPreference(modelGlobalPreference);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference createModelGlobalPreference(
        java.lang.Long modelGlobalPreferencePK) {
        return getService().createModelGlobalPreference(modelGlobalPreferencePK);
    }

    public static void deleteModelGlobalPreference(
        java.lang.Long modelGlobalPreferencePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelGlobalPreference(modelGlobalPreferencePK);
    }

    public static void deleteModelGlobalPreference(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException {
        getService().deleteModelGlobalPreference(modelGlobalPreference);
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

    public static com.ext.portlet.models.model.ModelGlobalPreference getModelGlobalPreference(
        java.lang.Long modelGlobalPreferencePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelGlobalPreference(modelGlobalPreferencePK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelGlobalPreference> getModelGlobalPreferences(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelGlobalPreferences(start, end);
    }

    public static int getModelGlobalPreferencesCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelGlobalPreferencesCount();
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference updateModelGlobalPreference(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.SystemException {
        return getService().updateModelGlobalPreference(modelGlobalPreference);
    }

    public static com.ext.portlet.models.model.ModelGlobalPreference updateModelGlobalPreference(
        com.ext.portlet.models.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService()
                   .updateModelGlobalPreference(modelGlobalPreference, merge);
    }

    public static boolean isVisible(mit.simulation.climate.client.Simulation s)
        throws com.liferay.portal.SystemException {
        return getService().isVisible(s);
    }

    public static void setVisible(mit.simulation.climate.client.Simulation s,
        boolean visible) throws com.liferay.portal.SystemException {
        getService().setVisible(s, visible);
    }

    public static int getWeight(mit.simulation.climate.client.Simulation s)
        throws com.liferay.portal.SystemException {
        return getService().getWeight(s);
    }

    public static void setWeight(mit.simulation.climate.client.Simulation s,
        int weight) throws com.liferay.portal.SystemException {
        getService().setWeight(s, weight);
    }

    public static java.lang.Long getExpertEvaluationPageId(
        mit.simulation.climate.client.Simulation s)
        throws com.liferay.portal.SystemException {
        return getService().getExpertEvaluationPageId(s);
    }

    public static void setExpertEvaluationPageId(
        mit.simulation.climate.client.Simulation s, java.lang.Long pageId)
        throws com.liferay.portal.SystemException {
        getService().setExpertEvaluationPageId(s, pageId);
    }

    public static ModelGlobalPreferenceLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ModelGlobalPreferenceLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelGlobalPreferenceLocalService service) {
        _service = service;
    }
}
