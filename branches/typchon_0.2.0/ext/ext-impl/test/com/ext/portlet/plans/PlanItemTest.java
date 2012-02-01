package com.ext.portlet.plans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.climatecollaboratorium.test.BaseCollabTest;

import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanConstants.Columns;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanAttributeFilter;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.service.PlanAttributeFilterLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlansUserSettingsLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;

import edu.emory.mathcs.backport.java.util.Collections;


public class PlanItemTest extends BaseCollabTest {
    private static final Random rand = new Random();
    private static final Long defaultAuthorId = 10144L;
    private static final Long defaultPlanTypeId = 1L;
    private String name;
    private PlanItem plan;
    
    public void setUp() {
        name = String.valueOf(rand.nextLong());/*
        try {
            /*plan = PlanItemLocalServiceUtil.createPlan(defaultPlanTypeId, defaultAuthorId);
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (PortalException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
    }
    
    public void tearDown() {
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(plan.getPlanId());
    }
    
    
    /**
     * simple creation test that checks if db was set up correctly 
     * @throws SystemException
     * @throws PortalException
     */
    public void testCreation() throws SystemException, PortalException {
        // plans before
        List<PlanItem> plansBefore = PlanItemLocalServiceUtil.getPlans();
        PlanItem plan = null;//PlanItemLocalServiceUtil.createPlan(defaultPlanTypeId, defaultAuthorId);
        
        // check if version is properly set
        assertEquals(new Long(0), plan.getVersion());
        // check author
        assertEquals(defaultAuthorId, plan.getAuthorId());
        // check update author
        assertEquals(defaultAuthorId, plan.getUpdateAuthorId());
        // description should be empty
        assertEquals("", plan.getDescription());
        // check name
        assertEquals(name, plan.getName());
        // check plan type
        assertEquals(defaultPlanTypeId, plan.getPlanTypeId());
        assertEquals(defaultPlanTypeId, plan.getPlanType().getPlanTypeId());
        
        // check attributes
        List<PlanAttribute> attributes =  plan.getPlanAttributes();
        Map<String, String> attributeValues = new HashMap<String, String>();
        for (PlanAttribute attr: attributes) {
            attributeValues.put(attr.getAttributeName(), attr.getAttributeValue());
        }
        assertTrue("Plan doesn't have name attribute set", attributeValues.containsKey(Attribute.NAME.name()));
        assertTrue("Plan doesn't have createDate attribute set", attributeValues.containsKey(Attribute.CREATE_DATE.name()));
        assertTrue("Plan doesn't have creator attribute set", attributeValues.containsKey(Attribute.CREATOR.name()));
        
        // name
        assertEquals(plan.getName(), attributeValues.get(Attribute.NAME.name()));
        
        // author
        assertEquals(plan.getCreator(), attributeValues.get(Attribute.CREATOR.name()));
        assertEquals(plan.getAuthor().getScreenName(), attributeValues.get(Attribute.CREATOR.name()));
        
        // create date
        assertEquals(plan.getCreateDate().toString(), attributeValues.get(Attribute.CREATE_DATE.name()));
        assertEquals(plan.getUpdated().toString(), attributeValues.get(Attribute.CREATE_DATE.name()));
        
        // check if only one plan has been created
        List<PlanItem> plansAfter = PlanItemLocalServiceUtil.getPlans();
        assertEquals(plansBefore.size() + 1, plansAfter.size());
        
        // check if newly created plan can be found in list of plans
        boolean itemFound = false;
        for (PlanItem item: plansAfter) {
            if (item.equals(plan)) {
                itemFound = true;
            }
        }
        assertTrue("Newly created plan can't be found in the list of available plans", itemFound);

        // clean up
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(plan.getPlanId());
        
        // check if plan has been removed
        List<PlanItem> plansAfterRemoval = PlanItemLocalServiceUtil.getPlans();
        assertEquals(plansBefore, plansAfterRemoval);    
    }
    
    /**
     * test for description updating, checks if versions are updated properly 
     * @throws SystemException
     */
    public void testSetDescription() throws SystemException, PortalException {
        
        Long versionBefore = plan.getVersion();
        String newDescription = String.valueOf(rand.nextLong());
        
        List<PlanItem> plansBefore = PlanItemLocalServiceUtil.getPlans();
        
        Long changeAuthorId = rand.nextLong();
        
        List<PlanDescription> oldDescriptions = plan.getPlanDescriptions();
        
        plan.setDescription(newDescription, changeAuthorId);
        List<PlanDescription> newDescriptions = plan.getPlanDescriptions();
        
        assertEquals(new Long(versionBefore+1), plan.getVersion());
        assertEquals(newDescription, plan.getDescription());
        
        assertEquals(oldDescriptions.size()+1, newDescriptions.size());
        assertEquals(new Long(oldDescriptions.get(0).getVersion()+1), newDescriptions.get(0).getVersion());
        assertEquals(newDescription, newDescriptions.get(0).getDescription());
        assertEquals(plan.getVersion(), newDescriptions.get(0).getPlanVersion());
        assertEquals(UpdateType.DESCRIPTION_UPDATED.name(), plan.getUpdateType());
        assertEquals(changeAuthorId, newDescriptions.get(0).getUpdateAuthorId());
        

        List<PlanItem> plansAfter = PlanItemLocalServiceUtil.getPlans();
        // check if plans count hasn't changed
        assertEquals(plansBefore.size(), plansAfter.size());
        
        boolean hasCorrectVersion = false;
        for (PlanItem item: plansAfter) {
            if (item.getId().equals(plan.getId())) {
                assertEquals(new Long(versionBefore + 1), item.getVersion());
                hasCorrectVersion = true;
            }
        }
        
        assertTrue(hasCorrectVersion);
        // cleanup
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(plan.getPlanId());
    }
    
    // test for name updating
    public void testSetName() throws SystemException, PortalException {
        Long versionBefore = plan.getVersion();
        String newName = String.valueOf(rand.nextLong());
        Long changeAuthorId = rand.nextLong();
        
        List<PlanDescription> oldDescriptions = plan.getPlanDescriptions();
        
        plan.setName(newName, changeAuthorId);
        List<PlanDescription> newDescriptions = plan.getPlanDescriptions();
        
        assertEquals(new Long(versionBefore+1), plan.getVersion());
        assertEquals(newName, plan.getName());
        
        assertEquals(oldDescriptions.size()+1, newDescriptions.size());
        assertEquals(new Long(oldDescriptions.get(0).getVersion()+1), newDescriptions.get(0).getVersion());
        assertEquals(newName, newDescriptions.get(0).getName());
        assertEquals(plan.getVersion(), newDescriptions.get(0).getPlanVersion());
        assertEquals(UpdateType.NAME_UPDATED.name(), plan.getUpdateType());
        assertEquals(changeAuthorId, newDescriptions.get(0).getUpdateAuthorId());
        
        boolean hasNameAttribute = false;
        for(PlanAttribute attr: plan.getPlanAttributes()) {
            if (Attribute.valueOf(attr.getAttributeName()) == Attribute.NAME) {
                hasNameAttribute = true;
                assertEquals(newName, attr.getAttributeValue());
            }
        }
        assertTrue(hasNameAttribute);
        
        
        //cleanup 
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(plan.getPlanId());
    }
    
    // test for positions updating
    public void testSetPositions() throws SystemException, PortalException {
        Long versionBefore = plan.getVersion();
        Long changeAuthorId = rand.nextLong();
        
        List<Long> positionsIds = Arrays.asList(1L, 2L, 3L, 4L);
        plan.setPositions(positionsIds, changeAuthorId);
        List<PlanPositions> positions = PlanPositionsLocalServiceUtil.getPlanPositionses(0, 1000);
        
        assertEquals(new Long(versionBefore+1), plan.getVersion());
        assertEquals(positionsIds, plan.getPositionsIds());
        
        assertEquals(UpdateType.PLAN_POSITIONS_UPDATED.name(), plan.getUpdateType());
        
        //cleanup 
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(plan.getPlanId());
    }
    
    @SuppressWarnings("unchecked")
    public void testPlanFinder1() throws PortalException, SystemException {
        ThemeDisplay td = new ThemeDisplay();
        User user = UserLocalServiceUtil.getUser(10144L);
        user.setUserId(10L);
        td.setUser(user);
        
        PlanType planType = PlanTypeLocalServiceUtil.getPlanTypes(0, 2).get(0);
        Map requestMap = new HashMap();
        Map sessionMap = new HashMap();
        requestMap.put(WebKeys.THEME_DISPLAY, td);

        List<PlanItem> plansDesc = PlanItemLocalServiceUtil.getPlans(sessionMap, requestMap, planType,(ContestPhase) null, 0, 1000, Columns.NAME.name(), "DESC");
        List<PlanItem> plansAsc = PlanItemLocalServiceUtil.getPlans(sessionMap, requestMap, planType,(ContestPhase) null, 0, 1000, Columns.NAME.name(), "ASC");
        
        assertTrue("There should be more than 1 plan", plansAsc.size() > 0);
        
        System.out.println("Desc: ");
        for (PlanItem plan: plansDesc) {
            System.out.println(plan.getPlanId() + "\t" + plan.getName());
        }
        
        System.out.println("\nAsc: ");
        for (PlanItem plan: plansAsc) {
            System.out.println(plan.getPlanId() + "\t" + plan.getName());
        }
        assertEquals(plansDesc.size(), plansAsc.size());
        
        // copy to allow modifications
        plansDesc = new ArrayList<PlanItem>(plansDesc);
        Collections.reverse(plansDesc);
        assertEquals(plansDesc, plansAsc);
        
        //System.out.println("Plans: " + plans.size());
    }
    
    public void testPlanFinderFiltered() throws PortalException, SystemException {        
        ThemeDisplay td = new ThemeDisplay();
        User user = UserLocalServiceUtil.getUser(10144L);
        user.setUserId(10L);
        td.setUser(user);
        
        PlanType planType = PlanTypeLocalServiceUtil.getPlanTypes(0, 2).get(0);
        Map requestMap = new HashMap();
        Map sessionMap = new HashMap();
        requestMap.put(WebKeys.THEME_DISPLAY, td);
        
        PlansUserSettings plansUserSettings = PlansUserSettingsLocalServiceUtil.getPlanUserSettings(sessionMap, requestMap, planType);
        
        List<PlanItem> plansBefore = PlanItemLocalServiceUtil.getPlans(sessionMap, requestMap, planType, (ContestPhase) null, 0, 1000, Columns.NAME.name(), "ASC");
        assertTrue("Not enough plans to do filters testing, need at least 2 plans", plansBefore.size() > 0);
        
        plansUserSettings.setFilterEnabled(true);
        PlanAttributeFilter filter = plansUserSettings.getAttributeFilter(Attribute.NAME.name());
        if (filter == null) {
            filter = PlanAttributeFilterLocalServiceUtil.createPlanAttributeFilter(null);
            filter.setAttributeName(Attribute.NAME.toString());
            filter.setPlanUserSettingsId(plansUserSettings.getPlanUserSettingsId());
        }
        filter.setStringVal(plansBefore.get(0).getName());
        
        plansUserSettings.addPlanAttributeFilter(filter);
        List<PlanItem> plansAfter = PlanItemLocalServiceUtil.getPlans(sessionMap, requestMap, planType, (ContestPhase) null, 0, 1000, Columns.NAME.name(), "ASC");
        assertEquals("There should be only one plan after applying filters", 1, plansAfter.size());
        
        
    }
    
    
    public static void main(String args[]) {
        System.out.println(String.format("%0$ta-%tb", new Date(), new Date()));
    }
    
}