package com.ext.portlet.debaterevision.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="DebateCategoryModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateCategory</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateCategory
 * @see com.ext.portlet.debaterevision.model.impl.DebateCategoryImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateCategoryModelImpl
 *
 */
public interface DebateCategoryModel extends BaseModel<DebateCategory> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateCategoryPK();

    public void setDebateCategoryPK(Long debateCategoryPK);

    public Long getAuthorId();

    public void setAuthorId(Long authorId);

    public String getTitle();

    public void setTitle(String title);

    public Long getBrandingContentId();

    public void setBrandingContentId(Long brandingContentId);

    public String getDescription();

    public void setDescription(String description);

    public Long getParentCategory();

    public void setParentCategory(Long parentCategory);

    public Date getUpdated();

    public void setUpdated(Date updated);

    public DebateCategory toEscapedModel();
}
