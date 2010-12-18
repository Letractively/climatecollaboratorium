package com.ext.portlet.debatemigration.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface DebateMigrationItemPersistence extends BasePersistence {
    public void cacheResult(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem);

    public void cacheResult(
        java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> debateMigrationItems);

    public void clearCache();

    public com.ext.portlet.debatemigration.model.DebateMigrationItem create(
        java.lang.Long debateMigrationItemPK);

    public com.ext.portlet.debatemigration.model.DebateMigrationItem remove(
        java.lang.Long debateMigrationItemPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationItem remove(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(DebateMigrationItem debateMigrationItem, boolean merge)</code>.
     */
    public com.ext.portlet.debatemigration.model.DebateMigrationItem update(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                debateMigrationItem the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when debateMigrationItem is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.debatemigration.model.DebateMigrationItem update(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationItem updateImpl(
        com.ext.portlet.debatemigration.model.DebateMigrationItem debateMigrationItem,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationItem findByPrimaryKey(
        java.lang.Long debateMigrationItemPK)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationItem fetchByPrimaryKey(
        java.lang.Long debateMigrationItemPK)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationItem findByDebateMigrationItem(
        java.lang.Long debateMigrationId, java.lang.Long oldMBMessageId)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationItem fetchByDebateMigrationItem(
        java.lang.Long debateMigrationId, java.lang.Long oldMBMessageId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigrationItem fetchByDebateMigrationItem(
        java.lang.Long debateMigrationId, java.lang.Long oldMBMessageId,
        boolean retrieveFromCache) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigrationItem> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByDebateMigrationItem(java.lang.Long debateMigrationId,
        java.lang.Long oldMBMessageId)
        throws com.ext.portlet.debatemigration.NoSuchDebateMigrationItemException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByDebateMigrationItem(java.lang.Long debateMigrationId,
        java.lang.Long oldMBMessageId)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
