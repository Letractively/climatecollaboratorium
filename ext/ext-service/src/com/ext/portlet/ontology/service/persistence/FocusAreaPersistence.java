package com.ext.portlet.ontology.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;


public interface FocusAreaPersistence extends BasePersistence {
    public void cacheResult(com.ext.portlet.ontology.model.FocusArea focusArea);

    public void cacheResult(
        java.util.List<com.ext.portlet.ontology.model.FocusArea> focusAreas);

    public void clearCache();

    public com.ext.portlet.ontology.model.FocusArea create(java.lang.Long id);

    public com.ext.portlet.ontology.model.FocusArea remove(java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea remove(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.SystemException;

    /**
     * @deprecated Use <code>update(FocusArea focusArea, boolean merge)</code>.
     */
    public com.ext.portlet.ontology.model.FocusArea update(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.SystemException;

    /**
     * Add, update, or merge, the entity. This method also calls the model
     * listeners to trigger the proper events associated with adding, deleting,
     * or updating an entity.
     *
     * @param                focusArea the entity to add, update, or merge
     * @param                merge boolean value for whether to merge the entity. The
     *                                default value is false. Setting merge to true is more
     *                                expensive and should only be true when focusArea is
     *                                transient. See LEP-5473 for a detailed discussion of this
     *                                method.
     * @return                true if the portlet can be displayed via Ajax
     */
    public com.ext.portlet.ontology.model.FocusArea update(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea updateImpl(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea fetchByPrimaryKey(
        java.lang.Long id) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea findByName(
        java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea fetchByName(
        java.lang.String name) throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea fetchByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> findWithDynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll(
        int start, int end) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.FocusArea> findAll(
        int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
        throws com.liferay.portal.SystemException;

    public void removeByName(java.lang.String name)
        throws com.ext.portlet.ontology.NoSuchFocusAreaException,
            com.liferay.portal.SystemException;

    public void removeAll() throws com.liferay.portal.SystemException;

    public int countByName(java.lang.String name)
        throws com.liferay.portal.SystemException;

    public int countAll() throws com.liferay.portal.SystemException;
}
