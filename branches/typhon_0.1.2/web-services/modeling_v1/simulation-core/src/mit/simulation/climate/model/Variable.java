package mit.simulation.climate.model;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.jaxb.VariableJAXBDelegate;


@XmlJavaTypeAdapter(VariableJAXBDelegate.Adapter.class)
public interface Variable extends HasId {

	public String getId();
	public MetaData getMetaData();
	public void setMetaData(MetaData md);

	public List<Tuple> getValue();
	public void addValue(Tuple t);
}
