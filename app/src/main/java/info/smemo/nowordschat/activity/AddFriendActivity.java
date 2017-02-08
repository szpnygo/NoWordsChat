package info.smemo.nowordschat.activity;

import android.content.Intent;
import android.support.v7.widget.SearchView;

import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.action.UserInfoAction;
import info.smemo.nowordschat.appaction.bean.FriendBean;
import info.smemo.nowordschat.appaction.controller.FriendController;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.databinding.ActivityAddFriendBinding;

public class AddFriendActivity extends BaseCompatActivity {

    private ActivityAddFriendBinding binding;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_add_friend);
        binding.toolbar.setTitle("添加好友");
        setSupportActionBar(binding.toolbar);
        setToolbarFinish(binding.toolbar);
        binding.setUserBean(UserInfoAction.getUserInfo());
        binding.searchView.requestFocus();
        binding.searchView.requestFocusFromTouch();
        binding.searchView.onActionViewExpanded();
        binding.searchView.setSubmitButtonEnabled(true);
        binding.searchView.setIconifiedByDefault(false);
        binding.searchView.setIconified(false);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (StringUtil.isEmpty(query)) {
                    showSnackbarMessage("请输入要查询的无语号");
                } else {
                    search(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void showSnackbarMessage(String message) {
        super.showSnackbarMessage(message);
        showSnackbar(message, binding.rootView);
    }

    private void search(String query) {
        showProgressDialog("查询中");
        FriendController.getUserInfo(query, new FriendController.GetUserListener() {
            @Override
            public void success(FriendBean friendBean) {
                dismissProgressDialog();
                if (friendBean == null) {
                    showSnackbarMessage("没有发现该账号，请核对后重新输入");
                } else {
                    Intent intent = new Intent(AddFriendActivity.this, UserActivity.class);
                    intent.putExtra("user", friendBean);
                    startActivity(intent);
                }
            }

            @Override
            public void error(int code, String message) {
                dismissProgressDialog();
                showSnackbarMessage(message);
            }
        });
    }

}
