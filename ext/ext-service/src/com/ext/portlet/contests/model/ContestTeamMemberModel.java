package com.ext.portlet.contests.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ContestTeamMemberModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ContestTeamMember</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestTeamMember
 * @see com.ext.portlet.contests.model.impl.ContestTeamMemberImpl
 * @see com.ext.portlet.contests.model.impl.ContestTeamMemberModelImpl
 *
 */
public interface ContestTeamMemberModel extends BaseModel<ContestTeamMember> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getContestId();

    public void setContestId(Long contestId);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getRole();

    public void setRole(String role);

    public ContestTeamMember toEscapedModel();
}
