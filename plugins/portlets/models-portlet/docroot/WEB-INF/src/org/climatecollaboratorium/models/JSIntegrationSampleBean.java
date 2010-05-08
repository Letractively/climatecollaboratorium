package org.climatecollaboratorium.models;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSIntegrationSampleBean {
    private int sliderVal;
    private String data;

    public int getSliderVal() {
        return sliderVal;
    }

    public void setSliderVal(int sliderVal) throws InterruptedException {
        this.sliderVal = sliderVal;
        
        try {
            List<Number []> points = new ArrayList<Number[]>();
            for (double i=-5; i < 5; i+= 0.1) {
                points.add(new Number[] {i, Math.pow(i, sliderVal)});
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
        //this.data = data;
    }
}
