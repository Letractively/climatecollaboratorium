package com.ext.portlet.ontology.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="OntologySpaceModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>OntologySpace</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.OntologySpace
 * @see com.ext.portlet.ontology.model.impl.OntologySpaceImpl
 * @see com.ext.portlet.ontology.model.impl.OntologySpaceModelImpl
 *
 */
public interface OntologySpaceModel extends BaseModel<OntologySpace> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public OntologySpace toEscapedModel();
}
