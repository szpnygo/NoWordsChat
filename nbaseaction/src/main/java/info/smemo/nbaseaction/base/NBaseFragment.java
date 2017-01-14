package info.smemo.nbaseaction.base;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import info.smemo.nbaseaction.app.AppConstant;
import info.smemo.nbaseaction.ui.MaterialDialog;

public class NBaseFragment extends Fragment implements AppConstant, NBaseCommonView {

    protected FragmentManager mFragmentManager;

    protected ProgressDialog mProgressDialog;
    private MaterialDialog mMessageDialog;

    protected final BaseHandler mBaseHandler = new BaseHandler(this);

    private static final int SHOW_PROGRESS_DIALOG = 0x110001;
    private static final int DISMISS_PROGRESS_DIALOG = 0X110002;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentManager = getChildFragmentManager();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destoryProgressDialog();
        mProgressDialog = null;
    }

    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                showProgressDialog(msg.getData().getString("title"));
                break;
            case DISMISS_PROGRESS_DIALOG:
                destoryProgressDialog();
                break;
        }
    }

    protected static class BaseHandler extends Handler {

        private final WeakReference<NBaseFragment> mBaseFragmentWeakReference;

        public BaseHandler(NBaseFragment baseFragment) {
            super();
            mBaseFragmentWeakReference = new WeakReference<>(baseFragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NBaseFragment baseFragment = mBaseFragmentWeakReference.get();
            if (null != baseFragment) {
                baseFragment.handleMessage(msg);
            }
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showProgressDialog(String title) {
        if (null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setMessage(title);
        }
        mProgressDialog.setMessage(title);
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void showProgressDialogInThread(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        Message message = new Message();
        message.what = SHOW_PROGRESS_DIALOG;
        message.setData(bundle);
        mBaseHandler.sendMessage(message);
    }

    @Override
    public void dismissProgressDialog() {
        Message message = new Message();
        message.what = DISMISS_PROGRESS_DIALOG;
        mBaseHandler.sendMessage(message);
    }

    @Override
    public void destoryProgressDialog() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showSnackbarMessage(String message) {

    }

    @Override
    public void showMessage(String title, String message) {
        showMessage(title, message, null, null);
    }

    @Override
    public void showMessage(String title, String message, final View.OnClickListener okClickListener, final View.OnClickListener cancelListener) {
        if (null == mMessageDialog)
            mMessageDialog = new MaterialDialog(getContext());
        mMessageDialog
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (okClickListener != null)
                            okClickListener.onClick(v);
                        mMessageDialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cancelListener != null)
                            cancelListener.onClick(v);
                        mMessageDialog.dismiss();
                    }
                })
                .setTitle(title)
                .setMessage(message);
        mMessageDialog.show();
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getVContext() {
        return getContext();
    }

    @Override
    public Context getVApplicationContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public Application getVApplication() {
        return getActivity().getApplication();
    }

    @Override
    public void finishSelf() {

    }

}
