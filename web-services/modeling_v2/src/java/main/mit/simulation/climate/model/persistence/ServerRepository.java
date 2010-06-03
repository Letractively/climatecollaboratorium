/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate.model.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.dao.ScenarioDAO;
import mit.simulation.climate.dao.SimulationDAO;
import mit.simulation.climate.dao.TupleDAO;
import mit.simulation.climate.dao.VariableDAO;
import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.DataObjectUtils;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.apache.log4j.Logger;

/**
 * <p>
 * The DAO class for Simulation service.
 * </p>
 * <p>
 * Change note for 1.1: The main changes are as following:
 * <ul>
 * <li>Remove cache since it is unnecessary.</li>
 * <li>Remove some unused methods.</li>
 * <li>Remove discard function which is a duplicate of commit.</li>
 * <li>Remove idhack method.</li>
 * <li>Use long as id for find/get methods.</li>
 * <li>refactor the cleaner to use the executor as well as providing way to stop it.</li>
 * </ul>
 * <p>
 * <b>Thread Safety</b> all public methods are thread safe.
 * </p>
 *
 * @author jintrone, TCSDEVELOPER
 * @version 1.1
 * @since 1.0
 */
public class ServerRepository {
    /**
     * <p>
     * Logger for this class.
     * </p>
     */
    private static final Logger LOGGER = Logger.getLogger(ServerRepository.class);

    /**
     * <p>
     * The seconds for the hour which the interval for running the cleaner.
     * </p>
     */
    private static final long MILSECONDS_FOR_ONE_HOUR = 1000 * 60 * 60;

    /**
     * <p>
     * Server factory instance.
     * </p>
     */
    private static ServerFactory factory = ServerFactory.instance();

    /**
     * <p>
     * This is to be used in <code>instance</code> method.
     * </p>
     */
    private static ServerRepository instance = new ServerRepository();

    /**
     * <p>
     * Cleaner executor.
     * </p>
     */
    private ScheduledThreadPoolExecutor executor;

    /**
     * <p>
     * default ctor.
     * </p>
     */
    protected ServerRepository() {
        executor = new ScheduledThreadPoolExecutor(1);
    }

