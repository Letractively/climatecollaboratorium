package com.ext.portlet.plans.model;


/**
 * <a href="PlanItem.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanItem</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanItemImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanItemModel
 * @see com.ext.portlet.plans.model.impl.PlanItemImpl
 * @see com.ext.portlet.plans.model.impl.PlanItemModelImpl
 *
 */
public interface PlanItem extends PlanItemModel {
    public java.lang.String getDescription()
        throws com.liferay.portal.SystemException;

    public java.lang.String getName() throws com.liferay.portal.SystemException;

    public void setDescription(java.lang.String description,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public void setName(java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllDescriptionVersions()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions()
        throws com.liferay.portal.SystemException;

    public java.lang.Long getScenarioId()
        throws com.liferay.portal.SystemException;

    public void setScenarioId(java.lang.Long scenarioId, java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void setModelId(java.lang.Long simulationId, java.lang.Long authorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> getAllPlanModelRuns()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanMeta getPlanMeta()
        throws com.liferay.portal.SystemException;

    public java.lang.Long getPlanTypeId()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType getPlanType()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void setPlanTypeId(java.lang.Long planTypeId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public java.lang.Long getMBCategoryId()
        throws com.liferay.portal.SystemException;

    public void setMBCategoryId(java.lang.Long mbCategoryId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public java.lang.Long getPlanGroupId()
        throws com.liferay.portal.SystemException;

    public void setPlanGroupId(java.lang.Long groupId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public java.lang.Long getAuthorId()
        throws com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void setAuthorId(java.lang.Long authorId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public java.util.Date getCreateDate()
        throws com.liferay.portal.SystemException;

    public java.util.Date getPublishDate()
        throws com.liferay.portal.SystemException;

    public java.lang.String getCreator()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.lang.Integer getVotes()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions getPlanPositions()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public java.util.List<Long> getPositionsIds()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public void setPositions(java.util.List<Long> positionsIds,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> getAllPositionsVersions()
        throws com.liferay.portal.SystemException;

    public boolean hasUserVoted(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void vote(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void unvote(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions()
        throws com.liferay.portal.SystemException;

    public void store() throws com.liferay.portal.SystemException;

    public void updateAllAttributes() throws com.liferay.portal.SystemException;

    public void updateAttribute(java.lang.String attributeName)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        java.lang.String name) throws com.liferay.portal.SystemException;
}
