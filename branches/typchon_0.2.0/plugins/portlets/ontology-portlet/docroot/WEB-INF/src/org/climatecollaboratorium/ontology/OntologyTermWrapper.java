package org.climatecollaboratorium.ontology;

import com.ext.portlet.ontology.model.OntologyTerm;
import com.ext.portlet.ontology.service.OntologyTermLocalServiceUtil;
import com.liferay.portal.SystemException;

public class OntologyTermWrapper {
    private OntologyTerm ontologyTerm;

    public OntologyTermWrapper(OntologyTerm t) {
        ontologyTerm = t;
    }
    
    public String getName() {
        return ontologyTerm.getName();
    }
    
    public int getChildCount() throws SystemException {
        return ontologyTerm.getChildTermsCount();
    }

}
