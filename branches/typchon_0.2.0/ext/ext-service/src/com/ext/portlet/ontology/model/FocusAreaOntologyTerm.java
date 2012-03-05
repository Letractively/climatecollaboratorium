package com.ext.portlet.ontology.model;


/**
 * <a href="FocusAreaOntologyTerm.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>FocusAreaOntologyTerm</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.FocusAreaOntologyTermModel
 * @see com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermImpl
 * @see com.ext.portlet.ontology.model.impl.FocusAreaOntologyTermModelImpl
 *
 */
public interface FocusAreaOntologyTerm extends FocusAreaOntologyTermModel {
    public void store() throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm getTerm()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.FocusArea getArea()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
