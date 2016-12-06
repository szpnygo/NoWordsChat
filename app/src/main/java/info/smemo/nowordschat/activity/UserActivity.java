package info.smemo.nowordschat.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.bean.FindBean;

public class UserActivity extends BaseCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<FindBean> findBeanArrayList;
    private NBaseBindingAdapter findAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("无语开发者");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);

        initView();

        initFindAdapter();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.finds_list);
    }

    private void initFindAdapter() {

        findBeanArrayList = new ArrayList<>();
        findAdapter = new NBaseBindingAdapter<>(findBeanArrayList, BR.bean, R.layout.item_user_finds,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(findAdapter);

        FindBean findBean = new FindBean();
        findBean.imgUri = "res:///" + R.drawable.user_logo;

        findBeanArrayList.add(findBean);
        findBeanArrayList.add(findBean);
        findBeanArrayList.add(findBean);

        findAdapter.notifyDataSetChanged();

    }

    /**
     * 发送朋友圈消息
     */
    public void postClick(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_group:
                break;
            case R.id.nav_set:
                break;
            case R.id.nav_friend:
                break;
            case R.id.nav_complain:
                break;
            case R.id.nav_block:
                break;
            case R.id.nav_delete:
                break;
            case R.id.nav_desktop:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
