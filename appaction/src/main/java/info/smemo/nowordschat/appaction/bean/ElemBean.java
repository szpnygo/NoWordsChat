package info.smemo.nowordschat.appaction.bean;

import android.databinding.Bindable;
import android.databinding.BaseObservable;

import com.tencent.TIMCustomElem;
import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMFaceElem;
import com.tencent.TIMFileElem;
import com.tencent.TIMGroupSystemElem;
import com.tencent.TIMGroupTipsElem;
import com.tencent.TIMImageElem;
import com.tencent.TIMLocationElem;
import com.tencent.TIMMessage;
import com.tencent.TIMProfileSystemElem;
import com.tencent.TIMSNSSystemElem;
import com.tencent.TIMSoundElem;
import com.tencent.TIMTextElem;

import info.smemo.nowordschat.appaction.BR;

public class ElemBean extends BaseObservable {

    private TIMCustomElem timCustomElem;
    private TIMFaceElem timFaceElem;
    private TIMFileElem timFileElem;
    private TIMGroupSystemElem timGroupSystemElem;
    private TIMGroupTipsElem timGroupTipsElem;
    private TIMImageElem timImageElem;
    private TIMLocationElem timLocationElem;
    private TIMProfileSystemElem timProfileSystemElem;
    private TIMSNSSystemElem timsnsSystemElem;
    private TIMSoundElem timSoundElem;
    private TIMTextElem timTextElem;

    private TIMElemType elemType;

    private TIMElem timElem;
    private TIMMessage timMessage;

    public ElemBean(TIMMessage message,TIMElem timElem) {
        this.elemType = timElem.getType();
        this.timElem = timElem;
        this.timMessage = message;
        switch (timElem.getType()) {
            case Custom:
                this.setTimCustomElem((TIMCustomElem) timElem);
                break;
            case Face:
                this.setTimFaceElem((TIMFaceElem) timElem);
                break;
            case File:
                this.setTimFileElem((TIMFileElem) timElem);
                break;
            case GroupSystem:
                this.setTimGroupSystemElem((TIMGroupSystemElem) timElem);
                break;
            case GroupTips:
                this.setTimGroupTipsElem((TIMGroupTipsElem) timElem);
                break;
            case Image:
                this.setTimImageElem((TIMImageElem) timElem);
                break;
            case Location:
                this.setTimLocationElem((TIMLocationElem) timElem);
                break;
            case ProfileTips:
                this.setTimProfileSystemElem((TIMProfileSystemElem) timElem);
                break;
            case SNSTips:
                this.setTimsnsSystemElem((TIMSNSSystemElem) timElem);
                break;
            case Sound:
                this.setTimSoundElem((TIMSoundElem) timElem);
                break;
            case Text:
                this.setTimTextElem((TIMTextElem) timElem);
                break;
        }
    }

    public ElemBean(TIMCustomElem timCustomElem) {
        this.timCustomElem = timCustomElem;
        this.elemType = timCustomElem.getType();
    }

    public ElemBean(TIMFaceElem timFaceElem) {
        this.timFaceElem = timFaceElem;
        this.elemType = timFaceElem.getType();
    }

    public ElemBean(TIMFileElem timFileElem) {
        this.timFileElem = timFileElem;
        this.elemType = timFileElem.getType();
    }

    public ElemBean(TIMGroupSystemElem timGroupSystemElem) {
        this.timGroupSystemElem = timGroupSystemElem;
        this.elemType = timGroupSystemElem.getType();
    }

    public ElemBean(TIMGroupTipsElem timGroupTipsElem) {
        this.timGroupTipsElem = timGroupTipsElem;
        this.elemType = timGroupTipsElem.getType();
    }

    public ElemBean(TIMImageElem timImageElem) {
        this.timImageElem = timImageElem;
        this.elemType = timImageElem.getType();
    }

    public ElemBean(TIMLocationElem timLocationElem) {
        this.timLocationElem = timLocationElem;
        this.elemType = timLocationElem.getType();
    }

    public ElemBean(TIMProfileSystemElem timProfileSystemElem) {
        this.timProfileSystemElem = timProfileSystemElem;
        this.elemType = timProfileSystemElem.getType();
    }

    public ElemBean(TIMSNSSystemElem timsnsSystemElem) {
        this.timsnsSystemElem = timsnsSystemElem;
        this.elemType = timsnsSystemElem.getType();
    }

