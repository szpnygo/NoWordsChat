package info.smemo.nbaseaction.base;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import info.smemo.nbaseaction.BuildConfig;
import info.smemo.nbaseaction.app.AppConstant;

public class NBaseApplication extends Application {

    public static NBaseApplication sBaseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        if (null == sBaseApplication) {
            sBaseApplication = this;
        }
        Logger.init(AppConstant.TAG)
                .methodCount(2)
                .methodOffset(1)
                .logLevel(BuildConfig.MY_DEBUG ? LogLevel.FULL : LogLevel.NONE);
    }

    public static NBaseApplication getInstance() {
        return sBaseApplication;
    }

    public static Context getContext() {
        return getInstance().getApplicationContext();
    }
}
