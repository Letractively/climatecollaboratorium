package com.ext.portlet.contests.model;


/**
 * <a href="ContestPhase.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ContestPhase</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.contests.model.impl.ContestPhaseImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.contests.model.ContestPhaseModel
 * @see com.ext.portlet.contests.model.impl.ContestPhaseImpl
 * @see com.ext.portlet.contests.model.impl.ContestPhaseModelImpl
 *
 */
public interface ContestPhase extends ContestPhaseModel {
    public com.ext.portlet.contests.model.Contest getContest()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getPlans()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestStatus getContestStatus();

    public java.util.List<String> getPhaseColumns()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> getPhaseColumnsRaw()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> getPreviousPhases()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.contests.model.ContestPhase getNextContestPhase()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
