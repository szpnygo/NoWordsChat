package info.smemo.nowordschat.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;

public class UserActivity extends BaseCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("无语开发");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);

    }

    public void postClick(View view) {

    }
}
