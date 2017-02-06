package info.smemo.nowordschat.contract;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;
import info.smemo.nowordschat.appaction.bean.BookBean;

public interface NewFriendContract {

    interface View extends NBaseView<Presenter> {

        void notifyDataSetChanged();

        void startUser(BookBean bean);

    }

    interface Presenter extends NBasePresenter {

        void onRefresh();

        void loadFutureData();

        ArrayList<BookBean> getData();
    }

}
