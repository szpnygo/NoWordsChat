package info.smemo.nowordschat.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.adapter.ChatAdapter;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.ChatContract;
import info.smemo.nowordschat.databinding.ActivityChatBinding;
import info.smemo.nowordschat.presenter.ChatPresenter;

public class ChatActivity extends BaseCompatActivity implements ChatContract.View {

    private ChatPresenter presenter;
    private ActivityChatBinding binding;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_chat);
        String type = getIntent().getStringExtra("type");
        String peer = getIntent().getStringExtra("peer");
        if (StringUtil.isEmpty(type) || StringUtil.isEmpty(peer)) {
            finish();
        }
        setTitle("无语");
        setSupportActionBar(binding.toolbar);
        setToolbarFinish(binding.toolbar);

        presenter = new ChatPresenter(this);
        presenter.init(type, peer);
        binding.setPresenter(presenter);

        chatAdapter = new ChatAdapter(this, presenter.getData());
        binding.chatList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.chatList.setAdapter(chatAdapter);

        presenter.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showSnackbarMessage(String message) {
        super.showSnackbarMessage(message);
        showSnackbar(message, binding.rootView);
    }

    @Override
    public void notifyDataSetChanged() {
        chatAdapter.notifyDataSetChanged();
        stopLoading();
    }

    @Override
    public void startLoading() {
        binding.refresh.setRefreshing(true);
    }

    @Override
    public void stopLoading() {
        binding.refresh.setRefreshing(false);
    }

    @Override
    public void moveToBottom() {
        binding.chatList.scrollToPosition(chatAdapter.getItemCount() - 1);
    }

    @Override
    public void setTitle(String title) {
        binding.toolbar.setTitle(title);
    }

    public void messageMenuClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.menu_emoji:
                break;
            case R.id.menu_at:
                presenter.sendYueMessage();
                break;
            case R.id.menu_shake:
                break;
            case R.id.menu_location:
                break;
            case R.id.menu_voice:
                break;
            case R.id.menu_camera:
                break;
            case R.id.menu_photo:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_info) {
            startActivity(new Intent(this, UserActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(ChatContract.Presenter presenter) {

    }

}