    public ElemBean(TIMSoundElem timSoundElem) {
        this.timSoundElem = timSoundElem;
        this.elemType = timSoundElem.getType();
    }

    public ElemBean(TIMTextElem timTextElem) {
        this.timTextElem = timTextElem;
        this.elemType = timTextElem.getType();
    }

    @Bindable
    public TIMCustomElem getTimCustomElem() {
        return timCustomElem;
    }

    public void setTimCustomElem(TIMCustomElem timCustomElem) {
        this.timCustomElem = timCustomElem;
        notifyPropertyChanged(BR.timCustomElem);
    }

    @Bindable
    public TIMFaceElem getTimFaceElem() {
        return timFaceElem;
    }

    public void setTimFaceElem(TIMFaceElem timFaceElem) {
        this.timFaceElem = timFaceElem;
        notifyPropertyChanged(BR.timFaceElem);
    }

    @Bindable
    public TIMFileElem getTimFileElem() {
        return timFileElem;
    }

    public void setTimFileElem(TIMFileElem timFileElem) {
        this.timFileElem = timFileElem;
        notifyPropertyChanged(BR.timFileElem);
    }

    @Bindable
    public TIMGroupSystemElem getTimGroupSystemElem() {
        return timGroupSystemElem;
    }

    public void setTimGroupSystemElem(TIMGroupSystemElem timGroupSystemElem) {
        this.timGroupSystemElem = timGroupSystemElem;
        notifyPropertyChanged(BR.timGroupSystemElem);
    }

    @Bindable
    public TIMGroupTipsElem getTimGroupTipsElem() {
        return timGroupTipsElem;
    }

    public void setTimGroupTipsElem(TIMGroupTipsElem timGroupTipsElem) {
        this.timGroupTipsElem = timGroupTipsElem;
        notifyPropertyChanged(BR.timGroupTipsElem);
    }

    @Bindable
    public TIMImageElem getTimImageElem() {
        return timImageElem;
    }

    public void setTimImageElem(TIMImageElem timImageElem) {
        this.timImageElem = timImageElem;
        notifyPropertyChanged(BR.timImageElem);
    }

    @Bindable
    public TIMLocationElem getTimLocationElem() {
        return timLocationElem;
    }

    public void setTimLocationElem(TIMLocationElem timLocationElem) {
        this.timLocationElem = timLocationElem;
        notifyPropertyChanged(BR.timLocationElem);
    }

    @Bindable
    public TIMProfileSystemElem getTimProfileSystemElem() {
        return timProfileSystemElem;
    }

    public void setTimProfileSystemElem(TIMProfileSystemElem timProfileSystemElem) {
        this.timProfileSystemElem = timProfileSystemElem;
        notifyPropertyChanged(BR.timProfileSystemElem);
    }

    @Bindable
    public TIMSNSSystemElem getTimsnsSystemElem() {
        return timsnsSystemElem;
    }

    public void setTimsnsSystemElem(TIMSNSSystemElem timsnsSystemElem) {
        this.timsnsSystemElem = timsnsSystemElem;
        notifyPropertyChanged(BR.timsnsSystemElem);
    }

    @Bindable
    public TIMSoundElem getTimSoundElem() {
        return timSoundElem;
    }

    public void setTimSoundElem(TIMSoundElem timSoundElem) {
        this.timSoundElem = timSoundElem;
        notifyPropertyChanged(BR.timSoundElem);
    }

    @Bindable
    public TIMTextElem getTimTextElem() {
        return timTextElem;
    }

    public void setTimTextElem(TIMTextElem timTextElem) {
        this.timTextElem = timTextElem;
        notifyPropertyChanged(BR.timTextElem);
    }

    @Bindable
    public TIMElemType getElemType() {
        return elemType;
    }

    public void setElemType(TIMElemType elemType) {
        this.elemType = elemType;
        notifyPropertyChanged(BR.elemType);
    }

    @Bindable
    public TIMElem getTimElem() {
        return timElem;
    }

    public void setTimElem(TIMElem timElem) {
        this.timElem = timElem;
        notifyPropertyChanged(BR.timElem);
    }

    @Bindable
    public TIMMessage getTimMessage() {
        return timMessage;
    }

    public void setTimMessage(TIMMessage timMessage) {
        this.timMessage = timMessage;
        notifyPropertyChanged(BR.timMessage);
    }
}
