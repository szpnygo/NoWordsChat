package info.smemo.nowordschat.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import info.smemo.nowordschat.BR;

public class BookBean extends BaseObservable {

    public String title;
    public String username;
    public String userLogo;
    public boolean showLine = false;

    public String account;
    public int id;


    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

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
    public boolean isShowLine() {
        return showLine;
    }

    public void setShowLine(boolean showLine) {
        this.showLine = showLine;
        notifyPropertyChanged(BR.showLine);
    }
}
