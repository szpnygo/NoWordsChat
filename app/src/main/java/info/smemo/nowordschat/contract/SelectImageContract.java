package info.smemo.nowordschat.contract;


import java.util.ArrayList;

import info.smemo.nbaseaction.base.NBasePresenter;
import info.smemo.nbaseaction.base.NBaseView;

public interface SelectImageContract {

    interface View extends NBaseView<Presenter> {

        void notifyDataSetChanged();

        void setAlbumListShow();

    }

    interface Presenter extends NBasePresenter {

        void loadData();

        void loadAlbumData();

        void selectAlbum();

        ArrayList<String> getData();

    }

}
