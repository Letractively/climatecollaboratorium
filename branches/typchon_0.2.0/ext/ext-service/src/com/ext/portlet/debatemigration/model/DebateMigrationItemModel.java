package com.ext.portlet.debatemigration.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateMigrationItemModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateMigrationItem</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationItem
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationItemImpl
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationItemModelImpl
 *
 */
public interface DebateMigrationItemModel extends BaseModel<DebateMigrationItem> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateMigrationItemPK();

    public void setDebateMigrationItemPK(Long debateMigrationItemPK);

    public Long getDebateMigrationId();

    public void setDebateMigrationId(Long debateMigrationId);

    public Long getOldMBMessageId();

    public void setOldMBMessageId(Long oldMBMessageId);

    public Long getNewDebateItemId();

    public void setNewDebateItemId(Long newDebateItemId);

    public DebateMigrationItem toEscapedModel();
}
