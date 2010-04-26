package mit.simulation.climate.model.persistence;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.dao.ScenarioDAO;
import mit.simulation.climate.dao.SimulationDAO;
import mit.simulation.climate.dao.TupleDAO;
import mit.simulation.climate.dao.UserDAO;
import mit.simulation.climate.dao.VariableDAO;
import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;

import mit.simulation.climate.model.Variable;
import mit.simulation.climate.model.MetaData.VarContext;
import mit.simulation.util.CsvDumper;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.conf.ServletUtil;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.apache.log4j.Logger;

public class ServerRepository {

	private static Logger logger = Logger.getLogger(ServerRepository.class);

	private Map<CayenneDataObject,ServerObject<?>> cache = new HashMap<CayenneDataObject,ServerObject<?>>();

	private DataContext context;

	private static ServerFactory factory  = ServerFactory.instance();

	private static Map<DataContext,ServerRepository> existing_repositories = new HashMap<DataContext,ServerRepository>();

	private static ServerRepository instance = null;

	private static Thread cleaner = new Thread();

	private static long HOUR = 1000*60*60;

	private ServerRepository(DataContext context) {
		this.context = context;
		initCleanerThread();
	}

	private void initCleanerThread() {
		//run once on startup
		cleanAllTemporaryItems(System.currentTimeMillis()-HOUR);
		cleaner = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.currentThread().sleep(HOUR);
					} catch (InterruptedException e) {
						//ignore
					}
					cleanAllTemporaryItems(System.currentTimeMillis()-HOUR);
				}
			}
		});
		cleaner.start();
	}

	public static ServerRepository instance() {
		if (instance == null) {
			instance = new ServerRepository(DataContext.createDataContext());
		}
		return instance;
	}

	public void commit() {
		context.commitChanges();
	}


	public void discard() {
		commit();
	}



	private static DataContext getContextCandidate() {
		DataContext result = null;
		try {
		    result = DataContext.getThreadDataContext();
		}
		catch(IllegalStateException ex) {
		    result = DataContext.createDataContext();
		    DataContext.bindThreadDataContext(result);
		}
		return result;
	}

	public DataContext getContext() {
		return context;
	}

	public  void idhack(ServerObject object) {
		ServerRepository.instance().commit();
		String id = ""+object.dao.readProperty("id");
		object.dao.writeProperty("id", id);
	}

	public <T>T createDAO(Class<T> cls) {
		T result = (T)context.newObject(cls);
		return result;
	}

	public void remove(CayenneDataObject dataObject) {
		cache.remove(dataObject);
		context.deleteObject(dataObject);
	}


	public <S>S findDAO(Class<S> cls, String id) {
		S result = DataObjectUtils.objectForPK(context, cls, id);
		return result;
	}

	public void putCache(CayenneDataObject dao, ServerObject<?> serverobject) {
		//cache.put(dao, serverobject);
	}

	public ServerMetaData get(MetaDataDAO dao) {
		if (dao==null) return null;
		ServerMetaData result = (ServerMetaData)cache.get(dao);
		if (result == null) {
			result = (ServerMetaData) factory.get(dao);
			putCache(dao,result);
			//cache.put(dao, result);
		}
		return result;
	}

	public ServerTuple get(TupleDAO dao) {
		if (dao==null) return null;
		ServerTuple result = (ServerTuple)cache.get(dao);
		if (result == null) {
			result = (ServerTuple) factory.get(dao);
			putCache(dao,result);
			//cache.put(dao, result);
		}
		return result;
	}



	public synchronized ServerScenario get(ScenarioDAO dao) {
		if (dao==null) return null;
		ServerScenario result = (ServerScenario)cache.get(dao);
		if (result == null) {
			result = (ServerScenario) factory.get(dao);
			putCache(dao,result);

			//cache.put(dao, result);
		}
		return result;
	}

	public ServerVariable get(VariableDAO dao) {
		if (dao==null) return null;
		ServerVariable result = (ServerVariable)cache.get(dao);
		if (result == null) {
			result = (ServerVariable) factory.get(dao);
			putCache(dao,result);

			//cache.put(dao, result);
		}
		return result;
	}

	public ServerSimulation get(SimulationDAO dao) {
		if (dao==null) return null;
		ServerSimulation result = (ServerSimulation)cache.get(dao);
		if (result == null) {
			result = (ServerSimulation) factory.get(dao);
			putCache(dao,result);

			//cache.put(dao, result);
		}
		return result;
	}




	public synchronized void cleanAllTemporaryItems(long since) {

		Expression e = ExpressionFactory.matchExp(ScenarioDAO.STATE_PROPERTY, EntityState.TEMPORARY.toString()).andExp(ExpressionFactory.lessExp(ScenarioDAO.CREATION_PROPERTY, new Date(since)));
		SelectQuery query = new SelectQuery(ScenarioDAO.class,e);
		List<ScenarioDAO> scenarios = (List<ScenarioDAO>)context.performQuery(query);
		logger.info("Cleaning "+scenarios.size()+" scenarios");
		context.deleteObjects(scenarios);

		e = ExpressionFactory.matchExp(SimulationDAO.STATE_PROPERTY, EntityState.TEMPORARY.toString()).andExp(ExpressionFactory.lessExp(SimulationDAO.CREATION_PROPERTY, new Date(since)));
		query = new SelectQuery(ScenarioDAO.class,e);
		List<SimulationDAO> sims = (List<SimulationDAO>)context.performQuery(query);
		logger.info("Cleaning "+sims.size()+" scenarios");
		context.deleteObjects(sims);


		this.commit();
	}

	public synchronized void cleanTemporaryItems(String u) {
		Expression e = ExpressionFactory.matchExp(ScenarioDAO.STATE_PROPERTY, EntityState.TEMPORARY.toString());
		if (u!=null) e = e.andExp(
				ExpressionFactory.matchExp(ScenarioDAO.USER_PROPERTY, u));
		SelectQuery query = new SelectQuery(ScenarioDAO.class,e);
		List<ScenarioDAO> scenarios = (List<ScenarioDAO>)context.performQuery(query);
		logger.info("Cleaning "+scenarios.size()+" scenarios");
		context.deleteObjects(scenarios);
		this.commit();
	}

	public	Collection<Scenario> getAllPublicScenarios() {
		SelectQuery query = new SelectQuery(ScenarioDAO.class,ExpressionFactory.matchExp(ScenarioDAO.STATE_PROPERTY, EntityState.PUBLIC.toString()));
		List<ScenarioDAO> scenarios = (List<ScenarioDAO>)context.performQuery(query);
		List<Scenario> result = new ArrayList<Scenario>();
		for (ScenarioDAO dao:scenarios) {
			result.add(ServerFactory.instance().get(dao));
		}
		return result;
	}

	public	Collection<Scenario> getAllScenarios() {
		SelectQuery query = new SelectQuery(ScenarioDAO.class);
		List<ScenarioDAO> scenarios = (List<ScenarioDAO>)context.performQuery(query);
		List<Scenario> result = new ArrayList<Scenario>();
		for (ScenarioDAO dao:scenarios) {
			result.add(ServerFactory.instance().get(dao));
		}
		return result;
	}

	public Collection<Simulation> getAllPublicSimulations() {
		SelectQuery query = new SelectQuery(SimulationDAO.class,ExpressionFactory.matchExp(ScenarioDAO.STATE_PROPERTY, EntityState.PUBLIC.toString()));
		List<SimulationDAO> simulations = (List<SimulationDAO>)context.performQuery(query);
		List<Simulation> result = new ArrayList<Simulation>();
		for (SimulationDAO dao:simulations) {
			result.add(ServerFactory.instance().get(dao));
		}
		return result;
	}

	public	Collection<Simulation> getAllSimulations() {
		SelectQuery query = new SelectQuery(SimulationDAO.class);
		List<SimulationDAO> simulations = (List<SimulationDAO>)context.performQuery(query);
		List<Simulation> result = new ArrayList<Simulation>();
		for (SimulationDAO dao:simulations) {
			result.add(ServerFactory.instance().get(dao));
		}
		return result;
	}

	public Simulation findSimulation(String id) {
		SimulationDAO dao = findDAO(SimulationDAO.class,id);
		Simulation result = null;
		if (dao != null) {
			result = get(dao);
		}
		return result;
	}

	public Simulation findSimulationByName(String name) {
		SelectQuery query = new SelectQuery(SimulationDAO.class,ExpressionFactory.matchExp("name", name));
		List result = context.performQuery(query);
		if (result != null && result.size() > 0) {
			return get((SimulationDAO)result.get(0));
		}
		return null;
	}

	public Scenario findScenario(String id) {
		logger.info("Find scenario for id "+id);
		ScenarioDAO dao = findDAO(ScenarioDAO.class,id);
		logger.info("Got scenario "+dao);
		Scenario result = null;
		if (dao != null) {
			result = get(dao);
		}
		return result;
	}

	public Scenario findScenarioByName(String name) {
		SelectQuery query = new SelectQuery(ScenarioDAO.class,ExpressionFactory.matchExp("name", name));
		List result = context.performQuery(query);
		if (result != null && result.size() > 0) {
			return get((ScenarioDAO)result.get(0));
		}
		return null;
	}

	public MetaData findMetaData(String id) {
		MetaDataDAO dao = findDAO(MetaDataDAO.class,id);
		MetaData result = null;
		if (dao != null) {
			result = get(dao);
		}
		return result;
	}

	public MetaData findMetaDataByVarName(String name) {
		SelectQuery query = new SelectQuery(MetaDataDAO.class,ExpressionFactory.matchExp("internalname", name));
		List result = context.performQuery(query);
		if (result != null && result.size() > 0) {
			return get((MetaDataDAO)result.get(0));
		}
		return null;

	}




