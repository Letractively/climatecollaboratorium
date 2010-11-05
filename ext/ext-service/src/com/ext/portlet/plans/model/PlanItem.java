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
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void setName(java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

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

    public java.util.List<com.ext.portlet.plans.model.PlanMeta> getAllPlanMetas()
        throws com.liferay.portal.SystemException;

    public java.lang.Long getPlanTypeId()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType getPlanType()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest getContest()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase getContestPhase()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void setContestPhase(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public void setPlanTypeId(java.lang.Long planTypeId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.lang.Long getMBCategoryId()
        throws com.liferay.portal.SystemException;

    public void setMBCategoryId(java.lang.Long mbCategoryId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public java.lang.Long getCategoryGroupId()
        throws com.liferay.portal.SystemException;

    public void setCategoryGroupId(java.lang.Long categoryGroupId,
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

    public boolean getOpen() throws com.liferay.portal.SystemException;

    public void setOpen(boolean open, java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public java.lang.String getStatus()
        throws com.liferay.portal.SystemException;

    public void setStatus(java.lang.String status, java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanPositions getPlanPositions()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public java.util.List<Long> getPositionsIds()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.SystemException;

    public java.lang.Long[] getPositionsIdsArray()
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

    public java.util.List<com.liferay.portal.model.User> getMembers()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests()
        throws com.liferay.portal.SystemException;

    public void addMembershipRequest(java.lang.Long userId,
        java.lang.String comments)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void dennyMembershipRequest(java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void approveMembershipRequest(java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void publish(java.lang.Long updateAuthorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void delete(java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getUpdateAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> getFans()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanFan addFan(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public void removeFan(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public boolean isUserAFan(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public boolean isUserAMember(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public boolean hasUserRequestedMembership(java.lang.Long userId)
        throws com.liferay.portal.SystemException;

    public boolean isAdmin(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public boolean isOwner(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void setUserPermission(java.lang.Long userId,
        java.lang.String userPermission, java.lang.Long updateAuthorId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void removeMember(java.lang.Long userId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.SystemException;

    public void joinIfNotAMember(java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void setSeekingAssistance(boolean seekingAssistance)
        throws com.liferay.portal.SystemException;

    public boolean isSeekingAssistance()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanItem promote(
        com.liferay.portal.model.User user)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public boolean getPromoted() throws com.liferay.portal.SystemException;
}
