package mit.simulation.climate.client.comm;

import mit.simulation.climate.client.Scenario;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 1, 2010
 * Time: 3:41:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientRepositoryTest {


    @Test
    public void testScenarioCache_Size() throws IOException {
        System.setProperty(ClientRepository.CACHE_PROPERTY,3+"");
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Assert.assertEquals("Cache size should be equal to property setting",3,repo.currentScenarioCacheSize);

    }


    @Test
    public void testScenarioCache_growth() throws IOException, ScenarioNotFoundException, ModelNotFoundException {
        System.setProperty(ClientRepository.CACHE_PROPERTY,3+"");
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Assert.assertEquals("Cache size should be equal to property setting",3,repo.currentScenarioCacheSize);
        repo.scenarioCache.clear();

        
        Scenario old = TestHelper.runCompositeOne(repo);

        List<Scenario> scenarios = new ArrayList<Scenario>();
        scenarios.add(TestHelper.runCompositeOne(repo));
        scenarios.add(TestHelper.runCompositeOne(repo));
        scenarios.add(TestHelper.runCompositeOne(repo));

        Assert.assertEquals("Cache size should only grow to size of 3",3,repo.scenarioCache.size());
        Assert.assertTrue("Cache should contain last three elements accessed",repo.scenarioCache.values().containsAll(scenarios));
        Assert.assertFalse("Cache should not contain first element added",repo.scenarioCache.values().contains(old));
    }

    @Test
    public void testScenarioCache_accessOrder() throws IOException, ScenarioNotFoundException, ModelNotFoundException {
        System.setProperty(ClientRepository.CACHE_PROPERTY,3+"");
        ClientRepository repo = ClientRepository.instance("localhost", 8080);
        Assert.assertEquals("Cache size should be equal to property setting",3,repo.currentScenarioCacheSize);
        repo.scenarioCache.clear();


        Scenario a = TestHelper.runCompositeOne(repo);

        List<Scenario> scenarios = new ArrayList<Scenario>();
        Scenario b = TestHelper.runCompositeOne(repo);
        Scenario c = TestHelper.runCompositeOne(repo);

        repo.getScenario(a.getId());
        Scenario d = TestHelper.runCompositeOne(repo);

        // should contain a, b, d

        Assert.assertTrue("Cache should contain element ",repo.scenarioCache.values().contains(a));
        Assert.assertTrue("Cache should contain element ",repo.scenarioCache.values().contains(c));
        Assert.assertTrue("Cache should contain element ",repo.scenarioCache.values().contains(d));

        Assert.assertFalse("Cache should not contain element ",repo.scenarioCache.values().contains(b));

    }



}
