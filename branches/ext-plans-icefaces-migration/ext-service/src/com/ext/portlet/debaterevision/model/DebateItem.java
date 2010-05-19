package com.ext.portlet.debaterevision.model;


/**
 * <a href="DebateItem.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateItem</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.debaterevision.model.impl.DebateItemImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItemModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemModelImpl
 *
 */
public interface DebateItem extends DebateItemModel {
    public void setDebateVersion(long debateVersion);

    public long getDebateVersion();

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getChildren();

    public com.ext.portlet.debaterevision.model.DebateItem getParent()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.Debate getDebate()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem addChild(
        java.lang.String title, java.lang.String content, long userId,
        java.lang.String type,
        java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> refs,
        long weight) throws com.liferay.portal.SystemException;

    public void delete(long authorId) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem update(
        java.lang.String title, java.lang.String detail,
        java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> refs,
        long authorId, long weight) throws com.liferay.portal.SystemException;

    public void moveForeward() throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem copyForward()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment addComment(
        java.lang.String title, java.lang.String message, long authorId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateComment> getComments()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> getReferences()
        throws com.liferay.portal.SystemException;

    public void voteForThisItem(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void unvoteThisItem(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public boolean hasUserVotedForThisItem(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getCompleteHistory();

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getHistoryForTreeVersion();

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.lang.Long getVotesCount()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
