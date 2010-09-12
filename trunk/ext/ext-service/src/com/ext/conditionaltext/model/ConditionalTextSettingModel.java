package com.ext.conditionaltext.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ConditionalTextSettingModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ConditionalTextSetting</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.conditionaltext.model.ConditionalTextSetting
 * @see com.ext.conditionaltext.model.impl.ConditionalTextSettingImpl
 * @see com.ext.conditionaltext.model.impl.ConditionalTextSettingModelImpl
 *
 */
public interface ConditionalTextSettingModel extends BaseModel<ConditionalTextSetting> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getConditionalTextSettingId();

    public void setConditionalTextSettingId(Long ConditionalTextSettingId);

    public String getStyleClass();

    public void setStyleClass(String styleClass);

    public String getParamKey();

    public void setParamKey(String paramKey);

    public String getParamValue();

    public void setParamValue(String paramValue);

    public String getHtml();

    public void setHtml(String html);

    public ConditionalTextSetting toEscapedModel();
}
