/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate.resource;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import junit.framework.Assert;
import mit.simulation.climate.dao.ScenarioDAO;
import mit.simulation.climate.dao.SimulationDAO;
import mit.simulation.climate.exception.SimulationException;
import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.persistence.CompositeServerSimulation;
import mit.simulation.climate.model.persistence.ResponseWrapper;
import mit.simulation.climate.model.persistence.ServerRepository;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.access.DataContext;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * <p>
 * The test class for Service APIs. They are based on testing for <code>SimulationResource</code>.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class SimulationResourceTest {
    /**
     * <p>
     * Logger for this class.
     * </p>
     */
    private static final Logger LOGGER = Logger.getLogger(SimulationResourceTest.class);

    /**
     * <p>
     * test_files directory.
     * </p>
     */
    private static final String TEST_FILES_DIR = "test_files/";

    /**
     * <p>
     * It is a preset simulation running as excel wrapper service.
     * </p>
     */
    private static final long SIMULATION_ID_STERN = 588;

    /**
     * <p>
     * the metadata id for stern simulation. It is String profile.
     * </p>
     */
    private static final long META_DATA_ID_TEMPRATURE_CHANGE_OUT_PUT = 2889;

    /**
     * <p>
     * It is a long name and it will be too long for name field.
     * </p>
     */
    private static final String LONG_NAME = StringUtils.leftPad("long name", 1000);

    /**
     * <p>
     * Test repository.
     * </p>
     */
    private TestServerRepository repository;

    /**
     * <p>
     * The request parser service to be used in the tests.
     * </p>
     */
    private MockMultipartRequestParser requestParser;

    /**
     * <p>
     * The resource instance to be tested.
     * </p>
     */
    private SimulationResource resource;

    /**
     * <p>
     * Test http request.
     * </p>
     */
    private MockHttpServletRequest request;

    /**
     * <p>
     * Set up function.
     * </p>
     *
     * @throws Exception throw to JUnit
     */
    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
        request.setServerName("localhost");
        request.setScheme("http");
        request.setServerPort(8080);

        repository = new TestServerRepository();
        requestParser = new MockMultipartRequestParser();
        resource = new SimulationResource(repository, requestParser);

        // set a data context
        BaseContext.bindThreadObjectContext(DataContext.createDataContext());

        // set new repository for ServerRepository so we could control rollback of new objects
        setField(ServerRepository.class, null, "instance", repository);
    }

    /**
     * <p>
     * Tear down function.
     * </p>
     */
    @After
    public void tearDown() {
        repository.stopCleaner();
        repository.removeCreatedObject();

        resource = null;
        repository = null;
    }

    /**
     * <p>
     * Accuracy test for <code>getSimulations()</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetSimulations_Accuracy() {
        Response response = resource.getSimulations();
        List<Simulation> simulations = ((JAXBElement<ResponseWrapper>) response.getEntity()).getValue().sims;
        LOGGER.info("total number of public simulations : " + simulations.size());
        Assert.assertTrue(simulations.size() > 0);
        for (Simulation sim : simulations) {
            if (sim.getId() == 583) {
                verifySimulation(sim, "IGSM", "http://localhost:8080/excel_wrapper-servlet/rest/wrapper/961");
            }
            if (sim.getId() == 588) {
                verifySimulation(sim, "STERN", "http://localhost:8080/excel_wrapper-servlet/rest/wrapper/966");
            }
            if (sim.getId() == 621) {
                verifySimulation(sim, "SeaLevel", "http://localhost:8080/excel_wrapper-servlet/rest/wrapper/1001");
            }
        }
    }

    /**
     * <p>
     * Verifies simulation.
     * </p>
     *
     * @param sim the simulation
     * @param name the name
     * @param url the url
     */
    private void verifySimulation(Simulation sim, String name, String url) {
        Assert.assertEquals(name, sim.getName());
        Assert.assertEquals(url, sim.getURL().toString());
    }

    /**
     * <p>
     * Failure test for <code>getSimulations()</code>.
     * </p>
     * <p>
     * Triggers mock error. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testGetSimulations_Failure1() throws Exception {
        try {
            repository.setMockError(true);
            resource.getSimulations();
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>createScenario(HttpServletRequest,String,String,String,String,String)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testCreateScenario_Accuracy() {
        Response response = resource.createScenario(request, SIMULATION_ID_STERN + "", null, "test description",
            "the name", "PUBLIC");
        Assert.assertNotNull(response);
    }

    /**
     * <p>
     * Failure test for <code>createScenario(HttpServletRequest,String,String,String,String,String)</code>.
     * </p>
     * <p>
     * Passes in invalid value. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testCreateScenario_Failure1() throws Exception {
        try {
            // give a name which is too long
            resource.createScenario(request, SIMULATION_ID_STERN + "", null, "test description", LONG_NAME, "PUBLIC");
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>runSimulation(HttpServletRequest,String,String,MultivaluedMap&lt;,String&gt;)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testRunSimulation_Accuracy() {
        MultivaluedMapImpl formparams = new MultivaluedMapImpl();
        formparams.putSingle("Temperature_Change", "2.00");
        Response response = resource.runSimulation(request, SIMULATION_ID_STERN + "", null, formparams);
        Assert.assertNotNull(response);
    }

    /**
     * <p>
     * Failure test for <code>runSimulation(HttpServletRequest,String,String,MultivaluedMap&lt;,String&gt;)</code>.
     * </p>
     * <p>
     * Trigger mock error. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testRunSimulation_Failure1() throws Exception {
        try {
            repository.setMockError(true);

            MultivaluedMapImpl formparams = new MultivaluedMapImpl();
            formparams.putSingle("Temperature_Change", "2.0");
            resource.runSimulation(request, SIMULATION_ID_STERN + "", null, formparams);
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>getScenarios()</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetScenarios_Accuracy() {
        // creates a scenario
        resource.createScenario(request, SIMULATION_ID_STERN + "", null, "test description", "the name", "PUBLIC");
        Response response = resource.getScenarios();
        List<Scenario> scenarios = ((JAXBElement<ResponseWrapper>) response.getEntity()).getValue().scenarios;
        LOGGER.info("total number of public scenarios : " + scenarios.size());
        Assert.assertTrue(scenarios.size() > 0);
    }

    /**
     * <p>
     * Failure test for <code>getScenarios()</code>.
     * </p>
     * <p>
     * Passes in invalid value. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    public void testGetScenarios_Failure1() throws Exception {
        try {
            repository.setMockError(true);
            resource.getScenarios();
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>getSimulationOrScenarioById(String,String)</code>.
     * </p>
     * <p>
     * Gets a simulation. No exception should be thrown.
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetSimulationOrScenarioById_Accuracy1() {
        Response response = resource.getSimulationOrScenarioById(SIMULATION_ID_STERN + "", "simulation");
        List<Simulation> simulations = ((JAXBElement<ResponseWrapper>) response.getEntity()).getValue().sims;
        Assert.assertEquals(1, simulations.size());
        Simulation simulation = simulations.get(0);
        Assert.assertEquals(SIMULATION_ID_STERN, simulation.getId().longValue());
    }

    /**
     * <p>
     * Accuracy test for <code>getSimulationOrScenarioById(String,String)</code>.
     * </p>
     * <p>
     * Gets a scenario. No exception should be thrown.
     * </p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetSimulationOrScenarioById_Accuracy2() {
        ScenarioDAO scenario = createScenario();
        Response response = resource.getSimulationOrScenarioById(scenario.getId() + "", "scenario");
        List<Scenario> scenarios = ((JAXBElement<ResponseWrapper>) response.getEntity()).getValue().scenarios;
        Assert.assertEquals(1, scenarios.size());
        Scenario scenarioGet = scenarios.get(0);
        Assert.assertEquals(scenario.getName(), scenarioGet.getName());
    }

    /**
     * <p>
     * Creates a scenario.
     * </p>
     *
     * @return the scenario created
     */
    private ScenarioDAO createScenario() {
        resource.createScenario(request, SIMULATION_ID_STERN + "", null, "test description", "the name", "PUBLIC");
        return (ScenarioDAO) repository.getCreatedObjects().iterator().next();
    }

    /**
     * <p>
     * Failure test for <code>getSimulationOrScenarioById(String,String)</code>.
     * </p>
     * <p>
     * Triggers the mock error. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testGetSimulationOrScenarioById_Failure1() throws Exception {
        try {
            repository.setMockError(true);
            resource.getSimulationOrScenarioById(SIMULATION_ID_STERN + "", "simulation");
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>editScenario(HttpServletRequest,String,String,String,String,String)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testEditScenario_Accuracy() {
        ScenarioDAO scenario = createScenario();
        resource.editScenario(request, scenario.getId() + "", "new name", "new user", "new description",
            EntityState.TEMPORARY + "");
        // verify all values
        Scenario scenarioGet = repository.findScenario(scenario.getId() + "");
        Assert.assertEquals("new name", scenarioGet.getName());
        Assert.assertEquals("new user", scenarioGet.getAuthor());
        Assert.assertEquals("new description", scenarioGet.getDescription());
        Assert.assertTrue(EntityState.TEMPORARY == scenarioGet.getState());
    }

    /**
     * <p>
     * Failure test for <code>editScenario(HttpServletRequest,String,String,String,String,String)</code>.
     * </p>
     * <p>
     * Triggers the mock error. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testEditScenario_Failure1() throws Exception {
        try {
            ScenarioDAO scenario = createScenario();
            repository.setMockError(true);
            resource.editScenario(request, scenario.getId() + "", "new name", "new user", "new description",
                EntityState.TEMPORARY + "");
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>addDataToScenario(HttpServletRequest,String,String,String,String)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testAddDataToScenario_Accuracy() {
        // stern simulation 588, metadata 2889. output
        ScenarioDAO scenario = createScenario();
        int previous = scenario.getScenarioOutputToVars().size();
        resource.addDataToScenario(request, scenario.getId() + "", META_DATA_ID_TEMPRATURE_CHANGE_OUT_PUT + "",
            "test user", "[[temp change data]]");
        int now = scenario.getScenarioOutputToVars().size();
        Assert.assertEquals(previous + 1, now);
    }

    /**
     * <p>
     * Failure test for <code>addDataToScenario(HttpServletRequest,String,String,String,String)</code>.
     * </p>
     * <p>
     * Triggers mock error. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testAddDataToScenario_Failure1() throws Exception {
        try {
            repository.setMockError(true);
            ScenarioDAO scenario = createScenario();
            resource.addDataToScenario(request, scenario.getId() + "", META_DATA_ID_TEMPRATURE_CHANGE_OUT_PUT + "",
                "test user", "[[temp change data]]");
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>createSimulation(String,String,String,String)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testCreateSimulation_Accuracy() {
        SimulationDAO simulation = createSimulation("testCreateSimulation_Accuracy");
        Assert.assertEquals("testCreateSimulation_Accuracy", simulation.getName());
        Assert.assertEquals("description", simulation.getDescription());
        Assert.assertEquals("http://localhost:8080/excel_wrapper-servlet/rest/wrapper/961", simulation.getUrl());
        Assert.assertEquals(EntityState.PUBLIC + "", simulation.getState());
    }

    /**
     * <p>
     * Creates a simulation.
     * </p>
     *
     * @param name the simulation name
     *
     * @return the created simulation
     */
    private SimulationDAO createSimulation(String name) {
        resource.createSimulation("description", name, EntityState.PUBLIC + "",
            "http://localhost:8080/excel_wrapper-servlet/rest/wrapper/961");
        // get created simulation
        return (SimulationDAO) repository.getCreatedObjects().iterator().next();
    }

    /**
     * <p>
     * Failure test for <code>createSimulation(String,String,String,String)</code>.
     * </p>
     * <p>
     * Passes in invalid url value. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testCreateSimulation_Failure1() throws Exception {
        try {
            resource.createSimulation("description", "name", EntityState.PUBLIC + "", "INVALID URL");
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>addMetadataToSimulation(String,String,String,
     * String,String,String,String,String,String,String,String,String,String,String,String)</code> .
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testAddMetadataToSimulation_Accuracy() {
        SimulationDAO simulation = createSimulation("testAddMetadataToSimulation_Accuracy");
        int previous = simulation.getInputs().size();
        resource.addMetadataToSimulation(simulation.getId() + "", "input", "FREE", "SCALAR", "INTEGER",
            "an_input_one", "desc", "an input one", "", "x", "0", "100", "category", "40", "");
        Simulation updatedSimulation = repository.findSimulation(simulation.getId() + "");
        int now = updatedSimulation.getInputs().size();
        Assert.assertEquals(previous + 1, now);
    }

    /**
     * <p>
     * Failure test for <code>addMetadataToSimulation(String,String,String,String,
     * String,String,String,String,String,String,String,String,String,String,String)</code> .
     * </p>
     * <p>
     * Passes in invalid varType value. no metadata should be inserted.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testAddMetadataToSimulation_Failure1() throws Exception {
        try {
            SimulationDAO simulation = createSimulation("testAddMetadataToSimulation_Failure1");
            resource.addMetadataToSimulation(simulation.getId() + "", "input", "INVALID VARTYPE", "SCALAR",
                "INTEGER", "an_input_one", "desc", "an input one", "", "x", "0", "100", "category", "40", "");
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>createCompositeSimulation(HttpServletRequest)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testCreateCompositeSimulation_Accuracy() {
        requestParser.setFileItems(Arrays.asList(new MockFileItem("name", true,
            "testCreateCompositeSimulation_Accuracy", null), new MockFileItem("description", true,
            "Some Description", null), new MockFileItem("state", true, "PUBLIC", null), new MockFileItem(
            "composite descriptor file", false, null, TEST_FILES_DIR + "composite.xml")));
        resource.createCompositeSimulation(request);
        // get created one
        SimulationDAO dao = getCreatedSimulation();
        Simulation simulation = repository.findSimulation(dao.getId() + "");
        Assert.assertTrue(simulation instanceof CompositeServerSimulation);
    }

    /**
     * <p>
     * Gets created simulation.
     * </p>
     *
     * @return the created simulation
     */
    private SimulationDAO getCreatedSimulation() {
        for (CayenneDataObject object : repository.getCreatedObjects()) {
            if (object instanceof SimulationDAO) {
                return (SimulationDAO) object;
            }
        }

        return null;
    }

    /**
     * <p>
     * Failure test for <code>createCompositeSimulation(HttpServletRequest)</code>.
     * </p>
     * <p>
     * Passes in too long name value. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testCreateCompositeSimulation_Failure1() throws Exception {
        try {
            requestParser.setFileItems(Arrays.asList(new MockFileItem("name", true, LONG_NAME, null),
                new MockFileItem("description", true, "Some Description", null), new MockFileItem("state", true,
                    "PUBLIC", null), new MockFileItem("composite descriptor file", false, null, TEST_FILES_DIR
                    + "composite.xml")));
            resource.createCompositeSimulation(request);
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>editSimulation(HttpServletRequest,String,String,String,String,String)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testEditSimulation_Accuracy() {
        SimulationDAO simulation = createSimulation("testEditSimulation_Accuracy");
        String newUrl = "http://localhost:8080/excel_wrapper-servlet/rest/wrapper/1001";
        resource.editSimulation(request, simulation.getId() + "", "new name", "new description", EntityState.PUBLIC
            + "", newUrl,null);
        Simulation updatedSimulation = repository.findSimulation(simulation.getId() + "");
        Assert.assertEquals("new name", updatedSimulation.getName());
        Assert.assertEquals("new description", updatedSimulation.getDescription());
        Assert.assertEquals(EntityState.PUBLIC, updatedSimulation.getState());
    }

    /**
     * <p>
     * Failure test for <code>editSimulation(HttpServletRequest,String,String,String,String,String)</code>.
     * </p>
     * <p>
     * Passes in too long name value. <code>SimulationException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testEditSimulation_Failure1() throws Exception {
        try {
            SimulationDAO simulation = createSimulation("testEditSimulation_Failure1");
            String newUrl = "http://localhost:8080/excel_wrapper-servlet/rest/wrapper/1001";
            resource.editSimulation(request, simulation.getId() + "", LONG_NAME, "new description",
                EntityState.PUBLIC + "", newUrl,null);
            Assert.fail("SimulationException should be thrown.");
        } catch (SimulationException e) {
            // pass
        }
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

    /**
     * <p>
     * Test server repository class.
     * </p>
     *
     * @author TCSDEVELOPER
     * @version 1.0
     */
    private static class TestServerRepository extends ServerRepository {
        /**
         * <p>
         * Indicates if we need to have mock error.
         * </p>
         */
        private boolean mockError = false;

        /**
         * <p>
         * Created objects.
         * </p>
         */
        private Set<CayenneDataObject> createdObjects = new HashSet<CayenneDataObject>();

        /**
         * <p>
         * Ctor.
         * </p>
         */
        public TestServerRepository() {

        }

        /**
         * <p>
         * Sets mock error field.
         * </p>
         *
         * @param mockError mock error
         */
        public void setMockError(boolean mockError) {
            this.mockError = mockError;
        }

        /**
         * <p>
         * Gets created objects.
         * <p>
         *
         * @return the objects
         */
        public Set<CayenneDataObject> getCreatedObjects() {
            return createdObjects;
        }

        /**
         * <p>
         * Gets all public simulations.
         * </p>
         *
         * @return all public simulations
         *
         * @throws SimulationException if mock error
         */
        @Override
        public Collection<Simulation> getAllPublicSimulations() {
            raiseMockErrorIfNecessary();
            return super.getAllPublicSimulations();
        }

        /**
         * <p>
         * Gets all public scenarios.
         * </p>
         *
         * @return all public scenarios
         */
        @Override
        public Collection<Scenario> getAllPublicScenarios() {
            raiseMockErrorIfNecessary();
            return super.getAllPublicScenarios();
        }

        /**
         * <p>
         * Creates the dao object.
         * <p>
         *
         * @param <T> the class type
         * @param cls the class
         * @return the dao object
         */
        @Override
        public <T> T createDAO(Class<T> cls) {
            raiseMockErrorIfNecessary();
            T result = super.createDAO(cls);
            LOGGER.info("created dao :" + result.getClass().getSimpleName());
            createdObjects.add((CayenneDataObject) result);
            return result;
        }

        /**
         * <p>
         * Gets dao object by the id.
         * </p>
         *
         * @param <S> the dao type
         * @param cls the class
         * @param id the id. long type.
         * @return the dao object
         */
        @Override
        public <S> S findDAO(Class<S> cls, Long id) {
            raiseMockErrorIfNecessary();
            return super.findDAO(cls, id);
        }

        /**
         * <p>
         * remove/clean created object.
         * </p>
         */
        public void removeCreatedObject() {
            for (CayenneDataObject object : createdObjects) {
                LOGGER.info("remove object :" + object.getClass().getSimpleName());
                this.remove(object);
            }

            this.commit();
        }

        /**
         * <p>
         * Raises mock error if it is enabled.
         * </p>
         *
         * @throws SimulationException if mock error
         */
        private void raiseMockErrorIfNecessary() {
            if (mockError) {
                throw new SimulationException("mock error");
            }
        }
    }
}
