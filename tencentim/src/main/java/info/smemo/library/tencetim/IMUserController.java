package info.smemo.library.tencetim;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;

import tencent.tls.platform.TLSAccountHelper;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * 用户相关
 * Created by neo on 17/1/10.
 */
public class IMUserController implements IMConstant {

    private static IMUserController instance;

    private TLSAccountHelper mAccountHelper;
    private Context mContext;

    private static class Single {
        private static IMUserController instance = new IMUserController();
    }

    public static synchronized IMUserController getInstance() {
        if (null == instance) {
            instance = Single.instance;
        }
        return instance;
    }

    public void init(@NonNull Context context) {
        mAccountHelper = TLSAccountHelper.getInstance()
                .init(context, APP_ID, ACCOUNT_TYPE, LIBRARY_VER);
        mContext = context;
    }

    public void register(@NonNull final String email, @NonNull final String password, @NonNull final String nickname, @NonNull final IMInterface.RegisterListener listener) {
        int result = mAccountHelper.TLSStrAccReg(email, password, new TLSStrAccRegListener() {
            @Override
            public void OnStrAccRegSuccess(TLSUserInfo tlsUserInfo) {
                if (null != mContext)
                    IMSP.saveSpString(mContext, "my_nickname", nickname);
                listener.success();
            }

            @Override
            public void OnStrAccRegFail(TLSErrInfo tlsErrInfo) {
                listener.error(tlsErrInfo.ErrCode, tlsErrInfo.Msg);
            }

            @Override
            public void OnStrAccRegTimeout(TLSErrInfo tlsErrInfo) {
                listener.error(NETWORK_TIMEOUT, "网络失败，请求超时");
            }
        });
        if (result == TLSErrInfo.INPUT_INVALID) {
            listener.error(result, "无效的信息输入");
        }
    }

    public void getSelfProfile(@NonNull final IMInterface.SelfProfileListener listener) {
        TIMFriendshipManager.getInstance().getSelfProfile(new TIMValueCallBack<TIMUserProfile>() {
            @Override
            public void onError(int i, String s) {
                listener.error(i, s);
            }

            @Override
            public void onSuccess(TIMUserProfile timUserProfile) {
                listener.success(timUserProfile);
            }
        });
    }

    public String getTmpNickname(Context context) {
        return IMSP.getSpString(mContext, "my_nickname", "NULL");
    }


    public TIMFriendshipManager getFriendshipManager(){
        return TIMFriendshipManager.getInstance();
    }
}
