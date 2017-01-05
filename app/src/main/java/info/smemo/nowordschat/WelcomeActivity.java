package info.smemo.nowordschat;

import android.content.Intent;
import android.os.Bundle;

import info.smemo.nowordschat.action.UserAction;
import info.smemo.nowordschat.activity.LoginActivity;
import info.smemo.nowordschat.activity.MainActivity;
import info.smemo.nowordschat.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if(UserAction.isUserLogin()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
