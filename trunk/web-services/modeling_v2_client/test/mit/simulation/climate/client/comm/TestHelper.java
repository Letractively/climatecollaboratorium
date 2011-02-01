package mit.simulation.climate.client.comm;

import mit.simulation.climate.client.Scenario;
import mit.simulation.climate.client.Simulation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jul 1, 2010
 * Time: 3:43:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestHelper {


    public static Scenario runComposite3region(ClientRepository repo, Long simId) throws IOException, ScenarioNotFoundException, ModelNotFoundException, MetaDataNotFoundException {
       Simulation sim = repo.getSimulation(simId);

        Map<String, Object> inputs = new HashMap<String, Object>();
        //developed
        inputs.put("Developed start year", "2012");  //start year
        inputs.put("Developed target year", "2050");  //target year
        inputs.put("Pct change in Developed FF emissions", "0");   //target

        //developing a
        inputs.put("Developing A start year", "2012");
        inputs.put("Developing A target year", "2050");
        inputs.put("Pct change in Developing A FF emissions", "0");

        //developing b
        inputs.put("Developing B start year", "2012");
        inputs.put("Developing B target year", "2050");
        inputs.put("Pct change in Developing B FF emissions", "144");


        inputs.put("Target Sequestration", "0.0");  //sequestration (afforestation)
        inputs.put("Global land use emissions change", "0.0");  //deforestation
        Scenario scenario = repo.runModelWithInputNames(sim, inputs, 1L, true);
        return scenario;
    }

    public static Scenario runCompositeTwo(ClientRepository repo) throws IOException, MetaDataNotFoundException, ScenarioNotFoundException, ModelNotFoundException {
        Simulation sim = repo.getSimulation(981L);

        Map<String, Object> inputs = new HashMap<String, Object>();
        //developed
        inputs.put("Developed_Nations_Change_Start_Year_input2", "2012");  //start year
        inputs.put("Developed_Nations_Change_Target_Year_input3", "2050");  //target year
         inputs.put("US_EU_Emissions_Change_input0","100"); //US & EU
         inputs.put("Other_Developed_Change_input1", "100");   //other developed change

        //developing a
        inputs.put("Developing_Nations_Start_Year_input6", "2012");
        inputs.put("Developing_Nations_Target_Year_input7", "2013");
        inputs.put("China_India_Emissions_Change_input4", "100"); //china, india
        inputs.put("Other_Developing_Change_input5","100"); //other developing change

        //developing b
        inputs.put("Other_Nations_Start_Year_input10","2012");
        inputs.put("Other_Nations_Target_Year_input11","2050");
        inputs.put("Bloc_A_Emissions_Change_input8", "100"); //bloc a
        inputs.put("Bloc_B_Emissions_Change_input9", "100"); //bloc b


        inputs.put("Target Sequestration", "0.50");  //sequestration (afforestation)
        inputs.put("Global land use emissions change", "0.50");  //deforestation

        inputs.put("Percent_Transfer_from_Developed_to_Developing_input0", "50"); //percent transfer from developed to developing
        Scenario scenario = repo.runModelWithInputNames(sim, inputs, 1L, true);
        return scenario;
    }

    public static Scenario runCompositeThree(ClientRepository repo) throws IOException, MetaDataNotFoundException, ScenarioNotFoundException, ModelNotFoundException {
        Simulation sim = repo.getSimulation(841L);

        Map<String, Object> inputs = new HashMap<String, Object>();
        //developed
        inputs.put("Developed_countries_start_year_input5", "2012");  //start year
        inputs.put("Developed_countries_target_year_input6", "2050");  //target year
        inputs.put("Rapidly_developing_countries_start_year_input13", "2012");
        inputs.put("Rapidly_developing_countries_target_year_input14", "2050");
         inputs.put("Other_developing_countries_start_year_input19", "2012"); //china, india
          inputs.put("Other_developing_countries_target_year_input20","2050");

        inputs.put("Rapidly_developing_Asia_emissions_change_input12", "50"); //china, india
        inputs.put("Middle_East_input15", "50"); //china, india
        inputs.put("Latin_America_input16", "50"); //china, india
        inputs.put("Africa_input17", "50"); //china, india
        inputs.put("Other_developing_Asia_input18", "50"); //china, india

        inputs.put("Canada_emissions_change_input4", "50"); //china, india
        inputs.put("China_emissions_change_input7", "50"); //china, india
        inputs.put("India_emissions_change_input8", "50"); //china, india
        inputs.put("Brazil_emissions_change_input9", "50"); //china, india
        inputs.put("South_Africa_emissions_change_input10", "50"); //china, india
        inputs.put("Mexico_emissions_change_input11", "50"); //china, india
        inputs.put("EU_emissions_change_input1", "50"); //china, india
        inputs.put("US_emissions_change_input0", "50"); //china, india
        inputs.put("OECD_Asia_emissions_change_input3", "50"); //china, india
        inputs.put("Russia_Former_Soviet_Union_emissions_change_input2", "50"); //china, india
                                                                                                                        



        inputs.put("Target Sequestration", "0.50");  //sequestration (afforestation)
        inputs.put("Global land use emissions change", "0.50");  //deforestation
        Scenario scenario = repo.runModelWithInputNames(sim, inputs, 1L, true);
        return scenario;
    }

}
