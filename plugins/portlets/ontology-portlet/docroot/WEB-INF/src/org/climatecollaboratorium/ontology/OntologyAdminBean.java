package org.climatecollaboratorium.ontology;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.ext.portlet.ontology.model.OntologySpace;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologySpaceLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

import edu.emory.mathcs.backport.java.util.Collections;

public class OntologyAdminBean {
    
    private Long termId;
    private Long spaceId;
    
    private String newTerm;
    private String newTermDescriptionUrl;

    private String newSpace;

    private String newSpaceDescription;
    private Long editedTermId = null;
    
    private List<OntologyTerm> terms = null;
    
    public List<OntologyTerm> getOntologyTerms() throws SystemException {
        terms = OntologyTermLocalServiceUtil.findByParentIdSpaceId(termId, spaceId);
        return terms;
    }
    
    public List<OntologySpace> getOntologySpaces() throws SystemException {
        return OntologySpaceLocalServiceUtil.getOntologySpaces(0, Integer.MAX_VALUE);
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
    
    public OntologySpace getSelectedSpace() throws PortalException, SystemException {
        if (spaceId != null) {
            return OntologySpaceLocalServiceUtil.getOntologySpace(spaceId);
        }
        return null;
    }
    
    public void setSpaceId(ActionEvent e) {
        spaceId = null;
        termId = null;
        try {
            if (e.getComponent().getAttributes().get("spaceId") != null) 
                spaceId = Long.parseLong(e.getComponent().getAttributes().get("spaceId").toString());
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
        if (spaceId == null || newTerm == null || newTerm.trim().equals("")) {
            return;
        }
        
        // check if there is no such term
        List<OntologyTerm> actTerms = getOntologyTerms();
        for (OntologyTerm t: actTerms) {
            if (t.getName().equals(newTerm)) return;
        }
        
        
        OntologyTermLocalServiceUtil.createTerm(termId, newTerm, spaceId, newTermDescriptionUrl);
        newTerm = "";
    }
    
    public void createNewSpace(ActionEvent e) throws SystemException {
        if (newSpace == null || newSpace.trim().equals("")) {
            return;
        }
        
        // check if there is no such term
        List<OntologySpace> actSpaces = getOntologySpaces();
        for (OntologySpace t: actSpaces) {
            if (t.getName().equals(newSpace)) return;
        }
        
        
        OntologySpaceLocalServiceUtil.createSpace(newSpace, newSpaceDescription);
        
        newSpace = "";
        newSpaceDescription = "";
    }
    
    public void deleteTerm(ActionEvent e) throws PortalException, SystemException {
        Long termId = null;
        try {
            Object term = e.getComponent().getAttributes().get("termId");
            if (term != null) {
                termId = Long.parseLong(term.toString());
            }

        }
        catch (NumberFormatException ex) {
            // ignore
        }
        if (termId != null) {
            OntologyTermLocalServiceUtil.deleteOntologyTerm(termId);
        }
        
    }
    
    public void editTerm(ActionEvent e) throws PortalException, SystemException {
        Long termId = null;
        try {
            Object term = e.getComponent().getAttributes().get("termId");
            if (term != null) {
                termId = Long.parseLong(term.toString());
            }
        }
        catch (NumberFormatException ex) {
            // ignore
        }
        if (termId != null) {
            setEditedTermId(termId);
        }
        else {
            editedTermId = null;
        }
    }
    
    public void updateTerm(ActionEvent e) throws PortalException, SystemException {
        Long termId = null;
        try {
            Object term = e.getComponent().getAttributes().get("termId");
            if (term != null) {
                termId = Long.parseLong(term.toString());
            }

        }
        catch (NumberFormatException ex) {
            // ignore
        }
        if (termId != null) {
            for (OntologyTerm t: terms) {
                if (t.getId().equals(editedTermId)) {
                    t.store();
                }
            }
            setEditedTermId(termId);
        }
        editedTermId = null;
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
    
    public Long getTermId() {
        return termId;
    }


    public String getNewSpace() {
        return newSpace;
    }

    public void setNewSpace(String newSpace) {
        this.newSpace = newSpace;
    }

    public String getNewSpaceDescription() {
        return newSpaceDescription;
    }

    public void setNewSpaceDescription(String newSpaceDescription) {
        this.newSpaceDescription = newSpaceDescription;
    }

    public void setNewTermDescriptionUrl(String newTermDescriptionUrl) {
        this.newTermDescriptionUrl = newTermDescriptionUrl;
    }

    public String getNewTermDescriptionUrl() {
        return newTermDescriptionUrl;
    }

    public void setEditedTermId(Long editedTermId) {
        this.editedTermId = editedTermId;
    }

    public Long getEditedTermId() {
        return editedTermId;
    }
    
}
