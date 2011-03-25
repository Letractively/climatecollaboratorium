package org.climatecollaboratorium.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import edu.mit.cci.simulation.client.comm.*;

import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.SystemException;

public class SimulationTester {
    
    public void clear() {
        
    }
    
    public void basicTests() throws SystemException {
        try {
            ClientRepository repository = CollaboratoriumModelingService.repository();
            for (int i=0; i < 1000; i++) {
                repository.getAllSimulations();
            }
            FacesContext.getCurrentInstance().addMessage("Hej ho message", new FacesMessage("kukunamuniu"));
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        
    }

}
