package mit.simulation.climate.model.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import mit.simulation.climate.dao.ScenarioDAO;
import mit.simulation.climate.dao.VariableDAO;
import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Scenario;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.Variable;
import mit.simulation.climate.model.MetaData.VarContext;
import mit.simulation.climate.model.helper.CompositeVariable;

import org.apache.log4j.Logger;

public class ServerScenario extends ServerObject<ScenarioDAO> implements Scenario {

    private static Logger log = Logger.getLogger(ServerScenario.class);

    public ServerScenario(ScenarioDAO dao) {
        super(dao);
    }

    public ServerScenario(Simulation s, String userid, String name, String description, EntityState state) {
        setSimulation(s);
        setName(name);
        setAuthor(userid);
        setDescription(description);
        setCreation(new Date());
        if (state==null) {
            setState(EntityState.PUBLIC);
        } else {
            setState(state);
        }
    }

    @Override
    public String getAuthor() {
        return dao.getUser();
    }

    @Override
    public Date getCreation() {
        return dao.getCreation();
    }

    @Override
    public String getDescription() {
        return dao.getDescription();
    }

    @Override
    public Long getId() {
        return dao.getId();
    }

    @Override
    public List<Variable> getInputSet() {
        List<Variable> result = new ArrayList<Variable>();
        for (VariableDAO v:dao.getScenarioToInputVars()) {
            result.add(ServerRepository.instance().get(v));
        }
        return result;
    }

    public void addToInput(Variable v) {
        dao.addToScenarioToInputVars(((ServerVariable)v).getDataObject());
    }

    public void addToOutput(Variable v) {
        dao.addToScenarioOutputToVars(((ServerVariable)v).getDataObject());
    }


    @Override
    public List<Variable> getOutputSet() {
        log.debug("Returning regular outputs");
        List<Variable> result = new ArrayList<Variable>();

        for (VariableDAO v:dao.getScenarioOutputToVars()) {
            result.add(ServerRepository.instance().get(v));
        }

        return result;
    }

    public List<Variable> getCombinedOutputs() {
        log.debug("Returning combined outputs");
        List<Variable> outvars = getOutputSet();

        Variable defaultidx  = null;
        boolean hasindexed = false;
        for (Iterator<Variable> i = outvars.iterator();i.hasNext() && (!hasindexed || defaultidx==null);) {
            Variable test = i.next();
            log.debug("Checking variable "+test.getMetaData().getName());
            log.debug("Varcontext is "+test.getMetaData().getVarContext());
            hasindexed|=(test.getMetaData().getVarContext() == VarContext.INDEXED);
            if (defaultidx==null && test.getMetaData().isIndex()) {
                defaultidx = test;
                
            }
        }

        if (!hasindexed) {
            log.debug("No variables are indexed; returning regular outvars");
            return outvars;
        }
        else {
            List<Variable> result = new ArrayList<Variable>();
            for (Variable outvar:outvars) {
                Variable idx = ((outvar.getMetaData().getIndexingMetaData() == null)?defaultidx:findOutputVariable(outvar.getMetaData().getIndexingMetaData()));
                if (outvar.getMetaData().getVarContext() == VarContext.INDEXED) {
                    result.add(new CompositeVariable(outvar,idx,outvar));
                } else {
                    result.add(outvar);
                }
            }
            return result;
        }
    }

    private Variable findOutputVariable(MetaData md) {
        List<Variable> vars = getOutputSet();
        for (Variable var:vars) {
            log.debug("Comparing "+var.getMetaData().getInternalName()+var.getMetaData().getId()+" to "+md.getInternalName()+md.getId());            
            if (var.getMetaData().equals(md)) {
                return var;
            }
        }
        return null;
    }

    @Override
    public Simulation getSimulation() {
        return ServerRepository.instance().get(dao.getScenarioToSimulation());
    }

    @Override
    public void setAuthor(String u) {
        dao.setUser(u);

    }

    @Override
    public void setCreation(Date d) {
        dao.setCreation(d);

    }

    @Override
    public void setDescription(String desc) {
        dao.setDescription(desc);
    }

    @Override
    public void setSimulation(Simulation s) {
        dao.setScenarioToSimulation(((ServerSimulation)s).getDataObject());
    }

    @Override
    public String getName() {
        return dao.getName();
    }

    @Override
    public void setName(String name) {
        dao.setName(name);
    }

    public EntityState getState() {
        String state = dao.getState();
        return state==null?EntityState.PUBLIC:EntityState.valueOf(state);
    }

    public void setState(EntityState state) {
        dao.setState(state.toString());
    }



    public boolean equals(Object o) {
        return ((o instanceof ServerScenario) && dao.equals(((ServerScenario)o).dao));
    }

}
