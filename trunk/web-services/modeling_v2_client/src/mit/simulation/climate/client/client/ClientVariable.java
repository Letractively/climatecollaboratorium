package mit.simulation.climate.client.client;

import mit.simulation.climate.client.MetaData;
import mit.simulation.climate.client.Tuple;
import mit.simulation.climate.client.Variable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="variable")
public class ClientVariable implements Variable {

    Long id;
    MetaData metadata;
    List<Tuple> values = null;

    public ClientVariable() {

    }

    @XmlAttribute(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {

    }

    @XmlElement(name="metadata")
    @XmlJavaTypeAdapter(ClientJaxbReference.MetaDataAdapter.class)
    public MetaData getMetaData() {
        return metadata;
    }


    @XmlJavaTypeAdapter(ClientTupleListAdapter.Adapter.class)
    @XmlElement(name="values")
    public List<Tuple> getValue() {
        if (values == null) {
            values = new ArrayList<Tuple>();
        }
        return values;
    }

    public void setValue(List<Tuple> t) {
        values = new ArrayList<Tuple>(t);
    }

    @Override
    public void addValue(Tuple t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMetaData(MetaData md) {
        this.metadata = md;

    }


    public static class Adapter extends XmlAdapter<ClientVariable,Variable> {

        @Override
        public ClientVariable marshal(Variable v) throws Exception {
            return null;
        }

        @Override
        public Variable unmarshal(ClientVariable v) throws Exception {
            return v;
        }


    }



}