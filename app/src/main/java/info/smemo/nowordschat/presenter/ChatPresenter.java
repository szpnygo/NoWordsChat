package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMMessage;
import com.tencent.TIMValueCallBack;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import info.smemo.nbaseaction.util.LogHelper;
import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.appaction.bean.ElemBean;
import info.smemo.nowordschat.appaction.bean.FriendBean;
import info.smemo.nowordschat.appaction.controller.FriendController;
import info.smemo.nowordschat.appaction.controller.IMConversationController;
import info.smemo.nowordschat.appaction.event.MessageEvent;
import info.smemo.nowordschat.contract.ChatContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class ChatPresenter implements ChatContract.Presenter,
        IMConversationController.SendMessageListener, TIMValueCallBack<List<TIMMessage>>,
        Observer {

    private static final int ONCE_MESSAGES_NUM = 8;

    private IMConversationController controller;
    private ChatContract.View mView;

    private ArrayList<ElemBean> messages;
    private ArrayList<TIMMessage> timMessages = new ArrayList<>();

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
        getUserInfo();
    }

    @Override
    public void sendYueMessage() {
        controller.sendYueMessage(this);
    }

    @Override
    public void sendImageMessage(String path) {
        if (!StringUtil.isEmpty(path)) {
            controller.sendImageMessage(path, this);
        }
    }


    @Override
    public void getUserInfo() {
        FriendController.getUserInfo(peer, new FriendController.GetUserListener() {
            @Override
            public void success(FriendBean friendBean) {
                if (friendBean != null) {
                    mView.setTitle(friendBean.nickname);
                } else {
                    mView.showToastMessage("发生异常，获取对方信息失败");
                    mView.finishSelf();
                }
            }

            @Override
            public void error(int code, String message) {
                mView.showToastMessage("发生异常，获取对方信息失败");
                mView.finishSelf();
            }
        });
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
    public ArrayList<ElemBean> getData() {
        if (messages == null)
            messages = new ArrayList<>();
        return messages;
    }

    @Override
    public void onRefresh() {
        if (timMessages.size() > 0) {
            controller.getOnlineMessage(ONCE_MESSAGES_NUM, timMessages.get(0), this);
        } else {
            getLocalMessage();
        }
    }

    @Override
    public void addMessage(TIMMessage msg) {
        addMessage(msg, true);
    }

    @Override
    public void addMessage(TIMMessage msg, boolean first) {
        if (null != msg) {
            if (msg.getConversation().getType() == controller.conversation.getType()
                    && msg.getConversation().getIdentifer().equals(controller.conversation.getIdentifer())) {
                for (int i = 0; i < msg.getElementCount(); i++) {
                    TIMElem elem = msg.getElement(i);

                    if (elem.getType() == TIMElemType.Custom
                            || elem.getType() == TIMElemType.Image) {

                        LogHelper.i("Chat", "收到消息:" + elem.toString());
                        if (first) {
                            messages.add(0, new ElemBean(msg, elem));
                        } else {
                            messages.add(new ElemBean(msg, elem));
                        }
                    }
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
        this.timMessages.add(msg);
        addMessage(msg, false);
        mView.notifyDataSetChanged();
        mView.moveToBottom();
        controller.conversation.setReadMessage(this.timMessages.get(this.timMessages.size() - 1));
    }

    @Override
    public void onError(int i, String s) {
        mView.showSnackbarMessage(s);
    }

    @Override
    public void onSuccess(List<TIMMessage> timMessages) {
        for (TIMMessage message : timMessages) {
            addMessage(message);
            this.timMessages.add(0, message);
        }
        mView.notifyDataSetChanged();
        if (this.timMessages.size() > 0) {
            controller.conversation.setReadMessage(this.timMessages.get(this.timMessages.size() - 1));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MessageEvent) {
            TIMMessage msg = (TIMMessage) arg;
            addMessage(msg);
            this.timMessages.add(msg);
        }
        mView.notifyDataSetChanged();
    }
}
