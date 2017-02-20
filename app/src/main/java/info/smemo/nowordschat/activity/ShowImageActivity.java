package info.smemo.nowordschat.activity;

import android.graphics.drawable.Animatable;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;

import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nowordschat.R;
import info.smemo.nowordschat.base.BaseCompatActivity;
import info.smemo.nowordschat.databinding.ActivityShowImageBinding;

public class ShowImageActivity extends BaseCompatActivity {

    private ActivityShowImageBinding binding;

    @Override
    protected void onCreateDataBinding() {
        super.onCreateDataBinding();
        binding = createContentView(R.layout.activity_show_image);
        setSupportActionBar(binding.toolbar);
        setToolbarFinish(binding.toolbar);

        String thumb = getIntent().getStringExtra("Thumb");
        String large = getIntent().getStringExtra("Large");
        String original = getIntent().getStringExtra("Original");
        if (StringUtil.isEmpty(thumb) && StringUtil.isEmpty(large) && StringUtil.isEmpty(original)) {
            finish();
        }

        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
        if (!StringUtil.isEmpty(thumb))
            controller.setLowResImageRequest(ImageRequest.fromUri(thumb));
        if (!StringUtil.isEmpty(large))
            controller.setImageRequest(ImageRequest.fromUri(large));
        if (!StringUtil.isEmpty(original))
            controller.setImageRequest(ImageRequest.fromUri(large));

        controller.setOldController(binding.photoView.getController());
        controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if (imageInfo == null || binding.photoView == null) {
                    return;
                }
                binding.photoView.update(imageInfo.getWidth(), imageInfo.getHeight());
            }
        });
        binding.photoView.setController(controller.build());
    }
}
