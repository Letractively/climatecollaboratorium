package mit.simulation.climate.model.persistence;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.log4j.Logger;

import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.dao.SimulationDAO;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Simulation;

public class ServerSimulation extends ServerObject<SimulationDAO> implements Simulation {

	private static Logger log = Logger.getLogger(ServerSimulation.class);

	public ServerSimulation(SimulationDAO dao) {
		super(dao);
	}

	public ServerSimulation(String name, String description, URL url, List<MetaData> inputs, List<MetaData> outputs) {
		setName(name);
		setDescription(description);
		for (MetaData o:outputs) {
			addOutput(o);
		}
		for (MetaData i:inputs) {
			addInput(i);
		}
 	}



	@Override
	public void addInput(MetaData md) {
		dao.addToInputs(((ServerMetaData)md).getDataObject());
	}

	@Override
	public void addOutput(MetaData md) {
		dao.addToOutputs(((ServerMetaData)md).getDataObject());
	}

	@Override
	public String getDescription() {
		return dao.getDescription();
	}

	@Override
	public String getId() {
		return dao.getId();
	}

	@Override
	public List<MetaData> getInputs() {
		List<MetaData> result = new ArrayList<MetaData>();
		for (MetaDataDAO mddao:dao.getInputs()) {
			result.add(ServerRepository.instance().get(mddao));
		}
		return result;
	}

	@Override
	public List<MetaData> getOutputs() {
		List<MetaData> result = new ArrayList<MetaData>();
		for (MetaDataDAO mddao:dao.getOutputs()) {
			result.add(ServerRepository.instance().get(mddao));
		}
		return result;
	}

	@Override
	public URL getURL() {
		try {
			return dao.getUrl() == null?null:new URL(dao.getUrl());
		} catch (MalformedURLException e) {
			log.error("URL is not valid");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setDescription(String description) {
		dao.setDescription(description);
	}

	@Override
	public void setURL(String url) {
		try {
			setURL(new URL(url));
		} catch (MalformedURLException e) {
			log.error("URL is not valid");
			e.printStackTrace();
		}
	}

	@Override
	public void setURL(URL url) {
		dao.setUrl(url.toString());
	}

	@Override
	public String getName() {
		return dao.getName();
	}

	@Override
	public void setName(String name) {
		dao.setName(name);
	}



	public boolean equals(Object o) {
		return ((o instanceof ServerSimulation) && dao.equals(((ServerSimulation)o).dao));
	}

}
