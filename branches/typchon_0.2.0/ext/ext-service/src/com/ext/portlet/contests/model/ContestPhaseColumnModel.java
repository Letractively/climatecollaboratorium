package com.ext.portlet.contests.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ContestPhaseColumnModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ContestPhaseColumn</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhaseColumn
 * @see com.ext.portlet.contests.model.impl.ContestPhaseColumnImpl
 * @see com.ext.portlet.contests.model.impl.ContestPhaseColumnModelImpl
 *
 */
public interface ContestPhaseColumnModel extends BaseModel<ContestPhaseColumn> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getContestPhasePK();

    public void setContestPhasePK(Long ContestPhasePK);

    public String getColumnName();

    public void setColumnName(String columnName);

    public Integer getColumnWeight();

    public void setColumnWeight(Integer columnWeight);

    public Boolean getDefaultSort();

    public void setDefaultSort(Boolean defaultSort);

    public ContestPhaseColumn toEscapedModel();
}
