package info.smemo.library.tencetim;

import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMMessageListener;

import java.util.List;

public class IMMessageController implements IMConstant {

    private static IMMessageController instance;

    private static class Single {
        private static IMMessageController instance = new IMMessageController();
    }

    public static synchronized IMMessageController getInstance() {
        if (null == instance) {
            instance = IMMessageController.Single.instance;
        }
        return instance;
    }


    public void addMessageListener(){
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                return false;
            }
        });
    }
}
