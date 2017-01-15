package info.smemo.nowordschat.appaction.bean;

import android.databinding.Bindable;

import info.smemo.nowordschat.appaction.BR;

public class BookBean extends UserBean {

    public String title = "";
    public boolean showLine = false;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
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
