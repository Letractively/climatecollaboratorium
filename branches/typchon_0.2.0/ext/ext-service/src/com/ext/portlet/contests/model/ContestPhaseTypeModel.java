package com.ext.portlet.contests.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ContestPhaseTypeModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ContestPhaseType</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhaseType
 * @see com.ext.portlet.contests.model.impl.ContestPhaseTypeImpl
 * @see com.ext.portlet.contests.model.impl.ContestPhaseTypeModelImpl
 *
 */
public interface ContestPhaseTypeModel extends BaseModel<ContestPhaseType> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public String getStatus();

    public void setStatus(String status);

    public ContestPhaseType toEscapedModel();
}
