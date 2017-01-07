package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;
import android.view.inputmethod.EditorInfo;

import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.contract.LoginContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mView = checkNotNull(view, "LoginContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void register() {
        mView.register();
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        mView.setEmailErrorMessage(null);
        mView.setPasswordErrorMessage(null);
        mView.scrollToBottom();
    }

    @Override
    public boolean login(int id, @NonNull String email, @NonNull String password) {
        if (id == R.id.loginEt || id == EditorInfo.IME_ACTION_DONE) {
            login(email, password);
            return true;
        }
        return false;
    }

    @Override
    public void login(@NonNull String email, @NonNull String password) {
        if (!checkData(email, password)) return;
    }

    @Override
    public boolean checkData(String email, String password) {
        if (StringUtil.isEmpty(email)) {
            mView.setEmailErrorMessage("邮箱不能为空");
            return false;
        }
        if (StringUtil.isEmpty(password)) {
            mView.setPasswordErrorMessage("密码不能为空");
            return false;
        }
        if (!StringUtil.checkEmail(email)) {
            mView.setEmailErrorMessage("请输入正确的邮箱格式");
            return false;
        }
        if (password.length() < 6) {
            mView.setPasswordErrorMessage("密码不能小于6位");
            return false;
        }
        if (password.length() > 15) {
            mView.setPasswordErrorMessage("密码不能大于15位");
            return false;
        }
        return true;
    }

    @Override
    public void start() {

    }
}
