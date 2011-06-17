package org.climatecollaboratorium.debatebrowser.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateCategory;
import com.ext.portlet.debaterevision.service.DebateCategoryLocalServiceUtil;
import com.liferay.portal.SystemException;

public class DebateCategoryWrapper {
    private DebateCategory wrapped;
    private List<Debate> debates;
    private String sortColumn;
    private boolean sortAscending;
    private boolean edit;
    private String description;
    private String title;

    public DebateCategoryWrapper(DebateCategory cat, boolean fullView, int debatesToShow) throws SystemException {
        this.wrapped = cat;
        debates = new ArrayList<Debate>(cat.getDebates());
        if (!fullView) {
            debates = debates.subList(0, Math.min(debatesToShow, debates.size()));
        }
        Collections.shuffle(debates);
        // cat.getDebates().get(0).getNumberOfComments().
        title = wrapped.getTitle();
        description = wrapped.getDescription();
    }

    public String getTitle() {
        return wrapped.getTitle();
    }

    public String getDescription() {
        return wrapped.getDescription();
    }
    
    public void setTitle(String title) {
        this.title = title; 
    }
    
    public void setDescription(String description) {
        this.description = description; 
    }

    public DebateCategory getWrapped() {
        return wrapped;
    }

    public void setSortColumn(final String sortColumn) {
        this.sortColumn = sortColumn;

        // resort debates
        updateDebatesOrder();

    }
    
    

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortAscending(boolean sortAscending) {
        this.sortAscending = sortAscending;
        updateDebatesOrder();
    }

    public boolean isSortAscending() {
        return sortAscending;
    }
    
    public List<Debate> getDebates() {
        return debates;
    }
    
    private void updateDebatesOrder() {

        final int modifier = sortAscending ? 1 : -1;
        Collections.sort(debates, new Comparator<Debate>() {
            public int compare(Debate o1, Debate o2) {
                int ret = o1.compareTo(o2);
                try {
                    if (sortColumn.equals("name")) {
                        ret = o1.getCurrentRoot().getDebateSummary().compareTo(o2.getCurrentRoot().getDebateSummary());
                    } else if (sortColumn.equals("votes")) {
                        ret = o1.getTotalVotesCount() - o2.getTotalVotesCount();
                    } else if (sortColumn.equals("comments")) {
                        ret = o1.getNumberOfComments() - o2.getNumberOfComments();
                    }
                } catch (Exception e) {
                    // ignore
                }
                return modifier * ret;

            }

        });
    }
    
    public boolean isEdit() {
        return edit;
    }
    
    public void toggleEdit(ActionEvent e) {
        edit = !edit;
    }
    
    public void save(ActionEvent e) throws SystemException {
        wrapped.setTitle(title);
        wrapped.setDescription(description);
        
        DebateCategoryLocalServiceUtil.updateDebateCategory(wrapped);
        edit = !edit;
    }

}
