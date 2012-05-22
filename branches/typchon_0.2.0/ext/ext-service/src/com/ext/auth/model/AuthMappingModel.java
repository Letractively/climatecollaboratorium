package com.ext.auth.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="AuthMappingModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>AuthMapping</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.auth.model.AuthMapping
 * @see com.ext.auth.model.impl.AuthMappingImpl
 * @see com.ext.auth.model.impl.AuthMappingModelImpl
 *
 */
public interface AuthMappingModel extends BaseModel<AuthMapping> {
    public String getPrimaryKey();

    public void setPrimaryKey(String pk);

    public String getIdentifier();

    public void setIdentifier(String identifier);

    public Long getUserId();

    public void setUserId(Long userId);

    public AuthMapping toEscapedModel();
}
