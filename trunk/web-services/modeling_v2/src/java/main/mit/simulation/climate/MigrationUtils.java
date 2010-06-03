package mit.simulation.climate;

import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.persistence.ServerMetaData;
import mit.simulation.climate.model.persistence.ServerRepository;
import mit.simulation.climate.model.persistence.ServerSimulation;
import org.apache.cayenne.BaseContext;
import org.apache.cayenne.access.DataContext;

import java.net.URL;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jun 3, 2010
 * Time: 1:45:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class MigrationUtils {

    public static void copySimulation(String simid) {
        ServerRepository repo = ServerRepository.instance();
        Simulation sim = repo.findSimulation(simid);

        if (sim == null) {
            System.err.println("Can't find sim; bailing");
        }

        ServerSimulation nsim = new ServerSimulation(sim.getName(),
                sim.getDescription(),
                sim.getURL(), Collections.<MetaData>emptyList(), Collections.<MetaData>emptyList(),
                EntityState.PUBLIC);

        for (MetaData md:sim.getInputs()) {
            nsim.addInput(copyMetaData(md));
        }

        for (MetaData md:sim.getOutputs()) {
           nsim.addOutput(copyMetaData(md));
        }

        repo.commit();
    }

    public static ServerMetaData copyMetaData(MetaData md) {
       return new ServerMetaData(md.getName(),md.getInternalName(),md.getDescription(),
                    md.getVarType(),md.getVarContext(),md.getLabels(),md.getUnits(),md.getProfile(),
                    md.getMin(),md.getMax(),md.getCategories(),md.getDefault(),md.getExternalInfo());

    }

    public static void main(String[] args) {
        System.err.println("Running");
        if (args.length == 0) return;
        if ("copy".equals(args[0])) {
            System.err.println("Copying");
            if (args.length > 1) {
                BaseContext.bindThreadObjectContext(DataContext.createDataContext());
                copySimulation(args[1]);
            }
        }
        
    }

}
