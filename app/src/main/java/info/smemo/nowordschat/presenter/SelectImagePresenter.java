package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import info.smemo.nbaseaction.util.SystemUtil;
import info.smemo.nbaseaction.util.ThreadUtil;
import info.smemo.nowordschat.contract.SelectImageContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class SelectImagePresenter implements SelectImageContract.Presenter {

    private SelectImageContract.View mView;

    private ArrayList<String> list;

    private HashMap<String, List<String>> hashMap;

    public SelectImagePresenter(@NonNull SelectImageContract.View view) {
        mView = checkNotNull(view, "SelectImageContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadData();
    }

    @Override
    public void loadData() {
        mView.showProgressDialog("加载中");
        ThreadUtil.newThreadWithMain(new ThreadUtil.ThreadRunnableVoid() {
            @Override
            public void inThread() {
                getData().clear();
                hashMap = SystemUtil.getSystemImages(mView.getVContext());
                for (Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
                    for (String path : entry.getValue()) {
                        getData().add("file:///" + path);
                    }
                }
            }

            @Override
            public void inMain() {
                mView.notifyDataSetChanged();
            }
        });
    }

    @Override
    public ArrayList<String> getData() {
        if (list == null)
            list = new ArrayList<>();
        return list;
    }
}
