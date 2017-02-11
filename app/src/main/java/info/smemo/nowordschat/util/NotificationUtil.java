package info.smemo.nowordschat.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.tencent.TIMConversationType;
import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMMessage;
import com.tencent.TIMSNSSystemElem;

import java.util.Observable;
import java.util.Observer;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.activity.ChatActivity;
import info.smemo.nowordschat.activity.NewFriendActivity;
import info.smemo.nowordschat.app.AppApplication;
import info.smemo.nowordschat.appaction.event.MessageEvent;

public class NotificationUtil implements Observer {

    private static NotificationUtil instance;

    private static final int pushId = 1;

    private static class Single {
        private static NotificationUtil instance = new NotificationUtil();
    }

    public static synchronized NotificationUtil getInstance() {
        if (null == instance) {
            instance = NotificationUtil.Single.instance;
        }
        return instance;
    }

    private NotificationUtil() {
        MessageEvent.getInstance().addObserver(this);
    }

    public void reset() {
        NotificationManager notificationManager = (NotificationManager) AppApplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(pushId);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof MessageEvent) {
            TIMMessage msg = (TIMMessage) arg;
            if (null != msg) {
                if (msg.getConversation().getType() == TIMConversationType.System) {
                    for (int i = 0; i < msg.getElementCount(); i++) {
                        TIMElem elem = msg.getElement(i);
                        if (elem.getType() == TIMElemType.SNSTips) {
                            createSystemNotification((TIMSNSSystemElem) elem);
                        }
                    }
                } else if (msg.getConversation().getType() == TIMConversationType.C2C && !msg.isRead() && !msg.isSelf()) {
                    String message;
                    if (msg.getSenderProfile() != null) {
                        message = msg.getSenderProfile().getNickName() + "向你发送一条消息";
                    } else {
                        message = "系统向你发送一条消息";
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("peer", msg.getConversation().getIdentifer());
                    bundle.putString("type", "c2c");
                    notification(ChatActivity.class, 1, message, message, bundle);
                }
            }
        }
    }

    private void createSystemNotification(TIMSNSSystemElem elem) {
        switch (elem.getSubType()) {
            case TIM_SNS_SYSTEM_ADD_FRIEND_REQ:
                if (elem.getChangeInfoList().size() > 0) {
                    String message = elem.getChangeInfoList().get(0).getNickName() + "请求添加您为好友";
                    notification(NewFriendActivity.class, 1, message, message, null);
                }
                break;
        }
    }

    public static void notification(@NonNull Class<?> cls, int number, String content, String ticker, @Nullable Bundle bundle) {
        if (AppApplication.getContext() != null) {
            NotificationManager notificationManager = (NotificationManager) AppApplication.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(AppApplication.getContext());
            Intent intent = new Intent(AppApplication.getContext(), cls);
            if (bundle != null)
                intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(AppApplication.getContext(), 0, intent, 0);
            builder.setContentTitle("无语")
                    .setContentText(content)
                    .setContentIntent(pendingIntent)
                    .setNumber(number)
                    .setTicker(ticker)
                    .setWhen(System.currentTimeMillis())
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSmallIcon(R.mipmap.ic_launcher);
            Notification notify = builder.build();
            notify.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(pushId, notify);
        }
    }
}
