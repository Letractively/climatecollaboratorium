package com.ext.portlet.debatemigration.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DebateMigrationLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.debatemigration.service.impl.DebateMigrationLocalServiceImpl</code>.
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
 * @see com.ext.portlet.debatemigration.service.DebateMigrationLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DebateMigrationLocalService {
    public com.ext.portlet.debatemigration.model.DebateMigration addDebateMigration(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigration createDebateMigration(
        java.lang.Long debateMigrationPK);

    public void deleteDebateMigration(java.lang.Long debateMigrationPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDebateMigration(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debatemigration.model.DebateMigration getDebateMigration(
        java.lang.Long debateMigrationPK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debatemigration.model.DebateMigration> getDebateMigrations(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDebateMigrationsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigration updateDebateMigration(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigration updateDebateMigration(
        com.ext.portlet.debatemigration.model.DebateMigration debateMigration,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debatemigration.model.DebateMigration addMigration()
        throws com.liferay.portal.SystemException;
}
