package com.ext.portlet.debatemigration.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateMigrationCommentPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment);

    public void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> debateMigrationComments);

    public void clearCache();

    public com.ext.portlet.debatemigration.model.DebateMigrationComment create(
        java.lang.Long debateMigrationCommentPK);

    public com.ext.portlet.debatemigration.model.DebateMigrationComment remove(
        java.lang.Long debateMigrationCommentPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationCommentException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationComment remove(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateMigrationComment debateMigrationComment, boolean merge)</code>.
     */
    public com.ext.portlet.debatemigration.model.DebateMigrationComment update(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationComment the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationComment is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debatemigration.model.DebateMigrationComment update(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationComment updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationComment debateMigrationComment,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationComment findByPrimaryKey(
        java.lang.Long debateMigrationCommentPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationCommentException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationComment fetchByPrimaryKey(
        java.lang.Long debateMigrationCommentPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationComment> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
