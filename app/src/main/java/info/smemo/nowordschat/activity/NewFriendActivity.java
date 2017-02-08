package info.smemo.nowordschat.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.appaction.bean.BookBean;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.NewFriendContract;
import info.smemo.nowordschat.databinding.ActivityNewFriendBinding;
import info.smemo.nowordschat.presenter.NewFriendPresenter;

public class NewFriendActivity extends BaseCompatActivity implements NewFriendContract.View {

    private ActivityNewFriendBinding binding;
    private NewFriendPresenter mPresenter;
    private NBaseBindingAdapter bookAdapter;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_new_friend);
        setSupportActionBar(binding.toolbar);
        setToolbarFinish(binding.toolbar);

        mPresenter = new NewFriendPresenter(this);
        binding.setPresenter(mPresenter);

        bookAdapter = new NBaseBindingAdapter<>(mPresenter.getData(), BR.bean, R.layout.item_new_friend);
        binding.bookList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.bookList.setAdapter(bookAdapter);
        bookAdapter.setListener(new NBaseBindingAdapter.OnAdapterClickListener<BookBean>() {
            @Override
            public void onClick(View view, int position, BookBean object) {
                startUser(object);
            }
        });
        bookAdapter.addClickListner(R.id.accept, new NBaseBindingAdapter.OnAdapterClickListener<BookBean>() {
            @Override
            public void onClick(View view, int position, BookBean object) {
                mPresenter.addFriendResponse(object, true);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showSnackbarMessage(String message) {
        super.showSnackbarMessage(message);
        showSnackbar(message, binding.rootView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_friend, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                startActivity(new Intent(this, AddFriendActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void notifyDataSetChanged() {
        bookAdapter.notifyDataSetChanged();
        binding.refresh.setRefreshing(false);
    }

    @Override
    public void startUser(BookBean bean) {
        Intent intent = new Intent(this, UserActivity.class);
        intent.putExtra("user", bean);
        startActivity(intent);
    }

    @Override
    public void setPresenter(NewFriendContract.Presenter presenter) {

    }

}
