package info.smemo.nowordschat.activity;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.adapter.SelectImageAdapter;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.contract.SelectImageContract;
import info.smemo.nowordschat.databinding.ActivitySelectImageBinding;
import info.smemo.nowordschat.presenter.SelectImagePresenter;

public class SelectImagesActivity extends BaseCompatActivity implements SelectImageContract.View {

    private ActivitySelectImageBinding binding;
    private SelectImagePresenter presenter;

    private SelectImageAdapter adapter;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_select_image);
        binding.toolbar.setTitle("图片");
        setSupportActionBar(binding.toolbar);
        setToolbarFinish(binding.toolbar);

        presenter = new SelectImagePresenter(this);

        adapter = new SelectImageAdapter(presenter.getData());
        binding.imageList.setLayoutManager(new GridLayoutManager(this, 3));
        binding.imageList.setAdapter(adapter);

        binding.photoBarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlbumListShow();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
        this.dismissProgressDialog();
    }

    @Override
    public void setAlbumListShow() {
        binding.photoSelect.setVisibility(binding.photoSelect.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPresenter(SelectImageContract.Presenter presenter) {

    }
}
