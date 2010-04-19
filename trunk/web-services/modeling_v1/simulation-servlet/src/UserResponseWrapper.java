import javax.xml.bind.annotation.XmlElement;

import mit.simulation.climate.model.User;




public class UserResponseWrapper {

	@XmlElement(name="user")
	public User user;
	@XmlElement(name="admin")
	public boolean admin = false;

	public UserResponseWrapper() {}
	public UserResponseWrapper(User u, boolean admin) {
		this.user = u;
		this.admin = admin;
	}
}
