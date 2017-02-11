package info.smemo.nowordschat.contract;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;

public interface ChatContract {

    interface View extends NBaseView<Presenter> {

        void showSnackbarMessage(String message);
    }

    interface Presenter extends NBasePresenter {

        void init(String type, String peer);

        void sendYueMessage();

    }
}
