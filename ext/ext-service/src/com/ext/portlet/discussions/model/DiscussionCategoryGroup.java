package com.ext.portlet.discussions.model;


/**
 * <a href="DiscussionCategoryGroup.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DiscussionCategoryGroup</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionCategoryGroupModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryGroupModelImpl
 *
 */
public interface DiscussionCategoryGroup extends DiscussionCategoryGroupModel {
    public com.ext.portlet.discussions.model.DiscussionCategory getCategoryById(
        java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage getThreadById(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getCategories()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory addCategory(
        java.lang.String name, java.lang.String description,
        com.liferay.portal.model.User creator)
        throws com.liferay.portal.SystemException;

    public void store() throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage getCommentThread()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage addComment(
        java.lang.String title, java.lang.String description,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public int getCommentsCount()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
