package com.ext.portlet.debaterevision.model;


/**
 * <a href="Debate.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Debate</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.debaterevision.model.impl.DebateImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateModelImpl
 *
 */
public interface Debate extends DebateModel {
    public com.ext.portlet.debaterevision.model.DebateItem getCurrentRoot();

    public com.ext.portlet.debaterevision.model.Debate copyBackward()
        throws com.liferay.portal.SystemException;

    public void moveForward() throws com.liferay.portal.SystemException;

    public int getNumberOfComments() throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateComment getMostRecentComment()
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.DebateItem> getLeadingPositions()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public int getTotalVotesCount()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
