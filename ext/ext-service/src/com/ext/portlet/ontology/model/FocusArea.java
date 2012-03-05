package com.ext.portlet.ontology.model;


/**
 * <a href="FocusArea.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>FocusArea</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.ontology.model.impl.FocusAreaImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.FocusAreaModel
 * @see com.ext.portlet.ontology.model.impl.FocusAreaImpl
 * @see com.ext.portlet.ontology.model.impl.FocusAreaModelImpl
 *
 */
public interface FocusArea extends FocusAreaModel {
    public void store() throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getTerms()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void removeTerm(java.lang.Long termId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void addTerm(java.lang.Long termId)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void tagClass(java.lang.Class clasz, java.lang.Long pk)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
