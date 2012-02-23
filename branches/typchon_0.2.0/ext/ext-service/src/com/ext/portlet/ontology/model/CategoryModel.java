package com.ext.portlet.ontology.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="CategoryModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Category</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.Category
 * @see com.ext.portlet.ontology.model.impl.CategoryImpl
 * @see com.ext.portlet.ontology.model.impl.CategoryModelImpl
 *
 */
public interface CategoryModel extends BaseModel<Category> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public Category toEscapedModel();
}
