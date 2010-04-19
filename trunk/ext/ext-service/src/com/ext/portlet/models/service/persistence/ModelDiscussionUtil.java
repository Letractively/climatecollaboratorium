/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.models.service.persistence;

public class ModelDiscussionUtil {
    private static ModelDiscussionPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion) {
        getPersistence().cacheResult(modelDiscussion);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelDiscussion> modelDiscussions) {
        getPersistence().cacheResult(modelDiscussions);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.models.model.ModelDiscussion create(
        java.lang.Long modelDiscussionId) {
        return getPersistence().create(modelDiscussionId);
    }

    public static com.ext.portlet.models.model.ModelDiscussion remove(
        java.lang.Long modelDiscussionId)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(modelDiscussionId);
    }

    public static com.ext.portlet.models.model.ModelDiscussion remove(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(modelDiscussion);
    }

    /**
     * @deprecated Use <code>update(ModelDiscussion modelDiscussion, boolean merge)</code>.
     */
    public static com.ext.portlet.models.model.ModelDiscussion update(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(modelDiscussion);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                modelDiscussion the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when modelDiscussion is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.models.model.ModelDiscussion update(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().update(modelDiscussion, merge);
    }

    public static com.ext.portlet.models.model.ModelDiscussion updateImpl(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion,
        boolean merge) throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(modelDiscussion, merge);
    }

    public static com.ext.portlet.models.model.ModelDiscussion findByPrimaryKey(
        java.lang.Long modelDiscussionId)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(modelDiscussionId);
    }

    public static com.ext.portlet.models.model.ModelDiscussion fetchByPrimaryKey(
        java.lang.Long modelDiscussionId)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(modelDiscussionId);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByModelId(
        java.lang.Long modelId) throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByModelId(
        java.lang.Long modelId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId, start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByModelId(
        java.lang.Long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByModelId(modelId, start, end, obc);
    }

    public static com.ext.portlet.models.model.ModelDiscussion findByModelId_First(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelId_First(modelId, obc);
    }

    public static com.ext.portlet.models.model.ModelDiscussion findByModelId_Last(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByModelId_Last(modelId, obc);
    }

    public static com.ext.portlet.models.model.ModelDiscussion[] findByModelId_PrevAndNext(
        java.lang.Long modelDiscussionId, java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByModelId_PrevAndNext(modelDiscussionId, modelId, obc);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByDiscussionId(
        java.lang.Long categoryId) throws com.liferay.portal.SystemException {
        return getPersistence().findByDiscussionId(categoryId);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByDiscussionId(
        java.lang.Long categoryId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByDiscussionId(categoryId, start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findByDiscussionId(
        java.lang.Long categoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByDiscussionId(categoryId, start, end, obc);
    }

    public static com.ext.portlet.models.model.ModelDiscussion findByDiscussionId_First(
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByDiscussionId_First(categoryId, obc);
    }

    public static com.ext.portlet.models.model.ModelDiscussion findByDiscussionId_Last(
        java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException {
        return getPersistence().findByDiscussionId_Last(categoryId, obc);
    }

    public static com.ext.portlet.models.model.ModelDiscussion[] findByDiscussionId_PrevAndNext(
        java.lang.Long modelDiscussionId, java.lang.Long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.models.NoSuchModelDiscussionException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByDiscussionId_PrevAndNext(modelDiscussionId,
            categoryId, obc);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.models.model.ModelDiscussion> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByModelId(modelId);
    }

    public static void removeByDiscussionId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByDiscussionId(categoryId);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    public static int countByDiscussionId(java.lang.Long categoryId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByDiscussionId(categoryId);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static ModelDiscussionPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(ModelDiscussionPersistence persistence) {
        _persistence = persistence;
    }
}
