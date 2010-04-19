package mit.simulation.climate.model.persistence;

import mit.simulation.climate.dao.UserDAO;
import mit.simulation.climate.model.User;

public class ServerUser extends ServerObject<UserDAO> implements User {


	public ServerUser(UserDAO dao) {
		super(dao);
	}

	@Override
	public String getId() {
		return dao.getId();
	}

	@Override
	public String getUsername() {
		return dao.getUsername();
	}

	@Override
	public void setUsername(String name) {
		dao.setUsername(name);
	}



	public boolean equals(Object o) {
		return ((o instanceof ServerUser) && dao.equals(((ServerUser)o).dao));
	}

}
