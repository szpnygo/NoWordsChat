package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.bean.MessageBean;
import info.smemo.nowordschat.contract.IndexContract;
import static com.google.common.base.Preconditions.checkNotNull;

public class IndexPresenter implements IndexContract.Presenter {

    private final IndexContract.View mView;

    public IndexPresenter(@NonNull IndexContract.View view) {
        mView = checkNotNull(view,"IndexContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        ArrayList<MessageBean> messageBeanArrayList = new ArrayList<>();

        MessageBean messageBean = new MessageBean();
        messageBean.username = "无语开发者";
        messageBean.time = "10:44";
        messageBean.message = "吃饭了吗？";
        messageBean.unRead = 2;
        messageBean.userLogo = "res:///" + R.drawable.user_logo;
        messageBeanArrayList.add(messageBean);

        MessageBean messageBean1 = new MessageBean();
        messageBean1.username = "陪你去看海";
        messageBean1.time = "12月3号";
        messageBean1.message = "你去哪了？来斗图呀";
        messageBean1.userLogo = "res:///" + R.drawable.user_logo;
        messageBeanArrayList.add(messageBean1);

        MessageBean messageBean2 = new MessageBean();
        messageBean2.username = "爱情公寓";
        messageBean2.time = "11月3号";
        messageBean2.message = "你的月亮我的新";
        messageBean2.unRead = 12;
        messageBean2.userLogo = "res:///" + R.drawable.user_logo;
        messageBeanArrayList.add(messageBean2);

        mView.showMessageList(messageBeanArrayList);
    }

    @Override
    public void start() {
        loadData();
    }
}
