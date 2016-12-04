package info.smemo.nbaseaction.base;

import android.app.Application;
import android.content.Context;

public class NBaseApplication extends Application {

    public static NBaseApplication sBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        if (null == sBaseApplication) {
            sBaseApplication = this;
        }
    }

    public static NBaseApplication getInstance() {
        return sBaseApplication;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }
}
