package org.climatecollaboratorium.debatebrowser.utils;

import java.util.Map;

import javax.portlet.PortletRequest;

import org.climatecollaboratorium.debatebrowser.utils.Helper;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.Validator;

public class DebateBrowserFriendlyURLMapper extends BaseFriendlyURLMapper {
    private static final String _MAPPING = "debates";

    private static final String _PORTLET_ID = "debatebrowser_portlet";

    @Override
    public String getPortletId() {
        return _PORTLET_ID;
    }

    @Override
    public String buildPath(LiferayPortletURL portletURL) {
        StringBuilder friendlyURLPathSB = new StringBuilder();
        String friendlyURLPath = null;

        String categoryId = portletURL.getParameter("categoryId");
        String debateId = portletURL.getParameter("debateId");


        if (portletURL.getLifecycle().equals(PortletRequest.RESOURCE_PHASE)) {
            friendlyURLPathSB.append("/debates");
            if (Validator.isNotNull(debateId)) {
                friendlyURLPathSB.append("categoryId/");
                friendlyURLPathSB.append(debateId);

                portletURL.addParameterIncludedInPath("categoryId");
            }
            if (Validator.isNotNull(categoryId)) {
                friendlyURLPathSB.append("debateId/");
                friendlyURLPathSB.append(categoryId);
                portletURL.addParameterIncludedInPath("debateId");
            }

            friendlyURLPath = friendlyURLPathSB.toString();
        }
        
        if (friendlyURLPath != null) {
            portletURL.addParameterIncludedInPath("p_p_id");
            portletURL.addParameterIncludedInPath("p_p_lifecycle");
            portletURL.addParameterIncludedInPath("p_p_cacheability");

            portletURL.addParameterIncludedInPath("struts_action");
        }

        return friendlyURLPath;
    }

    @Override
    public String getMapping() {
        return _MAPPING;
    }

    @Override
    public void populateParams(String friendlyURLPath, Map<String, String[]> params) {

        String[] urlParts = friendlyURLPath.split("/");
        // ignore first 2 parts as url is formated like
        // /_MAPPING/here/are/parameters
        // so we need to throw away /_MAPPING/

        for (int i = 2; i < urlParts.length - 1; i += 2) {
            // take parameters from url treating them as key/value pairs
            String key = urlParts[i];
            String value = urlParts[i + 1];

            params.put(Helper.getUrlParameterKey(key), new String[] { value });
            addParam(params, key, value);
        }
    }

}
