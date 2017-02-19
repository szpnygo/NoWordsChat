package info.smemo.nowordschat.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tencent.TIMMessage;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBaseViewHolder;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.app.AppConstant;
import info.smemo.nowordschat.appaction.bean.ElemBean;
import info.smemo.nowordschat.util.TimeHelper;

public class ChatAdapter extends RecyclerView.Adapter implements AppConstant {

    private static final int TYPE_CUSTOM = 1;

    private ArrayList<ElemBean> list;
    private Context context;

    public ChatAdapter(Context context, ArrayList<ElemBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CUSTOM:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_yue, null);
                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return new YueHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatHolder chatHolder = (ChatHolder) holder;
        chatHolder.getBinding().setVariable(BR.elem, getItem(position));
        chatHolder.getBinding().executePendingBindings();
        chatHolder.setElem(getItem(position));
    }

    @Override
    public int getItemViewType(int position) {
        switch (getItem(position).getElemType()) {
            case Custom:
                return 1;
            default:
                return 0;
        }
    }

    private ElemBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ChatHolder extends NBaseViewHolder {

        private ElemBean elem;

        public ChatHolder(View itemView) {
            super(itemView);
        }

        public void setElem(ElemBean elem) {
            this.elem = elem;
        }
    }

    class YueHolder extends ChatHolder {

        public YueHolder(View itemView) {
            super(itemView);
        }
    }

    @BindingAdapter({"android:chatYue"})
    public static void showText(TextView view, TIMMessage message) {

        view.setText((message.isSelf() ? "您" : "对方") + TimeHelper.getTimePassStr(message.timestamp() * 1000) + "约了" + (message.isSelf() ? "对方" : "您") + "一下");
    }

}


