package mit.simulation.climate.client;

import mit.simulation.climate.client.comm.*;
import mit.simulation.climate.client.model.impl.ClientSimulation;
import mit.simulation.climate.client.model.jaxb.ResponseWrapper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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
                buf.append(" : isIndex - ").append(md.getIndex());
                buf.append(" : indexingmd - "+(md.getIndexingMetaData()!=null?md.getIndexingMetaData().getId():"<none>"));

                buf.append("\n");
            }
            buf.append("\n");
            log.info(buf);
        }

    }

    @Test
    public void testRepositormodeling_v2_clientyRetrieval_Scenario() throws IOException {
        //ClientRepository repo = ClientRepository.instance("localhost", 8080);
        ClientRepository repo = ClientRepository.instance("localhost",8080);
        
        Scenario s = repo.getScenario(5524l);
        Assert.assertNotNull(s);
        log.info("Scenario: " + s.getName());
        log.info(getScenarioString(s));

    }


    @Test
    public void testCompositeModelRun() throws IOException, ScenarioNotFoundException, ModelNotFoundException, MetaDataNotFoundException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Scenario scenario = TestHelper.runComposite3region(repo, 783L);
        log.info("Scenario: "+scenario.getName()+" id:"+scenario.getId());
        log.info(getScenarioString(scenario));
        Assert.assertEquals(EntityState.TEMPORARY,scenario.getState());

    }


     @Test
    public void testCompositeModelRunOptimized() throws IOException, ScenarioNotFoundException, ModelNotFoundException, MetaDataNotFoundException {
        ClientRepository repo = ClientRepository.instance("climatecolab.org", 8080);
        Scenario scenario = TestHelper.runComposite3region(repo, 862L);
        log.info("Scenario: "+scenario.getName()+" id:"+scenario.getId());
        log.info(getScenarioString(scenario));
        Assert.assertEquals(EntityState.TEMPORARY,scenario.getState());

    }
       @Test
     public void testCompositeModelRun2() throws IOException, ScenarioNotFoundException, ModelNotFoundException, MetaDataNotFoundException {
        ClientRepository repo = ClientRepository.instance("cognosis.mit.edu", 8888);
        Scenario scenario = TestHelper.runCompositeTwo(repo);
        log.info("Scenario: "+scenario.getName()+" id:"+scenario.getId());
        log.info(getScenarioString(scenario));
        Assert.assertEquals(EntityState.TEMPORARY,scenario.getState());

    }

    @Test
    public void testCompositeModelRun3() throws IOException, ScenarioNotFoundException, ModelNotFoundException, MetaDataNotFoundException {
        ClientRepository repo = ClientRepository.instance("climatecolab.org", 8080);
        Scenario scenario = TestHelper.runCompositeThree(repo);
        log.info("Scenario: "+scenario.getName()+" id:"+scenario.getId());
        log.info(getScenarioString(scenario));
        Assert.assertEquals(EntityState.TEMPORARY,scenario.getState());

    }

    @Test
    public void testTypeField() throws IOException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
         Set<Simulation> sims = repo.getAllSimulations();
        log.info("Retrieved " + sims.size() + " simulations:");
        for (Simulation s:repo.getAllSimulations()) {
            log.info("Retrieved: "+s.getName()+" - "+s.getId());
        }
        Simulation sim = repo.getSimulation(920L);
        Assert.assertNotNull(sim);
        Assert.assertEquals("test test",sim.getType());
    }

   

    @Test
    public void testModelUpdate() throws IOException, ScenarioNotFoundException, ModelNotFoundException {
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Simulation s = repo.getSimulation(621L);
        String name = s.getName();
        s.setName("fooey");
        repo.updateSimulation(s);

        s = repo.getSimulation(621L);
        Assert.assertEquals("fooey",s.getName());

        s.setName(name);
        repo.updateSimulation(s);

        s = repo.getSimulation(621L);
        Assert.assertEquals(name,s.getName());

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
            buf.append(v.getMetaData().getName()).append(":").append(v.getMetaData().getId()).append(":");
            buf.append(v.getValue().toString()).append(":");
            buf.append("\n");
        }
        return buf.toString();
    }

   


}
