package info.smemo.library.tencetim;

import android.content.Context;

import com.tencent.TIMManager;

/**
 * 初始化操作
 * Created by neo on 17/1/10.
 */

public class IMInitController {

    public static void init(Context context) {
        TIMManager.getInstance().init(context);
        IMUserController.getInstance().init(context);
        IMLoginController.getInstance().init(context);
    }

}
