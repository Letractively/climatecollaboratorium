package com.ext.portlet.discussions.model;


/**
 * <a href="DiscussionCategory.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DiscussionCategory</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionCategoryModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionCategoryModelImpl
 *
 */
public interface DiscussionCategory extends DiscussionCategoryModel {
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreads()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage addThread(
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.SystemException;

    public void store() throws com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getLastActivityAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void delete() throws com.liferay.portal.SystemException;

    public void update(java.lang.String name, java.lang.String description)
        throws com.liferay.portal.SystemException;
}
