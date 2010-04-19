package mit.simulation.climate.model.jaxb;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import mit.simulation.climate.model.Tuple;

@XmlRootElement
public class TupleJAXBDelegate implements Tuple {

	private Tuple src;

	public TupleJAXBDelegate(Tuple src) {
		this.src = src;
	}


	public Number[] getValues() {
		return src.getValues();
	}

	@Override
	public void setValues(Number[] vals) {
		// TODO Auto-generated method stub

	}

	public static class Adapter extends XmlAdapter<TupleJAXBDelegate, Tuple> {

		@Override
		public TupleJAXBDelegate marshal(Tuple arg0) throws Exception {
			return new TupleJAXBDelegate(arg0);
		}

		@Override
		public Tuple unmarshal(TupleJAXBDelegate arg0) throws Exception {
			return arg0;
		}

	}

}
