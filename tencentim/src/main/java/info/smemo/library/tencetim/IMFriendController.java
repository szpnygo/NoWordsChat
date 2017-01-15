package info.smemo.library.tencetim;

import com.tencent.TIMFriendshipManager;
import com.tencent.TIMFriendshipProxy;
import com.tencent.TIMUserProfile;
import com.tencent.TIMUserSearchSucc;
import com.tencent.TIMValueCallBack;

import java.util.List;

public class IMFriendController {

    public static void searchUser(String nickname, int pageIndex, int pageSize, TIMValueCallBack<TIMUserSearchSucc> callBack) {
        TIMFriendshipManager.getInstance().searchUser(nickname, pageIndex, pageSize, callBack);
    }

    public static List<TIMUserProfile> getLocalFriendsById(List<String> identifiers) {
        return TIMFriendshipProxy.getInstance().getFriendsById(identifiers);
    }

    public static List<TIMUserProfile> getLocalFriends() {
        return TIMFriendshipProxy.getInstance().getFriends();
    }
}
