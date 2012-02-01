package com.ext.portlet.debaterevision.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateCommentPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debaterevision.model.DebateComment debateComment);

    public void cacheResult(
        java.util.List<com.ext.portlet.debaterevision.model.DebateComment> debateComments);

    public void clearCache();

    public com.ext.portlet.debaterevision.model.DebateComment create(
        java.lang.Long debateCommentId);

    public com.ext.portlet.debaterevision.model.DebateComment remove(
        java.lang.Long debateCommentId)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment remove(
        com.ext.portlet.debaterevision.model.DebateComment debateComment)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateComment debateComment, boolean merge)</code>.
     */
    public com.ext.portlet.debaterevision.model.DebateComment update(
        com.ext.portlet.debaterevision.model.DebateComment debateComment)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateComment the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateComment is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debaterevision.model.DebateComment update(
        com.ext.portlet.debaterevision.model.DebateComment debateComment,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment updateImpl(
        com.ext.portlet.debaterevision.model.DebateComment debateComment,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment findByPrimaryKey(
        java.lang.Long debateCommentId)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment fetchByPrimaryKey(
        java.lang.Long debateCommentId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findBycommentForDebateItem(
        java.lang.Long debateItemId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findBycommentForDebateItem(
        java.lang.Long debateItemId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findBycommentForDebateItem(
        java.lang.Long debateItemId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment findBycommentForDebateItem_First(
        java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment findBycommentForDebateItem_Last(
        java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment[] findBycommentForDebateItem_PrevAndNext(
        java.lang.Long debateCommentId, java.lang.Long debateItemId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.debaterevision.NoSuchDebateCommentException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateComment> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeBycommentForDebateItem(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countBycommentForDebateItem(java.lang.Long debateItemId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
