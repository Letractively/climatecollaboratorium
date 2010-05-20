package mit.simulation.climate.client.model.jaxb;

import mit.simulation.climate.client.*;
import mit.simulation.climate.client.model.ClientRepository;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlRootElement(name="reference")
public class ClientJaxbReference <T extends HasId> {
    @XmlElement
    public String type=null;
    @XmlElement
    public String id = null;

    public Class clz;

    public ClientJaxbReference() {

    }

    public Class referenceClass() {
        return clz;
    }

    public static class SimulationAdapter extends
            XmlAdapter<ClientJaxbReference<Simulation>, Simulation> {

        @Override
        public ClientJaxbReference<Simulation> marshal(Simulation arg0) throws Exception {
            return null;
        }

        @Override
        public Simulation unmarshal(ClientJaxbReference<Simulation> arg0) throws Exception {
             return (Simulation) ClientRepository.instance().resolveOrDefer(arg0,Simulation.class);


        }
    }

    public static class ScenarioAdapter extends
            XmlAdapter<ClientJaxbReference, Scenario> {

        @Override
        public ClientJaxbReference marshal(Scenario arg0) throws Exception {
            return null;
        }

        @Override
        public Scenario unmarshal(ClientJaxbReference arg0) throws Exception {
            return (Scenario)ClientRepository.instance().resolveOrDefer(arg0,Scenario.class);
        }
    }

    public static class MetaDataAdapter extends
            XmlAdapter<ClientJaxbReference, MetaData> {

        @Override
        public ClientJaxbReference marshal(MetaData arg0) throws Exception {
            return null;
        }

        @Override
        public MetaData unmarshal(ClientJaxbReference arg0) throws Exception {
            return (MetaData)ClientRepository.instance().resolveOrDefer(arg0,MetaData.class);
        }
    }

    public static class VariableAdapter extends
            XmlAdapter<ClientJaxbReference, Variable> {

        @Override
        public ClientJaxbReference marshal(Variable arg0) throws Exception {
            return null;
        }

        @Override
        public Variable unmarshal(ClientJaxbReference arg0) throws Exception {
            return (Variable)ClientRepository.instance().resolveOrDefer(arg0,MetaData.class);
        }
    }

}