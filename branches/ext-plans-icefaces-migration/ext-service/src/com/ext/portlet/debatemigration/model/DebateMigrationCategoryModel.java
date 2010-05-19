package com.ext.portlet.debatemigration.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateMigrationCategoryModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateMigrationCategory</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationCategory
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationCategoryImpl
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationCategoryModelImpl
 *
 */
public interface DebateMigrationCategoryModel extends BaseModel<DebateMigrationCategory> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateMigrationCategoryPK();

    public void setDebateMigrationCategoryPK(Long debateMigrationCategoryPK);

    public Long getDebateMigrationId();

    public void setDebateMigrationId(Long debateMigrationId);

    public Long getOldMBCategoryId();

    public void setOldMBCategoryId(Long oldMBCategoryId);

    public Long getNewCategoryId();

    public void setNewCategoryId(Long newCategoryId);

    public DebateMigrationCategory toEscapedModel();
}
