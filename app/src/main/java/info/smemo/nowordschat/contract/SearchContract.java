package info.smemo.nowordschat.contract;


import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;
import info.smemo.nowordschat.appaction.bean.FriendBean;

public interface SearchContract {

    interface View extends NBaseView<Presenter> {

        void notifyDataSetChanged();

        void startLoading();

        void stopLoading();

        String getQuery();
    }

    interface Presenter extends NBasePresenter {

        void search(String nickname);

        ArrayList<FriendBean> getData();

    }

}
