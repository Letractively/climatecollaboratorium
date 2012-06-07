package mit.simulation.climate.model.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import mit.simulation.climate.dao.SimulationDAO;
import mit.simulation.climate.model.CompositeSimulation;
import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.MetaData.VarContext;
import mit.simulation.climate.model.SimulationStepper.Link;
import mit.simulation.climate.model.SimulationStepper.LinkType;
import mit.simulation.climate.model.SimulationStepper.Step;

public class CompositeServerSimulation extends ServerSimulation implements
		CompositeSimulation {

	private static Logger log = Logger
			.getLogger(CompositeServerSimulation.class);

	private ParserFactory factory;

	private boolean configured = false;

	public CompositeServerSimulation(SimulationDAO dao) {
		super(dao);
		configure(dao.getCompositeDescriptor());
	}

	public CompositeServerSimulation(String name, String description,
			String compositeString, EntityState state) {
		super(name, description, null, null, null, state);
		dao.setCompositeDescriptor(configure(compositeString));

	}

	private ParserFactory getFactory() {
		if (factory == null) {
			factory = new ParserFactory();
		}
		return factory;
	}

	protected String configure(String descriptor) {
		if (configured) return descriptor;
		dao.getToChildren().clear();
		dao.getInputs().clear();
		dao.getOutputs().clear();
		getSimulationStepper().clear();
		DocumentBuilder builder;
		if (descriptor == null) {
			log
					.warn("No composite descriptor; nothing to configure in composite simulation - "
							+ getName());
			return null;
		}
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setIgnoringElementContentWhitespace(true);
			builder = factory.newDocumentBuilder();

		} catch (ParserConfigurationException e) {
			throw new RuntimeException("Couldn't create document builder");
		}

		Document doc;
		try {
			log.debug("Attempting to parse " + descriptor);
			doc = builder.parse(new StringBufferInputStream(descriptor));
			log.debug("Removing whitespace");
			removeWhitespaceNodes(doc.getDocumentElement());
		} catch (SAXException e) {

			throw new RuntimeException("Error parsing composite descriptor", e);
		} catch (IOException e) {
			throw new RuntimeException("Error parsing descriptor", e);
		}


		String result = getFactory().parse(doc);
		configured = true;
		return result;
	}

	//hack to remove whitespace nodes - why oh why java?
	public static void removeWhitespaceNodes(Element e) {
		NodeList children = e.getChildNodes();
		for (int i = children.getLength() - 1; i >= 0; i--) {
			Node child = children.item(i);
			if (child instanceof Text
					&& ((Text) child).getData().trim().length() == 0) {
				e.removeChild(child);
			} else if (child instanceof Element) {
				removeWhitespaceNodes((Element) child);
			}
		}
	}

	@Override
	public void addInput(MetaData md) {
		throw new UnsupportedOperationException(
				"Adding inputs not supported in composite simulation");
	}

	@Override
	public void addOutput(MetaData md) {
		// TODO Auto-generated method stub

	}

	private void _addInput(MetaData md) {
		super.addInput(md);
	}

	private void _addOutput(MetaData md) {
		super.addOutput(md);
	}

	private static class MetaDataHolder {
		MetaData md = null;
		Simulation sim = null;

		public MetaDataHolder(Simulation sim, MetaData md) {
			this.sim = sim;
			this.md = md;
		}
	}

	@Override
	public Set<Simulation> getChildren() {
		Set<Simulation> result = new HashSet<Simulation>();
		for (SimulationDAO d : dao.getToChildren()) {
			result.add((Simulation) ServerFactory.instance().get(d));
		}
		return result;
	}


	/**
	 * Responsible for parsing the composite descriptor
	 *
	 * @author jintrone
	 *
	 */
	private class ParserFactory {

		boolean shouldRemap = true;
		Map<MetaData,MetaData> remapping = new HashMap<MetaData,MetaData>();

		private String parse(Document doc) {
			remapping.clear();
			shouldRemap = true;

			doc.normalizeDocument();

			boolean hasIndex = false;

			NodeList inputSection = doc.getElementsByTagName("inputs");
			for (int i = 0; i < inputSection.getLength(); i++) {
				NodeList inputSims = inputSection.item(0).getChildNodes();
				for (int j = 0; j < inputSims.getLength(); j++) {
					Simulation sim = getSimulation(inputSims.item(j));
					for (MetaData input : sim.getInputs()) {
						hasIndex |= input.isIndex();
						_addInput(input);
					}
				}
			}

			//check to see if we've already determined the necessary remappings.
			NodeList remappings = doc.getElementsByTagName("remap");
			if (remappings!=null && remappings.getLength() > 0) {
				shouldRemap = false;
				for (int i=0;i<remappings.getLength();i++) {
					Node remap = remappings.item(i);
					String frommd = remap.getAttributes().getNamedItem("from").getNodeValue();
					String tomd = remap.getAttributes().getNamedItem("to").getNodeValue();
					remapping.put(ServerRepository.instance().findMetaData(frommd), ServerRepository.instance().findMetaData(tomd));
				}

			}



			NodeList stepSection = doc.getElementsByTagName("step");
			for (int i = 0; i < stepSection.getLength(); i++) {
				log.debug("Processing step "+i);
				NodeList sims = stepSection.item(i).getChildNodes();
				for (int simidx = 0; simidx < sims.getLength(); simidx++) {
					processSimulation(sims.item(simidx));
				}
			}

			// not currently supporting the selection of specific inputs and
			// outputs.



			NodeList outputSection = doc.getElementsByTagName("outputs");
			for (int i = 0; i < outputSection.getLength(); i++) {
				NodeList outputSims = outputSection.item(0).getChildNodes();
				for (int j = 0; j < outputSims.getLength(); j++) {
					Node simnode = outputSims.item(j);
					Simulation sim = getSimulation(outputSims.item(j));
					for (MetaData output : sim.getOutputs()) {
						if (remapping.containsKey(output)) {
							_addOutput(remapping.get(output));
							if (shouldRemap) {
								simnode.appendChild(createRemappingNode(doc,output,remapping.get(output)));
							}
						} else {
							_addOutput(output);
						}
					}
				}
			}

			return getDocumentAsString(doc);



		}

		public String getDocumentAsString(Document doc) {
			Source source = new DOMSource(doc);
			StringWriter writer = new StringWriter();
		    Result result = new StreamResult(writer);

		    Transformer xformer;
			try {
				xformer = TransformerFactory.newInstance().newTransformer();
				xformer.transform(source,result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Error trying to write document back to string");
				e.printStackTrace();
			}
			return writer.toString();

		}

		private Node createRemappingNode(Document doc,  MetaData original, MetaData noutput) {
			Element n = doc.createElement("remap");
			n.setAttribute("from", original.getId());
			n.setAttribute("to", noutput.getId());
			return n;
		}

		private MetaData getReferencedMetadata(Simulation sim,
				String internalname, boolean isInput) {
			for (MetaData md : (isInput) ? sim.getInputs() : sim.getOutputs()) {
				if (md.getInternalName().equals(internalname))
					return md;
			}
			throw new MalformedCompositeDescriptorException(
					"Could not identify referenced metadata " + internalname
							+ " in simulation " + sim.getId());
		}

		private MetaData createIndexedMetaDataFromScalar(MetaData md) {
			ServerMetaData smd = new ServerMetaData(md.getName(),md.getInternalName(),
					md.getDescription(),
					md.getVarType(),VarContext.INDEXED,md.getLabels(),md.getUnits(),
					md.getProfile(),md.getMin(),md.getMax(),md.getCategories(),md.getDefault(),md.getExternalInfo());
			ServerRepository.instance().idhack(smd);
			return smd;
		}

		private void remapOutputs(Simulation sim) {
			for (MetaData d:sim.getOutputs()) {
				if (d.getVarContext() != VarContext.INDEXED) {
					remapping.put(d, createIndexedMetaDataFromScalar(d));
				}
			}
			//return outputmap;
		}

		private Link processLink(Node input, Simulation simulation) {

			String inputvar = input.getAttributes().getNamedItem(
					"internalname").getNodeValue();
			String[] sourcevar = input.getAttributes().getNamedItem("source")
					.getNodeValue().split("\\.", 2);
			Simulation sourcesim = getSimulation(sourcevar[0]);

			LinkType type = null;
			Double interval = null;
			Node attr = input.getAttributes().getNamedItem("map");
			if (attr != null) {
				if (attr.getNodeValue().equals("all")) {
					type = LinkType.ALL;
					//we're going to be mapping an input across a vector dataset
					//so, we need to switch possibly scalar output values to be indexed
					//off of the source data set.
					if (shouldRemap) remapOutputs(simulation);
				} else if (attr.getNodeValue().equals("some")) {
					type = LinkType.SOME;
					Node intervalnode = input.getAttributes().getNamedItem("interval");
					if (intervalnode!=null) {
						interval = Double.parseDouble(intervalnode.getNodeValue());
					}
				}
			} else {
				type = LinkType.MAX;
			}
			return new Link(inputvar, sourcevar[1], type,interval);
		}

		private void processSimulation(Node sim) {
			Simulation simulation = getSimulation(sim);
			log.debug("Got simulation "+simulation.getId());
			List<Link> links = new ArrayList<Link>();
			NodeList inputs = sim.getChildNodes();
			for (int j = 0; j < inputs.getLength(); j++) {
				links.add(processLink(inputs.item(j),simulation));
			}
			Step s = getSimulationStepper().new Step(simulation, links, remapping);
			Node accumulate = sim.getAttributes().getNamedItem("accumulate");
			s.setAccumulate(accumulate!=null);
		}

		private Simulation getSimulation(String simid) {

			Simulation result = ServerRepository.instance().findSimulation(
					simid);
			if (result != null)
				addChild(result);
			else {
				throw new MalformedCompositeDescriptorException(
						"Could not identify referenced simulation");

			}
			return result;
		}

		private Simulation getSimulation(Node sim) {
			log.debug("Attempting to get simulation from " + sim);
			log.debug("Attributes of node are " + sim.getAttributes());
			String simid = sim.getAttributes().getNamedItem("id")
					.getNodeValue();
			return getSimulation(simid);
		}

	}

	public static class MalformedCompositeDescriptorException extends
			RuntimeException {

		public MalformedCompositeDescriptorException(String msg) {
			super(msg);
		}
	}

	@Override
	public String getCompositeDescriptor() {
		return dao.getCompositeDescriptor();
	}

	@Override
	public void setCompositeDescriptor(String descriptor) {
		dao.setCompositeDescriptor(descriptor);

		configured = false;
		configure();
	}

	private void addChild(Simulation sim) {
		if (getChildren().contains(sim))
			return;
		else
			dao.addToToChildren(((ServerSimulation) sim).dao);
	}

}