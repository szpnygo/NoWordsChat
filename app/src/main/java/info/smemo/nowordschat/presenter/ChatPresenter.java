package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import info.smemo.nowordschat.appaction.controller.IMConversationController;
import info.smemo.nowordschat.contract.ChatContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChatPresenter implements ChatContract.Presenter, IMConversationController.SendMessageListener {

    private IMConversationController controller;

    private ChatContract.View mView;

    public ChatPresenter(@NonNull ChatContract.View view) {
        mView = checkNotNull(view, "ChatPresenter.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void init(String type, String peer) {
        controller = new IMConversationController(type, peer);
    }

    @Override
    public void sendYueMessage() {
        controller.sendYueMessage(this);
    }

    @Override
    public void error(int code, String message) {
        mView.showSnackbarMessage(message);
    }

    @Override
    public void success() {

    }
}
