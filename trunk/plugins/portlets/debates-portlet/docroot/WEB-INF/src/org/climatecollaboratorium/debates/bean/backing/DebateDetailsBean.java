package org.climatecollaboratorium.debates.bean.backing;

import com.ext.portlet.Activity.ActivityUtil;
import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.IllegalDebateItemType;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.service.DebateItemLocalServiceUtil;
import com.ext.portlet.debaterevision.service.DebateLocalServiceUtil;
import com.icesoft.faces.async.render.OnDemandRenderer;
import com.icesoft.faces.async.render.RenderManager;
import com.icesoft.faces.async.render.Renderable;
import com.icesoft.faces.component.tree.IceUserObject;
import com.icesoft.faces.webapp.xmlhttp.PersistentFacesState;
import com.icesoft.faces.webapp.xmlhttp.RenderingException;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import org.climatecollaboratorium.debates.activity.DebateActivityKeys;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DebateDetailsBean implements SelectionListener<DebateItem>, Renderable {

    private List<DefaultTreeModel> positionTrees;
    private DefaultTreeModel positionTree;
    private int totalVotes = 0;

    private String text;
    private DebateItem rootDebateItem;
    private DebateItem selectedDebateItem;
    private EditDebateCommentBean editDebateCommentBean = null;

    Map<Long, DefaultMutableTreeNode> treeNodeByItemId = null;

    private Debate debate;

    private boolean showAddDebateItemForm = false;

    private User currentUser;

    private int dividerPosition = 500;

    private PersistentFacesState state;
    private List<SelectionListener<DebateItem>> selectionListeners = new ArrayList<SelectionListener<DebateItem>>();
    private RenderManager renderManager;
    private OnDemandRenderer renderer;
    private ThemeDisplay td = Helper.getThemeDisplay();
    private boolean advanced = false;


    public DebateDetailsBean() throws SystemException {
        //setDebateId(5L);
        state = PersistentFacesState.getInstance();

    }


    public boolean isSubscribed() {
     String userid = Helper.getLiferayUserId();
     if (userid==null || debate==null) return false;

        try {
            return ActivityUtil.isSubscribed(DebateActivityKeys.ALL,Long.parseLong(userid),selectedDebateItem.getDebateId());
        } catch (SystemException e) {

        }
        return false;
    }

    public void setSubscribed(boolean b) {
         String userid = Helper.getLiferayUserId();
        if (userid==null || debate==null) return;

        if (!b && isSubscribed()) {
            try {
                ActivityUtil.removeSubscription(DebateActivityKeys.ALL,Long.parseLong(userid),debate.getDebateId());
            } catch (SystemException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } else if (b && !isSubscribed()) {
           try {
                ActivityUtil.addSubscription(DebateActivityKeys.ALL,Long.parseLong(userid),debate.getDebateId());
            } catch (SystemException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public void subscribe() {
        setSubscribed(true);
    }

    public void unsubscribe() {
        setSubscribed(false);
    }

    public void setAdvanced(boolean b) {
        this.advanced = b;
    }

    public Boolean getAdvanced() {
        return advanced;
    }

    public void showAdvanced() {
        setAdvanced(true);
    }

    public void showBasic() {
        setAdvanced(false);
    }

    private void clear() {
        editDebateCommentBean = new EditDebateCommentBean();
        treeNodeByItemId = Collections.<Long,DefaultMutableTreeNode>emptyMap();
        positionTrees = Collections.emptyList();
        totalVotes = 0;
        debate = null;
        rootDebateItem = null;


    }

    public void setDebateId(long debateid) throws SystemException {
        try {
            if (debate != null && debateid == debate.getDebateId()) return;
            if (debateid < 0) {
                clear();
                return;
            }

            editDebateCommentBean = new EditDebateCommentBean();

            treeNodeByItemId = new HashMap<Long, DefaultMutableTreeNode>();


            debate = DebateLocalServiceUtil.findLastVersion(debateid);
            rootDebateItem = debate.getCurrentRoot();
            if (Helper.isUserLoggedIn()) {
                currentUser = Helper.getLiferayUser();
            }

            setSelectedDebateItem(rootDebateItem);


            List<DebateItem> positions = rootDebateItem.getChildren();

            positionTrees = new ArrayList<DefaultTreeModel>();
            for (DebateItem position : positions) {
                DefaultMutableTreeNode positionNode = generateSubTree(position, debate);
                positionTrees.add(new DefaultTreeModel(positionNode));

                totalVotes += Helper.getDebateItemVotes(position);
            }
            Collections.sort(positionTrees, new Comparator<DefaultTreeModel>() {

                @Override
                public int compare(DefaultTreeModel posTree0, DefaultTreeModel posTree1) {
                    DebateItem position0 = ((DebateItemUserObject) ((DefaultMutableTreeNode) posTree0.getRoot()).getUserObject()).getItem();
                    DebateItem position1 = ((DebateItemUserObject) ((DefaultMutableTreeNode) posTree1.getRoot()).getUserObject()).getItem();
                    try {
                        if (position0.getVotesCount() > position1.getVotesCount()) {
                            return -1;
                        } else if (position0.getVotesCount() < position1.getVotesCount()) {
                            return 1;
                        }
                    } catch (Exception e) {
                        //ignore
                    }
                    return position0.getDebateSummary().compareTo(position1.getDebateSummary());
                }

            });
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

    public void debateItemUpdated(DebateItem item) throws SystemException, RenderingException {
        if (item.getDebateItemId().equals(rootDebateItem.getDebateItemId())) {
            rootDebateItem = item;
        } else {
            DefaultMutableTreeNode itemNode = treeNodeByItemId.get(item.getDebateItemId());
            DebateItemUserObject itemObject = new DebateItemUserObject(itemNode, item, debate, this);
            itemObject.addSelectionListener(this);
            itemNode.setUserObject(itemObject);

            for (DefaultTreeModel tree : positionTrees) {
                tree.nodeChanged(itemNode);
            }
        }
        setSelectedDebateItem(item);
        refresh();//state.executeAndRender();
    }

    public void debateItemRemoved(DebateItem item) throws RenderingException {
        DefaultMutableTreeNode itemNode = treeNodeByItemId.get(item.getDebateItemId());
        if (itemNode != null) {
            itemNode.removeFromParent();
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) itemNode.getParent();
            if (parent != null) {
                parent.remove(itemNode);
                if (parent.getChildCount() == 0) {
                    ((IceUserObject) parent.getUserObject()).setLeaf(true);
                }
            }
        }
        setSelectedDebateItem(getRootItem());
        refresh();
        //state.executeAndRender();
    }

    public void debateItemAdded(DebateItem item) throws SystemException, PortalException, RenderingException {
        DebateItem parent = item.getParent();
        DefaultMutableTreeNode itemNode = null;
        if (parent.getDebateItemId().equals(rootDebateItem.getDebateItemId())) {
            rootDebateItem = parent;
            itemNode = generateSubTree(item, debate);
            positionTrees.add(new DefaultTreeModel(itemNode));
        } else {
            DefaultMutableTreeNode parentItemNode = treeNodeByItemId.get(parent.getDebateItemId());
            // replace old item object with new one refering
            DebateItemUserObject parentItemObject = new DebateItemUserObject(parentItemNode, parent, debate, this);
            parentItemObject.addSelectionListener(this);
            parentItemNode.setUserObject(parentItemObject);
            parentItemObject.setLeaf(false);

            itemNode = generateSubTree(item, debate);
            parentItemNode.add(itemNode);

            for (DefaultTreeModel tree : positionTrees) {
                tree.nodeChanged(parentItemNode);
            }

            // expand tree up till added node
            DefaultMutableTreeNode tmp = parentItemNode;
            while (tmp != null) {
                ((IceUserObject) tmp.getUserObject()).setExpanded(true);
                tmp = (DefaultMutableTreeNode) tmp.getParent();
            }
        }
        treeNodeByItemId.put(item.getDebateItemId(), itemNode);
        ((DebateItemUserObject) itemNode.getUserObject()).addSelectionListener(this);
        setSelectedDebateItem(item);
        refresh();
        //        state.executeAndRender();

    }

    private DefaultMutableTreeNode generateSubTree(DebateItem item, Debate debate) throws SystemException {
        DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode();
        treeNodeByItemId.put(item.getDebateItemId(), itemNode);

        DebateItemUserObject itemObject = new DebateItemUserObject(itemNode, item, debate, this);
        itemObject.setExpanded(true);
        // IceUserObject itemObject = new IceUserObject(itemNode);
        // itemObject.setText("ble");
        itemObject.addSelectionListener(this);

        itemNode.setUserObject(itemObject);
        for (DebateItem childItem : item.getChildren()) {
            DefaultMutableTreeNode childNode = generateSubTree(childItem, debate);
            itemNode.add(childNode);
        }

        if (itemNode.isLeaf()) {
            itemObject.setLeaf(true);
        }

        return itemNode;
    }

    public List<DefaultTreeModel> getPositionTrees() {
        return positionTrees;
    }

    public String getText() {
        return text;
    }

    public DebateItem getSelectedDebateItem() {
        return selectedDebateItem;
    }

    public void setSelectedDebateItem(DebateItem debateItem) {
        this.selectedDebateItem = debateItem;
    }

    @Override
    public void onSelected(DebateItem item) {
        try {
            int i = DebateLocalServiceUtil.getDebatesCount();
            System.out.println("Found " + i + " debates");

        } catch (SystemException e) {
            e.printStackTrace();
        }
        setSelectedDebateItem(item);

        // pass selection event to other listeners
        for (SelectionListener<DebateItem> listener : selectionListeners) {
            listener.onSelected(item);
        }

    }

    public void selectRootItem(ActionEvent event) {
        setSelectedDebateItem(rootDebateItem);
        for (SelectionListener<DebateItem> listener : selectionListeners) {
            listener.onSelected(rootDebateItem);
        }
    }

    public List<DebateComment> getSelectedDebateItemComments() throws SystemException {
        return selectedDebateItem.getComments();
    }


    public DebateItem getRootItem() {
        return rootDebateItem;
    }

    public Boolean getShowAddDebateItem() {
        return showAddDebateItemForm;
    }

    /*
     * public Boolean isShowAddDebateItem() { return showAddDebateItemForm; }
     */

    public void showAddPositionForm(ActionEvent event) {
        showAddDebateItemForm = true;
    }

    public String getAddedDebateItemType() {
        return "POSITION";
    }

    public void hideAddDebateItemForm(ActionEvent event) {
        showAddDebateItemForm = false;
    }

    public EditDebateCommentBean getEditDebateCommentBean() {
        return editDebateCommentBean;
    }

    public void setEditDebateCommentBean(EditDebateCommentBean editDebateCommentBean) {
        this.editDebateCommentBean = editDebateCommentBean;
    }

    public Debate getDebate() {
        return debate;
    }

    public DefaultTreeModel getPositionTree() {
        return positionTree;
    }

    public DefaultTreeModel getModel() {
        return positionTree;
    }

    public boolean getCanHaveArguments() {
        DebateItemType type = DebateItemType.valueOf(selectedDebateItem.getDebatePostType());
        boolean ret = true;
        try {
            ret &= type.isLegalChild(DebateItemType.ARGUMENT_PRO, false);
            ret &= type.isLegalChild(DebateItemType.ARGUMENT_CON, false);
            return ret;
        } catch (IllegalDebateItemType e) {
            return false;
        }
    }


    public boolean getCanHavePositions() {
        DebateItemType type = DebateItemType.valueOf(selectedDebateItem.getDebatePostType());
        boolean ret = true;
        try {
            ret &= type.isLegalChild(DebateItemType.POSITION, false);
            return ret;
        } catch (IllegalDebateItemType e) {
            return false;
        }
    }

    // FIXME: this is only a mock!
    public int getTotalVotes() throws SystemException, PortalException {
        if (debate != null) {
            return debate.getTotalVotesCount();
        }
        return 0;
    }

    public boolean getCanBeVotedOn() {
        DebateItemType type = DebateItemType.valueOf(selectedDebateItem.getDebatePostType());
        return type.equals(DebateItemType.POSITION);
    }

    public boolean getHasUserVotedOnSelectedItem() throws PortalException, SystemException {
        if (! Helper.isUserLoggedIn()) {
            return false;
        }
        return selectedDebateItem.hasUserVotedForThisItem(currentUser.getUserId());
    }

    public void voteForCurrentItem(ActionEvent event) throws PortalException, SystemException {
        selectedDebateItem.voteForThisItem(Helper.getLiferayUser().getUserId());


        SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                DebateItem.class.getName(), selectedDebateItem.getDebateItemId(), DebateActivityKeys.VOTE_FOR_POSITION.id(),
                StringPool.BLANK, 0);
    }

    public void unvoteCurrentItem(ActionEvent event) throws PortalException, SystemException {
        selectedDebateItem.unvoteThisItem(Helper.getLiferayUser().getUserId());
        SocialActivityLocalServiceUtil.addActivity(td.getUserId(), td.getScopeGroupId(),
                DebateItem.class.getName(), selectedDebateItem.getDebateItemId(), DebateActivityKeys.RETRACT_VOTE_FOR_POSITION.id(),
                StringPool.BLANK, 0);
    }

    public int getDividerPosition() {
        return dividerPosition;
    }

    public void setDividerPosition(int dividerPosition) {
        this.dividerPosition = dividerPosition;
    }

    public List<SelectItem> getSelectedItemHistory() {
        List<SelectItem> ret = new ArrayList<SelectItem>();
        int addedElements = 0;
        DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm");
        for (DebateItem item : selectedDebateItem.getCompleteHistory()) {
            addedElements++;
            if (item.getDebateItemPK().equals(selectedDebateItem.getDebateItemPK())) {
                ret.add(new SelectItem(item.getDebateItemPK(), "current"));
            } else {
                ret.add(new SelectItem(item.getDebateItemPK(), df.format(item.getUpdated())));
            }
        }
        if (addedElements == 1) {
            ret.add(new SelectItem("0", ""));
        }
        return ret;

    }

    @Override
    public PersistentFacesState getState() {
        return state;
    }

    @Override
    public void renderingException(RenderingException arg0) {
        System.err.println(arg0.toString());

    }

    public void setRenderManager(RenderManager renderManager) {

        this.renderManager = renderManager;
        renderer = renderManager.getOnDemandRenderer("renderer");
        renderer.add(this);

    }

    public void addSelectionListener(SelectionListener<DebateItem> listener) {
        selectionListeners.add(listener);
    }

    public void refresh() {
        //SessionRenderer.render(SessionRenderer.ALL_SESSIONS);
        //renderer.requestRender();
        /*try {
            //state.render();
        } catch (RenderingException e) {
            System.out.println("exception: " + e.getMessage());
        }
*/

    }

    public OnDemandRenderer getRenderer() {
        return renderer;
    }

    public void setSelectedItemId(Long item) {
        this.selectedDebateItem = DebateItemLocalServiceUtil.getLastActiveItem(item);
    }

}
