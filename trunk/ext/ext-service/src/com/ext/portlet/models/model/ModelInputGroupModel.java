package com.ext.portlet.models.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="ModelInputGroupModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ModelInputGroup</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.model.ModelInputGroup
 * @see com.ext.portlet.models.model.impl.ModelInputGroupImpl
 * @see com.ext.portlet.models.model.impl.ModelInputGroupModelImpl
 *
 */
public interface ModelInputGroupModel extends BaseModel<ModelInputGroup> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getModelInputGroupPK();

    public void setModelInputGroupPK(Long modelInputGroupPK);

    public Long getModelId();

    public void setModelId(Long modelId);

    public Long getNameAndDescriptionMetaDataId();

    public void setNameAndDescriptionMetaDataId(
        Long nameAndDescriptionMetaDataId);

    public String getName();

    public void setName(String name);

    public String getDescription();

    public void setDescription(String description);

    public Integer getGroupOrder();

    public void setGroupOrder(Integer groupOrder);

    public ModelInputGroup toEscapedModel();
}
