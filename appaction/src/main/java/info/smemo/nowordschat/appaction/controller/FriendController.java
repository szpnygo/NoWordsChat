package info.smemo.nowordschat.appaction.controller;

import android.support.annotation.NonNull;

import com.tencent.TIMAddFriendRequest;
import com.tencent.TIMFriendFutureItem;
import com.tencent.TIMFriendFutureMeta;
import com.tencent.TIMFriendResult;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMGetFriendFutureListSucc;
import com.tencent.TIMPageDirectionType;
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

    public interface GetUserListener {

        void success(FriendBean friendBean);

        void error(int code, String message);

    }

    public interface AddFriendListener {

        void success(boolean success, String message);

        void error(int code, String message);

    }

    public interface GetFutureFriendListener {

        void success(ArrayList<BookBean> list);

        void error(int code, String message);
    }


    //搜索用户，作废
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

    //获取任何人的信息
    public static void getUserInfo(String identifier, final GetUserListener listener) {
        List<String> users = new ArrayList<>();
        users.add(identifier);
        TIMFriendshipManager.getInstance().getUsersProfile(users, new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int i, String s) {
                listener.error(i, s);
            }

            @Override
            public void onSuccess(List<TIMUserProfile> timUserProfiles) {
                if (timUserProfiles != null && timUserProfiles.size() > 0) {
                    listener.success(DataBeanAction.toFriendBean(timUserProfiles.get(0)));
                } else {
                    listener.success(null);
                }
            }
        });
    }

    //添加好友
    public static void addFriend(String identifier, String word, final AddFriendListener listener) {
        List<TIMAddFriendRequest> reqList = new ArrayList<>();

        final TIMAddFriendRequest req = new TIMAddFriendRequest();
        req.setAddrSource("AddSource_Type_Android");
        req.setAddWording(word);
        req.setIdentifier(identifier);
        reqList.add(req);

        //申请添加好友
        TIMFriendshipManager.getInstance().addFriend(reqList, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int code, String desc) {
                listener.error(code, desc);
            }

            @Override
            public void onSuccess(List<TIMFriendResult> result) {
                if (result != null && result.size() > 0) {
                    switch (result.get(0).getStatus()) {
                        case TIM_FRIEND_STATUS_UNKNOWN:
                            listener.success(false, "未知错误");
                            break;
                        case TIM_FRIEND_STATUS_SUCC:
                            listener.success(true, "好友添加成功");
                            break;
                        case TIM_ADD_FRIEND_STATUS_PENDING:
                            listener.success(false, "等待好友审核同意");
                            break;
                        case TIM_ADD_FRIEND_STATUS_ALREADY_FRIEND:
                            listener.success(true, "对方已经是您的好友");
                            break;
                        case TIM_ADD_FRIEND_STATUS_FRIEND_SIDE_FORBID_ADD:
                            listener.success(false, "对方设置禁止加好友");
                            break;
                        case TIM_ADD_FRIEND_STATUS_IN_OTHER_SIDE_BLACK_LIST:
                            listener.success(false, "好友添加失败");
                            break;
                        case TIM_ADD_FRIEND_STATUS_IN_SELF_BLACK_LIST:
                            listener.success(false, "好友已被您加入黑名单");
                            break;
                        case TIM_ADD_FRIEND_STATUS_SELF_FRIEND_FULL:
                            listener.success(false, "您的好友列表已满，无法添加新的好友");
                            break;
                        default:
                            listener.success(false, "未知错误" + result.get(0).getStatus().toString());
                            break;
                    }
                } else {
                    listener.success(false, "添加好友异常");
                }
            }
        });

    }

    //获取为决请求
    public static void getFutureFriend(final GetFutureFriendListener listener) {
        TIMFriendFutureMeta meta = new TIMFriendFutureMeta();
        meta.setReqNum(10);
        meta.setDirectionType(TIMPageDirectionType.TIM_PAGE_DIRECTION_DOWN_TYPE);

        long reqFlag = 0, futureFlags = 0;
        reqFlag |= TIMFriendshipManager.TIM_PROFILE_FLAG_NICK;
        reqFlag |= TIMFriendshipManager.TIM_PROFILE_FLAG_REMARK;
        reqFlag |= TIMFriendshipManager.TIM_PROFILE_FLAG_FACE_URL;
        reqFlag |= TIMFriendshipManager.TIM_PROFILE_FLAG_ALLOW_TYPE;
        futureFlags |= TIMFriendshipManager.TIM_FUTURE_FRIEND_PENDENCY_IN_TYPE;
        futureFlags |= TIMFriendshipManager.TIM_FUTURE_FRIEND_RECOMMEND_TYPE;

        TIMFriendshipManager.getInstance().getFutureFriends(reqFlag, futureFlags, null, meta, new TIMValueCallBack<TIMGetFriendFutureListSucc>() {
            @Override
            public void onError(int i, String s) {
                listener.error(i, s);
            }

            @Override
            public void onSuccess(TIMGetFriendFutureListSucc timGetFriendFutureListSucc) {
                ArrayList<BookBean> list = new ArrayList<>();
                for (TIMFriendFutureItem item : timGetFriendFutureListSucc.getItems()) {
                    list.add(DataBeanAction.toFriendFutureBean(item));
                }
                listener.success(list);
            }
        });
    }

}
