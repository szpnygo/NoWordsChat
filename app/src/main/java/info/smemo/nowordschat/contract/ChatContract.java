package info.smemo.nowordschat.contract;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;
import info.smemo.nowordschat.appaction.bean.ElemBean;

public interface ChatContract {

    interface View extends NBaseView<Presenter> {

        void showSnackbarMessage(String message);

        void notifyDataSetChanged();
    }

    interface Presenter extends NBasePresenter {

        void getLocalMessage();

        void init(String type, String peer);

        void sendYueMessage();

        ArrayList<ElemBean> getData();

        void onRefresh();

    }
}
