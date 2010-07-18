package com.ext.portlet.debatemigration.model.impl;

import com.ext.portlet.debatemigration.model.*;
import com.ext.portlet.debatemigration.service.DebateMigrationCategoryLocalServiceUtil;
import com.ext.portlet.debatemigration.service.DebateMigrationCommentLocalServiceUtil;
import com.ext.portlet.debatemigration.service.DebateMigrationDebateLocalServiceUtil;
import com.ext.portlet.debatemigration.service.DebateMigrationItemLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;


public class DebateMigrationImpl extends DebateMigrationModelImpl
    implements DebateMigration {
    public DebateMigrationImpl() {

    }

        public void mapCategory(long mbcategoryid, long debatecategoryid) throws SystemException {
            long pk = CounterLocalServiceUtil.increment(DebateMigrationCategory.class.getName());
            DebateMigrationCategory categorymapping = DebateMigrationCategoryLocalServiceUtil.createDebateMigrationCategory(pk);
            categorymapping.setOldMBCategoryId(mbcategoryid);
            categorymapping.setNewCategoryId(debatecategoryid);
            categorymapping.setDebateMigrationId(this.getDebateMigrationPK());
            DebateMigrationCategoryLocalServiceUtil.updateDebateMigrationCategory(categorymapping);
        }

    public void mapDebate(long mbcategoryid, long debateid) throws SystemException {
            long pk = CounterLocalServiceUtil.increment(DebateMigrationDebate.class.getName());
            DebateMigrationDebate debatemapping = DebateMigrationDebateLocalServiceUtil.createDebateMigrationDebate(pk);
            debatemapping.setOldMBCategoryId(mbcategoryid);
            debatemapping.setNewDebateId(debateid);
            debatemapping.setDebateMigrationId(this.getDebateMigrationPK());
            DebateMigrationDebateLocalServiceUtil.updateDebateMigrationDebate(debatemapping);
        }

    public void mapItem(long mbmessageid, long debateitemid) throws SystemException {
            long pk = CounterLocalServiceUtil.increment(DebateMigrationItem.class.getName());
            DebateMigrationItem itemmapping = DebateMigrationItemLocalServiceUtil.createDebateMigrationItem(pk);
            itemmapping.setOldMBMessageId(mbmessageid);
            itemmapping.setNewDebateItemId(debateitemid);
            itemmapping.setDebateMigrationId(this.getDebateMigrationPK());
            DebateMigrationItemLocalServiceUtil.updateDebateMigrationItem(itemmapping);
     }

    public void mapComment(long mbmessageid, long debatecommentid) throws SystemException {
            long pk = CounterLocalServiceUtil.increment(DebateMigrationComment.class.getName());
            DebateMigrationComment commentmapping = DebateMigrationCommentLocalServiceUtil.createDebateMigrationComment(pk);
            commentmapping.setOldMBMessageId(mbmessageid);
            commentmapping.setNewDebateCommentId(debatecommentid);
            commentmapping.setDebateMigrationId(this.getDebateMigrationPK());
            DebateMigrationCommentLocalServiceUtil.updateDebateMigrationComment(commentmapping);
     }
}
