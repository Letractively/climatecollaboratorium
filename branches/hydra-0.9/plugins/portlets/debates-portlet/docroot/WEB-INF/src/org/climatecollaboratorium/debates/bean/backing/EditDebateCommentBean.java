package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.DebateItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.debates.activity.DebateActivityKeys;

public class EditDebateCommentBean {
    private DebateDetailsBean debateDetailsBean;
    private String content;
    private String title;
    private DebatesPermissionsBean permissions;
    private ThemeDisplay td = Helper.getThemeDisplay();

    public DebateDetailsBean getDebateDetailsBean() {
        return debateDetailsBean;
    }

    public void setDebateDetailsBean(DebateDetailsBean debateDetailsBean) {
        this.debateDetailsBean = debateDetailsBean;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void save(ActionEvent event) throws SystemException, PortalException {
        if (permissions.getCanAddComment()) {
            debateDetailsBean.getSelectedDebateItem().getItem().addComment(title, content, Helper.getLiferayUser().getUserId());
            title = "";
            content = "";
            DebateItemType type = DebateItemType.valueOf(debateDetailsBean.getSelectedDebateItem().getDebatePostType());
            DebateItem item = debateDetailsBean.getSelectedDebateItem().getItem();
            if (type.equals(DebateItemType.POSITION)) {
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), DebateItem.class
                        .getName(), item.getDebateItemId(), DebateActivityKeys.COMMENT_ON_POSITION.id(), StringPool.BLANK,
                        0);

            } else if (type.equals(DebateItemType.ARGUMENT_CON) || type.equals(DebateItemType.ARGUMENT_PRO)) {
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), DebateItem.class
                        .getName(), item.getDebateItemId(), DebateActivityKeys.COMMENT_ON_ARGUMENT.id(), StringPool.BLANK,
                        0);
            }
            if (type.equals(DebateItemType.QUESTION)) {
                SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(), DebateItem.class
                        .getName(), item.getDebateItemId(), DebateActivityKeys.COMMENT_ON_QUESTION.id(), StringPool.BLANK,
                        0);

            }
        }
        if (! debateDetailsBean.isSubscribed()) {
            debateDetailsBean.subscribe();
        }
    }

    public DebatesPermissionsBean getPermissions() {
        return permissions;
    }

    public void setPermissions(DebatesPermissionsBean permissions) {
        this.permissions = permissions;
    }
}