package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.action.UserAction;
import info.smemo.nowordschat.base.BaseActionInterface;
import info.smemo.nowordschat.contract.RegisterContract;

import static com.google.common.base.Preconditions.checkNotNull;

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
    public void register(@NonNull String nickname, @NonNull String email, @NonNull String password) {
        if (!checkData(nickname, email, password)) return;
        mView.showProgressDialog("注册中......");
        UserAction.createUser(email, nickname, password, new BaseActionInterface.BaseComplete() {
            @Override
            public void success() {
                mView.dismissProgressDialog();
                mView.registerSuccess();
            }

            @Override
            public void error(int code, String message) {
                mView.dismissProgressDialog();
                if (code == 125 || code == 200 || code == 202 || code == 203 || code == 204 || code == 217) {
                    mView.setEmailErrorMessage(message);
                } else if (code == 201 || code == 218) {
                    mView.setPasswordErrorMessage(message);
                } else if (code == 137) {
                    mView.setNicknameErrorMessage("该昵称已被占用");
                } else {
                    mView.showMessage(message);
                }

            }
        });

    }

    @Override
    public boolean checkData(String nickname, String email, String password) {
        if (StringUtil.isEmpty(nickname)) {
            mView.setNicknameErrorMessage("昵称不能为空");
            return false;
        }
        if (StringUtil.isEmpty(email)) {
            mView.setEmailErrorMessage("邮箱不能为空");
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
    public void onFocusChange(View view, boolean hasFocus) {
        mView.scrollToBottom();
    }

}
