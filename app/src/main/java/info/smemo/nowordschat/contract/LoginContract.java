package info.smemo.nowordschat.contract;

import android.support.annotation.NonNull;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;

public interface LoginContract {

    interface View extends NBaseView<Presenter> {

        void showMessage(String message);

        void loginSuccess();

        void register();

        void scrollToBottom();

        void setEmailErrorMessage(String message);

        void setPasswordErrorMessage(String message);
    }

    interface Presenter extends NBasePresenter {

        void register();

        void onTextChanged(CharSequence charSequence, int start, int before, int count);

        boolean login(int id, @NonNull String email, @NonNull String password);

        void login(@NonNull String email, @NonNull String password);

        boolean checkData(String email,String password);

    }
}
