package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelOutputChartOrderModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelOutputChartOrder</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelOutputChartOrder
 * @see com.ext.portlet.models.model.impl.ModelOutputChartOrderImpl
 * @see com.ext.portlet.models.model.impl.ModelOutputChartOrderModelImpl
 *
 */
public interface ModelOutputChartOrderModel extends BaseModel<ModelOutputChartOrder> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelOutputChartOrderPK();

    public void setModelOutputChartOrderPK(Long modelOutputChartOrderPK);

    public Long getModelId();

    public void setModelId(Long modelId);

    public String getModelOutputLabel();

    public void setModelOutputLabel(String modelOutputLabel);

    public Integer getModelOutputChartOrder();

    public void setModelOutputChartOrder(Integer modelOutputChartOrder);

    public String getModelIndexRangePolicy();

    public void setModelIndexRangePolicy(String modelIndexRangePolicy);

    public String getModelIndexRangeMessage();

    public void setModelIndexRangeMessage(String modelIndexRangeMessage);

    public String getModelIndexErrorPolicy();

    public void setModelIndexErrorPolicy(String modelIndexErrorPolicy);

    public String getModelIndexErrorMessage();

    public void setModelIndexErrorMessage(String modelIndexErrorMessage);

    public Boolean getModelChartIsVisible();

    public void setModelChartIsVisible(Boolean modelChartIsVisible);

    public ModelOutputChartOrder toEscapedModel();
}
