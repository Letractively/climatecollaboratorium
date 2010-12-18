package org.climatecollaboratorium.facelets.debates;

import java.io.IOException;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.sun.facelets.FaceletContext;
import com.sun.facelets.FaceletException;
import com.sun.facelets.tag.TagAttribute;
import com.sun.facelets.tag.TagConfig;
import com.sun.facelets.tag.TagHandler;

public class DebateSupportTag extends TagHandler {
    private final TagAttribute debateIdParam;
    private final TagAttribute debateBeanParam;

    private static Log _log = LogFactoryUtil.getLog(DebateSupportTag.class);

    public DebateSupportTag(TagConfig config) {
        super(config);
        debateIdParam = this.getRequiredAttribute("debateId");
        debateBeanParam = this.getRequiredAttribute("debateBean");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException,
            ELException {

        DebateBean debateBean = (DebateBean) debateBeanParam.getObject(ctx);
        Long debateId = (Long) debateIdParam.getValueExpression(ctx, Long.class).getValue(ctx);

        debateBean.init(debateId);
        nextHandler.apply(ctx, parent);

    }

}
