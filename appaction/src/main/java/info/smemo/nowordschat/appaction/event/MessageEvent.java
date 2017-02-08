package info.smemo.nowordschat.appaction.event;

import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMMessageListener;

import java.util.List;
import java.util.Observable;

public class MessageEvent extends Observable implements TIMMessageListener{

    private volatile static MessageEvent instance;

    private MessageEvent(){
        //注册消息监听器
        TIMManager.getInstance().addMessageListener(this);
    }

    public static MessageEvent getInstance(){
        if (instance == null) {
            synchronized (MessageEvent.class) {
                if (instance == null) {
                    instance = new MessageEvent();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean onNewMessages(List<TIMMessage> list) {
        for (TIMMessage item:list){
            setChanged();
            notifyObservers(item);
        }
        return false;
    }

    /**
     * 主动通知新消息
     */
    public void onNewMessage(TIMMessage message){
        setChanged();
        notifyObservers(message);
    }

    /**
     * 清理消息监听
     */
    public void clear(){
        instance = null;
    }


}
