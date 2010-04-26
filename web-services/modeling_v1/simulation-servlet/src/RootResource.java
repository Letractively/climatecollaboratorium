import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.Descriptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.SimulationStepper;
import mit.simulation.climate.model.Tuple;

import mit.simulation.climate.model.Variable;

import mit.simulation.climate.model.MetaData.VarContext;
import mit.simulation.climate.model.MetaData.VarType;
import mit.simulation.climate.model.SimulationStepper.SimulationRunner;
import mit.simulation.climate.model.persistence.CompositeServerSimulation;
import mit.simulation.climate.model.persistence.ResponseWrapper;
import mit.simulation.climate.model.persistence.ServerMetaData;
import mit.simulation.climate.model.persistence.ServerObject;
import mit.simulation.climate.model.persistence.ServerRepository;
import mit.simulation.climate.model.persistence.ServerScenario;
import mit.simulation.climate.model.persistence.ServerSimulation;
import mit.simulation.climate.model.persistence.ServerTuple;
import mit.simulation.climate.model.persistence.ServerVariable;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;



@Path("/")
@Produces("application/xml")
public class RootResource implements HttpSessionListener {

	public static Logger log = Logger.getLogger(RootResource.class);

	private Runner runner= new Runner();

	@Context
	SecurityContext security;

	@Context
	HttpServletRequest request;

	@GET
	@Path("/simulation")
	public Response getSimulationContent() {
		ServerRepository repo = ServerRepository.instance();
		ResponseWrapper wrapper = new ResponseWrapper();

		wrapper.add(repo.getAllPublicSimulations());

		Response r = Response.ok(
				new JAXBElement<ResponseWrapper>(new QName("payload"),
						ResponseWrapper.class, wrapper)).build();
		ServerRepository.instance().discard();
		return r;

	}

	@GET
	@Path("/scenario")
	public Response getScenarioContent() {
		request.getSession();
		log.info("Handle scenario request");
		ServerRepository repo = ServerRepository.instance();
		ResponseWrapper wrapper = new ResponseWrapper();

		wrapper.add(repo.getAllPublicScenarios());
		Response r = Response.ok(
				new JAXBElement<ResponseWrapper>(new QName("payload"),
						ResponseWrapper.class, wrapper)).build();
		ServerRepository.instance().discard();
		return r;

	}

