package org.climatecollaboratorium.userprofile.utils;

import java.util.Map;

import javax.portlet.PortletMode;
import javax.portlet.PortletRequest;

import org.climatecollaboratorium.userprofile.Helper;

import com.liferay.portal.kernel.portlet.BaseFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.Validator;

public class UserprofileFriendlyURLMapper extends BaseFriendlyURLMapper {
    private static final String _MAPPING = "member";

    private static final String _PORTLET_ID = "member_portlet";

    @Override
    public String getPortletId() {
        return _PORTLET_ID;
    }

    @Override
    public String buildPath(LiferayPortletURL portletURL) {
        StringBuilder friendlyURLPathSB = new StringBuilder();
        String friendlyURLPath = null;

        String memberId = portletURL.getParameter("userId");


        if (portletURL.getLifecycle().equals(PortletRequest.RESOURCE_PHASE)) {
            friendlyURLPathSB.append("/member");
            if (Validator.isNotNull(memberId)) {
                friendlyURLPathSB.append("userId/");
                friendlyURLPathSB.append(memberId);
                portletURL.addParameterIncludedInPath("userId");
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
