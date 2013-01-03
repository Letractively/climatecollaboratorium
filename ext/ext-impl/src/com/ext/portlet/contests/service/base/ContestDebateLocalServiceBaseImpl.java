package com.ext.portlet.contests.service.base;

import com.ext.portlet.contests.model.ContestDebate;
import com.ext.portlet.contests.service.ContestDebateLocalService;
import com.ext.portlet.contests.service.ContestDebateService;
import com.ext.portlet.contests.service.ContestLocalService;
import com.ext.portlet.contests.service.ContestPhaseColumnLocalService;
import com.ext.portlet.contests.service.ContestPhaseColumnService;
import com.ext.portlet.contests.service.ContestPhaseLocalService;
import com.ext.portlet.contests.service.ContestPhaseService;
import com.ext.portlet.contests.service.ContestPhaseStatusLocalService;
import com.ext.portlet.contests.service.ContestPhaseStatusService;
import com.ext.portlet.contests.service.ContestService;
import com.ext.portlet.contests.service.ContestTeamMemberLocalService;
import com.ext.portlet.contests.service.ContestTeamMemberService;
import com.ext.portlet.contests.service.persistence.ContestDebatePersistence;
import com.ext.portlet.contests.service.persistence.ContestPersistence;
import com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.contests.service.persistence.ContestPhasePersistence;
import com.ext.portlet.contests.service.persistence.ContestPhaseStatusPersistence;
import com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class ContestDebateLocalServiceBaseImpl
    implements ContestDebateLocalService {
    @BeanReference(name = "com.ext.portlet.contests.service.ContestLocalService.impl")
    protected ContestLocalService contestLocalService;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestService.impl")
    protected ContestService contestService;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPersistence.impl")
    protected ContestPersistence contestPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestDebateLocalService.impl")
    protected ContestDebateLocalService contestDebateLocalService;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestDebateService.impl")
    protected ContestDebateService contestDebateService;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestDebatePersistence.impl")
    protected ContestDebatePersistence contestDebatePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestPhaseLocalService.impl")
    protected ContestPhaseLocalService contestPhaseLocalService;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestPhaseService.impl")
    protected ContestPhaseService contestPhaseService;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhasePersistence.impl")
    protected ContestPhasePersistence contestPhasePersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestPhaseStatusLocalService.impl")
    protected ContestPhaseStatusLocalService contestPhaseStatusLocalService;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestPhaseStatusService.impl")
    protected ContestPhaseStatusService contestPhaseStatusService;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhaseStatusPersistence.impl")
    protected ContestPhaseStatusPersistence contestPhaseStatusPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestPhaseColumnLocalService.impl")
    protected ContestPhaseColumnLocalService contestPhaseColumnLocalService;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestPhaseColumnService.impl")
    protected ContestPhaseColumnService contestPhaseColumnService;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence.impl")
    protected ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestTeamMemberLocalService.impl")
    protected ContestTeamMemberLocalService contestTeamMemberLocalService;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestTeamMemberService.impl")
    protected ContestTeamMemberService contestTeamMemberService;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence.impl")
    protected ContestTeamMemberPersistence contestTeamMemberPersistence;

    public ContestDebate addContestDebate(ContestDebate contestDebate)
        throws SystemException {
        contestDebate.setNew(true);

        return contestDebatePersistence.update(contestDebate, false);
    }

    public ContestDebate createContestDebate(Long id) {
        return contestDebatePersistence.create(id);
    }

    public void deleteContestDebate(Long id)
        throws PortalException, SystemException {
        contestDebatePersistence.remove(id);
    }

    public void deleteContestDebate(ContestDebate contestDebate)
        throws SystemException {
        contestDebatePersistence.remove(contestDebate);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return contestDebatePersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return contestDebatePersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public ContestDebate getContestDebate(Long id)
        throws PortalException, SystemException {
        return contestDebatePersistence.findByPrimaryKey(id);
    }

    public List<ContestDebate> getContestDebates(int start, int end)
        throws SystemException {
        return contestDebatePersistence.findAll(start, end);
    }

    public int getContestDebatesCount() throws SystemException {
        return contestDebatePersistence.countAll();
    }

    public ContestDebate updateContestDebate(ContestDebate contestDebate)
        throws SystemException {
        contestDebate.setNew(false);

        return contestDebatePersistence.update(contestDebate, true);
    }

    public ContestDebate updateContestDebate(ContestDebate contestDebate,
        boolean merge) throws SystemException {
        contestDebate.setNew(false);

        return contestDebatePersistence.update(contestDebate, merge);
    }

    public ContestLocalService getContestLocalService() {
        return contestLocalService;
    }

    public void setContestLocalService(ContestLocalService contestLocalService) {
        this.contestLocalService = contestLocalService;
    }

    public ContestService getContestService() {
        return contestService;
    }

    public void setContestService(ContestService contestService) {
        this.contestService = contestService;
    }

    public ContestPersistence getContestPersistence() {
        return contestPersistence;
    }

    public void setContestPersistence(ContestPersistence contestPersistence) {
        this.contestPersistence = contestPersistence;
    }

    public ContestDebateLocalService getContestDebateLocalService() {
        return contestDebateLocalService;
    }

    public void setContestDebateLocalService(
        ContestDebateLocalService contestDebateLocalService) {
        this.contestDebateLocalService = contestDebateLocalService;
    }

    public ContestDebateService getContestDebateService() {
        return contestDebateService;
    }

    public void setContestDebateService(
        ContestDebateService contestDebateService) {
        this.contestDebateService = contestDebateService;
    }

    public ContestDebatePersistence getContestDebatePersistence() {
        return contestDebatePersistence;
    }

    public void setContestDebatePersistence(
        ContestDebatePersistence contestDebatePersistence) {
        this.contestDebatePersistence = contestDebatePersistence;
    }

    public ContestPhaseLocalService getContestPhaseLocalService() {
        return contestPhaseLocalService;
    }

    public void setContestPhaseLocalService(
        ContestPhaseLocalService contestPhaseLocalService) {
        this.contestPhaseLocalService = contestPhaseLocalService;
    }

    public ContestPhaseService getContestPhaseService() {
        return contestPhaseService;
    }

    public void setContestPhaseService(ContestPhaseService contestPhaseService) {
        this.contestPhaseService = contestPhaseService;
    }

    public ContestPhasePersistence getContestPhasePersistence() {
        return contestPhasePersistence;
    }

    public void setContestPhasePersistence(
        ContestPhasePersistence contestPhasePersistence) {
        this.contestPhasePersistence = contestPhasePersistence;
    }

    public ContestPhaseStatusLocalService getContestPhaseStatusLocalService() {
        return contestPhaseStatusLocalService;
    }

    public void setContestPhaseStatusLocalService(
        ContestPhaseStatusLocalService contestPhaseStatusLocalService) {
        this.contestPhaseStatusLocalService = contestPhaseStatusLocalService;
    }

    public ContestPhaseStatusService getContestPhaseStatusService() {
        return contestPhaseStatusService;
    }

    public void setContestPhaseStatusService(
        ContestPhaseStatusService contestPhaseStatusService) {
        this.contestPhaseStatusService = contestPhaseStatusService;
    }

    public ContestPhaseStatusPersistence getContestPhaseStatusPersistence() {
        return contestPhaseStatusPersistence;
    }

    public void setContestPhaseStatusPersistence(
        ContestPhaseStatusPersistence contestPhaseStatusPersistence) {
        this.contestPhaseStatusPersistence = contestPhaseStatusPersistence;
    }

    public ContestPhaseColumnLocalService getContestPhaseColumnLocalService() {
        return contestPhaseColumnLocalService;
    }

    public void setContestPhaseColumnLocalService(
        ContestPhaseColumnLocalService contestPhaseColumnLocalService) {
        this.contestPhaseColumnLocalService = contestPhaseColumnLocalService;
    }

    public ContestPhaseColumnService getContestPhaseColumnService() {
        return contestPhaseColumnService;
    }

    public void setContestPhaseColumnService(
        ContestPhaseColumnService contestPhaseColumnService) {
        this.contestPhaseColumnService = contestPhaseColumnService;
    }

    public ContestPhaseColumnPersistence getContestPhaseColumnPersistence() {
        return contestPhaseColumnPersistence;
    }

    public void setContestPhaseColumnPersistence(
        ContestPhaseColumnPersistence contestPhaseColumnPersistence) {
        this.contestPhaseColumnPersistence = contestPhaseColumnPersistence;
    }

    public ContestTeamMemberLocalService getContestTeamMemberLocalService() {
        return contestTeamMemberLocalService;
    }

    public void setContestTeamMemberLocalService(
        ContestTeamMemberLocalService contestTeamMemberLocalService) {
        this.contestTeamMemberLocalService = contestTeamMemberLocalService;
    }

    public ContestTeamMemberService getContestTeamMemberService() {
        return contestTeamMemberService;
    }

    public void setContestTeamMemberService(
        ContestTeamMemberService contestTeamMemberService) {
        this.contestTeamMemberService = contestTeamMemberService;
    }

    public ContestTeamMemberPersistence getContestTeamMemberPersistence() {
        return contestTeamMemberPersistence;
    }

    public void setContestTeamMemberPersistence(
        ContestTeamMemberPersistence contestTeamMemberPersistence) {
        this.contestTeamMemberPersistence = contestTeamMemberPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
