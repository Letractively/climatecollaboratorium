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

    public Contest toEscapedModel();
}
