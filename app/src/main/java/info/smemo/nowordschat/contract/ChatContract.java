package info.smemo.nowordschat.contract;

import com.tencent.TIMMessage;

import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;
import info.smemo.nowordschat.appaction.bean.ElemBean;

public interface ChatContract {

    interface View extends NBaseView<Presenter> {

        void showSnackbarMessage(String message);

        void notifyDataSetChanged();

        void startLoading();

        void stopLoading();

        void moveToBottom();

        void setTitle(String title);

    }

    interface Presenter extends NBasePresenter {

        void sendYueMessage();

        void sendImageMessage(String path);


        void getUserInfo();

        void getLocalMessage();

        void init(String type, String peer);

        ArrayList<ElemBean> getData();

        void onRefresh();

        void addMessage(TIMMessage message);

        void addMessage(TIMMessage message,boolean first);

    }
}
