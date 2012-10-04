package com.ext.portlet.contests.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="ContestModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Contest</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.Contest
 * @see com.ext.portlet.contests.model.impl.ContestImpl
 * @see com.ext.portlet.contests.model.impl.ContestModelImpl
 *
 */
public interface ContestModel extends BaseModel<Contest> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getContestPK();

    public void setContestPK(Long ContestPK);

    public String getContestName();

    public void setContestName(String ContestName);

    public String getContestShortName();

    public void setContestShortName(String ContestShortName);

    public String getContestDescription();

    public void setContestDescription(String ContestDescription);

    public String getContestModelDescription();

    public void setContestModelDescription(String ContestModelDescription);

    public String getContestPositionsDescription();

    public void setContestPositionsDescription(
        String ContestPositionsDescription);

    public String getDefaultPlanDescription();

    public void setDefaultPlanDescription(String defaultPlanDescription);

    public Long getPlanTypeId();

    public void setPlanTypeId(Long PlanTypeId);

    public Date getCreated();

    public void setCreated(Date created);

    public Date getUpdated();

    public void setUpdated(Date updated);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public Boolean getContestActive();

    public void setContestActive(Boolean contestActive);

    public Long getPlanTemplateId();

    public void setPlanTemplateId(Long planTemplateId);

    public Long getFocusAreaId();

    public void setFocusAreaId(Long focusAreaId);

    public Long getContestLogoId();

    public void setContestLogoId(Long contestLogoId);

    public Boolean getFeatured();

    public void setFeatured(Boolean featured);

    public Boolean getPlansOpenByDefault();

    public void setPlansOpenByDefault(Boolean plansOpenByDefault);

    public Integer getFlag();

    public void setFlag(Integer flag);

    public String getFlagText();

    public void setFlagText(String flagText);

    public Long getGroupId();

    public void setGroupId(Long groupId);

    public Long getDiscussionGroupId();

    public void setDiscussionGroupId(Long discussionGroupId);

    public Integer getWeight();

    public void setWeight(Integer weight);

    public String getResourcesUrl();

    public void setResourcesUrl(String resourcesUrl);

    public Contest toEscapedModel();
}
