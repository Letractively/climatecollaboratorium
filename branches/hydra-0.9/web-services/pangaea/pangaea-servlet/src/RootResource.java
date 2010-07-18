import java.util.List;
import java.util.Set;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import org.climatecollaboratorium.pangaea.vensim.PangaeaConnection;
import org.climatecollaboratorium.pangaea.vensim.SimulationResults.ScalarElement;
import org.climatecollaboratorium.pangaea.vensim.SimulationInput;
import org.climatecollaboratorium.pangaea.vensim.SimulationResults;
import org.apache.log4j.Logger;
import org.climatecollaboratorium.pangaea.vensim.Variable;


@Path("/")
@Produces("text/plain")
public class  RootResource {

	public static Logger log = Logger.getLogger(RootResource.class);



	@POST       
	@Produces("text/plain")
	public Response runSimulation(
			@FormParam("Pct change in Developed FF emissions") Double devdchange,
			@FormParam("Pct change in Developing A FF emissions") Double devingchange,
			@FormParam("Pct change in Developing B FF emissions") Double nonchange,
			@FormParam("Global land use emissions change") Double landUseChange,
			@FormParam("Target Sequestration") Double targSequestration,
			@FormParam("Developed start year") Double devdStart,
			@FormParam("Developed target year") Double devdTarget,
			@FormParam("Developing A start year") Double devingAStart,
			@FormParam("Developing A target year") Double devingATarget,
			@FormParam("Developing B start year") Double devingBStart,
			@FormParam("Developing B target year") Double devingBTarget,
			@FormParam("Goal for CO2 in the atmosphere") Double co2inAtm) {

		SimulationInput input = new SimulationInput();
		input.setVariable(SimulationInput.InputVariable.DEVELOPED_FF_CHANGE, devdchange*100.0d);
		input.setVariable(SimulationInput.InputVariable.DEVELOPINGA_FF_CHANGE, devingchange*100.0d);
		input.setVariable(SimulationInput.InputVariable.DEVELOPINGB_FF_CHANGE,nonchange*100.0d);
		input.setVariable(SimulationInput.InputVariable.DEFORESTATION, landUseChange);
		input.setVariable(SimulationInput.InputVariable.AFFORESTATION,targSequestration);
		input.setVariable(SimulationInput.InputVariable.DEVELOPED_FF_START,devdStart);
		input.setVariable(SimulationInput.InputVariable.DEVELOPED_FF_TARGET,devdTarget);
		input.setVariable(SimulationInput.InputVariable.DEVELOPINGA_FF_START,devingAStart);
		input.setVariable(SimulationInput.InputVariable.DEVELOPINGA_FF_TARGET,devingATarget);
		input.setVariable(SimulationInput.InputVariable.DEVELOPINGB_FF_START,devingBStart);
		input.setVariable(SimulationInput.InputVariable.DEVELOPINGB_FF_TARGET,devingBTarget);
		//input.setVariable(SimulationInput.VensimVariable.AFFORESTATION,targSequestration);
		//input.setVariable(SimulationInput.VensimVariable.C, co2inAtm);

		PangaeaConnection connection = new PangaeaConnection();
		SimulationResults result = connection.submit(input);
		Response r = Response.ok(createTextResult(result)).build();

		return r;

	}

	private String createTextResult(SimulationResults result) {
		StringBuilder buffer = new StringBuilder();
		Set<Variable> vars = result.getPopulatedVariables();
		for (Variable v:result.getPopulatedVariables()) {
			buffer.append("<");
			buffer.append(v.getInternalName());
			buffer.append(">[");
			List<ScalarElement> vresult = result.get(v);
			for (ScalarElement e:vresult) {
				buffer.append(e.toString());
			}
			buffer.append("]");
		}
		return buffer.toString();
	}

}
