package mit.simulation.climate.client.model.jaxb;

import mit.simulation.climate.client.Tuple;
import mit.simulation.climate.client.model.impl.ClientTuple;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(stripped);
        List<Tuple> result = new ArrayList<Tuple>();
        while (m.find()) {
            String[] splited = m.group(1).split(",");
            for (int i=0; i < splited.length; i++) {
                try {
                    splited[i] = URLDecoder.decode(splited[i], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            result.add(new ClientTuple(splited));
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