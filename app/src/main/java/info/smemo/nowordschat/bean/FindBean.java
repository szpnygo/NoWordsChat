package info.smemo.nowordschat.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;

import info.smemo.nowordschat.BR;

public class FindBean extends BaseObservable {

    //图片列表
    public ArrayList<String> imgUri;

    //发布时间
    public long time;
    //喜爱数
    public int favorites;
    //评论数量
    public int comments;

    //动态发布人昵称
    public String username;
    //动态发布人头像
    public String userLogo;
    //动态发布人账号
    public String account;

    public FindBean() {
        imgUri = new ArrayList<>();
    }

    @Bindable
    public ArrayList<String> getImgUri() {
        return imgUri;
    }

    public void setImgUri(ArrayList<String> imgUri) {
        this.imgUri = imgUri;
        notifyPropertyChanged(BR.imgUri);
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
    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
        notifyPropertyChanged(BR.favorites);
    }

    @Bindable
    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
        notifyPropertyChanged(BR.comments);
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
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }
}
