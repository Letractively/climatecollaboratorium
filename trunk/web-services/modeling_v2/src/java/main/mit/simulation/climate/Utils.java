/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate;

import mit.simulation.climate.model.Tuple;
import mit.simulation.climate.model.TupleStatus;
import org.apache.commons.lang.StringUtils;

import mit.simulation.climate.model.EntityState;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Utils class.
 * </p>
 * <p>
 * <b>Thread Safety</b> This is an util class thus it is thread safe.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public final class Utils {
    /**
     * <p>
     * Private ctor.
     * </p>
     */
    private static Logger log = Logger.getLogger(Utils.class);



    private Utils() {
        // do nothing
    }

    /**
     * <p>
     * Checks to object is null or not. It is used to check method parameter.
     * </p>
     *
     * @param object the object value
     * @param objectName the object name
     *
     * @throws IllegalArgumentException if the object is null
     */
    public static void checkNull(Object object, String objectName) {
        if (object == null) {
            throw new IllegalArgumentException(objectName + " should not be null.");
        }
    }

    /**
     * <p>
     * Gets the entity state from state string.
     * </p>
     *
     * @param stateString state string
     * @return the entity state value.
     */
    public static EntityState getEntityStateFromString(String stateString) {
        if (StringUtils.isBlank(stateString)) {
            return EntityState.PUBLIC;
        }

        try {
            return EntityState.valueOf(stateString);
        } catch (IllegalArgumentException e) {
            // ignore and we will use PUBLIC value
            return EntityState.PUBLIC;
        }
    }

    public static String extractTupleListString(List<Tuple> vals, int idx, String nullval) {
        StringBuffer buffer = new StringBuffer();
        for (Tuple t:vals) {
            if (t.getStatus() == TupleStatus.INVALID || t.getValues()[idx]==null) {
                log.info("Encountered a null tuple value, skipping");
                buffer.append("[").append(nullval).append("]");
            } else {
                buffer.append("[").append(t.getValues()[idx]).append("]");
            }
        }
        return buffer.toString();

    }

    public static String[] parseBracketedString(String s) {
        Pattern p = Pattern.compile("\\[([^\\[^\\]]+)\\]");
        Matcher m = p.matcher(s);
        List<String> result = new ArrayList<String>();
        while (m.find()) {
            result.add(m.group(1));
        }
        return (String[]) result.toArray(new String[0]);
    }
}
