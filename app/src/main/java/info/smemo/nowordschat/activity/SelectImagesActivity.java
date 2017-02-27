package info.smemo.nowordschat.activity;

import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.databinding.ActivitySelectImageBinding;

public class SelectImagesActivity extends BaseCompatActivity {

    private ActivitySelectImageBinding binding;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_select_image);
        binding.toolbar.setTitle("图片");
        setSupportActionBar(binding.toolbar);
        setToolbarFinish(binding.toolbar);
    }
}
