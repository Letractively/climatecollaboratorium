package mit.simulation.climate.model.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.MetaData;


@XmlType(name="metadata")
public class MetaDataJAXBDelegate implements MetaData {

	MetaData src;

	public MetaDataJAXBDelegate() {

	}

	public MetaDataJAXBDelegate(MetaData src) {
		this.src = src;
	}


	@XmlElement(name="description")
	public String getDescription() {
		return src.getDescription();
	}


	@XmlElement(name="id")
	public String getId() {
		return src.getId();
	}


	@XmlElement(name="internalname")
	public String getInternalName() {
		return src.getInternalName();
	}

	@XmlElement(name="name")
	public String getName() {
		return src.getName();
	}

	@XmlElement(name="profile")
	@XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
	public Class<Number>[] getProfile() {
		return src.getProfile();
	}

	@XmlAttribute(name="type")
	public Type getType() {
		return src.getType();
	}

	@XmlElement(name="units")
	@XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
	public String[] getUnits() {
		return src.getUnits();
	}

	@XmlElement(name="defaults")
	@XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
	public Number[] getDefault() {
		return src.getDefault();
	}

	@XmlElement(name="maxes")
	@XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
	public Number[] getMax() {
		return src.getMax();
	}

	@XmlElement(name="mins")
	@XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
	public Number[] getMin() {
		return src.getMin();
	}

	@XmlElement(name="labels")
	@XmlJavaTypeAdapter(ArrayAdapter.Adapter.class)
	public String[] getLabels() {
		return src.getLabels();
	}

	@XmlElement(name="external")
	public String getExternalInfo() {
		return src.getExternalInfo();
	}


	public void setLabels(String[] l) {
		//do nothing
	}

	public void setDescription(String desc) {
	//	src.setDescription(desc);
	}

	public void setInternalName(String name) {
	//	src.setInternalName(name);
	}

	public void setName(String name) {
	//	src.setName(name);
	}

	public void setProfile(Class<Number>[] profile) {
		//src.setProfile(profile);
	}

	public void setType(Type t) {
	//	src.setType(t);
	}

	public void setUnits(String[] units) {
	//	src.setUnits(units);
	}

	public static class Adapter extends XmlAdapter<MetaDataJAXBDelegate,MetaData> {

		@Override
		public MetaDataJAXBDelegate marshal(MetaData v) throws Exception {
			return new MetaDataJAXBDelegate(v);
		}

		@Override
		public MetaData unmarshal(MetaDataJAXBDelegate v) throws Exception {
			return v;
		}

	}


	@Override
	public void setDefault(Number[] n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMax(Number[] n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMin(Number[] n) {
		// TODO Auto-generated method stub

	}


	@Override
	public void setExternalInfo(String info) {
		// TODO Auto-generated method stub

	}





}
