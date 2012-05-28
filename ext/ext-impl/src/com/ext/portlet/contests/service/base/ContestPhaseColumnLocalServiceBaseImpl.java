package com.ext.portlet.contests.service.base;

import com.ext.portlet.contests.model.ContestPhaseColumn;
import com.ext.portlet.contests.service.ContestDebateLocalService;
import com.ext.portlet.contests.service.ContestDebateService;
import com.ext.portlet.contests.service.ContestLocalService;
import com.ext.portlet.contests.service.ContestPhaseColumnLocalService;
import com.ext.portlet.contests.service.ContestPhaseColumnService;
import com.ext.portlet.contests.service.ContestPhaseLocalService;
import com.ext.portlet.contests.service.ContestPhaseService;
import com.ext.portlet.contests.service.ContestService;
import com.ext.portlet.contests.service.ContestTeamMemberLocalService;
import com.ext.portlet.contests.service.ContestTeamMemberService;
import com.ext.portlet.contests.service.persistence.ContestDebatePersistence;
import com.ext.portlet.contests.service.persistence.ContestPersistence;
import com.ext.portlet.contests.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.contests.service.persistence.ContestPhasePersistence;
import com.ext.portlet.contests.service.persistence.ContestTeamMemberPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class ContestPhaseColumnLocalServiceBaseImpl
    implements ContestPhaseColumnLocalService {
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

    public ContestPhaseColumn addContestPhaseColumn(
        ContestPhaseColumn contestPhaseColumn) throws SystemException {
        contestPhaseColumn.setNew(true);

        return contestPhaseColumnPersistence.update(contestPhaseColumn, false);
    }

    public ContestPhaseColumn createContestPhaseColumn(Long id) {
        return contestPhaseColumnPersistence.create(id);
    }

    public void deleteContestPhaseColumn(Long id)
        throws PortalException, SystemException {
        contestPhaseColumnPersistence.remove(id);
    }

    public void deleteContestPhaseColumn(ContestPhaseColumn contestPhaseColumn)
        throws SystemException {
        contestPhaseColumnPersistence.remove(contestPhaseColumn);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return contestPhaseColumnPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return contestPhaseColumnPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public ContestPhaseColumn getContestPhaseColumn(Long id)
        throws PortalException, SystemException {
        return contestPhaseColumnPersistence.findByPrimaryKey(id);
    }

    public List<ContestPhaseColumn> getContestPhaseColumns(int start, int end)
        throws SystemException {
        return contestPhaseColumnPersistence.findAll(start, end);
    }

    public int getContestPhaseColumnsCount() throws SystemException {
        return contestPhaseColumnPersistence.countAll();
    }

    public ContestPhaseColumn updateContestPhaseColumn(
        ContestPhaseColumn contestPhaseColumn) throws SystemException {
        contestPhaseColumn.setNew(false);

        return contestPhaseColumnPersistence.update(contestPhaseColumn, true);
    }

    public ContestPhaseColumn updateContestPhaseColumn(
        ContestPhaseColumn contestPhaseColumn, boolean merge)
        throws SystemException {
        contestPhaseColumn.setNew(false);

        return contestPhaseColumnPersistence.update(contestPhaseColumn, merge);
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
