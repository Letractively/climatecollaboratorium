package com.ext.portlet.twitter.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="UserTwitterMappingModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>UserTwitterMapping</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.twitter.model.UserTwitterMapping
 * @see com.ext.portlet.twitter.model.impl.UserTwitterMappingImpl
 * @see com.ext.portlet.twitter.model.impl.UserTwitterMappingModelImpl
 *
 */
public interface UserTwitterMappingModel extends BaseModel<UserTwitterMapping> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getTwitterId();

    public void setTwitterId(Long twitterId);

    public Long getUserId();

    public void setUserId(Long userId);

    public UserTwitterMapping toEscapedModel();
}
