/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.service;

import java.io.IOException;
import java.util.List;

import mit.excelwrapper.Utils;
import mit.excelwrapper.model.ExcelModel;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * <p>
 * The default implementation of simulation service which talks to simulation service.
 * </p>
 * <p>
 * <b>Thread Safety</b> all functions should be thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class DefaultSimulationService implements SimulationService {
    /**
     * <p>
     * Connection time out value. It is in milliseconds.
     * </p>
     */
    private static final int CONNECTION_TIME_OUT = 10 * 1000;

    /**
     * <p>
     * read time out value. It is in milliseconds.
     * </p>
     */
    private static final int READ_TIME_OUT = 10 * 1000;

    /**
     * <p>
     * Http client. It will be initialized in ctor and it will be never changed.
     * </p>
     */
    private final HttpClient client;

    /**
     * <p>
     * Connection manager.It will be initialized in ctor and it will be never changed.
     * </p>
     */
    private final MultiThreadedHttpConnectionManager connectionManager;

    /**
     * <p>
     * The constructor. It initializes http client and prepares some shared http connections.
     * </p>
     */
    public DefaultSimulationService() {
        connectionManager = new MultiThreadedHttpConnectionManager();

        client = new HttpClient(connectionManager);

        client.getHttpConnectionManager().getParams().setConnectionTimeout(CONNECTION_TIME_OUT);
        client.getHttpConnectionManager().getParams().setSoTimeout(READ_TIME_OUT);
    }

    /**
     * <p>
     * Creates the simulation for excel model given.
     * </p>
     *
     * @param model the model
     * @param name the name for this simulation
     * @param description the description
     * @param serverURLPrefix the server url prefix which will points to the actual simulation service
     * @return created simulation id
     *
     * @throws IOException if any IO error occurs during the process
     * @throws IllegalArgumentException if model or param list is null
     */
    public String createExcelModelSimulation(ExcelModel model, String name, String description, String serverURLPrefix)
        throws IOException {
        Utils.checkNull(model, "model");
        Utils.checkNull(model.getParamList(), "param list");

        String simulationURL = serverURLPrefix + "/simulation-servlet/rest/simulation";
        String excelRunURL = serverURLPrefix + "/excel_wrapper-servlet/rest/wrapper/" + model.getId();

        String simulationId = null;

        // creates the simulation
        PostMethod post = new PostMethod(simulationURL);
        NameValuePair[] postValues = new NameValuePair[] {new NameValuePair("name", name),
            new NameValuePair("description", description), new NameValuePair("state", "TEMPORARY"),
            new NameValuePair("url", excelRunURL)};
        post.setRequestBody(postValues);
        try {
            client.executeMethod(post);
            simulationId = new String(post.getResponseBody());
        } finally {
            post.releaseConnection();
        }

        String createSimulationParamURL = serverURLPrefix + "/simulation-servlet/rest/createsim/" + simulationId;
        for (List<NameValuePair> param : model.getParamList()) {
            // created the param
            post = new PostMethod(createSimulationParamURL);
            post.setRequestBody(param.toArray(new NameValuePair[] {}));
            try {
                client.executeMethod(post);
            } finally {
                post.releaseConnection();
            }
        }

        return simulationId;
    }

    /**
     * <p>
     * Clears up any resource. In this case, connections are cleared.
     * </p>
     */
    public void destroy() {
        if (connectionManager != null) {
            connectionManager.shutdown();
        }
    }

}
