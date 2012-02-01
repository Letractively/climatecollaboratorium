package com.ext.portlet.plans.service;


/**
 * <a href="PlanSectionDefinitionServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.plans.service.PlanSectionDefinitionService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.service.PlanSectionDefinitionService
 *
 */
public class PlanSectionDefinitionServiceUtil {
    private static PlanSectionDefinitionService _service;

    public static PlanSectionDefinitionService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "PlanSectionDefinitionService is not set");
        }

        return _service;
    }

    public void setService(PlanSectionDefinitionService service) {
        _service = service;
    }
}
