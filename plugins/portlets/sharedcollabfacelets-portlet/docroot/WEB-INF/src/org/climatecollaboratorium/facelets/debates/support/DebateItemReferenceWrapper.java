package org.climatecollaboratorium.facelets.debates.support;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.facelets.debates.AddEditDebateItemBean;
import org.climatecollaboratorium.facelets.debates.backing.EditDebateItemBean;

import com.ext.portlet.debaterevision.model.DebateItemReference;

public class DebateItemReferenceWrapper  {
    private DebateItemReference reference;
    private EditDebateItemBean editDebateItemBean;
    private AddEditDebateItemBean addEditDebateItemBean;
    
    public DebateItemReferenceWrapper(DebateItemReference reference, EditDebateItemBean editDebateItemBean) {
        this.reference = reference;
        this.editDebateItemBean = editDebateItemBean;
    }
    
    public DebateItemReferenceWrapper(DebateItemReference reference, AddEditDebateItemBean addEditDebateItemBean) {
        this.reference = reference;
        this.addEditDebateItemBean = addEditDebateItemBean;
    }
    
    public DebateItemReference getReference() {
        return reference;
    }
    public void setReference(DebateItemReference reference) {
        this.reference = reference;
    }
    public EditDebateItemBean getEditDebateItemBean() {
        return editDebateItemBean;
    }
    public void setEditDebateItemBean(EditDebateItemBean editDebateItemBean) {
        this.editDebateItemBean = editDebateItemBean;
    }
    
    public String getText() {
        return reference.getText();
    }
    
    public String getUrl() {
        return reference.getUrl();
    }

    
    public void removeReference(ActionEvent event) {
        if (addEditDebateItemBean != null) {
            addEditDebateItemBean.removeReference(this);
        }
        else {
            editDebateItemBean.removeReference(this);
        }
    }
}
