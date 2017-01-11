package info.smemo.nowordschat.contract;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;
import info.smemo.nowordschat.bean.FindBean;

public interface FindContract {

    interface View extends NBaseView<Presenter> {

        void showFinds(ArrayList<FindBean> list);

        void notifyDataSetChanged();

        void showMessageToast(int message);
    }

    interface Presenter extends NBasePresenter {

        void loadBookData();

        void checkNewMessage();

    }
}
