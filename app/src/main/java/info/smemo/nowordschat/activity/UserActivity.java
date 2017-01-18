package info.smemo.nowordschat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.action.FriendAction;
import info.smemo.nowordschat.appaction.bean.UserBean;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.UserContract;
import info.smemo.nowordschat.databinding.ActivityUserBinding;
import info.smemo.nowordschat.presenter.UserPresenter;

public class UserActivity extends BaseCompatActivity implements UserContract.View {


    private NBaseBindingAdapter findAdapter;

    private ActivityUserBinding binding;
    private UserPresenter presenter;

    private UserBean mUserBean;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        mUserBean = (UserBean) getIntent().getSerializableExtra("user");
        if (null == mUserBean) {
            finish();
            return;
        }
        binding = createContentView(R.layout.activity_user);
        binding.setUserInfo(mUserBean);
        binding.setIsFriend(FriendAction.isFriend(mUserBean.identifier));
        setSupportActionBar(binding.toolbar);
        setToolbarFinish(binding.toolbar);

        presenter = new UserPresenter(this);
        binding.setPresenter(presenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFindAdapter();
    }

    @Override
    public void showSnackbarMessage(String message) {
        super.showSnackbarMessage(message);
        showSnackbar(message, binding.rootView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void initFindAdapter() {
        findAdapter = new NBaseBindingAdapter<>(presenter.getData(), BR.bean, R.layout.item_user_finds,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        binding.findsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.findsList.setHasFixedSize(true);
        binding.findsList.setAdapter(findAdapter);

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

    @Override
    public void setPresenter(UserContract.Presenter presenter) {

    }

    @Override
    public void startChat() {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("user", mUserBean);
        startActivity(intent);
    }

    @Override
    public void notifyDataSetChanged() {
        findAdapter.notifyDataSetChanged();
    }

    @Override
    public void addFriendSuccess() {
        binding.setIsFriend(true);
        binding.notifyPropertyChanged(BR.isFriend);
        binding.menuPost.setImageResource(R.drawable.ic_chat_black_24dp);
    }

    @Override
    public UserBean getUser() {
        return mUserBean;
    }
}
