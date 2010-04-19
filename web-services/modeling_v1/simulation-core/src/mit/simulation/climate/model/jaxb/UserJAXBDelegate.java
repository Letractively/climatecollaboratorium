package mit.simulation.climate.model.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import mit.simulation.climate.model.User;

@XmlRootElement
public class UserJAXBDelegate implements User {

	private User src;


	public UserJAXBDelegate() {
		
	}
	
	public UserJAXBDelegate(User src) {
		this.src = src;
	}
	
	@XmlAttribute(name="id")
	public String getId() {
		return src.getId();
	}

	@XmlAttribute(name="username")
	public String getUsername() {
		return src.getUsername();
	}

	

	@Override
	public void setUsername(String name) {
		// TODO Auto-generated method stub

	}


	public static class Adapter extends XmlAdapter<UserJAXBDelegate,User> {

		@Override
		public UserJAXBDelegate marshal(User arg0) throws Exception {
			return new UserJAXBDelegate(arg0);
		}

		@Override
		public User unmarshal(UserJAXBDelegate arg0) throws Exception {
			return arg0;
		}


	}



}
