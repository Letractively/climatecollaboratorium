package org.climatecollaboratorium.pangaea.vensim;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.vensim.Vensim;

/**
 * Helper class for pangaea climate simulation service. It allows easy usage
 * of Vensim engine used for launching climate changes simulations. 
 *  
 * @author janusz
 *
 */
public class VensimHelper {
    
    /**
     * Logger.
     */
    private static Logger log = Logger.getLogger(VensimHelper.class);
    
    /**    
     * Runname used for testing.
     */
    private static final String V_RUNNAME = "test.vdf";
    
    /**
     * Command used to set runname.
     */
    private static final String V_SET_RUNNAME = "SIMULATE>RUNNAME|" + V_RUNNAME;
    
    /**
     * Command used to load model.
     */
    private static final String V_LOAD_MODEL = "SPECIAL>LOADMODEL|";
    
    /**
     * Command used to tell vensim to opearte only in memory without using files.
     */
    private static final String V_MEMFILE = "FILE>MEMFILE|1";
    
    /**
     * Command for running a model.
     */
    private static final String V_RUN = "MENU>RUN|o";
    
    /**
     * Command for setting input variable value. 
     */
    private static final String V_SET_VARIABLE = "SIMULATE>SETVAL|";

    /** 
     * Vensim instance.
     */
    private Vensim vensim;
    
    /**
     * Context id that will be used for all operations.
     */
    private int ctxId;
    
    
    /**
     * Initializes Vensim with passed lib name.
     * @param libName name of dll that should be used
     * @param modelPath path to model that should be loaded 
     * @throws VensimException when there is a problem with Vensim communication
     */
    public VensimHelper(String libName, String modelPath) throws VensimException {        
        log.debug("Initializing vensim for dll: " + libName);
        vensim = new Vensim(libName);
         
        if (vensim == null) {
            throw new RuntimeException(new Error("vensim wasn't properly initialized"));
        }

        log.debug("Vensim initialized properly");
        log.debug(getVensimInfo());
        
        // create new context that will be removed by a call to end() method
        ctxId = Vensim.ContextAdd(1);
        if (ctxId == 0) {
            throw new VensimException("Can't create new vensim context");
        }
        log.debug("Will be operating on context: " + ctxId);
        

        loadModel(modelPath);
        doCommand(V_MEMFILE);
        doCommand(V_SET_RUNNAME);
    }
    

    /**
     * Ends Vensim session by dropping context attached to current VensimHelper
     * instance.
     * 
     * @throws VensimException when there is a problem with dropping Vensim
     *  context
     */
    public void end() throws VensimException {
        validateContext();

        log.debug("Dropping context: " + ctxId);
        int ret = Vensim.ContextDrop(ctxId);
        if (ret == 0) {
            throw new VensimException("Context " + ctxId + " can't be dropped");
        }
        ctxId = 0;
    }
    
    /**
     * Runs the simulation.
     * @throws VensimException in case of any Vensim error
     */
    public void run() throws VensimException {
        validateContext();

        log.debug("Running the model");
        doCommand(V_RUN);
    }

    /**
     * Sets input variable to given value 
     * @param name variable name
     * @param val variable value
     * @throws VensimException in case of any Vensim error
     */
    public void setVariable(String name, float val) throws VensimException {
        validateContext();

        doCommand(V_SET_VARIABLE + name + "=" + val);
    }
    
    
    /**
     * Returns values for variable with given name. Returns empty 
     * array when variable wasn't found.
     * 
     * @param name variable name
     * @return array of variable values (can be empty)
     * @throws VensimException in case of any Vensim error
     */
    public float[] getVariable(String name) throws VensimException {
        validateContext();

        final int maxVal = 500;
        return getVariable(name, maxVal);
    }
    

    /**
     * Returns values for variable with given name. Returns empty 
     * array when variable wasn't found.
     * 
     * @param name variable name
     * @param maxVal maximal number of values that should be retrieved
     * @return array of variable values (can be empty)
     * @throws VensimException in case of any Vensim error
     */
    public float[] getVariable(String name, int maxVal) throws VensimException {
        validateContext();

        float[] varVal = new float[maxVal];
        float[] timeVal = new float[maxVal];
        
        log.debug("Looking for value of variable " + name);
        int ret = Vensim.CGetData(ctxId, V_RUNNAME, name, "", varVal, timeVal, maxVal);
        if (ret == 0) {
            log.debug("VensimVariable " + name + " wasn't found");
            return new float[0]; 
        }
        log.debug("Found " + ret + " values for variable " + name);
        return Arrays.copyOfRange(varVal, 150, ret);
    }
    
