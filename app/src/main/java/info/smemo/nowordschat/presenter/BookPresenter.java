package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import info.smemo.nowordschat.action.FriendAction;
import info.smemo.nowordschat.appaction.bean.BookBean;
import info.smemo.nowordschat.appaction.controller.FriendController;
import info.smemo.nowordschat.contract.BookContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class BookPresenter implements BookContract.Presenter {

    private BookContract.View mView;

    private ArrayList<BookBean> list;

    public BookPresenter(@NonNull BookContract.View view) {
        mView = checkNotNull(view, "BookContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public ArrayList<BookBean> getData() {
        if (null == list)
            list = new ArrayList<>();
        return list;
    }

    @Override
    public void loadBookData() {
        FriendAction.getFriendList(new FriendController.GetFriendListener() {
            @Override
            public void success(ArrayList<BookBean> bookBeanArrayList) {
                list.clear();
                for (BookBean bookBean : bookBeanArrayList) {
                    list.add(bookBean);
                }
                mView.notifyDataSetChanged();
            }

            @Override
            public void error(int code, String message) {
                mView.showSnackbarMessage(message);
            }
        });
    }


    @Override
    public void start() {
        loadBookData();
    }
}
