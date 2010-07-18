package mit.simulation.climate.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import mit.simulation.climate.model.jaxb.MetaDataJAXBDelegate;


/**
 * MetaData is used to describe the inputs and outputs in a simulation
 *
 * @author jintrone
 *
 */
@XmlJavaTypeAdapter(MetaDataJAXBDelegate.Adapter.class)
public interface MetaData extends HasId {

    /**
     * Describes how meta data relates to other meta data in the set
     * @author jintrone
     *
     */
    public enum VarContext {
        /**
         * Indicates that the variable is an index
         */
        INDEX,
        /**
         * Indicates that the variable is indexed by the INDEX
         */
        INDEXED,
        /**
         * Indicates that the variable is a standalone value
         */
        SCALAR,

        /**
         * Indicates that this variable is a list
         */
        LIST
    }

    /**
     * Describes how this variable should be interpreted
     * @author jintrone
     *
     */
    public enum VarType {
        /**
         * This variable lies on a continuous range between a min and a max
         */
        RANGE,
        /**
         * This variable is categorical, and take one of a finite number of values
         */
        CATEGORICAL,
        /**
         * This variable is not interpreted; assumed to be free text
         */
        FREE,
        /**
         * This variable is used as input.  It lies on a continuous range between a min
         * and a max.  However, if it is non-integral, the two integer values bounding
         * this value will be used as input, and you will receive two results.
         */
        FUZZY_DISCRETE
    }

    public Long getId();


    /**
     * The MetaData that is an index for this MetaData (if this one is INDEXED)
     * @return
     */
    public MetaData getIndexingMetaData();
    public void setIndexingMetaData(MetaData md);

    public String getName();
    public void setName(String name);


    public String getInternalName();
    public void setInternalName(String name);

    /**
     * The units for this MetaData - should not be an array!
     * @return
     */
    public String[] getUnits();
    public void setUnits(String[] units);

    /**
     * The profile (datatype) for this MetaData - should not be an array!
     * @return
     */
    public Class<Object>[] getProfile();
    public void setProfile(Class<Object>[] profile);


    /**
     * The label for this MetaData - should not be an array!
     * @return
     */
    public String[] getLabels();
    public void setLabels(String[] lables);

    /**
     * The max value for this MetaData - should not be an array!
     * @return
     */
    public String[] getMax();
    public void setMax(String[] n);

    /**
     * The min value for this MetaData - should not be an array!
     * @return
     */
    public String[] getMin();
    public void setMin(String[] n);

    /**
     * The default value for this MetaData - should not be an array!
     * @return
     */
    public String[] getDefault();
    public void setDefault(String[] n);

    public String[] getCategories();


    public String getDescription();
    public void setDescription(String desc);

    public VarContext getVarContext();
    public void setVarContext(VarContext t);

    public VarType getVarType();
    public void setVarType(VarType t);

    public void setExternalInfo(String info);
    public String getExternalInfo();

    public boolean isIndex();


    public boolean isInRange(String[] values);

    public static class Utils {

        public static String formatNumber(Class cls, String actual) {
			if (cls == null || actual == null) return actual;

			if (cls.equals(java.lang.Integer.class)) {
				return ((Double)Double.parseDouble(actual)).intValue()+"";
			} else if (cls == java.lang.Float.class | cls==java.lang.Double.class) {
				return Double.parseDouble(actual)+"";
			}
            return actual;
        }


        public static <T> T convertToValue(Class<T> cls, String number) {
           if (cls.equals(java.lang.Integer.class)) {
			   try {
                return (T)((Integer)((Double)Double.parseDouble(number)).intValue());
               } catch (NumberFormatException e) {
                   return null;
               }
			} else if (cls == java.lang.Float.class || cls==java.lang.Double.class) {
               try {
				return (T)((Double)Double.parseDouble(number));
               } catch (NumberFormatException e) {
                   return null;
               }
			} else if (cls.equals(String.class)) {
               return (T)number;
           } else return null;
        }

		public static String[] convertArray(String[] array, MetaData md) {
			String[] result = new String[array.length];

			for (int i =0;i<array.length;i++) {
				result[i] = formatNumber(md.getProfile()[i],array[i]);
			}
			return result;
		}

        // new edit
//        public static String[] convertArray(String[] array, MetaData md) {
//            String[] result = new String[array.length];
//            for (int i =0;i<array.length;i++) {
//                result[i] = formatNumber(new String(array[i]));
//            }
//            return result;
//        }

       
    }

    void setCategories(String[] categories);




}
