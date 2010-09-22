package com.ext.portlet.debates.service.base;

import com.ext.portlet.debates.model.DebateDiscussionMap;
import com.ext.portlet.debates.service.DebateDiscussionMapLocalService;
import com.ext.portlet.debates.service.DebateDiscussionMapService;
import com.ext.portlet.debates.service.persistence.DebateDiscussionMapPersistence;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import java.util.List;


public abstract class DebateDiscussionMapLocalServiceBaseImpl
    implements DebateDiscussionMapLocalService {
    @BeanReference(name = "com.ext.portlet.debates.service.DebateDiscussionMapLocalService.impl")
    protected DebateDiscussionMapLocalService debateDiscussionMapLocalService;
    @BeanReference(name = "com.ext.portlet.debates.service.DebateDiscussionMapService.impl")
    protected DebateDiscussionMapService debateDiscussionMapService;
    @BeanReference(name = "com.ext.portlet.debates.service.persistence.DebateDiscussionMapPersistence.impl")
    protected DebateDiscussionMapPersistence debateDiscussionMapPersistence;

    public DebateDiscussionMap addDebateDiscussionMap(
        DebateDiscussionMap debateDiscussionMap) throws SystemException {
        debateDiscussionMap.setNew(true);

        return debateDiscussionMapPersistence.update(debateDiscussionMap, false);
    }

    public DebateDiscussionMap createDebateDiscussionMap(Long debateMessageId) {
        return debateDiscussionMapPersistence.create(debateMessageId);
    }

    public void deleteDebateDiscussionMap(Long debateMessageId)
        throws PortalException, SystemException {
        debateDiscussionMapPersistence.remove(debateMessageId);
    }

    public void deleteDebateDiscussionMap(
        DebateDiscussionMap debateDiscussionMap) throws SystemException {
        debateDiscussionMapPersistence.remove(debateDiscussionMap);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return debateDiscussionMapPersistence.findWithDynamicQuery(dynamicQuery);
    }

    public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return debateDiscussionMapPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    public DebateDiscussionMap getDebateDiscussionMap(Long debateMessageId)
        throws PortalException, SystemException {
        return debateDiscussionMapPersistence.findByPrimaryKey(debateMessageId);
    }

    public List<DebateDiscussionMap> getDebateDiscussionMaps(int start, int end)
        throws SystemException {
        return debateDiscussionMapPersistence.findAll(start, end);
    }

    public int getDebateDiscussionMapsCount() throws SystemException {
        return debateDiscussionMapPersistence.countAll();
    }

    public DebateDiscussionMap updateDebateDiscussionMap(
        DebateDiscussionMap debateDiscussionMap) throws SystemException {
        debateDiscussionMap.setNew(false);

        return debateDiscussionMapPersistence.update(debateDiscussionMap, true);
    }

    public DebateDiscussionMap updateDebateDiscussionMap(
        DebateDiscussionMap debateDiscussionMap, boolean merge)
        throws SystemException {
        debateDiscussionMap.setNew(false);

        return debateDiscussionMapPersistence.update(debateDiscussionMap, merge);
    }

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
