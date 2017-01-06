package info.smemo.nowordschat.action;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import info.smemo.nowordschat.base.BaseAction;
import info.smemo.nowordschat.base.BaseActionInterface;
import info.smemo.nowordschat.leancloud.LeanActionInterface;
import info.smemo.nowordschat.leancloud.LeanUserController;

public class UserAction extends BaseAction {

    /**
     * 注册用户
     *
     * @param nickname 用户昵称
     * @param password 密码
     * @param email    邮箱
     */
    public static void createUser(@NonNull String email, @NonNull String nickname, @NonNull String password,
                                  @Nullable final BaseActionInterface.BaseComplete complete) {
        LeanUserController.createUser(nickname, email, password, new LeanActionInterface.LeanBaseComplete() {
            @Override
            public void success() {
                if (null != complete)
                    complete.success();
            }

            @Override
            public void error(int code, String message) {
                if (null != complete)
                    complete.error(code, message);
            }
        });
    }

    /**
     * 用户是否登录
     */
    public static boolean isUserLogin() {
        return false;
    }

    public static void getCurrentUser() {
        LeanUserController.getCurrentUser();
    }
}
