package info.smemo.nbaseaction.util;

import android.app.ActivityManager;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/**
 * get all manager from this class
 */
public class PhoneManager {

    /**
     * get ActivityManager
     */
    public static ActivityManager getActivityManager(Context context) {
        if (context == null) return null;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        return activityManager;
    }

    /**
     * get WindowManager
     */
    public static WindowManager getWindowManger(Context context) {
        if (context == null) return null;
        WindowManager windowManager = null;
        windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return windowManager;
    }

    /**
     * get TelephonyManager
     */
    public static TelephonyManager getTelephonyManager(Context context) {
        if (context == null) return null;
        TelephonyManager telephonyManager = null;
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager;
    }

    /**
     * get ConnectivityManager
     */
    public static ConnectivityManager getConnectivityManager(Context context) {
        if (context == null) return null;
        ConnectivityManager connectivityManager = null;
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager;
    }

    /**
     *
     */
    public static NetworkInfo getNetworkInfo(Context context) {
        if (context == null) return null;
        NetworkInfo ni = getConnectivityManager(context).getActiveNetworkInfo();
        return ni;
    }

    /**
     * wifi's state can change
     * so everytime calling this method we must get a new WifiManager
     *
     * @param context
     * @return
     */
    public static WifiManager getWifiManager(Context context) {
        if (context == null) return null;
        WifiManager wifiManager = null;
        wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wifiManager;
    }

    /**
     * get WifiInfo
     *
     * @param context
     * @return
     */
    public static WifiInfo getWifiInfo(Context context) {
        if (context == null) return null;
        return getWifiManager(context).getConnectionInfo();
    }

    public static LocationManager getLocationManager(Context context) {
        if (context == null) return null;
        return (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
    }

    public static NotificationManager getNotificationManager(Context context) {
        if (context == null) return null;
        return (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static DownloadManager getDownloadManager(Context context) {
        if (context == null) return null;
        DownloadManager downloadManager = null;
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        return downloadManager;
    }


}
