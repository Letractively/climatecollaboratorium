package org.climatecollaboratorium.debatebrowser;

import com.ext.portlet.debaterevision.model.DebateCategory;

public class DebateCategoryEditItem {
    
    private DebateCategory wrapped;
    private boolean visible;
    private int debatesOnIndex;
    
    public DebateCategoryEditItem(DebateCategory wrapped, boolean visible, int debatesOnIndex) {
        this.wrapped = wrapped;
        this.visible = visible;
        this.debatesOnIndex = debatesOnIndex;
    }
    
    public DebateCategory getDebateCategory() {
        return wrapped;
    }
    
    public boolean isVisible() {
        return visible;
    }
    
    public void setVisible(boolean visible) {
        this.visible = visible; 
    }

    public void setDebatesOnIndex(int debatesOnIndex) {
        this.debatesOnIndex = debatesOnIndex;
    }

    public int getDebatesOnIndex() {
        return debatesOnIndex;
    }

}
