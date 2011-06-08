package org.climatecollaboratorium.debatebrowser.wrappers;

import com.ext.portlet.debaterevision.model.DebateCategory;

public class DebateCategoryWrapper {
    private DebateCategory wrapped;

    public DebateCategoryWrapper(DebateCategory cat) {
        this.wrapped = cat;
        //cat.getDebates().get(0).getNumberOfComments().
    }
    
    public String getTitle() {
        return wrapped.getTitle();
    }
    
    public String getDescription() {
        return wrapped.getDescription();
    }

}
