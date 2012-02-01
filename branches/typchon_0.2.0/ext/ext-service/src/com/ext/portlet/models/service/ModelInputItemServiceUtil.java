package com.ext.portlet.models.service;


/**
 * <a href="ModelInputItemServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelInputItemService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelInputItemService
 *
 */
public class ModelInputItemServiceUtil {
    private static ModelInputItemService _service;

    public static ModelInputItemService getService() {
        if (_service == null) {
            throw new RuntimeException("ModelInputItemService is not set");
        }

        return _service;
    }

    public void setService(ModelInputItemService service) {
        _service = service;
    }
}
