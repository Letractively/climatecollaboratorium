package com.ext.portlet.debaterevision.model.impl;

import com.ext.portlet.debaterevision.DebateItemStatus;
import com.ext.portlet.debaterevision.DebateItemType;
import com.ext.portlet.debaterevision.IllegalDebateItemType;
import com.ext.portlet.debaterevision.IllegalOpererationException;
import com.ext.portlet.debaterevision.model.Debate;
import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.ext.portlet.debaterevision.model.DebateItemReference;
import com.ext.portlet.debaterevision.service.*;
import com.ext.portlet.debaterevision.util.Indexer;
import com.liferay.counter.service.persistence.CounterUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DebateItemImpl extends DebateItemModelImpl implements DebateItem {




    public long debateVersion = -1L;
    private final static Log _log = LogFactoryUtil.getLog(DebateItemImpl.class);

    public DebateItemImpl() {

    }



    public void setDebateVersion(long debateVersion) {
      this.debateVersion = debateVersion;
    }

    public long getDebateVersion() {
        return debateVersion;
    }

    public List<DebateItem> getChildren() {
        return DebateItemLocalServiceUtil.getChildren(this);
    }

    public DebateItem getParent() throws SystemException, PortalException {
        return DebateItemLocalServiceUtil.getParent(this);
    }

    private DebateItemType getType() {
       return DebateItemType.valueOf(this.getDebatePostType()); 
    }

    public Debate getDebate() throws SystemException {
      return DebateLocalServiceUtil.findByVersion(getDebateId(),debateVersion);
    }

    private static boolean isMostRecent(DebateItem d, boolean checked) throws SystemException {
        long lastTreeVersion = DebateLocalServiceUtil.findLastVersion(d.getDebateId()).getTreeVersion();
        DebateItem d1 = DebateItemLocalServiceUtil.getLastItemInVersion(lastTreeVersion,d.getDebateItemId());
        if (!d.equals(d1)) {
            if (checked) throw new SystemException(new IllegalOpererationException("Cannot delete historical versions"));
            return false;
        }
        return true;

    }



    // there should be a check for each of the below to make sure we are using only the most recent version of the tree


    public DebateItem addChild(String title, String content, long userId, String type, List<DebateItemReference> refs, long weight) throws SystemException  {
        isMostRecent(this,true);

        try {
        getType().isLegalChild(DebateItemType.valueOf(type), true);
        } catch (IllegalDebateItemType x) {
          throw new SystemException(x);      
        }

        long id = CounterUtil.increment(DebateItem.class.getName());
        DebateItem item = DebateItemLocalServiceUtil.createDebateItem(id);
        item.setDebateItemId(id);
        item.setDebateSummary(title);
        item.setDebateDetail(content);
        item.setAuthorId(userId);
        item.setDebateItemParentId(this.getDebateItemId());
        item.setStatus(DebateItemStatus.ACTIVE.name());
        item.setUpdated(new Date());
        item.setDebatePostType(type);
        item.setItemVersion(0L);
        item.setDebateId(getDebateId());
        item.setWeight(weight);

        Debate mostRecent = DebateLocalServiceUtil.findLastVersion(getDebateId());

        //needs to be a move forward
        mostRecent.moveForward();

        item.setTreeVersion(mostRecent.getTreeVersion());
        item.setDebateVersion(mostRecent.getTreeVersion());
        DebateItemLocalServiceUtil.addDebateItem(item);
        try {
            Indexer.updateEntry(10112L, item);
        } catch (SearchException e) {
            _log.error("Can't index debate item", e);
        }

        updateReferences(item,refs);
        

        return item;
    }


    



    public void delete(long authorId) throws SystemException {
        isMostRecent(this,true);
        moveForeward();
        setAuthorId(authorId);
        for (DebateItem item:getChildren()) {
           item.delete(authorId);
        }
       setStatus(DebateItemStatus.DELETED.name());
       DebateItemLocalServiceUtil.updateDebateItem(this);
       try {
           Indexer.deleteEntry(10112L, getDebateItemId());
       } catch (SearchException e) {
           _log.error("Can't remove item from search cache", e);
       }



    }



    public DebateItem update(String title, String detail, List<DebateItemReference> refs, long authorId, long weight) throws SystemException {
        if (isMostRecent(this,true)) moveForeward();
        setAuthorId(authorId);
        setDebateSummary(title);
        setDebateDetail(detail);
        setWeight(weight);
        
        DebateItemLocalServiceUtil.updateDebateItem(this);

        updateReferences(this,refs);

        return this;
    }

    /**
        * Moves this item into the current tree version, and leaves a copy itself into the past
        *
        * @return
        * @throws SystemException
        */
       public void moveForeward() throws SystemException {
           long pk = CounterUtil.increment(DebateItem.class.getName());
           DebateItem result = DebateItemLocalServiceUtil.createDebateItem(pk);
           result.setDebateItemId(getDebateItemId());
           result.setDebateVersion(getDebateVersion());
           result.setAuthorId(getAuthorId());
           result.setDebateId(getDebateId());
           result.setItemVersion(getItemVersion());
           result.setTreeVersion(getTreeVersion());
           result.setStatus(getStatus());
           result.setDebatePostType(getDebatePostType());
           result.setDebateDetail(getDebateDetail());
           result.setDebateSummary(getDebateSummary());
           result.setUpdated(getUpdated());
           result.setWeight(getWeight());
           DebateItemLocalServiceUtil.updateDebateItem(result);


            


           long lastTreeVersion = DebateLocalServiceUtil.findLastVersion(getDebateId()).getTreeVersion();
           setDebateVersion(lastTreeVersion);
           setTreeVersion(lastTreeVersion);
           setUpdated(new Date());
           setItemVersion(getItemVersion()+1);
           DebateItemLocalServiceUtil.updateDebateItem(this);

        for (DebateItemReference ref:getReferences()) {
            ref.moveForward(getItemVersion()+1);
        }

       }


       /**
        * Copies this item to create the most recent version of this item
        *
        * @return The newly created item
        * @throws SystemException
        */
       public DebateItem copyForward() throws SystemException {
           long pk = CounterUtil.increment(DebateItem.class.getName());
           DebateItem result = DebateItemLocalServiceUtil.createDebateItem(pk);
           result.setDebateItemId(getDebateItemId());
           result.setDebateVersion(getDebateVersion());
           result.setAuthorId(getAuthorId());
           result.setDebateId(getDebateId());
           result.setItemVersion(getItemVersion()+1);
           result.setTreeVersion(DebateLocalServiceUtil.findLastVersion(getDebateId()).getTreeVersion());
           result.setStatus(getStatus());
           result.setDebatePostType(getDebatePostType());
           result.setDebateDetail(getDebateDetail());
           result.setDebateSummary(getDebateSummary());
           result.setUpdated(new Date());
           result.setWeight(getWeight());
           DebateItemLocalServiceUtil.updateDebateItem(result);

           for (DebateItemReference ref:getReferences()) {
            ref.moveForward(result.getItemVersion());
        }
           return result;
       }


    public DebateComment addComment(String title, String message, long authorId) throws SystemException {
        long pk = CounterUtil.increment(DebateComment.class.getName());
        DebateComment comment = DebateCommentLocalServiceUtil.createDebateComment(pk);
        comment.setDebateItemId(this.getDebateItemId());
        comment.setItemVersion(this.getItemVersion());
        comment.setDebateCommentTitle(title);
        comment.setDebateCommentDetail(message);
        comment.setAuthorId(authorId);
        comment.setUpdated(new Date());
        DebateCommentLocalServiceUtil.updateDebateComment(comment);
        // reindex current item
        try {
            Indexer.updateEntry(10112L, this);
        } catch (SearchException e) {
            _log.error("Can't reindex debate item " + getDebateItemId(), e);
        }
        return comment;
    }

    public List<DebateComment> getComments() throws SystemException {
        return DebateCommentLocalServiceUtil.getCommentsForItem(this.getDebateItemId());
    }

    public List<DebateItemReference> getReferences() throws SystemException {
        return DebateItemReferenceLocalServiceUtil.getDebateItemReferences(this);
    }
    
    public void voteForThisItem(Long userId) throws PortalException, SystemException {
        DebateItemLocalServiceUtil.voteForDebateItem(this, userId);
    }
    
    public void unvoteThisItem(Long userId) throws PortalException, SystemException {
        DebateItemLocalServiceUtil.unvoteDebateItem(this, userId);
    }
    
    public boolean hasUserVotedForThisItem(Long userId) throws PortalException, SystemException {
        return DebateItemLocalServiceUtil.hasUserVotedForItem(this, userId);
    }

    public List<DebateItem> getCompleteHistory() {
        return DebateItemLocalServiceUtil.getHistory(DebateLocalServiceUtil.findLastVersion(getDebateId()).getTreeVersion(),getDebateItemId());
    }

    public List<DebateItem> getHistoryForTreeVersion() {
        return DebateItemLocalServiceUtil.getHistory(getDebateVersion(),getDebateItemId());     
    }
    
    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(this.getAuthorId());
    }

    /**
     * Note - new references should not be backed; this method will create PKs as needed. Backed references
     * that are not associated with this item already will be ignored
     *
     * @param references
     * @return
     * @throws SystemException
     */
    private boolean updateReferences(DebateItem item, List<DebateItemReference> references) throws SystemException {
        boolean modified = false;

        Map<Long,DebateItemReference> existing = new HashMap<Long,DebateItemReference>();
        for (DebateItemReference ref:getReferences()) {
            existing.put(ref.getPrimaryKey(),ref);
        }

        for (DebateItemReference ref:references) {
            if (ref.getPrimaryKey() == null) {
                modified = true;
                copyReferenceFrom(item,ref);
            } else if (!existing.containsKey(ref.getPrimaryKey())) {
                modified = true;
                attachReference(item,ref);
            }
            if (existing.containsKey(ref.getPrimaryKey())) {
                DebateItemReference existingRef = existing.get(ref.getPrimaryKey());
                if (ObjectUtils.equals(existingRef.getUrl(),ref.getUrl()) &&
                     ObjectUtils.equals(existingRef.getText(),ref.getText())) {
                    existing.remove(existingRef.getPrimaryKey());
                } else {
                    //needs update
                    modified = true;
                    existing.remove(existingRef.getPrimaryKey());
                    existingRef.moveForward(getItemVersion());
                    existingRef.setText(ref.getText());
                    existingRef.setUrl(ref.getUrl());
                    DebateItemReferenceLocalServiceUtil.updateDebateItemReference(ref);
                }
            }
        }

        for (DebateItemReference ref:existing.values()) {
            modified = true;
            ref.moveForward(getItemVersion());
            ref.setStatus(DebateItemStatus.DELETED.name());
            DebateItemReferenceLocalServiceUtil.updateDebateItemReference(ref);
        }
        return modified;
    }

    private void attachReference(DebateItem item, DebateItemReference ref) throws SystemException {
        if (ref.getDebateId()!=null) {
            ref = DebateItemReferenceLocalServiceUtil.createNewFrom(item, ref);
            //throw new SystemException("References cannot be reassigned");
        }
        ref.setDebateId(item.getDebateId());
        ref.setDebateItemId(item.getDebateItemId());
        item = DebateItemLocalServiceUtil.getLastActiveItem(item.getDebateItemId());
        ref.setItemVersion(item.getItemVersion());
        ref.setStatus(DebateItemStatus.ACTIVE.name());
        DebateItemReferenceLocalServiceUtil.updateDebateItemReference(ref);
    }

    private void copyReferenceFrom(DebateItem item, DebateItemReference ref) throws SystemException {
        DebateItemReferenceLocalServiceUtil.createNewFrom(item,ref);
    }
    
    public Long getVotesCount() throws PortalException, SystemException {
        return Long.valueOf(DebateItemVoteLocalServiceUtil.getVotesByDebateItemId(this.getDebateItemId()).size());
        // this doesn't worr correctly with icefaces
        //return DebateItemVoteStatsLocalServiceUtil.getByDebateItemId(this.getDebateItemId()).getVotesCount();
    }
    
    
    public int getCommentsCount() {
        return DebateItemLocalServiceUtil.getItemCommentsCount(getDebateItemId());
    }

}
