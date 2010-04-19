/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate;

import mit.simulation.climate.resource.SimulationResourceTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * <p>
 * Aggregates all tests.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses( {SimulationResourceTest.class})
public class AllTests {
    /**
     * <p>
     * Before class set up function.
     * </p>
     */
    @BeforeClass
    public static void setUpBeforeClass() {

    }

    /**
     * <p>
     * After class set up function.
     * </p>
     */
    @AfterClass
    public static void tearDownAfterClass() {

    }

}
