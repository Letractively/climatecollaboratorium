package org.climatecollaboratorium.debatebrowser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.climatecollaboratorium.debatebrowser.utils.Helper;
import org.compass.core.util.backport.java.util.Collections;

import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.model.DebateCategoryMap;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateCategoryMapLocalServiceUtil;
import com.liferay.portal.SystemException;


public class DebateBrowserPreferences {
    
    private final static String VISIBLE_CATEGORIES_PREF = "VISIBLE_CATEGORIES";
    private final static String[] DEFAULT_VISIBLE_CATEGORIES = {};
    private final static String DEBATES_SHOWN_ON_INDEX = "DEBATES_ON_INDEX_PREF";
    private final static String[] DEFAULT_DEBATES_SHOWN_ON_INDEX = {};
    private final static int DEFAULT_DEBATES_ON_INDEX = 5;
    
    
    
    private List<DebateCategoryEditItem> debateCategoryEditItems = new ArrayList<DebateCategoryEditItem>();
    private Long[] visibleCategories;
    private String categoriesOrderString;
    private Map<Long, Integer> debatesOnIndex = new HashMap<Long, Integer>();
    
    public DebateBrowserPreferences() throws SystemException {
        
        String[] debatesOnIndexStr = Helper.getPortletPrefs().getValues(DEBATES_SHOWN_ON_INDEX, DEFAULT_DEBATES_SHOWN_ON_INDEX);
        for (String str: debatesOnIndexStr) {
            if (str.length() > 0) {
                try {
                    String[] items = str.split("\\|");
                    Long debateId = Long.parseLong(items[0]);
                    Integer debatesCount = Integer.parseInt(items[1]);
                    
                    debatesOnIndex.put(debateId, debatesCount);
                }
                catch (Exception e) {
                    // ignore
                }
            }
        }
        
        String[] vCat = Helper.getPortletPrefs().getValues(VISIBLE_CATEGORIES_PREF, DEFAULT_VISIBLE_CATEGORIES);
        Set<Long> visibleCat = new HashSet<Long>();
        visibleCategories = new Long[vCat.length];
        for (int i=0; i < vCat.length; i++) {
            visibleCategories[i] = Long.parseLong(vCat[i]);
            visibleCat.add(visibleCategories[i]);
        }
        
        for (DebateCategory debateCategory: DebateCategoryLocalServiceUtil.getDebateCategories(0, Integer.MAX_VALUE)) {
            Helper.getPortletPrefs().getValues(VISIBLE_CATEGORIES_PREF, DEFAULT_VISIBLE_CATEGORIES);
            
            debateCategoryEditItems.add(
                    new DebateCategoryEditItem(debateCategory, 
                            visibleCat.contains(debateCategory.getDebateCategoryPK()), 
                            getDebatesOnIndex(debateCategory.getDebateCategoryPK())));
            
        }
        
        
        updateCategoriesOrder();
    }
    
    public Long[] getVisibleCategoriesIds() {
        return visibleCategories;
    }
    
    public int getDebatesOnIndex(Long debateCategoryId) {
        Integer count = debatesOnIndex.get(debateCategoryId);
        return count == null ? DEFAULT_DEBATES_ON_INDEX : count;
    }
    
    public void update(ActionEvent e) throws ReadOnlyException, ValidatorException, IOException {
        FacesMessage fm = new FacesMessage();
        
        ArrayList<String> vCat = new ArrayList<String>();
        ArrayList<Long> vCatIds = new ArrayList<Long>();
        for (DebateCategoryEditItem item: debateCategoryEditItems) {
            if (item.isVisible()) {
                vCat.add(item.getDebateCategory().getDebateCategoryPK().toString());
                vCatIds.add(item.getDebateCategory().getDebateCategoryPK());
            }
        }
        
        PortletPreferences prefs = Helper.getPortletPrefs();
        prefs.setValues(VISIBLE_CATEGORIES_PREF, vCat.toArray(new String[] {}));

        prefs.store();
            
            
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
        
        visibleCategories = vCatIds.toArray(visibleCategories);
        updateCategoriesOrder();
    }
    
    public List<DebateCategoryEditItem> getCategories() {
        return debateCategoryEditItems;
    }

    public void setCategoriesOrderString(String categoriesOrderString) {
        this.categoriesOrderString = categoriesOrderString;
    }

    public String getCategoriesOrderString() {
        return categoriesOrderString;
    }
    
    public void updateOrder(ActionEvent e) throws ReadOnlyException, ValidatorException, IOException {
        // parser ordering string
        String[] categoriesIdsStr = categoriesOrderString.split(",");
        List<Long> categoriesIds = new ArrayList<Long>();
        for (int i = 0; i < categoriesIdsStr.length; i++) {
            if (categoriesIdsStr[i].trim().length() > 0) {
                categoriesIds.add(Long.parseLong(categoriesIdsStr[i]));
            }  
        }
        visibleCategories = categoriesIds.toArray(new Long[] {});
        
        updateCategoriesOrder();
        update(e);
    }
    
    private void updateCategoriesOrder() {
        final Map<Long, Integer> categoryWeights = new HashMap<Long, Integer>();
        int counter = 0;
        for (int i=0; i < visibleCategories.length; i++) {
            categoryWeights.put(visibleCategories[i], counter++);
        }
        
        for (DebateCategoryEditItem item: debateCategoryEditItems) {
            if (categoryWeights.containsKey(item.getDebateCategory().getDebateCategoryPK())) {
                continue;
            }
            else {
                categoryWeights.put(item.getDebateCategory().getDebateCategoryPK(), counter++);
            }
        }
        
        Collections.sort(debateCategoryEditItems, new Comparator<DebateCategoryEditItem>() {

            @Override
            public int compare(DebateCategoryEditItem o1, DebateCategoryEditItem o2) {
                return categoryWeights.get(o1.getDebateCategory().getDebateCategoryPK()) - categoryWeights.get(o2.getDebateCategory().getDebateCategoryPK());
            } 
        });
    }
    
    public void cleanDebateMap(ActionEvent e) throws SystemException {
        Set<String> debateCategoryMapping = new HashSet<String>();
        int count = 0;
        for (DebateCategoryMap map: DebateCategoryMapLocalServiceUtil.getDebateCategoryMaps(0, Integer.MAX_VALUE)) {
            String mapping = map.getDebateCategoryPK() + "|" + map.getDebateId();
            if (debateCategoryMapping.contains(mapping)) {
                // mapping already exists, remove duplicate
                count++;
                DebateCategoryMapLocalServiceUtil.deleteDebateCategoryMap(map);
            }
            else {
                // there is no mapping, add one and leave DebateCategoryMap as is
                debateCategoryMapping.add(mapping);
            }
        }
        FacesMessage fm = new FacesMessage();
        fm.setSummary(count + "duplicates removed");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
    }
    
    public void updateDebatesShownOnIndex(ActionEvent e) throws ReadOnlyException, ValidatorException, IOException {
        List<String> settings = new ArrayList<String>();
        
        for (DebateCategoryEditItem item: debateCategoryEditItems) {
            if (item.getDebatesOnIndex() != DEFAULT_DEBATES_ON_INDEX) {
                settings.add(item.getDebateCategory().getDebateCategoryPK() + "|" + item.getDebatesOnIndex());
                debatesOnIndex.put(item.getDebateCategory().getDebateCategoryPK(), item.getDebatesOnIndex());
            }
        }
        
        PortletPreferences prefs = Helper.getPortletPrefs();
        prefs.setValues(DEBATES_SHOWN_ON_INDEX, settings.toArray(new String[] {}));

        prefs.store();
            
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);
    }

}
