/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.debaterevision.model.DebateCategory;
import com.liferay.portal.SystemException;
import org.climatecollaboratorium.debates.bean.support.DebateCategoryWrapper;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: jintrone
 * @date: Mar 23, 2010
 */
public class DebateCategoriesBean {


    public List<DebateCategoryWrapper> categories;

    public EditDebateCategoryBean editCategory;

    public AddQuestionBean addQuestion;

    public DebatesPreferencesBean debatePreferences;


    
    public DebateCategoriesBean() {
        repopulate();
    }

    public List<DebateCategoryWrapper> getCategories() {

        return categories;
    }

    public void setCategories(List<DebateCategoryWrapper> categories) {
        this.categories = categories;
    }

    private void repopulate() {
        
        List<DebateCategoryWrapper> ncats = new ArrayList<DebateCategoryWrapper>();
        if (debatePreferences == null) {
            debatePreferences = (DebatesPreferencesBean) FacesContext.getCurrentInstance().getApplication().getVariableResolver().resolveVariable(FacesContext.getCurrentInstance(),"preferences");
        }
        try {
            for (DebateCategory cat : debatePreferences.getPortletTopics()) {
                ncats.add(new DebateCategoryWrapper(cat));
            }
           

        } catch (Exception e) {
            e.printStackTrace();
        }
        setCategories(ncats);
    }

    public void setDebatePreferences(DebatesPreferencesBean bean) {
        this.debatePreferences = bean;
    }

    public DebatesPreferencesBean getDebatePreferences() {
        return debatePreferences;
    }

    public void setEditCategoryBean(EditDebateCategoryBean bean) {
        editCategory = bean;
    }

    public EditDebateCategoryBean getEditCategory() {
        return editCategory;
    }

    public void setAddQuestionBean(AddQuestionBean bean) {
        addQuestion = bean;
    }

    public AddQuestionBean getAddQuestionBean() {
        return addQuestion;
    }

    public void doEditCategory(ActionEvent evt) throws SystemException {
        DebateCategoryWrapper wrapper = (DebateCategoryWrapper) evt.getComponent().getAttributes().get("category");
        editCategory.edit(
                wrapper,
                new Runnable() {
                    public void run() {
                        repopulate();
                    }
                });
    }

    public void addCategory(ActionEvent evt) throws SystemException {
        editCategory.add(new Runnable() {
            public void run() {
                if (debatePreferences.getIsManagingTopics()) {
                    try {
                    debatePreferences.addTopic(editCategory.getDebateCategory().getDelegate());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                repopulate();
            }
        });
    }

    

    

}
