package info.smemo.nowordschat;

import android.content.Intent;
import android.os.Bundle;

import info.smemo.nowordschat.action.UserAction;
import info.smemo.nowordschat.activity.LoginActivity;
import info.smemo.nowordschat.activity.MainActivity;
import info.smemo.nowordschat.appaction.ActionInterface;
import info.smemo.nowordschat.appaction.controller.ImController;
import info.smemo.nowordschat.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ImController.init(this);
        if (UserAction.needLogin()) {
            UserAction.autoLogin(new ActionInterface.BaseComplete() {
                @Override
                public void success() {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }

                @Override
                public void error(int code, String message) {
                    startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
                    finish();
                }
            });
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}
