package info.smemo.nbaseaction.base;

import android.app.Application;
import android.content.Context;
import android.view.View;

public interface NBaseCommonView {

    boolean isActive();

    void showProgressDialog(String title);

    void showProgressDialogInThread(String title);

    void dismissProgressDialog();

    void destoryProgressDialog();

    void showMessage(String title, String message);

    void showMessage(String title, String message, final View.OnClickListener okClickListener, final View.OnClickListener cancelListener);

    void showToastMessage(String message);

    Context getVContext();

    Context getVApplicationContext();

    Application getVApplication();


}
