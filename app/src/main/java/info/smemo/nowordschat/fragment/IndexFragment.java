package info.smemo.nowordschat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.smemo.nbaseaction.base.NBaseFragment;
import info.smemo.nowordschat.R;

public class IndexFragment extends NBaseFragment {

    public static IndexFragment newInstance() {
        return new IndexFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        return view;
    }
}
