package com.ext.inlinehelp.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="HelpUserSettingModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>HelpUserSetting</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.inlinehelp.model.HelpUserSetting
 * @see com.ext.inlinehelp.model.impl.HelpUserSettingImpl
 * @see com.ext.inlinehelp.model.impl.HelpUserSettingModelImpl
 *
 */
public interface HelpUserSettingModel extends BaseModel<HelpUserSetting> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getHelpUserSettingId();

    public void setHelpUserSettingId(Long HelpUserSettingId);

    public Long getUserId();

    public void setUserId(Long userId);

    public String getMessageId();

    public void setMessageId(String messageId);

    public Boolean getVisible();

    public void setVisible(Boolean visible);

    public HelpUserSetting toEscapedModel();
}
