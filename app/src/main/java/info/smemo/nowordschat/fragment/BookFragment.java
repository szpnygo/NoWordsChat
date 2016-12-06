package info.smemo.nowordschat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nbaseaction.base.NBaseFragment;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.activity.UserActivity;
import info.smemo.nowordschat.bean.BookBean;

public class BookFragment extends NBaseFragment {

    private NBaseBindingAdapter bookAdapter;

    private ArrayList<BookBean> bookBeanArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookBeanArrayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        bookAdapter = new NBaseBindingAdapter<>(bookBeanArrayList, BR.bean, R.layout.item_book);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.book_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(bookAdapter);

        initAdapter();

        return view;
    }

    private void initAdapter() {
        bookAdapter.setListener(new NBaseBindingAdapter.OnAdapterClickListener<BookBean>() {

            @Override
            public void onClick(View view, int position, BookBean object) {
                startActivity(new Intent(getActivity(), UserActivity.class));
            }
        });

        BookBean bookBean1 = new BookBean();
        bookBean1.title = "star";
        bookBean1.username = "无语开发者";
        bookBean1.userLogo = "res:///" + R.drawable.user_logo;
        bookBean1.showLine = false;

        bookBeanArrayList.add(bookBean1);
        BookBean bookBean2 = new BookBean();
        bookBean2.title = "";
        bookBean2.username = "陪你去看海";
        bookBean2.userLogo = "res:///" + R.drawable.user_logo;
        bookBean2.showLine = false;

        bookBeanArrayList.add(bookBean2);
        BookBean bookBean3 = new BookBean();
        bookBean3.title = "";
        bookBean3.username = "一个人的狂欢";
        bookBean3.userLogo = "res:///" + R.drawable.user_logo;
        bookBean3.showLine = true;

        bookBeanArrayList.add(bookBean3);
        BookBean bookBean4 = new BookBean();
        bookBean4.title = "A";
        bookBean4.username = "爱情公寓";
        bookBean4.userLogo = "res:///" + R.drawable.user_logo;
        bookBean4.showLine = false;

        bookBeanArrayList.add(bookBean4);
        BookBean bookBean5 = new BookBean();
        bookBean5.title = "";
        bookBean5.username = "阿凡达";
        bookBean5.userLogo = "res:///" + R.drawable.user_logo;
        bookBean5.showLine = false;

        bookBeanArrayList.add(bookBean5);
        BookBean bookBean6 = new BookBean();
        bookBean6.title = "";
        bookBean6.username = "吖吖吖吖";
        bookBean6.userLogo = "res:///" + R.drawable.user_logo;
        bookBean6.showLine = true;

        bookBeanArrayList.add(bookBean6);
        BookBean bookBean7 = new BookBean();
        bookBean7.title = "B";
        bookBean7.username = "宝宝";
        bookBean7.userLogo = "res:///" + R.drawable.user_logo;
        bookBean7.showLine = false;

        bookBeanArrayList.add(bookBean7);
        BookBean bookBean8 = new BookBean();
        bookBean8.title = "";
        bookBean8.username = "不愿放弃";
        bookBean8.userLogo = "res:///" + R.drawable.user_logo;
        bookBean8.showLine = true;

        bookBeanArrayList.add(bookBean8);
        BookBean bookBean9 = new BookBean();
        bookBean9.title = "C";
        bookBean9.username = "从未出现";
        bookBean9.userLogo = "res:///" + R.drawable.user_logo;
        bookBean9.showLine = false;

        bookBeanArrayList.add(bookBean9);
        BookBean bookBean10 = new BookBean();
        bookBean10.title = "";
        bookBean10.username = "丑女无敌";
        bookBean10.userLogo = "res:///" + R.drawable.user_logo;
        bookBean10.showLine = false;

        bookBeanArrayList.add(bookBean10);
        BookBean bookBean11 = new BookBean();
        bookBean11.title = "";
        bookBean11.username = "草上飞";
        bookBean11.userLogo = "res:///" + R.drawable.user_logo;
        bookBean11.showLine = true;
        bookBeanArrayList.add(bookBean11);

        bookAdapter.notifyDataSetChanged();
    }
}
