package mit.simulation.climate.model.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Tuple;
import mit.simulation.climate.model.Variable;

public class VariableJAXBDelegate implements Variable {

    private Variable src;


    public VariableJAXBDelegate() {

    }

    public VariableJAXBDelegate(Variable src) {
        this.src = src;
    }

    @XmlAttribute(name="id")
    public Long getId() {
        return src.getId();
    }

    @XmlElement(name="metadata")
    @XmlJavaTypeAdapter(JaxbReference.Adapter.class)
    public MetaData getMetaData() {
        return src.getMetaData();
    }


    @XmlJavaTypeAdapter(TupleListAdapter.Adapter.class)
    @XmlElement(name="values")
    public List<Tuple> getValue() {
        return src.getValue();
    }

    @Override
    public void addValue(Tuple t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setMetaData(MetaData md) {
        // TODO Auto-generated method stub

    }


    public static class Adapter extends XmlAdapter<VariableJAXBDelegate,Variable> {

        @Override
        public VariableJAXBDelegate marshal(Variable v) throws Exception {
            return new VariableJAXBDelegate(v);
        }

        @Override
        public Variable unmarshal(VariableJAXBDelegate v) throws Exception {
            return v;
        }


    }



}
