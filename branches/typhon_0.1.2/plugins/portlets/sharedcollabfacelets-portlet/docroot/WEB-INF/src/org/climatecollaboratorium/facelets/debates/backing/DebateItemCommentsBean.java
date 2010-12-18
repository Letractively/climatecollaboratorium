package org.climatecollaboratorium.facelets.debates.backing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.climatecollaboratorium.facelets.debates.support.DebateCommentWrapper;
import java.util.Collections;

import com.ext.portlet.debaterevision.model.DebateComment;
import com.ext.portlet.debaterevision.model.DebateItem;
import com.liferay.portal.SystemException;


public class DebateItemCommentsBean {
    private DebateDetailsBean debateDetailsBean;
    private DebatesPermissionsBean permissions;

    public DebateDetailsBean getDebateDetailsBean() {
        return debateDetailsBean;
    }

    public void setDebateDetailsBean(DebateDetailsBean debateDetailsBean) {
        this.debateDetailsBean = debateDetailsBean;
    }
    
    public List<DebateCommentWrapper> getComments() throws SystemException {
        List<DebateCommentWrapper> comments = new ArrayList<DebateCommentWrapper>();
        DebateItem item = debateDetailsBean.getSelectedDebateItem().getItem();
        
        for (DebateComment comment: item.getComments()) {
            DebateCommentWrapper commentWrapper = new DebateCommentWrapper(comment);
            commentWrapper.setPermissions(permissions);
            comments.add(commentWrapper);
        
        }
        
        Collections.sort(comments, new Comparator<DebateCommentWrapper>() {

            @Override
            public int compare(DebateCommentWrapper o1, DebateCommentWrapper o2) {
                return o1.getComment().getUpdated().compareTo(o2.getComment().getUpdated());
            }
        });
        
        return comments;
    }

    public DebatesPermissionsBean getPermissions() {
        return permissions;
    }

    public void setPermissions(DebatesPermissionsBean permissions) {
        this.permissions = permissions;
    }


    
    
    

}
