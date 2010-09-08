package mit.simulation.climate.model;

import mit.simulation.climate.Utils;
import mit.simulation.climate.model.persistence.ServerRepository;
import mit.simulation.climate.model.persistence.ServerTuple;
import mit.simulation.climate.model.persistence.ServerVariable;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Responsible for running a simulation. Each simulation is advanced using "steps." An atomic simulation is created
 * with a single step. A composite simulation has multiple steps.
 * <p/>
 * See {@link Step}
 *
 * @author jintrone
 */
public class SimulationStepper {

    private static Logger log = Logger.getLogger(SimulationStepper.class);

    private List<Step> allsteps = new ArrayList<Step>();

    private Iterator<Step> iterator;

    /**
     * Get a new iterator for stepping through the simulation
     *
     * @return A new iterator over the steps in the simulation
     */
    public Iterator<Step> reset() {
        iterator = allsteps.iterator();
        return iterator;
    }

    /**
     * Zeros the simulation stepper and deletes all steps
     *
     * @return
     */
    public void clear() {
        if (iterator != null) {
            while (iterator.hasNext()) {
                iterator.next();
            }
        }
        iterator = null;
        allsteps.clear();
    }

    /**
     * Checks if there are more steps left
     *
     * @return true if there are more steps
     */
    public boolean hasNext() {
        return iterator != null && iterator.hasNext();
    }

    /**
     * Advances the iterator
     *
     * @return
     */
    public Step next() {
        return iterator.next();
    }

    /**
     * Represents a step in a simulation. Responsible for running a single (atomic) simulation.
     *
     * @author jintrone
     */
    public class Step {

        /**
         * The simulation to run
         */
        private Simulation sim;

        /**
         * A list of incoming links from other simulation runs. The list of links establishes a chain between multiple
         * simulations.
         */
        private List<Link> links;

        /**
         * Whether or not the values derived from this simulation should be treated as a summation. Note, this only
         * applies when this simulation takes a scalar input and the simulation that is feeding it is a vector
         */
        private boolean accumulate = false;

        /**
         * Used to remap output MetaData from {@link mit.simulation.climate.model.MetaData.VarContext.SCALAR} to
         * {@link MetaData.VarContext.INDEXED}. This may be necessary if this simulation produces a single (scalar)
         * value, but is linked (via a {@link Link}) to a variable which is a vector. In this case, there will be a
         * range of output values, and in order for the client to handle these properly, they must be marked as
         * INDEXED and inherit the INDEX of the simulation that is feeding it.
         */
        private Map<MetaData, MetaData> outputmap = new HashMap<MetaData, MetaData>();

        /**
         * Constructor
         *
         * @param sim     The simulation this step handles.
         * @param links   The links describing how it's input parameters are fed
         * @param outputs An map that describes how outputs from this simulation should be remaped into outputs with a
         *                different MetaData definition. Only necessary if this simulation produces a single value but is
         *                linked to a simulation that creates multiple values.
         */
        public Step(Simulation sim, List<Link> links, Map<MetaData, MetaData> outputs) {
            this.sim = sim;
            this.links = (List<Link>) ((links == null) ? Collections.emptyList() : new ArrayList<Link>(links));
            allsteps.add(this);
            outputmap.putAll(outputs);
        }

        /**
         * Constructor
         *
         * @param sim   The simulation this step handles.
         * @param links The links describing how it's input parameters are fed
         */
        public Step(Simulation sim, List<Link> links) {
            this(sim, links, (Map<MetaData, MetaData>) Collections.EMPTY_MAP);
        }

        public void setAccumulate(boolean b) {
            this.accumulate = b;
        }

