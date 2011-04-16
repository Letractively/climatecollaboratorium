package com.ext.portlet.surveys.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface SurveyPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.surveys.model.Survey survey);

    public void cacheResult(
        java.util.List<com.ext.portlet.surveys.model.Survey> surveies);

    public void clearCache();

    public com.ext.portlet.surveys.model.Survey create(
        java.lang.String eventName);

    public com.ext.portlet.surveys.model.Survey remove(
        java.lang.String eventName)
        throws com.ext.portlet.surveys.NoSuchSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.Survey remove(
        com.ext.portlet.surveys.model.Survey survey)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(Survey survey, boolean merge)</code>.
     */
    public com.ext.portlet.surveys.model.Survey update(
        com.ext.portlet.surveys.model.Survey survey)
        throws com.liferay.portal.SystemException;

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
    public com.ext.portlet.surveys.model.Survey update(
        com.ext.portlet.surveys.model.Survey survey, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.Survey updateImpl(
        com.ext.portlet.surveys.model.Survey survey, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.Survey findByPrimaryKey(
        java.lang.String eventName)
        throws com.ext.portlet.surveys.NoSuchSurveyException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.surveys.model.Survey fetchByPrimaryKey(
        java.lang.String eventName) throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.Survey> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.Survey> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.surveys.model.Survey> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
