package info.smemo.nowordschat.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.adapter.FindAdapter;
import info.smemo.nowordschat.base.BaseCompatActivity;

public class FindActivity extends BaseCompatActivity {

    private FindAdapter findAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("朋友圈");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);

        initAdapter();

    }

    private void initAdapter() {
        findAdapter = new FindAdapter();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.find_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(findAdapter);
    }

    public void postClick(View view) {

    }
}
