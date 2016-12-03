package info.smemo.nowordschat.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import info.smemo.nbaseaction.base.NBaseCompatActivity;
import info.smemo.nowordschat.R;

public class BaseCompatActivity extends NBaseCompatActivity {

    /**
     * 设置Toolbar左侧为返回按钮并且返回
     */
    protected void setToolbarFinish(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
