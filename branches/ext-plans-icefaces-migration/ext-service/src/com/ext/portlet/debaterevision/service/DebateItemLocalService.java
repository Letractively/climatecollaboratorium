package com.ext.portlet.debaterevision.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DebateItemLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.debaterevision.service.impl.DebateItemLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DebateItemLocalService {
    public com.ext.portlet.debaterevision.model.DebateItem addDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem createDebateItem(
        java.lang.Long debateItemPK);

    public void deleteDebateItem(java.lang.Long debateItemPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateItem getDebateItem(
        java.lang.Long debateItemPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getDebateItems(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDebateItemsCount() throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem updateDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItem updateDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        boolean merge) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getChildren(
        com.ext.portlet.debaterevision.model.DebateItem debateItem);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateItem getParent(
        com.ext.portlet.debaterevision.model.DebateItem debateItem)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void voteForDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void unvoteDebateItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserVotedForItem(
        com.ext.portlet.debaterevision.model.DebateItem debateItem,
        java.lang.Long userId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateItem getLastItemInVersion(
        long treeVersion, long itemId);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateItem getLastItem(
        long itemid);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateItem getLastActiveItem(
        long itemid);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getHistory(
        long treeVersion, long itemId);
}
