package info.smemo.nowordschat.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import info.smemo.nowordschat.BR;

public class FindBean extends BaseObservable {

    public String imgUri;

    @Bindable
    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
        notifyPropertyChanged(BR.imgUri);
    }
}
