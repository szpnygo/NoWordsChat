package info.smemo.nowordschat.appaction.bean;

import android.databinding.Bindable;

import info.smemo.nbaseaction.util.PinyinUtil;
import info.smemo.nowordschat.appaction.BR;

public class BookBean extends UserBean {

    public String firstChar = "";
    public String title = "";
    public boolean showLine = false;

    @Bindable
    public String getTitle() {
        return title;
    }

    @Override
    public void setNickname(String nickname) {
        super.setNickname(nickname);
        setTitle(PinyinUtil.getFirstChar(nickname));
        setFirstChar(PinyinUtil.getFirstChar(nickname));
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

    @Bindable
    public String getFirstChar() {
        return firstChar;
    }

    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
        notifyPropertyChanged(BR.firstChar);
    }
}
