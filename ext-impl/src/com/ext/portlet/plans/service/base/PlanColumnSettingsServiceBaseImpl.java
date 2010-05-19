package com.ext.portlet.plans.service.base;

import com.ext.portlet.plans.service.PlanAttributeFilterLocalService;
import com.ext.portlet.plans.service.PlanAttributeFilterService;
import com.ext.portlet.plans.service.PlanAttributeLocalService;
import com.ext.portlet.plans.service.PlanAttributeService;
import com.ext.portlet.plans.service.PlanColumnSettingsLocalService;
import com.ext.portlet.plans.service.PlanColumnSettingsService;
import com.ext.portlet.plans.service.PlanLocalService;
import com.ext.portlet.plans.service.PlanPositionLocalService;
import com.ext.portlet.plans.service.PlanPositionService;
import com.ext.portlet.plans.service.PlanPropertyFilterLocalService;
import com.ext.portlet.plans.service.PlanPropertyFilterService;
import com.ext.portlet.plans.service.PlanService;
import com.ext.portlet.plans.service.PlanTypeAttributeLocalService;
import com.ext.portlet.plans.service.PlanTypeAttributeService;
import com.ext.portlet.plans.service.PlanTypeColumnLocalService;
import com.ext.portlet.plans.service.PlanTypeColumnService;
import com.ext.portlet.plans.service.PlanTypeLocalService;
import com.ext.portlet.plans.service.PlanTypeService;
import com.ext.portlet.plans.service.PlanVoteLocalService;
import com.ext.portlet.plans.service.PlanVoteService;
import com.ext.portlet.plans.service.PlansFilterLocalService;
import com.ext.portlet.plans.service.PlansFilterPositionLocalService;
import com.ext.portlet.plans.service.PlansFilterPositionService;
import com.ext.portlet.plans.service.PlansFilterService;
import com.ext.portlet.plans.service.PlansUserSettingsLocalService;
import com.ext.portlet.plans.service.PlansUserSettingsService;
import com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlanAttributePersistence;
import com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.plans.service.persistence.PlanPersistence;
import com.ext.portlet.plans.service.persistence.PlanPositionPersistence;
import com.ext.portlet.plans.service.persistence.PlanPropertyFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistence;
import com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistence;
import com.ext.portlet.plans.service.persistence.PlanTypePersistence;
import com.ext.portlet.plans.service.persistence.PlanVotePersistence;
import com.ext.portlet.plans.service.persistence.PlansFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.plans.service.persistence.PlansUserSettingsPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class PlanColumnSettingsServiceBaseImpl extends PrincipalBean
    implements PlanColumnSettingsService {
    @BeanReference(name = "com.ext.portlet.plans.service.PlanLocalService.impl")
    protected PlanLocalService planLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanService.impl")
    protected PlanService planService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPersistence.impl")
    protected PlanPersistence planPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanAttributeLocalService.impl")
    protected PlanAttributeLocalService planAttributeLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanAttributeService.impl")
    protected PlanAttributeService planAttributeService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanAttributePersistence.impl")
    protected PlanAttributePersistence planAttributePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanPositionLocalService.impl")
    protected PlanPositionLocalService planPositionLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanPositionService.impl")
    protected PlanPositionService planPositionService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPositionPersistence.impl")
    protected PlanPositionPersistence planPositionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlansUserSettingsLocalService.impl")
    protected PlansUserSettingsLocalService plansUserSettingsLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlansUserSettingsService.impl")
    protected PlansUserSettingsService plansUserSettingsService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlansUserSettingsPersistence.impl")
    protected PlansUserSettingsPersistence plansUserSettingsPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanVoteLocalService.impl")
    protected PlanVoteLocalService planVoteLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanVoteService.impl")
    protected PlanVoteService planVoteService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanVotePersistence.impl")
    protected PlanVotePersistence planVotePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlansFilterLocalService.impl")
    protected PlansFilterLocalService plansFilterLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlansFilterService.impl")
    protected PlansFilterService plansFilterService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlansFilterPersistence.impl")
    protected PlansFilterPersistence plansFilterPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanAttributeFilterLocalService.impl")
    protected PlanAttributeFilterLocalService planAttributeFilterLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanAttributeFilterService.impl")
    protected PlanAttributeFilterService planAttributeFilterService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistence.impl")
    protected PlanAttributeFilterPersistence planAttributeFilterPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanPropertyFilterLocalService.impl")
    protected PlanPropertyFilterLocalService planPropertyFilterLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanPropertyFilterService.impl")
    protected PlanPropertyFilterService planPropertyFilterService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanPropertyFilterPersistence.impl")
    protected PlanPropertyFilterPersistence planPropertyFilterPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanColumnSettingsLocalService.impl")
    protected PlanColumnSettingsLocalService planColumnSettingsLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanColumnSettingsService.impl")
    protected PlanColumnSettingsService planColumnSettingsService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistence.impl")
    protected PlanColumnSettingsPersistence planColumnSettingsPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlansFilterPositionLocalService.impl")
    protected PlansFilterPositionLocalService plansFilterPositionLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlansFilterPositionService.impl")
    protected PlansFilterPositionService plansFilterPositionService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlansFilterPositionPersistence.impl")
    protected PlansFilterPositionPersistence plansFilterPositionPersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanTypeLocalService.impl")
    protected PlanTypeLocalService planTypeLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanTypeService.impl")
    protected PlanTypeService planTypeService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTypePersistence.impl")
    protected PlanTypePersistence planTypePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanTypeAttributeLocalService.impl")
    protected PlanTypeAttributeLocalService planTypeAttributeLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanTypeAttributeService.impl")
    protected PlanTypeAttributeService planTypeAttributeService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistence.impl")
    protected PlanTypeAttributePersistence planTypeAttributePersistence;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanTypeColumnLocalService.impl")
    protected PlanTypeColumnLocalService planTypeColumnLocalService;
    @BeanReference(name = "com.ext.portlet.plans.service.PlanTypeColumnService.impl")
    protected PlanTypeColumnService planTypeColumnService;
    @BeanReference(name = "com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistence.impl")
    protected PlanTypeColumnPersistence planTypeColumnPersistence;

    public PlanLocalService getPlanLocalService() {
        return planLocalService;
    }

    public void setPlanLocalService(PlanLocalService planLocalService) {
        this.planLocalService = planLocalService;
    }

    public PlanService getPlanService() {
        return planService;
    }

    public void setPlanService(PlanService planService) {
        this.planService = planService;
    }

    public PlanPersistence getPlanPersistence() {
        return planPersistence;
    }

    public void setPlanPersistence(PlanPersistence planPersistence) {
        this.planPersistence = planPersistence;
    }

    public PlanAttributeLocalService getPlanAttributeLocalService() {
        return planAttributeLocalService;
    }

    public void setPlanAttributeLocalService(
        PlanAttributeLocalService planAttributeLocalService) {
        this.planAttributeLocalService = planAttributeLocalService;
    }

    public PlanAttributeService getPlanAttributeService() {
        return planAttributeService;
    }

    public void setPlanAttributeService(
        PlanAttributeService planAttributeService) {
        this.planAttributeService = planAttributeService;
    }

    public PlanAttributePersistence getPlanAttributePersistence() {
        return planAttributePersistence;
    }

    public void setPlanAttributePersistence(
        PlanAttributePersistence planAttributePersistence) {
        this.planAttributePersistence = planAttributePersistence;
    }

    public PlanPositionLocalService getPlanPositionLocalService() {
        return planPositionLocalService;
    }

    public void setPlanPositionLocalService(
        PlanPositionLocalService planPositionLocalService) {
        this.planPositionLocalService = planPositionLocalService;
    }

    public PlanPositionService getPlanPositionService() {
        return planPositionService;
    }

    public void setPlanPositionService(PlanPositionService planPositionService) {
        this.planPositionService = planPositionService;
    }

    public PlanPositionPersistence getPlanPositionPersistence() {
        return planPositionPersistence;
    }

    public void setPlanPositionPersistence(
        PlanPositionPersistence planPositionPersistence) {
        this.planPositionPersistence = planPositionPersistence;
    }

    public PlansUserSettingsLocalService getPlansUserSettingsLocalService() {
        return plansUserSettingsLocalService;
    }

    public void setPlansUserSettingsLocalService(
        PlansUserSettingsLocalService plansUserSettingsLocalService) {
        this.plansUserSettingsLocalService = plansUserSettingsLocalService;
    }

    public PlansUserSettingsService getPlansUserSettingsService() {
        return plansUserSettingsService;
    }

    public void setPlansUserSettingsService(
        PlansUserSettingsService plansUserSettingsService) {
        this.plansUserSettingsService = plansUserSettingsService;
    }

    public PlansUserSettingsPersistence getPlansUserSettingsPersistence() {
        return plansUserSettingsPersistence;
    }

    public void setPlansUserSettingsPersistence(
        PlansUserSettingsPersistence plansUserSettingsPersistence) {
        this.plansUserSettingsPersistence = plansUserSettingsPersistence;
    }

    public PlanVoteLocalService getPlanVoteLocalService() {
        return planVoteLocalService;
    }

    public void setPlanVoteLocalService(
        PlanVoteLocalService planVoteLocalService) {
        this.planVoteLocalService = planVoteLocalService;
    }

    public PlanVoteService getPlanVoteService() {
        return planVoteService;
    }

    public void setPlanVoteService(PlanVoteService planVoteService) {
        this.planVoteService = planVoteService;
    }

    public PlanVotePersistence getPlanVotePersistence() {
        return planVotePersistence;
    }

    public void setPlanVotePersistence(PlanVotePersistence planVotePersistence) {
        this.planVotePersistence = planVotePersistence;
    }

    public PlansFilterLocalService getPlansFilterLocalService() {
        return plansFilterLocalService;
    }

    public void setPlansFilterLocalService(
        PlansFilterLocalService plansFilterLocalService) {
        this.plansFilterLocalService = plansFilterLocalService;
    }

    public PlansFilterService getPlansFilterService() {
        return plansFilterService;
    }

    public void setPlansFilterService(PlansFilterService plansFilterService) {
        this.plansFilterService = plansFilterService;
    }

    public PlansFilterPersistence getPlansFilterPersistence() {
        return plansFilterPersistence;
    }

    public void setPlansFilterPersistence(
        PlansFilterPersistence plansFilterPersistence) {
        this.plansFilterPersistence = plansFilterPersistence;
    }

    public PlanAttributeFilterLocalService getPlanAttributeFilterLocalService() {
        return planAttributeFilterLocalService;
    }

    public void setPlanAttributeFilterLocalService(
        PlanAttributeFilterLocalService planAttributeFilterLocalService) {
        this.planAttributeFilterLocalService = planAttributeFilterLocalService;
    }

    public PlanAttributeFilterService getPlanAttributeFilterService() {
        return planAttributeFilterService;
    }

    public void setPlanAttributeFilterService(
        PlanAttributeFilterService planAttributeFilterService) {
        this.planAttributeFilterService = planAttributeFilterService;
    }

    public PlanAttributeFilterPersistence getPlanAttributeFilterPersistence() {
        return planAttributeFilterPersistence;
    }

    public void setPlanAttributeFilterPersistence(
        PlanAttributeFilterPersistence planAttributeFilterPersistence) {
        this.planAttributeFilterPersistence = planAttributeFilterPersistence;
    }

    public PlanPropertyFilterLocalService getPlanPropertyFilterLocalService() {
        return planPropertyFilterLocalService;
    }

    public void setPlanPropertyFilterLocalService(
        PlanPropertyFilterLocalService planPropertyFilterLocalService) {
        this.planPropertyFilterLocalService = planPropertyFilterLocalService;
    }

    public PlanPropertyFilterService getPlanPropertyFilterService() {
        return planPropertyFilterService;
    }

    public void setPlanPropertyFilterService(
        PlanPropertyFilterService planPropertyFilterService) {
        this.planPropertyFilterService = planPropertyFilterService;
    }

    public PlanPropertyFilterPersistence getPlanPropertyFilterPersistence() {
        return planPropertyFilterPersistence;
    }

    public void setPlanPropertyFilterPersistence(
        PlanPropertyFilterPersistence planPropertyFilterPersistence) {
        this.planPropertyFilterPersistence = planPropertyFilterPersistence;
    }

    public PlanColumnSettingsLocalService getPlanColumnSettingsLocalService() {
        return planColumnSettingsLocalService;
    }

    public void setPlanColumnSettingsLocalService(
        PlanColumnSettingsLocalService planColumnSettingsLocalService) {
        this.planColumnSettingsLocalService = planColumnSettingsLocalService;
    }

    public PlanColumnSettingsService getPlanColumnSettingsService() {
        return planColumnSettingsService;
    }

    public void setPlanColumnSettingsService(
        PlanColumnSettingsService planColumnSettingsService) {
        this.planColumnSettingsService = planColumnSettingsService;
    }

    public PlanColumnSettingsPersistence getPlanColumnSettingsPersistence() {
        return planColumnSettingsPersistence;
    }

    public void setPlanColumnSettingsPersistence(
        PlanColumnSettingsPersistence planColumnSettingsPersistence) {
        this.planColumnSettingsPersistence = planColumnSettingsPersistence;
    }

    public PlansFilterPositionLocalService getPlansFilterPositionLocalService() {
        return plansFilterPositionLocalService;
    }

    public void setPlansFilterPositionLocalService(
        PlansFilterPositionLocalService plansFilterPositionLocalService) {
        this.plansFilterPositionLocalService = plansFilterPositionLocalService;
    }

    public PlansFilterPositionService getPlansFilterPositionService() {
        return plansFilterPositionService;
    }

    public void setPlansFilterPositionService(
        PlansFilterPositionService plansFilterPositionService) {
        this.plansFilterPositionService = plansFilterPositionService;
    }

    public PlansFilterPositionPersistence getPlansFilterPositionPersistence() {
        return plansFilterPositionPersistence;
    }

    public void setPlansFilterPositionPersistence(
        PlansFilterPositionPersistence plansFilterPositionPersistence) {
        this.plansFilterPositionPersistence = plansFilterPositionPersistence;
    }

    public PlanTypeLocalService getPlanTypeLocalService() {
        return planTypeLocalService;
    }

    public void setPlanTypeLocalService(
        PlanTypeLocalService planTypeLocalService) {
        this.planTypeLocalService = planTypeLocalService;
    }

    public PlanTypeService getPlanTypeService() {
        return planTypeService;
    }

    public void setPlanTypeService(PlanTypeService planTypeService) {
        this.planTypeService = planTypeService;
    }

    public PlanTypePersistence getPlanTypePersistence() {
        return planTypePersistence;
    }

    public void setPlanTypePersistence(PlanTypePersistence planTypePersistence) {
        this.planTypePersistence = planTypePersistence;
    }

    public PlanTypeAttributeLocalService getPlanTypeAttributeLocalService() {
        return planTypeAttributeLocalService;
    }

    public void setPlanTypeAttributeLocalService(
        PlanTypeAttributeLocalService planTypeAttributeLocalService) {
        this.planTypeAttributeLocalService = planTypeAttributeLocalService;
    }

    public PlanTypeAttributeService getPlanTypeAttributeService() {
        return planTypeAttributeService;
    }

    public void setPlanTypeAttributeService(
        PlanTypeAttributeService planTypeAttributeService) {
        this.planTypeAttributeService = planTypeAttributeService;
    }

    public PlanTypeAttributePersistence getPlanTypeAttributePersistence() {
        return planTypeAttributePersistence;
    }

    public void setPlanTypeAttributePersistence(
        PlanTypeAttributePersistence planTypeAttributePersistence) {
        this.planTypeAttributePersistence = planTypeAttributePersistence;
    }

    public PlanTypeColumnLocalService getPlanTypeColumnLocalService() {
        return planTypeColumnLocalService;
    }

    public void setPlanTypeColumnLocalService(
        PlanTypeColumnLocalService planTypeColumnLocalService) {
        this.planTypeColumnLocalService = planTypeColumnLocalService;
    }

    public PlanTypeColumnService getPlanTypeColumnService() {
        return planTypeColumnService;
    }

    public void setPlanTypeColumnService(
        PlanTypeColumnService planTypeColumnService) {
        this.planTypeColumnService = planTypeColumnService;
    }

    public PlanTypeColumnPersistence getPlanTypeColumnPersistence() {
        return planTypeColumnPersistence;
    }

    public void setPlanTypeColumnPersistence(
        PlanTypeColumnPersistence planTypeColumnPersistence) {
        this.planTypeColumnPersistence = planTypeColumnPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
