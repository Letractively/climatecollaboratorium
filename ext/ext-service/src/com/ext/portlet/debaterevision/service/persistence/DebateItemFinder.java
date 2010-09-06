package com.ext.portlet.debaterevision.service.persistence;

public interface DebateItemFinder {
    public com.ext.portlet.debaterevision.model.DebateItem getLastItem(
        long itemId);

    public com.ext.portlet.debaterevision.model.DebateItem getLastActiveItem(
        long itemId);

    public com.ext.portlet.debaterevision.model.DebateItem getLastItemInVersion(
        long treeVersion, long itemId);

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getHistory(
        long treeVersion, long itemId);

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> findByParentInVersion(
        long treeVersion, long parentId);

    public int getDebateItemCommentsCount(long debateItemId);
}
