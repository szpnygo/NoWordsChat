package info.smemo.nowordschat.adapter.holder;

import android.content.Context;
import android.view.View;

import info.smemo.nbaseaction.base.NBaseViewHolder;
import info.smemo.nowordschat.appaction.bean.ElemBean;

public class ChatHolder extends NBaseViewHolder {

    protected ElemBean elem;
    protected Context mContext;

    public ChatHolder(Context context,View itemView) {
        super(itemView);
        this.mContext = context;
    }

    public void setElem(ElemBean elem) {
        this.elem = elem;
    }
}
