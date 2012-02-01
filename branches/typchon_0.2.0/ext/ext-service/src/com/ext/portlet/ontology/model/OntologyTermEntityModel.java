package com.ext.portlet.ontology.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="OntologyTermEntityModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>OntologyTermEntity</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.OntologyTermEntity
 * @see com.ext.portlet.ontology.model.impl.OntologyTermEntityImpl
 * @see com.ext.portlet.ontology.model.impl.OntologyTermEntityModelImpl
 *
 */
public interface OntologyTermEntityModel extends BaseModel<OntologyTermEntity> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public Long getTermId();

    public void setTermId(Long termId);

    public String getClassName();

    public Long getClassNameId();

    public void setClassNameId(Long classNameId);

    public Long getClassPK();

    public void setClassPK(Long classPK);

    public OntologyTermEntity toEscapedModel();
}
