package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import com.tencent.TIMMessage;
import com.tencent.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;

import info.smemo.nowordschat.appaction.bean.ElemBean;
import info.smemo.nowordschat.appaction.controller.IMConversationController;
import info.smemo.nowordschat.contract.ChatContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChatPresenter implements ChatContract.Presenter,
        IMConversationController.SendMessageListener, TIMValueCallBack<List<TIMMessage>> {

    private static final int ONCE_MESSAGES_NUM = 10;

    private IMConversationController controller;
    private ChatContract.View mView;

    private ArrayList<ElemBean> messages;

    private String type;
    private String peer;

    public ChatPresenter(@NonNull ChatContract.View view) {
        mView = checkNotNull(view, "ChatPresenter.View cannot be null");
        mView.setPresenter(this);

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
    public void error(int code, String message) {
        mView.showSnackbarMessage(message);
    }

    @Override
    public void success(TIMMessage timMessage) {

    }

    @Override
    public void onError(int i, String s) {
        mView.showSnackbarMessage(s);
    }

    @Override
    public void onSuccess(List<TIMMessage> timMessages) {
        for (TIMMessage message : timMessages) {
            for (int i = 0; i < message.getElementCount(); i++) {
                messages.add(new ElemBean(message.getElement(i)));
            }
        }
        mView.notifyDataSetChanged();
    }

}
