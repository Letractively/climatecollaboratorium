package com.ext.portlet.contests.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="ContestPhaseModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ContestPhase</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhase
 * @see com.ext.portlet.contests.model.impl.ContestPhaseImpl
 * @see com.ext.portlet.contests.model.impl.ContestPhaseModelImpl
 *
 */
public interface ContestPhaseModel extends BaseModel<ContestPhase> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getContestPhasePK();

    public void setContestPhasePK(Long ContestPhasePK);

    public Long getContestPK();

    public void setContestPK(Long ContestPK);

    public Long getContestPhaseType();

    public void setContestPhaseType(Long ContestPhaseType);

    public String getContestPhaseDescriptionOverride();

    public void setContestPhaseDescriptionOverride(
        String ContestPhaseDescriptionOverride);

    public Boolean getPhaseActiveOverride();

    public void setPhaseActiveOverride(Boolean phaseActiveOverride);

    public Date getPhaseStartDate();

    public void setPhaseStartDate(Date PhaseStartDate);

    public Date getPhaseEndDate();

    public void setPhaseEndDate(Date PhaseEndDate);

    public String getNextStatus();

    public void setNextStatus(String nextStatus);

    public Date getCreated();

    public void setCreated(Date created);

    public Date getUpdated();

    public void setUpdated(Date updated);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public ContestPhase toEscapedModel();
}
