package info.smemo.nowordschat.util;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import info.smemo.nowordschat.R;

public class DataBingUtils {

    @BindingAdapter({"android:showText"})
    public static void showText(TextView view, String text) {
        if (text.toUpperCase().equals("STAR")) {
            view.setText("");
            view.setBackgroundResource(R.drawable.ic_star_green_24dp);
        } else {
            view.setBackground(null);
            view.setText(text.toUpperCase());
        }
    }
}
