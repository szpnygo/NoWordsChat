package info.smemo.nowordschat.leancloud;

import android.content.Context;
import android.content.Intent;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVOSCloud;

public class LeanInitController implements LeanCloudConfig {

    public static void initialize(Context context) {
        AVOSCloud.initialize(context, APP_ID, APP_KEY);
        AVAnalytics.enableCrashReport(context, true);
    }

    public static void trackAppOpened(Intent intent) {
        AVAnalytics.trackAppOpened(intent);
    }

    public static void analyticsOnPause(Context context) {
        AVAnalytics.onPause(context);
    }

    public static void analyticsOnPesume(Context context) {
        AVAnalytics.onResume(context);
    }
}
