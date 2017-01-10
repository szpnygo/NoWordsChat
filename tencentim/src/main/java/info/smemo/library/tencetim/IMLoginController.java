package info.smemo.library.tencetim;

import android.content.Context;
import android.support.annotation.NonNull;

import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSRefreshUserSigListener;
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

    public void login(@NonNull String email, @NonNull String password) {
        mTLSLoginHelper.TLSPwdLogin(email, password.getBytes(), new TLSPwdLoginListener() {
            @Override
            public void OnPwdLoginSuccess(TLSUserInfo tlsUserInfo) {

            }

            @Override
            public void OnPwdLoginReaskImgcodeSuccess(byte[] bytes) {

            }

            @Override
            public void OnPwdLoginNeedImgcode(byte[] bytes, TLSErrInfo tlsErrInfo) {

            }

            @Override
            public void OnPwdLoginFail(TLSErrInfo tlsErrInfo) {

            }

            @Override
            public void OnPwdLoginTimeout(TLSErrInfo tlsErrInfo) {

            }
        });
    }

    public void login(@NonNull String email, @NonNull String password, TLSPwdLoginListener listener) {
        mTLSLoginHelper.TLSPwdLogin(email, password.getBytes(), listener);
    }


    public TLSUserInfo getLastUser() {
        return mTLSLoginHelper.getLastUserInfo();
    }

    public boolean needLogin() {
        return needLogin(getLastUser());
    }

    public boolean needLogin(TLSUserInfo userInfo) {
        return userInfo != null && needLogin(userInfo.identifier);
    }

    public boolean needLogin(@NonNull String identifier) {
        return mTLSLoginHelper.needLogin(identifier);
    }

    public boolean hasLogin() {
        return !needLogin();
    }

    public void refreshUserSig(String identifier) {
        if (!needLogin(identifier)) {
            mTLSLoginHelper.TLSRefreshUserSig(identifier, new TLSRefreshUserSigListener() {
                @Override
                public void OnRefreshUserSigSuccess(TLSUserInfo tlsUserInfo) {

                }

                @Override
                public void OnRefreshUserSigFail(TLSErrInfo tlsErrInfo) {
//                    tlsErrInfo.ErrCode == TLSErrInfo. LOGIN_WRONG_PWD
                }

                @Override
                public void OnRefreshUserSigTimeout(TLSErrInfo tlsErrInfo) {

                }
            });
        }
    }
}


