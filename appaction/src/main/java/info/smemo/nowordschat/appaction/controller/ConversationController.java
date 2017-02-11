package info.smemo.nowordschat.appaction.controller;

import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMFriendshipProxy;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMUserProfile;

import java.util.ArrayList;
import java.util.List;

import info.smemo.nowordschat.appaction.action.DataBeanAction;
import info.smemo.nowordschat.appaction.bean.FriendBean;
import info.smemo.nowordschat.appaction.bean.MessageBean;

public class ConversationController {

    public static ArrayList<MessageBean> getConversations() {
        ArrayList<MessageBean> messageBeanArrayList = new ArrayList<>();
        for (int i = 0; i < TIMManager.getInstance().getConversationCount(); i++) {
            TIMConversation conversation = TIMManager.getInstance().getConversationByIndex(i);
            if (conversation.getType() == TIMConversationType.C2C) {
                List<String> users = new ArrayList<>();
                users.add(conversation.getPeer());
                List<TIMUserProfile> profiles = TIMFriendshipProxy.getInstance().getFriendsById(users);
                if (profiles != null && profiles.size() > 0) {
                    FriendBean friendBean = DataBeanAction.toFriendBean(profiles.get(0));
                    MessageBean messageBean = new MessageBean();
                    messageBean.username = friendBean.getNickname();
                    messageBean.identifier = friendBean.getIdentifier();
                    messageBean.userLogo = friendBean.getFaceurl();
                    messageBean.type = conversation.getType();
                    messageBean.unRead = conversation.getUnreadMessageNum();
                    List<TIMMessage> messages = conversation.getLastMsgs(1);
                    if (messages.size() > 0) {
                        messageBean.time = messages.get(0).timestamp();
                    } else {
                        messageBean.time = System.currentTimeMillis();
                    }
                    messageBeanArrayList.add(messageBean);
                }
            } else if (conversation.getType() == TIMConversationType.Group) {

            }
        }
        return messageBeanArrayList;
    }

}
