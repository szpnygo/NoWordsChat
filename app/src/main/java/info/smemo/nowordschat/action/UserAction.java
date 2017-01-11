package info.smemo.nowordschat.action;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import info.smemo.nowordschat.appaction.UserController;
import info.smemo.nowordschat.base.BaseAction;

public class UserAction extends BaseAction {

    /**
     * 注册用户
     *
     * @param nickname 用户昵称
     * @param password 密码
     * @param account  邮箱
     */
    public static void createUser(@NonNull String account, @NonNull String nickname, @NonNull String password,
                                  @Nullable UserController.RegisterSuccessListener listener) {
        UserController.getInstance().register(account, password, nickname, listener);
    }

    public static void loginUser(@NonNull String account, @NonNull String password, @NonNull UserController.LoginSuccessListener listener) {
        UserController.getInstance().login(account, password, listener);
    }

    /**
     * 用户是否登录
     */
    public static boolean needLogin() {
        return UserController.getInstance().needLogin();
    }

}
