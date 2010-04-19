

import java.util.Collection;

import javax.xml.bind.JAXB;

import junit.framework.TestCase;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.persistence.ResponseWrapper;
import mit.simulation.climate.model.persistence.ServerRepository;

import org.apache.log4j.Logger;

public class RepositoryTest extends TestCase {

	static Logger log= Logger.getLogger(RepositoryTest.class);


	public void testMarshaller() {
		Collection<Scenario> scenarios = ServerRepository.instance().getAllScenarios();
		Collection<Simulation> sims = ServerRepository.instance().getAllSimulations();
		ResponseWrapper response = new ResponseWrapper(scenarios);
		response.add(sims);
		JAXB.marshal(response, System.err);
	}




}
