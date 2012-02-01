package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="PlanModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Plan</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.Plan
 * @see com.ext.portlet.plans.model.impl.PlanImpl
 * @see com.ext.portlet.plans.model.impl.PlanModelImpl
 *
 */
public interface PlanModel extends BaseModel<Plan> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPlanId();

    public void setPlanId(Long planId);

    public String getName();

    public void setName(String name);

    public String getContent();

    public void setContent(String content);

    public String getShortcontent();

    public void setShortcontent(String shortcontent);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long planTypeId);

    public Long getCompanyId();

    public void setCompanyId(Long companyId);

    public Long getGroupId();

    public void setGroupId(Long groupId);

    public Long getChildGroupId();

    public void setChildGroupId(Long childGroupId);

    public Long getMBCategoryId();

    public void setMBCategoryId(Long MBCategoryId);

    public String getScenarioId();

    public void setScenarioId(String scenarioId);

    public String getTopicId();

    public void setTopicId(String topicId);

    public Integer getVotes();

    public void setVotes(Integer votes);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Date getPublishDate();

    public void setPublishDate(Date publishDate);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getUserName();

    public void setUserName(String userName);

    public String getUserScreenName();

    public void setUserScreenName(String userScreenName);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public Plan toEscapedModel();
}
