package org.climatecollaboratorium.ontology.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.ontology.model.OntologySpace;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import edu.emory.mathcs.backport.java.util.Collections;

public class OntologySpaceWrapper {
    private OntologySpace space;
    private OntologyTerm term;
    private OntologyTerm newTerm;
    private boolean changingTerm;

    public OntologySpaceWrapper(OntologySpace space, OntologyTerm term) {
        this.space = space;
        this.term = term;
        newTerm = term;
    }

    public OntologySpace getSpace() {
        return space;
    }

    public void setSpace(OntologySpace space) {
        this.space = space;
    }

    public OntologyTerm getTerm() {
        return term;
    }

    public void setTerm(OntologyTerm term) {
        this.term = term;
    }

    public void setChangingTerm(boolean changingTerm) {
        this.changingTerm = changingTerm;
    }

    public boolean isChangingTerm() {
        return changingTerm;
    }
    
    public void changeTerm(ActionEvent e) {
        changingTerm = !changingTerm;
        newTerm = term;
    }

    public void setNewTerm(OntologyTerm newTerm) {
        this.newTerm = newTerm;
    }

    public OntologyTerm getNewTerm() {
        return newTerm;
    }
    
    public List<OntologyTerm> getAllTermParents() throws PortalException, SystemException {
        List<OntologyTerm> ret = new ArrayList<OntologyTerm>();
        
        if (newTerm != null) {
            OntologyTerm t = newTerm;
            
            while (t != null) {
                ret.add(t);
                t = t.getParent();
            }
            
            Collections.reverse(ret);
            
        }
        return ret;
        
    }
    
    public void selectTerm(ActionEvent e) throws PortalException, SystemException {
        Long termId = Long.parseLong(e.getComponent().getAttributes().get("termId").toString());
        
        term = OntologyTermLocalServiceUtil.getOntologyTerm(termId);
        changingTerm = !changingTerm;
    }
    
    public void setNewTermId(ActionEvent e) throws PortalException, SystemException {
        Long termId = Long.parseLong(e.getComponent().getAttributes().get("termId").toString());
        newTerm = OntologyTermLocalServiceUtil.getOntologyTerm(termId);
        
    }
    

}