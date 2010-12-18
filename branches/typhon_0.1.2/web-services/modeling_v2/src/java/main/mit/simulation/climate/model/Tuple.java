package mit.simulation.climate.model;


/**
 * Simple array of values
 *
 * @author jintrone
 *
 */
public interface Tuple {

	public String[] getValues();
	public void setValues(String[] vals);

    public TupleStatus getStatus();

}
