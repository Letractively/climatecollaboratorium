package com.ext.portlet.contests.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="ContestSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.contests.service.http.ContestServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.service.http.ContestServiceSoap
 *
 */
public class ContestSoap implements Serializable {
    private Long _ContestPK;
    private String _ContestName;
    private String _ContestShortName;
    private String _ContestDescription;
    private String _ContestModelDescription;
    private String _ContestPositionsDescription;
    private String _defaultPlanDescription;
    private Long _PlanTypeId;
    private Date _created;
    private Date _updated;
    private Long _authorId;
    private Boolean _contestActive;
    private Long _planTemplateId;
    private Long _focusAreaId;
    private Long _contestLogoId;
    private Boolean _featured;
    private Long _groupId;
    private Long _discussionGroupId;
    private Integer _weight;
    private String _resourcesUrl;

    public ContestSoap() {
    }

    public static ContestSoap toSoapModel(Contest model) {
        ContestSoap soapModel = new ContestSoap();

        soapModel.setContestPK(model.getContestPK());
        soapModel.setContestName(model.getContestName());
        soapModel.setContestShortName(model.getContestShortName());
        soapModel.setContestDescription(model.getContestDescription());
        soapModel.setContestModelDescription(model.getContestModelDescription());
        soapModel.setContestPositionsDescription(model.getContestPositionsDescription());
        soapModel.setDefaultPlanDescription(model.getDefaultPlanDescription());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdated(model.getUpdated());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setContestActive(model.getContestActive());
        soapModel.setPlanTemplateId(model.getPlanTemplateId());
        soapModel.setFocusAreaId(model.getFocusAreaId());
        soapModel.setContestLogoId(model.getContestLogoId());
        soapModel.setFeatured(model.getFeatured());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setDiscussionGroupId(model.getDiscussionGroupId());
        soapModel.setWeight(model.getWeight());
        soapModel.setResourcesUrl(model.getResourcesUrl());

        return soapModel;
    }

    public static ContestSoap[] toSoapModels(Contest[] models) {
        ContestSoap[] soapModels = new ContestSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestSoap[][] toSoapModels(Contest[][] models) {
        ContestSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestSoap[] toSoapModels(List<Contest> models) {
        List<ContestSoap> soapModels = new ArrayList<ContestSoap>(models.size());

        for (Contest model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _ContestPK;
    }

    public void setPrimaryKey(Long pk) {
        setContestPK(pk);
    }

    public Long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(Long ContestPK) {
        _ContestPK = ContestPK;
    }

    public String getContestName() {
        return _ContestName;
    }

    public void setContestName(String ContestName) {
        _ContestName = ContestName;
    }

    public String getContestShortName() {
        return _ContestShortName;
    }

    public void setContestShortName(String ContestShortName) {
        _ContestShortName = ContestShortName;
    }

    public String getContestDescription() {
        return _ContestDescription;
    }

    public void setContestDescription(String ContestDescription) {
        _ContestDescription = ContestDescription;
    }

    public String getContestModelDescription() {
        return _ContestModelDescription;
    }

    public void setContestModelDescription(String ContestModelDescription) {
        _ContestModelDescription = ContestModelDescription;
    }

    public String getContestPositionsDescription() {
        return _ContestPositionsDescription;
    }

    public void setContestPositionsDescription(
        String ContestPositionsDescription) {
        _ContestPositionsDescription = ContestPositionsDescription;
    }

    public String getDefaultPlanDescription() {
        return _defaultPlanDescription;
    }

    public void setDefaultPlanDescription(String defaultPlanDescription) {
        _defaultPlanDescription = defaultPlanDescription;
    }

    public Long getPlanTypeId() {
        return _PlanTypeId;
    }

    public void setPlanTypeId(Long PlanTypeId) {
        _PlanTypeId = PlanTypeId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Boolean getContestActive() {
        return _contestActive;
    }

    public void setContestActive(Boolean contestActive) {
        _contestActive = contestActive;
    }

    public Long getPlanTemplateId() {
        return _planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        _planTemplateId = planTemplateId;
    }

    public Long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public Long getContestLogoId() {
        return _contestLogoId;
    }

    public void setContestLogoId(Long contestLogoId) {
        _contestLogoId = contestLogoId;
    }

    public Boolean getFeatured() {
        return _featured;
    }

    public void setFeatured(Boolean featured) {
        _featured = featured;
    }

    public Long getGroupId() {
        return _groupId;
    }

    public void setGroupId(Long groupId) {
        _groupId = groupId;
    }

    public Long getDiscussionGroupId() {
        return _discussionGroupId;
    }

    public void setDiscussionGroupId(Long discussionGroupId) {
        _discussionGroupId = discussionGroupId;
    }

    public Integer getWeight() {
        return _weight;
    }

    public void setWeight(Integer weight) {
        _weight = weight;
    }

    public String getResourcesUrl() {
        return _resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        _resourcesUrl = resourcesUrl;
    }
}