        /**
         * Process this step
         *
         * @param runner An externally provided class that encapsulates a (possibly remote) process responsible for
         *               running this simulation.
         * @param vars   A map of known variables that have been accumulated. The results of this simulation run will
         *               accumulate in this map. Map is constructed to be [MetaData.internalname -> variable]. *** NOTE:
         *               Will not handle non-unique internalname's across simulations properly.
         * @param inputs A map of input parameters that may have been provided. In this case, the map is expected to
         *               be [MetaData.internalname -> value]. These will take precedence over the list of vars
         */
        public void process(SimulationRunner runner, Map<String, Variable> vars, Map<String, String> inputs) {
            if (inputs == null)
                inputs = new HashMap<String, String>();
            log.debug("Running simulation " + sim.getName() + " (" + sim.getId() + ")");
            log.debug("Inputs are " + inputs);
            log.debug("Vars are " + vars);

            List<Link> varlinks = new ArrayList<Link>();

            for (Link l : links) {
                if (l.type == LinkType.ALL || l.type == LinkType.SOME) {
                    // accumulate links which require iteration, process these later
                    varlinks.add(l);
                    continue;
                } else if (l.type == LinkType.TO_ONE) {
                    Variable v = vars.get(l.output);


                        inputs.put(l.getInput(), Utils.extractTupleListString(v.getValue(), 0, "[#N/A]"));
                        log.debug("Added input to "+l.getInput());

                    

                } else {
                    // single value mapping, so just pick the last one and use it
                    Variable v = vars.get(l.output);
                    if (v == null) {

                    }
                    List<Tuple> tuples = v.getValue();
                    if (tuples.size() == 0) {
                        log.warn("Output variable " + l.output + " has no values; expecting one; skipping");
                        continue;
                    } else {
                        inputs.put(l.getInput(), tuples.get(tuples.size() - 1).getValues()[0]);
                    }
                }
            }

            // process links that require iteration
            if (!varlinks.isEmpty()) {
                int tuplecount = Short.MAX_VALUE;
                // get the shortest list of vector inputs
                // for example, <internalname1>[[x1][x2][x3]]
                for (Link varlink : varlinks) {
                    Variable v = vars.get(varlink.getOutput());
                    tuplecount = Math.min(tuplecount, v.getValue().size());
                }
                Double step = null;
                // for each element
                for (int i = 0; i < tuplecount; i++) {
                    boolean runstep = true;
                    // for each variable
                    for (int j = 0; j < varlinks.size(); j++) {
                        Link link = varlinks.get(j);
                        Variable v = vars.get(link.getOutput());
                        String value = v.getValue().get(i).getValues()[0];
                        // handles a stepped mapping - e.g. in the case
                        // we only want to map every nth value in the input
                        // to this simulation
                        if (link.type == LinkType.SOME) {
                            //step is the delta between this value and the last
                            if (step == null || Math.abs(step - Double.parseDouble(value)) >= link.interval) {
                                step = Double.parseDouble(value);
                                inputs.put(link.getInput(), value);
                            } else {
                                runstep = false;
                                break;
                            }
                        } else {
                            inputs.put(link.getInput(), value);
                        }
                    }

                    if (runstep) {
                        // if we are running, process the step and add to variables
                        addToVariables(vars, runner.process(sim, vars, inputs));
                    } else {
                        // otherwise just add a null
                        addNullToVariables(vars, sim);
                    }
                }

                // otherwise, just run the input variables
            } else {
                addToVariables(vars, runner.process(sim, vars, inputs));
            }
        }

        /**
         * Handles remapping the variables produces by this simulation to the corresponding variable in the composite
         * simulation of which this may be a part
         *
         * @param v
         * @return
         */
        private Variable remap(Variable v) {
            if (outputmap.containsKey(v.getMetaData())) {
                MetaData nmetadata = outputmap.get(v.getMetaData());
                log.debug("Remapping metadata in variable from " + v.getMetaData().getId() + ":"
                        + v.getMetaData().getName() + " to " + nmetadata.getId() + ":" + nmetadata.getName());
                ((ServerVariable) v).setMetaData(nmetadata);
            }
            return v;
        }

        private Variable createVariable(MetaData m) {
            // make sure we are creating the proper output variable type
            if (outputmap.containsKey(m)) {
                m = outputmap.get(m);
            }
            ServerVariable result = new ServerVariable(m);
            return result;
        }

        private Tuple createEmptyTuple() {
            ServerTuple result = new ServerTuple(new String[]{});
            return result;
        }