	@GET
	@Path("/{type}/{id}")
	public Response getSimContent(@PathParam("id") String id,
			@PathParam("type") String type) {
		log.info("Handle request: type " + type + " id " + id);
		Object payload = null;
		if ("scenario".equals(type)) {
			payload = ServerRepository.instance().findScenario(id);
		} else if ("simulation".equals(type)) {
			payload = ServerRepository.instance().findSimulation(id);

		}

		if (payload == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		ResponseWrapper wrapper = new ResponseWrapper(Collections
				.singleton(payload));

		Response r = Response.ok(
				new JAXBElement<ResponseWrapper>(new QName("payload"),
						ResponseWrapper.class, wrapper)).build();
		ServerRepository.instance().discard();
		return r;

	}

	@POST
	@Path("/scenario")
	public Response handleCreateScenario(@Context SecurityContext security,
			@Context UriInfo uriinfo, @FormParam("simulation") String id,
			@FormParam("user") String user,
			@FormParam("description") String information,
			@FormParam("name") String name, @FormParam("state") String state) {

		Principal p = security.getUserPrincipal();
		log.info("Create scenario ("+p==null?("No user"):p + ")");
//		User u = p != null ? ServerRepository.instance().findUserByName(
//				p.getName()) : ServerRepository.instance().findUserByName("jintrone");
//
//				log.debug("Got user object "+u);
//		log.debug("Retrieved user "+u.getUsername());
//		if (u == null) {
//			log.warn("No user or user unauthorized");
//			return Response.status(Response.Status.UNAUTHORIZED).build();
//		}
		Simulation sim = ServerRepository.instance().findSimulation(id);
		if (sim == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		//TODO bug with id being assigned as Integer instead of String?  why?
		EntityState estate = state == null?null:EntityState.valueOf(state);
		ServerScenario scenario = new ServerScenario(sim,user,name,information,estate==null?EntityState.PUBLIC:estate);
		ServerRepository.instance().commit();
		String hackid = ""+scenario.dao.readProperty(scenario.dao.ID_PROPERTY);
		scenario.dao.setId(hackid);
		ServerRepository.instance().discard();
		return Response.seeOther(
				uriinfo.getBaseUriBuilder().path("scenario").path(
						scenario.getId()).build()).build();
	}






	@POST
	@Path("/scenariostate/{id}")
	public Response handleEditScenario(@Context UriInfo uriinfo, @PathParam("id") String id,
			@FormParam("name") String name,
			@FormParam("user") String user,
			@FormParam("description") String description,
			@FormParam("state") String action) {
		
	Principal p = security.getUserPrincipal();
	log.info("Edit scenario ("+p==null?("No user"):p + ")");
	//				User u = p != null ? ServerRepository.instance().findUserByName(
//				p.getName()) : ServerRepository.instance().findUserByName("jintrone");
//		if (u == null) {
//			log.warn("No user or user unauthorized");
//			return Response.status(Response.Status.UNAUTHORIZED).build();
//		}
		Scenario scenario = ServerRepository.instance().findScenario(id);
		if (scenario == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		EntityState state = action == null?null:EntityState.valueOf(action);
		if (state == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		scenario.setState(state);

		if (name!=null) scenario.setName(name);
		if (user!=null) scenario.setAuthor(user);
		if (description!=null) scenario.setDescription(description);

		//TODO bug with id being assigned as Integer instead of String?  why?

		ServerRepository.instance().discard();
		return Response.seeOther(uriinfo.getBaseUriBuilder().path("scenario").path(
						scenario.getId()).build()).build();
	}

	@POST
	@Path("/scenario/{id}/{metaid}")
	public Response handleAddDataToScenario(@Context SecurityContext security,
			@Context UriInfo uriinfo, @PathParam("id") String scenarioid,
			@PathParam("metaid") String metaid,
			@FormParam("user") String user,
			@FormParam("data") String data) {
		//
		 Principal p = security.getUserPrincipal();
		 log.info("Add data to scenario ("+p==null?("No user"):p + ")");
		 log.info("Data is:"+data);
		 
		 // User u = p != null ? ServerRepository.instance().findUserByName(
		// p.getName()) : null;
		// if (u == null) {
		// return Response.status(Response.Status.UNAUTHORIZED).build();
		// }
		Scenario scenario = ServerRepository.instance()
				.findScenario(scenarioid);
		MetaData md = ServerRepository.instance().findMetaData(metaid);
		Variable v = createVariableFromString(md, data);
		if (scenario.getSimulation().getInputs().contains(md)) {
			scenario.addToInput(v);
		} else
			scenario.addToOutput(v);

		ServerRepository.instance().discard();
		return Response.seeOther(
				uriinfo.getBaseUriBuilder().path("scenario").path(scenarioid)
						.build()).build();
	}

	@POST
	@Path("/simulation")
	public String handleCreateSimulation(@Context SecurityContext security,
			@Context UriInfo uriinfo, @FormParam("description") String information,
			@FormParam("name") String name, @FormParam("state") String action,
			@FormParam("url") String location) throws MalformedURLException{

		String serverInfo = "http://"+uriinfo.getBaseUri().getHost()+":"+uriinfo.getBaseUri().getPort();
		List<MetaData> empty = new ArrayList<MetaData>();
		EntityState estate = action == null?null:EntityState.valueOf(action);
		ServerSimulation sim = new ServerSimulation(name,information,
				new URL(location),
				empty, empty,estate==null?EntityState.PUBLIC:estate);
		log.info("Sim url is "+sim.getURL());
		ServerRepository.instance().commit();
		String hackid = ""+sim.dao.readProperty(sim.dao.ID_PROPERTY);
		sim.dao.setId(hackid);
		ServerRepository.instance().discard();
		return sim.getId();
	}

	@POST
	@Path("/compositesimulation")
	public String handleCreateCompositeSimulation(@Context HttpServletRequest request,
			@Context UriInfo uriinfo) throws URISyntaxException, IOException,
			FileUploadException {
		BufferedReader reader = null;
		String result = null;
		Map<String,String> fields = new HashMap<String,String>();
		if (ServletFileUpload.isMultipartContent(request)) {
			ServletFileUpload servletUpload = new ServletFileUpload(
					new DiskFileItemFactory());
			List items = null;
			items = servletUpload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem fileItem = (FileItem) iter.next();
				if (fileItem.isFormField()) {
					fields.put(fileItem.getFieldName(), fileItem.getString());
				} else {
					reader = new BufferedReader(new InputStreamReader(fileItem.getInputStream()));
				}
			}
			StringBuffer descriptor = new StringBuffer();

			if (reader !=null) {
				String line = null;
				while ((line = reader.readLine())!=null) {
					descriptor.append(line);
				}
			}
			result = descriptor.toString();


		}
		CompositeServerSimulation sim = new CompositeServerSimulation(fields.get("name"),fields.get("description"),result,EntityState.PUBLIC);
		ServerRepository.instance().commit();
		String hackid = ""+sim.dao.readProperty(sim.dao.ID_PROPERTY);
		sim.dao.setId(hackid);
		ServerRepository.instance().discard();
		return sim.getId();
	}

	@POST
	@Path("/simulationstate/{id}")
	public Response handleSimulationState(@Context UriInfo uriinfo, @PathParam("id") String id, @FormParam("state") String action) {
		
//		Principal p = security.getUserPrincipal();
//				User u = p != null ? ServerRepository.instance().findUserByName(
//				p.getName()) : ServerRepository.instance().findUserByName("jintrone");
//		if (u == null) {
//			log.warn("No user or user unauthorized");
//			return Response.status(Response.Status.UNAUTHORIZED).build();
//		}
		Simulation sim = ServerRepository.instance().findSimulation(id);
		if (sim == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		EntityState state = action == null?null:EntityState.valueOf(action);
		if (state == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		sim.setState(state);
		//TODO bug with id being assigned as Integer instead of String?  why?

		ServerRepository.instance().discard();
		return Response.seeOther(uriinfo.getBaseUriBuilder().path("simulation").path(
						sim.getId()).build()).build();
	}

	/*
	 * hack to set id for metadata objects
	 */
	private void hack(MetaDataDAO obj) {
		ServerRepository.instance().commit();
		String hackid = ""+obj.readProperty("id");
		obj.setId(hackid);
		ServerRepository.instance().discard();
	}

	@POST
	@Path("/createsim/{id}")
	public void handleAddDataToSimulation(@Context SecurityContext security,
		@Context UriInfo uriinfo, @PathParam("id") String id,
		@DefaultValue("input") @FormParam("type") String inputOrOutput,
		@DefaultValue("RANGE") @FormParam("type2") String varType,
		@DefaultValue("SCALAR") @FormParam("varcontext") String varContext,
		@DefaultValue("DOUBLE") @FormParam("datatype") String datatype,
		@DefaultValue("none") @FormParam("intName") String intName,
		@DefaultValue("none") @FormParam("description") String description,
		@DefaultValue("none") @FormParam("name") String name,
		@DefaultValue("none") @FormParam("units") String units,
		@FormParam("label") String label,
		@FormParam("min") String min,
		@FormParam("max") String max,
		@FormParam("categories") String categories,
		@FormParam("value") String value,
		@FormParam("external") String external){
		Simulation sim = ServerRepository.instance().findSimulation(id);
		List<ServerMetaData> inputsDB = new ArrayList<ServerMetaData>();
		List<ServerMetaData> outputsDB = new ArrayList<ServerMetaData>();
		VarType theType = VarType.valueOf(varType);
		VarContext theContext = VarContext.valueOf(varContext);


		Class clzz = classForString(datatype);
		if (theType == null || theContext ==null || clzz == null) {
			log.error("Invalid constants used");
			return;
		}


		if (inputOrOutput.equals("input")){
			//note - we are assuming inputs will either be categorical or scalar

			inputsDB.add(new ServerMetaData(name,
					intName,
					description,
					theType,
					theContext,
					new String[] {label},
					new String[] {units},
					new Class[] {clzz},
					min==null?null:new String[] {min},
					max==null?null:new String[] {max},
					categories==null?null:splitString(categories),
					value==null?null:new String[] {value},
					external));

		} else {
			outputsDB.add(new ServerMetaData(name,
					intName,
					description,
					theType,
					theContext,
					new String[] {label},
					new String[] {units},
					new Class[] {clzz},
					min==null?null:new String[] {min},
					max==null?null:new String[] {max},
					categories==null?null:splitString(categories),
					value==null?null:new String[] {value},
					external));
		}

		for (ServerMetaData m:inputsDB){
			hack(m.dao);
			sim.addInput(m);
		}
		for (ServerMetaData m:outputsDB){
			hack(m.dao);
			sim.addOutput(m);
		}
		ServerRepository.instance().discard();
	}

	private static Class classForString(String s) {
		return "STRING".equals(s)?String.class:
			"INTEGER".equals(s)?Integer.class:
				"DOUBLE".equals(s)?Double.class:null;
	}

	private static String[] splitString(String s) {
		return s.split(";");
	}

	/*
	 * update information for a given simulation
	 */
	@POST
	@Path("/editsim/{id}")
	public Response handleEditSimulationData(@Context SecurityContext security,
			@Context UriInfo uriinfo, @PathParam("id") String id,
			@FormParam("name") String name,
			@FormParam("description") String description,
			@FormParam("state") String action,
			@FormParam("url") String url){

		log.debug("Handling edit to simulation "+id);
//		Principal p = security.getUserPrincipal();
//				User u = p != null ? ServerRepository.instance().findUserByName(
//				p.getName()) : ServerRepository.instance().findUserByName("jintrone");
//		if (u == null) {
//			log.warn("No user or user unauthorized");
//			return Response.status(Response.Status.UNAUTHORIZED).build();
//		}
//		log.debug("Got user "+u);

		Simulation sim = ServerRepository.instance().findSimulation(id);
		if (sim == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		log.debug("Found simulation");

		if (name!=null) {sim.setName(name);}
		if(description!=null) {sim.setDescription(description);}
		EntityState state = null;
		if (action != null) {
			state = action == null?null:EntityState.valueOf(action);
			if (state == null) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			log.debug("Set state to "+state);
			sim.setState(state);
		}
		if (url!=null) {sim.setURL(url);}

		ServerRepository.instance().discard();
		return Response.seeOther(uriinfo.getBaseUriBuilder().path("simulation").path(
						sim.getId()).build()).build();
	}

	@POST
	@Path("/runsim")
	public Response runSimulation(@Context SecurityContext security,
			@Context UriInfo uriinfo,
			@FormParam("simId") String id,
			@FormParam("user") String user,
			MultivaluedMap<String,String> formparams) throws MalformedURLException {
		
		Principal p = security.getUserPrincipal();
		 log.info("Run simulation "+id+", User:"+user+"("+(p==null?"no principle":p.toString())+")");

//		User u = getRepository().findUserByName("jintrone");
//		if(u == null){
//			return Response.status(Status.UNAUTHORIZED).build();
//		}
		
		log.debug("Attempting to find simulation "+id);
		if (id == null) {
			log.warn("No valid id, can't run simulation");
			return Response.status(Status.BAD_REQUEST).build();
		}
		Simulation sim = ServerRepository.instance().findSimulation(id);
		formparams.remove("simId");
		formparams.remove("path");

		Map<String,Variable> varmap = new HashMap<String,Variable> ();
		for (Map.Entry<String, List<String>> ent:formparams.entrySet()) {
			varmap.put(ent.getKey(), createVariableFromForm(sim,ent.getKey(),ent.getValue().get(0)));
		}
		log.debug("Gathered inputs "+varmap);


		SimulationStepper stepper = ((ServerSimulation)sim).getSimulationStepper();

		while (stepper.hasNext()) {
			log.debug("Running simulation step");
			stepper.next().process(runner,varmap,null);
		}

		ServerScenario scenario = new ServerScenario(sim,user,"-tmp-"+sim.getName()+"-"+sim.getId(),"",EntityState.TEMPORARY);
		idhack(scenario);
		log.debug("Attempting to add "+varmap+" to scenario");
		addDataToScenario(varmap, scenario);

		ServerRepository.instance().commit();

		// xml document of scenario information
		return Response.seeOther(
				uriinfo.getBaseUriBuilder().path("scenario").path(scenario.getId())
						.build()).build();


	}

	private String executePost(Simulation sim, NameValuePair[] params) throws HttpCommunicationException {
		HttpClient client = new HttpClient();
		String BASE_URL = sim.getURL().toExternalForm();
		log.debug("Will be running sim at "+BASE_URL);
		PostMethod get = new PostMethod(BASE_URL);
		get.setRequestBody(params);
		get.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
		String response = null;

		try {
			client.executeMethod(get);
			byte[] responsebody = get.getResponseBody();
			response = new String(responsebody);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HttpCommunicationException("HttpException communicating with "+BASE_URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new HttpCommunicationException("IOException communicating with "+BASE_URL);
		} finally {
			get.releaseConnection();
		}
		log.debug("Response from simulation is: "+response);
		return response;
	}


	private Map<String,Variable> parseResponseString(Simulation sim, String data) {

		Map<String,String> vars = new HashMap<String,String>();
		Map<String,Variable> result = new HashMap<String,Variable>();



		Pattern p = Pattern.compile("(?:^|\\]\\])\\<([^(?:\\>\\[\\[)]*)\\>\\[\\[",Pattern.DOTALL);
		Matcher m = p.matcher(data);
		List<String> varnames = new ArrayList<String>();
		while (m.find()) {
			varnames.add(m.group(1));
		}
		log.debug("Got varnames "+varnames);

		p = Pattern.compile("\\>(\\[\\[(?:.*?)\\]\\])(?:\\<|$)",Pattern.DOTALL);
		m = p.matcher(data);

		Iterator<String> names = varnames.iterator();
		while (m.find()) {
			String value = m.group(1);
			log.debug("Got value "+value);
			vars.put(names.next(), value);

		}


		for (MetaData md:sim.getOutputs()){
			if (!vars.containsKey(md.getInternalName())) {
				log.warn("Could not find output variable "+md.getInternalName()+" in response from simulation");
				continue;
			}
			Variable v = createVariableFromString(md,vars.get(md.getInternalName()));
			result.put(md.getInternalName(), v);
		}
		return result;
	}


	private void addDataToScenario(Map<String,Variable> varmap, Scenario scenario) throws ArrayIndexOutOfBoundsException{
		for (MetaData m:scenario.getSimulation().getInputs()){
			String internalname = m.getInternalName();
			Variable v = varmap.get(internalname);
			log.debug("Adding "+v+" to inputs");
			scenario.addToInput(v);
		}
		for (MetaData m:scenario.getSimulation().getOutputs()){
			String internalname = m.getInternalName();
			Variable v = varmap.get(internalname);
			log.debug("Adding "+v+" to outputs for "+internalname);
			if (v == null) {
				log.debug("Variable is null; varmap is "+varmap);
				continue;
			}
			scenario.addToOutput(v);
		}
	}

	private Variable createVariableFromForm(Simulation sim, String internalname, String value) {
		log.debug("Setting "+internalname+" to "+value);
		MetaData targmd = null;
		for (MetaData md:sim.getInputs()) {
			if (internalname.equals(md.getInternalName())) {
				targmd = md;
			}
		}
		if (targmd == null) {
			log.warn("Unrecognized input parameter "+internalname+" in sim "+sim.getId()+"; skipping");
			return null;
		}
		ServerVariable result = new ServerVariable(targmd);
		idhack(result);
		result.addValue(new ServerTuple(new String[] {value}));
		return result;
	}
	private Variable createVariableFromString(MetaData md, String data) {
		ServerVariable result = new ServerVariable(md);
		idhack(result);
		data = data.substring(1, data.length() - 1);
		Pattern p = Pattern.compile("\\[([^\\[^\\]]+)\\]");
		Matcher m = p.matcher(data);
		while (m.find()) {
			String match = m.group(1);
			if (md.getProfile()[0].equals(java.lang.String.class)) {
				result.addValue(new ServerTuple(new String[] {match}));
			} else {
			result.addValue(new ServerTuple(MetaData.Utils.convertArray(match
					.split(","), md)));
			}
		}
		return result;
	}


	private ServerRepository getRepository() {
		return ServerRepository.instance();
	}



	private void idhack(ServerObject object) {
		ServerRepository.instance().commit();
		String id = ""+object.dao.readProperty("id");
		object.dao.writeProperty("id", id);
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		System.err.println("SIM:Session created "+arg0.getSession());

	}


	public void sessionDestroyed(HttpSessionEvent arg0) {
//		System.err.println("SIM:Session destroyed "+arg0.getSession());
//
//		Principal p = security.getUserPrincipal();
//		User u = p != null ? ServerRepository.instance().findUserByName(
//				p.getName()) : null;
		ServerRepository.instance().cleanTemporaryItems(null);
	}

	public static class HttpCommunicationException extends Exception {

		public HttpCommunicationException(String msg) {
			super(msg);
		}
	}


	/**
	 * Provides the simulation API with a means for running remote simulations. See {@link SimulationRunner}
	 *
	 * @author jintrone
	 *
	 */
	public class Runner implements SimulationRunner {

		@Override
		public Map<String, Variable> process(Simulation sim,
				Map<String, Variable> varinputs, Map<String, String> strinputs) {
			List<MetaData> inputs = sim.getInputs();
			MetaData fuzzy = null;
			String[] bounds;
			for (MetaData md:inputs) {
				String internalname = md.getInternalName();
				if (md.getVarType() == MetaData.VarType.FUZZY_DISCRETE) {
					fuzzy = md;
					break;
				}

			}
			if (fuzzy== null) {
				return _process(sim,varinputs,strinputs);
			}
			else {
				String value = getInputValue(fuzzy,strinputs,varinputs);
				if (fuzzy.getProfile()[0].equals(Integer.class)) {
					double dvalue = Double.parseDouble(value);
					if (dvalue == Math.floor(dvalue)) {
						return _process(sim,varinputs,strinputs);
					} else {
						strinputs.put(fuzzy.getInternalName(), Math.floor(dvalue)+"");
						Map<String,Variable> firstpass = _process(sim,varinputs,strinputs);
						strinputs.put(fuzzy.getInternalName(), Math.ceil(dvalue)+"");
						return combine(firstpass,_process(sim,varinputs,strinputs));
					}
				} else {
					return _process(sim,varinputs,strinputs);
				}
			}
		}

		public String getInputValue(MetaData md, Map<String,String> strinputs, Map<String, Variable> varinputs) {
			String internalname = md.getInternalName();
			String value = md.getDefault() == null?null:md.getDefault()[0];
			if (strinputs.containsKey(internalname)) {
				value = strinputs.get(internalname);
			} else if (varinputs.containsKey(internalname)) {
				List<Tuple> tuples = varinputs.get(internalname).getValue();
				if (tuples == null || tuples.size() == 0) {
					return value;
				} else if (tuples.get(0).getValues() == null || tuples.get(0).getValues().length == 0) {
					return value;
				} else {
					value = tuples.get(0).getValues()[0];
				}
			}

			return value;

		}

		public Map<String, Variable> _process(Simulation sim,
				Map<String, Variable> varinputs, Map<String, String> strinputs) {

			List<MetaData> inputs = sim.getInputs();
			NameValuePair[] params = new NameValuePair[inputs.size()];
			int i = 0;
			for (MetaData md:inputs) {
				params[i++] = new NameValuePair(md.getInternalName(),getInputValue(md,strinputs,varinputs));
			}
			String result = null;
			try {
				result = executePost(sim,params);
			} catch (HttpCommunicationException ex) {
				log.error("Error running simulation", ex);
				throw new RuntimeException("Couldn't run simualation");
			}

			return parseResponseString(sim,result);

		}

		private Map<String,Variable> combine(Map<String,Variable> existing, Map<String,Variable> toadd) {
			for (String var:existing.keySet()) {
				Variable evar = existing.get(var);
				Variable nvar = toadd.get(var);

				List<Tuple> tuplelist = nvar.getValue();
				for (Tuple t : tuplelist) {
					evar.addValue(new ServerTuple(t.getValues()));
					ServerRepository.instance().remove(((ServerTuple)t).dao);
				}
				ServerVariable sv = (ServerVariable) nvar;
				ServerRepository.instance().remove(sv.dao);

			}
			return existing;
		}

	}

}
