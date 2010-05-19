package com.ext.portlet.debatemigration.service;


/**
 * <a href="DebateMigrationCategoryServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debatemigration.service.DebateMigrationCategoryService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debatemigration.service.DebateMigrationCategoryService
 *
 */
public class DebateMigrationCategoryServiceUtil {
    private static DebateMigrationCategoryService _service;

    public static DebateMigrationCategoryService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateMigrationCategoryService is not set");
        }

        return _service;
    }

    public void setService(DebateMigrationCategoryService service) {
        _service = service;
    }
}
