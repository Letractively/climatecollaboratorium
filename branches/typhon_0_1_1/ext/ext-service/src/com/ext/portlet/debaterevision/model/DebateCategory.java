package com.ext.portlet.debaterevision.model;


/**
 * <a href="DebateCategory.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateCategory</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.debaterevision.model.impl.DebateCategoryImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateCategoryModel
 * @see com.ext.portlet.debaterevision.model.impl.DebateCategoryImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateCategoryModelImpl
 *
 */
public interface DebateCategory extends DebateCategoryModel {
    public void addDebate(long debateId)
        throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.debaterevision.model.Debate> getDebates()
        throws com.liferay.portal.SystemException;

    public void removeDebate(long debateId)
        throws com.liferay.portal.SystemException;
}
