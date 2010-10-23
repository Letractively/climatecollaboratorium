package com.ext.portlet.plans.service;


/**
 * <a href="PlanVoteLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanVoteLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanVoteLocalService
 *
 */
public class PlanVoteLocalServiceUtil {
    private static PlanVoteLocalService _service;

    public static com.ext.portlet.plans.model.PlanVote addPlanVote(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.SystemException {
        return getService().addPlanVote(planVote);
    }

    public static com.ext.portlet.plans.model.PlanVote createPlanVote(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK) {
        return getService().createPlanVote(planVotePK);
    }

    public static void deletePlanVote(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deletePlanVote(planVotePK);
    }

    public static void deletePlanVote(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.SystemException {
        getService().deletePlanVote(planVote);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.plans.model.PlanVote getPlanVote(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanVote(planVotePK);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanVote> getPlanVotes(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getPlanVotes(start, end);
    }

    public static int getPlanVotesCount()
        throws com.liferay.portal.SystemException {
        return getService().getPlanVotesCount();
    }

    public static com.ext.portlet.plans.model.PlanVote updatePlanVote(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanVote(planVote);
    }

    public static com.ext.portlet.plans.model.PlanVote updatePlanVote(
        com.ext.portlet.plans.model.PlanVote planVote, boolean merge)
        throws com.liferay.portal.SystemException {
        return getService().updatePlanVote(planVote, merge);
    }

    public static boolean voteForPlan(java.lang.Long planId,
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().voteForPlan(planId, userId);
    }

    public static boolean unvote(java.lang.Long userId, java.lang.Long contestId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().unvote(userId, contestId);
    }

    public static com.ext.portlet.plans.model.PlanVote getPlanVote(
        java.lang.Long userId, java.lang.Long contestId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getPlanVote(userId, contestId);
    }

    public static int coutPlanVotes(java.lang.Long planId)
        throws com.liferay.portal.SystemException {
        return getService().coutPlanVotes(planId);
    }

    public static int countPlanVotes(com.ext.portlet.plans.model.PlanType type)
        throws com.liferay.portal.SystemException {
        return getService().countPlanVotes(type);
    }

    public static PlanVoteLocalService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanVoteLocalService is not set");
        }

        return _service;
    }

    public void setService(PlanVoteLocalService service) {
        _service = service;
    }
}
