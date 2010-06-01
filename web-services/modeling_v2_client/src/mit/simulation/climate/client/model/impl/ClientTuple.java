package mit.simulation.climate.client.model.impl;

import mit.simulation.climate.client.Tuple;
import mit.simulation.climate.client.TupleStatus;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.*;

@XmlRootElement
public class ClientTuple implements Tuple {

	String[] values;
    Map<Integer,TupleStatus> statuses = new LinkedHashMap<Integer,TupleStatus>();

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

    @Override
    public TupleStatus getStatus(int index) {
        return statuses.get(index);
    }


    @Override
    public List<TupleStatus> getAllStatuses() {
        return new ArrayList<TupleStatus>(statuses.values());
    }

    public void setStatus(int i, TupleStatus status) {
        statuses.put(i,status);
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