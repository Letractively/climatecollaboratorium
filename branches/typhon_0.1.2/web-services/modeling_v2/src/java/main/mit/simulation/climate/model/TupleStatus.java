package mit.simulation.climate.model;

/**
 * Created by IntelliJ IDEA.
 * User: jintrone
 * Date: May 30, 2010
 * Time: 11:54:12 AM
 * To change this template use File | Settings | File Templates.
 */
public enum TupleStatus {

    OUT_OF_RANGE("@RANGE"), INVALID("@ERROR"), OK(null);

    private String code;

    TupleStatus(String strcode) {
      this.code = strcode;
    }

    public String getCode() {
        return code;
    }
}
