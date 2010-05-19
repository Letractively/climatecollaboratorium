package com.ext.portlet.debatemigration.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateMigrationPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration);

    public void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> debateMigrations);

    public void clearCache();

    public com.ext.portlet.debatemigration.model.DebateMigration create(
        java.lang.Long debateMigrationPK);

    public com.ext.portlet.debatemigration.model.DebateMigration remove(
        java.lang.Long debateMigrationPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigration remove(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateMigration debateMigration, boolean merge)</code>.
     */
    public com.ext.portlet.debatemigration.model.DebateMigration update(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigration the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigration is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debatemigration.model.DebateMigration update(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigration updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigration findByPrimaryKey(
        java.lang.Long debateMigrationPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigration fetchByPrimaryKey(
        java.lang.Long debateMigrationPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