    /**
     * Executes given command against Vensim core.
     * 
     * @param command command that should be executed
     * @throws VensimException in case of any Vensim error
     */
    private void doCommand(String command) throws VensimException {
        int ret = Vensim.CCommand(ctxId, command);
        log.debug("Executing command: " + command + " ..........");
        
        if (ret == 0) {
            log.debug("FAILED");
            throw new VensimException("Error when executing command: " + command);
        }

        log.debug("SUCCESS");
        
    }
    
    /**
     * Loads given model.
     * @param modelPath path to model file that should be loaded
     * @throws VensimException in case of any Vensim error
     */
    private void loadModel(String modelPath) throws VensimException {
        doCommand(V_LOAD_MODEL + modelPath);
        
        String[] name = Vensim.CGetInfo(ctxId, Vensim.INFO_MODELNAME);
        log.debug("Loaded model " + name[0]);
    }

    /**
     * Map of available variable attributes together with their human readable counterparts,
     * it is used for retrieval of variable data in getVariableInfo method.
     */
    private final static Map<Integer, String> varAttributeType = new HashMap<Integer, String>();
    static {
        varAttributeType.put(Vensim.ATTRIB_ACTIVECAUSES, "activecauses");
        varAttributeType.put(Vensim.ATTRIB_CAUSES, "causes");
        varAttributeType.put(Vensim.ATTRIB_COMMENT, "comment");
        varAttributeType.put(Vensim.ATTRIB_EQUATIONS, "equations");
        varAttributeType.put(Vensim.ATTRIB_GROUP, "group");
        varAttributeType.put(Vensim.ATTRIB_INCREMENT, "increment");
        varAttributeType.put(Vensim.ATTRIB_INITCAUSES, "initcauses");
        varAttributeType.put(Vensim.ATTRIB_MAX, "max");
        varAttributeType.put(Vensim.ATTRIB_MIN, "min");
        varAttributeType.put(Vensim.ATTRIB_SUBALL, "suball");
        varAttributeType.put(Vensim.ATTRIB_SUBFAMILY, "subfamily");
        varAttributeType.put(Vensim.ATTRIB_SUBWORK, "subwork");
        varAttributeType.put(Vensim.ATTRIB_UNITS, "units");
        varAttributeType.put(Vensim.ATTRIB_USES, "uses");
        varAttributeType.put(Vensim.ATTRIB_VARTYPE, "vartype");
    }
    
    /**
     * Returns string with variable metadata.
     * @param name variable name
     * @return variable metadata
     */
    public String getVariableInfo(String name) {
        StringBuilder buf = new StringBuilder();
        buf.append("Info about variable: " + name + "\n");
        for (Integer varId: varAttributeType.keySet()) {
            String[] output = Vensim.CGetVarAttrib(ctxId, name, varId);
            if (output.length <= 0) continue;
            buf.append(varId + ". " + varAttributeType.get(varId) + "  ");
            for (String out: output) {
                buf.append(" [ " + out + " ] ");
            }
            buf.append("\n");
                
        }
        buf.append("\n");
        return buf.toString();
    }
    
    
    /**
     * Map of available Vensim  attributes together with their human readable counterparts,
     * it is used for retrieval of Vensim info in getVensimInfo method.
     */
    private final static Map<Integer, String> vensimInfoType = new HashMap<Integer, String>();
    static {
        vensimInfoType.put(Vensim.INFO_VERSION, "version");
        vensimInfoType.put(Vensim.INFO_DLL, "dll");
    }
    
    /**
     * Returns string with Vensim informations.
     * @return
     */
    public String getVensimInfo() {
        StringBuilder buf = new StringBuilder();
        buf.append("Vensim status: " + Vensim.check_status() + "\n");
        buf.append("Vensim info: ");
        
        for (Integer varId: vensimInfoType.keySet()) {
            String[] output = Vensim.CGetInfo(ctxId, varId);
            
            if (output.length <= 0) continue;
            buf.append(varId + " >> " + vensimInfoType.get(varId));
            
            for (String out: output) {
                buf.append(" [ " + out + " ] ");
            }
            buf.append("\n");
                
        }
        buf.append("\n");
        return buf.toString();
    }
    
    public String[] getVariables() {
        return Vensim.CGetVarNames(ctxId, "*", Vensim.VARTYPE_ALL);
    }
    
    /**
     * Checks if Vensim context was properly initialized, throws
     * exception if it wasn't
     * @throws VensimException if context wasn't properly initialized.
     */
    private void validateContext() throws VensimException { 
        if (ctxId <= 0) {
            throw new VensimException("There is no valid Vensim context available, trying to reuse the same instance twice?");
        }
    }

}
