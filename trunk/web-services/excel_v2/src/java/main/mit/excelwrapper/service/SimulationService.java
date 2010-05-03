/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.excelwrapper.service;

import mit.excelwrapper.model.ExcelModel;

/**
 * <p>
 * The interface for the service which talks to simulation service.
 * </p>
 * <p>
 * <b>Thread Safety</b> All implementation classes should be thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public interface SimulationService {
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
     * @throws Exception if any error occurs during the process
     */
    public String createExcelModelSimulation(ExcelModel model, String name, String description, String serverURLPrefix)
        throws Exception;

    /**
     * <p>
     * Clears up any resource.
     * </p>
     */
    public void destroy();
}
