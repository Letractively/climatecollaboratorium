package com.ext.portlet.ontology.model;

import com.ext.portlet.ontology.service.persistence.CategoryOntologyTermPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="CategoryOntologyTermModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>CategoryOntologyTerm</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.CategoryOntologyTerm
 * @see com.ext.portlet.ontology.model.impl.CategoryOntologyTermImpl
 * @see com.ext.portlet.ontology.model.impl.CategoryOntologyTermModelImpl
 *
 */
public interface CategoryOntologyTermModel extends BaseModel<CategoryOntologyTerm> {
    public CategoryOntologyTermPK getPrimaryKey();

    public void setPrimaryKey(CategoryOntologyTermPK pk);

    public Long getCategoryId();

    public void setCategoryId(Long categoryId);

    public Long getOntologyTerm();

    public void setOntologyTerm(Long ontologyTerm);

    public CategoryOntologyTerm toEscapedModel();
}
