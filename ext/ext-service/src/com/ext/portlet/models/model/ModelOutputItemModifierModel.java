package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelOutputItemModifierModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelOutputItemModifier</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelOutputItemModifier
 * @see com.ext.portlet.models.model.impl.ModelOutputItemModifierImpl
 * @see com.ext.portlet.models.model.impl.ModelOutputItemModifierModelImpl
 *
 */
public interface ModelOutputItemModifierModel extends BaseModel<ModelOutputItemModifier> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelOutputItemModifierPK();

    public void setModelOutputItemModifierPK(Long modelOutputItemModifierPK);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Long getModelOutputItemId();

    public void setModelOutputItemId(Long modelOutputItemId);

    public Long getSourceItemId();

    public void setSourceItemId(Long sourceItemId);

    public String getType();

    public void setType(String type);

    public ModelOutputItemModifier toEscapedModel();
}
