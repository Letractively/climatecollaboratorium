package resource_classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import excel_model.Input;
import excel_model.Output;
import excel_model.parser.InputParse;
import excel_model.parser.ModelParse;
import excel_model.parser.OutputParse;
import excel_model.persistence.ServerRepository;
import excel_model.runner.InputRun;
import excel_model.runner.ModelRun;
import excel_model.runner.OutputRun;

@Path("/")
@Produces("*/*")
@Consumes("*/*")
public class RootResource {

	Map<String, String> newInputVals = new HashMap<String, String>();
	public static Logger log = Logger.getLogger(RootResource.class);

	// given an uploaded spread sheet, adds the simulation data to the data base
	// and returns an xml document of the simulation data to the client
	@SuppressWarnings("unchecked")
	@POST
	@Path("/createsim")
	public Response createSimFromExcel(@Context HttpServletRequest request,
			@Context UriInfo uriinfo) throws URISyntaxException, IOException,
			FileUploadException {
		// get server info from uriinfo

		String serverInfo = "http://" + uriinfo.getBaseUri().getHost() + ":"
				+ uriinfo.getBaseUri().getPort();
		log.debug("Server info: "+serverInfo);

		InputStream file = null;
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("name", "none-given");
		fields.put("description", "none-given");
		fields.put("worksheet", "0");


		if (ServletFileUpload.isMultipartContent(request)) {
			ServletFileUpload servletUpload = new ServletFileUpload(
					new DiskFileItemFactory());
			List items = null;
			items = servletUpload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem fileItem = (FileItem) iter.next();
				if (fileItem.isFormField()) {
					log.info("file item = " + fileItem.getString());
					if (fileItem.getString().equals("")) {
					} else {
						fields.put(fileItem.getFieldName(), fileItem
								.getString());
					}
				} else {
					file = fileItem.getInputStream();

				}
			}
		}
		log.debug("Received file "+file.toString());

		String filepath = "/var/excelserver/" + fields.get("name") + System.currentTimeMillis()
				+ ".xls";

		log.debug("Processing file");
		HSSFWorkbook wb = ModelParse.getWorkbook(file);
		Collection<ModelParse> allModels = ModelParse.parseWorkbook(wb, fields
				.get("name"), filepath);
		log.debug("Successfully parsed");


		log.debug("Start commit excel workbook");
		ServerRepository.instance().commit();
		log.debug("Succeful commit excel workbook");

		log.debug("Save file");
		OutputStream outputFile = null;
		try {
			outputFile = new FileOutputStream(filepath);
			wb.write(outputFile);
		} finally {
			outputFile.close();
			file.close();
		}
		log.debug("Successfully saved file");


		//begin create simulation


