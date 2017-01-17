package info.smemo.nowordschat.contract;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;
import info.smemo.nowordschat.appaction.bean.UserBean;
import info.smemo.nowordschat.bean.FindBean;

public interface UserContract {

    interface View extends NBaseView<Presenter> {

        void startChat();

        void notifyDataSetChanged();

        void addFriendSuccess();

        UserBean getUser();

    }

    interface Presenter extends NBasePresenter {

        void floatingButtonClick(boolean isFriend);

        void loadData();

        ArrayList<FindBean> getData();

        void addFriend();

        void sendMessage();
    }

}
