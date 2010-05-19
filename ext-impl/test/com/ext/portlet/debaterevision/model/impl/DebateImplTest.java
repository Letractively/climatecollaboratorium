package com.ext.portlet.debaterevision.model.impl;

import java.util.List;

import org.climatecollaboratorium.test.BaseCollabTest;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;

public class DebateImplTest extends BaseCollabTest {
    
    public void setUp() {
    }

    
    public void tearDown() {
    }
    
    
    public void testDebatesNonEmpty() throws Exception {
        List<Debate> debates = DebateLocalServiceUtil.getDebates();
        assertTrue(debates.size() > 0);
    }
    
    public void testThisTestShouldFail() throws Exception {
        Debate debate = DebateLocalServiceUtil.getDebate(-3L);
    }
 
}
