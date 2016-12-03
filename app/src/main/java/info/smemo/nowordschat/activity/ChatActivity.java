package info.smemo.nowordschat.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;

public class ChatActivity extends BaseCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("对方正在输入");
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);
    }

    public void messageMenuClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.menu_emoji:
                break;
            case R.id.menu_at:
                break;
            case R.id.menu_shake:
                break;
            case R.id.menu_location:
                break;
            case R.id.menu_voice:
                break;
            case R.id.menu_camera:
                break;
            case R.id.menu_photo:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
