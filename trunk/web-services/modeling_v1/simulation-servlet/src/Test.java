import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.persistence.ServerTuple;


public class Test {




	public static void main(String[] args) {
		//String s = "[[foo]]";
//		String s = "[[At least 10% of land species facing\n"+
//"extinction (according to one estimate). 80% bleaching of coral reefs, including Great Barrier Reef]]";

		String s = "<Temperature_Change>[[1.0]]<Land_Impacts>[[Permafrost thawing damages buildings and roads in parts of Canada and Russia]]<Abrupt_and_Large_Scale_Impacts>[[Atlantic Thermohaline Circulation starts to weaken]]<Food_Impacts>[[Modest increases in\n"+
"cereal yields in temperate regions]]<Water_Impacts>[[Small glaciers in the Andes  disappear completely, threatening water supplies for 50 million people]]<Environment_Impacts>[[At least 10% of land species facing\n"+
"extinction (according to one estimate). 80% bleaching of coral reefs, including Great Barrier Reef]]<Health_Impacts>[[At least 300,000 people each year die from climate related diseases (predominantly diarrhea, malaria, and malnutrition).  Reduction in winter mortality in higher latitudes (Northern Europe, USA)]]<Temperature_Change>[[1.0]]";

		Pattern p = Pattern.compile("\\>\\[(\\[(?:.*?)\\])\\](?:\\<|$)",Pattern.DOTALL);

		Matcher m = p.matcher(s);
		while (m.find()) {
			String varname = m.group(1);
			System.err.println(varname+":");
		}

	}

}
