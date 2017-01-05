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

public class FindActivity extends BaseCompatActivity {

    private FindAdapter findAdapter;
    private ArrayList<FindBean> mFindBeanArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("朋友圈");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);

        initAdapter();
    }

    private void initAdapter() {
        FindBean findBean = new FindBean();
        findBean.imgUri.add("res:///" + R.drawable.user_logo);
        findBean.userLogo = "res:///" + R.drawable.user_logo;
        findBean.username = "朋友圈昵称0";
        findBean.favorites = 5;
        findBean.comments = 9;

        FindBean findBean1 = new FindBean();
        findBean1.imgUri.add("res:///" + R.drawable.user_logo);
        findBean1.imgUri.add("res:///" + R.drawable.user_logo);
        findBean1.userLogo = "res:///" + R.drawable.user_logo;
        findBean1.username = "朋友圈昵称1";
        findBean1.favorites = 5;
        findBean1.comments = 9;


        FindBean findBean2 = new FindBean();
        findBean2.imgUri.add("res:///" + R.drawable.user_logo);
        findBean2.userLogo = "res:///" + R.drawable.user_logo;
        findBean2.username = "朋友圈昵称2";
        findBean2.favorites = 5;
        findBean2.comments = 9;


        FindBean findBean3 = new FindBean();
        findBean3.imgUri.add("res:///" + R.drawable.user_logo);
        findBean3.imgUri.add("res:///" + R.drawable.user_logo);
        findBean3.imgUri.add("res:///" + R.drawable.user_logo);
        findBean3.imgUri.add("res:///" + R.drawable.user_logo);
        findBean3.userLogo = "res:///" + R.drawable.user_logo;
        findBean3.username = "朋友圈昵称3";
        findBean3.favorites = 5;
        findBean3.comments = 9;

        FindBean findBean4 = new FindBean();
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.imgUri.add("res:///" + R.drawable.user_logo);
        findBean4.userLogo = "res:///" + R.drawable.user_logo;
        findBean4.username = "朋友圈昵称4";
        findBean4.favorites = 5;
        findBean4.comments = 9;

        FindBean findBean5 = new FindBean();
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.imgUri.add("res:///" + R.drawable.user_logo);
        findBean5.userLogo = "res:///" + R.drawable.user_logo;
        findBean5.username = "朋友圈昵称5";
        findBean5.favorites = 5;
        findBean5.comments = 9;

        FindBean findBean6 = new FindBean();
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.imgUri.add("res:///" + R.drawable.user_logo);
        findBean6.userLogo = "res:///" + R.drawable.user_logo;
        findBean6.username = "朋友圈昵称6";
        findBean6.favorites = 5;
        findBean6.comments = 9;

        mFindBeanArrayList = new ArrayList<>();
        mFindBeanArrayList.add(findBean);
        mFindBeanArrayList.add(findBean1);
        mFindBeanArrayList.add(findBean2);
        mFindBeanArrayList.add(findBean3);
        mFindBeanArrayList.add(findBean4);
        mFindBeanArrayList.add(findBean5);
        mFindBeanArrayList.add(findBean6);

        findAdapter = new FindAdapter(this, mFindBeanArrayList);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.find_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(findAdapter);
    }

    public void postClick(View view) {

    }
}
