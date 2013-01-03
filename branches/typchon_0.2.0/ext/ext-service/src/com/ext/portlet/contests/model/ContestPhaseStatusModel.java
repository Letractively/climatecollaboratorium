package com.ext.portlet.contests.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ContestPhaseStatusModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ContestPhaseStatus</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhaseStatus
 * @see com.ext.portlet.contests.model.impl.ContestPhaseStatusImpl
 * @see com.ext.portlet.contests.model.impl.ContestPhaseStatusModelImpl
 *
 */
public interface ContestPhaseStatusModel extends BaseModel<ContestPhaseStatus> {
    public String getPrimaryKey();

    public void setPrimaryKey(String pk);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public ContestPhaseStatus toEscapedModel();
}
