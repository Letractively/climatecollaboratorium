package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateItem;

import com.icesoft.faces.component.tree.IceUserObject;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import javax.swing.tree.DefaultMutableTreeNode;

import org.climatecollaboratorium.debates.activity.DebateActivityKeys;

public class DebateItemUserObject extends IceUserObject {

    private DebateItem item;
    private DebateDetailsBean debateDetailsBean;
    private Debate debate;

    private List<SelectionListener<DebateItem>> listeners = new ArrayList<SelectionListener<DebateItem>>();
    private User user;
    private ThemeDisplay td = Helper.getThemeDisplay();

    public DebateItemUserObject(DefaultMutableTreeNode wrapper, DebateItem item, Debate debate,
            DebateDetailsBean debateDetailsBean) {
        super(wrapper);
        this.item = item;
        this.debate = debate;
        this.debateDetailsBean = debateDetailsBean;
        if (Helper.isUserLoggedIn()) {
            this.user = Helper.getLiferayUser();
        }
    }

    public void addSelectionListener(SelectionListener<DebateItem> listener) {
        listeners.add(listener);
    }

    public void removeSelectionListener(SelectionListener<DebateItem> listener) {
        listeners.remove(listener);
    }

    public String getText() {
        return item.getDebateSummary();
    }

    public String getType() {
        return item.getDebateDetail();
    }

    public boolean getHasUserVotedForThisItem() throws PortalException, SystemException {
        if (user != null) {
            return item.hasUserVotedForThisItem(user.getUserId());
        }
        return false;
    }

    public Long getVotes() throws SystemException, PortalException {
        return item.getVotesCount();
    }

    public void itemSelectedCallback(ActionEvent action) {
        for (SelectionListener<DebateItem> listener: listeners) {

            listener.onSelected(item);
        }

    }

    public double getVotesPercent() throws SystemException, PortalException  {
        int totalVotes = debate.getTotalVotesCount();
        Long votes = getVotes();

        return totalVotes == 0 ? 0 : (votes * 100) / totalVotes;
    }

    public String getDebatePostType() {
        return item.getDebatePostType();
    }

    public void vote(ActionEvent event) throws PortalException, SystemException {
        item.voteForThisItem(Helper.getLiferayUser().getUserId());
        for (SelectionListener<DebateItem> listener: listeners) {
            listener.onSelected(item);
        }
        debateDetailsBean.refresh();

        SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                DebateItem.class.getName(), item.getDebateItemId(), DebateActivityKeys.VOTE_FOR_POSITION.id(),
                StringPool.BLANK, 0);

    }

    public void unvote(ActionEvent event) throws PortalException, SystemException {
        if (user != null) {
            item.unvoteThisItem(user.getUserId());
        }
        for (SelectionListener<DebateItem> listener: listeners) {
            listener.onSelected(item);
        }
        debateDetailsBean.refresh();

        SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                DebateItem.class.getName(), item.getDebateItemId(), DebateActivityKeys.RETRACT_VOTE_FOR_POSITION.id(),
                StringPool.BLANK, 0);

    }

    public DebateItem getItem() {
        return item;
    }

    public boolean isActive() {
        return item.getDebateItemId().equals(debateDetailsBean.getSelectedDebateItem().getDebateItemId());
    }
    
    public boolean getHasComments() throws SystemException {
        return item.getComments().size() > 0;
    }
    
    public int getCommentsCount() throws SystemException {
        return item.getComments().size();
    }
}