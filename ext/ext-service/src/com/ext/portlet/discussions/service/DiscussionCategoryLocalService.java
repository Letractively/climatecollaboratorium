package com.ext.portlet.discussions.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DiscussionCategoryLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.discussions.service.impl.DiscussionCategoryLocalServiceImpl</code>.
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
 * @see com.ext.portlet.discussions.service.DiscussionCategoryLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DiscussionCategoryLocalService {
    public com.ext.portlet.discussions.model.DiscussionCategory addDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory createDiscussionCategory(
        java.lang.Long pk);

    public void deleteDiscussionCategory(java.lang.Long pk)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.discussions.model.DiscussionCategory getDiscussionCategory(
        java.lang.Long pk)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getDiscussionCategories(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDiscussionCategoriesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory,
        boolean merge) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getCategoriesByCategoryGroupId(
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.discussions.model.DiscussionCategory getDiscussionCategoryById(
        java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory createDebateCategory(
        java.lang.Long categoryGroupId, java.lang.String name,
        java.lang.String description, com.liferay.portal.model.User author)
        throws com.liferay.portal.SystemException;
}
