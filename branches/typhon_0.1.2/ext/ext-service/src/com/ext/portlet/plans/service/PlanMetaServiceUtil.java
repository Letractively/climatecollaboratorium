package com.ext.portlet.plans.service;


/**
 * <a href="PlanMetaServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanMetaService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanMetaService
 *
 */
public class PlanMetaServiceUtil {
    private static PlanMetaService _service;

    public static PlanMetaService getService() {
        if (_service == null) {
            throw new RuntimeException("PlanMetaService is not set");
        }

        return _service;
    }

    public void setService(PlanMetaService service) {
        _service = service;
    }
}
