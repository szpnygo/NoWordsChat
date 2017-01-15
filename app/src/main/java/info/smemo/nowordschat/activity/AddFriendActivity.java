package info.smemo.nowordschat.activity;

import android.support.v7.widget.SearchView;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.action.UserInfoAction;
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
        binding.searchView.setIconified(false);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void search(String query) {

    }
}
