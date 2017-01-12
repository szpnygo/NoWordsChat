package info.smemo.nowordschat.appaction.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.appaction.BR;
import info.smemo.nowordschat.appaction.R;
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

    public UserBean() {

    }

    @Bindable
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
        notifyPropertyChanged(BR.identifier);
    }

    @Bindable
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        notifyPropertyChanged(BR.nickname);
    }

    @Bindable
    public String getFaceurl() {
        return StringUtil.isEmpty(faceurl) ? "res:///" + R.drawable.anonymous : faceurl;
    }

    public void setFaceurl(String faceurl) {
        this.faceurl = faceurl;
        notifyPropertyChanged(BR.faceurl);
    }

    @Bindable
    public String getSelfSignature() {
        return selfSignature;
    }

    public void setSelfSignature(String selfSignature) {
        this.selfSignature = selfSignature;
        notifyPropertyChanged(BR.selfSignature);
    }

    @Bindable
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
        notifyPropertyChanged(BR.remark);
    }

    @Bindable
    public IMFriendAllowType getAllowType() {
        return allowType;
    }

    public void setAllowType(IMFriendAllowType allowType) {
        this.allowType = allowType;
        notifyPropertyChanged(BR.allowType);
    }

    @Bindable
    public String getGender() {
        switch (gender) {
            case Unknow:
                return "未知";
            case Female:
                return "女";
            case Male:
                return "男";
        }
        return "未知";
    }

    public void setGender(IMFriendGenderType gender) {
        this.gender = gender;
        notifyPropertyChanged(BR.gender);
    }

    @Bindable
    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
        notifyPropertyChanged(BR.birthday);
    }

    @Bindable
    public long getLanguage() {
        return language;
    }

    public void setLanguage(long language) {
        this.language = language;
        notifyPropertyChanged(BR.language);
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.location);
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "identifier='" + identifier + '\'' +
                ", nickname='" + nickname + '\'' +
                ", faceurl='" + faceurl + '\'' +
                ", selfSignature='" + selfSignature + '\'' +
                ", remark='" + remark + '\'' +
                ", allowType=" + allowType +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", language=" + language +
                ", location='" + location + '\'' +
                '}';
    }
}
