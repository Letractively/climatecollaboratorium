package com.ext.portlet.debaterevision.service;


/**
 * <a href="DebateItemReferenceLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class provides static methods for the
 * <code>com.ext.portlet.debaterevision.service.DebateItemReferenceLocalService</code>
 * bean. The static methods of this class calls the same methods of the bean
 * instance. It's convenient to be able to just write one line to call a method
 * on a bean instead of writing a lookup call and a method call.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.service.DebateItemReferenceLocalService
 *
 */
public class DebateItemReferenceLocalServiceUtil {
    private static DebateItemReferenceLocalService _service;

    public static com.ext.portlet.debaterevision.model.DebateItemReference addDebateItemReference(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException {
        return getService().addDebateItemReference(debateItemReference);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference createDebateItemReference(
        java.lang.Long debateItemReferencePK) {
        return getService().createDebateItemReference(debateItemReferencePK);
    }

    public static void deleteDebateItemReference(
        java.lang.Long debateItemReferencePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        getService().deleteDebateItemReference(debateItemReferencePK);
    }

    public static void deleteDebateItemReference(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException {
        getService().deleteDebateItemReference(debateItemReference);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    public static java.util.List<Object> dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference getDebateItemReference(
        java.lang.Long debateItemReferencePK)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException {
        return getService().getDebateItemReference(debateItemReferencePK);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> getDebateItemReferences(
        int start, int end) throws com.liferay.portal.SystemException {
        return getService().getDebateItemReferences(start, end);
    }

    public static int getDebateItemReferencesCount()
        throws com.liferay.portal.SystemException {
        return getService().getDebateItemReferencesCount();
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference updateDebateItemReference(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference)
        throws com.liferay.portal.SystemException {
        return getService().updateDebateItemReference(debateItemReference);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference updateDebateItemReference(
        com.ext.portlet.debaterevision.model.DebateItemReference debateItemReference,
        boolean merge) throws com.liferay.portal.SystemException {
        return getService().updateDebateItemReference(debateItemReference, merge);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference createNew(
        java.lang.String text, java.lang.String url)
        throws com.liferay.portal.SystemException {
        return getService().createNew(text, url);
    }

    public static com.ext.portlet.debaterevision.model.DebateItemReference createNewFrom(
        com.ext.portlet.debaterevision.model.DebateItem item,
        com.ext.portlet.debaterevision.model.DebateItemReference template)
        throws com.liferay.portal.SystemException {
        return getService().createNewFrom(item, template);
    }

    public static java.util.List<com.ext.portlet.debaterevision.model.DebateItemReference> getDebateItemReferences(
        com.ext.portlet.debaterevision.model.DebateItem item)
        throws com.liferay.portal.SystemException {
        return getService().getDebateItemReferences(item);
    }

    public static DebateItemReferenceLocalService getService() {
        if (_service == null) {
            throw new RuntimeException(
                "DebateItemReferenceLocalService is not set");
        }

        return _service;
    }

    public void setService(DebateItemReferenceLocalService service) {
        _service = service;
    }
}
