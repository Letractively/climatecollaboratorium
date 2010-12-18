package org.climatecollaboratorium.facelets.simulations;

import mit.simulation.climate.client.Scenario;

import org.climatecollaboratorium.events.Event;

public class ScenarioSavedEvent implements Event {
    private Scenario scenario;

    public ScenarioSavedEvent(Scenario scenario) {
        this.setScenario(scenario);
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

}
