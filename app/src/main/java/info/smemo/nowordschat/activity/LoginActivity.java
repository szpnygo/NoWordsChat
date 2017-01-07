package info.smemo.nowordschat.activity;

import android.content.Intent;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.LoginContract;
import info.smemo.nowordschat.databinding.ActivityLoginBinding;
import info.smemo.nowordschat.presenter.LoginPresenter;


public class LoginActivity extends BaseCompatActivity implements LoginContract.View {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_login);
        LoginPresenter presenter = new LoginPresenter(this);
        binding.setPresenter(presenter);

    }

    @Override
    public void showMessage(String message) {
        showSnackbar(message, binding.rootView);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void register() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @Override
    public void scrollToBottom() {
        binding.scrollView.scrollTo(0, binding.scrollView.getBottom());
    }

    @Override
    public void setEmailErrorMessage(String message) {
        binding.emailLayout.setError(message);
    }

    @Override
    public void setPasswordErrorMessage(String message) {
        binding.passwordLayout.setError(message);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }

}

