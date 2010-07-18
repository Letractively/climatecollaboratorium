package mit.simulation.climate.model.helper;

import mit.simulation.climate.model.Tuple;

public class SimpleTuple implements Tuple{

	private final String[] values;

	public SimpleTuple(String[] values) {
		this.values = values;
	}
	@Override
	public String[] getValues() {
		return values;
	}

	@Override
	public void setValues(String[] vals) {
		throw new RuntimeException("Cannot set values on SimpleTuple");

	}

	public String toString() {
		StringBuffer buffer=new StringBuffer();
		String sep = "";
		for (String v:values) {

			buffer.append(sep);
			sep=",";
		}
		return "["+buffer.toString()+"]";
	}

}
