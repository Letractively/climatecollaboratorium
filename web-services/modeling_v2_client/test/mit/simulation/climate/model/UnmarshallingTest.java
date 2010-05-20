package mit.simulation.climate.model;

import mit.simulation.climate.model.client.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: jintrone
 * @date: May 17, 2010
 */
public class UnmarshallingTest {


    private static Logger log = Logger.getLogger(UnmarshallingTest.class);

    @Test
    public void testUnmarshall() throws FileNotFoundException, JAXBException {

        JAXBContext context = JAXBContext.newInstance(ResponseWrapper.class);
        System.setProperty("jaxb.debug", "true");


        Unmarshaller um = context.createUnmarshaller();
        um.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
        ResponseWrapper o = (ResponseWrapper) um.unmarshal(ClientSimulation.class.getResourceAsStream("/AllSimulations.xml"));

    }

    @Test
    public void testRepositoryRetrieval_Simulations() throws IOException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Set<Simulation> sims = repo.getAllSimulations();
        log.info("Retrieved " + sims.size() + " simulations:");
        for (Simulation sim : sims) {
            log.info(sim.getName());
            StringBuffer buf = new StringBuffer();
            buf.append("-- Inputs\n");
            for (MetaData md : sim.getInputs()) {
                buf.append("---- ").append(md.getId()).append(":").append(md.getName()).append(" - ").append(md.getDescription());
                buf.append("\n");
            }
            log.info(buf.toString());
            buf = new StringBuffer();
            buf.append("-- Outputs\n");
            for (MetaData md : sim.getOutputs()) {
                buf.append("---- ").append(md.getId()).append(":").append(md.getName()).append(" - ").append(md.getDescription());
                buf.append("\n");
            }
            buf.append("\n");
            log.info(buf);
        }

    }

    @Test
    public void testRepositoryRetrieval_Scenario() throws IOException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);

        Scenario s = repo.getScenario(2703l);
        Assert.assertNotNull(s);
        log.info("Scenario: " + s.getName());
        log.info(getScenarioString(s));

    }


    @Test
    public void testModelRun() throws IOException, ScenarioNotFoundException, ModelNotFoundException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Map<Long, Object> inputs = new HashMap<Long, Object>();
        inputs.put(2941L,4.0);
        Scenario s = repo.runModel(repo.getSimulation(621L),inputs,null,false);
        log.info("Scenario: "+s.getName());
        log.info(getScenarioString(s));
        Assert.assertEquals(EntityState.TEMPORARY,s.getState());

    }


    private static String getScenarioString(Scenario s) {
        List<Variable> inputs = s.getInputSet();
        StringBuffer buf = new StringBuffer();
        buf.append("Inputs\n");
        for (Variable v : inputs) {
            buf.append(v.getMetaData().getName()).append(":").append(v.getMetaData().getId()).append(":");
            buf.append(v.getValue().toString());
            buf.append("\n");
        }
        buf.append("\n");
        List<Variable> outputs = s.getOutputSet();
        buf.append("Outputs\n");
        for (Variable v : outputs) {
            buf.append(v.getMetaData().getName()).append(":");
            buf.append(v.getValue().toString());
            buf.append("\n");
        }
        return buf.toString();
    }


}