//	public static void buildCROADS() throws MalformedURLException {
////		User user = ServerRepository.instance().findUserByName("jintrone");
//
//		MetaData devedin = new ServerMetaData("Developed countries fossil fuel emissions change",
//						"D_Developed ME change",
//						"Fossil fuel emissions for many of the most developed nations with respect to 2008 levels. US, EU (27 countries), Russia and former Soviet States, Japan, Canada, South Korea, and Australia.",
//						Type.SCALAR,
//						new String[] {"Fossil Fuel Emissions"},
//						new String[] {"Emissions relative to 2008"},
//						new Class[] {Double.class},
//						new Number[] {0d},
//						new Number[] {2.0d},
//						null, null);
//		MetaData devingin = new ServerMetaData("Developing countries fossil fuel emissions change",
//				"D_Developing ME change",
//				"Fossil fuel emssions for many of the fastest developing and larger nations with respect to 2008 levels. China, India, South Africa, Mexico, Brazil, and Indonesia.",
//				Type.SCALAR,
//				new String[] {"Fossil Fuel Emissions"},
//				new String[] {"Emissions relative to 2008"},
//				new Class[] {Double.class},
//				new Number[] {0d},
//				new Number[] {2.0d},
//				null, null);
//		MetaData otherin = new ServerMetaData("Other countries fossil fuel emissions change",
//				"D_Non ME change",
//				"Fossil fuel emissions for other countries with respect to 2008 levels.",
//				Type.SCALAR,
//				new String[] {"Fossil Fuel Emissions"},
//				new String[] {"Emissions relative to 2008"},
//				new Class[] {Double.class},
//				new Number[] {0d},
//				new Number[] {3.0d},
//				null, null);
//		MetaData deforestin = new ServerMetaData("Reduction of land use emissions",
//				"D_Land use emissions change",
//				"Rate of deforestation indexed to land use emission change. This value is the indexed annual fractional reduction of land use emissions between 2008 and 2050. Projected emissions are calibrated to be consistent with IPCC projections for various scenarios and indexed from 0 to 1. A value of 0 yields a 90% drop by 2050, .3 yields a 47% drop, .64 yields a constant value, and 1 yields a 52% increase. In the real world, this would depend on success or failure of policies and incentives to change the conversion of forests to agricultural land.",
//				Type.SCALAR,
//				new String[] {"Deforestation"},
//				new String[] {"Indexed Fractional Reduction"},
//				new Class[] {Double.class},
//				new Number[] {0d},
//				new Number[] {1.0d},
//				null, null);
//		MetaData afforestin = new ServerMetaData("Carbon sequestration due to treegrowth",
//				"D_Target Sequestration",
//				"Rate of CO2 sequestration due to treegrowth, starting in 2008 and continuing until 2050, ranging from 0 to 1. A value of 1 delivers the IPCC estimated maximum of 1.6 billion tons Carbon per year; .5 delivers half of the maximum. In the real world, this would be success in increasing the amount of land in forests. Removals are not permanent, some CO2 returns to the atmosphere.",
//				Type.SCALAR,
//				new String[] {"Afforestation"},
//				new String[] {"Percentage Maximum Removal"},
//				new Class[] {Double.class},
//				new Number[] {0d},
//				new Number[] {1.0d},
//				null, null);
//
//		MetaData devedFFout = new ServerMetaData("Developed countries fossil fuel emissions",
//				"DevelopedFossilFuelEmissions",
//				"Amount of emissions per year for developed countries including US, EU (27 countries), Russia and former Soviet States, Japan, Canada, South Korea, and Australia.",
//				Type.ORDINAL,
//				new String[] {"Year","Fossil Fuel Emissions"},
//				new String[] {"Year","10^9 tons"},
//				new Class[] {Integer.class,Double.class},
//				null,
//				null,
//				null, null);
//
//		MetaData devingFFout = new ServerMetaData("Developing countries fossil fuel emissions",
//				"Developing Fossil Fuel Emissions",
//				"Amount of emissions per year for developing countries including China, India, South Africa, Mexico, Brazil, and Indonesia.",
//				Type.ORDINAL,
//				new String[] {"Year","Fossil Fuel Emissions"},
//				new String[] {"Year","10^9 tons"},
//				new Class[] {Integer.class,Double.class},
//				null,
//				null,
//				null, null);
//		MetaData otherFFout = new ServerMetaData("Other countries fossil fuel emissions",
//				"NondevelopingFossilFuelEmissions",
//				"Amount of emissions per year for other countries.",
//				Type.ORDINAL,
//				new String[] {"Year","Fossil Fuel Emissions"},
//				new String[] {"Year","10^9 tons"},
//				new Class[] {Integer.class,Double.class},
//				null,
//				null,
//				null, null);
//		MetaData atmosFFout = new ServerMetaData("Atmospheric CO2 Concentration",
//				"AtmosphericCO2Concentration",
//				"Concentration of CO2 in the atmosphere each year.",
//				Type.SCALAR,
//				new String[] {"Year","Carbon Concentration"},
//				new String[] {"Year","ppm"},
//				new Class[] {Integer.class,Double.class},
//				null,
//				null,
//				null, null);
//
//		List<MetaData> inputs = new ArrayList<MetaData>();
//		Collections.addAll(inputs, devedin, devingin, otherin, deforestin, afforestin);
//
//		List<MetaData> outputs = new ArrayList<MetaData>();
//		Collections.addAll(outputs, devedFFout, devingFFout, otherFFout, atmosFFout);
//
//		Simulation sim = new ServerSimulation("C-ROADS",
//				"The C-ROADS model is a lightweight GHG model, written in the Vensim simulation language, and developed by researchers from the Sustainability Institute and Rocky Mountain Institute. Although it is lightweight enough to be run on a personal computer, its results are consistent with much larger models, including the MiniCam and Merge models.  The web-based version of the model is hosted at the <a href='http://forio.com/simulation/international-climate-change-simulation/'>Forio</a> website.",
//				new URL("http://localhost:8080/pangaea-servlet/rest"),
//				inputs,outputs,null);
//
//		ServerRepository.instance().discard();
//	}


