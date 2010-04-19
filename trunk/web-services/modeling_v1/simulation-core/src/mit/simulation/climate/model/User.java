package mit.simulation.climate.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.jaxb.UserJAXBDelegate;


@XmlJavaTypeAdapter(UserJAXBDelegate.Adapter.class)
public interface User extends HasId {

	public String getId();

	public String getUsername();


	public void setUsername(String name);

}
