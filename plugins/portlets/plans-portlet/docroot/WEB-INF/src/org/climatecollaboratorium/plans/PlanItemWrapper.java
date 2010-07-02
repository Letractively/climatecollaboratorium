package org.climatecollaboratorium.plans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanType;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;

public class PlanItemWrapper {
    public PlanItem wrapped;
    public PlanBean planBean;

    public PlanItemWrapper(PlanItem plan, PlanBean planBean) {
        wrapped = plan;
        this.planBean = planBean;
    }

    public List<PlanItem> getAllVersions() throws SystemException {
        return wrapped.getAllVersions();
    }

    public User getAuthor() throws PortalException, SystemException {
        return wrapped.getAuthor();
    }

    public Long getAuthorId() throws SystemException {
        return wrapped.getAuthorId();
    }

    public String getDescription() throws SystemException {
        return wrapped.getDescription();
    }

    public Long getId() {
        return wrapped.getId();
    }

    public Long getMBCategoryId() throws SystemException {
        return wrapped.getMBCategoryId();
    }

    public String getName() throws SystemException {
        return wrapped.getName();
    }

    public List<PlanDescription> getPlanDescriptions() throws SystemException {
        return wrapped.getPlanDescriptions();
    }

    public Long getPlanGroupId() throws SystemException {
        return wrapped.getPlanGroupId();
    }

    public Long getPlanId() {
        return wrapped.getPlanId();
    }

    public PlanType getPlanType() throws PortalException, SystemException {
        return wrapped.getPlanType();
    }

    public Long getPlanTypeId() throws SystemException {
        return wrapped.getPlanTypeId();
    }

    public Long getScenarioId() throws SystemException {
        return wrapped.getScenarioId();
    }

    public Long getUpdateAuthorId() {
        return wrapped.getUpdateAuthorId();
    }

    public Date getUpdated() {
        return wrapped.getUpdated();
    }

    public String getUpdateType() {
        return wrapped.getUpdateType();
    }

    public Long getVersion() {
        return wrapped.getVersion();
    }

    public void setAuthorId(Long authorId, Long updateAuthorId) throws SystemException {
        wrapped.setAuthorId(authorId, updateAuthorId);
    }

    public void setDescription(String description) throws SystemException {
        if (!wrapped.getDescription().equals(description)) {
            wrapped.setDescription(description, (new Random()).nextLong());            
        }
        planBean.setEditingDescription(false);
    }

    public void setId(Long id) {
        wrapped.setId(id);
    }

    public void setMBCategoryId(Long mbCategoryId, Long updateAuthorId) throws SystemException {
        wrapped.setMBCategoryId(mbCategoryId, updateAuthorId);
    }

    public void setName(String name) throws SystemException {
        if (!wrapped.getName().equals(name)) {
            wrapped.setName(name, 3L);
        }
    }

    public void setPlanGroupId(Long groupId, Long updateAuthorId) throws SystemException {
        wrapped.setPlanGroupId(groupId, updateAuthorId);
    }

    public void setPlanId(Long planId) {
        wrapped.setPlanId(planId);
    }

    public void setPlanTypeId(Long planTypeId, Long updateAuthorId) throws SystemException {
        wrapped.setPlanTypeId(planTypeId, updateAuthorId);
    }

    public void setScenarioId(Long scenarioId, Long authorId) throws SystemException, PortalException {
        wrapped.setScenarioId(scenarioId, authorId);
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        wrapped.setUpdateAuthorId(updateAuthorId);
    }

    public void setUpdated(Date updated) {
        wrapped.setUpdated(updated);
    }

    public void setUpdateType(String updateType) {
        wrapped.setUpdateType(updateType);
    }

    public void setVersion(Long version) {
        wrapped.setVersion(version);
    }
}
