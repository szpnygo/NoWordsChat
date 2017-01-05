package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import info.smemo.nowordschat.contract.LoginContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mView = checkNotNull(view, "LoginContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void login(@NonNull String email, @NonNull String password) {

    }

    @Override
    public void register() {

    }

    @Override
    public void start() {

    }
}
