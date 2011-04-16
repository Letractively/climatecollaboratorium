package com.ext.portlet.surveys.service.persistence;

public class SurveyUtil {
    private static SurveyPersistence _persistence;

    public static void cacheResult(com.ext.portlet.surveys.model.Survey survey) {
        getPersistence().cacheResult(survey);
    }

    public static void cacheResult(
        java.util.List<com.ext.portlet.surveys.model.Survey> surveies) {
        getPersistence().cacheResult(surveies);
    }

    public static void clearCache() {
        getPersistence().clearCache();
    }

    public static com.ext.portlet.surveys.model.Survey create(
        java.lang.String eventName) {
        return getPersistence().create(eventName);
    }

    public static com.ext.portlet.surveys.model.Survey remove(
        java.lang.String eventName)
        throws com.ext.portlet.surveys.NoSuchSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence().remove(eventName);
    }

    public static com.ext.portlet.surveys.model.Survey remove(
        com.ext.portlet.surveys.model.Survey survey)
        throws com.liferay.portal.SystemException {
        return getPersistence().remove(survey);
    }

    /**
     * @deprecated Use <code>update(Survey survey, boolean merge)</code>.
     */
    public static com.ext.portlet.surveys.model.Survey update(
        com.ext.portlet.surveys.model.Survey survey)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(survey);
    }

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                survey the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when survey is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public static com.ext.portlet.surveys.model.Survey update(
        com.ext.portlet.surveys.model.Survey survey, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().update(survey, merge);
    }

    public static com.ext.portlet.surveys.model.Survey updateImpl(
        com.ext.portlet.surveys.model.Survey survey, boolean merge)
        throws com.liferay.portal.SystemException {
        return getPersistence().updateImpl(survey, merge);
    }

    public static com.ext.portlet.surveys.model.Survey findByPrimaryKey(
        java.lang.String eventName)
        throws com.ext.portlet.surveys.NoSuchSurveyException,
            com.liferay.portal.SystemException {
        return getPersistence().findByPrimaryKey(eventName);
    }

    public static com.ext.portlet.surveys.model.Survey fetchByPrimaryKey(
        java.lang.String eventName) throws com.liferay.portal.SystemException {
        return getPersistence().fetchByPrimaryKey(eventName);
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

    public static java.util.List<com.ext.portlet.surveys.model.Survey> findAll()
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll();
    }

    public static java.util.List<com.ext.portlet.surveys.model.Survey> findAll(
        int start, int end) throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end);
    }

    public static java.util.List<com.ext.portlet.surveys.model.Survey> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException {
        return getPersistence().findAll(start, end, obc);
    }

    public static void removeAll() throws com.liferay.portal.SystemException {
        getPersistence().removeAll();
    }

    public static int countAll() throws com.liferay.portal.SystemException {
        return getPersistence().countAll();
    }

    public static SurveyPersistence getPersistence() {
        return _persistence;
    }

    public void setPersistence(SurveyPersistence persistence) {
        _persistence = persistence;
    }
}
