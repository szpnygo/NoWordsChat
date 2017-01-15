package info.smemo.nowordschat.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nbaseaction.base.NBaseFragment;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.activity.UserActivity;
import info.smemo.nowordschat.appaction.bean.BookBean;
import info.smemo.nowordschat.contract.BookContract;
import info.smemo.nowordschat.databinding.FragmentBookBinding;
import info.smemo.nowordschat.presenter.BookPresenter;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookFragment extends NBaseFragment implements BookContract.View {

    private NBaseBindingAdapter bookAdapter;
    private BookPresenter mPresenter;
    private FragmentBookBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book, container, false);
        binding.setPresenter(mPresenter);

        bookAdapter = new NBaseBindingAdapter<>(mPresenter.getData(), BR.bean, R.layout.item_book);
        binding.bookList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.bookList.setAdapter(bookAdapter);

        initAdapter();

        return binding.getRoot();
    }

    @Override
    public void showSnackbarMessage(String message) {
        super.showSnackbarMessage(message);
        showSnackbar(message, binding.rootView);
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
    public void notifyDataSetChanged() {
        bookAdapter.notifyDataSetChanged();
        binding.refresh.setRefreshing(false);
    }

    @Override
    public void startUser(BookBean bean) {
        startActivity(new Intent(getActivity(), UserActivity.class));
    }

    @Override
    public void setPresenter(BookContract.Presenter presenter) {
        this.mPresenter = (BookPresenter) checkNotNull(presenter);
    }
}
