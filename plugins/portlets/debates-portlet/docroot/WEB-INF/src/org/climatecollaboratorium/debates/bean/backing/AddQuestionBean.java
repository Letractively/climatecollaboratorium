/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.liferay.portal.SystemException;

import javax.faces.event.ActionEvent;

public class AddQuestionBean {
    private DebateCategory debateCategory;
    private String content;
    private String title;

    private boolean editing = false;

    public DebateCategory getDebateCategory() {
        return debateCategory;
    }
    public void setDebateCategory(DebateCategory debateCategory) {
        this.debateCategory = debateCategory;
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

    private void reset() {
        debateCategory = null;
        content = null;
        title = null;
    }

    public void cancel(ActionEvent event) {
        reset();
        editing = false;
    }

    public void setEditing(boolean b) {
        this.editing = b;
    }

    public boolean getEditing() {
        return editing;
    }

    public void save(ActionEvent event) throws SystemException {
        Debate d = DebateLocalServiceUtil.createNewDebate(title,content,10144L);
        debateCategory.addDebate(d.getDebateId());
        reset();
        editing = false;
    }

    public void add(DebateCategory delegate) {
        setDebateCategory(delegate);
        editing = true;
    }
}