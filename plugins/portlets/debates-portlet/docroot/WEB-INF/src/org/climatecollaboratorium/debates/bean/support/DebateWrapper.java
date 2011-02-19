package org.climatecollaboratorium.debates.bean.support;

import com.ext.portlet.debaterevision.model.Debate;

public class DebateWrapper {
    private Debate wrapped;
    
    public DebateWrapper(Debate debate) {
        this.wrapped = debate;
    }
    
}
