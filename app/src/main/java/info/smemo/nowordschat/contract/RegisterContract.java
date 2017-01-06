package info.smemo.nowordschat.contract;

import android.support.annotation.NonNull;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;

public interface RegisterContract {

    interface View extends NBaseView<Presenter> {

        void registerSuccess();

        void scrollToBottom();

        void setEmailErrorMessage(String message);

        void setPasswordErrorMessage(String message);

        void setNicknameErrorMessage(String message);
    }

    interface Presenter extends NBasePresenter {

        void onTextChanged(CharSequence charSequence, int start, int before, int count);

        boolean register(int id,@NonNull String nickname, @NonNull String email, @NonNull String password);

        void register(@NonNull String nickname, @NonNull String email, @NonNull String password);

        boolean checkData(String nickname,String email,String password);

        void onFocusChange(android.view.View view, boolean hasFocus);
    }
}
