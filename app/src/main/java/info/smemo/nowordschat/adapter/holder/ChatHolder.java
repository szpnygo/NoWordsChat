package info.smemo.nowordschat.adapter.holder;

import android.view.View;

import info.smemo.nbaseaction.base.NBaseViewHolder;
import info.smemo.nowordschat.appaction.bean.ElemBean;

public class ChatHolder extends NBaseViewHolder {

    private ElemBean elem;

    public ChatHolder(View itemView) {
        super(itemView);
    }

    public void setElem(ElemBean elem) {
        this.elem = elem;
    }
}
