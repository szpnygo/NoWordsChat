package info.smemo.nowordschat.contract;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;

public interface MainContract {

    interface View extends NBaseView<Presenter> {

        void reLogin();

    }

    interface Presenter extends NBasePresenter {


    }

}
