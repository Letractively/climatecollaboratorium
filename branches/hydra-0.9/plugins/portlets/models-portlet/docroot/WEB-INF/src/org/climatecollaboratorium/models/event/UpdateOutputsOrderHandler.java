package org.climatecollaboratorium.models.event;

import net.sf.json.JSONArray;

import org.climatecollaboratorium.jsintegration.JSEvent;
import org.climatecollaboratorium.jsintegration.JSEventHandler;

public class UpdateOutputsOrderHandler implements JSEventHandler {

    @Override
    public void onJsEvent(JSEvent event) {
        System.out.println("event");
        System.out.println(event.getPayload().getClass().getName());
        System.out.println(event.getPayload());
        System.out.println(JSONArray.toArray(JSONArray.fromObject(event.getPayload())));

    }

}
