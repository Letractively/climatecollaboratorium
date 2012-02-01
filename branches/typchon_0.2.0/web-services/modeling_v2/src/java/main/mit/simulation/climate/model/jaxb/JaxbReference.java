package mit.simulation.climate.model.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;


import mit.simulation.climate.model.HasId;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.Variable;
import mit.simulation.climate.model.persistence.ServerObject;

@XmlRootElement(name="reference")
public class JaxbReference {
    @XmlElement
    public String type=null;
    @XmlElement
    public String id = null;


    public JaxbReference() {

    }

    public JaxbReference(HasId node) {
        if (node==null) return;
        if (node instanceof Simulation) {
            type = "simulation";
        } else if (node instanceof Scenario) {
            type = "scenario";
        } else if (node instanceof MetaData) {
            type = "metadata";
        } else if (node instanceof Variable) {
            type = "variable";
        }
        if (node instanceof ServerObject && ((ServerObject)node).getDataObject() == null) {
            this.id = "unknown";
        } else {
            this.id = node.getId()+"";
        }
    }

    public static class Adapter extends
            XmlAdapter<JaxbReference, HasId> {

        @Override
        public JaxbReference marshal(HasId arg0) throws Exception {
            return new JaxbReference(arg0);
        }

        @Override
        public HasId unmarshal(JaxbReference arg0) throws Exception {
            return null;
        }
    }

}
