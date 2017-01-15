package info.smemo.nowordschat.appaction.bean;

import info.smemo.nowordschat.appaction.controller.FriendController;

public class FriendBean extends UserBean {

    public boolean isLocalFriend() {
        return FriendController.isFriend(identifier);
    }

}
