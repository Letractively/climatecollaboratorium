package com.ext.portlet.plans;

import java.util.List;
import java.util.Random;

import org.climatecollaboratorium.test.BaseCollabTest;

import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class PlanItemTest extends BaseCollabTest {
    public Random rand = new Random();
    
    
    
    
    // simple creation test that checks if db was set up correctly 
    public void testCreation() throws SystemException, PortalException {
        // plans before
        List<PlanItem> plansBefore = PlanItemLocalServiceUtil.getPlans();
        
        PlanItem planItemFromDb = PlanItemLocalServiceUtil.createPlan(1L);
        
        
        assertEquals(new Long(0), planItemFromDb.getVersion());
        assertEquals(new Long(1), planItemFromDb.getUpdateAuthorId());
        assertEquals("", planItemFromDb.getDescription());

        List<PlanItem> plansAfter = PlanItemLocalServiceUtil.getPlans();
        
        assertEquals(plansBefore.size() + 1, plansAfter.size());
        
        boolean itemFound = false;
        for (PlanItem item: plansAfter) {
            if (item.equals(planItemFromDb)) {
                itemFound = true;
            }
        }
        assertTrue(itemFound);
        // remove plan
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(planItemFromDb.getPlanId());
        List<PlanItem> plansAfterRemoval = PlanItemLocalServiceUtil.getPlans();
        assertEquals(plansBefore, plansAfterRemoval);
        
        
        
    }
    
    // test for description updating
    public void testSetDescription() throws SystemException {
        PlanItem planItemFromDb = PlanItemLocalServiceUtil.createPlan(1L);
        Long versionBefore = planItemFromDb.getVersion();
        String descriptionBefore = planItemFromDb.getDescription();
        String newDescription = String.valueOf(rand.nextLong());
        List<PlanItem> plansBefore = PlanItemLocalServiceUtil.getPlans();
        
        
        List<PlanDescription> oldDescriptions = planItemFromDb.getPlanDescriptions();
        
        planItemFromDb.setDescription(newDescription);
        List<PlanDescription> newDescriptions = planItemFromDb.getPlanDescriptions();
        
        assertEquals(new Long(versionBefore+1), planItemFromDb.getVersion());
        assertEquals(newDescription, planItemFromDb.getDescription());
        
        assertEquals(oldDescriptions.size()+1, newDescriptions.size());
        assertEquals(new Long(oldDescriptions.get(0).getVersion()+1), newDescriptions.get(0).getVersion());
        assertEquals(newDescription, newDescriptions.get(0).getDescription());
        assertEquals(planItemFromDb.getVersion(), newDescriptions.get(0).getPlanVersion());
        assertEquals(UpdateType.DESCRIPTION_UPDATED.name(), planItemFromDb.getUpdateType());
        

        List<PlanItem> plansAfter = PlanItemLocalServiceUtil.getPlans();
        // check if plans count hasn't changed
        assertEquals(plansBefore.size(), plansAfter.size());
        
        boolean hasCorrectVersion = false;
        for (PlanItem item: plansAfter) {
            if (item.getId().equals(planItemFromDb.getId())) {
                assertEquals(new Long(versionBefore + 1), item.getVersion());
                hasCorrectVersion = true;
            }
        }
        
        assertTrue(hasCorrectVersion);
        // cleanup
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(planItemFromDb.getPlanId());
    }
    
    // test for description updating
    public void testSetName() throws SystemException {
        PlanItem planItemFromDb = PlanItemLocalServiceUtil.createPlan(1L);
        Long versionBefore = planItemFromDb.getVersion();
        String newName = String.valueOf(rand.nextLong());
        
        List<PlanDescription> oldDescriptions = planItemFromDb.getPlanDescriptions();
        
        planItemFromDb.setName(newName);
        List<PlanDescription> newDescriptions = planItemFromDb.getPlanDescriptions();
        
        assertEquals(new Long(versionBefore+1), planItemFromDb.getVersion());
        assertEquals(newName, planItemFromDb.getName());
        
        assertEquals(oldDescriptions.size()+1, newDescriptions.size());
        assertEquals(new Long(oldDescriptions.get(0).getVersion()+1), newDescriptions.get(0).getVersion());
        assertEquals(newName, newDescriptions.get(0).getName());
        assertEquals(planItemFromDb.getVersion(), newDescriptions.get(0).getPlanVersion());
        assertEquals(UpdateType.NAME_UPDATED.name(), planItemFromDb.getUpdateType());
        
        //cleanup 
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(planItemFromDb.getPlanId());
    }
}
