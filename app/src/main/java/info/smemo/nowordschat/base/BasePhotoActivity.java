package info.smemo.nowordschat.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import info.smemo.nbaseaction.activity.NPhotoActivity;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.app.AppApplication;

public class BasePhotoActivity extends NPhotoActivity{

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

    @Override
    protected void onResume() {
        super.onResume();
        AppApplication.appResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppApplication.appPause(this);
    }

}
