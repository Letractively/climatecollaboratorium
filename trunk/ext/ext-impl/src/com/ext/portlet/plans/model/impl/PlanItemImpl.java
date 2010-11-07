/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans.model.impl;

import java.util.Date;
import java.util.List;

import mit.simulation.climate.client.Simulation;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.NoSuchPlanFanException;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.NoSuchPlanTeamHistoryException;
import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanTeamActions;
import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanTeamHistory;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanFanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTeamHistoryLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.plans.util.Indexer;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.MembershipRequestImpl;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

public class PlanItemImpl extends PlanItemModelImpl implements PlanItem {
    private final static Log _log = LogFactoryUtil.getLog(PlanItemImpl.class);

    public static List<PlanItem> getPlans() throws SystemException {
        return PlanItemLocalServiceUtil.getPlans();
    }

    public PlanItemImpl() {
    }

    /* Description related stuff */
    public String getDescription() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getCurrentForPlan(this).getDescription();
    }

    public String getName() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getCurrentForPlan(this).getName();
    }

    public void setDescription(String description, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.DESCRIPTION_UPDATED, updateAuthorId);

        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setDescription(description);
        planDescription.store();
        updateAttribute(Attribute.DESCRIPTION);
        
       //joinIfNotAMember(updateAuthorId);
        updateSearchIndex();
    }

    public void setName(String name, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.NAME_UPDATED, updateAuthorId);
        // update plan name attribute

        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setName(name);
        planDescription.store();
        updateAttribute(Attribute.NAME);
        //joinIfNotAMember(updateAuthorId);
        updateSearchIndex();
    }

    public List<PlanDescription> getAllDescriptionVersions() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getAllForPlan(this);
    }

    /**
     * List of all versions of PlanDescription objects related to given plan
     * 
     * @see com.ext.portlet.plans.model.PlanItem#getPlanDescriptions()
     */
    public List<PlanDescription> getPlanDescriptions() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getAllForPlan(this);
    }

    /*
     * 
     * Scenarios
     */
    public Long getScenarioId() throws SystemException {
        return PlanModelRunLocalServiceUtil.getCurrentForPlan(this).getScenarioId();
    }

    public void setScenarioId(Long scenarioId, Long authorId) throws SystemException, PortalException {
        newVersion(UpdateType.SCENARIO_UPDATED, authorId);

        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.createNewVersionForPlan(this);
        planModelRun.setScenarioId(scenarioId);
        planModelRun.store();

        // update plan attributes to reflect values from new scenario
        PlanType planType = getPlanType();

        for (PlanConstants.Attribute attribute : PlanConstants.Attribute.getPlanTypeAttributes(planType)) {
            updateAttribute(attribute);
        }
        
        //joinIfNotAMember(authorId);
    }

    public void setModelId(Long simulationId, Long authorId) throws SystemException, PortalException {

        PlanType planType = getPlanType();
        List<Simulation> sims = planType.getAvailableModels();
        boolean found = false;
        for (Simulation sim : sims) {
            if (simulationId.equals(sim.getId())) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new SystemException("Model id " + simulationId + " not valid for planType");
        }

        newVersion(UpdateType.MODEL_UPDATED, authorId);
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setModelId(simulationId);
        planMeta.store();

        setScenarioId(null, authorId);
        //joinIfNotAMember(authorId);
    }

    public List<PlanModelRun> getAllPlanModelRuns() throws SystemException {
        return PlanModelRunLocalServiceUtil.getAllForPlan(this);
    }

    /*
     * Plan meta informations.
     */

    public PlanMeta getPlanMeta() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this);
    }

    public List<PlanMeta> getAllPlanMetas() throws SystemException {
        return PlanMetaLocalServiceUtil.getAllForPlan(this);
    }

    public Long getPlanTypeId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPlanTypeId();
    }

    public PlanType getPlanType() throws SystemException, PortalException {
        return PlanTypeLocalServiceUtil.getPlanType(getPlanTypeId());
    }

    public Contest getContest() throws SystemException, PortalException {
       ContestPhase phase = getContestPhase();
        return phase == null?null:phase.getContest();
    }

    public ContestPhase getContestPhase() throws SystemException, PortalException {
        Long phase =  getPlanMeta().getContestPhase();
        return phase == null?null:ContestPhaseLocalServiceUtil.getContestPhase(phase);
    }

    public void setContestPhase(ContestPhase phase, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_TYPE_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setContestPhase(phase.getContestPhasePK());
        planMeta.store();

        //joinIfNotAMember(updateAuthorId);
    }

    public void setPlanTypeId(Long planTypeId, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.PLAN_TYPE_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setPlanTypeId(planTypeId);
        planMeta.store();
        
        //joinIfNotAMember(updateAuthorId);
    }

    public Long getMBCategoryId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getMbCategoryId();
    }

    public void setMBCategoryId(Long mbCategoryId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.MB_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setMbCategoryId(mbCategoryId);
        planMeta.store();
    }

    public Long getCategoryGroupId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getCategoryGroupId();
    }

    public void setCategoryGroupId(Long categoryGroupId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.MB_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setCategoryGroupId(categoryGroupId);
        planMeta.store();
    }

    public Long getPlanGroupId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPlanGroupId();
    }

    public void setPlanGroupId(Long groupId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setPlanGroupId(groupId);
        planMeta.store();
    }

    public Long getAuthorId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getAuthorId();
    }

    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getAuthorId());
    }

    public void setAuthorId(Long authorId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setAuthorId(authorId);
        planMeta.store();
    }

    public Date getCreateDate() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getCreated();
    }

    public Date getPublishDate() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getCreated();
    }

    public String getCreator() throws PortalException, SystemException {
        return getAuthor().getScreenName();

    }

    public Integer getVotes() throws SystemException {
        //return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getVotes();
        return PlanVoteLocalServiceUtil.countPlanVotesByPlanId(this.getPlanId());
    }

    public boolean getOpen() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getOpen();
    }

    public void setOpen(boolean open, Long updateAuthorId) throws SystemException {
        if (open) {
            newVersion(UpdateType.PLAN_OPENED, updateAuthorId);
        }
        else {
            newVersion(UpdateType.PLAN_CLOSED, updateAuthorId);
            
        }

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setOpen(open);
        planMeta.store();
        updateAttribute(Attribute.IS_PLAN_OPEN);

    }

    public String getStatus() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getStatus();
    }

    public void setStatus(String status, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_STATUS_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setStatus(status);
        planMeta.store();
        updateAttribute(PlanConstants.Attribute.STATUS);

    }

    /*
     * POSITIONS
     */

    public PlanPositions getPlanPositions() throws NoSuchPlanPositionsException, SystemException {
        return PlanPositionsLocalServiceUtil.getCurrentForPlan(this);
    }

    public List<Long> getPositionsIds() throws SystemException, NoSuchPlanPositionsException {
        PlanPositions x = PlanPositionsLocalServiceUtil.getCurrentForPlan(this);
        return x.getPositionsIds();
    }

    public Long[] getPositionsIdsArray() throws SystemException, NoSuchPlanPositionsException {
        List<Long> idsList = getPositionsIds();
        Long[] ret = new Long[idsList.size()];
        return idsList.toArray(ret);
    }

    public void setPositions(List<Long> positionsIds, Long updateAuthorId) throws PortalException, SystemException {
        newVersion(UpdateType.PLAN_POSITIONS_UPDATED, updateAuthorId);

        PlanPositions planPositions = PlanPositionsLocalServiceUtil.createNewVersionForPlan(this);
        planPositions.store();
        planPositions.setPositionsIds(positionsIds);
        updateAttribute(Attribute.POSITIONS);
        
        //joinIfNotAMember(updateAuthorId);
    }

    public List<PlanPositions> getAllPositionsVersions() throws SystemException {
        return PlanPositionsLocalServiceUtil.getAllForPlan(this);
    }

    /*
     * VOTES
     */
    public boolean hasUserVoted(Long userId) throws PortalException, SystemException {
        try {
            PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(userId, getContest().getContestPK());
            return vote.getPlanId().equals(getPlanId());
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
        return false;
    }

    public void vote(Long userId) throws PortalException, SystemException {
        if (PlanVoteLocalServiceUtil.voteForPlan(getPlanId(), userId)) {
            PlanMetaLocalServiceUtil.getCurrentForPlan(this).vote();
        }
        updateAttribute(Attribute.VOTES);
    }

    public void unvote(Long userId) throws PortalException, SystemException {
        if (PlanVoteLocalServiceUtil.unvote(userId, this.getContest().getContestPK())) {
            PlanMetaLocalServiceUtil.getCurrentForPlan(this).unvote();
        }
        updateAttribute(Attribute.VOTES);
    }

    public List<PlanItem> getAllVersions() throws SystemException {
        return PlanItemLocalServiceUtil.getAllVersions(this);
    }

    private PlanItem newVersion(UpdateType updateType, Long updateAuthorId) throws SystemException {
        PlanItem latestVersion = this;
        try {
            /*
             *
             // i don't think that this is a problem
            if (!latestVersion.equals()) {
                throw new SystemException(
                        "Can only create a new version of a plan from the latest version in existence");
            }
            */
            latestVersion = PlanItemLocalServiceUtil.getPlan(this.getPlanId());
        } catch (NoSuchPlanItemException e) {
            // ignore
        }
        PlanItem oldVersion = (PlanItem) latestVersion.clone();
        //newVersion.setId();
        oldVersion.store();

        this.setId(CounterLocalServiceUtil.increment(PlanItem.class.getName()));
        this.setVersion(Math.max(getVersion(), latestVersion.getVersion()) + 1);
        this.setUpdated(new Date());
        this.setUpdateType(updateType.name());
        this.setUpdateAuthorId(updateAuthorId);
        this.setNew(true);
        this.store();

        return this;
    }

    public void store() throws SystemException {
        if (this.isNew()) {
            PlanItemLocalServiceUtil.addPlanItem(this);
        } else {
            PlanItemLocalServiceUtil.updatePlanItem(this);
        }
    }

    /**
     * Updates values of all available attributes.
     * 
     * @throws SystemException
     */

    public void updateAllAttributes() throws SystemException {
        for (Attribute attribute : Attribute.values()) {
            updateAttribute(attribute);
        }
    }

    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attributeName
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    public void updateAttribute(String attributeName) throws SystemException {
        updateAttribute(Attribute.valueOf(attributeName));
    }

    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attribute
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    private void updateAttribute(Attribute attribute) throws SystemException {
        String value = attribute.calculateValue(this).toString();
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(getPlanId(), attribute.name());
        if (att != null) {
            if (! att.getAttributeValue().equals(value)) {
                att.setAttributeValue(value);
                PlanAttributeLocalServiceUtil.updatePlanAttribute(att);
            }
        } else {
            PlanAttributeLocalServiceUtil.addPlanAttribute(getPlanId(), attribute.name(), value);
        }
    }
    
    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attribute
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    private void setAttribute(Attribute attribute, String value) throws SystemException {
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(getPlanId(), attribute.name());
        if (att != null) {
            att.setAttributeValue(value);
            PlanAttributeLocalServiceUtil.updatePlanAttribute(att);
        }
        else {
            PlanAttributeLocalServiceUtil.addPlanAttribute(getPlanId(), attribute.name(), value);
        }
    }

    public List<PlanAttribute> getPlanAttributes() throws SystemException {
        return PlanItemLocalServiceUtil.getPlanAttributes(this);
    }

    public PlanAttribute getPlanAttribute(String name) throws SystemException {
        return PlanItemLocalServiceUtil.getPlanAttribute(this, name);
    }

    /*
     * Plan membership related stuff
     */

    /**
     * Returns list of plan members.
     */
    public List<User> getMembers() throws SystemException {
        return UserLocalServiceUtil.getGroupUsers(getPlanGroupId());
    }

    public List<MembershipRequest> getMembershipRequests() throws SystemException {
        return MembershipRequestLocalServiceUtil.search(getPlanGroupId(), MembershipRequestImpl.STATUS_PENDING, 0,
                Integer.MAX_VALUE);
    }

    public void addMembershipRequest(Long userId, String comments) throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_REQUESTED.name(), userId);
        MembershipRequestLocalServiceUtil.addMembershipRequest(userId, getPlanGroupId(), comments);
    }

    public void dennyMembershipRequest(Long userId, MembershipRequest request, String reply, Long updateAuthorId)
            throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_DECLEINED.name(), updateAuthorId);
        MembershipRequestLocalServiceUtil.updateStatus(userId, request.getMembershipRequestId(), reply,
                MembershipRequestImpl.STATUS_DENIED);
    }

    public void approveMembershipRequest(Long userId, MembershipRequest request, String reply, Long updateAuthorId)
            throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_APPROVED.name(), updateAuthorId);
        MembershipRequestLocalServiceUtil.updateStatus(userId, request.getMembershipRequestId(), reply,
                MembershipRequestImpl.STATUS_APPROVED);
    }

    /*
     * Plan actions, publishing/deleting
     */
    public void publish(Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.PLAN_PUBLISHED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setPlanTypeId(getPlanType().getPublishedCounterpartId());
        planMeta.store();
    }

    public void delete(Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_DELETED, updateAuthorId);
        this.setState(EntityState.DELETED.name());
        this.store();
        
        try {
            Indexer.deleteEntry(10112L, getPlanId());
        } catch (SearchException e) {
            _log.error("can't remove plan " + getPlanId() + " from search index", e);
        }
    }

    public User getUpdateAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUpdateAuthorId());
    }

    public List<PlanFan> getFans() throws SystemException {
        return PlanFanLocalServiceUtil.getPlanFansForPlan(this.getPlanId());
    }

    public PlanFan addFan(Long userId) throws SystemException {
        if (!isUserAFan(userId)) {
            PlanFan planFan = PlanFanLocalServiceUtil.addFan(this.getPlanId(), userId);
            setAttribute(Attribute.SUPPORTERS, String.valueOf(PlanFanLocalServiceUtil.countPlanFansForPlan(getPlanId())));
            return planFan;
        }
        return null;
    }

    public void removeFan(Long userId) throws SystemException {
        if (isUserAFan(userId)) {
            PlanFanLocalServiceUtil.removePlanFan(this.getPlanId(), userId);
            setAttribute(Attribute.SUPPORTERS, String.valueOf(PlanFanLocalServiceUtil.countPlanFansForPlan(getPlanId())));
        }
    }

    public boolean isUserAFan(Long userId) throws SystemException {
        try {
            return PlanFanLocalServiceUtil.getPlanFanByPlanIdUserId(this.getPlanId(), userId) != null;
        } catch (NoSuchPlanFanException e) {
            return false;
        }
    }

    public boolean isUserAMember(Long userId) throws SystemException {
        return GroupLocalServiceUtil.hasUserGroup(userId, getPlanGroupId());
    }

    public boolean hasUserRequestedMembership(Long userId) throws SystemException {
        try {
            PlanTeamHistory action = PlanTeamHistoryLocalServiceUtil.getLastUserActionInPlan(getPlanId(), userId);
            if (action.getAction().equals(PlanTeamActions.MEMBERSHIP_REQUESTED.name())) {
                return true;
            }
        } catch (NoSuchPlanTeamHistoryException e) {
            // ignore
        }
        return false;
    }

    public boolean isAdmin(Long userId) throws PortalException, SystemException {
        return UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, getPlanGroupId(),
                RoleConstants.COMMUNITY_ADMINISTRATOR)
                || isOwner(userId);
    }

    public boolean isOwner(Long userId) throws PortalException, SystemException {
        return UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, getPlanGroupId(), RoleConstants.COMMUNITY_OWNER);
    }

    public void setUserPermission(Long userId, String userPermission, Long updateAuthorId) throws SystemException,
            PortalException {
        PlanUserPermission userPerm = PlanUserPermission.valueOf(userPermission);

        Group group = GroupLocalServiceUtil.getGroup(getPlanGroupId());
        UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(userId, new long[] { getPlanGroupId() });
        String roleName = RoleConstants.COMMUNITY_MEMBER;

        if (userPerm == PlanUserPermission.OWNER) {
            roleName = RoleConstants.COMMUNITY_OWNER;
        } else if (userPerm == PlanUserPermission.ADMIN) {
            roleName = RoleConstants.COMMUNITY_ADMINISTRATOR;
        }

        Role role = RoleLocalServiceUtil.getRole(group.getCompanyId(), roleName);
        UserGroupRoleLocalServiceUtil.addUserGroupRoles(userId, getPlanGroupId(), new long[] { role.getRoleId() });
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId, PlanTeamActions.PERMISSIONS_CHANGED.name(),
                userPermission, updateAuthorId);
    }

    public void removeMember(Long userId, Long updateAuthorId) throws SystemException {
        UserLocalServiceUtil.unsetGroupUsers(getPlanGroupId(), new long[] { userId });
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId, PlanTeamActions.MEMBER_REMOVED.name(),
                updateAuthorId);
    }
    

    public void joinIfNotAMember(Long userId) throws SystemException, PortalException {
        if (! isUserAMember(userId)) {
            GroupLocalServiceUtil.addUserGroups(userId, new long[] {getPlanGroupId()});
            PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId, PlanTeamActions.MEMBERSHIP_APPROVED.name(),
                    userId);
        }
        
    }
    
    public void setSeekingAssistance(boolean seekingAssistance) throws SystemException {
        setAttribute(Attribute.SEEKING_ASSISTANCE, String.valueOf(seekingAssistance));
    }
    
    public boolean isSeekingAssistance() throws SystemException {
        Object value = Attribute.SEEKING_ASSISTANCE.getValue(this);
        if ("true".equals(value)) {
            return true;
        }
        return false;
    }
    

    private void updateSearchIndex() throws SearchException, SystemException {
        Indexer.updateEntry(10112L, this);
        
    }
    
    public DiscussionCategoryGroup getDiscussionCategoryGroup() throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(getCategoryGroupId());
        
    }
    
    public PlanItem promote(User user) throws SystemException, PortalException {
        ContestPhase contestPhase = getContestPhase().getNextContestPhase();
        
        PlanMeta planMeta = PlanMetaLocalServiceUtil.getCurrentForPlan(this);
        planMeta.setPromoted(true);
        planMeta.setPreviousContestPhase(planMeta.getContestPhase());
        planMeta.setContestPhase(contestPhase.getContestPhasePK());
        
        planMeta.store();

        return this;
    }
    
    public boolean getPromoted() throws SystemException {
        Boolean promoted = PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPromoted();
        return promoted != null ? promoted : false;
        
    }
    
    public int getCommentsCount() throws PortalException, SystemException {
        return getDiscussionCategoryGroup().getCommentsCount();
    }

}
