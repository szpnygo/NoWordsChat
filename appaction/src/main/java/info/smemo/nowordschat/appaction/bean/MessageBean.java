package info.smemo.nowordschat.appaction.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tencent.TIMConversationType;

import info.smemo.nowordschat.appaction.BR;


public class MessageBean extends BaseObservable {

    public String identifier;
    public String username;
    public String userLogo;

    public String message;
    public long time;

    public TIMConversationType type;

    public long unRead = 0;

    @Bindable
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
    }

    @Bindable
    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
        notifyPropertyChanged(BR.userLogo);
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }

    @Bindable
    public long getUnRead() {
        return unRead;
    }

    public void setUnRead(long unRead) {
        this.unRead = unRead;
        notifyPropertyChanged(BR.unRead);
    }
}
