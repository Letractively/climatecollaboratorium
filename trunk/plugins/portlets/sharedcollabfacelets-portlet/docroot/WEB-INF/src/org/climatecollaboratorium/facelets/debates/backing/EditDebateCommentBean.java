package org.climatecollaboratorium.facelets.debates.backing;

import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.model.DebateItem;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.climatecollaboratorium.facelets.debates.activity.DebateActivityKeys;

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
        if (content.trim().length() == 0) {
            // here is "required" validation for comment content
            // it has to be done that way because we can't user "required" attribute of inputTextarea
            // as we are embedding entire debate in existing form thus each button would force
            // validation
            UIComponent contentInput = event.getComponent().getParent().findComponent("content");
            
            FacesMessage fm = new FacesMessage();
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            fm.setDetail("Comment is required");
            
            FacesContext.getCurrentInstance().addMessage(contentInput.getClientId(FacesContext.getCurrentInstance()), fm);
            return;
        }
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