package com.ext.portlet.plans.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <a href="PlanSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * <code>com.ext.portlet.plans.service.http.PlanServiceSoap</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.http.PlanServiceSoap
 *
 */
public class PlanSoap implements Serializable {
    private Long _planId;
    private String _name;
    private String _content;
    private String _shortcontent;
    private Long _planTypeId;
    private Long _companyId;
    private Long _groupId;
    private Long _childGroupId;
    private Long _MBCategoryId;
    private String _scenarioId;
    private String _topicId;
    private Integer _votes;
    private Date _createDate;
    private Date _publishDate;
    private Long _userId;
    private String _userName;
    private String _userScreenName;
    private Date _modifiedDate;
    private Boolean _userEdited;

    public PlanSoap() {
    }

    public static PlanSoap toSoapModel(Plan model) {
        PlanSoap soapModel = new PlanSoap();

        soapModel.setPlanId(model.getPlanId());
        soapModel.setName(model.getName());
        soapModel.setContent(model.getContent());
        soapModel.setShortcontent(model.getShortcontent());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setCompanyId(model.getCompanyId());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setChildGroupId(model.getChildGroupId());
        soapModel.setMBCategoryId(model.getMBCategoryId());
        soapModel.setScenarioId(model.getScenarioId());
        soapModel.setTopicId(model.getTopicId());
        soapModel.setVotes(model.getVotes());
        soapModel.setCreateDate(model.getCreateDate());
        soapModel.setPublishDate(model.getPublishDate());
        soapModel.setUserId(model.getUserId());
        soapModel.setUserName(model.getUserName());
        soapModel.setUserScreenName(model.getUserScreenName());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setUserEdited(model.getUserEdited());

        return soapModel;
    }

    public static PlanSoap[] toSoapModels(Plan[] models) {
        PlanSoap[] soapModels = new PlanSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static PlanSoap[][] toSoapModels(Plan[][] models) {
        PlanSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new PlanSoap[models.length][models[0].length];
        } else {
            soapModels = new PlanSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static PlanSoap[] toSoapModels(List<Plan> models) {
        List<PlanSoap> soapModels = new ArrayList<PlanSoap>(models.size());

        for (Plan model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new PlanSoap[soapModels.size()]);
    }

    public Long getPrimaryKey() {
        return _planId;
    }

    public void setPrimaryKey(Long pk) {
        setPlanId(pk);
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public String getShortcontent() {
        return _shortcontent;
    }

    public void setShortcontent(String shortcontent) {
        _shortcontent = shortcontent;
    }

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Long getCompanyId() {
        return _companyId;
    }

    public void setCompanyId(Long companyId) {
        _companyId = companyId;
    }

    public Long getGroupId() {
        return _groupId;
    }

    public void setGroupId(Long groupId) {
        _groupId = groupId;
    }

    public Long getChildGroupId() {
        return _childGroupId;
    }

    public void setChildGroupId(Long childGroupId) {
        _childGroupId = childGroupId;
    }

    public Long getMBCategoryId() {
        return _MBCategoryId;
    }

    public void setMBCategoryId(Long MBCategoryId) {
        _MBCategoryId = MBCategoryId;
    }

    public String getScenarioId() {
        return _scenarioId;
    }

    public void setScenarioId(String scenarioId) {
        _scenarioId = scenarioId;
    }

    public String getTopicId() {
        return _topicId;
    }

    public void setTopicId(String topicId) {
        _topicId = topicId;
    }

    public Integer getVotes() {
        return _votes;
    }

    public void setVotes(Integer votes) {
        _votes = votes;
    }

    public Date getCreateDate() {
        return _createDate;
    }

    public void setCreateDate(Date createDate) {
        _createDate = createDate;
    }

    public Date getPublishDate() {
        return _publishDate;
    }

    public void setPublishDate(Date publishDate) {
        _publishDate = publishDate;
    }

    public Long getUserId() {
        return _userId;
    }

    public void setUserId(Long userId) {
        _userId = userId;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public String getUserScreenName() {
        return _userScreenName;
    }

    public void setUserScreenName(String userScreenName) {
        _userScreenName = userScreenName;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public Boolean getUserEdited() {
        return _userEdited;
    }

    public void setUserEdited(Boolean userEdited) {
        _userEdited = userEdited;
    }
}
