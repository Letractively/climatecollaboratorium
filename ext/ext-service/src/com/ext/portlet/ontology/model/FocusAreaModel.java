package com.ext.portlet.ontology.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="FocusAreaModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>FocusArea</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.FocusArea
 * @see com.ext.portlet.ontology.model.impl.FocusAreaImpl
 * @see com.ext.portlet.ontology.model.impl.FocusAreaModelImpl
 *
 */
public interface FocusAreaModel extends BaseModel<FocusArea> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public FocusArea toEscapedModel();
}
