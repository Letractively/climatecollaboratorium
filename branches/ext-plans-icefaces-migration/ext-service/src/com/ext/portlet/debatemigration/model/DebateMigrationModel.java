package com.ext.portlet.debatemigration.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="DebateMigrationModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateMigration</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigration
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationImpl
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationModelImpl
 *
 */
public interface DebateMigrationModel extends BaseModel<DebateMigration> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateMigrationPK();

    public void setDebateMigrationPK(Long debateMigrationPK);

    public Date getMigrationDate();

    public void setMigrationDate(Date migrationDate);

    public DebateMigration toEscapedModel();
}
