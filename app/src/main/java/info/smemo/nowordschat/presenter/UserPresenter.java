package info.smemo.nowordschat.presenter;

import java.util.ArrayList;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.bean.FindBean;
import info.smemo.nowordschat.contract.UserContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserPresenter implements UserContract.Presenter {

    private UserContract.View mView;

    private ArrayList<FindBean> mList;

    public UserPresenter(UserContract.View view) {
        mView = checkNotNull(view, "UserContract.View cannot be null");
        mView.setPresenter(this);
    }


    @Override
    public void start() {
        loadData();
    }

    @Override
    public void floatingButtonClick(boolean isFriend) {
        if (isFriend) {
            sendMessage();
        } else {
            addFriend();
        }
    }

    @Override
    public void loadData() {
        FindBean findBean = new FindBean();
        findBean.imgUri.add("res:///" + R.drawable.user_logo);

        mList.add(findBean);
        mList.add(findBean);
        mList.add(findBean);

        mView.notifyDataSetChanged();
    }

    @Override
    public ArrayList<FindBean> getData() {
        if (mList == null)
            mList = new ArrayList<>();
        return mList;
    }

    @Override
    public void addFriend() {
        mView.showSnackbarMessage("好友添加成功");
        mView.addFriendSuccess();
    }

    @Override
    public void sendMessage() {

    }
}
