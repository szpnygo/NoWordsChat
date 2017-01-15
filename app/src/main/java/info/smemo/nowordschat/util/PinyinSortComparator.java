package info.smemo.nowordschat.util;

import java.util.Comparator;

import info.smemo.nowordschat.appaction.bean.BookBean;

public class PinyinSortComparator implements Comparator<BookBean> {

    @Override
    public int compare(BookBean o1, BookBean o2) {
        return (int) o1.getTitle().charAt(0) > (int) o2.getTitle().charAt(0) ? 1 : -1;
    }
}
