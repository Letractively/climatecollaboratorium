package com.ext.portlet.discussions.model;


/**
 * <a href="DiscussionMessage.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DiscussionMessage</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.discussions.model.impl.DiscussionMessageImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.model.DiscussionMessageModel
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageImpl
 * @see com.ext.portlet.discussions.model.impl.DiscussionMessageModelImpl
 *
 */
public interface DiscussionMessage extends DiscussionMessageModel {
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadMessages()
        throws com.liferay.portal.SystemException;

    public int getThreadMessagesCount()
        throws com.liferay.portal.SystemException;

    public void store() throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage addThreadMessage(
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getLastActivityAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void delete()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void update(java.lang.String subject, java.lang.String body)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategory getCategory()
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getCategoryGroup()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage getThread()
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessageFlag> getFlags()
        throws com.liferay.portal.SystemException;

    public void addFlag(java.lang.String flagType, java.lang.String data,
        com.liferay.portal.model.User user)
        throws com.liferay.portal.SystemException;

    public void removeFlag(java.lang.String flagType)
        throws com.liferay.portal.SystemException;
}
