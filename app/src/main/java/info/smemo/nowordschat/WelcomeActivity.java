package info.smemo.nowordschat;

import android.content.Intent;
import android.os.Bundle;

import info.smemo.nowordschat.activity.MainActivity;
import info.smemo.nowordschat.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
