/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates.service;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Isolation;
import com.liferay.portal.kernel.annotation.Propagation;
import com.liferay.portal.kernel.annotation.Transactional;


/**
 * <a href="DebateDiscussionMapLocalService.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface defines the service. The default implementation is
 * <code>com.ext.portlet.debates.service.impl.DebateDiscussionMapLocalServiceImpl</code>.
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
 * @see com.ext.portlet.debates.service.DebateDiscussionMapLocalServiceUtil
 *
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface DebateDiscussionMapLocalService {
    public com.ext.portlet.debates.model.DebateDiscussionMap addDebateDiscussionMap(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap createDebateDiscussionMap(
        java.lang.Long debateMessageId);

    public void deleteDebateDiscussionMap(java.lang.Long debateMessageId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    public void deleteDebateDiscussionMap(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException;

    public java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.debates.model.DebateDiscussionMap getDebateDiscussionMap(
        java.lang.Long debateMessageId)
        throws com.liferay.portal.SystemException,
            com.liferay.portal.PortalException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.debates.model.DebateDiscussionMap> getDebateDiscussionMaps(
        int start, int end) throws com.liferay.portal.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getDebateDiscussionMapsCount()
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap updateDebateDiscussionMap(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap)
        throws com.liferay.portal.SystemException;

    public com.ext.portlet.debates.model.DebateDiscussionMap updateDebateDiscussionMap(
        com.ext.portlet.debates.model.DebateDiscussionMap debateDiscussionMap,
        boolean merge) throws com.liferay.portal.SystemException;
}
