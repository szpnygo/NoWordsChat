package info.smemo.nowordschat.appaction.controller;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tencent.TIMCallBack;
import com.tencent.TIMUserProfile;

import info.smemo.library.tencetim.IMInterface;
import info.smemo.library.tencetim.IMLoginController;
import info.smemo.library.tencetim.IMUserController;
import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.appaction.ActionInterface;
import info.smemo.nowordschat.appaction.bean.UserBean;
import info.smemo.nowordschat.appaction.enums.IMFriendAllowType;
import info.smemo.nowordschat.appaction.enums.IMFriendGenderType;

public class UserController {

    private static UserController instance;

    private IMLoginController mLoginController = IMLoginController.getInstance();
    private IMUserController mUserController = IMUserController.getInstance();

    private UserBean mUserBean = null;

    private static class Single {
        private static UserController instance = new UserController();
    }

    public static synchronized UserController getInstance() {
        if (null == instance) {
            instance = Single.instance;
        }
        return instance;
    }

    public interface RegisterSuccessListener extends ActionInterface.BaseComplete {
    }

    public interface LoginSuccessListener extends ActionInterface.BaseComplete {
    }

    public interface LoadUserProfileSuccessListener extends ActionInterface.BaseComplete {
    }


    //注册
    public void register(@NonNull String email, @NonNull String password, @NonNull String nickname, @NonNull final RegisterSuccessListener listener) {
        mUserController.register(email, password, nickname, new IMInterface.RegisterListener() {
            @Override
            public void success() {
                listener.success();
            }

            @Override
            public void error(int code, String message) {
                listener.error(code, message);
            }
        });
    }

    //登录
    public void login(@NonNull String account, @NonNull String password, @NonNull final LoginSuccessListener listener) {
        mLoginController.login(account, password, new IMInterface.LoginListener() {
            @Override
            public void success() {
                listener.success();
            }

            @Override
            public void error(int code, String message) {
                listener.error(code, message);
            }
        });
    }

    //是否需要登录
    public boolean needLogin() {
        return mLoginController.needLogin();
    }

    //获取用户资料
    public void getUser(final LoadUserProfileSuccessListener listener) {
        mUserController.getSelfProfile(new IMInterface.SelfProfileListener() {
            @Override
            public void success(TIMUserProfile userProfile) {
                if (StringUtil.isEmpty(userProfile.getIdentifier())) {
                    if (null != listener)
                        listener.error(-1, "获取用户信息identifier异常");
                } else {
                    toUserInfo(userProfile);
                    listener.success();
                }

            }

            @Override
            public void error(int code, String message) {
                if (null != listener)
                    listener.error(code, message);
            }
        });
    }

    //生成用户信息
    private void toUserInfo(TIMUserProfile userProfile) {
        if (null == mUserBean)
            mUserBean = new UserBean(userProfile.getIdentifier());
        mUserBean.identifier = userProfile.getIdentifier();
        mUserBean.nickname = userProfile.getNickName();
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
    }

    //获取用户信息
    public UserBean getUserInfo() {
        return mUserBean;
    }

    //退出
    public void logout() {
        mLoginController.logout();
    }

    public String getTmpNcikName(Context context) {
        return mUserController.getTmpNickname(context);
    }


    public void setNickname(String nickname, final ActionInterface.BaseComplete complete) {
        mUserController.getFriendshipManager().setNickName(nickname, new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                complete.error(i, s);
            }

            @Override
            public void onSuccess() {
                complete.success();
            }
        });
    }

}
