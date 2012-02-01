package com.ext.portlet.landingPage.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;


/**
 * <a href="LandingPageModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>LandingPage</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.landingPage.model.LandingPage
 * @see com.ext.portlet.landingPage.model.impl.LandingPageImpl
 * @see com.ext.portlet.landingPage.model.impl.LandingPageModelImpl
 *
 */
public interface LandingPageModel extends BaseModel<LandingPage> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getId();

    public void setId(Long id);

    public String getBaseUrl();

    public void setBaseUrl(String baseUrl);

    public String getTargetUrl();

    public void setTargetUrl(String targetUrl);

    public Date getUpdated();

    public void setUpdated(Date updated);

    public LandingPage toEscapedModel();
}
