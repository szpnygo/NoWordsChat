package info.smemo.nowordschat.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;

public class FindActivity extends BaseCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("朋友圈");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);

    }

    public void postClick(View view) {

    }
}
