package info.smemo.library.tencetim;

import android.support.annotation.NonNull;

import com.tencent.TIMCallBack;
import com.tencent.TIMFriendshipManager;

public class IMUserInfoAction {

    public static void setNickname(@NonNull String nickname, @NonNull TIMCallBack callBack) {
        TIMFriendshipManager.getInstance().setNickName(nickname, callBack);
    }

}
