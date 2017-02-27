package info.smemo.nowordschat.contract;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;
import info.smemo.nowordschat.appaction.bean.UserBean;

public interface MyUserContract {

    interface View extends NBaseView<Presenter> {

        void startEditNickNamePage();

        void startEditSignaturePage();

        void selectImage();

    }

    interface Presenter extends NBasePresenter {

        UserBean getUserInfo();

        void editNickname();

        void editSignature();

        void selectImage();

    }

}
