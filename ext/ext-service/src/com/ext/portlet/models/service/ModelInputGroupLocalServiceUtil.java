package com.ext.portlet.models.service;


/**
 * <a href="ModelInputGroupLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelInputGroupLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelInputGroupLocalService
 *
 */
public class ModelInputGroupLocalServiceUtil {
    private static ModelInputGroupLocalService _service;

    public static com.ext.portlet.models.model.ModelInputGroup addModelInputGroup(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.SystemException {
        return getService().addModelInputGroup(modelInputGroup);
    }

    public static com.ext.portlet.models.model.ModelInputGroup createModelInputGroup(
        java.lang.Long modelInputGroupPK) {
        return getService().createModelInputGroup(modelInputGroupPK);
    }

    public static void deleteModelInputGroup(java.lang.Long modelInputGroupPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelInputGroup(modelInputGroupPK);
    }

    public static void deleteModelInputGroup(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.SystemException {
        getService().deleteModelInputGroup(modelInputGroup);
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

    public static com.ext.portlet.models.model.ModelInputGroup getModelInputGroup(
        java.lang.Long modelInputGroupPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelInputGroup(modelInputGroupPK);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> getModelInputGroups(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelInputGroups(start, end);
    }

    public static int getModelInputGroupsCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelInputGroupsCount();
    }

    public static com.ext.portlet.models.model.ModelInputGroup updateModelInputGroup(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.SystemException {
        return getService().updateModelInputGroup(modelInputGroup);
    }

    public static com.ext.portlet.models.model.ModelInputGroup updateModelInputGroup(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateModelInputGroup(modelInputGroup, merge);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> getInputGroups(
        edu.mit.cci.simulation.client.Simulation sim) {
        return getService().getInputGroups(sim);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelInputGroup> getChildGroups(
        com.ext.portlet.models.model.ModelInputGroup group) {
        return getService().getChildGroups(group);
    }

    public static ModelInputGroupLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ModelInputGroupLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelInputGroupLocalService service) {
        _service = service;
    }
}
