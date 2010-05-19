package com.ext.portlet.debaterevision.model;

import com.liferay.portal.model.BaseModel;


/**
 * <a href="DebateItemReferenceModel.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the <code>DebateItemReference</code>
 * table in the database.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.ext.portlet.debaterevision.model.DebateItemReference
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemReferenceImpl
 * @see com.ext.portlet.debaterevision.model.impl.DebateItemReferenceModelImpl
 *
 */
public interface DebateItemReferenceModel extends BaseModel<DebateItemReference> {
    public Long getPrimaryKey();

    public void setPrimaryKey(Long pk);

    public Long getDebateItemReferencePK();

    public void setDebateItemReferencePK(Long debateItemReferencePK);

    public Long getDebateItemId();

    public void setDebateItemId(Long debateItemId);

    public Long getDebateId();

    public void setDebateId(Long debateId);

    public Long getItemVersion();

    public void setItemVersion(Long itemVersion);

    public String getStatus();

    public void setStatus(String status);

    public String getText();

    public void setText(String text);

    public String getUrl();

    public void setUrl(String url);

    public DebateItemReference toEscapedModel();
}
