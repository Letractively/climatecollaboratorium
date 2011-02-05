package com.ext.portlet.Activity.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="ActivitySubscriptionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>ActivitySubscription</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.Activity.model.ActivitySubscription
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionImpl
 * @see com.ext.portlet.Activity.model.impl.ActivitySubscriptionModelImpl
 *
 */
public interface ActivitySubscriptionModel extends BaseModel<ActivitySubscription> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getPk();

    public void setPk(Long pk);

    public String getClassName();

    public Long getClassNameId();

    public void setClassNameId(Long classNameId);

    public Long getClassPK();

    public void setClassPK(Long classPK);

    public Integer getType();

    public void setType(Integer type);

    public String getExtraData();

    public void setExtraData(String extraData);

    public Long getReceiverId();

    public void setReceiverId(Long receiverId);

    public Date getCreateDate();

    public void setCreateDate(Date createDate);

    public Date getModifiedDate();

    public void setModifiedDate(Date modifiedDate);

    public ActivitySubscription toEscapedModel();
}
