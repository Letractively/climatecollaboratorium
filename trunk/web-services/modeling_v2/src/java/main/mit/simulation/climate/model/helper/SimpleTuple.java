package mit.simulation.climate.model.helper;

import mit.simulation.climate.model.Tuple;
import mit.simulation.climate.model.TupleStatus;

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

    @Override
    public TupleStatus getStatus() {
        return TupleStatus.OK;
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