//	public static void buildCROADS2() {
//		ServerRepository repo = ServerRepository.instance();
//		MetaData devedstopgrowth = new ServerMetaData("Developed countries stop growth year",
//				"D_Developed Stop Growth",
//				"Year at which fossil fuel emissions ceases to increase. US, EU (27 countries), Russia and former Soviet States, Japan, Canada, South Korea, and Australia.",
//				Type.SCALAR,
//				new String[] {"Year"},
//				new String[] {"Year"},
//				new Class[] {Integer.class},
//				new Number[] {2000},
//				new Number[] {2100},
//				null, null);
//		MetaData devingstopgrowth = new ServerMetaData("Developing countries stop growth year",
//				"D_Developing Stop Growth",
//				"Year at which fossil fuel emissions ceases to increase. China, India, South Africa, Mexico, Brazil, and Indonesia.",
//				Type.SCALAR,
//				new String[] {"Year"},
//				new String[] {"Year"},
//				new Class[] {Integer.class},
//				new Number[] {2000},
//				new Number[] {2100},
//				null, null);
//		MetaData otherstopgrowth = new ServerMetaData("Other countries stop growth year",
//				"D_Non Stop Growth",
//				"Year at which other countries fossil fuel emissions ceases to increase.",
//				Type.SCALAR,
//				new String[] {"Year"},
//				new String[] {"Year"},
//				new Class[] {Integer.class},
//				new Number[] {2000},
//				new Number[] {2100},
//				null, null);
//
//		MetaData devedstartreduction = new ServerMetaData("Developed countries start reduction year",
//				"D_Developed Start Reduction",
//				"Year at which fossil fuel emissions begins to decrease. US, EU (27 countries), Russia and former Soviet States, Japan, Canada, South Korea, and Australia.",
//				Type.SCALAR,
//				new String[] {"Year"},
//				new String[] {"Year"},
//				new Class[] {Integer.class},
//				new Number[] {2000},
//				new Number[] {2100},
//				null, null);
//		MetaData devingstartreduction = new ServerMetaData("Developing countries start reduction year",
//				"D_Developing Start Reduction",
//				"Year at which fossil fuel emissions begins to decrease. China, India, South Africa, Mexico, Brazil, and Indonesia.",
//				Type.SCALAR,
//				new String[] {"Year"},
//				new String[] {"Year"},
//				new Class[] {Integer.class},
//				new Number[] {2000},
//				new Number[] {2100},
//				null, null);
//		MetaData otherstartreduction = new ServerMetaData("Other countries start reduction year",
//				"D_Non Start Reduction",
//				"Year at which other countries fossil fuel emissions begins to decrease.",
//				Type.SCALAR,
//				new String[] {"Year"},
//				new String[] {"Year"},
//				new Class[] {Integer.class},
//				new Number[] {2000},
//				new Number[] {2100},
//				null, null);
//		MetaData devedin = new ServerMetaData("Developed countries fossil fuel change",
//				"D_Developed ME Change",
//				"Annual change in fossil fuel emssions. US, EU (27 countries), Russia and former Soviet States, Japan, Canada, South Korea, and Australia.",
//				Type.SCALAR,
//				new String[] {"Fossil Fuel Emissions"},
//				new String[] {"Emissions relative to reduction start year"},
//				new Class[] {Double.class},
//				new Number[] {0d},
//				new Number[] {2.0d},
//				null, null);
//		MetaData devingin = new ServerMetaData("Developing countries fossil fuel change",
//				"D_Developing ME Change",
//				"Annual reduction in fossil fuel emssions. China, India, South Africa, Mexico, Brazil, and Indonesia.",
//				Type.SCALAR,
//				new String[] {"Fossil Fuel Emissions"},
//				new String[] {"Emissions relative to reduction start year"},
//				new Class[] {Double.class},
//				new Number[] {0d},
//				new Number[] {2.0d},
//				null, null);
//		MetaData otherin = new ServerMetaData("Other countries fossil fuel emissions change",
//				"D_Non ME Change",
//				"Annual reduction in fossil fuel emissions for other countries.",
//				Type.SCALAR,
//				new String[] {"Fossil Fuel Emissions"},
//				new String[] {"Emissions relative to reduction start year"},
//				new Class[] {Double.class},
//				new Number[] {0d},
//				new Number[] {3.0d},
//				null, null);
//
//		MetaData deforestin = repo.findMetaDataByVarName("D_Land use emissions change");
//		MetaData afforestin = repo.findMetaDataByVarName("D_Target Sequestration");
//
//		List<MetaData> inputs = new ArrayList<MetaData>();
//		Collections.addAll(inputs, devedstopgrowth, devingstopgrowth, otherstopgrowth, devedstartreduction, devingstartreduction, otherstartreduction, devedin, devingin, otherin, deforestin, afforestin);
//
//		MetaData devedout = repo.findMetaDataByVarName("DevelopedFossilFuelEmissions");
//		MetaData devingout = repo.findMetaDataByVarName("DevelopingFossilFuelEmissions");
//		MetaData otherout = repo.findMetaDataByVarName("NondevelopingFossilFuelEmissions");
//		MetaData atmosout = repo.findMetaDataByVarName("AtmosphericCO2Concentration");
//
//		MetaData tempchange = new ServerMetaData("Global mean temperature change",
//				"GlobalTempChange",
//				"Change in mean global temperature (Celsius) each year, relative to pre-industrial temperatures.",
//				Type.SCALAR,
//				new String[] {"Year","Degrees(C)"},
//				new String[] {"Year","C"},
//				new Class[] {Integer.class,Double.class},
//				null,
//				null,
//				null, null);
//
//		MetaData sealevelchange = new ServerMetaData("Sea Level Rise",
//				"SeaLevelRise",
//				"Change in sea level each year, relative to sea level in 2000.",
//				Type.SCALAR,
//				new String[] {"Year","Sea Level (mm)"},
//				new String[] {"Year","mm"},
//				new Class[] {Integer.class,Double.class},
//				null,
//				null,
//				null, null);
//
//		List<MetaData> outputs = new ArrayList<MetaData>();
//		Collections.addAll(outputs, devedout, devingout, otherout, atmosout, tempchange, sealevelchange);
//		Simulation sim = new ServerSimulation("C-ROADS (alt)",
//				"The C-ROADS model is a lightweight GHG model, written in the Vensim simulation language, and developed by researchers from the Sustainability Institute and Rocky Mountain Institute. Although it is lightweight enough to be run on a personal computer, its results are consistent with much larger models, including the MiniCam and Merge models.  The web-based version of the model is hosted at the <a href='http://forio.com/simulation/international-climate-change-simulation/'>Forio</a> website.",
//				null,
//				inputs,outputs,null);
//
//		ServerRepository.instance().discard();
//
//	}

	public static void buildATLANTA(String name) throws IOException {
		if (name==null) {
			logger.warn("Requires a file name");
			return;
		}
		ServerSimulation sim = (ServerSimulation) ServerRepository.instance().findSimulationByName("C-ROADS (alt)");
		ServerScenario scenario = (ServerScenario)ServerRepository.instance().findScenarioByName("Atlanta Plan");
		if (scenario == null) {
			scenario = new ServerScenario(sim,"-1","Atalanta Plan","Commitments resulting from the Atlanta Negotiation Excercise",null);

		}
		new CsvDumper(name,scenario);
		ServerRepository.instance().discard();
	}

