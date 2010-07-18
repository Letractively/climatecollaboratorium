/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.debates.service.base;

import com.ext.portlet.debates.service.DebateDiscussionMapLocalService;
import com.ext.portlet.debates.service.DebateDiscussionMapService;
import com.ext.portlet.debates.service.persistence.DebateDiscussionMapPersistence;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.service.base.PrincipalBean;
import com.liferay.portal.util.PortalUtil;


public abstract class DebateDiscussionMapServiceBaseImpl extends PrincipalBean
    implements DebateDiscussionMapService {
    @BeanReference(name = "com.ext.portlet.debates.service.DebateDiscussionMapLocalService.impl")
    protected DebateDiscussionMapLocalService debateDiscussionMapLocalService;
    @BeanReference(name = "com.ext.portlet.debates.service.DebateDiscussionMapService.impl")
    protected DebateDiscussionMapService debateDiscussionMapService;
    @BeanReference(name = "com.ext.portlet.debates.service.persistence.DebateDiscussionMapPersistence.impl")
    protected DebateDiscussionMapPersistence debateDiscussionMapPersistence;

    public DebateDiscussionMapLocalService getDebateDiscussionMapLocalService() {
        return debateDiscussionMapLocalService;
    }

    public void setDebateDiscussionMapLocalService(
        DebateDiscussionMapLocalService debateDiscussionMapLocalService) {
        this.debateDiscussionMapLocalService = debateDiscussionMapLocalService;
    }

    public DebateDiscussionMapService getDebateDiscussionMapService() {
        return debateDiscussionMapService;
    }

    public void setDebateDiscussionMapService(
        DebateDiscussionMapService debateDiscussionMapService) {
        this.debateDiscussionMapService = debateDiscussionMapService;
    }

    public DebateDiscussionMapPersistence getDebateDiscussionMapPersistence() {
        return debateDiscussionMapPersistence;
    }

    public void setDebateDiscussionMapPersistence(
        DebateDiscussionMapPersistence debateDiscussionMapPersistence) {
        this.debateDiscussionMapPersistence = debateDiscussionMapPersistence;
    }

    protected void runSQL(String sql) throws SystemException {
        try {
            PortalUtil.runSQL(sql);
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
