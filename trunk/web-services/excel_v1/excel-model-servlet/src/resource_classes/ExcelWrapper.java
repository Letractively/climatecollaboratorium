package resource_classes;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import excel_model.Input;
import excel_model.Model;
import excel_model.Output;

@Path("/")
@Produces("*/*")
@Consumes("*/*")
public class ExcelWrapper{
	
	Map<String,Double> newInputVals = new HashMap<String,Double>();
	
	@POST
	@Path("/createsim")
	@Consumes("*/*")
	public Response createSimFromExcel(@Context HttpServletRequest request) throws URISyntaxException {
		System.out.println("someone posted!!");
		InputStream file = null;
		if (ServletFileUpload.isMultipartContent(request)){
			ServletFileUpload servletUpload = new ServletFileUpload(new DiskFileItemFactory());
			List items = null;
			try {
				items = servletUpload.parseRequest(request);
				Iterator iter = items.iterator();
				while(iter.hasNext()){
					FileItem fileItem = (FileItem)iter.next();
					if(fileItem.isFormField()){
					} else {
						file = fileItem.getInputStream();
					}
				}
			} catch (IOException i){
				i.printStackTrace();
			} catch (org.apache.commons.fileupload.FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		Model model = new Model(file);
		
		HttpClient client = new HttpClient();
		String BASE_URL = "http://18.111.53.84:8080/simulation-servlet/rest/simulation";
		PostMethod post = new PostMethod(BASE_URL);
		NameValuePair[] simNameDescr = new NameValuePair[2];
		simNameDescr[0] = new NameValuePair("description","jacoby");
		simNameDescr[1] = new NameValuePair("name","jacoby");
		post.setRequestBody(simNameDescr);
		String simID = "";
		try {
			client.executeMethod(post);
			byte[] responseBody = post.getResponseBody();
			simID = new String(responseBody);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			post.releaseConnection();
		}
		
		String BASE_URL2 = "http://18.111.53.84:8080/simulation-servlet/rest/simulation/"+simID;
		String inputsAndOutputs = "";
		String inputs = "";
		String outputs = "";
		for (Input in:model.getInputs()){
			client = new HttpClient();
			post = new PostMethod(BASE_URL2);
			NameValuePair[] inputToSend = new NameValuePair[8];
			inputToSend[0] = new NameValuePair("name",in.getName());
			inputToSend[1] = new NameValuePair("description",in.getDescr());
			inputToSend[2] = new NameValuePair("intName",in.getIntName());
			inputToSend[3] = new NameValuePair("units",in.getUnits());
			inputToSend[4] = new NameValuePair("min",String.valueOf(in.getMin()));
			inputToSend[5] = new NameValuePair("max",String.valueOf(in.getMax()));
			inputToSend[6] = new NameValuePair("value",String.valueOf(in.getValue()));
			inputToSend[7] = new NameValuePair("type","input");
			post.setRequestBody(inputToSend);
			try {
				client.executeMethod(post);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
		for (Output out:model.getOutputs()){
			client = new HttpClient();
			post = new PostMethod(BASE_URL2);
			NameValuePair[] outputToSend = new NameValuePair[8];
			outputToSend[0] = new NameValuePair("name",out.getName());
			outputToSend[1] = new NameValuePair("description",out.getDescr());
			outputToSend[2] = new NameValuePair("intName",out.getIntName());
			outputToSend[3] = new NameValuePair("units",out.getUnits());
			outputToSend[4] = new NameValuePair("min",String.valueOf(out.getMin()));
			outputToSend[5] = new NameValuePair("max",String.valueOf(out.getMax()));
			outputToSend[6] = new NameValuePair("value",null);
			outputToSend[7] = new NameValuePair("type","output");
			post.setRequestBody(outputToSend);
			try {
				client.executeMethod(post);
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				post.releaseConnection();
			}
		}
//		return "done";
		return Response.seeOther(new URI("http://localhost:8080/simulation-servlet/rest/simulation")).build();	
	}
	
	
	@POST
	@Path("/wrapper")
	@Produces("text/plain")
	public String getJacobyOutputs(
			MultivaluedMap<String,String> formparams
			){
		
		// instantiate a new model
		InputStream input = ExcelWrapper.class.getResourceAsStream("jacoby_md.xls");
		Model model = new Model(input);
		
		// add the new input values to newInputVals and update model
		for (String s:formparams.keySet()){
			boolean isInput = false;
			for (Input p:model.getInputs()){
				if (p.getIntName().equals(s)){
					isInput = true;
				}
			}
			if (isInput){
				newInputVals.put(s, Double.valueOf(formparams.get(s).get(0)));
			}
		}
		model.update(newInputVals);
		
		// put outputs and parameters into one string that will be parsed
		// later
		String outputString = "";
		for (String s:newInputVals.keySet()){
			outputString += s+",[["+newInputVals.get(s)+"]];";
		}
		outputString = outputString.substring(0, outputString.length()-1)+",,,";
		
		for (int i=0;i<model.getOutputs().size();i++){
			Output output = model.getOutputs().get(i);
			String outputName = output.getIntName();
			ArrayList<Double> outputArray = output.getValues();
			outputString += outputName+",,[";
			int year = 2000;
			for (Double d:outputArray){
				outputString += "["+year+","+d+"]";
				year += 10;
			}
			outputString = outputString+"];";
		}
		return outputString.substring(0, outputString.length()-1);
	}
		
}
