package com.ext.portlet.debaterevision;

import com.ext.portlet.debaterevision.model.impl.DebateImplTest;
import com.ext.portlet.debaterevision.model.impl.DebateItemImplTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

    /**
     * Aggregates all tests in this class.
     *
     * @return Test suite aggregating all tests.
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite();

        // unit tests
        suite.addTestSuite(DebateImplTest.class);
        suite.addTestSuite(DebateItemImplTest.class);
        
        return suite;
    }

}
