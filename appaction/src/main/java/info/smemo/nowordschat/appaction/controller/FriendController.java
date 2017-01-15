package info.smemo.nowordschat.appaction.controller;

import android.support.annotation.NonNull;

import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserProfile;
import com.tencent.TIMUserSearchSucc;
import com.tencent.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;

import info.smemo.library.tencetim.IMFriendController;
import info.smemo.nowordschat.appaction.action.DataBeanAction;
import info.smemo.nowordschat.appaction.bean.BookBean;
import info.smemo.nowordschat.appaction.bean.FriendBean;

public class FriendController {

    public static final int PAGE_SIZE = 20;

    public interface SearchUserListener {

        void error(int code, String message);

        void success(ArrayList<FriendBean> list);
    }

    public interface GetFriendListener {

        void success(ArrayList<BookBean> list);

        void error(int code, String message);

    }

    public static void searchUser(@NonNull String nickname, int page, @NonNull final SearchUserListener listener) {
        searchUser(nickname, page, PAGE_SIZE, listener);
    }

    //搜索用户
    public static void searchUser(@NonNull String nickname, int page, int pageSize, @NonNull final SearchUserListener listener) {
        IMFriendController.searchUser(nickname, page, pageSize, new TIMValueCallBack<TIMUserSearchSucc>() {
            @Override
            public void onError(int i, String s) {
                listener.error(i, s);
            }

            @Override
            public void onSuccess(TIMUserSearchSucc timUserSearchSucc) {
                List<TIMUserProfile> list = timUserSearchSucc.getInfoList();
                ArrayList<FriendBean> friendBeans = new ArrayList<>();
                for (TIMUserProfile profile : list) {
                    friendBeans.add(DataBeanAction.toFriendBean(profile));
                }
                listener.success(friendBeans);
            }
        });
    }

    //获取好友列表
    public static void getFriendList(@NonNull final GetFriendListener listener) {
        TIMFriendshipManager.getInstance().getFriendList(new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int i, String s) {
                listener.error(i, s);
            }

            @Override
            public void onSuccess(List<TIMUserProfile> timUserProfiles) {
                ArrayList<BookBean> list = new ArrayList<>();
                for (TIMUserProfile profile : timUserProfiles) {
                    list.add(DataBeanAction.toBookBean(profile));
                }
                listener.success(list);
            }
        });
    }

    //是否是朋友
    public static boolean isFriend(String identifier) {
        List<String> list = new ArrayList<>();
        list.add(identifier);
        List<TIMUserProfile> identifiers = IMFriendController.getLocalFriendsById(list);
        return identifiers != null && identifiers.size() > 0;
    }

    //获取本地好友列表
    public static ArrayList<FriendBean> getLocalFriends() {
        List<TIMUserProfile> timUserProfiles = IMFriendController.getLocalFriends();
        ArrayList<FriendBean> friendBeanArrayList = new ArrayList<>();
        for (TIMUserProfile profile : timUserProfiles) {
            friendBeanArrayList.add(DataBeanAction.toFriendBean(profile));
        }
        return friendBeanArrayList;
    }

}
