package mit.simulation.climate.model.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mit.simulation.climate.dao.ScenarioDAO;
import mit.simulation.climate.dao.VariableDAO;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.User;
import mit.simulation.climate.model.Variable;

public class ServerScenario extends ServerObject<ScenarioDAO> implements Scenario {


	public ServerScenario(ScenarioDAO dao) {
		super(dao);
	}

	public ServerScenario(Simulation s, User u, String name, String description) {
		setSimulation(s);
		setName(name);
		setAuthor(u);
		setDescription(description);
		setCreation(new Date());
	}

	@Override
	public User getAuthor() {
		return ServerRepository.instance().get(dao.getScenarioToAuthor());
	}

	@Override
	public Date getCreation() {
		return dao.getCreation();
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
	public List<Variable> getInputSet() {
		List<Variable> result = new ArrayList<Variable>();
		for (VariableDAO v:dao.getScenarioToInputVars()) {
			result.add(ServerRepository.instance().get(v));
		}
		return result;
	}

	public void addToInput(Variable v) {
		dao.addToScenarioToInputVars(((ServerVariable)v).getDataObject());
	}

	public void addToOutput(Variable v) {
		dao.addToScenarioOutputToVars(((ServerVariable)v).getDataObject());
	}


	@Override
	public List<Variable> getOutputSet() {
		List<Variable> result = new ArrayList<Variable>();

		for (VariableDAO v:dao.getScenarioOutputToVars()) {
			result.add(ServerRepository.instance().get(v));
		}

		return result;
	}

	@Override
	public Simulation getSimulation() {
		return ServerRepository.instance().get(dao.getScenarioToSimulation());
	}

	@Override
	public void setAuthor(User u) {
		dao.setScenarioToAuthor(((ServerUser)u).getDataObject());

	}

	@Override
	public void setCreation(Date d) {
		dao.setCreation(d);

	}

	@Override
	public void setDescription(String desc) {
		dao.setDescription(desc);
	}

	@Override
	public void setSimulation(Simulation s) {
		dao.setScenarioToSimulation(((ServerSimulation)s).getDataObject());
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
		return ((o instanceof ServerScenario) && dao.equals(((ServerScenario)o).dao));
	}

}
