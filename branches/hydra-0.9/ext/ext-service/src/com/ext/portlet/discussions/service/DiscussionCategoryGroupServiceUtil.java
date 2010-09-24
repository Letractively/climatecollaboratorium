package com.ext.portlet.discussions.service;


/**
 * <a href="DiscussionCategoryGroupServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.discussions.service.DiscussionCategoryGroupService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.discussions.service.DiscussionCategoryGroupService
 *
 */
public class DiscussionCategoryGroupServiceUtil {
    private static DiscussionCategoryGroupService _service;

    public static DiscussionCategoryGroupService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DiscussionCategoryGroupService is not set");
        }

        return _service;
    }

    public void setService(DiscussionCategoryGroupService service) {
        _service = service;
    }
}