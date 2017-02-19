package info.smemo.nowordschat.app;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import info.smemo.nbaseaction.BuildConfig;
import info.smemo.nbaseaction.app.AppConstant;
import info.smemo.nbaseaction.base.NBaseApplication;
import info.smemo.nowordschat.appaction.controller.ImController;
import info.smemo.nowordschat.leancloud.LeanInitController;


public class AppApplication extends NBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(AppConstant.TAG)
                .methodCount(2)
                .methodOffset(1)
                .logLevel(BuildConfig.MY_DEBUG ? LogLevel.FULL : LogLevel.NONE);
        Fresco.initialize(this);
        ImController.init(this);

        LeanInitController.initialize(getApplicationContext());
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void appPause(Context context) {
        LeanInitController.analyticsOnPause(context);
    }

    public static void appResume(Context context) {
        LeanInitController.analyticsOnPesume(context);
    }
}
