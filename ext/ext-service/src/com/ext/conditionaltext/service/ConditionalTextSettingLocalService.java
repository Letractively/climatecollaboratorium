package com.ext.conditionaltext.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="ConditionalTextSettingLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.conditionaltext.service.impl.ConditionalTextSettingLocalServiceImpl</code>.
 * Modify methods in that class and rerun ServiceBuilder to populate this class
 * and all other generated classes.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.conditionaltext.service.ConditionalTextSettingLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ConditionalTextSettingLocalService {
    public com.ext.conditionaltext.model.ConditionalTextSetting addConditionalTextSetting(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting createConditionalTextSetting(
        java.lang.Long ConditionalTextSettingId);

    public void deleteConditionalTextSetting(
        java.lang.Long ConditionalTextSettingId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteConditionalTextSetting(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.conditionaltext.model.ConditionalTextSetting getConditionalTextSetting(
        java.lang.Long ConditionalTextSettingId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> getConditionalTextSettings(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getConditionalTextSettingsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting updateConditionalTextSetting(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting)
        throws com.liferay.portal.SystemException;

    public com.ext.conditionaltext.model.ConditionalTextSetting updateConditionalTextSetting(
        com.ext.conditionaltext.model.ConditionalTextSetting conditionalTextSetting,
        boolean merge) throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.conditionaltext.model.ConditionalTextSetting> findByKey(
        java.lang.String key);

    public com.ext.conditionaltext.model.ConditionalTextSetting findByKeyVal(
        java.lang.String key, java.lang.String val);

    public void updateSetting(java.lang.String key, java.lang.String val,
        java.lang.String style, java.lang.String text)
        throws com.liferay.portal.SystemException;
}
