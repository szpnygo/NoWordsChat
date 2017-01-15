package info.smemo.nowordschat.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

import info.smemo.nowordschat.action.FriendAction;
import info.smemo.nowordschat.appaction.bean.BookBean;
import info.smemo.nowordschat.appaction.controller.FriendController;
import info.smemo.nowordschat.contract.BookContract;
import info.smemo.nowordschat.util.PinyinSortComparator;

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
    synchronized public void onRefresh() {
        FriendAction.getFriendList(new FriendController.GetFriendListener() {
            @Override
            public void success(ArrayList<BookBean> bookBeanArrayList) {
                PinyinSortComparator sortComparator = new PinyinSortComparator();
                Collections.sort(bookBeanArrayList, sortComparator);
                list.clear();
                int size = bookBeanArrayList.size();
                for (int i = 0; i < size; i++) {
                    BookBean bean = bookBeanArrayList.get(i);
                    //如果是第一个
                    if (i == 0) {
                        //如果有第二个
                        if (size > 1) {
                            //如果第二个的标题不等于第一个，显示下划线
                            if (!bean.firstChar.equals(bookBeanArrayList.get(i + 1).getFirstChar())) {
                                bean.setShowLine(true);
                            }
                        }
                    } else {
                        //如果等于上一个，隐藏
                        if (bean.firstChar.equals(bookBeanArrayList.get(i - 1).getFirstChar())) {
                            bean.setTitle("");
                        }
                        //如果是最后一个，或者不等于下一个，显示线
                        if (i == size - 1 || !bean.firstChar.equals(bookBeanArrayList.get(i + 1).getFirstChar())) {
                            bean.setShowLine(true);
                        }
                    }
                    list.add(bean);
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
        onRefresh();
    }
}
