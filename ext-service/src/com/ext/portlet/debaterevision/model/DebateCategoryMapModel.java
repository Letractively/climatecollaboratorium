package com.ext.portlet.debaterevision.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateCategoryMapModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateCategoryMap</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateCategoryMap
 * @see com.ext.portlet.debaterevision.model.impl.DebateCategoryMapImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateCategoryMapModelImpl
 *
 */
public interface DebateCategoryMapModel extends BaseModel<DebateCategoryMap> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateCategoryMapPK();

    public void setDebateCategoryMapPK(Long debateCategoryMapPK);

    public Long getDebateCategoryPK();

    public void setDebateCategoryPK(Long debateCategoryPK);

    public Long getDebateId();

    public void setDebateId(Long debateId);

    public DebateCategoryMap toEscapedModel();
}
