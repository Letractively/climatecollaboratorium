package org.climatecollaboratorium.facelets.debates;

import java.io.IOException;
import java.util.List;

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

public class DebatesDetailsSupportTag extends TagHandler {
    private final TagAttribute debatesIdsParam;
    private final TagAttribute debatesDetailsBeanParam;

    private static Log _log = LogFactoryUtil.getLog(DebatesDetailsSupportTag.class);

    public DebatesDetailsSupportTag(TagConfig config) {
        super(config);
        debatesIdsParam = this.getRequiredAttribute("debatesIds");
        debatesDetailsBeanParam = this.getRequiredAttribute("debatesDetailsBean");
    }

    @Override
    public void apply(FaceletContext ctx, UIComponent parent) throws IOException, FacesException, FaceletException,
            ELException {

        DebatesDetailsBean debatesDetailsBean = (DebatesDetailsBean) debatesDetailsBeanParam.getObject(ctx);
        Object debatesIdsObj = debatesIdsParam.getObject(ctx);
        Long[] debatesIds = new Long[0];
        if (debatesIdsObj instanceof Long[]) {
            debatesIds = (Long[]) debatesIdsObj;
        }
        else if (debatesIdsObj instanceof List<?>){
            List<Long> debatesIdsList = (List<Long>) debatesIdsObj;
            debatesIds = new Long[debatesIdsList.size()];
            debatesIdsList.toArray(debatesIds);
        }
        else {
            String debatesIdsStr = debatesIdsParam.getValue();
            String[] debatesIdsArrayStr = debatesIdsStr.split(",");
            debatesIds = new Long[debatesIdsArrayStr.length];
            for (int i=0; i < debatesIdsArrayStr.length; i++) {
                try {
                    debatesIds[i] = Long.parseLong(debatesIdsArrayStr[i]);
                }
                catch (NumberFormatException e) {
                    _log.warn("Can't parse debate id: " + debatesIdsArrayStr[i], e);
                    return;
                }
            }
        }

        debatesDetailsBean.init(debatesIds);
        nextHandler.apply(ctx, parent);

    }

}
