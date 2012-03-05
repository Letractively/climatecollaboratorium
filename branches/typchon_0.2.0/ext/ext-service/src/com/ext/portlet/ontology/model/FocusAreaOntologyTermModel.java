package com.ext.portlet.ontology.model;

import com.ext.portlet.ontology.service.persistence.FocusAreaOntologyTermPK;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="FocusAreaOntologyTermModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>FocusAreaOntologyTerm</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.FocusAreaOntologyTerm
 * @see com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl
 * @see com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermModelImpl
 *
 */
public interface FocusAreaOntologyTermModel extends BaseModel<FocusAreaOntologyTerm> {
    public FocusAreaOntologyTermPK getPrimaryKey();

    public void setPrimaryKey(FocusAreaOntologyTermPK pk);

    public Long getFocusAreaId();

    public void setFocusAreaId(Long focusAreaId);

    public Long getOntologyTermId();

    public void setOntologyTermId(Long ontologyTermId);

    public FocusAreaOntologyTerm toEscapedModel();
}
