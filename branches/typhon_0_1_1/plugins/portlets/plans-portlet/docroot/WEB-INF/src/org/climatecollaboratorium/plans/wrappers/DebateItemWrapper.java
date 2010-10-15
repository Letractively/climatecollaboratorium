package org.climatecollaboratorium.plans.wrappers;

import com.ext.portlet.debaterevision.model.DebateItem;

public class DebateItemWrapper {
    private DebateItem wrapped;

    public DebateItemWrapper(DebateItem wrapped) {
        this.wrapped = wrapped;
    }

    public Long getDebateId() {
        return wrapped.getDebateId();
    }

    public String getDebateSummary() {
        return wrapped.getDebateSummary();
    }

    public Long getDebateItemId() {
        return wrapped.getDebateItemId();
    }

    

    public String getItemAnchor() {
        return "<a href='" + getItemLink() + "' target='_blank'>" + wrapped.getDebateSummary() + "</a>";
    }

    public String getItemLink() {
        return "/web/guest/debates#debate=" + wrapped.getDebateId() + ";item=" + wrapped.getDebateItemId();
    }

}