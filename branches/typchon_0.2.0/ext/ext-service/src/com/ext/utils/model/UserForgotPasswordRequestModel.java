package com.ext.utils.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="UserForgotPasswordRequestModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>UserForgotPasswordRequest</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.utils.model.UserForgotPasswordRequest
 * @see com.ext.utils.model.impl.UserForgotPasswordRequestImpl
 * @see com.ext.utils.model.impl.UserForgotPasswordRequestModelImpl
 *
 */
public interface UserForgotPasswordRequestModel extends BaseModel<UserForgotPasswordRequest> {
    public String getPrimaryKey();

    public void setPrimaryKey(String pk);

    public String getToken();

    public void setToken(String token);

    public Long getUserId();

    public void setUserId(Long userId);

    public Date getCreated();

    public void setCreated(Date created);

    public UserForgotPasswordRequest toEscapedModel();
}
