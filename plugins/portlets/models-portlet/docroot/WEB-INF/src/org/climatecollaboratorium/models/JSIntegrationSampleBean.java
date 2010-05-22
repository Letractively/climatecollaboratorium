package org.climatecollaboratorium.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;

import com.ext.portlet.modeling.CollaboratoriumModelingService;
import com.liferay.portal.SystemException;

import mit.simulation.climate.client.Simulation;
import mit.simulation.climate.client.comm.ClientRepository;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSIntegrationSampleBean {
    private int sliderVal;
    private String data;
    private ClientRepository r;
    private Set<Simulation> simulations;
    public JSIntegrationSampleBean() throws SystemException, IOException {
        r = ClientRepository.instance("localhost", 8080);
        simulations = r.getAllSimulations();
    }

    public int getSliderVal() {
        return sliderVal;
    }

    public void setSliderVal(int sliderVal) throws InterruptedException, SystemException {
        try {
            this.sliderVal = sliderVal;

            List<Number[]> points = new  ArrayList<Number[]>();
            for (double i = -5; i < 5; i += 0.1) {
                points.add(new Number[] { i, Math.pow(i, sliderVal) });
            }

            JSDataTransport transportData = new JSDataTransport("graph1", System.currentTimeMillis(), points);

            data = JSONObject.fromObject(transportData).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ActionEvent event) {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        // this.data = data;
    }
    
    public Set<Simulation> getSimulations() {
        return simulations;
    }
}
