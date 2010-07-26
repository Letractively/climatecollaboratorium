package org.climatecollaboratorium.facelets.discussions;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import com.sun.facelets.FaceletContext;
import com.sun.facelets.FaceletException;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class DiscussionsSupportTag extends TagHandler{
    private final TagAttribute discussionBeanParam;
    private final TagAttribute discussionIdParam;
    private final TagAttribute categoryIdParam;
    private final TagAttribute threadIdParam;
    private final TagAttribute messageIdParam;
    
    private static Log _log = LogFactoryUtil.getLog(DiscussionsSupportTag.class);

    public DiscussionsSupportTag(TagConfig config) {
        super(config);
        discussionBeanParam = this.getRequiredAttribute("discussionBean");
        discussionIdParam = this.getRequiredAttribute("discussionId");
        categoryIdParam = this.getAttribute("simulationId");
        threadIdParam = this.getAttribute("threadId");
        messageIdParam = this.getAttribute("messageId");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException,
            ELException {

        DiscussionBean discussionBean = (DiscussionBean) discussionBeanParam.getObject(ctx);
        Long discussionId = (Long) discussionIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        Long categoryId = categoryIdParam == null ? null : (Long) categoryIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        Long threadId = threadIdParam == null ? null : (Long) threadIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        Long messageId = messageIdParam == null ? null : (Long) messageIdParam.getValueExpression(ctx, Long.class).getValue(ctx);
        if (discussionBean.init(discussionId, categoryId, threadId, messageId)) {
            // initialization succeded
            nextHandler.apply(ctx, parent);
        }
        
    }
    
    

}
