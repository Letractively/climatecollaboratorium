package org.climatecollaboratorium.ontology;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import edu.emory.mathcs.backport.java.util.Collections;

public class OntologyAdminBean {
    
    private Long termId;
    
    private String newTerm;
    
    public List<OntologyTerm> getOntologyTerms() throws SystemException {
        return OntologyTermLocalServiceUtil.findByParentId(termId);
    }
    
    
    public void setTermId(ActionEvent e) {
        termId = null;
        try {
            termId = Long.parseLong(e.getComponent().getAttributes().get("termId").toString());
        }
        catch (NumberFormatException ex) {
            // ignore
        }
        
    }


    public void setNewTerm(String newTerm) {
        this.newTerm = newTerm;
    }


    public String getNewTerm() {
        return newTerm;
    }
    
    
    
    public void createNewTerm(ActionEvent e) throws SystemException {
        if (newTerm == null || newTerm.trim().equals("")) {
            return;
        }
        
        // check if there is no such term
        List<OntologyTerm> actTerms = getOntologyTerms();
        for (OntologyTerm t: actTerms) {
            if (t.getName().equals(newTerm)) return;
        }
        
        
        OntologyTermLocalServiceUtil.createTerm(termId, newTerm);
    }
    
    public void deleteTerm(ActionEvent e) throws PortalException, SystemException {
        Long termId = null;
        try {
            termId = Long.parseLong(e.getComponent().getAttributes().get("termId").toString());
        }
        catch (NumberFormatException ex) {
            // ignore
        }
        if (termId != null) {
            OntologyTermLocalServiceUtil.deleteOntologyTerm(termId);
        }
        
    }
    
    public List<OntologyTerm> getAllTermParents() throws PortalException, SystemException {
        List<OntologyTerm> ret = new ArrayList<OntologyTerm>();
        
        if (termId != null) {
            OntologyTerm t = OntologyTermLocalServiceUtil.getOntologyTerm(termId);
            
            while (t != null) {
                ret.add(t);
                t = t.getParent();
            }
            
            Collections.reverse(ret);
            
        }
        return ret;
        
    }
    
    
}
