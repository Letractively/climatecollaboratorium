package com.ext.portlet.plans;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;

import junit.extensions.TestSetup;
import junit.framework.TestSuite;

public class PlanAllTests extends TestSuite {
    private static final Random rand = new Random();
    private static final Long[] defaultAuthorIds = { 10144L, 22964L, 10115L, 21112L };
    private static final Long[] defaultPlanTypeId = { 1L, 2L };
    private static final Long[] defaultScenarioIds = { 5159L, 5160L, 5161L, 5162L, 5163L, 5164L, 5165L, 5166L };
    private static List<PlanItem> plansBefore;
    private static Set<Long> plansIdsBefore = new HashSet<Long>();

    public static TestSetup suite() {
        return new TestSetup(new TestSuite(PlanItemTest.class)) {

            protected void setUp() throws Exception {
                System.out.println(" Global setUp ");
                try {
                    plansBefore = PlanItemLocalServiceUtil.getPlans();
                    // create few plans that will be used during testing
                    for (int i = 0; i < 50; i++) {
                        PlanItem plan = PlanItemLocalServiceUtil.createPlan(
                                defaultPlanTypeId[rand.nextInt(defaultPlanTypeId.length)], defaultAuthorIds[rand
                                        .nextInt(defaultAuthorIds.length)]);
                    }

                    for (PlanItem plan : plansBefore) {
                        plansIdsBefore.add(plan.getPlanId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw e;
                }

            }

            protected void tearDown() throws Exception {
                // remove all plans that was created during test but wasn't
                // removed properly
                List<PlanItem> plansAfter = PlanItemLocalServiceUtil.getPlans();
                for (PlanItem plan : plansAfter) {
                    if (plansIdsBefore.contains(plan.getPlanId())) {
                        continue;
                    }
                    //PlanItemLocalServiceUtil.removePlanWithEntireHistory(plan.getPlanId());
                }

            }
        };
    }

}
