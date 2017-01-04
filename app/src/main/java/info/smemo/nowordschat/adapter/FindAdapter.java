package info.smemo.nowordschat.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
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
            return new ImagesHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_pic, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ImageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == FIND_TYPE_ONE_PIC) {
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.getBinding().setVariable(BR.bean, findBeanList.get(position));
            imageHolder.getBinding().executePendingBindings();
        } else if (getItemViewType(position) == FIND_TYPE_MORE_PICS) {
            ImagesHolder imageHolders = (ImagesHolder) holder;
            imageHolders.getBinding().setVariable(BR.bean, findBeanList.get(position));
            imageHolders.getBinding().executePendingBindings();
            if (getItemViewType(position) == FIND_TYPE_MORE_PICS) {
                imageHolders.setImages(findBeanList.get(position).getImgUri());
            }
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

        public ImageHolder(View itemView) {
            super(itemView);
        }
    }

    //多图类型
    class ImagesHolder extends NBaseViewHolder {

        private RecyclerView recyclerView;

        private NBaseBindingAdapter findAdapter;
        private ArrayList<String> findUris = new ArrayList<>();

        public ImagesHolder(View itemView) {
            super(itemView);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.finds_list);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

        public void setImages(ArrayList<String> uris) {
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
