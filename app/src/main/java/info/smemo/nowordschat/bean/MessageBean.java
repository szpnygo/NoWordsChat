package info.smemo.nowordschat.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import info.smemo.nowordschat.BR;

public class MessageBean extends BaseObservable {

    public String username;
    public String userLogo;
    public String message;
    public String time;
    public String account;
    public int unRead = 0;
    public int id;

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
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }

    @Bindable
    public int getUnRead() {
        return unRead;
    }

    public void setUnRead(int unRead) {
        this.unRead = unRead;
        notifyPropertyChanged(BR.unRead);
    }
}
