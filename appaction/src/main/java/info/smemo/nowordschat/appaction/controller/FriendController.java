package info.smemo.nowordschat.appaction.controller;

import android.support.annotation.NonNull;

import com.tencent.TIMUserProfile;
import com.tencent.TIMUserSearchSucc;
import com.tencent.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;

import info.smemo.library.tencetim.IMFriendController;
import info.smemo.nowordschat.appaction.bean.FriendBean;
import info.smemo.nowordschat.appaction.enums.IMFriendAllowType;
import info.smemo.nowordschat.appaction.enums.IMFriendGenderType;

public class FriendController {

    public static final int PAGE_SIZE = 20;

    interface SearchUserListener {

        void error(int code, String message);

        void success(ArrayList<FriendBean> list);
    }

    public static void searchUser(@NonNull String nickname, int page, @NonNull final SearchUserListener listener) {
        searchUser(nickname, page, PAGE_SIZE, listener);
    }

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
                    friendBeans.add(toUserInfo(profile));
                }
                listener.success(friendBeans);
            }
        });
    }

    //生成用户信息
    private static FriendBean toUserInfo(TIMUserProfile userProfile) {
        FriendBean mUserBean = new FriendBean();
        mUserBean.setIdentifier(userProfile.getIdentifier());
        mUserBean.setNickname(userProfile.getNickName());
        mUserBean.faceurl = userProfile.getFaceUrl();
        mUserBean.selfSignature = userProfile.getSelfSignature();
        if (null != userProfile.getAllowType()) {
            switch (userProfile.getAllowType()) {
                case TIM_FRIEND_ALLOW_ANY:
                    mUserBean.allowType = IMFriendAllowType.IM_FRIEND_ALLOW_ANY;
                    break;
                case TIM_FRIEND_INVALID:
                    mUserBean.allowType = IMFriendAllowType.IM_FRIEND_INVALID;
                    break;
                case TIM_FRIEND_NEED_CONFIRM:
                    mUserBean.allowType = IMFriendAllowType.IM_FRIEND_NEED_CONFIRM;
                    break;
                case TIM_FRIEND_DENY_ANY:
                    mUserBean.allowType = IMFriendAllowType.IM_FRIEND_DENY_ANY;
                    break;
            }
        }
        mUserBean.remark = userProfile.getRemark();
        if (null != userProfile.getGender()) {
            switch (userProfile.getGender()) {
                case Unknow:
                    mUserBean.gender = IMFriendGenderType.Unknow;
                    break;
                case Female:
                    mUserBean.gender = IMFriendGenderType.Female;
                    break;
                case Male:
                    mUserBean.gender = IMFriendGenderType.Male;
                    break;
            }
        }
        mUserBean.birthday = userProfile.getBirthday();
        mUserBean.language = userProfile.getLanguage();
        mUserBean.location = userProfile.getLocation();

        return mUserBean;
    }

}
