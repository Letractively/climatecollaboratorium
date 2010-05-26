package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelInputItemModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelInputItem</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelInputItem
 * @see com.ext.portlet.models.model.impl.ModelInputItemImpl
 * @see com.ext.portlet.models.model.impl.ModelInputItemModelImpl
 *
 */
public interface ModelInputItemModel extends BaseModel<ModelInputItem> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelInputItemPK();

    public void setModelInputItemPK(Long modelInputItemPK);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Long getModelInputItemID();

    public void setModelInputItemID(Long modelInputItemID);

    public Long getModelGroupId();

    public void setModelGroupId(Long modelGroupId);

    public Integer getItemOrder();

    public void setItemOrder(Integer itemOrder);

    public String getType();

    public void setType(String type);

    public ModelInputItem toEscapedModel();
}
