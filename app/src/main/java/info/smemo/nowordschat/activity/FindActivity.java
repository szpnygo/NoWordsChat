package info.smemo.nowordschat.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.adapter.FindAdapter;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.bean.FindBean;
import info.smemo.nowordschat.contract.FindContract;
import info.smemo.nowordschat.presenter.FindPresenter;

public class FindActivity extends BaseCompatActivity implements FindContract.View {

    private FindAdapter findAdapter;
    private ArrayList<FindBean> mFindBeanArrayList;

    private FindPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("朋友圈");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);

        initAdapter();

        mPresenter = new FindPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void initAdapter() {
        mFindBeanArrayList = new ArrayList<>();
        findAdapter = new FindAdapter(this, mFindBeanArrayList);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.find_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(findAdapter);
    }

    /**
     * 发布小动态
     */
    public void postClick(View view) {

    }

    @Override
    public void showFinds(ArrayList<FindBean> list) {
        mFindBeanArrayList.clear();
        for (FindBean bean : list) {
            mFindBeanArrayList.add(bean);
        }
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        findAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessageToast(int message) {
        findAdapter.setNewMessage(message);
    }

    @Override
    public void setPresenter(FindContract.Presenter presenter) {

    }
}
