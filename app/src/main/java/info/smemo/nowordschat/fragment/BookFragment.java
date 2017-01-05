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
import info.smemo.nowordschat.contract.BookContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookFragment extends NBaseFragment implements BookContract.View {

    private NBaseBindingAdapter bookAdapter;

    private ArrayList<BookBean> bookBeanArrayList;

    private BookContract.Presenter mPresenter;

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

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void initAdapter() {
        bookAdapter.setListener(new NBaseBindingAdapter.OnAdapterClickListener<BookBean>() {

            @Override
            public void onClick(View view, int position, BookBean object) {
                startUser(object);
            }
        });
    }

    @Override
    public void showBooks(ArrayList<BookBean> list) {
        bookBeanArrayList.clear();
        for (BookBean bookBean : list) {
            bookBeanArrayList.add(bookBean);
        }
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void startUser(BookBean bean) {
        startActivity(new Intent(getActivity(), UserActivity.class));
    }

    @Override
    public void setPresenter(BookContract.Presenter presenter) {
        this.mPresenter = checkNotNull(presenter);
    }
}
