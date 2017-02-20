package info.smemo.nowordschat.adapter;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.TIMImage;
import com.tencent.TIMImageElem;
import com.tencent.TIMImageType;
import com.tencent.TIMMessage;

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
                view.setImageURI(Uri.parse(image.getUrl()));
                break;
            }
        }
    }

}
