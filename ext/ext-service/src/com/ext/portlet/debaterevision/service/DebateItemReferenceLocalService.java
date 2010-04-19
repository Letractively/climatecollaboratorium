package com.ext.portlet.debaterevision.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DebateItemReferenceLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.debaterevision.service.impl.DebateItemReferenceLocalServiceImpl</code>.
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
 * @see com.ext.portlet.debaterevision.service.DebateItemReferenceLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DebateItemReferenceLocalService {
    public com.ext.portlet.debaterevision.model.DebateItemReference addDebateItemReference(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference createDebateItemReference(
        java.lang.Long debateItemReferencePK);

    public void deleteDebateItemReference(java.lang.Long debateItemReferencePK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDebateItemReference(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debaterevision.model.DebateItemReference getDebateItemReference(
        java.lang.Long debateItemReferencePK)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> getDebateItemReferences(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDebateItemReferencesCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference updateDebateItemReference(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference updateDebateItemReference(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference,
        boolean merge) throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference createNew(
        java.lang.String text, java.lang.String url)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debaterevision.model.DebateItemReference createNewFrom(
        com.ext.portlet.debaterevision.model.DebateItem item,
        com.ext.portlet.debaterevision.model.DebateItemReference template)
        throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> getDebateItemReferences(
        com.ext.portlet.debaterevision.model.DebateItem item)
        throws com.liferay.portal.SystemException;
}
