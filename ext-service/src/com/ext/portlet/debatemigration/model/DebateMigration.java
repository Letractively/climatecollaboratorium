package com.ext.portlet.debatemigration.model;


/**
 * <a href="DebateMigration.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateMigration</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.debatemigration.model.impl.DebateMigrationImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationModel
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationImpl
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationModelImpl
 *
 */
public interface DebateMigration extends DebateMigrationModel {
    public void mapCategory(long mbcategoryid, long debatecategoryid)
        throws com.liferay.portal.SystemException;

    public void mapDebate(long mbcategoryid, long debateid)
        throws com.liferay.portal.SystemException;

    public void mapItem(long mbmessageid, long debateitemid)
        throws com.liferay.portal.SystemException;

    public void mapComment(long mbmessageid, long debatecommentid)
        throws com.liferay.portal.SystemException;
}
