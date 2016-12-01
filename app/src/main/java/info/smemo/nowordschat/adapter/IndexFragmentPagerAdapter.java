package info.smemo.nowordschat.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import info.smemo.nowordschat.fragment.PageFragment;

public class IndexFragmentPagerAdapter extends FragmentPagerAdapter {

    public final int COUNT = 3;
    private String[] titles = new String[]{"消息", "联系人", "发现"};
    private Context context;

    public IndexFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }
    @Override
    public int getCount() {
        return COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
