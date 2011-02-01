package org.climatecollaboratorium.messaging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;

import org.climatecollaboratorium.messaging.utils.DataPage;
import org.climatecollaboratorium.messaging.utils.PagedListDataModel;

import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.messaging.NoSuchMessageRecipientStatusException;
import com.ext.portlet.messaging.model.Message;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

public class MessagingBean {
    private User user;
    private DataModel onePageDataModel;
    private MessageType messageType = MessageType.INBOX;
    private int pageSize = 25;
    private static final Log _log = LogFactoryUtil.getLog(MessagingBean.class);
    private int messagesCount = 0;
    private List<MessageBean> items = new ArrayList<MessageBean>();
    private boolean sendingMessage;
    private SendMessageBean sendMessageBean;
    
    public MessagingBean() throws SystemException, PortalException {
        if (Helper.isUserLoggedIn()) {
            user = Helper.getLiferayUser();
            //MessageLocalServiceUtil.
            messagesCount = MessageUtil.countMessages(user.getUserId(), messageType.getTypeStr());
        }
    }
    
    public boolean isInitialized() {
        return user != null;
    }

    
    /**
     * Bound to DataTable value in the ui.
     */
    public DataModel getData() {
        if(onePageDataModel == null){
            onePageDataModel = new LocalDataModel(pageSize);
        }
        return onePageDataModel;
    }
    
    /**
     * This is where the Customer data is retrieved from the database and
     * returned as a list of CustomerBean objects for display in the UI.
     * @throws IOException 
     * @throws ParseException 
     * @throws PortalException 
     * @throws SystemException 
     */
    private DataPage getDataPage(int startRow, int pageSize) throws IOException, SystemException, PortalException {
        
        messagesCount = MessageUtil.countMessages(user.getUserId(), messageType.getTypeStr());

        // Calculate indices to be displayed in the ui.
        int endIndex = startRow + pageSize;
        if (endIndex > messagesCount) {
            endIndex = messagesCount;
        }

        items = new ArrayList<MessageBean>();
        for (Message message: MessageUtil.getMessages(user.getUserId(), startRow, endIndex, messageType.getTypeStr())) {
            items.add(new MessageBean(message));
        }

        return new DataPage(messagesCount, startRow, items);
    }

    private class LocalDataModel extends PagedListDataModel {
        public LocalDataModel(int pageSize) {
            super(pageSize);
        }

        public DataPage fetchPage(int startRow, int pageSize) {
            // call enclosing managed bean method to fetch the data
            try {
                return getDataPage(startRow, pageSize);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return new DataPage(0, 0, new ArrayList<MessageBean>());
        }
    }
    
    public void changeType(ActionEvent e) {
        try {
            MessageType type = MessageType.valueOf(e.getComponent().getAttributes().get("type").toString());
            
            if (type != messageType) {
                messageType = type;
                onePageDataModel = null;
                messagesCount = MessageUtil.countMessages(user.getUserId(), messageType.getTypeStr());
            }
        }
        catch (Exception ex) {
            _log.error("There was an error when changing view type", ex);
        }
    }
    
    public MessageType getType() {
        return messageType;
    }
    
    public int getMessagesCount() {
        return messagesCount;
    }
    
    public void archiveSelectedMessages(ActionEvent e) throws NoSuchMessageRecipientStatusException, SystemException {
        for (MessageBean item: items) {
            if (item.isSelected()) {
                Message message = item.getMessage(); 
                if (! message.isArchived(user.getUserId())) {
                    message.setArchived(user.getUserId());
                }
            }
        }
        onePageDataModel = null;
    }
    
    public void toggleSendMessage(ActionEvent e) {
        toggleSendMessage();
    }

    public void toggleSendMessage() {
        sendingMessage = !sendingMessage;
        if (sendingMessage) {
            if (sendMessageBean != null) {
                sendMessageBean.init();
            }
        }
        onePageDataModel = null;
    }

    public void messageSent() {
        sendingMessage = false;
    }

    public void setSendMessageBean(SendMessageBean sendMessageBean) {
        this.sendMessageBean = sendMessageBean;
    }
    public boolean getSendingMessage() {
        return sendingMessage;
    }
    
    public User getUser() {
        return user;
    }

}
