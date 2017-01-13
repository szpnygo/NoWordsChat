package info.smemo.library.tencetim;

import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserSearchSucc;
import com.tencent.TIMValueCallBack;

public class IMFriendController {

    public static void searchUser(String nickname, int pageIndex, int pageSize, TIMValueCallBack<TIMUserSearchSucc> callBack) {
        TIMFriendshipManager.getInstance().searchUser(nickname, pageIndex, pageSize, callBack);
    }
}
