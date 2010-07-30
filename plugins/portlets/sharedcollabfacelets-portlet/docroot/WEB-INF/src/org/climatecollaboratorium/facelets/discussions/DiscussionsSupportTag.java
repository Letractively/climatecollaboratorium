package org.climatecollaboratorium.facelets.discussions;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import org.climatecollaboratorium.facelets.discussions.permissions.DiscussionsPermissions;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.FaceletException;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DiscussionsSupportTag extends TagHandler{
    private final TagAttribute discussionBeanParam;
    private final TagAttribute discussionIdParam;
    private final TagAttribute permissionsParam;
    
    private static Log _log = LogFactoryUtil.getLog(DiscussionsSupportTag.class);

    public DiscussionsSupportTag(TagConfig config) {
        super(config);
        discussionBeanParam = this.getRequiredAttribute("discussionBean");
        discussionIdParam = this.getRequiredAttribute("discussionId");
        permissionsParam = this.getAttribute("permissions");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException,
            ELException {

        DiscussionBean discussionBean = (DiscussionBean) discussionBeanParam.getObject(ctx);
        Long discussionId = (Long) discussionIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        DiscussionsPermissions permissions = permissionsParam == null ? null : (DiscussionsPermissions) permissionsParam.getObject(ctx, DiscussionsPermissions.class);
        try {
            if (discussionBean.init(discussionId, permissions)) {
                // initialization succeded
                nextHandler.apply(ctx, parent);
            }
        }
        catch (SystemException e) {
            _log.error(e);
            throw new FaceletException(e);
        } catch (PortalException e) {
            // TODO Auto-generated catch block
            _log.error(e);
            throw new FaceletException(e);
        }
        
    }
    
    

}
