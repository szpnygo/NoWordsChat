package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import com.tencent.TIMElem;
import com.tencent.TIMMessage;
import com.tencent.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import info.smemo.nbaseaction.util.LogHelper;
import info.smemo.nowordschat.appaction.bean.ElemBean;
import info.smemo.nowordschat.appaction.controller.IMConversationController;
import info.smemo.nowordschat.appaction.event.MessageEvent;
import info.smemo.nowordschat.contract.ChatContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChatPresenter implements ChatContract.Presenter,
        IMConversationController.SendMessageListener, TIMValueCallBack<List<TIMMessage>>,
        Observer {

    private static final int ONCE_MESSAGES_NUM = 10;

    private IMConversationController controller;
    private ChatContract.View mView;

    private ArrayList<ElemBean> messages;

    private String type;
    private String peer;

    public ChatPresenter(@NonNull ChatContract.View view) {
        mView = checkNotNull(view, "ChatPresenter.View cannot be null");
        mView.setPresenter(this);
        MessageEvent.getInstance().addObserver(this);
    }

    @Override
    public void start() {
        getLocalMessage();
    }

    @Override
    public void getLocalMessage() {
        controller.getMessage(ONCE_MESSAGES_NUM, null, this);
    }

    @Override
    public void init(String type, String peer) {
        this.type = type;
        this.peer = peer;
        controller = new IMConversationController(type, peer);
    }

    @Override
    public void sendYueMessage() {
        controller.sendYueMessage(this);
    }

    @Override
    public ArrayList<ElemBean> getData() {
        if (messages == null)
            messages = new ArrayList<>();
        return messages;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void addMessage(TIMMessage msg) {
        if (null != msg) {
            if (msg.getConversation().getType() == controller.conversation.getType()
                    && msg.getConversation().getIdentifer().equals(controller.conversation.getIdentifer())) {
                for (int i = 0; i < msg.getElementCount(); i++) {
                    TIMElem elem = msg.getElement(i);
                    LogHelper.i("Chat", "收到消息:" + elem.toString());
                    messages.add(new ElemBean(elem));
                }
            }
        }
    }

    @Override
    public void error(int code, String message) {
        mView.showSnackbarMessage(message);
    }

    @Override
    public void success(TIMMessage msg) {
        addMessage(msg);
        mView.notifyDataSetChanged();
    }

    @Override
    public void onError(int i, String s) {
        mView.showSnackbarMessage(s);
    }

    @Override
    public void onSuccess(List<TIMMessage> timMessages) {
        for (TIMMessage message : timMessages) {
            addMessage(message);
        }
        mView.notifyDataSetChanged();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MessageEvent) {
            TIMMessage msg = (TIMMessage) arg;
            addMessage(msg);
        }
        mView.notifyDataSetChanged();
    }
}
