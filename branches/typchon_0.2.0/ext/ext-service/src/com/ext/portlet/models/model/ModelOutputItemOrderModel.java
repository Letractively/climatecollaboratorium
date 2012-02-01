package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelOutputItemOrderModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelOutputItemOrder</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelOutputItemOrder
 * @see com.ext.portlet.models.model.impl.ModelOutputItemOrderImpl
 * @see com.ext.portlet.models.model.impl.ModelOutputItemOrderModelImpl
 *
 */
public interface ModelOutputItemOrderModel extends BaseModel<ModelOutputItemOrder> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelOutputItemModifierPK();

    public void setModelOutputItemModifierPK(Long modelOutputItemModifierPK);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Long getModelOutputItemId();

    public void setModelOutputItemId(Long modelOutputItemId);

    public Long getModelOutputItemOrder();

    public void setModelOutputItemOrder(Long modelOutputItemOrder);

    public String getType();

    public void setType(String type);

    public ModelOutputItemOrder toEscapedModel();
}
