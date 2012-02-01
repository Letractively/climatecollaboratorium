package org.climatecollaboratorium.facelets.debates;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.DebateItemReference;
import com.ext.portlet.debaterevision.service.DebateItemReferenceLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.ext.utils.userInput.UserInputException;
import com.ext.utils.userInput.service.UserInputFilterUtil;
import com.icesoft.faces.async.render.Renderable;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import org.climatecollaboratorium.facelets.debates.activity.DebateActivityKeys;
import org.climatecollaboratorium.facelets.debates.backing.DebatesPermissionsBean;
import org.climatecollaboratorium.facelets.debates.backing.Helper;
import org.climatecollaboratorium.facelets.debates.support.DebateCategoryWrapper;
import org.climatecollaboratorium.facelets.debates.support.DebateItemReferenceWrapper;
import org.compass.core.util.backport.java.util.Collections;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddEditDebateItemBean {
    private static final int DEFAULT_ROWS_TO_SHOW = 4;
    private DebateItem item;
    private boolean adding;
    private boolean editing;
    private String title;
    private String content;
    private DebateItemType type;
    private DebateItem selectedHistoryVersion;
    private DebateBean debateBean;
    private String referenceUrl;
    private String referenceText;
    private List<DebateItemReference> references;
    private List<DebateItemReferenceWrapper> wrappedReferences;
    private ThemeDisplay td = Helper.getThemeDisplay();
    private DebateCategoryWrapper debateCategory;
    private Long weight = 0L;
    private int rowsToShow = DEFAULT_ROWS_TO_SHOW;
    private int versionsAvailable = 0;
    private boolean showingAllVersions;
    
    private DebatesPermissionsBean permissions;

    public DebateItem getItem() {
        return item;
    }
    public void setItem(DebateItem item) throws SystemException {
        this.item = item;
        rowsToShow = DEFAULT_ROWS_TO_SHOW;
        showingAllVersions = false;
        versionsAvailable = item.getCompleteHistory().size();
        type = DebateItemType.valueOf(item.getDebatePostType());
        selectedHistoryVersion = item;
        setReferences(item.getReferences());
        setEditedItem(selectedHistoryVersion);
    }
    
    

    public boolean isAdding() {
        return adding;
    }
    public void setAdding(boolean adding) {
        this.adding = adding;
    }
    public boolean isEditing() {
        return editing;
    }
    public void setEditing(boolean editing) {
        this.editing = editing;
    }
    public DebateItemType getType() {
        if (item != null && editing) {
            return DebateItemType.valueOf(item.getDebatePostType());
        }
        return type;
    }
    public void setType(DebateItemType type) {
        this.type = type;
    }
    public String getTitle() {
        if (selectedHistoryVersion != null && editing) {
            return selectedHistoryVersion.getDebateSummary();
        }
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        
        if (selectedHistoryVersion != null && editing) {
            
            return selectedHistoryVersion.getDebateDetail();
        }
        
        return content;
    }
    
    public void setContent(String content) throws UserInputException {
        this.content = UserInputFilterUtil.filterHtml(content);
    }

    public void addQuestion(ActionEvent event) {
        addDebateItem(DebateItemType.QUESTION);
        debateCategory = (DebateCategoryWrapper)event.getComponent().getAttributes().get("category");
    }

    public void addPosition(ActionEvent event) {
        addDebateItem(DebateItemType.POSITION);
    }

    public void addArgumentPro(ActionEvent event) {
        addDebateItem(DebateItemType.ARGUMENT_PRO);
    }

    public void addArgumentCon(ActionEvent event) {
        addDebateItem(DebateItemType.ARGUMENT_CON);
    }

    public void cancelAddEdit(ActionEvent event) {
        hideForms();
    }

    public void addDebateItem(DebateItemType type) {
        this.type = type;
        adding = true;
        editing = false;
        setReferences(new ArrayList<DebateItemReference>());
    }

    public void save(ActionEvent event) throws SystemException, PortalException, RenderingException {

        if (! permissions.getCanEditDebateMap() && ! permissions.getCanAdmin()) {
            hideForms();
            return;
        }
        DebateItem savedItem = null;
        long userId =  Helper.getLiferayUser().getUserId();
        if (adding) {
            if (type.equals(DebateItemType.QUESTION)) {
                Debate d = DebateLocalServiceUtil.createNewDebate(title,content,userId);
                savedItem = d.getCurrentRoot();
                // debateDetailsBean.debateItemAdded(savedItem);
                
                if (debateCategory!=null) {
                    debateCategory.addDebate(d.getDebateId());
                    SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                        DebateCategory.class.getName(), debateCategory.getDebateCategoryPK(), DebateActivityKeys.ADD_QUESTION_TO_CATEGORY.id(),
                        savedItem.getDebateItemId()+"", 0);
                }

                debateCategory = null;


                    

            } else if (type.equals(DebateItemType.POSITION)) {
                Debate debate = debateBean.getDebate();
                // USER ID has to be taken from the session
                savedItem = debate.getCurrentRoot().addChild(title, content, userId, type.toString(), references, weight == null ? 0 : weight);
                //debateBean.debateItemAdded(savedItem);
                
                /* activity */
                /*
                 SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), 
                        DebateItem.class.getName(), savedItem.getDebateItemId(), DebateActivityKeys.ADD_POSITION.id(), 
                        StringPool.BLANK, 0);
                */
                 SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), 
                        Debate.class.getName(), savedItem.getDebateId(), DebateActivityKeys.ADD_POSITION.id(), 
                        ActivityUtil.getExtraDataForIds(debate.getCurrentRoot().getDebateItemId(), savedItem.getDebateItemId()), 0);
            }
            else {
                DebateItem parent = debateBean.getCurrentItem().getItem();
                savedItem = parent.addChild(title, content, userId, type.toString(), references, weight);
                //debateBean.debateItemAdded(savedItem);
                Debate debate = savedItem.getDebate();

                Long[] itemsIdsArray = DebatesUtil.getIdsOnDebatePath(savedItem);                
                
                
                /* activity */
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), 
                        Debate.class.getName(), savedItem.getDebateId(), DebateActivityKeys.ADD_ARGUMENT.id(), 
                        ActivityUtil.getExtraDataForIds(itemsIdsArray), 0);
            }
            debateBean.debateItemAdded(savedItem);
        }
        else {
            savedItem = item.update(title, content, references, userId, weight == null ? 0 : weight);
            debateBean.debateItemUpdated(savedItem);
            type = DebateItemType.valueOf(savedItem.getDebatePostType());
            Debate debate = savedItem.getDebate();
            
            Long[] itemsIdsArray = DebatesUtil.getIdsOnDebatePath(savedItem);
            
            /* activity */

            if (type.equals(DebateItemType.POSITION)) {
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), 
                        Debate.class.getName(), debate.getDebateId(), DebateActivityKeys.EDIT_POSITION.id(), 
                        ActivityUtil.getExtraDataForIds(itemsIdsArray), 0);
                
            }
            else if (type.equals(DebateItemType.ARGUMENT_CON) || type.equals(DebateItemType.ARGUMENT_PRO)) {
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), 
                        Debate.class.getName(), debate.getDebateId(), DebateActivityKeys.EDIT_ARGUMENT.id(), 
                        ActivityUtil.getExtraDataForIds(itemsIdsArray), 0);
            }
        }

        if (! debateBean.isSubscribed()) {
            debateBean.subscribe(null);
        }
        hideForms();

    }
    
    public Object getSelectedHistoryVersion() {
        if (selectedHistoryVersion != null) {
            return selectedHistoryVersion.getDebateItemPK();
        }
        return null;
    }
    
    public void setSelectedHistoryVersion(Object selectedHistoryVersion) {
        // ignore
    }
    
    public void historyVersionChanged(ValueChangeEvent event) throws SystemException {
        try {
            if (!isAdding()) {
                long id = Long.parseLong(event.getNewValue().toString());
                for (DebateItem oldItems: item.getCompleteHistory()) {
                    if (oldItems.getDebateItemPK().equals(id)) {
                        if (! oldItems.getDebateItemPK().equals(selectedHistoryVersion.getDebateItemPK())) {
                            selectedHistoryVersion = oldItems;
                            setReferences(selectedHistoryVersion.getReferences());
                            setEditedItem(selectedHistoryVersion);
                            // FIXME not otimal
                        }
                    }
                }
            }
            
        } catch (NumberFormatException e) {
            // ignore
        }
        
        
    }
    
    private void setEditedItem(DebateItem item) {
        content = item.getDebateDetail();
        title = item.getDebateSummary();
    }

    public String getReferenceUrl() {
        if (referenceUrl == null || referenceUrl.trim().equals("")) {
            return "http://";
        }
        return referenceUrl;
    }
    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }
    public String getReferenceText() {
        return referenceText;
    }
    public void setReferenceText(String referenceText) {
        this.referenceText = referenceText;
    }
    public void setSelectedHistoryVersion(DebateItem selectedHistoryVersion) {
        this.selectedHistoryVersion = selectedHistoryVersion;
    }
    
    public void addReference(ActionEvent event) throws SystemException {
        DebateItemReference reference = DebateItemReferenceLocalServiceUtil.createNew(referenceText, referenceUrl);
        
        references.add(reference);
        wrappedReferences.add(new DebateItemReferenceWrapper(reference, this));

        // add reference
        item = item.update(title, content, references, Helper.getLiferayUser().getUserId(), weight == null ? 0 : weight);
        
        
        referenceUrl = null;
        referenceText = null;
    }
    
    public List<DebateItemReferenceWrapper> getWrappedReferences() {
        return wrappedReferences;
    }
    
    public void setReferences(List<DebateItemReference> references) {
        this.references = new ArrayList<DebateItemReference>(references);
        this.wrappedReferences = new ArrayList<DebateItemReferenceWrapper>();
        for (DebateItemReference reference: references) {
            wrappedReferences.add(new DebateItemReferenceWrapper(reference, this));
        }
    }
    
    private void hideForms() {
        editing = false;
        adding = false;
        title = "";
        content = "";
        referenceUrl = null;
        referenceText = null;
        //item = null;
        weight = 0L;
        selectedHistoryVersion = null;
    }
    public void removeReference(DebateItemReferenceWrapper debateItemReferenceWrapper) {
        this.references.remove(debateItemReferenceWrapper.getReference());
        wrappedReferences.remove(debateItemReferenceWrapper);
        
    }
    
    public void deleteSelectedItem(ActionEvent event) throws SystemException, RenderingException, PortalException {
        if (! permissions.getCanAdmin()) {
            return;
        }
        DebateItem item = debateBean.getCurrentItem().getItem();
        Long[] itemsIdsArray = DebatesUtil.getIdsOnDebatePath(item);
        if (item != null) {
            /// check permissions
            item.delete(Helper.getLiferayUser().getUserId());
            debateBean.debateItemRemoved(item);
        }
        
        if (type.equals(DebateItemType.POSITION)) {
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), 
                    Debate.class.getName(), item.getDebateId(), DebateActivityKeys.REMOVE_POSITION.id(), 
                    ActivityUtil.getExtraDataForIds(itemsIdsArray), 0);
            
        }
        else if (type.equals(DebateItemType.ARGUMENT_CON) || type.equals(DebateItemType.ARGUMENT_PRO)) {
            SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), 
                    Debate.class.getName(), item.getDebateId(), DebateActivityKeys.REMOVE_ARGUMENT.id(), 
                    ActivityUtil.getExtraDataForIds(itemsIdsArray), 0);
        }
    }
    public DebatesPermissionsBean getPermissions() {
        return permissions;
    }
    public void setPermissions(DebatesPermissionsBean permissions) {
        this.permissions = permissions;
    }
    
    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getWeight() {
        if (selectedHistoryVersion != null && editing) {
            return selectedHistoryVersion.getWeight() == null ? 0 : selectedHistoryVersion.getWeight();
        }
        return weight == null ? 0 : weight;       
    }
    
    public boolean getShowWeightField() {
        if (item != null) {
            return ! item.getDebatePostType().equals(DebateItemType.QUESTION.toString());
        }
        return false;
    }
    
    public List<DebateItem> getItemVersions() {
        return item.getCompleteHistory();
    }
    
    
    public DebateItemType getDebatePostType() {
        return type;
    }
    
    public void changeVersion(ActionEvent event) throws SystemException {
        Long version = (Long) event.getComponent().getAttributes().get("version");
        for (DebateItem oldItems: item.getCompleteHistory()) {
            
            if (oldItems.getDebateItemPK().equals(version)) {
                if (! oldItems.getDebateItemPK().equals(selectedHistoryVersion.getDebateItemPK())) {
                    selectedHistoryVersion = oldItems;
                    setReferences(selectedHistoryVersion.getReferences());
                    setEditedItem(selectedHistoryVersion);
                }
            }
        }
    }
    public void setDebateBean(DebateBean debateBean) {
        this.debateBean = debateBean;
    }
    
    public User getHistoryItemAuthor() throws PortalException, SystemException {
        return selectedHistoryVersion.getAuthor();
    }
    
    public Date getHistoryItemDate() {
        return selectedHistoryVersion.getUpdated();
    }
    
    public void showAllVersions(ActionEvent e) {
        rowsToShow = item.getCompleteHistory().size();
        showingAllVersions = true;
    }
    
    public int getRowsToShow() {
        return rowsToShow;
    }
    
    public boolean isShowingAllVersions() {
        return showingAllVersions;
    }
    
    public int getVersionsAvailable() {
        return versionsAvailable;
    }
    
    public Long getDebateItemPK() {
        if (selectedHistoryVersion != null) {
            return selectedHistoryVersion.getDebateItemPK();
        }
        return -1L;
    }

}