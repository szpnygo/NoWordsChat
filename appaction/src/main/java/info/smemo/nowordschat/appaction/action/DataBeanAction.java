package info.smemo.nowordschat.appaction.action;

import com.tencent.TIMFriendFutureItem;
import com.tencent.TIMUserProfile;

import info.smemo.nowordschat.appaction.bean.BookBean;
import info.smemo.nowordschat.appaction.bean.FriendBean;
import info.smemo.nowordschat.appaction.bean.UserBean;
import info.smemo.nowordschat.appaction.enums.IMFriendAllowType;
import info.smemo.nowordschat.appaction.enums.IMFriendGenderType;
import info.smemo.nowordschat.appaction.enums.IMFutureFriendType;

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
        mUserBean.type = IMFutureFriendType.IM_FUTURE_FRIEND_DECIDE_TYPE;
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

    //生成未决请求对象
    public static BookBean toFriendFutureBean(TIMFriendFutureItem item) {
        BookBean futureBean = new BookBean();
        futureBean.addSource = item.getAddSource();
        futureBean.addTime = item.getAddTime();
        futureBean.identifier = item.getIdentifier();
        if (null != item.getType()) {
            switch (item.getType()) {
                case TIM_FUTURE_FRIEND_DECIDE_TYPE:
                    futureBean.type = IMFutureFriendType.IM_FUTURE_FRIEND_DECIDE_TYPE;
                    break;
                case TIM_FUTURE_FRIEND_PENDENCY_IN_TYPE:
                    futureBean.type = IMFutureFriendType.IM_FUTURE_FRIEND_PENDENCY_IN_TYPE;
                    break;
                case TIM_FUTURE_FRIEND_PENDENCY_OUT_TYPE:
                    futureBean.type = IMFutureFriendType.IM_FUTURE_FRIEND_PENDENCY_OUT_TYPE;
                    break;
                case TIM_FUTURE_FRIEND_RECOMMEND_TYPE:
                    futureBean.type = IMFutureFriendType.IM_FUTURE_FRIEND_RECOMMEND_TYPE;
                    break;
            }
        }

        futureBean.setIdentifier(item.getProfile().getIdentifier());
        futureBean.setNickname(item.getProfile().getNickName());
        futureBean.faceurl = item.getProfile().getFaceUrl();
        futureBean.selfSignature = item.getProfile().getSelfSignature();
        if (null != item.getProfile().getAllowType()) {
            switch (item.getProfile().getAllowType()) {
                case TIM_FRIEND_ALLOW_ANY:
                    futureBean.allowType = IMFriendAllowType.IM_FRIEND_ALLOW_ANY;
                    break;
                case TIM_FRIEND_INVALID:
                    futureBean.allowType = IMFriendAllowType.IM_FRIEND_INVALID;
                    break;
                case TIM_FRIEND_NEED_CONFIRM:
                    futureBean.allowType = IMFriendAllowType.IM_FRIEND_NEED_CONFIRM;
                    break;
                case TIM_FRIEND_DENY_ANY:
                    futureBean.allowType = IMFriendAllowType.IM_FRIEND_DENY_ANY;
                    break;
            }
        }
        futureBean.remark = item.getProfile().getRemark();
        if (null != item.getProfile().getGender()) {
            switch (item.getProfile().getGender()) {
                case Unknow:
                    futureBean.gender = IMFriendGenderType.Unknow;
                    break;
                case Female:
                    futureBean.gender = IMFriendGenderType.Female;
                    break;
                case Male:
                    futureBean.gender = IMFriendGenderType.Male;
                    break;
            }
        }
        futureBean.birthday = item.getProfile().getBirthday();
        futureBean.language = item.getProfile().getLanguage();
        futureBean.location = item.getProfile().getLocation();

        return futureBean;
    }

}
