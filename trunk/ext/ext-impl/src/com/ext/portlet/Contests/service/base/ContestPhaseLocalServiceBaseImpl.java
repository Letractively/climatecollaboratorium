package com.ext.portlet.contests.service.base;

import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestLocalService;
import com.ext.portlet.contests.service.ContestPhaseLocalService;
import com.ext.portlet.contests.service.ContestPhaseService;
import com.ext.portlet.contests.service.ContestService;
import com.ext.portlet.contests.service.persistence.ContestPersistence;
import com.ext.portlet.contests.service.persistence.ContestPhasePersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class ContestPhaseLocalServiceBaseImpl
    implements ContestPhaseLocalService {
    @BeanReference(name = "com.ext.portlet.contests.service.ContestLocalService.impl")
    protected ContestLocalService contestLocalService;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestService.impl")
    protected ContestService contestService;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPersistence.impl")
    protected ContestPersistence contestPersistence;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestPhaseLocalService.impl")
    protected ContestPhaseLocalService contestPhaseLocalService;
    @BeanReference(name = "com.ext.portlet.contests.service.ContestPhaseService.impl")
    protected ContestPhaseService contestPhaseService;
    @BeanReference(name = "com.ext.portlet.contests.service.persistence.ContestPhasePersistence.impl")
    protected ContestPhasePersistence contestPhasePersistence;

    public ContestPhase addContestPhase(ContestPhase contestPhase)
        throws SystemException {
        contestPhase.setNew(true);

        return contestPhasePersistence.update(contestPhase, false);
    }

    public ContestPhase createContestPhase(Long ContestPhasePK) {
        return contestPhasePersistence.create(ContestPhasePK);
    }

    public void deleteContestPhase(Long ContestPhasePK)
        throws PortalException, SystemException {
        contestPhasePersistence.remove(ContestPhasePK);
    }

    public void deleteContestPhase(ContestPhase contestPhase)
        throws SystemException {
        contestPhasePersistence.remove(contestPhase);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return contestPhasePersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return contestPhasePersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public ContestPhase getContestPhase(Long ContestPhasePK)
        throws PortalException, SystemException {
        return contestPhasePersistence.findByPrimaryKey(ContestPhasePK);
    }

    public List<ContestPhase> getContestPhases(int start, int end)
        throws SystemException {
        return contestPhasePersistence.findAll(start, end);
    }

    public int getContestPhasesCount() throws SystemException {
        return contestPhasePersistence.countAll();
    }

    public ContestPhase updateContestPhase(ContestPhase contestPhase)
        throws SystemException {
        contestPhase.setNew(false);

        return contestPhasePersistence.update(contestPhase, true);
    }

    public ContestPhase updateContestPhase(ContestPhase contestPhase,
        boolean merge) throws SystemException {
        contestPhase.setNew(false);

        return contestPhasePersistence.update(contestPhase, merge);
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

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
