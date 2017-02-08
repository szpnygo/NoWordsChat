package info.smemo.nowordschat.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import info.smemo.nowordschat.R;

public class NotificationUtil {

    public static void notification(Context context, Class<?> cls, String content, String ticker) {
        notification(context, cls, 0, content, ticker, 0);
    }

    public static void notification(Context context, Class<?> cls, int number, String content, String ticker, int pushId) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
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
