import java.security.Principal;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import mit.simulation.climate.dao.ScenarioDAO;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.User;
import mit.simulation.climate.model.Variable;
import mit.simulation.climate.model.persistence.ResponseWrapper;
import mit.simulation.climate.model.persistence.ServerObject;
import mit.simulation.climate.model.persistence.ServerRepository;
import mit.simulation.climate.model.persistence.ServerScenario;
import mit.simulation.climate.model.persistence.ServerTuple;
import mit.simulation.climate.model.persistence.ServerVariable;

import org.apache.log4j.Logger;

@Path("/")
@Produces("application/xml")
public class RootResource {

	public static Logger log = Logger.getLogger(RootResource.class);

	@GET
	@Path("/simulation")
	public Response getSimulationContent() {
		ServerRepository repo = ServerRepository.instance();
		ResponseWrapper wrapper = new ResponseWrapper();

		wrapper.add(repo.getAllSimulations());

		Response r = Response.ok(
				new JAXBElement<ResponseWrapper>(new QName("payload"),
						ResponseWrapper.class, wrapper)).build();
		ServerRepository.instance().discard();
		return r;

	}

	@GET
	@Path("/scenario")
	public Response getScenarioContent() {
		log.info("Handle scenario request");
		ServerRepository repo = ServerRepository.instance();
		ResponseWrapper wrapper = new ResponseWrapper();

		wrapper.add(repo.getAllScenarios());
		Response r = Response.ok(
				new JAXBElement<ResponseWrapper>(new QName("payload"),
						ResponseWrapper.class, wrapper)).build();
		ServerRepository.instance().discard();
		return r;

	}

	@GET
	@Path("/{type}/{id}")
	public Response getContent(@PathParam("id") String id,
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
			@FormParam("description") String information, @FormParam("name") String name) {
		log.debug("Handling scenario post");
		log.debug("Security is "+security);
		Principal p = security.getUserPrincipal();
		log.debug("Name of principal is "+p.getName());

		User u = p != null ? ServerRepository.instance().findUserByName(
				p.getName()) : ServerRepository.instance().findUserByName("jintrone");
		log.debug("Got user object "+u);
		log.debug("Retrieved user "+u.getUsername());
		if (u == null) {
			log.warn("No user or user unauthorized");
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		Simulation sim = ServerRepository.instance().findSimulation(id);
		if (sim == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		//TODO bug with id being assigned as Integer instead of String?  why?
		ServerScenario scenario = new ServerScenario(sim,u,name,information);
		ServerRepository.instance().commit();
		String hackid = ""+scenario.dao.readProperty(scenario.dao.ID_PROPERTY);
		scenario.dao.setId(hackid);
		ServerRepository.instance().discard();
		return Response.seeOther(
				uriinfo.getBaseUriBuilder().path("scenario").path(
						scenario.getId()).build()).build();
	}

	@POST
	@Path("/scenario/{id}/{metaid}")
	public Response handleAddDataToScenario(@Context SecurityContext security,
			@Context UriInfo uriinfo, @PathParam("id") String scenarioid,
			@PathParam("metaid") String metaid, @FormParam("data") String data) {
		//
		// Principal p = security.getUserPrincipal();
		// User u = p != null ? ServerRepository.instance().findUserByName(
		// p.getName()) : null;
		// if (u == null) {
		// return Response.status(Response.Status.UNAUTHORIZED).build();
		// }
		Scenario scenario = ServerRepository.instance()
				.findScenario(scenarioid);
		MetaData md = ServerRepository.instance().findMetaData(metaid);
		Variable v = createVariable(md, data);
		if (scenario.getSimulation().getInputs().contains(md)) {
			scenario.addToInput(v);
		} else
			scenario.addToOutput(v);

		ServerRepository.instance().discard();
		return Response.seeOther(
				uriinfo.getBaseUriBuilder().path("scenario").path(scenarioid)
						.build()).build();

	}

	private Variable createVariable(MetaData md, String data) {
		ServerVariable result = new ServerVariable(md);
		idhack(result);
		data = data.substring(1, data.length() - 1);
		Pattern p = Pattern.compile("\\[([^\\[^\\]]+)\\]");
		Matcher m = p.matcher(data);
		while (m.find()) {
			String match = m.group(1);
			result.addValue(new ServerTuple(MetaData.Utils.convertArray(match
					.split(","), md)));
		}
		return result;

	}

	// @POST
	// @Path("/simulation/")
	// public Response test(MultivaluedMap<String,String> test) {
	// log.info("Received a parameter "+test);
	// return Response.ok().build();
	// }
	@GET
	@Path("/user")
	public Response getUser(@Context SecurityContext security) {
		log.info("Looking for " + security.getUserPrincipal());
		Principal p = security.getUserPrincipal();
		User u = p != null ? ServerRepository.instance().findUserByName(
				p.getName()) : null;

		log.info("Found user " + u);
		if (u == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		UserResponseWrapper wrapper = new UserResponseWrapper(u, security
				.isUserInRole("admin"));
		Response r = Response.ok(
				new JAXBElement<UserResponseWrapper>(new QName("payload"),
						UserResponseWrapper.class, wrapper)).build();
		ServerRepository.instance().discard();
		return r;
	}


	private void idhack(ServerObject object) {
		ServerRepository.instance().commit();
		String id = ""+object.dao.readProperty("id");
		object.dao.writeProperty("id", id);
	}
	//
	// @POST
	// @Path("/scenario")
	// public Response handleCreateScenario(@Context SecurityContext security,
	// @Context UriInfo uriinfo,
	// @FormParam("simulation") String id,
	// @PathParam("name") String type,
	// @FormParam("description") String information) {
	//
	// return null;
	// }
	//
	//
	// @POST
	// @Path("/simulation")
	// public Response handleNewSimulation(@Context SecurityContext security,
	// @Context UriInfo uriinfo,
	// @PathParam("name") String type,
	// @FormParam("description") String information) {
	// log.info("Why am i posting?");
	// return null;
	// }
	//
	//
	//
	// @POST
	// @Path("/simulation/{id}/{type}")
	// public Response handleAddMetaDataToSimulation(@Context SecurityContext
	// security,
	// @Context UriInfo uriinfo,
	// @PathParam("id") String id,
	// @PathParam("type") String type,
	// @FormParam("name") String name,
	// @FormParam("internalname") String iname,
	// @FormParam("description") String description,
	// @FormParam("profile") String profile) {
	// return null;
	// }
	//
	// @POST
	// @Path("/simulation/{id}")
	// public Response handleAddMetaDataToSimulation(@Context SecurityContext
	// security,
	// @Context UriInfo uriinfo,
	// MultivaluedMap<String,String> map) {
	// return null;
	// }

}
