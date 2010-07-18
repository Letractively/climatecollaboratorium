package mit.simulation.climate.model.helper;

import org.apache.log4j.Logger;

import mit.simulation.climate.model.MetaData;


/**
 * Allows multiple sets of meta data so be combined to preserve the indexed variable
 * view in the xml file.  Combines indexed variables with their indices
 *
 * Note, in keeping with prior API, only some fields are combined.
 *
 * @author jintrone
 *
 */
public class CompositeMetaData implements MetaData {

    private final MetaData[] datas;
    private final MetaData primary;

    Logger log = Logger.getLogger(CompositeMetaData.class);

    /**
     * Constructor
     *
     * @param primary  The variable that is the indexed value
     * @param datas This list of variables that form the columns of the matrix in the output
     * set.  Generally, only two elements, consisting of an index and the primary variable itself
     * This API could be tightened up at this point to only support an index and an indexed value
     *
     */
    public CompositeMetaData(MetaData primary, MetaData...datas) {
        log.debug("Setting primary to +"+primary);
        StringBuffer buffer = new StringBuffer();
        for (MetaData md:datas) {
            buffer.append(md+",");
        }
        log.debug("attached data is "+buffer.toString());
        this.datas = datas;
        this.primary = primary;
    }

    @Override
    public String[] getCategories() {
        return primary.getCategories();
    }

    @Override
    public String[] getDefault() {
        String[] result = new String[datas.length];
        for (int i =0;i<datas.length;i++) {
            String[] x = datas[i].getDefault();
            result[i] = x==null?null:x[0];
        }
        return result;
    }

    @Override
    public String getDescription() {
        return primary.getDescription();
    }

    @Override
    public String getExternalInfo() {
        return primary.getDescription();
    }

    @Override
    public Long getId() {
        return primary.getId();
    }

    @Override
    public String getInternalName() {
        return primary.getInternalName();
    }

    @Override
    public String[] getLabels() {
        String[] result = new String[datas.length];
        for (int i =0;i<datas.length;i++) {
            String[] x = datas[i].getLabels();
            result[i] = x==null?null:x[0];
            //result[i] = datas[i].getLabels()[0];
        }
        return result;
    }

    @Override
    public String[] getMax() {
        String[] result = new String[datas.length];
        for (int i =0;i<datas.length;i++) {
            String[] x = datas[i].getMax();
            result[i] = x==null?null:x[0];
            //result[i] = datas[i].getMax()[0];
        }
        return result;
    }

    @Override
    public String[] getMin() {
        String[] result = new String[datas.length];
        for (int i =0;i<datas.length;i++) {
            String[] x = datas[i].getMin();
            result[i] = x==null?null:x[0];
            //result[i] = datas[i].getMin()[0];
        }
        return result;
    }

    @Override
    public String getName() {
        return primary.getName();
    }

    @Override
    public Class<Object>[] getProfile() {
        Class[] result = new Class[datas.length];
        for (int i =0;i<datas.length;i++) {
            Class[] x = datas[i].getProfile();
            result[i] = x==null?null:x[0];
            //result[i] = datas[i].getProfile()[0];
        }
        return result;
    }

    @Override
    public String[] getUnits() {
        String[] result = new String[datas.length];
        for (int i =0;i<datas.length;i++) {
            String[] x = datas[i].getUnits();
            result[i] = x==null?null:x[0];
            //result[i] = datas[i].getUnits()[0];
        }
        return result;
    }

    @Override
    public VarContext getVarContext() {
        return primary.getVarContext();
    }

    @Override
    public VarType getVarType() {
        return primary.getVarType();
    }

    @Override
    public boolean isIndex() {
        return primary.isIndex();
    }

    @Override
    public boolean isInRange(String[] values) {
        return primary.isInRange(values);
    }

    @Override
    public void setCategories(String[] categories) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setDefault(String[] n) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setDescription(String desc) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setExternalInfo(String info) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setInternalName(String name) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }



    @Override
    public void setLabels(String[] lables) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setMax(String[] n) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setMin(String[] n) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setName(String name) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setProfile(Class<Object>[] profile) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setUnits(String[] units) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setVarContext(VarContext t) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public void setVarType(VarType t) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

    @Override
    public MetaData getIndexingMetaData() {
        return primary.getIndexingMetaData();
    }

    @Override
    public void setIndexingMetaData(MetaData md) {
        throw new RuntimeException("Operation not supported on combined MetaData");

    }

}
