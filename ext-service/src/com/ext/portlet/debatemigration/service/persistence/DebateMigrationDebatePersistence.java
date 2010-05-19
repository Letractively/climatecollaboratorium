package com.ext.portlet.debatemigration.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateMigrationDebatePersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate);

    public void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> debateMigrationDebates);

    public void clearCache();

    public com.ext.portlet.debatemigration.model.DebateMigrationDebate create(
        java.lang.Long debateMigrationDebatePK);

    public com.ext.portlet.debatemigration.model.DebateMigrationDebate remove(
        java.lang.Long debateMigrationDebatePK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationDebate remove(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateMigrationDebate debateMigrationDebate, boolean merge)</code>.
     */
    public com.ext.portlet.debatemigration.model.DebateMigrationDebate update(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationDebate the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationDebate is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debatemigration.model.DebateMigrationDebate update(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationDebate updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationDebate debateMigrationDebate,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationDebate findByPrimaryKey(
        java.lang.Long debateMigrationDebatePK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationDebateException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationDebate fetchByPrimaryKey(
        java.lang.Long debateMigrationDebatePK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationDebate> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
