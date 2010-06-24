/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.resource;

import com.sun.jersey.core.util.MultivaluedMapImpl;
import junit.framework.Assert;
import mit.excelwrapper.dao.ExcelWrapperDAO;
import mit.excelwrapper.excel.ExcelConstants;
import mit.excelwrapper.exception.ExcelWrapperException;
import mit.excelwrapper.model.ExcelModel;
import mit.excelwrapper.model.InputParam;
import mit.excelwrapper.model.OutputParam;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.access.DataContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;

import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * <p>
 * The test class for <code>ExcelResource</code>.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class ExcelResourceTest {
    /**
     * <p>
     * Logger for this class.
     * </p>
     */
    private static final Logger LOGGER = Logger.getLogger(ExcelResourceTest.class);

    /**
     * <p>
     * test_files directory.
     * </p>
     */
    private static final String TEST_FILES_DIR = "test_files/";

    /**
     * <p>
     * The resource instance to be tested.
     * </p>
     */
    private ExcelResource resource;

    /**
     * <p>
     * The request parser service to be used in the tests.
     * </p>
     */
    private MockMultipartRequestParser requestParser;

    /**
     * <p>
     * The test dao.
     * </p>
     */
    private TestExcelWrapperDAO dao;

    /**
     * <p>
     * Test http request.
     * </p>
     */
    private MockHttpServletRequest request;

    /**
     * <p>
     * Test servlet context.
     * </p>
     */
    private MockServletContext servletContext;

    /**
     * <p>
     * Set up function.
     * </p>
     *
     * @throws Exception throw to JUnit
     */
    @Before
    public void setUp() throws Exception {
        requestParser = new MockMultipartRequestParser();
        dao = new TestExcelWrapperDAO();
        resource = new ExcelResource(requestParser, new MockSimulationService(), dao);

        request = new MockHttpServletRequest();
        servletContext = new MockServletContext();
        setField(ExcelResource.class, resource, "servletContext", servletContext);

        // set a data context
        BaseContext.bindThreadObjectContext(DataContext.createDataContext());
    }

    /**
     * <p>
     * Tear down function.
     * </p>
     */
    @After
    public void tearDown() {
        rollback();

        requestParser = null;
        resource.destroy();
        resource = null;
    }

    /**
     * <p>
     * Rolls back any change made to db and file system in unit test.
     * </p>
     */
    private void rollback() {
        ExcelModel model = dao.getModel();
        if (model != null) {
            try {
                // remove file
                if (!StringUtils.isBlank(model.getPath())) {
                    FileUtils.deleteQuietly(new File(model.getPath()));
                }

                // delete model
                dao.deleteModel(model);
                dao.commit();
            } catch (Exception e) {
                LOGGER.error("Error when rolling back model insertion : " + e.getMessage(), e);
            }
        }

    }

    /**
     * <p>
     * Failure test for <code>createSimulation(HttpServletRequest)</code>.
     * </p>
     * <p>
     * Mock the request which doesn't have any name. <code>ExcelWrapperException</code> should be thrown.
     * </p>
     */
    @Test
    public void createSimulationFailure1() {
        try {
            resource.createSimulation(request);
            Assert.fail();
        } catch (ExcelWrapperException e) {
            Assert.assertEquals("The name field should be specified.", e.getResponse().getEntity() + "");
        }
    }

    /**
     * <p>
     * Failure test for <code>createSimulation(HttpServletRequest)</code>.
     * </p>
     * <p>
     * Mock the request which has the name but not the file. <code>ExcelWrapperException</code> should be thrown.
     * </p>
     */
    @Test
    public void createSimulationFailure2() {
        try {
            requestParser.setFileItems(Arrays.asList(new MockFileItem("name", true, "Some Name", null)));
            resource.createSimulation(request);
            Assert.fail();
        } catch (ExcelWrapperException e) {
            Assert.assertEquals("An excel file should be uploaded.", e.getResponse().getEntity() + "");
        }
    }

    /**
     * <p>
     * Failure test for <code>createSimulation(HttpServletRequest)</code>.
     * </p>
     * <p>
     * The uploaded file is invalid. <code>ExcelWrapperException</code> should be thrown.
     * </p>
     */
    @Test
    public void createSimulationFailure3() {
        try {
            requestParser.setFileItems(Arrays.asList(new MockFileItem("name", true, "Some Name", null),
                new MockFileItem("excel file", false, null, TEST_FILES_DIR + "bad_xls.txt")));
            resource.createSimulation(request);
            Assert.fail();
        } catch (ExcelWrapperException e) {
            // pass
        }
    }

    /**
     * <p>
     * Accuracy test for <code>createSimulation(HttpServletRequest)</code>.
     * </p>
     * <p>
     * Creates one simulation using STERN.xls. No exception should be thrown.
     * </p>
     */
    @Test
    public void testCreateSimulation_Accuracy1() {
        final int expectedNumOfInputParams = 1;
        final int expectedNumOfOutputParams = 7;
        final int expectedOuputNumRows = 1;
        testCreateSimulation("STERN", "STERN.xls", expectedNumOfInputParams, expectedNumOfOutputParams,
            expectedOuputNumRows);
    }

    /**
     * <p>
     * Accuracy test for <code>createSimulation(HttpServletRequest)</code>.
     * </p>
     * <p>
     * Creates one simulation using SEALEVEL.xls. No exception should be thrown.
     * </p>
     */
    @Test
    public void testCreateSimulation_Accuracy2() {
        final int expectedNumOfInputParams = 1;
        final int expectedNumOfOutputParams = 1;
        final int expectedOuputNumRows = 1;
        testCreateSimulation("SEALEVEL", "SEALEVEL.xls", expectedNumOfInputParams, expectedNumOfOutputParams,
            expectedOuputNumRows);
    }

    /**
     * <p>
     * Tests create simulation function.
     * </p>
     *
     * @param name the name value
     * @param excelFile the excel file. They are in test_files folder.
     * @param numInputParams number of input params
     * @param numOutputParams number of output params
     * @param outputNumRows number of output numRows
     */
    private void testCreateSimulation(String name, String excelFile, int numInputParams, int numOutputParams,
        int outputNumRows) {
        ExcelModel model = createModel(name, excelFile,ExcelConstants.Format.SINGLE_SHEET);
        Assert.assertNotNull(model);
        Assert.assertEquals(numInputParams, model.getInputParams().size());
        Assert.assertEquals(numOutputParams, model.getOutputParams().size());
        if (numOutputParams > 0) {
            Assert.assertEquals(outputNumRows, model.getOutputParams().get(0).getNumRows().intValue());
        }
    }

    /**
     * <p>
     * Creates the model.
     * </p>
     *
     * @param name the name
     * @param excelFile the excel file
     * @return the model created
     */
    private ExcelModel createModel(String name, String excelFile, ExcelConstants.Format format) {
        requestParser.setFileItems(Arrays.asList(new MockFileItem("name", true, name, null), new MockFileItem(
            "excel file", false, null, TEST_FILES_DIR + excelFile),new MockFileItem("format",true,format.name(),null)));
        resource.createSimulation(request);
        return dao.getModel();
    }

    /**
     * <p>
     * Failure test for <code>runSimulation(MultivaluedMap&lt;,String&gt;,int)</code>.
     * </p>
     * <p>
     * Passes in invalid value. <code>ExcelWrapperException</code> should be thrown.
     * </p>
     *
     * @throws Exception to JUnit, indicates an error
     */
    @Test
    public void testRunSimulation_Failure1() throws Exception {
        try {
            runSimulation("SEALEVEL.xls", "not a number",null);
            Assert.fail("ExcelWrapperException should be thrown.");
        } catch (ExcelWrapperException e) {
            Assert.assertTrue((e.getResponse().getEntity() + "").contains("the value should be integer"));
        }
    }

    /**
     * <p>
     * Accuracy test for <code>runSimulation(MultivaluedMap&lt;,String&gt;,int)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testRunSimulation_Accuracy1() {
        runSimulation("SEALEVEL.xls", null,null);
    }

    /**
     * <p>
     * Accuracy test for <code>runSimulation(MultivaluedMap&lt;,String&gt;,int)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testRunSimulation_Accuracy2() {
        runSimulation("STERN.xls", "5",null);
    }

     /**
     * <p>
     * Accuracy test for <code>runSimulation(MultivaluedMap&lt;,String&gt;,int)</code>.
     * </p>
     * <p>
     * Passes in valid value. No exception should be thrown.
     * </p>
     */
    @Test
    public void testRunTwoWorksheetSimulation_Accuracy2() {
        runSimulation("2WorksheetTest.xls", "[4][5][6][7]",ExcelConstants.Format.TWO_SHEET);
    }

    /**
     * <p>
     * Runs the simulation.
     * </p>
     *
     * @param fileName the file used to create the simulation for testing
     * @param paramValue the parameter value
     */
    private void runSimulation(String fileName, String paramValue,ExcelConstants.Format format) {
        ExcelModel model = createModel("EXCEL RUN TEST", fileName,format==null?ExcelConstants.Format.SINGLE_SHEET:format);

        if (paramValue == null) {
            // it is safe to just give number string
            paramValue = "1";
        }

        MultivaluedMap<String, String> formFields = new MultivaluedMapImpl();
        for (InputParam input : model.getInputParams()) {
            formFields.add(input.getInternalName(), paramValue);
        }
        String result = resource.runSimulation(formFields, model.getId());
        LOGGER.info("run result :" + result);
        for (InputParam input : model.getInputParams()) {
            Assert.assertTrue(result.contains("<" + input.getInternalName() + ">"));
        }
        for (OutputParam output : model.getOutputParams()) {
            Assert.assertTrue(result.contains("<" + output.getInternalName() + ">"));
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
     * Test DAO class.
     * </p>
     *
     * @author TCSDEVELOPER
     * @version 1.0
     */
    private static class TestExcelWrapperDAO extends ExcelWrapperDAO {
        /**
         * <p>
         * recent model created.
         * </p>
         */
        private ExcelModel model;

        /**
         * <p>
         * New a model.
         * </p>
         *
         * @return model entity
         */
        @Override
        public ExcelModel newExcelModel() {
            model = super.newExcelModel();
            return model;
        }

        /**
         * <p>
         * Gets most recent model created.
         * </p>
         *
         * @return the model. It could be null
         */
        public ExcelModel getModel() {
            return this.model;
        }
    }
}
