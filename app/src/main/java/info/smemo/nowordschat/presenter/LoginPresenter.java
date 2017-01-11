package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;
import android.view.inputmethod.EditorInfo;

import info.smemo.nbaseaction.util.LogHelper;
import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.action.UserAction;
import info.smemo.nowordschat.appaction.UserController;
import info.smemo.nowordschat.contract.LoginContract;

import static com.google.common.base.Preconditions.checkNotNull;
import static info.smemo.nowordschat.app.AppConstant.LOG_TAG;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mView = checkNotNull(view, "LoginContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!UserAction.needLogin()) {
            mView.loginSuccess();
        }
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
        mView.showProgressDialog("登录中......");
        UserAction.loginUser(email, password, new UserController.LoginSuccessListener() {
            @Override
            public void success() {
                mView.dismissProgressDialog();
                mView.loginSuccess();
            }

            @Override
            public void error(int code, String message) {
                LogHelper.e(LOG_TAG, message);
                mView.dismissProgressDialog();
                mView.showMessage(message);
            }
        });
    }

    @Override
    public boolean checkData(String account, String password) {
        if (StringUtil.isEmpty(account)) {
            mView.setEmailErrorMessage("账号不能为空");
            return false;
        }
        if (StringUtil.isEmpty(password)) {
            mView.setPasswordErrorMessage("密码不能为空");
            return false;
        }
        if (account.length() < 4 || account.length() > 24) {
            mView.setEmailErrorMessage("账号请在2-20个字符之间");
            return false;
        }
        if (password.length() < 8) {
            mView.setPasswordErrorMessage("密码不能小于8位");
            return false;
        }
        if (password.length() >= 30) {
            mView.setPasswordErrorMessage("密码不能大于30位");
            return false;
        }
        return true;
    }


}
