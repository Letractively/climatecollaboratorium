package com.ext.portlet.plans.model;


/**
 * <a href="PlanTemplate.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanTemplate</code> table
 * in the database.
 * </p>
 *
 * <p>
 * Customize <code>com.ext.portlet.plans.model.impl.PlanTemplateImpl</code>
 * and rerun the ServiceBuilder to generate the new methods.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanTemplateModel
 * @see com.ext.portlet.plans.model.impl.PlanTemplateImpl
 * @see com.ext.portlet.plans.model.impl.PlanTemplateModelImpl
 *
 */
public interface PlanTemplate extends PlanTemplateModel {
    public void store() throws com.liferay.portal.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> getSections()
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void addSection(
        com.ext.portlet.plans.model.PlanSectionDefinition section)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void removeSection(
        com.ext.portlet.plans.model.PlanSectionDefinition section)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;

    public void updateSectionWeight(
        com.ext.portlet.plans.model.PlanSectionDefinition section, int weight)
        throws com.liferay.portal.PortalException,
            com.liferay.portal.SystemException;
}
