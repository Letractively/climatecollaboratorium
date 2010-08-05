package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelGlobalPreferenceModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelGlobalPreference</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelGlobalPreference
 * @see com.ext.portlet.models.model.impl.ModelGlobalPreferenceImpl
 * @see com.ext.portlet.models.model.impl.ModelGlobalPreferenceModelImpl
 *
 */
public interface ModelGlobalPreferenceModel extends BaseModel<ModelGlobalPreference> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelGlobalPreferencePK();

    public void setModelGlobalPreferencePK(Long modelGlobalPreferencePK);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Boolean getVisible();

    public void setVisible(Boolean visible);

    public Integer getWeight();

    public void setWeight(Integer weight);

    public Long getExpertEvaluationPageId();

    public void setExpertEvaluationPageId(Long expertEvaluationPageId);

    public ModelGlobalPreference toEscapedModel();
}
