package info.smemo.nowordschat.base;

import info.smemo.nbaseaction.base.NBaseCompatActivity;
import info.smemo.nowordschat.app.AppApplication;

public class BaseActivity extends NBaseCompatActivity{

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
