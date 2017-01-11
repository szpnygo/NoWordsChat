package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import info.smemo.nbaseaction.util.LogHelper;
import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.action.UserAction;
import info.smemo.nowordschat.appaction.controller.UserController;
import info.smemo.nowordschat.contract.RegisterContract;

import static com.google.common.base.Preconditions.checkNotNull;
import static info.smemo.nowordschat.app.AppConstant.LOG_TAG;

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mView;

    public RegisterPresenter(RegisterContract.View view) {
        mView = checkNotNull(view, "RegisterContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        mView.setEmailErrorMessage(null);
        mView.setPasswordErrorMessage(null);
        mView.setNicknameErrorMessage(null);
        mView.scrollToBottom();
    }

    @Override
    public boolean register(int id, @NonNull String nickname, @NonNull String email, @NonNull String password) {
        if (id == R.id.registerEt || id == EditorInfo.IME_ACTION_DONE) {
            register(nickname, email, password);
            return true;
        }
        return false;
    }

    @Override
    public void register(@NonNull String nickname, @NonNull String account, @NonNull String password) {
        if (!checkData(nickname, account, password)) return;
        mView.showProgressDialog("注册中......");
        UserAction.createUser(account, nickname, password, new UserController.RegisterSuccessListener() {
            @Override
            public void success() {
                mView.dismissProgressDialog();
                mView.registerSuccess();
            }

            @Override
            public void error(int code, String message) {
                LogHelper.e(LOG_TAG,message);
                mView.dismissProgressDialog();
                mView.showMessage(message);
            }
        });

    }

    @Override
    public boolean checkData(String nickname, String account, String password) {
        if (StringUtil.isEmpty(nickname)) {
            mView.setNicknameErrorMessage("昵称不能为空");
            return false;
        }
        if (StringUtil.isEmpty(account)) {
            mView.setEmailErrorMessage("账号不能为空");
            return false;
        }
        if (StringUtil.isEmpty(password)) {
            mView.setPasswordErrorMessage("密码不能为空");
            return false;
        }
        if (nickname.length() <= 2 || nickname.length() >= 20) {
            mView.setNicknameErrorMessage("昵称请在2-20个字符之间");
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

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        mView.scrollToBottom();
    }

}
