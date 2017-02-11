package info.smemo.nowordschat.presenter;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.widget.TextView;

import java.util.ArrayList;

import info.smemo.nowordschat.appaction.bean.MessageBean;
import info.smemo.nowordschat.appaction.controller.ConversationController;
import info.smemo.nowordschat.contract.IndexContract;
import info.smemo.nowordschat.util.TimeHelper;

import static com.google.common.base.Preconditions.checkNotNull;

public class IndexPresenter implements IndexContract.Presenter {

    private final IndexContract.View mView;

    private ArrayList<MessageBean> list;

    public IndexPresenter(@NonNull IndexContract.View view) {
        mView = checkNotNull(view, "IndexContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public ArrayList<MessageBean> getData() {
        if (null == list)
            list = new ArrayList<>();
        return list;
    }

    @Override
    public void loadData() {
        list.clear();
        ArrayList<MessageBean> messages = ConversationController.getConversations();
        for (MessageBean bean : messages) {
            list.add(bean);
        }
        mView.notifyDataSetChanged();
    }

    @Override
    public void start() {
        loadData();
    }

    @BindingAdapter({"android:timePass"})
    public static void showText(TextView view, long time) {
        view.setText(TimeHelper.getTimePassStr(time));
    }
}
