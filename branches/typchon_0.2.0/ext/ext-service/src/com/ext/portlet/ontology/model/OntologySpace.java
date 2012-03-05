package com.ext.portlet.ontology.model;


/**
 * <a href="OntologySpace.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>OntologySpace</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.ontology.model.impl.OntologySpaceImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.ontology.model.OntologySpaceModel
 * @see com.ext.portlet.ontology.model.impl.OntologySpaceImpl
 * @see com.ext.portlet.ontology.model.impl.OntologySpaceModelImpl
 *
 */
public interface OntologySpace extends OntologySpaceModel {
    public void store() throws com.liferay.portal.SystemException;

    public com.ext.portlet.ontology.model.OntologyTerm getTopTerm()
        throws com.liferay.portal.SystemException;
}
