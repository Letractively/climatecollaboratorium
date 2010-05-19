package com.ext.portlet.debaterevision.model.impl;

import java.util.List;

import org.climatecollaboratorium.test.BaseCollabTest;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;

public class DebateItemImplTest extends BaseCollabTest {
    
    public void testCheckIfAnyDebateItemExist() throws Exception {
        List<DebateItem> items = DebateItemLocalServiceUtil.getDebateItems(0, 1);
        assertTrue(items.size() > 0);
    }

}
