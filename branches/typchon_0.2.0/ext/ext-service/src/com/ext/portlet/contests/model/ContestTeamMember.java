package com.ext.portlet.contests.model;


/**
 * <a href="ContestTeamMember.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ContestTeamMember</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.contests.model.impl.ContestTeamMemberImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestTeamMemberModel
 * @see com.ext.portlet.contests.model.impl.ContestTeamMemberImpl
 * @see com.ext.portlet.contests.model.impl.ContestTeamMemberModelImpl
 *
 */
public interface ContestTeamMember extends ContestTeamMemberModel {
    public void store() throws com.liferay.portal.SystemException;

    public void delete() throws com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getUser()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.Contest getContest()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
