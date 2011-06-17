package org.climatecollaboratorium.debatebrowser.wrappers;

public class NewItemEdit {
    private String title;
    private String description;
    private Long id;
    private boolean added;
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    public void setAdded(boolean added) {
        this.added = added;
    }
    public boolean isAdded() {
        return added;
    }
    
    

}
