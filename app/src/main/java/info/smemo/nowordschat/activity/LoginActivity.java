package info.smemo.nowordschat.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;


public class LoginActivity extends BaseCompatActivity implements View.OnFocusChangeListener {

    private EditText mEmailView;
    private EditText mPasswordView;

    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);

        mEmailView.setOnFocusChangeListener(this);
        mPasswordView.setOnFocusChangeListener(this);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                attemptLogin();
            }
        });

    }


    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        mScrollView.scrollTo(0, mScrollView.getBottom());
    }
}

