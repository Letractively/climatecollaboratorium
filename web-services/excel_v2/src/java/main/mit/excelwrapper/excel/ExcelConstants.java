package mit.excelwrapper.excel;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: Jun 23, 2010
 * Time: 1:34:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExcelConstants {

    public static enum Params {
        NAME,LABEL,DESCRIPTION,UNITS,VARCONTEXT,TYPE2,DATATYPE,EXTERNAL,DEFAULT,MIN,MAX,CATEGORIES,VALUE;

        public String toFieldName() {
            return name().toLowerCase();
        }
    }


    private static final String[] PARAM_PROPERTY_NAMES = new String[] {"name", "label", "description",
        "units", "varcontext", "type2", "datatype", "external", "default","min", "max", "categories", "value"};


    public static enum Format {
        SINGLE_SHEET(1), TWO_SHEET(2);
        private int minNumSheets;

        Format(int minNumSheets) {
            this.minNumSheets = minNumSheets;

        }

        public int getNumSheets() {
            return minNumSheets;
        }

        public int getWorksheetIndex(int baseindex, boolean isInput) {
            return (this==SINGLE_SHEET)?baseindex:isInput?baseindex:baseindex+1;
        }

        public int getStartRow(boolean isInput) {
           if (this == SINGLE_SHEET) {
               return isInput?0:Params.values().length;
           } else {
               return 0;
           }
        }

        public int getValueRow(boolean isInput) {
            return (this==TWO_SHEET)?Params.values().length-1:isInput?Params.values().length-1:Params.values().length*2-1;
        }


    }
}
