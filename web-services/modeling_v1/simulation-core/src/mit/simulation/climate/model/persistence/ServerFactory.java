package mit.simulation.climate.model.persistence;

import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.dao.ScenarioDAO;
import mit.simulation.climate.dao.SimulationDAO;
import mit.simulation.climate.dao.TupleDAO;
import mit.simulation.climate.dao.UserDAO;
import mit.simulation.climate.dao.VariableDAO;
import mit.simulation.climate.model.MetaData;



public class ServerFactory {

	private static ServerFactory instance;

	private ServerFactory() {}

	public static ServerFactory instance() {
		if (instance == null) {
			instance = new ServerFactory();
		}
		return instance;
	}

	public MetaData get(MetaDataDAO dao) {
		if (dao==null) return null;
		return new ServerMetaData(dao);
	}

	public ServerUser get(UserDAO dao) {
		if (dao == null) return null;
		return new ServerUser(dao);
	}
	public ServerScenario get(ScenarioDAO dao) {
		if (dao == null) return null;
		return new ServerScenario(dao);
	}

	public ServerVariable get(VariableDAO dao) {
		if (dao == null) return null;
		return new ServerVariable(dao);
	}

	public ServerSimulation get(SimulationDAO dao) {
		if (dao == null) return null;
		return new ServerSimulation(dao);
	}
	public ServerTuple get(TupleDAO dao) {
		if (dao == null) return null;
		return new ServerTuple(dao);
	}


//	public Question get(QuestionDAO dao) {
//		if (dao==null) return null;
//		return new ServerQuestion(dao);
//	}
//
//	public Alternative get(AlternativeDAO dao) {
//		if (dao == null) return null;
//		return new ServerAlternative(dao);
//	}
//	public Argument get(ArgumentDAO dao) {
//		if (dao == null) return null;
//		return new ServerArgument(dao);
//	}
//	public User get(UserDAO dao) {
//		if (dao==null) return null;
//		return new ServerUser(dao);
//	}

}
