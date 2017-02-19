package info.smemo.nowordschat.contract;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;
import info.smemo.nowordschat.appaction.bean.MessageBean;

public interface IndexContract {

    interface View extends NBaseView<Presenter> {

        void notifyDataSetChanged();

        void showChat(MessageBean object);

    }

    interface Presenter extends NBasePresenter {

        void loadData();

        ArrayList<MessageBean> getData();

        void onRefresh();
    }

}
