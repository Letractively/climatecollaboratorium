package com.ext.portlet.debaterevision.service.persistence;

public class DebateItemFinderUtil {
    private static DebateItemFinder _finder;

    public static com.ext.portlet.debaterevision.model.DebateItem getLastItem(
        long itemId) {
        return getFinder().getLastItem(itemId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem getLastActiveItem(
        long itemId) {
        return getFinder().getLastActiveItem(itemId);
    }

    public static com.ext.portlet.debaterevision.model.DebateItem getLastItemInVersion(
        long treeVersion, long itemId) {
        return getFinder().getLastItemInVersion(treeVersion, itemId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getHistory(
        long treeVersion, long itemId) {
        return getFinder().getHistory(treeVersion, itemId);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findByParentInVersion(
        long treeVersion, long parentId) {
        return getFinder().findByParentInVersion(treeVersion, parentId);
    }

    public static int getDebateItemCommentsCount(long debateItemId) {
        return getFinder().getDebateItemCommentsCount(debateItemId);
    }

    public static DebateItemFinder getFinder() {
        return _finder;
    }

    public void setFinder(DebateItemFinder finder) {
        _finder = finder;
    }
}
