package com.ext.portlet.facebook.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="UserFacebookMappingModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>UserFacebookMapping</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.facebook.model.UserFacebookMapping
 * @see com.ext.portlet.facebook.model.impl.UserFacebookMappingImpl
 * @see com.ext.portlet.facebook.model.impl.UserFacebookMappingModelImpl
 *
 */
public interface UserFacebookMappingModel extends BaseModel<UserFacebookMapping> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getFacebookId();

    public void setFacebookId(String facebookId);

    public UserFacebookMapping toEscapedModel();
}
