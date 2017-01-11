package info.smemo.library.tencetim;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;


public class IMSP {

    private static final String PREFERENCES = "TencentImSdK";

    /**
     * 模式
     */
    public static int __sdkLevel = Build.VERSION.SDK_INT;
    public static final int mode = ((__sdkLevel > 10) ? 4 : 0);

    public static String getSpString(Context context, String key, String defaut) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(PREFERENCES, mode);
        return mySharedPreferences.getString(key, defaut);
    }

    public static void saveSpString(Context context, String key, String value) {
        SharedPreferences mySharedPreferences = context.getSharedPreferences(PREFERENCES, mode);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
