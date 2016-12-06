package info.smemo.nowordschat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nbaseaction.base.NBaseFragment;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.activity.ChatActivity;
import info.smemo.nowordschat.bean.MessageBean;

public class IndexFragment extends NBaseFragment {

    private NBaseBindingAdapter messageAdapter;
    private ArrayList<MessageBean> messageBeanArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messageBeanArrayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        messageAdapter = new NBaseBindingAdapter<>(messageBeanArrayList, BR.bean, R.layout.item_message);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);
        messageAdapter.notifyDataSetChanged();

        initAdapter();

        return view;
    }

    private void initAdapter() {
        messageAdapter.setListener(new NBaseBindingAdapter.OnAdapterClickListener<MessageBean>() {
            @Override
            public void onClick(View view, int position, MessageBean object) {
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });

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

        messageAdapter.notifyDataSetChanged();
    }

}