//	public static void buildATLANTAInputs() throws IOException {
//
//		ServerSimulation sim = (ServerSimulation) ServerRepository.instance().findSimulationByName("C-ROADS (alt)");
//		User user = ServerRepository.instance().findUserByName("jintrone");
//		ServerScenario scenario = (ServerScenario)ServerRepository.instance().findScenario("280");
//		scenario.getInputSet().clear();
//		if (scenario == null) {
//			scenario = new ServerScenario(sim,user,"Atalanta Plan","Commitments resulting from the Atlanta Negotiation Excercise",null);
//		}
//
//		ServerRepository repo = ServerRepository.instance();
//		List<Variable> vars = new ArrayList<Variable>();
//
//		MetaData deving_stop_growth = repo.findMetaDataByVarName("D_Developing Stop Growth"); //2017
//		Variable v = new ServerVariable(deving_stop_growth);
//		v.addValue(new ServerTuple(new Number[] {2017}));
//		vars.add(v);
//
//		MetaData deved_stop_growth = repo.findMetaDataByVarName("D_Developed Stop Growth"); //2010
//		v = new ServerVariable(deved_stop_growth);
//		v.addValue(new ServerTuple(new Number[] {2010}));
//		vars.add(v);
//
//		MetaData non_stop_growth = repo.findMetaDataByVarName("D_Non Stop Growth"); //2009
//		v = new ServerVariable(non_stop_growth);
//		v.addValue(new ServerTuple(new Number[] {2010}));
//		vars.add(v);
//
//
//		MetaData deving_start_red = repo.findMetaDataByVarName("D_Developing Start Reduction"); //2027
//		v = new ServerVariable(deving_start_red);
//		v.addValue(new ServerTuple(new Number[] {2027}));
//		vars.add(v);
//
//		MetaData deved_start_red = repo.findMetaDataByVarName("D_Developed Start Reduction"); //2015
//		v = new ServerVariable(deved_start_red);
//		v.addValue(new ServerTuple(new Number[] {2015}));
//		vars.add(v);
//
//		MetaData non_start_red = repo.findMetaDataByVarName("D_Non Start Reduction"); //2019
//		v = new ServerVariable(non_start_red);
//		v.addValue(new ServerTuple(new Number[] {2019}));
//		vars.add(v);
//
//		MetaData target_seq = repo.findMetaDataByVarName("D_Target Sequestration"); //0
//		v = new ServerVariable(target_seq);
//		v.addValue(new ServerTuple(new Number[] {0}));
//		vars.add(v);
//
//		MetaData land_use_emissions = repo.findMetaDataByVarName("D_Land use emissions change"); //.55
//		v = new ServerVariable(land_use_emissions);
//		v.addValue(new ServerTuple(new Number[] {.55}));
//		vars.add(v);
//
//		MetaData deving_change = repo.findMetaDataByVarName("D_Developing ME change"); //.04
//		v = new ServerVariable(deving_change);
//		v.addValue(new ServerTuple(new Number[] {.04}));
//		vars.add(v);
//
//		MetaData deved_change = repo.findMetaDataByVarName("D_Developed ME change"); //.045
//		v = new ServerVariable(deved_change);
//		v.addValue(new ServerTuple(new Number[] {.045}));
//		vars.add(v);
//
//		MetaData non_change = repo.findMetaDataByVarName("D_Non ME change"); //.01
//		v = new ServerVariable(non_change);
//		v.addValue(new ServerTuple(new Number[] {.01}));
//		vars.add(v);
//
//		for (Variable v1:vars) {
//			scenario.addToInput(v1);
//		}
//
//		ServerRepository.instance().discard();
//	}

