package info.smemo.nowordschat.contract;

import android.support.annotation.NonNull;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;

public interface LoginContract {

    interface View extends NBaseView<Presenter> {

        void checkData();

        void setEmailErrorMessage(String message);

        void setPasswordErrorMessage(String message);

    }

    interface Presenter extends NBasePresenter {

        void login(@NonNull String email,@NonNull String password);

        void register();
    }
}
