package info.smemo.nowordschat.appaction.bean;

import android.databinding.BaseObservable;

import info.smemo.nowordschat.appaction.enums.IMFriendAllowType;
import info.smemo.nowordschat.appaction.enums.IMFriendGenderType;

/**
 * 用户资料
 * Created by neo on 17/1/11.
 */

public class UserBean extends BaseObservable {

    public String identifier;
    public String nickname;
    public String faceurl;
    public String selfSignature;
    public String remark;
    public IMFriendAllowType allowType;
    public IMFriendGenderType gender = IMFriendGenderType.Unknow;//0未知 1男 2女
    public long birthday;
    public long language;
    public String location;

    public UserBean(String identifier) {

    }
}
