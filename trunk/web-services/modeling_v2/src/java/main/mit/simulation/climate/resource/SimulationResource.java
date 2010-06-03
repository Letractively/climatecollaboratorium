/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import mit.simulation.climate.Utils;
import mit.simulation.climate.exception.SimulationException;
import mit.simulation.climate.model.*;
import mit.simulation.climate.model.MetaData.VarContext;
import mit.simulation.climate.model.MetaData.VarType;
import mit.simulation.climate.model.SimulationStepper.SimulationRunner;
import mit.simulation.climate.model.SimulationStepper.Step;
import mit.simulation.climate.model.persistence.CompositeServerSimulation;
import mit.simulation.climate.model.persistence.ResponseWrapper;
import mit.simulation.climate.model.persistence.ServerMetaData;
import mit.simulation.climate.model.persistence.ServerRepository;
import mit.simulation.climate.model.persistence.ServerScenario;
import mit.simulation.climate.model.persistence.ServerSimulation;
import mit.simulation.climate.model.persistence.ServerTuple;
import mit.simulation.climate.model.persistence.ServerVariable;
import mit.simulation.climate.service.DefaultMultipartRequestParser;
import mit.simulation.climate.service.MultipartRequestParser;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.sun.jersey.spi.resource.Singleton;

/**
 * <p>
 * Simulation resource. It provides various service methods to create/manage/run various simulations.
 * </p>
 * <p>
 * Change note for 1.1: The changes are as following:
 * <ul>
 * <li>Remove security related code.</li>
 * <li>Make it singleton to be more efficient.</li>
 * <li>Remove the session listener interface.</li>
 * <li>Remove /simulationstate/{id} endpoint since it is redundant with /editsim</li>
 * <li>createCompositeSimulation adds state form parameter.</li>
 * <li>Remove idhack due to changes from the dao layer.</li>
 * <li>Add exception handling for all methods.</li>
 * <li>Changes made to ensure thread safe/transactional for each method.</li>
 * <li>The resource is made as singleton to be more efficient.</li>
 * </ul>
 * </p>
 * <p>
 * <b>Thread Safety</b> all public methods are thread safe.
 * </p>
 *
 * @author jintrone, TCSDEVELOPER
 * @version 1.1
 * @since 1.0
 */
@Path("/")
@Singleton
@Produces("application/xml")
public class SimulationResource {
    /**
     * <p>
     * Logger for this class.
     * </p>
     */
    private static final Logger LOGGER = Logger.getLogger(SimulationResource.class);

    /**
     * <p>
     * The runner to run the simulation.
     * </p>
     */
    private Runner runner = new Runner();

    /**
     * <p>
     * The request parser instance for multipart request. It is initialized in ctor and never changed.
     * </p>
     */
    private final MultipartRequestParser requestParser;

    /**
     * <p>
     * <code>ServerRepository</code> instance. It is initialized in the ctor.
     * </p>
     */
    private final ServerRepository repo;

    /**
     * <p>
     * The server url prefix.
     * </p>
     */
    private String serverURLPrefix;

    /**
     * <p>
     * Ctor.
     * </p>
     *
     * @param repo the server repository
     * @param requestParser the request parser
     *
     * @throws IllegalArgumentException if repo is null
     */
    public SimulationResource(ServerRepository repo, MultipartRequestParser requestParser) {
        Utils.checkNull(repo, "server repository");
        Utils.checkNull(requestParser, "requestParser");
        this.repo = repo;
        this.requestParser = requestParser;

        // starts the cleaner
        this.repo.startCleaner();
    }

    /**
     * <p>
     * Default ctor.
     * </p>
     */
    public SimulationResource() {
        this(ServerRepository.instance(), new DefaultMultipartRequestParser());
    }

    /**
     * <p>
     * clean up function.
     * </p>
     */
    @PreDestroy
    public void destroy() {
        // clean up resource
        repo.stopCleaner();
    }

