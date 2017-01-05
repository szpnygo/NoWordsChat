package info.smemo.nowordschat.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nbaseaction.base.NBaseViewHolder;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.bean.FindBean;

public class FindAdapter extends RecyclerView.Adapter {

    public static final int HEADER_SIZE = 1;

    public static final int FIND_TYPE_EMPTY = -1;
    public static final int FIND_TYPE_HEADER = 0;
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
        if (viewType == FIND_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_header, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new HeaderHolder(view);
        } else if (viewType == FIND_TYPE_EMPTY) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_header, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new HeaderHolder(view);
        } else if (viewType == FIND_TYPE_MORE_PICS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_pics, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ImagesHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_pics, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ImagesHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FIND_TYPE_EMPTY) {

            HeaderHolder headerHolder = (HeaderHolder) holder;
            headerHolder.setVisibility(false);

        } else if (getItemViewType(position) == FIND_TYPE_HEADER) {

            HeaderHolder headerHolder = (HeaderHolder) holder;
//            headerHolder.setVisibility(false);

        } else if (getItemViewType(position) == FIND_TYPE_MORE_PICS) {

            ImagesHolder imagesHolder = (ImagesHolder) holder;
            imagesHolder.getBinding().setVariable(BR.bean, getItem(position));
            imagesHolder.getBinding().executePendingBindings();
            imagesHolder.setImages(getItem(position).getImgUri());

        }
    }

    public FindBean getItem(int position) {
        return findBeanList.get(position - HEADER_SIZE);
    }

    @Override
    public int getItemCount() {
        return findBeanList.size() + HEADER_SIZE;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return FIND_TYPE_HEADER;
        if (position - HEADER_SIZE >= findBeanList.size())
            return FIND_TYPE_EMPTY;
        return FIND_TYPE_MORE_PICS;
    }

    //头部消息提醒
    class HeaderHolder extends NBaseViewHolder {

        public SimpleDraweeView logo;
        public String message;

        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    //图类型
    class ImagesHolder extends NBaseViewHolder {

        private RecyclerView recyclerView;

        private NBaseBindingAdapter findAdapter;
        private ArrayList<String> findUris = new ArrayList<>();

        private GridLayoutManager gridLayoutManager;

        private SimpleDraweeView image;

        public ImagesHolder(View itemView) {
            super(itemView);
            gridLayoutManager = new GridLayoutManager(mContext, 3);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.finds_list);
            recyclerView.setLayoutManager(gridLayoutManager);
            image = (SimpleDraweeView) itemView.findViewById(R.id.image);
        }

        public void setImages(ArrayList<String> uris) {
            if (uris.size() <= 1) {
                image.setVisibility(View.VISIBLE);
                return;
            } else {
                image.setVisibility(View.GONE);
            }
            if (null == findAdapter) {
                findAdapter = new NBaseBindingAdapter<>(uris, BR.uri, R.layout.item_find_finds,
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                recyclerView.setAdapter(findAdapter);
            }
            findUris.clear();
            for (String uri : uris) {
                findUris.add(uri);
            }

            findAdapter.notifyDataSetChanged();
        }

    }
}
