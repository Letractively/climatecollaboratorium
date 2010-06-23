package mit.simulation.climate.model.persistence;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import mit.simulation.climate.dao.MetaDataDAO;
import mit.simulation.climate.dao.SimulationDAO;
import mit.simulation.climate.model.EntityState;
import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Simulation;
import mit.simulation.climate.model.SimulationStepper;
import mit.simulation.climate.model.MetaData.VarContext;
import mit.simulation.climate.model.helper.CompositeMetaData;

public class ServerSimulation extends ServerObject<SimulationDAO> implements
        Simulation {

    private static Logger log = Logger.getLogger(ServerSimulation.class);

    private SimulationStepper stepper = null;

    public ServerSimulation(SimulationDAO dao) {
        super(dao);
        configure();
    }

    public ServerSimulation(String name, String description, URL url,
                            List<MetaData> inputs, List<MetaData> outputs, EntityState state, String type) {
        setName(name);
        setDescription(description);
        setURL(url);
        setType(type);
        setCreation(new Date());

        if (inputs != null) {
            for (MetaData o : outputs) {
                addOutput(o);
            }
        }
        if (outputs != null) {
            for (MetaData i : inputs) {
                addInput(i);
            }
        }

        setState(state != null ? state : EntityState.PUBLIC);

        configure();

    }

    protected void configure() {
        stepper = new SimulationStepper();
        stepper.new Step(this, null);
    }

    @Override
    public void setCreation(Date d) {
        dao.setCreation(d);

    }

    public Date getCreation() {
        return dao.getCreation();
    }

    @Override
    public void addInput(MetaData md) {
        dao.addToInputs(((ServerMetaData) md).getDataObject());
        
    }

    @Override
    public void addOutput(MetaData md) {
        dao.addToOutputs(((ServerMetaData) md).getDataObject());
        if (md.getVarContext() == VarContext.INDEXED && md.getIndexingMetaData() == null) {
            for (MetaData lmd:getOutputs()) {
                if (lmd.isIndex()) {
                    md.setIndexingMetaData(lmd);
                }
            }
        } else if (md.getVarContext() == VarContext.INDEX) {
            for (MetaData lmd:getOutputs()) {
                if (lmd.getIndexingMetaData() == null) {
                    lmd.setIndexingMetaData(md);
                }
            }
        }
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
    public List<MetaData> getInputs() {
        List<MetaData> result = new ArrayList<MetaData>();
        for (MetaDataDAO mddao : dao.getInputs()) {
            result.add(ServerRepository.instance().get(mddao));
        }
        return result;
    }

    @Override
    public List<MetaData> getOutputs() {
        List<MetaData> result = new ArrayList<MetaData>();
        for (MetaDataDAO mddao : dao.getOutputs()) {
            result.add(ServerRepository.instance().get(mddao));
        }
        return result;
    }

    @Override
    public List<MetaData> getCombinedOutputs() {
        List<MetaData> mds = getOutputs();

        MetaData defaultidx = null;
        boolean hasindexed = false;
        for (Iterator<MetaData> i = mds.iterator(); i.hasNext();) {
            MetaData test = i.next();
            hasindexed|=test.getVarContext()==VarContext.INDEXED;
            if (test.isIndex() && defaultidx == null) {
                defaultidx = test;

            }
        }
        if (!hasindexed) {
            log.debug("Server simulation "+getName()+"returning outputs "+mds);
            return mds;
        }else {
            List<MetaData> result = new ArrayList<MetaData>();
            for (MetaData md : mds) {
                if (md.getVarContext() == VarContext.INDEXED) {
                    log.debug("Atempting to locate index for "+md.getName());
                    MetaData idx = (md.getIndexingMetaData() == null?defaultidx:md.getIndexingMetaData());
                    if (idx == null) {
                        log.debug("Warning - trapped an indexed variable but no index available in "+this.getName()+":"+md.getId());
                        result.add(md);
                    } else {
                        result.add(new CompositeMetaData(md, idx, md));
                    }
                } else {
                    result.add(md);
                }
            }
            log.debug("Server simulation "+getName()+"returning outputs "+result);
            return result;
        }

    }

    @Override
    public URL getURL() {
        try {
            return dao.getUrl() == null ? null : new URL(dao.getUrl());
        } catch (MalformedURLException e) {
            log.error("URL is not valid");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setDescription(String description) {
        dao.setDescription(description);
    }

    @Override
    public void setURL(String url) {
        if (url == null) return;
        try {
            setURL(new URL(url));
        } catch (MalformedURLException e) {
            log.error("URL is not valid");
            e.printStackTrace();
        }
    }

    @Override
    public void setURL(URL url) {
        if (url == null) return;
        dao.setUrl(url.toString());
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
        return state == null ? EntityState.PUBLIC : EntityState.valueOf(state);
    }

    @Override
    public void setType(String type) {
        dao.setType(type);
    }

    @Override
    public String getType() {
        return dao.getType();
    }

    public void setState(EntityState state) {
        dao.setState(state.toString());
    }



    public SimulationStepper getSimulationStepper() {
        if (stepper == null) {
            stepper = new SimulationStepper();
        }
        stepper.reset();
        return stepper;
    }



}
