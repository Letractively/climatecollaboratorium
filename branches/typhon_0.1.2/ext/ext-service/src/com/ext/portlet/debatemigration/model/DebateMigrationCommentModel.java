package com.ext.portlet.debatemigration.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateMigrationCommentModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateMigrationComment</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.model.DebateMigrationComment
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationCommentImpl
 * @see com.ext.portlet.debatemigration.model.impl.DebateMigrationCommentModelImpl
 *
 */
public interface DebateMigrationCommentModel extends BaseModel<DebateMigrationComment> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateMigrationCommentPK();

    public void setDebateMigrationCommentPK(Long debateMigrationCommentPK);

    public Long getDebateMigrationId();

    public void setDebateMigrationId(Long debateMigrationId);

    public Long getOldMBMessageId();

    public void setOldMBMessageId(Long oldMBMessageId);

    public Long getOldMBThreadId();

    public void setOldMBThreadId(Long oldMBThreadId);

    public Long getNewDebateCommentId();

    public void setNewDebateCommentId(Long newDebateCommentId);

    public DebateMigrationComment toEscapedModel();
}
