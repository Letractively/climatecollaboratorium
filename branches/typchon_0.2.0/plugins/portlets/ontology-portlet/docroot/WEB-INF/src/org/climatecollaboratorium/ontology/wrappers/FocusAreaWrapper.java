package org.climatecollaboratorium.ontology.wrappers;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.ontology.FocusAreaAdminBean;

import com.ext.portlet.ontology.model.FocusArea;
import com.ext.portlet.ontology.model.OntologySpace;
import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.ontology.service.OntologySpaceLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class FocusAreaWrapper {
    private FocusArea area;
    private List<OntologySpaceWrapper> spaces;
   
    
    public FocusAreaWrapper(Long areaId) throws PortalException, SystemException {
        if (areaId == null) {
            area = FocusAreaLocalServiceUtil.createFocusArea(areaId);
        }
        else {
            area = FocusAreaLocalServiceUtil.getFocusArea(areaId);
        }
    }

    public FocusArea getArea() {
        return area;
    }
    
    public void save() {
        
    }
    
    public List<OntologySpaceWrapper> getOntologySpaces() throws SystemException, PortalException {
        if (spaces == null) {
            spaces = new ArrayList<OntologySpaceWrapper>();
            
            for (OntologySpace space: OntologySpaceLocalServiceUtil.getOntologySpaces(0, Integer.MAX_VALUE)) {
                OntologyTerm term = space.getTopTerm();
                for (OntologyTerm t: area.getTerms()) {
                    if (t.getOntologySpaceId().equals(term.getOntologySpaceId())) {
                        term = t;
                    }
                }
                
                spaces.add(new OntologySpaceWrapper(space, term));
            }
        }
        return spaces;
    }
    
    public void save(ActionEvent e) throws PortalException, SystemException {
        area.store();
        for (OntologySpaceWrapper osw: getOntologySpaces()) {
            area.addTerm(osw.getTerm().getId());
        }
        
    }
}
