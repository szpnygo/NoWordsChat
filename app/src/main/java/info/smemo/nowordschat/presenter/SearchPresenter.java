package info.smemo.nowordschat.presenter;

import java.util.ArrayList;

import info.smemo.nowordschat.appaction.bean.FriendBean;
import info.smemo.nowordschat.appaction.controller.FriendController;
import info.smemo.nowordschat.contract.SearchContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;

    private ArrayList<FriendBean> list;

    private int indexPage = 0;

    public SearchPresenter(SearchContract.View view) {
        mView = checkNotNull(view, "SearchContract.View cannot be null");
        mView.setPresenter(this);
    }

    @Override
    public void search(String nickname) {
        mView.startLoading();
        FriendController.searchUser(nickname, indexPage, new FriendController.SearchUserListener() {
            @Override
            public void error(int code, String message) {
                mView.stopLoading();
                mView.showSnackbarMessage(message);
            }

            @Override
            public void success(ArrayList<FriendBean> list) {
                list.clear();
                for (FriendBean bean : list) {
                    list.add(bean);
                }
                mView.notifyDataSetChanged();
                mView.stopLoading();
            }
        });
    }

    @Override
    public ArrayList<FriendBean> getData() {
        if (null == list)
            list = new ArrayList<>();
        return list;
    }

    @Override
    public void start() {

    }
}
