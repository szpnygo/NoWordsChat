package info.smemo.nowordschat.activity;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.RegisterContract;
import info.smemo.nowordschat.databinding.ActivityRegisterBinding;
import info.smemo.nowordschat.presenter.RegisterPresenter;


public class RegisterActivity extends BaseCompatActivity implements RegisterContract.View {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_register);
        RegisterPresenter mPresenter = new RegisterPresenter(this);
        binding.setPresenter(mPresenter);
    }

    @Override
    public void registerSuccess() {
        toast("注册成功请登录");
        finish();
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
    public void setNicknameErrorMessage(String message) {
        binding.nicknameLayout.setError(message);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {

    }
}

