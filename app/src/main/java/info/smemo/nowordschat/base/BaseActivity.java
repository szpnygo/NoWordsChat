package info.smemo.nowordschat.base;

import info.smemo.nbaseaction.base.NBaseCompatActivity;
import info.smemo.nowordschat.leancloud.LeanInitController;

public class BaseActivity extends NBaseCompatActivity{

    @Override
    protected void onResume() {
        super.onResume();
        LeanInitController.analyticsOnPesume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LeanInitController.analyticsOnPause(this);
    }
}