    /**
     * <p>
     * starts the cleaner.
     * </p>
     */
    public void startCleaner() {
        LOGGER.info("Starts the cleaner.");
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("running cleaner ...");
                try {
                    // make sure there is a data context bind in the running thread
                    try {
                        getDataContext();
                    } catch (IllegalStateException e) {
                        LOGGER.info("assign a data object in the thread");
                        BaseContext.bindThreadObjectContext(DataContext.createDataContext());
                    }

                    cleanAllTemporaryItems(System.currentTimeMillis() - MILSECONDS_FOR_ONE_HOUR);
                    commit();
                } catch (Exception e) {
                    try {
                        rollback();
                    } catch (Exception re) {
                        LOGGER.warn("rollback failed : " + re.getMessage());
                    }
                    LOGGER.error("Error when running the cleaner.", e);
                } finally {
                    LOGGER.info("running cleaner ended.");
                }
            }
        }, 0, MILSECONDS_FOR_ONE_HOUR, TimeUnit.MILLISECONDS);
    }

    /**
     * <p>
     * Stops the cleaner.
     * </p>
     */
    public void stopCleaner() {
        LOGGER.info("Stops the cleaner.");
        executor.shutdown();
    }

    /**
     * <p>
     * This method is to be kept since some Server classes refer to this.
     * </p>
     *
     * @return return the instance
     */
    public static ServerRepository instance() {
        return instance;
    }

    /**
     * <p>
     * Creates the dao object.
     * <p>
     *
     * @param <T> the class type
     * @param cls the class
     * @return the dao object
     */
    public <T> T createDAO(Class<T> cls) {
        T result = (T) getDataContext().newObject(cls);
        return result;
    }

    /**
     * <p>
     * Removes the data object.
     * </p>
     *
     * @param dataObject the data object to be removed
     */
    public void remove(CayenneDataObject dataObject) {
        getDataContext().deleteObject(dataObject);
    }

    /**
     * <p>
     * Gets the dao object by the id string.
     *
     * @param <S> the type
     * @param cls the class
     * @param id the id. string type. It should be able to be parsed to as long
     * @return dao object. null if the id is not long type
     */
    private <S> S findDAO(Class<S> cls, String id) {
        try {
            return findDAO(cls, Long.parseLong(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * <p>
     * Gets dao object by the id.
     * </p>
     *
     * @param <S> the dao type
     * @param cls the class
     * @param id the id. long type.
     * @return the dao object
     */
    public <S> S findDAO(Class<S> cls, Long id) {
        S result = DataObjectUtils.objectForPK(getDataContext(), cls, id);
        return result;
    }

    /**
     * <p>
     * Gets <code>ServerMetaData</code> object from <code>MetaDataDAO</code> object.
     * </p>
     *
     * @param dao the <code>MetaDataDAO</code> object
     * @return the <code>ServerMetaData</code> object
     */
    public ServerMetaData get(MetaDataDAO dao) {
        return (dao == null) ? null : (ServerMetaData) factory.get(dao);
    }

    /**
     * <p>
     * Gets <code>ServerTuple</code> object from <code>TupleDAO</code> object.
     * </p>
     *
     * @param dao the <code>TupleDAO</code> object
     * @return the <code>ServerTuple</code> object
     */
    public ServerTuple get(TupleDAO dao) {
        return (dao == null) ? null : (ServerTuple) factory.get(dao);
    }

    /**
     * <p>
     * Gets <code>ServerScenario</code> object from <code>ScenarioDAO</code> object.
     * </p>
     *
     * @param dao the <code>ScenarioDAO</code> object
     * @return the <code>ServerScenario</code> object
     */
    public ServerScenario get(ScenarioDAO dao) {
        return (dao == null) ? null : (ServerScenario) factory.get(dao);
    }

    /**
     * <p>
     * Gets <code>ServerVariable</code> object from <code>VariableDAO</code> object.
     * </p>
     *
     * @param dao the <code>VariableDAO</code> object
     * @return the <code>ServerVariable</code> object
     */
    public ServerVariable get(VariableDAO dao) {
        return (dao == null) ? null : (ServerVariable) factory.get(dao);
    }

    /**
     * <p>
     * Gets <code>ServerSimulation</code> object from <code>SimulationDAO</code> object.
     * </p>
     *
     * @param dao the <code>SimulationDAO</code> object
     * @return the <code>ServerSimulation</code> object
     */
    public ServerSimulation get(SimulationDAO dao) {
        return (dao == null) ? null : (ServerSimulation) factory.get(dao);
    }

    /**
     * <p>
     * Gets all public scenarios.
     * </p>
     *
     * @return all public scenarios
     */
    public Collection<Scenario> getAllPublicScenarios() {
        SelectQuery query = new SelectQuery(ScenarioDAO.class, ExpressionFactory.matchExp(ScenarioDAO.STATE_PROPERTY,
            EntityState.PUBLIC.toString()));
        List<ScenarioDAO> scenarios = (List<ScenarioDAO>) getDataContext().performQuery(query);
        List<Scenario> result = new ArrayList<Scenario>();
        for (ScenarioDAO dao : scenarios) {
            result.add(ServerFactory.instance().get(dao));
        }
        return result;
    }

    /**
     * <p>
     * Gets all scenarios.
     * </p>
     *
     * @return all scenarios
     */
    @SuppressWarnings("unchecked")
    public Collection<Scenario> getAllScenarios() {
        SelectQuery query = new SelectQuery(ScenarioDAO.class);
        List<ScenarioDAO> scenarios = (List<ScenarioDAO>) getDataContext().performQuery(query);
        List<Scenario> result = new ArrayList<Scenario>();
        for (ScenarioDAO dao : scenarios) {
            result.add(ServerFactory.instance().get(dao));
        }
        return result;
    }

    /**
     * <p>
     * Clean up all temporary items.
     * </p>
     *
     * @param since the time interval before which all temporary items will be removed
     */
    @SuppressWarnings("unchecked")
    private void cleanAllTemporaryItems(long since) {
        // remove temporary scenarios
        Expression e = ExpressionFactory.matchExp(ScenarioDAO.STATE_PROPERTY, EntityState.TEMPORARY.toString())
            .andExp(ExpressionFactory.lessExp(ScenarioDAO.CREATION_PROPERTY, new Date(since)));
        SelectQuery query = new SelectQuery(ScenarioDAO.class, e);
        List<ScenarioDAO> scenarios = (List<ScenarioDAO>) getDataContext().performQuery(query);
        LOGGER.info("Cleaning " + scenarios.size() + " scenarios");
        getDataContext().deleteObjects(scenarios);

        // remove temporary simulations
        e = ExpressionFactory.matchExp(SimulationDAO.STATE_PROPERTY, EntityState.TEMPORARY.toString()).andExp(
            ExpressionFactory.lessExp(SimulationDAO.CREATION_PROPERTY, new Date(since)));
        query = new SelectQuery(ScenarioDAO.class, e);
        List<SimulationDAO> sims = (List<SimulationDAO>) getDataContext().performQuery(query);
        LOGGER.info("Cleaning " + sims.size() + " simulations");
        getDataContext().deleteObjects(sims);
    }

    /**
     * <p>
     * Get all public simulations.
     * </p>
     *
     * @return all public simulations
     */
    @SuppressWarnings("unchecked")
    public Collection<Simulation> getAllPublicSimulations() {
        SelectQuery query = new SelectQuery(SimulationDAO.class, ExpressionFactory.matchExp(
            ScenarioDAO.STATE_PROPERTY, EntityState.PUBLIC.toString()));
        List<SimulationDAO> simulations = (List<SimulationDAO>) getDataContext().performQuery(query);
        List<Simulation> result = new ArrayList<Simulation>();
        for (SimulationDAO dao : simulations) {
            result.add(ServerFactory.instance().get(dao));
        }
        return result;
    }

    /**
     * <p>
     * Gets all simulations.
     * </p>
     *
     * @return all simulations
     */
    @SuppressWarnings("unchecked")
    public Collection<Simulation> getAllSimulations() {
        SelectQuery query = new SelectQuery(SimulationDAO.class);
        List<SimulationDAO> simulations = (List<SimulationDAO>) getDataContext().performQuery(query);
        List<Simulation> result = new ArrayList<Simulation>();
        for (SimulationDAO dao : simulations) {
            result.add(ServerFactory.instance().get(dao));
        }
        return result;
    }

    /**
     * <p>
     * Deletes the simulation.
     * <p>
     *
     * @param id the id
     */
    public void deleteSimulation(long id) {
        SimulationDAO simulation = findDAO(SimulationDAO.class, id);
        if (simulation != null) {
            //do this in steps to avoid infinite loop due to cyclical cascading

            for (ScenarioDAO scenario:simulation.getSimulationToScenario()) {
                getDataContext().deleteObject(scenario);
                commit();
            }

            getDataContext().deleteObject(simulation);
            commit();
        }
    }

    /**
     * <p>
     * Gets the simulations.
     * </p>
     *
     * @param id the simulation id
     * @return the simulation
     */
    public Simulation findSimulation(String id) {
        SimulationDAO dao = findDAO(SimulationDAO.class, id);
        Simulation result = null;
        if (dao != null) {
            result = get(dao);
        }
        return result;
    }

    /**
     * <p>
     * Gets the simulation by the name.
     * </p>
     *
     * @param name the simulation name
     * @return the simulation
     */
    public Simulation findSimulationByName(String name) {
        SelectQuery query = new SelectQuery(SimulationDAO.class, ExpressionFactory.matchExp("name", name));
        List result = getDataContext().performQuery(query);
        if (result != null && result.size() > 0) {
            return get((SimulationDAO) result.get(0));
        }
        return null;
    }

    /**
     * <p>
     * Gets the scenario by id.
     * </p>
     *
     * @param id the id
     * @return the scenario
     */
    public Scenario findScenario(String id) {
        LOGGER.info("Find scenario for id " + id);
        ScenarioDAO dao = findDAO(ScenarioDAO.class, id);
        LOGGER.info("Got scenario " + dao);
        Scenario result = null;
        if (dao != null) {
            result = get(dao);
        }
        return result;
    }

    /**
     * <p>
     * Gets the scenario by the name.
     * </p>
     *
     * @param name the scenario name
     * @return the scenario
     */
    public Scenario findScenarioByName(String name) {
        SelectQuery query = new SelectQuery(ScenarioDAO.class, ExpressionFactory.matchExp("name", name));
        List result = getDataContext().performQuery(query);
        if (result != null && result.size() > 0) {
            return get((ScenarioDAO) result.get(0));
        }
        return null;
    }

    /**
     * <p>
     * Gets the metadata by the id.
     * </p>
     *
     * @param id the metadata id
     * @return the metadata
     */
    public MetaData findMetaData(String id) {
        MetaDataDAO dao = findDAO(MetaDataDAO.class, id);
        MetaData result = null;
        if (dao != null) {
            result = get(dao);
        }
        return result;
    }

    /**
     * <p>
     * Gets the metadata by the internal name.
     * </p>
     *
     * @param name the internal name
     * @return the metadata
     */
    public MetaData findMetaDataByVarName(String name) {
        SelectQuery query = new SelectQuery(MetaDataDAO.class, ExpressionFactory.matchExp("internalname", name));
        List result = getDataContext().performQuery(query);
        if (result != null && result.size() > 0) {
            return get((MetaDataDAO) result.get(0));
        }
        return null;

    }

    /**
     * <p>
     * It will commit the changes made.
     * </p>
     */
    public void commit() {
        getDataContext().commitChanges();
    }

    /**
     * <p>
     * It will rollback the changes made.
     * </p>
     */
    public void rollback() {
        getDataContext().rollbackChanges();
    }

    /**
     * <p>
     * Gets the <code>DataContext</code> object in the current execution thread.
     * </p>
     *
     * @return the <code>DataContext</code>.
     */
    private DataContext getDataContext() {
        return (DataContext) BaseContext.getThreadObjectContext();
    }
}
