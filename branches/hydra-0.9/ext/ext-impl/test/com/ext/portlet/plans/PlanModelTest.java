/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.plans;

import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanTypeAttribute;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalService;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.Transactional;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;
import org.climatecollaboratorium.test.BaseCollabTest;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 7, 2010
 * Time: 10:07:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class PlanModelTest extends BaseCollabTest {


     private static final Random rand = new Random();
    private static final Long defaultAuthorId = 10144L;
    private static final Long defaultPlanTypeId = 1L;
    private ClientRepository repository;
    private String name;
    private PlanType multiModelPlanType;
    private PlanType singleModelPlanType;
    private PlanItem plan;

    public void setUp() throws Exception {

        List<Simulation> sims = new ArrayList<Simulation>();
        sims.add(new MockSimulation(1L,"a"));
        sims.add(new MockSimulation(2L,"b"));
        sims.add(new MockSimulation(3L,"a"));
        repository = new MockClientRepository(sims);

        multiModelPlanType = new MockPlanType(1L,"a");
        singleModelPlanType = new MockPlanType(2L,"b");

        Map<Long,PlanType> mocked = new HashMap<Long,PlanType>();
        mocked.put(multiModelPlanType.getPlanTypeId(),multiModelPlanType);
        mocked.put(singleModelPlanType.getPlanTypeId(),singleModelPlanType);
        PlanTypeLocalService service = PlanTypeLocalServiceUtil.getService();
        new PlanTypeLocalServiceUtil().setService(new MockPlanTypeLocalService(service,mocked));
        setField(ClientRepository.class, null, "instance", repository);
        name = String.valueOf(rand.nextLong());

    }

    public void createPlan(PlanType type) {
       try {
            plan = new MockPlanItem(PlanItemLocalServiceUtil.createPlan(name, defaultPlanTypeId, defaultAuthorId),type);
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (PortalException e) {
           e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
       }
    }


    public void testSingleModelSelection() {
        createPlan(singleModelPlanType);
        try {
            plan.setModelId(1L,defaultAuthorId);
            assertTrue("Should not be able to set invalid model id",false);
        } catch (PortalException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void testMultipleModelSelection() throws SystemException, PortalException {
        createPlan(multiModelPlanType);
        try {
            plan.setModelId(2L,defaultAuthorId);
            assertTrue("Should not be able to set invalid model id",false);
        } catch (PortalException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SystemException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        plan.setModelId(1L,defaultAuthorId);
        assertEquals(plan.getPlanMeta().getModelId().longValue(),1L);
    }

    public void testModelSelectionHistory() throws SystemException, PortalException {
        createPlan(multiModelPlanType);
        plan.setModelId(1L,defaultAuthorId);
        assertEquals(plan.getPlanMeta().getModelId().longValue(),1L);
        plan.setModelId(3L,defaultAuthorId);
        List<PlanItem> plans = plan.getAllVersions();

        assertEquals("Wrong number of history items",plans.size(),5);
        assertEquals("Wrong update type",plans.get(0).getUpdateType(),UpdateType.CREATED.name());
        assertEquals("Wrong update type",plans.get(1).getUpdateType(),UpdateType.MODEL_UPDATED.name());

        // this would be the intuitive
        // assertEquals("Wrong model id",1L,plans.get(1).getPlanMeta().getModelId().longValue());


        assertEquals("Wrong update type",plans.get(2).getUpdateType(),UpdateType.SCENARIO_UPDATED.name());
        assertEquals("Wrong update type",plans.get(3).getUpdateType(),UpdateType.MODEL_UPDATED.name());

        // this would be the intuitive behavior
        //assertEquals("Wrong model id",3L,plans.get(3).getPlanMeta().getModelId().longValue());

        assertEquals("Wrong update type",plans.get(4).getUpdateType(),UpdateType.SCENARIO_UPDATED.name());


    }



    public void tearDown() {
        PlanItemLocalServiceUtil.removePlanWithEntireHistory(plan.getPlanId());
    }




     /**
     * <p>
     * Get value of given <code>Field</code> of given <code>Object</code>.
     * </p>
     *
     * @param clazz Class to get declared field
     * @param object instance to get field from
     * @param fieldName name of field
     * @param fieldValue field value
     *
     * @throws Exception to JUnit
     */
    @SuppressWarnings("all")
    public static void setField(Class clazz, Object object, String fieldName, Object fieldValue) throws Exception {
        Field f = clazz.getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(object, fieldValue);
    }


    public static class MockPlanTypeLocalService implements PlanTypeLocalService {

        PlanTypeLocalService delegate;
        Map<Long,PlanType> mocked;

        public MockPlanTypeLocalService(PlanTypeLocalService delegate, Map<Long,PlanType> mocked) {
           this.delegate = delegate;
            this.mocked = mocked;
        }

        @Override
        public PlanType addPlanType(PlanType planType) throws SystemException {
            return delegate.addPlanType(planType);
        }

        @Override
        public PlanType createPlanType(Long planTypeId) {
            return delegate.createPlanType(planTypeId);
        }

        @Override
        public void deletePlanType(Long planTypeId) throws SystemException, PortalException {
            delegate.deletePlanType(planTypeId);
        }

        @Override
        public void deletePlanType(PlanType planType) throws SystemException {
            delegate.deletePlanType(planType);
        }

        @Override
        public List<Object> dynamicQuery(DynamicQuery dynamicQuery) throws SystemException {
            return delegate.dynamicQuery(dynamicQuery);
        }

        @Override
        public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start, int end) throws SystemException {
            return delegate.dynamicQuery(dynamicQuery, start, end);
        }

        @Override
        @Transactional
        public PlanType getPlanType(Long planTypeId) throws SystemException, PortalException {
            if (mocked.containsKey(planTypeId)) {
                return mocked.get(planTypeId);
            }
            return delegate.getPlanType(planTypeId);
        }

        @Override
        @Transactional
        public List<PlanType> getPlanTypes(int start, int end) throws SystemException {
            return delegate.getPlanTypes(start, end);
        }

        @Override
        @Transactional
        public int getPlanTypesCount() throws SystemException {
            return delegate.getPlanTypesCount();
        }

        @Override
        public PlanType updatePlanType(PlanType planType) throws SystemException {
            return delegate.updatePlanType(planType);
        }

        @Override
        public PlanType updatePlanType(PlanType planType, boolean merge) throws SystemException {
            return delegate.updatePlanType(planType, merge);
        }

        @Override
        @Transactional
        public PlanType getDefaultPlanType() throws NoSuchPlanTypeException, SystemException {
            return delegate.getDefaultPlanType();
        }

        @Override
        @Transactional
        public List<PlanTypeColumn> getColumnsByPlanTypeId(long planTypeId) throws SystemException {
            return delegate.getColumnsByPlanTypeId(planTypeId);
        }

        @Override
        @Transactional
        public List<PlanTypeAttribute> getAttributesByPlanTypeId(long planTypeId) throws SystemException {
            return delegate.getAttributesByPlanTypeId(planTypeId);
        }




    }
}
