package mit.simulation.climate.model.persistence;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import mit.simulation.climate.dao.TupleDAO;
import mit.simulation.climate.dao.TupleValuesDAO;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Tuple;

public class ServerTuple extends ServerObject<TupleDAO> implements Tuple {


	private static Logger log = Logger.getLogger(ServerTuple.class);


	public ServerTuple(TupleDAO dao) {
		super(dao);
	}

	public ServerTuple(Number[] values) {
		setValues(values);

	}

	@Override
	public Number[] getValues() {
		List<TupleValuesDAO> vals = dao.getTupleValues();
		Collections.sort(vals, new Comparator<TupleValuesDAO>() {

			@Override
			public int compare(TupleValuesDAO o1, TupleValuesDAO o2) {
				return o1.getSeq() - o2.getSeq();
			}

		});
		Number[] result = new Number[vals.size()];
		Class<Number>[] profile = ServerRepository.instance().get(dao.getTuplesToVariable()).getMetaData().getProfile();
		int idx=0;

		for (TupleValuesDAO val:vals) {
			Class clz =  profile[idx];
			if (clz == Integer.class) {
				result[idx++] = new Integer(val.getValue().intValue());
			} else if (clz == Double.class) {
				result[idx++] = new Double(val.getValue().doubleValue());
			} else if (clz == Float.class) {
				result[idx++] = new Double(val.getValue().floatValue());
			} else {
				log.error("Unhandled underlying data type for tuple value: "+clz);
			}
		}

		return result;
	}

	public static Number parseNumber(Class clz, String val) {
		if (clz == Integer.class) {
			return Integer.parseInt(val);
		} else if (clz == Double.class) {
			return Double.parseDouble(val);
		} else if (clz == Float.class) {
			return Double.parseDouble(val);

		} else {
			log.error("Unhandled underlying data type for tuple value: "+clz);
		}
		return null;
	}

	@Override
	public void setValues(Number[] vals) {
		dao.getTupleValues().clear();
		int idx = 0;
		for (Number val:vals) {
			TupleValuesDAO ndao = ServerRepository.instance().createDAO(TupleValuesDAO.class);
			ndao.setValue(val.doubleValue());
			ndao.setSeq(idx++);
			dao.addToTupleValues(ndao);
		}
	}

	public String toString() {
		String result = "{";
		Number[] vals = getValues();
		for (Number v:vals) {
			result+=v+" ";
		}
		result+="}";
		return result;
	}



	public boolean equals(Object o) {
		return ((o instanceof ServerTuple) && dao.equals(((ServerTuple)o).dao));
	}

}