		Iterator<ModelParse> models = allModels.iterator();
		String simID = "";
		while (models.hasNext()) {
			ModelParse model = models.next();
			int theId = model.getId();
			log.debug("Retrived a model id "+theId);
			HttpClient client = new HttpClient();
			String BASE_URL = serverInfo
					+ "/simulation-servlet/rest/simulation";
			String excel_sim_url = serverInfo
					+ "/excel_wrapper-servlet/rest/wrapper/" + theId;
			log.debug("Assigning excel simulation url :" + excel_sim_url);
			log.debug("Post model details to "+BASE_URL);
			PostMethod post = new PostMethod(BASE_URL);
			NameValuePair[] simNameDescr = new NameValuePair[4];
			simNameDescr[0] = new NameValuePair("description", fields
					.get("description"));
			simNameDescr[1] = new NameValuePair("name", fields.get("name"));
			simNameDescr[2] = new NameValuePair("state", "TEMPORARY");
			simNameDescr[3] = new NameValuePair("url", excel_sim_url);

			post.setRequestBody(simNameDescr);
			try {
				client.executeMethod(post);
				byte[] responseBody = post.getResponseBody();
				simID = new String(responseBody);
				log.debug("Got a simulation id "+simID);
			} catch (HttpException e) {
				log.warn("Couldn't execute post to /rest/simulation");
				return Response.serverError().build();
			} catch (IOException e) {
				log.warn("Couldn't execute post to /rest/simulation");
				return Response.serverError().build();
			} finally {
				post.releaseConnection();
			}
			// given the simulation id from BASE_URL, add the Input meta data to
			// the data base;
			// make sure to release HttpClient connection every time
			String BASE_URL2 = serverInfo
					+ "/simulation-servlet/rest/createsim/" + simID;
			for (Input in : model.getInputs()) {
				InputParse inparse = (InputParse) in;
				client = new HttpClient();
				post = new PostMethod(BASE_URL2);
				NameValuePair[] inputToSend = new NameValuePair[14];
				inputToSend[0] = new NameValuePair("name", inparse.getName());
				inputToSend[1] = new NameValuePair("description", inparse
						.getDescription());
				inputToSend[2] = new NameValuePair("intName", inparse
						.getInternalName());
				inputToSend[3] = new NameValuePair("units", inparse.getUnits());
				inputToSend[4] = new NameValuePair("min", String
						.valueOf(inparse.getMin()));
				inputToSend[5] = new NameValuePair("max", String
						.valueOf(inparse.getMax()));
				inputToSend[6] = new NameValuePair("value", String
						.valueOf(inparse.getValue()));
				inputToSend[7] = new NameValuePair("type", "input");
				inputToSend[8] = new NameValuePair("type2", inparse.getType());
				inputToSend[9] = new NameValuePair("varcontext", inparse
						.getContext());
				inputToSend[10] = new NameValuePair("datatype", inparse
						.getDatatype());
				inputToSend[11] = new NameValuePair("categories", inparse
						.getCategory());
				inputToSend[12] = new NameValuePair("external", inparse
						.getExternal());
				inputToSend[13] = new NameValuePair("label", inparse.getLabel());
				post.setRequestBody(inputToSend);
				try {
					client.executeMethod(post);
				} catch (HttpException e) {
					log.warn("Couldn't add input metadata");
					return Response.serverError().build();
				} catch (IOException e) {
					log.warn("Couldn't add input metadata");
					return Response.serverError().build();
				} finally {
					post.releaseConnection();
				}
			}

			// add Output metadata to data base
			for (Output out : model.getOutputs()) {
				OutputParse outparse = (OutputParse) out;
				client = new HttpClient();
				post = new PostMethod(BASE_URL2);
				NameValuePair[] outputToSend = new NameValuePair[14];
				outputToSend[0] = new NameValuePair("name", outparse.getName());
				outputToSend[1] = new NameValuePair("description", outparse
						.getDescription());
				outputToSend[2] = new NameValuePair("intName", outparse
						.getInternalName());
				outputToSend[3] = new NameValuePair("units", outparse
						.getUnits());
				outputToSend[4] = new NameValuePair("min", String
						.valueOf(outparse.getMin()));
				outputToSend[5] = new NameValuePair("max", String
						.valueOf(outparse.getMax()));
				outputToSend[6] = new NameValuePair("value", null);
				outputToSend[7] = new NameValuePair("type", "output");
				outputToSend[8] = new NameValuePair("type2", outparse.getType());
				outputToSend[9] = new NameValuePair("varcontext", outparse
						.getContext());
				outputToSend[10] = new NameValuePair("datatype", outparse
						.getDatatype());
				outputToSend[11] = new NameValuePair("categories", outparse
						.getCategory());
				outputToSend[12] = new NameValuePair("external", outparse
						.getExternal());
				outputToSend[13] = new NameValuePair("label", outparse
						.getLabel());
				post.setRequestBody(outputToSend);
				try {
					client.executeMethod(post);
				} catch (HttpException e) {
					log.warn("Couldn't add output metadata");
					return Response.serverError().build();
				} catch (IOException e) {
					log.warn("Couldn't add output metadata");
					return Response.serverError().build();
				} finally {
					post.releaseConnection();
				}
			}
		}
		return Response.seeOther(
				new URI(serverInfo + "/simulation-servlet/rest/simulation/"
						+ simID)).build();
	}

	@POST
	@Path("/wrapper/{id}")
	@Produces("text/plain")
	public String runSimulation(MultivaluedMap<String, String> formparams,
			@PathParam("id") String id) throws IOException {
		// instantiate new ModelRun, put all input values in workbook, get
		// inputs and outputs
		ModelRun model = new ModelRun(ServerRepository.instance().findModelById(id));
		for (String s : formparams.keySet()) {
			String value = formparams.get(s).get(0);
			log.debug("Input param: " + s + "," + value);
			newInputVals.put(s, value);
		}

		model.inputNewVals(newInputVals);
		model.getOutputsFromWorkbook();
		model.getInputsFromWorkbook();

		// add inputs to outputString
		String outputString = "";
		for (Input i : model.getInputs()) {
			InputRun irun = (InputRun) i;
			outputString += "<"+i.getInternalName() + ">[[" + irun.getValue() + "]]";
		}
			// add outputs to outputString
		outputString += convertOutputsToString(model);

		// add second set of outputs if 'fuzzy range'
		log.debug(outputString);
		return outputString;
	}

	

	private static String convertOutputsToString(ModelRun model) {
		// List<String> increments = model.getIncrements();
		StringBuffer result = new StringBuffer();
		for (Output output : model.getOutputs()) {
			OutputRun orun = (OutputRun) output;
			result.append("<"+orun.getInternalName() + ">[");
			for (String val : orun.getValues()) {
				result.append("[" + val + "]");
			}
			result.append("]");
		}
		return result.toString();
	}

	@GET
	@Path("/login")
	public String loginUser() {
		return "";
	}
}
