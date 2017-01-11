package info.smemo.nowordschat.appaction;

import android.support.annotation.NonNull;

import info.smemo.library.tencetim.IMInterface;
import info.smemo.library.tencetim.IMLoginController;
import info.smemo.library.tencetim.IMUserController;

public class UserController {

    private static UserController instance;

    private IMLoginController mLoginController = IMLoginController.getInstance();
    private IMUserController mUserController = IMUserController.getInstance();

    private static class Single {
        private static UserController instance = new UserController();
    }

    public static synchronized UserController getInstance() {
        if (null == instance) {
            instance = Single.instance;
        }
        return instance;
    }

    public void register(@NonNull String email, @NonNull String password, @NonNull String nickname, @NonNull final RegisterSuccessListener listener) {
        mUserController.register(email, password, nickname, new IMInterface.RegisterListener() {
            @Override
            public void success() {
                listener.success();
            }

            @Override
            public void error(int code, String message) {
                listener.error(code, message);
            }
        });
    }

    public void login(@NonNull String account, @NonNull String password, @NonNull final LoginSuccessListener listener) {
        mLoginController.login(account, password, new IMInterface.LoginListener() {
            @Override
            public void success() {
                listener.success();
            }

            @Override
            public void error(int code, String message) {
                listener.error(code, message);
            }
        });
    }

    public boolean needLogin(){
        return mLoginController.needLogin();
    }

    public void getUser(){
        mLoginController.getLastUser();
    }


    public interface RegisterSuccessListener extends ActionInterface.BaseComplete {
    }

    public interface LoginSuccessListener extends ActionInterface.BaseComplete {
    }

}
