/*
 * Copyright (C) 2009 TopCoder Inc., All Rights Reserved.
 */
package mit.simulation.climate.exception;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

/**
 * <p>
 * Excel wrapper service error.
 * </p>
 * <p>
 * <b>Thread Safety</b> This is an exception class thus it is thread safe since it is initialized only once.
 * </p>
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class SimulationException extends WebApplicationException {
    /**
     * <code>Serial version UID.</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * <p>
     * The default ctor.
     * </p>
     */
    public SimulationException() {
        super(Responses.notFound().build());
    }

    /**
     * <p>
     * The ctor with a message.
     * </p>
     *
     * @param message the message to be displayed
     */
    public SimulationException(String message) {
        super(Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).entity(message).type("text/plain")
            .build());
    }

    /**
     * <p>
     * The ctor with an exception object.
     * </p>
     *
     * @param ex the exception
     */
    public SimulationException(Exception ex) {
        super(ex);
    }

    /**
     * <p>
     * Gets the error message.
     * </p>
     *
     * @return the error message
     */
    @Override
    public String getMessage() {
        if (getResponse() != null && getResponse().getEntity() instanceof String) {
            return getResponse().getEntity() + "";
        }
        return super.getMessage();
    }
}
