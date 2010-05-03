/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.resource;

import java.io.IOException;
import java.util.Random;

import mit.excelwrapper.model.ExcelModel;
import mit.excelwrapper.service.SimulationService;

/**
 * <p>
 * The mock implementation of simulation service which talks to simulation service.
 * </p>
 * <p>
 * <b>Thread Safety</b> all functions are thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class MockSimulationService implements SimulationService {
    /**
     * <p>
     * Simply returns some random number.
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
        return new Random().nextInt() + "";
    }

    /**
     * <p>
     * Clears up any resource. In this case, connections are cleared.
     * </p>
     */
    public void destroy() {
        // do nothing
    }

}
