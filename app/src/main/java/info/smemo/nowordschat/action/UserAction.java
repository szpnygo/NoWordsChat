package info.smemo.nowordschat.action;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import info.smemo.nowordschat.appaction.ActionInterface;
import info.smemo.nowordschat.appaction.controller.UserController;
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

    public static void autoLogin(@NonNull ActionInterface.BaseComplete complete) {
        UserController.getInstance().autoLogin(complete);
    }

    /**
     * 用户是否登录
     */
    public static boolean needLogin() {
        return UserController.getInstance().needLogin();
    }

    /**
     * 获取登录用户信息
     */
    public static void getUser(UserController.LoadUserProfileSuccessListener listener) {
        UserController.getInstance().getUser(listener);
    }

    /**
     * 用户退出
     */
    public static void logout() {
        UserController.getInstance().logout();
    }

}
