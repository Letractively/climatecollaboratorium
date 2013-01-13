package com.ext.portlet.contests.model;


/**
 * <a href="Contest.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Contest</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.contests.model.impl.ContestImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestModel
 * @see com.ext.portlet.contests.model.impl.ContestImpl
 * @see com.ext.portlet.contests.model.impl.ContestModelImpl
 *
 */
public interface Contest extends ContestModel {
    public java.util.List<com.ext.portlet.contests.model.ContestPhase> getPhases()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanType getPlanType()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> getActivePhases()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase getActivePhase()
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.SystemException;

    public boolean isActive() throws com.liferay.portal.SystemException;

    public java.util.List<Long> getDebatesIds()
        throws com.liferay.portal.SystemException;

    public void setDebates(java.util.List<Long> debatesIds)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> getDebates()
        throws com.liferay.portal.SystemException;

    public java.lang.Integer getTotalVotes()
        throws com.liferay.portal.SystemException;

    public void updateDefaultPlanDescription(java.lang.String description)
        throws com.liferay.portal.SystemException;

    public void store() throws com.liferay.portal.SystemException;

    public com.ext.portlet.plans.model.PlanTemplate getPlanTemplate()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea getFocusArea()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.liferay.portal.model.Image getLogo()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void setLogo(java.io.File logoFile)
        throws com.liferay.portal.SystemException, java.io.IOException;

    public java.lang.String getLogoPath()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public long getProposalsCount()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public long getCommentsCount()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public long getProposalsCommentsCount()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public long getTotalComments()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> getTeamMembers()
        throws com.liferay.portal.SystemException;
}
