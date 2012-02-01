package com.ext.portlet.surveys.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface UserSurveyPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.surveys.model.UserSurvey userSurvey);

    public void cacheResult(
        java.util.List<com.ext.portlet.surveys.model.UserSurvey> userSurveies);

    public void clearCache();

    public com.ext.portlet.surveys.model.UserSurvey create(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK);

    public com.ext.portlet.surveys.model.UserSurvey remove(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey remove(
        com.ext.portlet.surveys.model.UserSurvey userSurvey)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(UserSurvey userSurvey, boolean merge)</code>.
     */
    public com.ext.portlet.surveys.model.UserSurvey update(
        com.ext.portlet.surveys.model.UserSurvey userSurvey)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                userSurvey the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when userSurvey is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.surveys.model.UserSurvey update(
        com.ext.portlet.surveys.model.UserSurvey userSurvey, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey updateImpl(
        com.ext.portlet.surveys.model.UserSurvey userSurvey, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey findByPrimaryKey(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey fetchByPrimaryKey(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey[] findByUserId_PrevAndNext(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK,
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByEventName(
        java.lang.String eventName) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByEventName(
        java.lang.String eventName, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByEventName(
        java.lang.String eventName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey findByEventName_First(
        java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey findByEventName_Last(
        java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey[] findByEventName_PrevAndNext(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK,
        java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserIdEventName(
        java.lang.Long userId, java.lang.String eventName)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserIdEventName(
        java.lang.Long userId, java.lang.String eventName, int start, int end)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserIdEventName(
        java.lang.Long userId, java.lang.String eventName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey findByUserIdEventName_First(
        java.lang.Long userId, java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey findByUserIdEventName_Last(
        java.lang.Long userId, java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.UserSurvey[] findByUserIdEventName_PrevAndNext(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK,
        java.lang.Long userId, java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.UserSurvey> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public void removeByEventName(java.lang.String eventName)
        throws com.liferay.portal.SystemException;

    public void removeByUserIdEventName(java.lang.Long userId,
        java.lang.String eventName) throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public int countByEventName(java.lang.String eventName)
        throws com.liferay.portal.SystemException;

    public int countByUserIdEventName(java.lang.Long userId,
        java.lang.String eventName) throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