    /**
     * <p>
     * Gets all public simulations.
     * <p>
     *
     * @return all simulations
     *
     * @throws SimulationException if any error occurs during the service
     */
    @GET
    @Path("/simulation")
    public Response getSimulations() {
        LOGGER.info("Handle getSimulations");
        try {
            ResponseWrapper wrapper = new ResponseWrapper();
            wrapper.add(repo.getAllPublicSimulations());
            return getPayloadResponse(wrapper);
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    /**
     * <p>
     * Gets all public scenarios.
     * </p>
     *
     * @return all scenarios
     *
     * @throws SimulationException if any error occurs during the service
     */
    @GET
    @Path("/scenario")
    public Response getScenarios() {
        LOGGER.info("Handle getScenario");
        try {
            ResponseWrapper wrapper = new ResponseWrapper();
            wrapper.add(repo.getAllPublicScenarios());
            return Response
                .ok(new JAXBElement<ResponseWrapper>(new QName("payload"), ResponseWrapper.class, wrapper)).build();
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    /**
     * <p>
     * Gets simulation or scenario content.
     * </p>
     *
     * @param id the simulation or scenario id
     * @param type scenario or simulation
     * @return the response
     *
     * @throws SimulationException if any error occurs during the service
     */
    @GET
    @Path("/{type}/{id}")
    public Response getSimulationOrScenarioById(@PathParam("id") String id, @PathParam("type") String type) {
        LOGGER.info("Handle getSimulationOrScenarioById: type " + type + " id " + id);
        try {
            Object payload = null;
            if ("scenario".equals(type)) {
                payload = repo.findScenario(id);
            } else if ("simulation".equals(type)) {
                payload = repo.findSimulation(id);
            }

            if (payload == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            ResponseWrapper wrapper = new ResponseWrapper(Collections.singleton(payload));
            return Response
                .ok(new JAXBElement<ResponseWrapper>(new QName("payload"), ResponseWrapper.class, wrapper)).build();
        } catch (Exception e) {
            throw handleException(e);
        }
    }

    /**
     * <p>
     * Creates the scenario.
     * </p>
     *
     * @param request the servlet request
     * @param id the sim id
     * @param user the user
     * @param information the description
     * @param name the name
     * @param state the state
     *
     * @return the response
     *
     * @throws SimulationException if any error occurs during the service
     */
    @POST
    @Path("/scenario")
    public Response createScenario(@Context HttpServletRequest request, @FormParam("simulation") String id,
        @FormParam("user") String user, @FormParam("description") String information, @FormParam("name") String name,
        @FormParam("state") String state) {
        LOGGER.info("Handle createScenario");
        try {
            Simulation sim = repo.findSimulation(id);
            if (sim == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            ServerScenario scenario = new ServerScenario(sim, user, name, information, Utils
                .getEntityStateFromString(state));
            repo.commit();
            return Response.seeOther(new URI(getResourcePathUrl(request, "/scenario/" + scenario.getId()))).build();
        } catch (Exception e) {
            repo.rollback();
            throw handleException(e);
        }
    }

    /**
     * <p>
     * Gets resource path url.
     * </p>
     *
     * @param request the request
     * @param resourceRelativePath the resource relative path
     * @return the path url
     */
    private String getResourcePathUrl(HttpServletRequest request, String resourceRelativePath) {
        if (serverURLPrefix == null) {
            serverURLPrefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + "/simulation-servlet/rest";
        }

        return serverURLPrefix + resourceRelativePath;
    }

    /**
     * <p>
     * Edits the scenario.
     * </p>
     *
     * @param request the request
     * @param id the id
     * @param name the name value
     * @param user the user value
     * @param description the description value
     * @param action the action string. to be converted to the state
     * @return the response
     *
     * @throws SimulationException if any error occurs during the service
     */
    @POST
    @Path("/scenariostate/{id}")
    public Response editScenario(@Context HttpServletRequest request, @PathParam("id") String id,
        @FormParam("name") String name, @FormParam("user") String user, @FormParam("description") String description,
        @FormParam("state") String action) {
        LOGGER.info("Handle editScenario");
        try {
            Scenario scenario = repo.findScenario(id);
            if (scenario == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            EntityState state = (action == null) ? null : EntityState.valueOf(action);
            if (state == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            scenario.setState(state);

            if (name != null) {
                scenario.setName(name);
            }
            if (user != null) {
                scenario.setAuthor(user);
            }
            if (description != null) {
                scenario.setDescription(description);
            }

            repo.commit();
            return Response.seeOther(new URI(getResourcePathUrl(request, "/scenario/" + scenario.getId()))).build();
        } catch (Exception e) {
            repo.rollback();
            throw handleException(e);
        }
    }

    /**
     * <p>
     * Adds data to the scenario.
     * </p>
     *
     * @param request the request
     * @param id the id
     * @param metaid the meta id
     * @param user the user
     * @param data the data
     * @return the response
     *
     * @throws SimulationException if any error occurs during the service
     */
    @POST
    @Path("/scenario/{id}/{metaid}")
    public Response addDataToScenario(@Context HttpServletRequest request, @PathParam("id") String id,
        @PathParam("metaid") String metaid, @FormParam("user") String user, @FormParam("data") String data) {
        LOGGER.info("Handle editScenario");
        try {
            Scenario scenario = repo.findScenario(id);
            if (scenario == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            MetaData md = repo.findMetaData(metaid);
            if (md == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            Variable v = createVariableFromString(md, data);
            if (scenario.getSimulation().getInputs().contains(md)) {
                scenario.addToInput(v);
            } else {
                scenario.addToOutput(v);
            }

            repo.commit();
            return Response.seeOther(new URI(getResourcePathUrl(request, "/scenario/" + scenario.getId()))).build();
        } catch (Exception e) {
            repo.rollback();
            throw handleException(e);
        }
    }

    /**
     * <p>
     * Creates the simulation.
     * </p>
     *
     * @param information the description
     * @param name the name
     * @param action the state string
     * @param location the location
     * @return the simulation id
     *
     * @throws SimulationException if any error occurs during the service
     */
    @POST
    @Path("/simulation")
    public String createSimulation(@FormParam("description") String information, @FormParam("name") String name,
        @FormParam("state") String action, @FormParam("url") String location) {
        LOGGER.info("Handle editScenario");
        try {
            LOGGER.info("Received location:" + location);
            List<MetaData> empty = new ArrayList<MetaData>();
            EntityState estate = action == null ? null : EntityState.valueOf(action);
            ServerSimulation sim = new ServerSimulation(name, information, new URL(location), empty, empty,
                (estate == null) ? EntityState.PUBLIC : estate);
            LOGGER.info("Sim url is " + sim.getURL());
            repo.commit();
            return sim.getId() + "";
        } catch (Exception e) {
            repo.rollback();
            throw handleException(e);
        }
    }

    /**
     * <p>
     * Creates composite simulation.
     * </p>
     *
     * @param request the request
     * @return the simulation id
     *
     * @throws SimulationException if any error occurs during the service
     */
    @POST
    @Path("/compositesimulation")
    @Consumes("multipart/*")
    public String createCompositeSimulation(@Context HttpServletRequest request) {
        LOGGER.info("Handle createCompositeSimulation");
        InputStream descriptorInput = null;
        CompositeServerSimulation sim = null;
        try {
            Map<String, String> fields = new HashMap<String, String>();
            List<? extends FileItem> items = requestParser.parseRequest(request);
            Iterator<? extends FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem fileItem = iter.next();
                if (fileItem.isFormField()) {
                    fields.put(fileItem.getFieldName(), fileItem.getString());
                } else {
                    descriptorInput = fileItem.getInputStream();
                }
            }

            String descriptor = null;
            if (descriptorInput != null) {
                descriptor = IOUtils.toString(descriptorInput);
            }

            sim = new CompositeServerSimulation(fields.get("name"), fields.get("description"), descriptor, Utils
                .getEntityStateFromString(fields.get("state")));
            repo.commit();
            return sim.getId() + "";
        } catch (Exception e) {
            repo.rollback();
            rollbackSimulationIfAny(sim);
            throw handleException(e);
        } finally {
            IOUtils.closeQuietly(descriptorInput);
        }
    }

    /**
     * <p>
     * Rolls back simulation if any.
     * </p>
     *
     * @param simulation the simulation to be rolled back
     */
    private void rollbackSimulationIfAny(CompositeServerSimulation simulation) {
        if (simulation != null && simulation.getId() != null) {
            try {
                repo.deleteSimulation(simulation.getId());
                repo.commit();
            } catch (Exception e) {
                LOGGER.warn("simulation can not be deleted : " + simulation.getId());
            }
        }
    }

    /**
     * <p>
     * Adds metadata to the simulation.
     * </p>
     *
     * @param id the input
     * @param inputOrOutput "input" or "output"
     * @param varType var type
     * @param varContext var context
     * @param datatype datatype
     * @param intName internal name
     * @param description description
     * @param name the name
     * @param units the units
     * @param label the label
     * @param min the min
     * @param max the max
     * @param categories the categories value
     * @param value the value
     * @param external the external value
     *
     * @throws SimulationException if any error occurs during the service
     */
    @POST
    @Path("/createsim/{id}")
    public void addMetadataToSimulation(@PathParam("id") String id,
        @DefaultValue("input") @FormParam("type") String inputOrOutput,
        @DefaultValue("RANGE") @FormParam("type2") String varType,
        @DefaultValue("SCALAR") @FormParam("varcontext") String varContext,
        @DefaultValue("DOUBLE") @FormParam("datatype") String datatype,
        @DefaultValue("none") @FormParam("intName") String intName,
        @DefaultValue("none") @FormParam("description") String description,
        @DefaultValue("none") @FormParam("name") String name, @DefaultValue("none") @FormParam("units") String units,
        @FormParam("label") String label, @FormParam("min") String min, @FormParam("max") String max,
        @FormParam("categories") String categories, @FormParam("value") String value,
        @FormParam("external") String external) {
        LOGGER.info("Handle createCompositeSimulation");
        try {
            Simulation sim = repo.findSimulation(id);
            List<ServerMetaData> inputsDB = new ArrayList<ServerMetaData>();
            List<ServerMetaData> outputsDB = new ArrayList<ServerMetaData>();
            VarType theType = VarType.valueOf(varType);
            VarContext theContext = VarContext.valueOf(varContext);

            Class clzz = classForString(datatype);
            if (theType == null || theContext == null || clzz == null) {
                LOGGER.error("Invalid constants used");
                return;
            }

            if (inputOrOutput.equals("input")) {
                // note - we are assuming inputs will either be categorical or scalar

                inputsDB.add(new ServerMetaData(name, intName, description, theType, theContext,
                    new String[] {label}, new String[] {units}, new Class[] {clzz}, min == null ? null
                        : new String[] {min}, max == null ? null : new String[] {max}, categories == null ? null
                        : splitString(categories), value == null ? null : new String[] {value}, external));

            } else {
                outputsDB.add(new ServerMetaData(name, intName, description, theType, theContext,
                    new String[] {label}, new String[] {units}, new Class[] {clzz}, min == null ? null
                        : new String[] {min}, max == null ? null : new String[] {max}, categories == null ? null
                        : splitString(categories), value == null ? null : new String[] {value}, external));
            }

            for (ServerMetaData m : inputsDB) {
                sim.addInput(m);
            }
            for (ServerMetaData m : outputsDB) {
                sim.addOutput(m);
            }
            repo.commit();
        } catch (Exception e) {
            repo.rollback();
            throw handleException(e);
        }
    }

    /**
     * <p>
     * The class for the given string.
     * <p>
     *
     * @param s the string
     * @return the class mapped
     */
    @SuppressWarnings("unchecked")
    private Class classForString(String s) {
        return "STRING".equals(s) ? String.class : "INTEGER".equals(s) ? Integer.class
            : "DOUBLE".equals(s) ? Double.class : null;
    }

    /**
     * <p>
     * splits the string.
     * </p>
     *
     * @param s the string
     * @return split result
     */
    private String[] splitString(String s) {
        return s.split(";");
    }

    private void deleteSimulation(Simulation sim) {
        repo.deleteSimulation(sim.getId());
        //repo.commit();
       
    }

    /**
     * <p>
     * Edits the simulation.
     * </p>
     *
     * @param request the request
     * @param id the id of simulation to be edited
     * @param name the name
     * @param description the description
     * @param state_ the action
     * @param url the url
     * @return the response which redirect to current simulation information
     *
     * @throws SimulationException if any error occurs during the service
     */
    @POST
    @Path("/editsim/{id}")
    public Response editSimulation(@Context HttpServletRequest request, @PathParam("id") String id,
        @FormParam("name") String name, @FormParam("description") String description,
        @FormParam("state") String state_, @FormParam("url") String url, @FormParam("command") String action) {
        LOGGER.info("Handle editSimulation : " + id);

        try {
            Simulation sim = repo.findSimulation(id);
            if (sim == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            if (action!=null && "delete".equals(action)) {
               deleteSimulation(sim);
               return Response.status(Response.Status.OK).build();
            }

            if (name != null) {
                sim.setName(name);
            }
            if (description != null) {
                sim.setDescription(description);
            }
            EntityState state = null;
            if (state_ != null) {
                state = (state_ == null) ? null : EntityState.valueOf(state_);
                if (state == null) {
                    return Response.status(Response.Status.BAD_REQUEST).build();
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Set state to " + state);
                }
                sim.setState(state);
            }
            if (url != null) {
                sim.setURL(url);
            }

            repo.commit();
            return Response.seeOther(new URI(getResourcePathUrl(request, "/simulation/" + sim.getId()))).build();
        } catch (Exception e) {
            repo.rollback();
            throw handleException(e);
        }

    }

    /**
     * <p>
     * Runs the simulation.
     * </p>
     *
     * @param request the request object
     * @param id the simulation id
     * @param user the user
     * @param formparams the form parameters
     * @return the response
     *
     * @throws SimulationException if any error occur
     */
    @POST
    @Path("/runsim")
    public Response runSimulation(@Context HttpServletRequest request, @FormParam("simId") String id,
        @FormParam("user") String user, MultivaluedMap<String, String> formparams) {
        LOGGER.info("Handle runSimulation id :" + id);
        if (StringUtils.isBlank(id)) {
            LOGGER.warn("No valid id, can't run simulation. id : " + id);
            return Response.status(Status.BAD_REQUEST).build();
        }

        try {
            Simulation sim = repo.findSimulation(id);
            if (sim == null) {
                return Response.status(Status.NOT_FOUND).build();
            }
            formparams.remove("simId");
            formparams.remove("user");

            Map<String, Variable> varmap = new HashMap<String, Variable>();
            for (Map.Entry<String, List<String>> ent : formparams.entrySet()) {
                varmap.put(ent.getKey(), createVariableFromForm(sim, ent.getKey(), ent.getValue().get(0)));
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Gathered inputs " + varmap);
            }

            SimulationStepper stepper = ((ServerSimulation) sim).getSimulationStepper();

            int count = 0;
            while (stepper.hasNext()) {
                LOGGER.debug("Running simulation step");
                Step step = stepper.next();
                try {
                    count++;
                    step.process(runner, varmap, null);
                } catch (Exception e) {
                    LOGGER.error("step " + count + " has some error.");
                    handleException(e);
                }
            }

            ServerScenario scenario = new ServerScenario(sim, user, "-tmp-" + sim.getName() + "-" + sim.getId(), "",
                EntityState.TEMPORARY);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Attempting to add inputs/outputs " + varmap + " to scenario");
            }
            addDataToScenario(varmap, scenario);

            repo.commit();
            // xml document of scenario information
            return Response.seeOther(new URI(getResourcePathUrl(request, "/scenario/" + scenario.getId()))).build();
        } catch (Exception e) {
            repo.rollback();
            throw handleException(e);
        }

    }

    /**
     * <p>
     * Executes the post to the given url.
     * </p>
     *
     * @param sim the simulation
     * @param params the name/value pairs
     * @return the response result
     *
     * @throws SimulationException if any error occur
     */
    private String executePost(Simulation sim, NameValuePair[] params) {
        HttpClient client = new HttpClient();
        final String baseUrl = sim.getURL().toExternalForm();
        LOGGER.debug("Will be running sim at " + baseUrl);
        PostMethod get = new PostMethod(baseUrl);
        get.setRequestBody(params);
        get.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
        String response = null;

        try {
            client.executeMethod(get);
            byte[] responsebody = get.getResponseBody();
            response = new String(responsebody);
        } catch (HttpException e) {
            LOGGER.error("HttpException communicating with " + baseUrl, e);
            throw new SimulationException("HttpException communicating with " + baseUrl);
        } catch (IOException e) {
            LOGGER.error("IOException communicating with " + baseUrl, e);
            throw new SimulationException("IOException communicating with " + baseUrl);
        } finally {
            get.releaseConnection();
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Response from simulation is: " + response);
        }
        return response;
    }

    /**
     * <p>
     * Parse the response string.  Response string of the form:
     * <code>
     * <variblename>[[val1,val2,...,valn][val1,val2,...,valn]]
     * </code>
     *
     * </p>
     *
     * @param sim the simulation
     * @param data the data
     * @return the data in variable form
     */
    private Map<String, Variable> parseResponseString(Simulation sim, String data) {

        Map<String, String> vars = new HashMap<String, String>();
        Map<String, Variable> result = new HashMap<String, Variable>();

        Pattern p = Pattern.compile("(?:^|\\]\\])\\<([^(?:\\>\\[\\[)]*)\\>\\[\\[", Pattern.DOTALL);
        Matcher m = p.matcher(data);
        List<String> varnames = new ArrayList<String>();
        while (m.find()) {
            varnames.add(m.group(1));
        }
        LOGGER.debug("Got varnames " + varnames);

        p = Pattern.compile("\\>(\\[\\[(?:.*?)\\]\\])(?:\\<|$)", Pattern.DOTALL);
        m = p.matcher(data);

        Iterator<String> names = varnames.iterator();
        while (m.find()) {
            String value = m.group(1);
            LOGGER.debug("Got value " + value);
            vars.put(names.next(), value);

        }

        for (MetaData md : sim.getOutputs()) {
            if (!vars.containsKey(md.getInternalName())) {
                LOGGER
                    .warn("Could not find output variable " + md.getInternalName() + " in response from simulation");
                continue;
            }
            Variable v = createVariableFromString(md, vars.get(md.getInternalName()));
            result.put(md.getInternalName(), v);
        }
        return result;
    }

    /**
     * <p>
     * Adds data to the scenario.
     * </p>
     *
     * @param varmap variable map
     * @param scenario the scenario
     */
    private void addDataToScenario(Map<String, Variable> varmap, Scenario scenario) {
        // inputs
        for (MetaData m : scenario.getSimulation().getInputs()) {
            String internalname = m.getInternalName();
            Variable v = varmap.get(internalname);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Adding " + v + " to inputs for " + internalname);
            }
            if (v == null) {
                LOGGER.warn("Variable is null; varmap is " + varmap);
                continue;
            }
            scenario.addToInput(v);
        }

        // outputs
        for (MetaData m : scenario.getSimulation().getOutputs()) {
            String internalname = m.getInternalName();
            Variable v = varmap.get(internalname);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Adding " + v + " to outputs for " + internalname);
            }
            if (v == null) {
                LOGGER.warn("Variable is null; varmap is " + varmap);
                continue;
            }
            scenario.addToOutput(v);
        }
    }

    /**
     * <p>
     * Creates the variable from the form.
     * </p>
     *
     * @param sim the simulation
     * @param internalname the internal name
     * @param value the value
     * @return the variable object
     */
    private static Variable createVariableFromForm(Simulation sim, String internalname, String value) {
        LOGGER.debug("Setting " + internalname + " to " + value);
        MetaData targmd = null;
        for (MetaData md : sim.getInputs()) {
            if (internalname.equals(md.getInternalName())) {
                targmd = md;
            }
        }
        if (targmd == null) {
            LOGGER.warn("Unrecognized input parameter " + internalname + " in sim " + sim.getId() + "; skipping");
            return null;
        }
        ServerVariable result = new ServerVariable(targmd);
        result.addValue(new ServerTuple(new String[] {value}));
        return result;
    }

    /**
     * <p>
     * Creates the variable from the string.
     * </p>
     *
     * @param md the meta data
     * @param data the data
     * @return the variable
     */
    private static Variable createVariableFromString(MetaData md, String data) {
        ServerVariable result = new ServerVariable(md);
        data = data.substring(1, data.length() - 1);
        Pattern p = Pattern.compile("\\[([^\\[^\\]]+)\\]");
        Matcher m = p.matcher(data);
        while (m.find()) {
            String match = m.group(1);
            if (match.contains(TupleStatus.INVALID.getCode())) {
                ServerTuple tpl = new ServerTuple(new String[]{null});
                tpl.setStatus(TupleStatus.INVALID);
            } else if (md.getProfile()[0].equals(java.lang.String.class)) {
                result.addValue(new ServerTuple(new String[] {match}));
            } else {
                result.addValue(new ServerTuple(MetaData.Utils.convertArray(match.split(","), md)));
            }
        }
        return result;
    }

    private static Variable createOutOfRangeVariable(MetaData md) {
        ServerVariable result = new ServerVariable(md);
        ServerTuple value = new ServerTuple(new String[] {null});
        value.setStatus(TupleStatus.OUT_OF_RANGE);
        result.addValue(value);
        return result;
    }


    /**
     * <p>
     * Gets payload response.
     * </p>
     *
     * @param wrapper the response wrapper
     * @return the payload response
     */
    private Response getPayloadResponse(ResponseWrapper wrapper) {
        return Response.ok(new JAXBElement<ResponseWrapper>(new QName("payload"), ResponseWrapper.class, wrapper))
            .build();
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
    private <T extends Exception> SimulationException handleException(T exception) {
        LOGGER.error("Error occurred during the service : " + exception.getMessage(), exception);
        if (exception instanceof SimulationException) {
            throw (SimulationException) exception;
        } else {
            return new SimulationException(exception.getMessage());
        }
    }

    /**
     * <p>
     * Provides the simulation API with a means for running remote simulations. See {@link SimulationRunner}
     * </p>
     *
     * @author jintrone
     * @version 1.0
     */
    public class Runner implements SimulationRunner {

        @Override
        public Map<String, Variable> process(Simulation sim, Map<String, Variable> varinputs,
            Map<String, String> strinputs) {
            List<MetaData> inputs = sim.getInputs();
            MetaData fuzzy = null;
            String[] bounds;
            for (MetaData md : inputs) {
                String internalname = md.getInternalName();
                if (md.getVarType() == MetaData.VarType.FUZZY_DISCRETE) {
                    fuzzy = md;
                    break;
                }

            }
            if (fuzzy == null) {
                return _process(sim, varinputs, strinputs);
            } else {
                String value = getInputValue(fuzzy, strinputs, varinputs);
                if (fuzzy.getProfile()[0].equals(Integer.class)) {
                    double dvalue = Double.parseDouble(value);
                    if (dvalue == Math.floor(dvalue)) {
                        return _process(sim, varinputs, strinputs);
                    } else {
                        strinputs.put(fuzzy.getInternalName(), Math.floor(dvalue) + "");
                        Map<String, Variable> firstpass = _process(sim, varinputs, strinputs);
                        strinputs.put(fuzzy.getInternalName(), Math.ceil(dvalue) + "");
                        return combine(firstpass, _process(sim, varinputs, strinputs));
                    }
                } else {
                    return _process(sim, varinputs, strinputs);
                }
            }
        }

        public String getInputValue(MetaData md, Map<String, String> strinputs, Map<String, Variable> varinputs) {
            String internalname = md.getInternalName();
            String value = md.getDefault() == null ? null : md.getDefault()[0];
            if (strinputs.containsKey(internalname)) {
                value = strinputs.get(internalname);
            } else if (varinputs.containsKey(internalname)) {
                List<Tuple> tuples = varinputs.get(internalname).getValue();
                if (tuples == null || tuples.size() == 0) {
                    return value;
                } else if (tuples.get(0).getValues() == null || tuples.get(0).getValues().length == 0) {
                    return value;
                } else {
                    value = tuples.get(0).getValues()[0];
                }
            }

            return value;

        }

        public Map<String, Variable> _process(Simulation sim, Map<String, Variable> varinputs,
            Map<String, String> strinputs) {

            List<MetaData> inputs = sim.getInputs();
            NameValuePair[] params = new NameValuePair[inputs.size()];
            List<MetaData> outofrange = new ArrayList<MetaData>();
            int i = 0;
            for (MetaData md : inputs) {
                String ival = getInputValue(md,strinputs,varinputs);
                if (!md.isInRange(new String[] {ival})) {
                        LOGGER.warn(ival+" in variable "+md.getInternalName()+" in "+sim.getName()+" is out of range.");
                  outofrange.add(md);
                }
                params[i++] = new NameValuePair(md.getInternalName(), ival);
            }

            if (outofrange.isEmpty()) {
                String result = executePost(sim, params);
                return parseResponseString(sim, result);
            } else {
                LOGGER.info("Build out of range result for "+sim.getName());
                return buildOutOfRangeResult(sim);
            }


        }

        private Map<String,Variable> buildOutOfRangeResult(Simulation sim) {
            Map<String,Variable> result = new HashMap<String,Variable>();

            for (MetaData md:sim.getOutputs()) {
                result.put(md.getInternalName(),createOutOfRangeVariable(md));
            }
            return result;
        }

        private Map<String, Variable> combine(Map<String, Variable> existing, Map<String, Variable> toadd) {
            for (String var : existing.keySet()) {
                Variable evar = existing.get(var);
                Variable nvar = toadd.get(var);

                List<Tuple> tuplelist = nvar.getValue();
                for (Tuple t : tuplelist) {
                    ServerTuple st = new ServerTuple(t.getValues());
                    st.setStatus(t.getStatus());
                    evar.addValue(st);
                    repo.remove(((ServerTuple) t).dao);
                }
                ServerVariable sv = (ServerVariable) nvar;
                repo.remove(sv.dao);

            }
            return existing;
        }

    }

}
