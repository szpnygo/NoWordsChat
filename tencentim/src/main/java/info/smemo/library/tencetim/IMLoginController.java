package info.smemo.library.tencetim;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;

import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSUserInfo;

public class IMLoginController implements IMConstant {

    private static IMLoginController instance;

    private TLSLoginHelper mTLSLoginHelper;

    private static class Single {
        private static IMLoginController instance = new IMLoginController();
    }

    public static synchronized IMLoginController getInstance() {
        if (null == instance) {
            instance = Single.instance;
        }
        return instance;
    }

    public void init(Context context) {
        mTLSLoginHelper = TLSLoginHelper.getInstance()
                .init(context, APP_ID, ACCOUNT_TYPE, LIBRARY_VER);
    }

    public void login(@NonNull String account, @NonNull String password, @NonNull final IMInterface.LoginListener listener) {
        mTLSLoginHelper.TLSPwdLogin(account, password.getBytes(), new TLSPwdLoginListener() {
            @Override
            public void OnPwdLoginSuccess(TLSUserInfo tlsUserInfo) {

                TIMUser user = new TIMUser();
                user.setAccountType(String.valueOf(ACCOUNT_TYPE));
                user.setIdentifier(tlsUserInfo.identifier);

                TIMManager.getInstance().login((int) APP_ID, user,
                        mTLSLoginHelper.getUserSig(tlsUserInfo.identifier), new TIMCallBack() {
                            @Override
                            public void onError(int i, String s) {
                                listener.error(i, s);
                            }

                            @Override
                            public void onSuccess() {
                                listener.success();
                            }
                        });
            }

            @Override
            public void OnPwdLoginReaskImgcodeSuccess(byte[] bytes) {

            }

            @Override
            public void OnPwdLoginNeedImgcode(byte[] bytes, TLSErrInfo tlsErrInfo) {

            }

            @Override
            public void OnPwdLoginFail(TLSErrInfo tlsErrInfo) {
                listener.error(tlsErrInfo.ErrCode, tlsErrInfo.Msg);
            }

            @Override
            public void OnPwdLoginTimeout(TLSErrInfo tlsErrInfo) {
                listener.error(NETWORK_TIMEOUT, "网络失败，请求超时");
            }
        });
    }

    public TLSUserInfo getLastUser() {
        return mTLSLoginHelper.getLastUserInfo();
    }

    public boolean needLogin() {
        return needLogin(getLastUser()) || TIMManager.getInstance().getLoginUser().isEmpty();
    }

    public boolean needLogin(TLSUserInfo userInfo) {
        return userInfo == null || needLogin(userInfo.identifier) || TIMManager.getInstance().getLoginUser().isEmpty();
    }

    public boolean needLogin(@NonNull String identifier) {
        return mTLSLoginHelper.needLogin(identifier) || TIMManager.getInstance().getLoginUser().isEmpty();
    }

    public void logout() {
        TIMManager.getInstance().logout(new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Log.e("imsdk", i + ":" + s);
            }

            @Override
            public void onSuccess() {
                Log.e("imsdk", "logout success");
            }
        });
    }

}


