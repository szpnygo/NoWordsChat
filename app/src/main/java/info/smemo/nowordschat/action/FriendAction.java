package info.smemo.nowordschat.action;

import android.support.annotation.NonNull;

import info.smemo.nowordschat.appaction.controller.FriendController;
import info.smemo.nowordschat.base.BaseAction;

public class FriendAction extends BaseAction {

    public static void searchUser(@NonNull String nickname, int page, FriendController.SearchUserListener listener) {
        FriendController.searchUser(nickname, page, listener);
    }

    public static void getFriendList(@NonNull FriendController.GetFriendListener listener) {
        FriendController.getFriendList(listener);
    }

}
