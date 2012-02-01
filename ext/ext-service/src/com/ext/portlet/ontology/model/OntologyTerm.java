package com.ext.portlet.ontology.model;


/**
 * <a href="OntologyTerm.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>OntologyTerm</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.ontology.model.impl.OntologyTermImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.OntologyTermModel
 * @see com.ext.portlet.ontology.model.impl.OntologyTermImpl
 * @see com.ext.portlet.ontology.model.impl.OntologyTermModelImpl
 *
 */
public interface OntologyTerm extends OntologyTermModel {
    public void store() throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm getParent()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public int getChildTermsCount() throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getChildTerms()
        throws com.liferay.portal.SystemException;
}
