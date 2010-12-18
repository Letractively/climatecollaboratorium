package com.ext.portlet.models.service;


/**
 * <a href="ModelOutputChartOrderServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelOutputChartOrderService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelOutputChartOrderService
 *
 */
public class ModelOutputChartOrderServiceUtil {
    private static ModelOutputChartOrderService _service;

    public static ModelOutputChartOrderService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ModelOutputChartOrderService is not set");
        }

        return _service;
    }

    public void setService(ModelOutputChartOrderService service) {
        _service = service;
    }
}
