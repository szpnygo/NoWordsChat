package info.smemo.nowordschat.activity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import info.smemo.nowordschat.BR;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.CommonEditContract;
import info.smemo.nowordschat.databinding.ActivityEditNameBinding;

public class CommonEditActivity extends BaseCompatActivity implements CommonEditContract.View {

    public EditBean mEditBean = new EditBean("");

    private ActivityEditNameBinding binding;

    private CommonEditContract.EditDoneListener doneListener;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_edit_name);
        binding.setEditBean(mEditBean);
        binding.textInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == R.id.textInput || actionId == EditorInfo.IME_ACTION_DONE) {
                    if (null != doneListener)
                        doneListener.done();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class clazz = (Class) getIntent().getSerializableExtra("presenter");

        try {
            Method initMethod = clazz.getMethod("init", new Class[]{CommonEditContract.View.class});
            Method startMethod = clazz.getMethod("start", new Class[]{});
            Object mObject = clazz.newInstance();
            initMethod.invoke(mObject, new Object[]{this});
            startMethod.invoke(mObject, new Object[]{});

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setMessageError(String messageError) {
        binding.textLayout.setError(messageError);
    }

    @Override
    public void setEditBean(String text) {
        mEditBean.setText(text);
    }

    @Override
    public void setTitle(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        setToolbarFinish(toolbar);
    }

    @Override
    public void setCounterMaxLength(int length) {
        binding.textLayout.setCounterEnabled(true);
        binding.textLayout.setCounterMaxLength(length);
    }

    @Override
    public void setOnDoneListener(CommonEditContract.EditDoneListener listener) {
        doneListener = listener;
    }

    @Override
    public String getText() {
        return mEditBean.text;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_done:
                if (null != doneListener)
                    doneListener.done();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(CommonEditContract.Presenter presenter) {

    }

    public class EditBean extends BaseObservable {

        public String text;

        public EditBean(String text) {
            this.text = text;
        }

        @Bindable
        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
            notifyPropertyChanged(BR.text);
        }
    }
}
