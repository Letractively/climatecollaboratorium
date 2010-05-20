package mit.simulation.climate.client.model.impl;

import mit.simulation.climate.client.Tuple;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement
public class ClientTuple implements Tuple {

	String[] values;

	public ClientTuple() {
	}

    public ClientTuple(String[] vals) {
        this.values = vals;
    }

	public String[] getValues() {
       return values;
    }

    public String toString() {
       StringBuffer buf = new StringBuffer();
        for (String val:getValues()) {
            buf.append("[").append(val).append("]");
        }
        return buf.toString();
    }

	@Override
	public void setValues(String[] vals) {
		values = vals;
	}

	public static class Adapter extends XmlAdapter<ClientTuple, Tuple> {

		@Override
		public ClientTuple marshal(Tuple arg0) throws Exception {
			return null;
		}

		@Override
		public Tuple unmarshal(ClientTuple arg0) throws Exception {
			return arg0;
		}

	}

}