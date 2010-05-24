package com.ext.portlet.models.service;


/**
 * <a href="ModelDiscussionLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelDiscussionLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelDiscussionLocalService
 *
 */
public class ModelDiscussionLocalServiceUtil {
    private static ModelDiscussionLocalService _service;

    public static com.ext.portlet.models.model.ModelDiscussion addModelDiscussion(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.SystemException {
        return getService().addModelDiscussion(modelDiscussion);
    }

    public static com.ext.portlet.models.model.ModelDiscussion createModelDiscussion(
        java.lang.Long modelDiscussionId) {
        return getService().createModelDiscussion(modelDiscussionId);
    }

    public static void deleteModelDiscussion(java.lang.Long modelDiscussionId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteModelDiscussion(modelDiscussionId);
    }

    public static void deleteModelDiscussion(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.SystemException {
        getService().deleteModelDiscussion(modelDiscussion);
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

    public static com.ext.portlet.models.model.ModelDiscussion getModelDiscussion(
        java.lang.Long modelDiscussionId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getModelDiscussion(modelDiscussionId);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> getModelDiscussions(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getModelDiscussions(start, end);
    }

    public static int getModelDiscussionsCount()
        throws com.liferay.portal.SystemException {
        return getService().getModelDiscussionsCount();
    }

    public static com.ext.portlet.models.model.ModelDiscussion updateModelDiscussion(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.SystemException {
        return getService().updateModelDiscussion(modelDiscussion);
    }

    public static com.ext.portlet.models.model.ModelDiscussion updateModelDiscussion(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateModelDiscussion(modelDiscussion, merge);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException {
        return getService().findByModelId(modelId);
    }

    public static ModelDiscussionLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("ModelDiscussionLocalService is not set");
        }

        return _service;
    }

    public void setService(ModelDiscussionLocalService service) {
        _service = service;
    }
}
