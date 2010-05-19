package com.ext.portlet.debaterevision.model;


/**
 * <a href="DebateComment.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateComment</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.debaterevision.model.impl.DebateCommentImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateCommentModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateCommentImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateCommentModelImpl
 *
 */
public interface DebateComment extends DebateCommentModel {
    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
