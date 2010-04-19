package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateItemLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateItemLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateItemLocalService
 *
 */
public class DebateItemLocalServiceUtil {
    private static DebateItemLocalService _service;

    public static com.ext.portlet.debaterevision.model.DebateItem addDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException {
        return getService().addDebateItem(debateItem);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem createDebateItem(
        java.lang.Long debateItemPK) {
        return getService().createDebateItem(debateItemPK);
    }

    public static void deleteDebateItem(java.lang.Long debateItemPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateItem(debateItemPK);
    }

    public static void deleteDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateItem(debateItem);
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

    public static com.ext.portlet.debaterevision.model.DebateItem getDebateItem(
        java.lang.Long debateItemPK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateItem(debateItemPK);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getDebateItems(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateItems(start, end);
    }

    public static int getDebateItemsCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateItemsCount();
    }

    public static com.ext.portlet.debaterevision.model.DebateItem updateDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateItem(debateItem);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem updateDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateItem(debateItem, merge);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getChildren(
        com.ext.portlet.debaterevision.model.DebateItem debateItem) {
        return getService().getChildren(debateItem);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem getParent(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getParent(debateItem);
    }

    public static void voteForDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().voteForDebateItem(debateItem, userId);
    }

    public static void unvoteDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().unvoteDebateItem(debateItem, userId);
    }

    public static boolean hasUserVotedForItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().hasUserVotedForItem(debateItem, userId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem getLastItemInVersion(
        long treeVersion, long itemId) {
        return getService().getLastItemInVersion(treeVersion, itemId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem getLastItem(
        long itemid) {
        return getService().getLastItem(itemid);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem getLastActiveItem(
        long itemid) {
        return getService().getLastActiveItem(itemid);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getHistory(
        long treeVersion, long itemId) {
        return getService().getHistory(treeVersion, itemId);
    }

    public static DebateItemLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("DebateItemLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateItemLocalService service) {
        _service = service;
    }
}
