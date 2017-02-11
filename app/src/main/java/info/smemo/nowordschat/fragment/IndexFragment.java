package info.smemo.nowordschat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.smemo.nbaseaction.adapter.NBaseBindingAdapter;
import info.smemo.nbaseaction.base.NBaseFragment;
import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.activity.ChatActivity;
import info.smemo.nowordschat.appaction.bean.MessageBean;
import info.smemo.nowordschat.contract.IndexContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class IndexFragment extends NBaseFragment implements IndexContract.View {

    private IndexContract.Presenter mPresenter;

    private NBaseBindingAdapter messageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        messageAdapter = new NBaseBindingAdapter<>(mPresenter.getData(), BR.bean, R.layout.item_message);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(messageAdapter);

        initAdapter();

        return view;
    }

    private void initAdapter() {
        messageAdapter.setListener(new NBaseBindingAdapter.OnAdapterClickListener<MessageBean>() {
            @Override
            public void onClick(View view, int position, MessageBean object) {
                showChat(object);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void notifyDataSetChanged() {
        messageAdapter.notifyDataSetChanged();
    }

    @Override
    public void showChat(MessageBean object) {
        Bundle bundle = new Bundle();
        bundle.putString("peer", object.identifier);
        bundle.putString("type", "c2c");
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void setPresenter(IndexContract.Presenter presenter) {
        this.mPresenter = checkNotNull(presenter);
    }
}
