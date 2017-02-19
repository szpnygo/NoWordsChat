package info.smemo.nowordschat.appaction.controller;

import com.android.annotations.NonNull;
import com.tencent.TIMConversation;
import com.tencent.TIMConversationType;
import com.tencent.TIMCustomElem;
import com.tencent.TIMManager;
import com.tencent.TIMMessage;
import com.tencent.TIMValueCallBack;

import java.util.List;

import info.smemo.nbaseaction.util.LogHelper;

public class IMConversationController {

    public TIMConversation conversation;
    public TIMConversationType type;
    public String peer;

    public IMConversationController(TIMConversation conversation) {
        this.conversation = conversation;
    }

    public IMConversationController(String type, String peer) {
        if (type.toUpperCase().equals("C2C")) {
            this.type = TIMConversationType.C2C;
        } else {
            this.type = TIMConversationType.Group;
        }
        this.peer = peer;
        this.createConversation(this.type, peer);
    }

    public IMConversationController(TIMConversationType type, String peer) {
        this.type = type;
        this.peer = peer;
        this.createConversation(type, peer);
    }

    public void getMessage(int num, TIMMessage message, TIMValueCallBack<List<TIMMessage>> callBack) {
        conversation.getLocalMessage(num, message, callBack);
    }

    public void getOnlineMessage(int num, TIMMessage message, TIMValueCallBack<List<TIMMessage>> callBack) {
        conversation.getMessage(num, message, callBack);
    }

    public void createConversation(TIMConversationType type, String peer) {
        conversation = getTIMManager().getConversation(type, peer);
    }

    public void sendYueMessage(@NonNull SendMessageListener listener) {
        TIMMessage msg = getCallMessage();
        if (msg == null) {
            listener.error(-1, "系统异常，发送失败");
        } else {
            sendMessage(msg, listener);
        }
    }

    public void sendMessage(@NonNull TIMMessage msg, @NonNull final SendMessageListener listener) {
        conversation.sendMessage(msg, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                listener.error(i, s);
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                LogHelper.i("Chat", timMessage.toString());
                listener.success(timMessage);
            }
        });
    }

    private TIMMessage getCallMessage() {
        TIMMessage msg = new TIMMessage();
        TIMCustomElem elem = new TIMCustomElem();
        elem.setData("".getBytes());
        elem.setDesc("Yue");
        if (msg.addElement(elem) != 0) {
            return null;
        }
        return msg;
    }

    private TIMManager getTIMManager() {
        return TIMManager.getInstance();
    }

    public interface SendMessageListener {

        void error(int code, String message);

        void success(TIMMessage timMessage);

    }
}
