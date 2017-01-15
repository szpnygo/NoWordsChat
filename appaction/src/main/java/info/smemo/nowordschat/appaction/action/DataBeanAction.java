package info.smemo.nowordschat.appaction.action;

import com.tencent.TIMUserProfile;

import info.smemo.nowordschat.appaction.bean.BookBean;
import info.smemo.nowordschat.appaction.bean.FriendBean;
import info.smemo.nowordschat.appaction.bean.UserBean;
import info.smemo.nowordschat.appaction.enums.IMFriendAllowType;
import info.smemo.nowordschat.appaction.enums.IMFriendGenderType;

public class DataBeanAction {

    //生成用户信息
    public static FriendBean toFriendBean(TIMUserProfile userProfile) {
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

    //生成用户信息
    public static UserBean toUserBean(TIMUserProfile userProfile) {
        UserBean mUserBean = new UserBean();
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

    //生成用户信息
    public static BookBean toBookBean(TIMUserProfile userProfile) {
        BookBean mUserBean = new BookBean();
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
