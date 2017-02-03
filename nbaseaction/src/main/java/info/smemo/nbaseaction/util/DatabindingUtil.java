package info.smemo.nbaseaction.util;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Locale;

@BindingMethods({
        @BindingMethod(type = SwipeRefreshLayout.class, attribute = "android:onRefresh", method = "setOnRefreshListener"),
        @BindingMethod(type = FloatingActionButton.class, attribute = "android:onClick", method = "setOnClickListener"),
})
public class DatabindingUtil {

    @BindingAdapter({"android:actualImageUri"})
    public static void setImageUri(SimpleDraweeView view, String url) {
        view.setImageURI(url);
    }

    @BindingAdapter({"android:smallImage", "android:highImage"})
    public static void setLowImageUri(SimpleDraweeView view, String small, String high) {
        FrescoUtil.loadLowImage(view, small, high);
    }

    @BindingAdapter({"android:timeFormat", "android:timeValue"})
    public static void formatTime(TextView view, String format, String time) {
        String timeFormat = StringUtil.isEmpty(format) ? "yyyy-MM-dd" : format;
        SimpleDateFormat dateFormat = new SimpleDateFormat(timeFormat, Locale.CHINA);
        view.setText(dateFormat.format(Long.valueOf(time)));
    }
}
