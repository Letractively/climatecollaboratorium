package mit.simulation.climate.model.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import mit.simulation.climate.model.Tuple;

public class TupleListAdapter {


	@XmlElement(name="data")
	String data;


	public TupleListAdapter() {

	}

	public TupleListAdapter(List<Tuple> src) {
		if (src == null) data = null;
		else {
		data = "[";
		for (Tuple t:src) {
			String sep="";
			data+="[";
			for (String n:t.getValues()) {
				data+=(sep+ (n==null?"null":n.toString()));
				sep=",";
			}
			data+="]";
		}
		data+="]";
		}
	}

	public static class Adapter extends XmlAdapter<TupleListAdapter,List<Tuple>> {

		@Override
		public TupleListAdapter marshal(List<Tuple> v) throws Exception {
			return new TupleListAdapter(v);
		}

		@Override
		public List<Tuple> unmarshal(TupleListAdapter v) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}


	}

}
