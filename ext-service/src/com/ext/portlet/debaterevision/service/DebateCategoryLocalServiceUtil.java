package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateCategoryLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateCategoryLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateCategoryLocalService
 *
 */
public class DebateCategoryLocalServiceUtil {
    private static DebateCategoryLocalService _service;

    public static com.ext.portlet.debaterevision.model.DebateCategory addDebateCategory(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory)
        throws com.liferay.portal.SystemException {
        return getService().addDebateCategory(debateCategory);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory createDebateCategory(
        java.lang.Long debateCategoryPK) {
        return getService().createDebateCategory(debateCategoryPK);
    }

    public static void deleteDebateCategory(java.lang.Long debateCategoryPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateCategory(debateCategoryPK);
    }

    public static void deleteDebateCategory(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateCategory(debateCategory);
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

    public static com.ext.portlet.debaterevision.model.DebateCategory getDebateCategory(
        java.lang.Long debateCategoryPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateCategory(debateCategoryPK);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateCategory> getDebateCategories(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateCategories(start, end);
    }

    public static int getDebateCategoriesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateCategoriesCount();
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory updateDebateCategory(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateCategory(debateCategory);
    }

    public static com.ext.portlet.debaterevision.model.DebateCategory updateDebateCategory(
        com.ext.portlet.debaterevision.model.DebateCategory debateCategory,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateCategory(debateCategory, merge);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.Debate> getDebates(
        long categoryId) {
        return getService().getDebates(categoryId);
    }

    public static DebateCategoryLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateCategoryLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateCategoryLocalService service) {
        _service = service;
    }
}
