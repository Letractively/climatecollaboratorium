package com.ext.portlet.surveys.service.persistence;

public class UserSurveyUtil {
    private static UserSurveyPersistence _persistence;

    public static void cacheResult(
        com.ext.portlet.surveys.model.UserSurvey userSurvey) {
        getPersistence().cacheResult(userSurvey);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.surveys.model.UserSurvey> userSurveies) {
        getPersistence().cacheResult(userSurveies);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.surveys.model.UserSurvey create(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK) {
        return getPersistence().create(userSurveyPK);
    }

    public static com.ext.portlet.surveys.model.UserSurvey remove(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(userSurveyPK);
    }

    public static com.ext.portlet.surveys.model.UserSurvey remove(
        com.ext.portlet.surveys.model.UserSurvey userSurvey)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(userSurvey);
    }

    /**
     * @deprecated Use <code>update(UserSurvey userSurvey, boolean merge)</code>.
     */
    public static com.ext.portlet.surveys.model.UserSurvey update(
        com.ext.portlet.surveys.model.UserSurvey userSurvey)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(userSurvey);
    }

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
    public static com.ext.portlet.surveys.model.UserSurvey update(
        com.ext.portlet.surveys.model.UserSurvey userSurvey, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(userSurvey, merge);
    }

    public static com.ext.portlet.surveys.model.UserSurvey updateImpl(
        com.ext.portlet.surveys.model.UserSurvey userSurvey, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(userSurvey, merge);
    }

    public static com.ext.portlet.surveys.model.UserSurvey findByPrimaryKey(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(userSurveyPK);
    }

    public static com.ext.portlet.surveys.model.UserSurvey fetchByPrimaryKey(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK)
        throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(userSurveyPK);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserId(
        java.lang.Long userId) throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserId(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserId(userId, start, end, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey findByUserId_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_First(userId, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey findByUserId_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence().findByUserId_Last(userId, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey[] findByUserId_PrevAndNext(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK,
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserId_PrevAndNext(userSurveyPK, userId, obc);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByEventName(
        java.lang.String eventName) throws com.liferay.portal.SystemException {
        return getPersistence().findByEventName(eventName);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByEventName(
        java.lang.String eventName, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByEventName(eventName, start, end);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByEventName(
        java.lang.String eventName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByEventName(eventName, start, end, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey findByEventName_First(
        java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence().findByEventName_First(eventName, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey findByEventName_Last(
        java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence().findByEventName_Last(eventName, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey[] findByEventName_PrevAndNext(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK,
        java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByEventName_PrevAndNext(userSurveyPK, eventName, obc);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserIdEventName(
        java.lang.Long userId, java.lang.String eventName)
        throws com.liferay.portal.SystemException {
        return getPersistence().findByUserIdEventName(userId, eventName);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserIdEventName(
        java.lang.Long userId, java.lang.String eventName, int start, int end)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdEventName(userId, eventName, start, end);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findByUserIdEventName(
        java.lang.Long userId, java.lang.String eventName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdEventName(userId, eventName, start, end, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey findByUserIdEventName_First(
        java.lang.Long userId, java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdEventName_First(userId, eventName, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey findByUserIdEventName_Last(
        java.lang.Long userId, java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdEventName_Last(userId, eventName, obc);
    }

    public static com.ext.portlet.surveys.model.UserSurvey[] findByUserIdEventName_PrevAndNext(
        com.ext.portlet.surveys.service.persistence.UserSurveyPK userSurveyPK,
        java.lang.Long userId, java.lang.String eventName,
        com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.ext.portlet.surveys.NoSuchUserSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence()
                   .findByUserIdEventName_PrevAndNext(userSurveyPK, userId,
            eventName, obc);
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

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.surveys.model.UserSurvey> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByUserId(userId);
    }

    public static void removeByEventName(java.lang.String eventName)
        throws com.liferay.portal.SystemException {
        getPersistence().removeByEventName(eventName);
    }

    public static void removeByUserIdEventName(java.lang.Long userId,
        java.lang.String eventName) throws com.liferay.portal.SystemException {
        getPersistence().removeByUserIdEventName(userId, eventName);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByUserId(userId);
    }

    public static int countByEventName(java.lang.String eventName)
        throws com.liferay.portal.SystemException {
        return getPersistence().countByEventName(eventName);
    }

    public static int countByUserIdEventName(java.lang.Long userId,
        java.lang.String eventName) throws com.liferay.portal.SystemException {
        return getPersistence().countByUserIdEventName(userId, eventName);
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static UserSurveyPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(UserSurveyPersistence persistence) {
        _persistence = persistence;
    }
}
