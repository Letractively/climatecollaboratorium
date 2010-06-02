package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelOutputItemModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelOutputItem</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelOutputItem
 * @see com.ext.portlet.models.model.impl.ModelOutputItemImpl
 * @see com.ext.portlet.models.model.impl.ModelOutputItemModelImpl
 *
 */
public interface ModelOutputItemModel extends BaseModel<ModelOutputItem> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelOutputItemModifierPK();

    public void setModelOutputItemModifierPK(Long modelOutputItemModifierPK);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Long getModelOutputItemId();

    public void setModelOutputItemId(Long modelOutputItemId);

    public Integer getModelOutputItemOrder();

    public void setModelOutputItemOrder(Integer modelOutputItemOrder);

    public String getModelItemRangePolicy();

    public void setModelItemRangePolicy(String modelItemRangePolicy);

    public String getModelItemRangeMessage();

    public void setModelItemRangeMessage(String modelItemRangeMessage);

    public String getModelItemErrorPolicy();

    public void setModelItemErrorPolicy(String modelItemErrorPolicy);

    public String getModelItemErrorMessage();

    public void setModelItemErrorMessage(String modelItemErrorMessage);

    public Boolean getModelItemIsVisible();

    public void setModelItemIsVisible(Boolean modelItemIsVisible);

    public String getItemType();

    public void setItemType(String itemType);

    public Long getRelatedOutputItem();

    public void setRelatedOutputItem(Long relatedOutputItem);

    public ModelOutputItem toEscapedModel();
}
