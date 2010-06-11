/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import mit.excelwrapper.Utils;
import mit.excelwrapper.dao.ExcelWrapperDAO;
import mit.excelwrapper.excel.ExcelModelParser;
import mit.excelwrapper.excel.ExcelModelRunner;
import mit.excelwrapper.exception.ExcelWrapperException;
import mit.excelwrapper.model.ExcelModel;
import mit.excelwrapper.service.DefaultExcelService;
import mit.excelwrapper.service.DefaultMultipartRequestParser;
import mit.excelwrapper.service.DefaultSimulationService;
import mit.excelwrapper.service.ExcelService;
import mit.excelwrapper.service.MultipartRequestParser;
import mit.excelwrapper.service.SimulationService;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.sun.jersey.spi.resource.Singleton;

/**
 * <p>
 * Excel wrapper resource. It is marked as singleton to be more efficient.
 * </p>
 * <p>
 * <b>Thread Safety</b> all public methods are thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@Path("/")
@Singleton
public class ExcelResource {
    /**
     * <p>
     * Logger for this class.
     * </p>
     */
    private static final Logger LOGGER = Logger.getLogger(ExcelResource.class);

    /**
     * <p>
     * The constant value for name field.
     * </p>
     */
    private static final String FIELD_NAME = "name";

    /**
     * <p>
     * The constant value for description field.
     * </p>
     */
    private static final String FIELD_DESCRIPTION = "description";


    private static final String FIELD_WORKSHEET = "worksheet";

    /**
     * <p>
     * Default value for the excel file directory.
     * </p>
     */
    private static final String DEFAULT_EXCEL_FILE_DIRECTORY = "/var/excelserver/";

    /**
     * <p>
     * init parameter name for excel path.
     * </p>
     */
    private static final String INIT_PARAM_EXCEL_PATH = "excel_path";

    /**
     * <p>
     * The dao instance. It is initialized in ctor and never changed.
     * </p>
     */
    private final ExcelWrapperDAO dao;

    /**
     * <p>
     * The simulation service instance. It is initialized in ctor and never changed.
     * </p>
     */
    private final SimulationService simulationService;

    /**
     * <p>
     * The request parser instance. It is initialized in ctor and never changed.
     * </p>
     */
    private final MultipartRequestParser requestParser;

    /**
     * <p>
     * The excel service instance. It is initialized in ctor and never changed.
     * </p>
     */
    private final ExcelService excelService;

    /**
     * <p>
     * The server url prefix.
     * </p>
     */
    private String serverURLPrefix;

    /**
     * <p>
     * servlet context and it will be injected.
     * </p>
     */
    @Context
    private ServletContext servletContext;

    /**
     * <p>
     * The default ctor.
     * </p>
     */
    public ExcelResource() {
        this(new DefaultMultipartRequestParser(), new DefaultSimulationService(), new ExcelWrapperDAO());
    }

    /**
     * <p>
     * The ctor which takes request parser and simulation service instances.
     *
     * @param requestParser the request parser
     * @param simulationService the simulation service
     * @param dao the dao object
     *
     * @throws IllegalArgumentException if any parameter is null
     */
    public ExcelResource(MultipartRequestParser requestParser, SimulationService simulationService,
        ExcelWrapperDAO dao) {
        Utils.checkNull(requestParser, "requestParser");
        Utils.checkNull(simulationService, "simulationService");
        Utils.checkNull(dao, "dao");

        this.dao = dao;
        this.requestParser = requestParser;
        this.simulationService = simulationService;
        this.excelService = new DefaultExcelService();
    }

    /**
     * <p>
     * The resource method to create a simulation.
     * </p>
     *
     * @param request the http request
     * @return the response
     *
     * @throws ExcelWrapperException if any error occurs in creation
     */
    @POST
    @Path("/createsim")
    @Consumes("multipart/*")
    public Response createSimulation(@Context HttpServletRequest request) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("executes createSimulation");
        }
        ExcelModel model = null;
        boolean success = false;
        try {
            // Gets form fields: name and excel file
            Object[] formFields = getFormFields(request);
            String name = (String) formFields[0];
            String description = (String) formFields[1];
            FileItem excelFile = (FileItem) formFields[2];
            int worksheet = (Integer)formFields[3];

            // Parses the excel
            HSSFWorkbook workbook = getWorkbook(excelFile.getInputStream());
            ExcelModelParser parser = new ExcelModelParser(workbook, dao, worksheet);
            model = parser.parse();
            // persist it first
            dao.commit();

            // Saves the file
            saveFile(name, model, workbook);

            // Creates the simulation
            setServerURLPrefix(request);
            String simulationId = simulationService.createExcelModelSimulation(model, name, description,
                serverURLPrefix);
            success = true;
            return Response
                .seeOther(new URI(serverURLPrefix + "/simulation-servlet/rest/simulation/" + simulationId)).build();
        } catch (FileUploadException e) {
            throw handleException(e);
        } catch (IOException e) {
            throw handleException(e);
        } catch (URISyntaxException e) {
            throw handleException(e);
        } catch (Exception e) {
            throw handleException(e);
        } finally {
            if (!success) {
                rollbackModel(model);
            }
        }
    }

    /**
     * <p>
     * The resource method to run the simulation. It uses <code>ExcelService</code> to load the excel file content
     * associated with model. The excel service uses weak reference map to cache the excel content. Please refer to
     * <code>ExcelService</code> for details.
     * </p>
     *
     * @param formFields the form fields
     * @param id the model id
     * @return the result of this run
     *
     * @throws ExcelWrapperException if any error occurs in running the model
     */
    @POST
    @Path("/wrapper/{id}")
    @Produces("text/plain")
    public String runSimulation(MultivaluedMap<String, String> formFields, @PathParam("id") int id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("executes runSimulation with id parameter of " + id);
        }

        // get excel model
        ExcelModel model = dao.findModelById(id);
        if (model == null) {
            throw new ExcelWrapperException("there is no model for id of " + id);
        }

        ExcelModelRunner runner;
        try {
            runner = new ExcelModelRunner(excelService.getWorkbook(model), model, formFields);
            return runner.runModel();
        } catch (IOException e) {
            throw handleException(e);
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    /**
     * <p>
     * clean up function.
     * </p>
     */
    @PreDestroy
    public void destroy() {
        // clean up resource
        simulationService.destroy();
        excelService.destroy();
    }

    /**
     * <p>
     * Gets the form fields.
     * </p>
     *
     * @param request the http request
     * @return the form fields array
     *
     * @throws FileUploadException if any upload error occurs during the process
     */
    private Object[] getFormFields(HttpServletRequest request) throws FileUploadException {
        String name = "";
        String description = "";
        int worksheet = 0;
        FileItem file = null;

        List<? extends FileItem> items = requestParser.parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) {
                if (FIELD_NAME.equals(item.getFieldName())) {
                    name = item.getString();
                }
                if (FIELD_DESCRIPTION.equals(item.getFieldName())) {
                    description = item.getString();
                }
                if (FIELD_WORKSHEET.equals(item.getFieldName())) {
                    worksheet = Integer.parseInt(item.getString());
                }
            } else {
                file = item;
            }
        }

        // name is required field
        if (StringUtils.isBlank(name)) {
            throw new ExcelWrapperException("The name field should be specified.");
        }

        // excel file is required
        if (file == null) {
            throw new ExcelWrapperException("An excel file should be uploaded.");
        }

        return new Object[] {name, description, file, worksheet};
    }

    /**
     * <p>
     * Saves the excel file into file system.
     * </p>
     *
     * @param name the name of the model
     * @param model the <code>ExcelModel</code> object
     * @param workbook the workbook to be worked on
     * @throws IOException if any IO error occurs
     */
    private void saveFile(String name, ExcelModel model, HSSFWorkbook workbook) throws IOException {
        // this should be an unique/useful file name
        String fileName = new StringBuilder().append(getExcelFileDirectory())
            .append(Utils.normalizeStringValue(name)).append("_").append(model.getId()).append(".xls").toString();
        OutputStream xlsFile = new FileOutputStream(fileName);
        try {
            workbook.write(xlsFile);
        } finally {
            if (xlsFile != null) {
                xlsFile.close();
            }
        }

        // update the model property
        model.setPath(fileName);
        dao.commit();
    }

    /**
     * <p>
     * Gets excel file directory. It gets it from certain context parameter.
     * </p>
     *
     * @return the directory value
     */
    private Object getExcelFileDirectory() {
        String configuredDir = servletContext.getInitParameter(INIT_PARAM_EXCEL_PATH);
        return configuredDir == null ? DEFAULT_EXCEL_FILE_DIRECTORY : configuredDir;
    }

    /**
     * <p>
     * sets server URL prefix.
     * </p>
     *
     * @param request the http request
     */
    private void setServerURLPrefix(HttpServletRequest request) {
        if (serverURLPrefix == null) {
            serverURLPrefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        }
    }

    /**
     * <p>
     * Gets the workbook.
     * </p>
     *
     * @param stream the stream
     * @return the workbook
     *
     * @throws IOException if any IO error occurs
     */
    private HSSFWorkbook getWorkbook(InputStream stream) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(stream));
        return workbook;
    }

    /**
     * <p>
     * Rolls back the model.
     * </p>
     *
     * @param model the excel model
     */
    private void rollbackModel(ExcelModel model) {
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
     * handles the exception.
     * </p>
     *
     * @param <T> the exception type
     * @param exception the exception
     * @return the exception
     */
    private <T extends Exception> ExcelWrapperException handleException(T exception) {
        LOGGER.error("Error occurred during the service : " + exception.getMessage(), exception);
        if (exception instanceof ExcelWrapperException) {
            throw (ExcelWrapperException) exception;
        } else {
            return new ExcelWrapperException(exception.getMessage());
        }
    }
}