        private void addNullToVariables(Map<String, Variable> existing, Simulation sim) {
            for (MetaData md : sim.getOutputs()) {
                if (!existing.containsKey(md.getInternalName())) {
                    Variable v = createVariable(md);
                    v.addValue(createEmptyTuple());

                } else {
                    Variable existingvar = existing.get(md.getInternalName());
                    existingvar.addValue(createEmptyTuple());
                }
            }
        }

        /**
         * Add new output into the map of existing variables
         *
         * @param existing The map of existing variables; [MetaData.internalname -> Variable] *** NOTE: Will not
         *                 handle non-unique internalname's across simulations properly.
         * @param nvars    The map of existing variables; [MetaData.internalname -> Variable] *** NOTE: Will not handle
         *                 non-unique internalname's across simulations properly
         */
        private void addToVariables(Map<String, Variable> existing, Map<String, Variable> nvars) {
            for (Map.Entry<String, Variable> pair : nvars.entrySet()) {
                if (!existing.containsKey(pair.getKey())) {
                    existing.put(pair.getKey(), remap(pair.getValue()));
                } else {
                    Variable existingvar = existing.get(pair.getKey());
                    Variable vartoadd = pair.getValue();
                    log.debug("Removing variable with metadata" + vartoadd.getMetaData().getId() + ":"
                            + vartoadd.getMetaData().getName());
                    log.debug("Swapping data out to " + existingvar.getMetaData().getId() + ":"
                            + existingvar.getMetaData().getName());
                    List<Tuple> tuplelist = vartoadd.getValue();
                    for (Tuple t : tuplelist) {

                        if (accumulate) {
                            // we're treating this as a summation, so add to the last value.
                            List<Tuple> etuples = existingvar.getValue();
                            String[] eval = etuples.get(etuples.size() - 1).getValues();
                            String[] result = new String[]{""
                                    + (Double.parseDouble(t.getValues()[0]) + Double.parseDouble(eval[0]))};
                            existingvar.addValue(new ServerTuple(result));
                        } else {
                            // just add the value
                            existingvar.addValue(new ServerTuple(t));
                        }
                        // make sure to clean up
                        ServerRepository.instance().remove(((ServerTuple) t).dao);
                    }
                    ServerVariable sv = (ServerVariable) vartoadd;
                    // make sure to clean up
                    ServerRepository.instance().remove(sv.dao);
                }
            }
        }
    }

    /**
     * Link describes how one simulation may be linked to another.
     *
     * @author jintrone
     */
    public static class Link {

        /**
         * The internalname of the output variable that will serve as a source
         */
        private String output;

        /**
         * The internalname of the input variable that this link is connecting to
         */
        private String input;

        /**
         * The type of link
         */
        private LinkType type;

        /**
         * The step to be used if this is a {@link LinkType.SOME} link
         */
        private Double interval = null;

        public Link(String input, String source, LinkType type, Double interval) {
            this.output = source;
            this.input = input;
            this.type = type;
            this.interval = interval;
        }

        public void setInterval(double d) {
            this.interval = d;
        }

        public String getOutput() {
            return output;
        }

        public String getInput() {
            return input;
        }

        public Double getInterval() {
            return interval;
        }

    }

    /**
     * Allows injection of remote service for running simulations.
     *
     * @author jintrone
     */
    public static interface SimulationRunner {

        /**
         * Run a remote simulation
         *
         * @param sim       The simulation to run
         * @param varinputs The currently available map of variables. Map of the form [MetaData.internalname ->
         *                  Variable]
         * @param strinputs Current inputs. This is examined first for input values. If they are not found, the system
         *                  falls back to the variable inputs above. Map of the form [MetaData.internalname->value]
         * @return A map of outputs. Map is of the form [MetaData.internalname -> value]
         */
        public Map<String, Variable> process(Simulation sim, Map<String, Variable> varinputs,
                                             Map<String, String> strinputs);

    }

    /**
     * Three allowable types of mapping
     *
     * @author jintrone
     */
    public static enum LinkType {
        /**
         * Map all values in the source to the specified input variable in the target
         */
        ALL,
        /**
         * Map only some of the values in the source to the specified input variable in the target
         */
        SOME,
        /**
         * Map only the last value in the source to the specified input variable in the target
         */
        MAX,
        /**
         * Map all of the values in the source to the specified input variable in the target
         */
        TO_ONE
    }

}
