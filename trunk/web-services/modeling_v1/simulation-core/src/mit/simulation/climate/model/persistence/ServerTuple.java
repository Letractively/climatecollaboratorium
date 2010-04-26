package mit.simulation.climate.model.persistence;

import mit.simulation.climate.dao.TupleDAO;
import mit.simulation.climate.model.Tuple;

import org.apache.log4j.Logger;

public class ServerTuple extends ServerObject<TupleDAO> implements Tuple {


	private static Logger log = Logger.getLogger(ServerTuple.class);


	public ServerTuple(TupleDAO dao) {
		super(dao);
	}

	public ServerTuple(String[] values) {
		setValues(values);

	}

	@Override
	public String[] getValues() {
		return new String[] {dao.getValue()};
	}

	public static String parseNumber(Class clz, String val) {
		return val;
	}

	@Override
	public void setValues(String[] vals) {
		dao.setValue(vals == null || vals.length == 0?null:vals[0]);
	}

	@Override
	public String toString() {
		String result = "{";
		String[] vals = getValues();
		for (String v:vals) {
			result+=v+" ";
		}
		result+="}";
		return result;
	}



	public boolean equals(Object o) {
		return ((o instanceof ServerTuple) && dao.equals(((ServerTuple)o).dao));
	}

}
