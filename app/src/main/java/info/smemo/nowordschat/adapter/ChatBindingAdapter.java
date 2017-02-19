package info.smemo.nowordschat.adapter;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.TIMImage;
import com.tencent.TIMImageElem;
import com.tencent.TIMImageType;
import com.tencent.TIMMessage;
import com.tencent.TIMValueCallBack;

import java.io.File;

import info.smemo.nbaseaction.util.FileUtils;
import info.smemo.nowordschat.app.AppApplication;
import info.smemo.nowordschat.util.TimeHelper;

public class ChatBindingAdapter {

    @BindingAdapter({"android:chatYue"})
    public static void showText(TextView view, TIMMessage message) {

        view.setText((message.isSelf() ? "您" : "对方") + TimeHelper.getTimePassStr(message.timestamp() * 1000) + "约了" + (message.isSelf() ? "对方" : "您") + "一下");
    }

    @BindingAdapter({"android:elemImage"})
    public static void setImageUri(final SimpleDraweeView view, TIMImageElem e) {
        for (final TIMImage image : e.getImageList()) {

            if (image.getType() == TIMImageType.Thumb) {
                final String filePath = AppApplication.getContext().getFilesDir().getAbsolutePath() + "/no_words_chat/img/" + image.getUuid() + "_Thumb.jpg";
                File thumbFile = new File(filePath);
                if (thumbFile.exists()) {
                    view.setAspectRatio(image.getHeight() / image.getWidth());
                    view.setImageURI(Uri.parse("file:///" + filePath));
                } else {
                    image.getImage(new TIMValueCallBack<byte[]>() {
                        @Override
                        public void onError(int code, String desc) {
                            view.setAspectRatio(1.0f);
                            view.setImageURI(Uri.parse("res:///" + info.smemo.nowordschat.appaction.R.drawable.anonymous));
                        }

                        @Override
                        public void onSuccess(byte[] data) {
                            FileUtils.saveBitmap(filePath, data);
                            view.setAspectRatio(image.getHeight() / image.getWidth());
                            view.setImageURI(Uri.parse("file:///" + filePath));
                        }
                    });
                }
                break;
            }
        }
    }

}
