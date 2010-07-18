/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import org.climatecollaboratorium.debates.activity.DebateActivityKeys;
import org.climatecollaboratorium.debates.bean.support.DebateCategoryWrapper;

import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EditDebateCategoryBean {
    private DebateCategoryWrapper debateCategory;
    private String content;
    private String title;
    private boolean editing = false;
    private Runnable callback;
    private ThemeDisplay td = Helper.getThemeDisplay();



    private final Map<Long,DebateSelectionWrapper> selected = new LinkedHashMap<Long,DebateSelectionWrapper>();

    public EditDebateCategoryBean() {

    }

    public DebateCategoryWrapper getDebateCategory() {
        return debateCategory;
    }

    public void setDebateCategory(DebateCategoryWrapper debateCategory) throws SystemException {
        this.debateCategory = debateCategory;
        if (debateCategory != null) {
            setTitle(debateCategory.getTitle());
            setContent(debateCategory.getDelegate().getDescription());
            for (Debate d : debateCategory.getDebates()) {
                selected.get(d.getDebateId()).setInModel(true);
            }

        }
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEditing() {

        return editing;
    }

    public void setEditing(boolean b) throws SystemException {

        editing = b;

    }

    public void moveAvailableToExisting() {
        for (DebateSelectionWrapper item:selected.values()) {
           if (item.isSelectedToAdd()) {
               item.setSelectionStatus(DebateSelectionWrapper.NOT_SELECTED);
               item.scheduleModelAddition();
           }
        }
    }

    public void moveExistingToAvailable() {
        for (DebateSelectionWrapper item:selected.values()) {
           if (item.isSelectedToRemove()) {
               item.setSelectionStatus(DebateSelectionWrapper.NOT_SELECTED);
               item.scheduleModelRemoval();
           }
        }
    }


    public void save(ActionEvent event) throws SystemException {
        if (debateCategory == null) {
            long pk = CounterLocalServiceUtil.increment(DebateCategory.class.getName());
            DebateCategory nCategory = DebateCategoryLocalServiceUtil.createDebateCategory(pk);
            debateCategory = new DebateCategoryWrapper(nCategory);
        }
        String userid = Helper.getLiferayUserId();
        debateCategory.setAuthorId(userid == null ? 10144L : Long.parseLong(userid));
        debateCategory.setDescription(content);
        debateCategory.setTitle(title);
        DebateCategoryLocalServiceUtil.updateDebateCategory(debateCategory.getDelegate());
        resolveDebates(debateCategory);
        if (callback != null) {
            callback.run();
        }
        reset();
    }

    private void resolveDebates(DebateCategoryWrapper category) throws SystemException {
        for (DebateSelectionWrapper item:selected.values()) {
            DebateItem question = item.debate.getCurrentRoot();
           if (item.isInModel() && !item.isScheduledToAdd()) {
               category.removeDebate(item.debate.getDebateId());
               try {
                   SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                       DebateCategory.class.getName(), category.getDebateCategoryPK(), DebateActivityKeys.REMOVE_QUESTION_FROM_CATEGORY.id(),
                       question.getDebateItemId()+"", 0);
               } catch (PortalException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               }
           } else if (!item.isInModel() && item.isScheduledToAdd()) {
               category.addDebate(item.debate.getDebateId());
               try {
                   SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                       DebateCategory.class.getName(), category.getDebateCategoryPK(), DebateActivityKeys.ADD_QUESTION_TO_CATEGORY.id(),
                       question.getDebateItemId()+"", 0);
               } catch (PortalException e) {
                   e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
               }      
           }
        }
    }


    public void cancel(ActionEvent event) {
        reset();
    }

    private void reset() {

        this.content = null;
        this.title = null;
        callback = null;
        editing = false;
        debateCategory = null;
        selected.clear();
    }

    private void repopulate() throws SystemException {
        selected.clear();
        for (Debate d : DebateLocalServiceUtil.getDebates()) {
            selected.put(d.getDebateId(),new DebateSelectionWrapper(d));
        }
    }

    public void edit(DebateCategoryWrapper category, Runnable r) throws SystemException {
        repopulate();
        setDebateCategory(category);
        callback = r;
        setEditing(true);
    }

    public void add(Runnable r) throws SystemException {
        try {
        repopulate();
        callback = r;
        setEditing(true);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    public List<DebateSelectionWrapper> getDebates() {
        List<DebateSelectionWrapper> result = new ArrayList<DebateSelectionWrapper>(selected.values());
        return result;
    }

    public static class DebateSelectionWrapper {

        public static int NOT_SELECTED = 0;
        public static int SELECTED_MOVE_IN = 1;
        public static int SELECTED_MOVE_OUT = 2;

        public Debate debate;

        public boolean inModel = false;
        public boolean scheduledAdd = false;

        public int selectionStatus = NOT_SELECTED;

        public DebateSelectionWrapper(Debate item) {
            this.debate = item;
        }



        public String getText() {
             DebateItem item = debate.getCurrentRoot();
             if (item == null) {
                 System.err.println("Uh oh - item is null");
                 return "--null--";
             } else {
                return item.getDebateSummary();
             }
        }

        public boolean isInModel() {
            return inModel;
        }

        public void setInModel(boolean b) {
            this.inModel = b;
            this.scheduledAdd = true;
        }

        public boolean isScheduledToAdd() {
            return scheduledAdd;
        }

        public void scheduleModelAddition() {
           scheduledAdd = true;
        }

        public void scheduleModelRemoval() {
           scheduledAdd = false;
        }

        public boolean isSelectedToAdd() {
           return selectionStatus == SELECTED_MOVE_IN;
        }

        public boolean isSelectedToRemove() {
            return selectionStatus == SELECTED_MOVE_OUT;
        }

        public void setSelectionStatus(int status) {
            this.selectionStatus = status;
        }

        public void selectToAdd() {
            setSelectionStatus(SELECTED_MOVE_IN);
        }

        public void selectToRemove() {
            setSelectionStatus(SELECTED_MOVE_OUT);
        }




    }


}