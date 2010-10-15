/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates.bean.support;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.liferay.portal.SystemException;
import org.climatecollaboratorium.debates.activity.DebateActivityKeys;
import org.climatecollaboratorium.debates.bean.backing.Helper;

import java.util.*;

/**
 * @author: jintrone
 * @date: Mar 23, 2010
 */
public class DebateCategoryWrapper {

    public String sortColumnName;

    public boolean ascending = false;

    public String commentCountColName = "# Comments";

    public String commentDateColName = "Last post";

    public String oldSort;

    public boolean oldAscending = false;

    public List<Debate> debatecache;

    public DebateCategoryWrapper() {
    }


    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean b) {
        ascending = b;
    }

    public String getSortColumnName() {
         return sortColumnName;
    }

    public void setSortColumnName(String name) {
        this.sortColumnName = name;
    }

    public String getCommentCountColName() {
        return commentCountColName;
    }

    public String getCommentDateColName() {
        return commentDateColName;
    }

    public DebateCategoryWrapper(DebateCategory delegate) {
        this.delegate = delegate;
    }

    public void addDebate(long debateId) throws SystemException {
        delegate.addDebate(debateId);
        debatecache = null;

    }

    public List<Debate> getDebates() throws SystemException {
        if (debatecache == null) {
            debatecache = new ArrayList<Debate>();
            debatecache.addAll(delegate.getDebates());
            sort();
        } else if ((sortColumnName != null && !(sortColumnName.equals(oldSort))) ||
                ascending != oldAscending) {

            sort();

        }
        return debatecache;
    }

    private void sort() {
        oldAscending = ascending;
        oldSort = sortColumnName;
        Comparator comparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                Debate c1 = (Debate) o1;
                Debate c2 = (Debate) o2;
                if (sortColumnName == null) {
                    return 0;
                }
                try {
                    if (sortColumnName.equals(commentCountColName)) {
                        return ascending ?
                                Integer.valueOf(c1.getNumberOfComments()).compareTo(Integer.valueOf(c2.getNumberOfComments())) :
                                Integer.valueOf(c2.getNumberOfComments()).compareTo(Integer.valueOf(c1.getNumberOfComments()));
                    } else if (sortColumnName.equals(commentDateColName)) {
                        if (c1.getMostRecentComment() == null && c2.getMostRecentComment() != null) return ascending?-1:1;
                        else if (c2.getMostRecentComment() == null && c1.getMostRecentComment() != null) return ascending?1:-1;
                        else if (c1.getMostRecentComment() == null) return 0;
                        return ascending ? c1.getMostRecentComment().getUpdated().compareTo(c2.getMostRecentComment().getUpdated()) :
                                c2.getMostRecentComment().getUpdated().compareTo(c1.getMostRecentComment().getUpdated());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return 0;
            }
        };
        Collections.sort(debatecache, comparator);

    }

    public void removeDebate(long debateId) throws SystemException {
        delegate.removeDebate(debateId);
        debatecache = null;
    }

    public Long getDebateCategoryPK() {
        return delegate.getDebateCategoryPK();
    }

    public String getTitle() {
        return delegate.getTitle();
    }

    public void setTitle(String title) {
        delegate.setTitle(title);
    }

    public String getDescription() {
        return Helper.filterContent(delegate.getDescription());
    }

    public void setDescription(String description) {
        delegate.setDescription(description);
        update();
    }

    public void setAuthorId(Long authorId) {
        delegate.setAuthorId(authorId);
        update();
    }

    public Long getAuthorId() {
        return delegate.getAuthorId();

    }

    public Date getUpdated() {
        return delegate.getUpdated();
    }


    private void update() {
        try {
            DebateCategoryLocalServiceUtil.updateDebateCategory(delegate);
        } catch (SystemException e) {
            //do something
        }
    }

    public void setDelegate(DebateCategory category) {
        this.delegate = category;
    }

    public DebateCategory getDelegate() {
        return delegate;
    }

    public boolean isSubscribed() {
        String userid = Helper.getLiferayUserId();
        if (userid == null || delegate == null) return false;

        try {
            return ActivityUtil.isSubscribed(DebateActivityKeys.ALL, Long.parseLong(userid), delegate.getDebateCategoryPK());
        } catch (SystemException e) {

        }
        return false;
    }

    public void setSubscribed(boolean b) {
        String userid = Helper.getLiferayUserId();
        if (userid == null || delegate == null) return;

        if (!b && isSubscribed()) {
            try {
                ActivityUtil.removeSubscription(DebateActivityKeys.ALL, Long.parseLong(userid), delegate.getDebateCategoryPK());
            } catch (SystemException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } else if (b && !isSubscribed()) {
            try {
                ActivityUtil.addSubscription(DebateActivityKeys.ALL, Long.parseLong(userid), delegate.getDebateCategoryPK());
            } catch (SystemException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public void subscribe() {
        setSubscribed(true);
    }

    public void unsubscribe() {
        setSubscribed(false);
    }

    private DebateCategory delegate;


}
