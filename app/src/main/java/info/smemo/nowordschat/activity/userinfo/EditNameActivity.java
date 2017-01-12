package info.smemo.nowordschat.activity.userinfo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.action.UserInfoAction;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.databinding.ActivityEditNameBinding;

public class EditNameActivity extends BaseCompatActivity {

    public EditBean mEditBean = new EditBean(UserInfoAction.getUserInfo().nickname);

    private ActivityEditNameBinding binding;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_edit_name);
        binding.setEditBean(mEditBean);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("更改名字");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);

        binding.textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.textInput || actionId == EditorInfo.IME_ACTION_DONE) {
                    done();
                    return true;
                }
                return false;
            }
        });
    }

    private void done() {
        if (StringUtil.isEmpty(mEditBean.text)) {
            setMessageError("昵称不能为空");
            return;
        }
        if (mEditBean.text.length() < 2 || mEditBean.text.length() > 20) {
            setMessageError("昵称请在2-20字节之间");
            return;
        }
        if (mEditBean.text.equals(UserInfoAction.getUserInfo().nickname)) {
            finish();
            return;
        }
        updateNickname();
    }

    private void updateNickname() {
        UserInfoAction.getUserInfo().setNickname(mEditBean.text);
        finish();
    }

    public void setMessageError(String messageError) {
        binding.textLayout.setError(messageError);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_done:
                done();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public class EditBean extends BaseObservable {

        public String text;

        public EditBean(String text) {
            this.text = text;
        }

        @Bindable
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
            notifyPropertyChanged(BR.text);
        }
    }
}
