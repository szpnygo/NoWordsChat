package info.smemo.nowordschat.action;

import android.content.Context;

import info.smemo.nowordschat.appaction.ActionInterface;
import info.smemo.nowordschat.appaction.bean.UserBean;
import info.smemo.nowordschat.appaction.controller.UserController;
import info.smemo.nowordschat.base.BaseAction;

public class UserInfoAction extends BaseAction {

    public static UserBean getUserInfo() {
        return UserController.getInstance().getUserInfo();
    }

    public static String getTmpNickname(Context context) {
        return UserController.getInstance().getTmpNcikName(context);
    }

    public static void setNickname(String nickname, ActionInterface.BaseComplete complete) {
        UserController.getInstance().setNickname(nickname, complete);
    }

    public static void setSignature(String signature, ActionInterface.BaseComplete complete) {
        UserController.getInstance().setSignature(signature, complete);
    }

}
