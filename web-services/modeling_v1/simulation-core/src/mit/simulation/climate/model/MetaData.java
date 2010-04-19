package mit.simulation.climate.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import mit.simulation.climate.model.jaxb.MetaDataJAXBDelegate;



@XmlJavaTypeAdapter(MetaDataJAXBDelegate.Adapter.class)
public interface MetaData extends HasId {

	public enum Type {

		ORDINAL, CATEGORICAL, SCALAR
	}

	public String getId();

	public String getName();
	public void setName(String name);


	public String getInternalName();
	public void setInternalName(String name);

	public String[] getUnits();
	public void setUnits(String[] units);

	public Class<Number>[] getProfile();
	public void setProfile(Class<Number>[] profile);

	public String[] getLabels();
	public void setLabels(String[] lables);

	public Number[] getMax();
	public void setMax(Number[] n);

	public Number[] getMin();
	public void setMin(Number[] n);

	public Number[] getDefault();
	public void setDefault(Number[] n);

	public String getDescription();
	public void setDescription(String desc);

	public Type getType();
	public void setType(Type t);
	
	public void setExternalInfo(String info);
	public String getExternalInfo();

	public static class Utils {

		public static Number formatNumber(Class<? extends Number> cls,Number actual) {
			if (cls == null || actual==null) return null;
			if (cls == java.lang.Integer.class) {
				return actual.intValue();
			} else if (cls == java.lang.Float.class) {
				return actual.floatValue();
			} else if (cls == java.lang.Double.class) {
				return actual.doubleValue();
			}
			return actual;
		}

		public static Number[] convertArray(String[] array, MetaData md) {
			Number[] result = new Number[array.length];

			for (int i =0;i<array.length;i++) {
				result[i] = formatNumber(md.getProfile()[i],new Double(array[i]));
			}
			return result;
		}
	}


}
