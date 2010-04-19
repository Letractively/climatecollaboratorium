/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate;

import org.apache.commons.lang.StringUtils;

import mit.simulation.climate.model.EntityState;

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
}
