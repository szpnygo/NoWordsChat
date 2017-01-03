package info.smemo.nowordschat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBaseViewHolder;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.bean.FindBean;

public class FindAdapter extends RecyclerView.Adapter {

    public static final int FIND_TYPE_ONE_PIC = 1;
    public static final int FIND_TYPE_MORE_PICS = 2;

    private Context mContext;

    private ArrayList<FindBean> findBeanList;

    public FindAdapter(Context context) {
        this.mContext = context;
    }

    public FindAdapter(Context context, ArrayList<FindBean> findBeanList) {
        mContext = context;
        this.findBeanList = findBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FIND_TYPE_MORE_PICS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_pics, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ImageHolder(view, viewType);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_pic, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ImageHolder(view, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FIND_TYPE_MORE_PICS || getItemViewType(position) == FIND_TYPE_ONE_PIC) {
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.getBinding().setVariable(BR.bean, findBeanList.get(position));
            imageHolder.getBinding().executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return findBeanList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return findBeanList.get(position).imgUri.size() > 1 ? FIND_TYPE_MORE_PICS : FIND_TYPE_ONE_PIC;
    }

    //图片类型
    class ImageHolder extends NBaseViewHolder {

        private int type;

        public ImageHolder(View itemView, int type) {
            super(itemView);
            this.type = type;
        }

    }
}
