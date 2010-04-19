package mit.simulation.climate.model.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;


@XmlRootElement
public class ArrayAdapter {

	@XmlElement
	public String data;

	public ArrayAdapter() {

	}

	public ArrayAdapter(Object[] data) {
		this.data = "[";
		String sep="";
		for (Object o:data) {
			this.data+=(sep+(o==null?"null":o.toString()));
			sep=",";
		}
		this.data+="]";
	}


	public static class Adapter extends XmlAdapter<ArrayAdapter,Object[]> {

		@Override
		public ArrayAdapter marshal(Object[] v) throws Exception {
			return new ArrayAdapter(v);
		}

		@Override
		public Object[] unmarshal(ArrayAdapter v) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
