package com.ext.portlet.models.service;


/**
 * <a href="ModelGlobalPreferenceServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.models.service.ModelGlobalPreferenceService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.models.service.ModelGlobalPreferenceService
 *
 */
public class ModelGlobalPreferenceServiceUtil {
    private static ModelGlobalPreferenceService _service;

    public static ModelGlobalPreferenceService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "ModelGlobalPreferenceService is not set");
        }

        return _service;
    }

    public void setService(ModelGlobalPreferenceService service) {
        _service = service;
    }
}
