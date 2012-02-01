package com.ext.portlet.ontology.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="OntologyTermModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>OntologyTerm</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.OntologyTerm
 * @see com.ext.portlet.ontology.model.impl.OntologyTermImpl
 * @see com.ext.portlet.ontology.model.impl.OntologyTermModelImpl
 *
 */
public interface OntologyTermModel extends BaseModel<OntologyTerm> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getParentId();

    public void setParentId(Long parentId);

    public String getName();

    public void setName(String name);

    public OntologyTerm toEscapedModel();
}
