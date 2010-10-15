package mit.simulation.climate.model.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mit.simulation.climate.model.MetaData;
import mit.simulation.climate.model.Tuple;
import mit.simulation.climate.model.Variable;

/**
 * Allows multiple variables to be combined to preserve the indexed variable "view"
 *
 * @see CompositeMetaData
 *
 * @author jintrone
 *
 */
public class CompositeVariable implements Variable {

	private final List<Variable> vars = new ArrayList<Variable>();
	private final Variable primary;

	private static Logger log = Logger.getLogger(CompositeVariable.class);

	/**
	 * Constructor
	 *
	 * @param primary  The variable that is the indexed value
	 * @param indexvar  The variable that is the index
	 * @param valuevars  A list of variables not including the index that are indexed by the index.  This API needs cleaning up.
	 */
	public CompositeVariable(Variable primary, Variable indexvar, Variable... valuevars) {
		if (!indexvar.getMetaData().isIndex()) throw new RuntimeException("First variable must be an index");
		this.vars.add(indexvar);
		for (Variable v : valuevars) {
			this.vars.add(v);
		}
		log.debug("Index is "+indexvar);
		log.debug("Composite variable contains: "+this.vars);
		this.primary =primary;
	}

	@Override
	public void addValue(Tuple t) {
		throw new RuntimeException("Cannot add tuples to composite class");
	}

	@Override
	public String getId() {
		return primary.getId();
	}

	@Override
	public MetaData getMetaData() {
		MetaData[] result = new MetaData[vars.size()];
		int i = 0;
		for (Variable v:vars) {
			result[i++] = v.getMetaData();
		}
		return new CompositeMetaData(primary.getMetaData(),result);
	}

	@Override
	public List<Tuple> getValue() {
		log.debug("Get value for "+getMetaData().getId());
		List<Tuple> result = new ArrayList<Tuple>();

		List<Tuple>[] srcs = (List<Tuple>[])new List[vars.size()];
		int i = 0;
		int length = -1;
		for (Variable v:vars) {
			srcs[i++] = v.getValue();
			length = Math.max(length, v.getValue().size());
		}

		//walking down elements in the lists
		for (i = 0;i<length;i++ ) {

			//holder - one spot for each src
			String[] vals = new String[srcs.length];

			//flag if we need to skip a row due to an empty value
			boolean skip = false;
			//walking across sources
			for (int j=0;j<srcs.length;j++) {

				//presume one value per
				Tuple t = srcs[j].size() > i? srcs[j].get(i):null;
				if (t == null) {
					log.error("Not enough tuples (non-existent index"+i+" in variable "+vars.get(j).getMetaData().getId()+":"+vars.get(j).getMetaData().getName()+") to index; skipping "+this.getMetaData().getName()+":"+this.getMetaData().getId());
				}


				String[] val =  srcs[j].get(i).getValues();
				if (val.length == 0 || val[0] == null) {
					log.debug("Detected filler, skipping");
					skip = true;
					break;
				} else {
					vals[j] =val[0];
				}
			}
			if (!skip) result.add(new SimpleTuple(vals));
		}
		log.debug(primary.getMetaData().getName()+":"+result);
		return result;
	}

	@Override
	public void setMetaData(MetaData md) {
		throw new RuntimeException("Cannot add tuples to composite class");

	}

}
