package com.ext.portlet.plans.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="PlanSectionDefinitionModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>PlanSectionDefinition</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.plans.model.PlanSectionDefinition
 * @see com.ext.portlet.plans.model.impl.PlanSectionDefinitionImpl
 * @see com.ext.portlet.plans.model.impl.PlanSectionDefinitionModelImpl
 *
 */
public interface PlanSectionDefinitionModel extends BaseModel<PlanSectionDefinition> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public String getTitle();

    public void setTitle(String title);

    public String getDefaultText();

    public void setDefaultText(String defaultText);

    public String getHelpText();

    public void setHelpText(String helpText);

    public Integer getCharacterLimit();

    public void setCharacterLimit(Integer characterLimit);

    public Long getFocusAreaId();

    public void setFocusAreaId(Long focusAreaId);

    public PlanSectionDefinition toEscapedModel();
}
