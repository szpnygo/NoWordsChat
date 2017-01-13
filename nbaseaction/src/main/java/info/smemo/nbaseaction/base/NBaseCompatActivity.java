package info.smemo.nbaseaction.base;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import info.smemo.nbaseaction.app.AppConstant;
import info.smemo.nbaseaction.app.AppManager;
import info.smemo.nbaseaction.ui.MaterialDialog;
import info.smemo.nbaseaction.util.ThreadUtil;
import info.smemo.nbaseaction.util.view.ViewInjectUtils;

public class NBaseCompatActivity extends AppCompatActivity implements AppConstant, NBaseCommonView {

    protected ProgressDialog mProgressDialog;
    private MaterialDialog mMessageDialog;

    protected final BaseHandler mBaseHandler = new BaseHandler(this);

    private static final int SHOW_PROGRESS_DIALOG = 0x110001;

    protected Toolbar mToolbar;

    protected void onCreateDataBinding() {

    }

    protected <T extends ViewDataBinding> T createContentView(int layout) {
        return DataBindingUtil.setContentView(this, layout);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateDataBinding();
        AppManager.getAppManager().addActivity(this);
        ViewInjectUtils.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
        destoryProgressDialog();
        mProgressDialog = null;
    }

    protected Toolbar setToolBar(int id) {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(id);
            setSupportActionBar(mToolbar);
        }
        return mToolbar;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    /**
     * show a progress dialog
     *
     * @param title progress notice message
     */
    @Override
    public void showProgressDialog(String title) {
        if (null == mProgressDialog) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
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

    /**
     * dismiss progress dialog
     */
    @Override
    public void dismissProgressDialog() {
        NBaseCompatActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                destoryProgressDialog();
            }
        });
    }

    @Override
    public void destoryProgressDialog() {
        if (null != mProgressDialog && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                showProgressDialog(msg.getData().getString("title"));
                break;
        }
    }

    protected static class BaseHandler extends Handler {

        private final WeakReference<NBaseCompatActivity> mBaseActivityWeakReference;

        public BaseHandler(NBaseCompatActivity baseActivity) {
            super();
            mBaseActivityWeakReference = new WeakReference<>(baseActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            NBaseCompatActivity baseActivity = mBaseActivityWeakReference.get();
            if (null != baseActivity) {
                baseActivity.handleMessage(msg);
            }
        }
    }

    @Override
    public void showMessage(String title, String message) {
        showMessage(title, message, null, null);
    }

    @Override
    public void showMessage(String title, String message, final View.OnClickListener okClickListener, final View.OnClickListener cancelListener) {
        if (null == mMessageDialog)
            mMessageDialog = new MaterialDialog(this);
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
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getVContext() {
        return this;
    }

    @Override
    public Context getVApplicationContext() {
        return getApplicationContext();
    }

    @Override
    public Application getVApplication() {
        return getApplication();
    }

    @Override
    public void finishSelf() {
        finish();
    }

    protected void injectView() {
        ViewInjectUtils.inject(this);
    }

    protected void showSnackbar(final String message, @NonNull final View view) {
        ThreadUtil.newThreadMain(new ThreadUtil.ThreadRunnableMain() {
            @Override
            public void inMain() {
                Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
            }
        });

    }

    protected void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
