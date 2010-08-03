/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debatemigration.support;

import com.ext.portlet.debatemigration.model.DebateMigration;
import com.ext.portlet.debatemigration.model.DebateMigrationItem;
import com.ext.portlet.debatemigration.service.DebateMigrationItemLocalServiceUtil;
import com.ext.portlet.debatemigration.service.DebateMigrationLocalServiceUtil;
import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.*;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateCommentLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.portlet.debates.DebatesConstants;
import com.ext.portlet.debates.DebatesUtil;
import com.ext.portlet.debates.MessageTypeException;
import com.ext.portlet.plans.model.PlanPosition;
import com.ext.portlet.plans.service.PlanPositionLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portlet.messageboards.NoSuchCategoryException;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;

import javax.portlet.PortletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jintrone
 * @date: Mar 28, 2010
 */
public class MigrationHelper {

    MigrationProgress progress;

    PortletRequest renderRequest;

    DebateMigration migration;

    public MigrationHelper(MigrationProgress progress, PortletRequest renderRequest) {
       this.progress = progress;
       this.renderRequest = renderRequest;
    }

    public void migrate() throws Exception, NoSuchCategoryException {
        migration = DebateMigrationLocalServiceUtil.addMigration();
        List<MBCategory> categories = DebatesUtil.getTopics(renderRequest);
        Map<DebateCategory,MBCategory> queue = new HashMap<DebateCategory,MBCategory>();
        progress.reset();
        progress.setTask("Message Categories");
        progress.setEnd(categories.size());
        for (MBCategory old:categories) {
            DebateCategory ncat = migrateCategory(old);
            queue.put(ncat,old);
            migration.mapCategory(old.getCategoryId(),ncat.getDebateCategoryPK());
            progress.increment();
        }
        progress.reset();
        migrateDebates(queue);
        migratePlanPositions();

   }

   private DebateCategory migrateCategory(MBCategory cat) throws SystemException {
       long pk = CounterLocalServiceUtil.increment(DebateCategory.class.getName());
       DebateCategory category = DebateCategoryLocalServiceUtil.createDebateCategory(pk);
       category.setAuthorId(cat.getUserId());
       category.setTitle(cat.getName());
       category.setUpdated(cat.getCreateDate());
       DebateCategoryLocalServiceUtil.updateDebateCategory(category);
       return category;
   }

   private void migrateDebates(Map<DebateCategory, MBCategory> queue) throws SystemException, PortalException {
       for (Map.Entry<DebateCategory,MBCategory> ent:queue.entrySet()) {
           List<MBCategory> issues = DebatesUtil.getIssues(renderRequest, ent.getValue().getCategoryId(),0,Integer.MAX_VALUE);
           progress.setEnd(issues.size());
           progress.setTask("Category: "+ent.getKey().getTitle());
           for (MBCategory issue: issues) {
               Debate d = migrateDebate(issue);

               ent.getKey().addDebate(d.getDebateId());
           }
       }
   }

    private Debate migrateDebate(MBCategory issue) throws SystemException, PortalException {
       MBCategory debateCategory = DebatesUtil.getSubcategory(issue.getCategoryId(),
		                    DebatesConstants.ISSUE_DEBATE_CATEGORY_NAME);
	   MBMessage question = DebatesUtil.getDebateMessage(debateCategory.getCategoryId());
       Debate d = DebateLocalServiceUtil.createNewDebate(issue.getName(),"",question.getUserId());
       d.setUpdated(question.getModifiedDate());
       DebateLocalServiceUtil.updateDebate(d);
       migration.mapDebate(issue.getCategoryId(),d.getDebateId());
       migration.mapItem(question.getMessageId(),d.getCurrentRoot().getDebateItemId());
       migrateChildren(question,d.getCurrentRoot());
        return d;
    }

    private void migrateChildren(MBMessage item, DebateItem nitem) throws SystemException, PortalException {

        List<MBMessage> oldchildren = DebatesUtil.getChildMessages(item);
        for (MBMessage oldchild:oldchildren) {
            DebateItem nchild = migrateItem(oldchild,nitem);
            migration.mapItem(oldchild.getMessageId(),nitem.getDebateItemId());
            migrateComments(oldchild,nchild);
            migrateChildren(oldchild,nchild);
        }
    }

    private void migrateComments(MBMessage item, DebateItem nitem) throws SystemException, PortalException {
        MBMessage m = DebatesUtil.getDebateMessageThread(item.getMessageId(), renderRequest);
        if (m !=null ) {
            List<MBMessage> msgs = MBMessageLocalServiceUtil.getThreadMessages(m.getThreadId());
            for (MBMessage comment:msgs) {
                if (comment.equals(m)) continue;
                long userid = comment.getUserId();
                try {
                    UserLocalServiceUtil.getUser(comment.getUserId());
                } catch (Exception e) {
                   userid = 10144l;
                }

                DebateComment ncomment = nitem.addComment(comment.getSubject(),comment.getBody(),userid);
                ncomment.setUpdated(comment.getCreateDate());
                DebateCommentLocalServiceUtil.updateDebateComment(ncomment);
                migration.mapComment(comment.getMessageId(),ncomment.getDebateCommentId());
            }
        }
    }

    private DebateItem migrateItem(MBMessage item, DebateItem nparent) throws SystemException, PortalException {
        DebateItemType type = resolveType(item);
        System.err.println("Migrating child");

        DebateItem nitem = nparent.addChild(item.getSubject(),item.getBody(),item.getUserId(),type.name(), Collections.<DebateItemReference>emptyList(),1L);
        nitem.setUpdated(item.getModifiedDate());
        if (type == DebateItemType.POSITION) {
           List<RatingsEntry> entries = RatingsEntryLocalServiceUtil.getEntries(MBMessage.class.getName(),item.getMessageId());
           for (RatingsEntry entry:entries) {
               if (entry.getScore() > 0) {
                    nitem.voteForThisItem(entry.getUserId());
               }
           }
        }
        return nitem;
    }

    private void migratePlanPositions() throws SystemException {
       List<PlanPosition> positions = PlanPositionLocalServiceUtil.getPlanPositions(0,Integer.MAX_VALUE);
       for (PlanPosition p:positions) {
           DebateMigrationItem item = DebateMigrationItemLocalServiceUtil.findItemMigration(migration.getDebateMigrationPK(),p.getPositionId());
           if (item!=null) {
           p.setPositionId(item.getNewDebateItemId());
           PlanPositionLocalServiceUtil.updatePlanPosition(p);
           }
       }
    }

    private DebateItemType resolveType(MBMessage item) throws SystemException, MessageTypeException {
        switch (DebatesUtil.getMessageType(item.getMessageId())) {
          case DebatesConstants.POSITION_MSG_TYPE: {
              return DebateItemType.POSITION;

          }
          case DebatesConstants.ARGUMENT_PRO_MSG_TYPE: {
              return DebateItemType.ARGUMENT_PRO;

          }
          case DebatesConstants.ARGUMENT_CON_MSG_TYPE: {
              return DebateItemType.ARGUMENT_CON;

          }
        }
        return DebateItemType.QUESTION;
    }



}
