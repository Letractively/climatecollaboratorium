package com.ext.portlet.debatemigration.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateMigrationDebateModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateMigrationDebate</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationDebate
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationDebateImpl
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationDebateModelImpl
 *
 */
public interface DebateMigrationDebateModel extends BaseModel<DebateMigrationDebate> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateMigrationDebatePK();

    public void setDebateMigrationDebatePK(Long debateMigrationDebatePK);

    public Long getDebateMigrationId();

    public void setDebateMigrationId(Long debateMigrationId);

    public Long getOldMBCategoryId();

    public void setOldMBCategoryId(Long oldMBCategoryId);

    public Long getNewDebateId();

    public void setNewDebateId(Long newDebateId);

    public DebateMigrationDebate toEscapedModel();
}
