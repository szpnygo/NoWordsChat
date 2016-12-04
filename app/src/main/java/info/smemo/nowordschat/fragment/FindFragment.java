package info.smemo.nowordschat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.smemo.nbaseaction.base.NBaseFragment;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.activity.FindActivity;

public class FindFragment extends NBaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        return view;
    }

    public void findMenuClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.menu_find:
                getContext().startActivity(new Intent(getContext(), FindActivity.class));
                break;
            case R.id.menu_scan:
                break;
            case R.id.menu_shake:
                break;
            case R.id.menu_around:
                break;
            case R.id.menu_glass:
                break;
            case R.id.menu_mall:
                break;
        }
    }
}
