package info.smemo.nowordschat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.LoginContract;
import info.smemo.nowordschat.presenter.LoginPresenter;


public class LoginActivity extends BaseCompatActivity implements View.OnFocusChangeListener, LoginContract.View {

    private EditText mEmailView;
    private EditText mPasswordView;

    private TextInputLayout mEmailLayout;
    private TextInputLayout mPasswordLayout;

    private ScrollView mScrollView;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                checkData();
            }
        });

        TextView registerButton = (TextView) findViewById(R.id.register);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        mPresenter = new LoginPresenter(this);
    }

    private void initView() {
        mEmailView = (EditText) findViewById(R.id.email);
        mEmailLayout = (TextInputLayout) findViewById(R.id.emailLayout);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordLayout = (TextInputLayout) findViewById(R.id.passwordLayout);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);

        mEmailView.setOnFocusChangeListener(this);
        mEmailView.addTextChangedListener(textWatcher);
        mPasswordView.setOnFocusChangeListener(this);
        mPasswordView.addTextChangedListener(textWatcher);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    checkData();
                    return true;
                }
                return false;
            }
        });

        setPasswordErrorMessage(null);
        setEmailErrorMessage(null);
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            setPasswordErrorMessage(null);
            setEmailErrorMessage(null);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        mScrollView.scrollTo(0, mScrollView.getBottom());
    }

    @Override
    public void checkData() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        if (StringUtil.isEmpty(email)) {
            setEmailErrorMessage("邮箱不能为空");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            setPasswordErrorMessage("密码不能为空");
            return;
        }
        if (!StringUtil.checkEmail(email)) {
            setEmailErrorMessage("请输入正确的邮箱格式");
            return;
        }
        if (password.length() < 6) {
            setPasswordErrorMessage("密码不能小于6位");
            return;
        }
        if (password.length() > 15) {
            setPasswordErrorMessage("密码不能大于15位");
            return;
        }
        mPresenter.login(email, password);
    }

    @Override
    public void setEmailErrorMessage(String message) {
        mEmailLayout.setErrorEnabled(true);
        mEmailLayout.setError(message);
    }

    @Override
    public void setPasswordErrorMessage(String message) {
        mPasswordLayout.setErrorEnabled(true);
        mPasswordLayout.setError(message);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {

    }
}

