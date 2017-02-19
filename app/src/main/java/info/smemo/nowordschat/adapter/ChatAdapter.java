package info.smemo.nowordschat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.adapter.holder.ChatHolder;
import info.smemo.nowordschat.adapter.holder.ImageHolder;
import info.smemo.nowordschat.adapter.holder.YueHolder;
import info.smemo.nowordschat.app.AppConstant;
import info.smemo.nowordschat.appaction.bean.ElemBean;

public class ChatAdapter extends RecyclerView.Adapter implements AppConstant {

    private static final int TYPE_CUSTOM = 1;
    private static final int TYPE_IMAGE = 2;

    private ArrayList<ElemBean> list;

    public ChatAdapter(ArrayList<ElemBean> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CUSTOM:
                View yueView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_yue, null);
                yueView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return new YueHolder(yueView);
            case TYPE_IMAGE:
                View imageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_image, null);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                return new ImageHolder(imageView);
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
                return TYPE_CUSTOM;
            case Image:
                return TYPE_IMAGE;
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

}


