package com.ext.portlet.messaging.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="MessagingUserPreferencesModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>MessagingUserPreferences</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.messaging.model.MessagingUserPreferences
 * @see com.ext.portlet.messaging.model.impl.MessagingUserPreferencesImpl
 * @see com.ext.portlet.messaging.model.impl.MessagingUserPreferencesModelImpl
 *
 */
public interface MessagingUserPreferencesModel extends BaseModel<MessagingUserPreferences> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getMessagingPreferencesId();

    public void setMessagingPreferencesId(Long messagingPreferencesId);

    public Long getUserId();

    public void setUserId(Long userId);

    public Boolean getEmailOnSend();

    public void setEmailOnSend(Boolean emailOnSend);

    public Boolean getEmailOnReceipt();

    public void setEmailOnReceipt(Boolean emailOnReceipt);

    public MessagingUserPreferences toEscapedModel();
}
