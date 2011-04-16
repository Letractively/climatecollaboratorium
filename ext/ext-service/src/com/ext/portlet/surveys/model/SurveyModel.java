package com.ext.portlet.surveys.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="SurveyModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>Survey</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.surveys.model.Survey
 * @see com.ext.portlet.surveys.model.impl.SurveyImpl
 * @see com.ext.portlet.surveys.model.impl.SurveyModelImpl
 *
 */
public interface SurveyModel extends BaseModel<Survey> {
    public String getPrimaryKey();

    public void setPrimaryKey(String pk);

    public String getEventName();

    public void setEventName(String eventName);

    public String getDescription();

    public void setDescription(String description);

    public String getUrl();

    public void setUrl(String url);

    public String getType();

    public void setType(String type);

    public Survey toEscapedModel();
}
