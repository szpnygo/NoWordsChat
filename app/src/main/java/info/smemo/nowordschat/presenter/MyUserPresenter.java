package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import info.smemo.nowordschat.action.UserInfoAction;
import info.smemo.nowordschat.appaction.bean.UserBean;
import info.smemo.nowordschat.contract.MyUserContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class MyUserPresenter implements MyUserContract.Presenter {

    private final MyUserContract.View mView;

    public MyUserPresenter(@NonNull MyUserContract.View view) {
        mView = checkNotNull(view, "MyUserContract.View cannot be null");
        mView.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public UserBean getUserInfo() {
        return UserInfoAction.getUserInfo();
    }
}
