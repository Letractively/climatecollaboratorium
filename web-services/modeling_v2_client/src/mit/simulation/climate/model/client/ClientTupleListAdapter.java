package mit.simulation.climate.model.client;

import mit.simulation.climate.model.Tuple;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientTupleListAdapter {


	@XmlElement(name="data")
	String data;


	public ClientTupleListAdapter() {

	}

	public ClientTupleListAdapter(List<Tuple> src) {
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

    public static List<Tuple> parse(String data) {
        String stripped =  data.substring(1,data.length()-1);
        Pattern p = Pattern.compile("\\[.*?\\]");
        Matcher m = p.matcher(stripped);
        List<Tuple> result = new ArrayList<Tuple>();
        while (m.find()) {
          result.add(new ClientTuple(m.group().split(",")));
        }
        return result;
    }


	public static class Adapter extends XmlAdapter<ClientTupleListAdapter,List<Tuple>> {

		@Override
		public ClientTupleListAdapter marshal(List<Tuple> v) throws Exception {
			return new ClientTupleListAdapter(v);
		}

		@Override
		public List<Tuple> unmarshal(ClientTupleListAdapter v) throws Exception {
			return parse(v.data);
		}


	}


    

}