package com.ext.portlet.debatemigration.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateMigrationCategoryPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory);

    public void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> debateMigrationCategories);

    public void clearCache();

    public com.ext.portlet.debatemigration.model.DebateMigrationCategory create(
        java.lang.Long debateMigrationCategoryPK);

    public com.ext.portlet.debatemigration.model.DebateMigrationCategory remove(
        java.lang.Long debateMigrationCategoryPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationCategory remove(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateMigrationCategory debateMigrationCategory, boolean merge)</code>.
     */
    public com.ext.portlet.debatemigration.model.DebateMigrationCategory update(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationCategory the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationCategory is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debatemigration.model.DebateMigrationCategory update(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationCategory updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationCategory debateMigrationCategory,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationCategory findByPrimaryKey(
        java.lang.Long debateMigrationCategoryPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationCategory fetchByPrimaryKey(
        java.lang.Long debateMigrationCategoryPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationCategory> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
