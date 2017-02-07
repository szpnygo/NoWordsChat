package info.smemo.nowordschat.action;

import android.support.annotation.NonNull;

import info.smemo.nowordschat.appaction.ActionInterface;
import info.smemo.nowordschat.appaction.controller.FriendController;
import info.smemo.nowordschat.base.BaseAction;

public class FriendAction extends BaseAction {

    public static void searchUser(@NonNull String nickname, int page, FriendController.SearchUserListener listener) {
        FriendController.searchUser(nickname, page, listener);
    }

    public static void getFriendList(@NonNull FriendController.GetFriendListener listener) {
        FriendController.getFriendList(listener);
    }

    public static boolean isFriend(String identifier) {
        return FriendController.isFriend(identifier);
    }

    public static void getUserInfo(String identifier, FriendController.GetUserListener listener) {
        FriendController.getUserInfo(identifier, listener);
    }

    public static void addFriend(@NonNull String identifier, @NonNull FriendController.AddFriendListener listener) {
        addFriend(identifier, "", listener);
    }

    public static void addFriend(String identifier, String words, FriendController.AddFriendListener listener) {
        FriendController.addFriend(identifier, words, listener);
    }

    public static void getFuture(final FriendController.GetFutureFriendListener listener) {
        FriendController.getFutureFriend(listener);
    }

    public static void addFriendResponse(String identifer, boolean isAccept, ActionInterface.BaseComplete listener) {
        FriendController.addFriendResponse(identifer, isAccept, listener);
    }
}
