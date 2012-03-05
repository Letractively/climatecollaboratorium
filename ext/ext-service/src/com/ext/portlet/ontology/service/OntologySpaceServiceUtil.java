package com.ext.portlet.ontology.service;


/**
 * <a href="OntologySpaceServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.ontology.service.OntologySpaceService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.service.OntologySpaceService
 *
 */
public class OntologySpaceServiceUtil {
    private static OntologySpaceService _service;

    public static OntologySpaceService getService() {
        if (_service == null) {
            throw new RuntimeException("OntologySpaceService is not set");
        }

        return _service;
    }

    public void setService(OntologySpaceService service) {
        _service = service;
    }
}
