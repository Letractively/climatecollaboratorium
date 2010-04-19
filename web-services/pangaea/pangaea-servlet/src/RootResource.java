import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import mit.simulation.climate.proxy.pangaea.PangaeaConnection;
import mit.simulation.climate.proxy.pangaea.SimulationInput;
import mit.simulation.climate.proxy.pangaea.SimulationResults;
import mit.simulation.climate.proxy.pangaea.SimulationResults.ScalarElement;
import mit.simulation.climate.proxy.pangaea.SimulationResults.Variable;

import org.apache.log4j.Logger;

@Path("/")
@Produces("application/text")
public class RootResource {

	public static Logger log = Logger.getLogger(RootResource.class);



	@POST
	public Response runSimulation(
			@FormParam("D_Pct change in Developed FF emissions") Double devdchange,
			@FormParam("D_Pct change in Developing A FF emissions") Double devingchange,
			@FormParam("D_Pct change in Developing B FF emissions") Double nonchange,
			@FormParam("D_Global land use emissions change") Double landUseChange,
			@FormParam("D_Target Sequestration") Double targSequestration,
			@FormParam("D_Developed start year") Double devdStart,
			@FormParam("D_Developed target year") Double devdTarget,
			@FormParam("D_Developing A start year") Double devingAStart,
			@FormParam("D_Developing A target year") Double devingATarget,
			@FormParam("D_Developing B start year") Double devingBStart,
			@FormParam("D_Developing B target year") Double devingBTarget,
			@FormParam("Goal for CO2 in the atmosphere") Double co2inAtm) {

		SimulationInput input = new SimulationInput();
		input.setVariable(SimulationInput.Variable.DEVELOPED_FF_CHANGE, devdchange*100.0d);
		input.setVariable(SimulationInput.Variable.DEVELOPINGA_FF_CHANGE, devingchange*100.0d);
		input.setVariable(SimulationInput.Variable.DEVELOPINGB_FF_CHANGE,nonchange*100.0d);
		input.setVariable(SimulationInput.Variable.DEFORESTATION, landUseChange);
		input.setVariable(SimulationInput.Variable.AFFORESTATION,targSequestration);
		input.setVariable(SimulationInput.Variable.DEVELOPED_FF_START,devdStart);
		input.setVariable(SimulationInput.Variable.DEVELOPED_FF_TARGET,devdTarget);
		input.setVariable(SimulationInput.Variable.DEVELOPINGA_FF_START,devingAStart);
		input.setVariable(SimulationInput.Variable.DEVELOPINGA_FF_TARGET,devingATarget);
		input.setVariable(SimulationInput.Variable.DEVELOPINGB_FF_START,devingBStart);
		input.setVariable(SimulationInput.Variable.DEVELOPINGB_FF_TARGET,devingBTarget);
		//input.setVariable(SimulationInput.Variable.AFFORESTATION,targSequestration);
		//input.setVariable(SimulationInput.Variable., co2inAtm);

		PangaeaConnection connection = new PangaeaConnection();
		SimulationResults result = connection.submit(input);
		Response r = Response.ok(createTextResult(result)).build();

		return r;

	}

	private String createTextResult(SimulationResults result) {
		StringBuffer buffer = new StringBuffer();
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
