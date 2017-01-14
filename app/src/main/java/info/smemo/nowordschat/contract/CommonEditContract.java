package info.smemo.nowordschat.contract;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;

public interface CommonEditContract {

    interface EditDoneListener{

        void done();
    }

    interface View extends NBaseView<Presenter> {

        void setMessageError(String messageError);

        void setEditBean(String text);

        void setTitle(String title);

        void setCounterMaxLength(int length);

        void setOnDoneListener(EditDoneListener listener);

        String getText();

    }

    interface Presenter extends NBasePresenter {

        void init(CommonEditContract.View view);

        void editDone();

        void updateText();

    }

}
