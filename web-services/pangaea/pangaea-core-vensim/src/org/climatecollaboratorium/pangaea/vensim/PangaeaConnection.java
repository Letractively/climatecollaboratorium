package org.climatecollaboratorium.pangaea.vensim;

import org.apache.log4j.Logger;
import org.climatecollaboratorium.pangaea.vensim.SimulationResults.ScalarElement;

import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author janusz.
 */
public class PangaeaConnection {

    /**
     * Logger.
     */
    private static Logger log = Logger.getLogger(PangaeaConnection.class);

    /**
     * Name of libname system property from which vensim libname will
     * be taken.
     */
    private static final String DLL_LIBNAME_PARAM = "vensim_lib_name";

    /**
     * Name of vensim model path system property from which vensim model path will
     * be taken.
     */
    private static final String MODEL_PATH_PARAM = "vensim_model_path";

    /**
     * Parameters for initial and final time.
     */
    private static final String INITIAL_TIME_VARNAME = "INITIAL TIME";
    private static final float INITIAL_TIME_VALUE = 2000;

    private static final String FINAL_TIME_VARNAME = "FINAL TIME";
    private static final float FINAL_TIME_VALUE = 2100;

    private static final String EMISSIONS_REFERENCE_YEAR_VARNAME = "Emissions reference year";
    private static final float EMISSIONS_REFERENCE_YEAR_VALUE = 2005;
    
    private static final String APPLY_TO_CO2E="Apply to CO2eq";
    private static final float APPLY_TO_CO2E_VALUE=1;

    /**
     * Helper for Vensim communication.
     */
    private VensimHelper vensimHelper;

    /**
     * Initializes vensim.
     */
    public PangaeaConnection() {
        String libName = System.getProperty(DLL_LIBNAME_PARAM);
        String modelPath = System.getProperty(MODEL_PATH_PARAM);
        try {
            System.out.println("creating new vensim helper with lib"+libName+" and path "+modelPath);

            vensimHelper = new VensimHelper(libName, modelPath);
            System.out.println("creating new vensim helper " + vensimHelper);
        }
        catch (VensimException e) {
            log.error("An exception was thrown when initializing Vensim", e);
            e.printStackTrace();
            // exception should be thrown, but in order to be consistent with old interface nothing happens
            //throw new PangaeaException("An exception was thrown when initializing Vensim", e);
        }
    }


    /**
     * Runs simulation
     *
     * @param input inputs that should be used as model parameters
     * @return simulation results
     */
    public SimulationResults submit(SimulationInput input) {
        SimulationResults result = new SimulationResults();
        try {

	    //   vensimHelper.setVariable(EMISSIONS_REFERENCE_YEAR_VARNAME,EMISSIONS_REFERENCE_YEAR_VALUE);
	    vensimHelper.setVariable(APPLY_TO_CO2E,APPLY_TO_CO2E_VALUE);
	    
            for (SimulationInput.InputVariable var : input.getAllVariables().keySet()) {
                System.out.println(var.internalName + " " + input.getValue(var));
                System.out.println("vensim helper: " + vensimHelper);
                vensimHelper.setVariable(var.internalName, input.getValue(var).floatValue());
            }

            // this doesn't work
            //vensimHelper.setVariable("TIME STEP", 2);
            File file = new File(".");
            System.out.println(file.getAbsolutePath());

            //vensimHelper.setVariable(INITIAL_TIME_VARNAME, INITIAL_TIME_VALUE);
            //vensimHelper.setVariable(FINAL_TIME_VARNAME, FINAL_TIME_VALUE);

            vensimHelper.run();

            log.debug("Retrieving results");
            for (SimulationResults.VensimVariable vv : SimulationResults.VensimVariable.values()) {
                    float[] val = vensimHelper.getVariable(vv.vensimName);
                    System.out.println("Adding data points for "+vv);
                    result.addDataPoints(vv, val);

                    log.debug(printArray(vv.vensimName, val));
                }

            vensimHelper.end();

            for (SimulationResults.CompositeVariable cv: SimulationResults.CompositeVariable.values()) {
                result.addDataPoints(cv,cv.process(result));
		
            }
        } catch (VensimException e) {

            log.error("An exception was thrown when accessing Vensim", e);
            // exception should be thrown, but in order to be consistent with old interface nothing happens
            //throw new PangaeaException("An exception was thrown when accessing Vensim", e);
        }
        return result;

    }


    /**
     * @param args the command line arguments
     * @throws VensimException
     */
    public static void main(String[] args) throws VensimException {


        long before = System.currentTimeMillis();
        String libName = System.getProperty(DLL_LIBNAME_PARAM);
        String modelPath = System.getProperty(MODEL_PATH_PARAM);

        if (libName == null) {
            libName = "vendll32";
        }
        if (modelPath == null) {
            modelPath = "/home/janusz/workdir/liferay/vensim/vensim_jni/clearn.vmf";
        }

        System.setProperty(DLL_LIBNAME_PARAM, libName);
        System.setProperty(MODEL_PATH_PARAM, modelPath);

        if (args.length > 0 && args[0].equals("info")) {
            System.out.println(new VensimHelper(libName, modelPath).getVensimInfo());
        } else if (args.length > 0 && args[0].equals("vars")) {
            VensimHelper helper = new VensimHelper(libName, modelPath);
            String[] vars = helper.getVariables();
            for (String var : vars) {
                System.out.println(helper.getVariableInfo(var));
            }
        } else {

            File f = new File(".");
            System.out.println(f.getAbsolutePath());

            PangaeaConnection conn = new PangaeaConnection();

            System.out.println("Retrieving results");
            SimulationResults results = conn.submit(new SimulationInput());
            List<Variable> vars = new ArrayList(Arrays.asList(SimulationResults.VensimVariable.values()));
            vars.addAll(Arrays.asList(SimulationResults.CompositeVariable.values()));

            for (org.climatecollaboratorium.pangaea.vensim.Variable v : vars) {
                List<ScalarElement> values = results.get(v);
                System.out.print(v.getInternalName() + ": [");
                if (values != null) {
                    for (ScalarElement val : values) {
                        System.out.print(val + ", ");
                    }
                }
                System.out.println("]");
            }

        }
        System.out.println("Execution time: " + (System.currentTimeMillis() - before));
    }


    /**
     * Returns string representing variable and its values.
     *
     * @param name name of variable that is being printed
     * @param val  array of variable values
     * @return string representing variable and its values
     */
    private static String printArray(String name, float[] val) {
        StringBuilder buf = new StringBuilder();
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(1);
        buf.append(name + ": [");
        for (int i = 0; i < val.length; i++) {
            buf.append(format.format(val[i]));
            if (i < val.length - 1) {
                buf.append(", ");
            }
        }
        buf.append("]\n");
        return buf.toString();
    }
}
