package mit.simulation.climate.dao;

import mit.simulation.climate.dao.auto._SimulationMap;

public class SimulationMap extends _SimulationMap {

    private static SimulationMap instance;

    private SimulationMap() {}

    public static SimulationMap getInstance() {
        if(instance == null) {
            instance = new SimulationMap();
        }

        return instance;
    }
}
