package info.smemo.nowordschat.app;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import info.smemo.nbaseaction.BuildConfig;
import info.smemo.nbaseaction.app.AppConstant;
import info.smemo.nbaseaction.base.NBaseApplication;


public class AppApplication extends NBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init(AppConstant.TAG)
                .methodCount(2)
                .methodOffset(1)
                .logLevel(BuildConfig.MY_DEBUG ? LogLevel.FULL : LogLevel.NONE);
        Fresco.initialize(this);
    }
}
