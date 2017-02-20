package info.smemo.nbaseaction.util;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class SystemUtil {


    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static void copy(Context context, String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("NBaseAndroid", content);
        cmb.setPrimaryClip(clipData);
    }

    public static int getPhoneWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getWidth();
    }


    public static int getPhoneHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getHeight();
    }

    public static void toastMessage(Context context, String msg) {
        if (context == null || StringUtil.isEmpty(msg))
            return;
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void toastMessage(Context context, int res) {
        String msg = context.getResources().getString(res);
        toastMessage(context, msg);
    }


    public static void sendMessage(Handler handler, int msg) {
        if (handler == null)
            return;
        Message message = new Message();
        message.what = msg;
        handler.sendMessage(message);
    }

    public static void sendMessage(Handler handler, int msg, String data) {
        if (handler == null)
            return;
        Bundle bundle = new Bundle();
        bundle.putString("data", data);
        Message message = new Message();
        message.what = msg;
        message.setData(bundle);
        handler.sendMessage(message);
    }


    public static void sendMessage(Handler handler, int msg, Bundle bundle) {
        if (handler == null || bundle == null)
            return;
        Message message = new Message();
        message.what = msg;
        message.setData(bundle);
        handler.sendMessage(message);
    }

    public static boolean existSDCard() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }


    public static String getVersionName(Context context) {
        String versionname = "";// 版本号
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionname = pi.versionName;// 获取在AndroidManifest.xml中配置的版本号
        } catch (PackageManager.NameNotFoundException e) {
            versionname = "1.0";
        }

        return versionname;

    }

    public static int getVersionCode(Context context) {
        int versionCode = 0;// 版本号
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;// 获取在AndroidManifest.xml中配置的版本号
        } catch (PackageManager.NameNotFoundException e) {
            versionCode = 1;
        }

        return versionCode;

    }

    public static String getAndroidManifestMetaData(Context context,
                                                    String metaName) {
        ApplicationInfo appInfo;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(
                    context.getPackageName(), PackageManager.GET_META_DATA);
            return appInfo.metaData.getString(metaName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (NullPointerException e) {
            return "";
        }
    }

    /**
     * 发送广播
     *
     * @param action 广播名
     * @param extras 数据
     */
    public static void sendBroadcaset(Context context, String action,
                                      Bundle extras) {
        Intent intent = new Intent(action);
        if (null != extras)
            intent.putExtras(extras);
        context.sendBroadcast(intent);
    }

    public static void sendMessage(Context context, String phone, String data) {
        Uri uri = Uri.parse("smsto:" + phone);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        sendIntent.putExtra("sms_body", data);
        context.startActivity(sendIntent);
    }

    public static void closeKeyMap(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean GetNetWorkStatus(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
}
