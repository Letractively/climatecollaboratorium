/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.liferay.portal.SystemException;
import org.climatecollaboratorium.debates.bean.support.DebateCategoryWrapper;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.*;

/**
 * @author: jintrone
 * @date: Mar 23, 2010
 */
public class DebateCategoriesBean {


    public List<DebateCategoryWrapper> categories;

    public EditDebateCategoryBean editCategory;

    public AddQuestionBean addQuestion;

    public DebatesPreferencesBean debatePreferences;


    
    public DebateCategoriesBean() throws SystemException {
        repopulate();
    }

    public List<DebateCategoryWrapper> getCategories() {

        return categories;
    }

    public void setCategories(List<DebateCategoryWrapper> categories) throws SystemException {
        // sort debate items randomly
        final Random rand = new Random();
        for (DebateCategoryWrapper category: categories) {
            Collections.sort(category.getDebates(), new Comparator<Debate>() {
                public int compare(Debate o1, Debate o2) {
                    return 1 - rand.nextInt(2);
                }
            });
        }
        this.categories = categories;
    }

    private void repopulate() throws SystemException {
        
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
                        try {
                            repopulate();
                        }
                        catch (Exception e) {
                            // ignore
                        }
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
                try {
                    repopulate();
                }
                catch (Exception e) {
                    // ignore
                }
            }
        });
    }

    

    

}