//	public static void main(String[] args) throws IOException {
//		if (args[0].equals("CROADS-sim")) {
//			buildCROADS();
//		} else if (args[0].equals("CROADS2-sim")) {
//			buildCROADS2();
//		} else if (args[0].equals("atlanta")) {
//			buildATLANTA(args[1]);
//		} else if (args[0].equals("atlantainputs")) {
//			buildATLANTAInputs();
//		}


		//
//		Scenario s = new ServerScenario(sim,user,"Test scenario");
//		String scenarioid = s.getId();
//		Variable iv = new ServerVariable(md2);
//		iv.addValue(new ServerTuple(new Number[] {new Float(1.1)}));
//		s.addToInput(iv);
//
//		Variable ov = new ServerVariable(md1);
//		ov.addValue(new ServerTuple(new Number[] {new Integer(2),new Float(4.5)}));
//		s.addToOutput(ov);
//
//
//		//ServerRepository.instance().commit();
//		ServerRepository.instance().discard();
//		ScenarioDAO sdao = ServerRepository.instance().findDAO(ScenarioDAO.class, "200");
//		Scenario s1 = ServerRepository.instance().get(sdao);

//		System.err.println("Inputs are "+s1.getInputSet());
//		System.err.println("Outputs are "+s1.getOutputSet());

//	}

}
