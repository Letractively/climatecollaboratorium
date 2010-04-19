package mit.simulation.climate.proxy.pangaea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mit.simulation.climate.proxy.pangaea.SimulationInput.Variable;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class PangaeaConnection {

	private static Logger log = Logger.getLogger(PangaeaConnection.class);

	private static String BASE_URL = "http://forio.com/simulation/climate-development/";
	private static String LOGIN_KEY = "FD_loginid";
	private static String login = "pangaeasim";
	private static String PASSWD_KEY = "FD_password";
	private static String password = "globalclimate";

	private static Pattern pattern = Pattern.compile("\\s*\\[(.+?)\\][\\s,]*");

	private HttpClient client = new HttpClient();

	public PangaeaConnection() {
		login();
	}

	public boolean login() {
//		NameValuePair[] queryparams = new NameValuePair[] {
//				new NameValuePair(LOGIN_KEY, login),
//				new NameValuePair(PASSWD_KEY, password) };
//		if (doGet(queryparams) != null) {
//			return true;
//		} else
//			return false;
		return true;
	}

	public SimulationResults submit(SimulationInput input) {
		NameValuePair[] queryparams = new NameValuePair[input.getAllVariables()
				.size() + 5];
		int idx = 0;
		queryparams[idx++] = new NameValuePair("run-sim", "submit");
		queryparams[idx++] = new NameValuePair("", "delete_runs");
		queryparams[idx++] = new NameValuePair("FD_action", "reset_vars");
		queryparams[idx++] = new NameValuePair("FD_run_name", "Run 1");
		queryparams[idx++] = new NameValuePair("FD_session", "2790123");

		for (Variable v : input.getAllVariables().keySet()) {
			log.debug("Set " + v.internalName() + "="
					+ input.getValue(v).toString());
			queryparams[idx++] = new NameValuePair(v.internalName(), input
					.getValue(v).toString());
		}

		String result = doPost(queryparams);

		return parseResult(result);
	}

	private SimulationResults parseResult(String results) {
		SimulationResults simresult = new SimulationResults();

		String[] lines = results.split("\n");
		boolean found = false;
		List<SimulationResults.Variable> variables = new ArrayList<SimulationResults.Variable>();
		variables.addAll(Arrays.asList(SimulationResults.Variable.values()));
		for (String line : lines) {
			if (variables.size() == 0) {
				log.debug("Out of variables");
				break;
			}
			if (line.matches("\\s*data:\\[\\s*")) {
				log.debug("Found "+line);
				found = true;
				continue;
			}
			if (found) {
				Matcher m = pattern.matcher(line);
				log.debug("Found line "+line);
				if (m.matches()) {
					log.debug("Got data "+m.group(1));
					String[] vals = m.group(1).split("\\s*,\\s*");

					SimulationResults.Variable v = variables.remove(0);
					log.debug("Adding to variable "+v.name);
					simresult.addDataPoints(v, vals);

				} else if (line.matches("\\s+")) {
					continue;
				} else {
					found = false;
				}
			}
		}
		simresult.createIndexFor(SimulationResults.Variable.DEVELOPED_COUNTRIES_FF_EMISSIONS, 2000, 2100);
		return simresult;
	}

	public String doPost(NameValuePair[] queryparams) {
		PostMethod get = new PostMethod(BASE_URL);
		get.setRequestBody(queryparams);
		// Execute the method.
		String result = null;
		try {
			int statusCode = client.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				log.warn("Method failed: " + get.getStatusLine());
				return null;
			}

			// Read the response body.
			byte[] responseBody = get.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			result = new String(responseBody);

		} catch (HttpException e) {
			log.error("Fatal protocol violation: " + e.getMessage(), e);
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			log.error("Fatal transport error: " + e.getMessage(), e);
			e.printStackTrace();
			return null;
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
		return result;
	}

	public String doGet(NameValuePair[] queryparams) {
		GetMethod get = new GetMethod(BASE_URL);
		get.setQueryString(queryparams);
		// Execute the method.
		String result = null;
		try {
			int statusCode = client.executeMethod(get);

			if (statusCode != HttpStatus.SC_OK) {
				log.warn("Method failed: " + get.getStatusLine());
				return null;
			}

			// Read the response body.
			byte[] responseBody = get.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			result = new String(responseBody);

		} catch (HttpException e) {
			log.error("Fatal protocol violation: " + e.getMessage(), e);
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			log.error("Fatal transport error: " + e.getMessage(), e);
			e.printStackTrace();
			return null;
		} finally {
			// Release the connection.
			get.releaseConnection();
		}
		return result;
	}

	public static void main(String[] args) {
		PangaeaConnection con = new PangaeaConnection();
		SimulationInput input = new SimulationInput();
		input.setVariable(SimulationInput.Variable.DEVELOPED_FF_CHANGE, 0.1);
		input.setVariable(SimulationInput.Variable.DEVELOPINGA_FF_CHANGE, 0.1);
		log.debug(con.submit(input));

//		String = "[1, 2, 3, 4]

	}

}
