package org.climatecollaboratorium.debatebrowser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.debatebrowser.utils.Helper;
import org.climatecollaboratorium.debatebrowser.wrappers.DebateCategoryWrapper;
import org.climatecollaboratorium.debatebrowser.wrappers.NewItemEdit;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class DebateBrowserBean {
    private List<DebateCategoryWrapper> categories = new ArrayList<DebateCategoryWrapper>();
    private DebateCategoryWrapper category;
    private Debate debate;
    private DebateBrowserPageType pageType = DebateBrowserPageType.CATEGORIES;
    private DebateBrowserPreferences preferences;
    private NewItemEdit newItem = new NewItemEdit();

    public DebateBrowserBean() {
    }

    public List<DebateCategoryWrapper> getCategories() {
        return categories;
    }

    public DebateCategoryWrapper getCategory() throws SystemException {
        return category;
    }

    public Debate getDebate() throws SystemException {
        return debate;
    }

    public DebateBrowserPageType getPageType() {
        return pageType;
    }

    public void setPreferences(DebateBrowserPreferences preferences) {
        this.preferences = preferences;
    }

    public DebateBrowserPreferences getPreferences() {
        return preferences;
    }

    public void setInitialize(String x) throws PortalException, SystemException {

        for (Long id : preferences.getVisibleCategoriesIds()) {
            categories.add(new DebateCategoryWrapper(DebateCategoryLocalServiceUtil.getDebateCategory(id), false,
                    preferences.getDebatesOnIndex(id)));
        }
        Map<String, String> params = Helper.getUrlParametersMap();

        if (params.containsKey("categoryId")) {
            Long categoryId = Long.parseLong(params.get("categoryId"));
            category = new DebateCategoryWrapper(DebateCategoryLocalServiceUtil.getDebateCategory(categoryId), true, 0);
            pageType = DebateBrowserPageType.CATEGORY_DEBATES;

            if (params.containsKey("debateId")) {
                Long debateId = Long.parseLong(params.get("debateId"));
                debate = DebateLocalServiceUtil.getDebate(debateId);
                pageType = DebateBrowserPageType.DEBATE_DETAILS;
            }
        }
    }

    public void toggleAddCategoryForm(ActionEvent e) {
        if (pageType == DebateBrowserPageType.ADD_CATEGORY) {
            pageType = DebateBrowserPageType.CATEGORIES;
            newItem = new NewItemEdit();
        } else {
            pageType = DebateBrowserPageType.ADD_CATEGORY;
            newItem = new NewItemEdit();
        }
    }

    public void toggleAddDebateForm(ActionEvent e) {
        if (pageType == DebateBrowserPageType.ADD_DEBATE) {
            pageType = DebateBrowserPageType.CATEGORY_DEBATES;
            newItem = new NewItemEdit();
        } else {
            newItem = new NewItemEdit();
            pageType = DebateBrowserPageType.ADD_DEBATE;
        }
    }

    public void setNewItem(NewItemEdit newItem) {
        this.newItem = newItem;
    }

    public NewItemEdit getNewItem() {
        return newItem;
    }
    
    public void addDebate(ActionEvent e) throws SystemException {
        if (!newItem.isAdded()) {
            DebateCategory category = this.category.getWrapped();

            Debate debate = DebateLocalServiceUtil.createNewDebate(newItem.getTitle(), newItem.getDescription(), Helper
                    .getLiferayUser().getUserId());

            category.addDebate(debate.getDebateId());
            newItem.setId(debate.getDebateId());
            newItem.setAdded(true);

            this.category = new DebateCategoryWrapper(category, true, 0);
            pageType = DebateBrowserPageType.CATEGORY_DEBATES;
        }
    }
    
    public void addCategory(ActionEvent e) throws SystemException {
        if (!newItem.isAdded()) {
            Long debateCategoryId = CounterLocalServiceUtil.increment(DebateCategory.class.getName());
            
            DebateCategory newCategory = DebateCategoryLocalServiceUtil.createDebateCategory(debateCategoryId);
            newCategory.setAuthorId(Helper.getLiferayUser().getUserId());
            newCategory.setDescription(newItem.getDescription());
            newCategory.setTitle(newItem.getTitle());
            
            DebateCategoryLocalServiceUtil.addDebateCategory(newCategory);

            newItem.setId(newCategory.getDebateCategoryPK());
            newItem.setAdded(true);
            
            pageType = DebateBrowserPageType.CATEGORY_DEBATES;
            category = new DebateCategoryWrapper(newCategory, true, 0);
        }
    }

}
