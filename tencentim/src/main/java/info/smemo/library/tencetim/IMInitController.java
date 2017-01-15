package info.smemo.library.tencetim;

import android.content.Context;

import com.tencent.TIMLogLevel;
import com.tencent.TIMManager;

/**
 * 初始化操作
 * Created by neo on 17/1/10.
 */

public class IMInitController implements IMConstant {

    public static void init(Context context) {
        TIMManager.getInstance().init(context);
        //开启本地储存
        TIMManager.getInstance().enableFriendshipStorage(true);
        //启用群资料存储
        TIMManager.getInstance().enableFriendshipStorage(true);
        //设置日志级别
        TIMManager.getInstance().setLogLevel(TIMLogLevel.INFO);
        //开启已读回执
        TIMManager.getInstance().enableReadReceipt();
        //禁用自动上报
        TIMManager.getInstance().disableAutoReport();

        IMUserController.getInstance().init(context);
        IMLoginController.getInstance().init(context);
    }

}
