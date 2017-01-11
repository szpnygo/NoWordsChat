package info.smemo.library.tencetim;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.NonNull;

import com.tencent.TIMCallBack;
import com.tencent.TIMFriendshipManager;

public class IMUserInfoAction {

    private static final String PREFERENCES = "TencentImSdK";

    /**
     * 模式
     */
    public static int __sdkLevel = Build.VERSION.SDK_INT;
    public static final int mode = ((__sdkLevel > 10) ? 4 : 0);

    public static void setNickname(@NonNull String nickname, @NonNull TIMCallBack callBack) {
        TIMFriendshipManager.getInstance().setNickName(nickname, callBack);
    }

    public static void setNickName(@NonNull String nickname) {

    }

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
