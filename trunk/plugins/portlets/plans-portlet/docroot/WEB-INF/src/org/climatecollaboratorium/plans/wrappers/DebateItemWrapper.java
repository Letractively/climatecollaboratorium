package org.climatecollaboratorium.plans.wrappers;

import com.ext.portlet.debaterevision.model.DebateItem;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class DebateItemWrapper {
    private DebateItem wrapped;

    public DebateItemWrapper(DebateItem wrapped, DebateQuestionWrapper debateQuestionWrapper) {
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
    
    public Long getVotesCount() throws PortalException, SystemException {
        return wrapped.getVotesCount();
    }

    
    public int getVotesPercent() throws PortalException, SystemException {
        int totalVotes = wrapped.getDebate().getTotalVotesCount();
        if (totalVotes <= 0) {
            return 0;
        }
        return (int) ((100 * wrapped.getVotesCount()) / totalVotes);
    }
    

    public String getItemAnchor() {
        return "<a href='" + getItemLink() + "' target='_blank'>" + wrapped.getDebateSummary() + "</a>";
    }

    public String getItemLink() {
        return "/web/guest/debates#debate=" + wrapped.getDebateId() + ";item=" + wrapped.getDebateItemId();
    }

}