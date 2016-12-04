package info.smemo.nbaseaction.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSubscriber;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

import info.smemo.nbaseaction.base.NBaseApplication;

public class FrescoUtil {

    /**
     * 加载图片
     *
     * @param view
     * @param url  图片URI
     */
    public static void setImageUri(SimpleDraweeView view, String url) {
        view.setImageURI(Uri.parse(url));
    }

    public static void setImageUri(SimpleDraweeView view, Uri uri) {
        view.setImageURI(uri);
    }

    /**
     * 加载多级图片
     *
     * @param smallImg 小图
     * @param bigImg   大图
     */
    public static void loadLowImage(SimpleDraweeView simpleDraweeView, String smallImg, String bigImg) {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setLowResImageRequest(getImageRequest(smallImg))
                .setImageRequest(getImageRequest(bigImg))
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 展示指定大小
     */
    public static void loadImageWithFixSize(SimpleDraweeView simpleDraweeView, String uri, int width, int height) {
        loadImageWithFixSize(simpleDraweeView, Uri.parse(uri), width, height);
    }

    public static void loadImageWithFixSize(SimpleDraweeView simpleDraweeView, Uri uri, int width, int height) {
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 获取文件缓存
     */
    public static File getFileCache(String uri) {
        ImageRequest imageRequest = ImageRequest.fromUri(uri);
        CacheKey cacheKey = DefaultCacheKeyFactory.getInstance().getEncodedCacheKey(imageRequest, null);
        BinaryResource resource = ImagePipelineFactory.getInstance()
                .getMainFileCache().getResource(cacheKey);
        return ((FileBinaryResource) resource).getFile();

    }

    /**
     * 是否本地文件缓存
     */
    public static void isDiskCache(String uri, @NonNull final IsImageFileCachedListener listener) {
        DataSource<Boolean> inDiskCacheSource = Fresco.getImagePipeline()
                .isInDiskCache(getUri(uri));
        DataSubscriber<Boolean> subscriber = new BaseDataSubscriber<Boolean>() {
            @Override
            protected void onNewResultImpl(DataSource<Boolean> dataSource) {
                if (!dataSource.isFinished()) {
                    listener.cached(false);
                    return;
                }
                listener.cached(true);

            }

            @Override
            protected void onFailureImpl(DataSource<Boolean> dataSource) {
                listener.cached(false);
            }
        };
        inDiskCacheSource.subscribe(subscriber, CallerThreadExecutor.getInstance());
    }

    /**
     * 获取bitmap
     */
    public static void getBitmap(ImageRequest request, @NonNull BaseBitmapDataSubscriber subscriber) {
        DataSource<CloseableReference<CloseableImage>> dataSource = Fresco.getImagePipeline().fetchDecodedImage(request, NBaseApplication.getContext());
        dataSource.subscribe(subscriber, CallerThreadExecutor.getInstance());
    }

    public static void getBitmap(ImageRequest request, @NonNull final DatasourceBitmapListener listener) {
        DataSource<CloseableReference<CloseableImage>> dataSource = Fresco.getImagePipeline().fetchDecodedImage(request, NBaseApplication.getContext());
        dataSource.subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() {
            @Override
            protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (!dataSource.isFinished()) {
                    listener.onNewResultImpl(null, null);
                    return;
                }
                CloseableReference<CloseableImage> imageCloseableReference = dataSource.getResult();
                Bitmap bitmap = null;
                if (imageCloseableReference != null) {
                    CloseableReference<CloseableImage> referenceClone = imageCloseableReference.clone();
                    try {
                        CloseableImage closeableImage = referenceClone.get();
                        if (closeableImage instanceof CloseableBitmap) {
                            bitmap = ((CloseableBitmap) closeableImage).getUnderlyingBitmap();
                            listener.onNewResultImpl(bitmap, referenceClone);
                        }
                    } finally {
                        imageCloseableReference.close();
                    }
                }

            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {

            }
        }, CallerThreadExecutor.getInstance());
    }

    public static void closeReference(CloseableReference<?> ref) {
        CloseableReference.closeSafely(ref);
    }

    public static BitmapDrawable createBitmapDrawable(Context context, Bitmap bitmap) {
        BitmapDrawable drawable;
        if (context != null) {
            drawable = new BitmapDrawable(context.getResources(), bitmap);
        } else {
            // can't happen
            drawable = new BitmapDrawable(null, bitmap);
        }
        return drawable;
    }

    /**
     * 是否在内存缓存中
     */
    public static boolean isMemCache(String uri) {
        return Fresco.getImagePipeline().isInBitmapMemoryCache(getUri(uri));
    }

    /**
     * 清理缓存
     */
    public static void clearCache() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
    }

    /**
     * 删除某条缓存
     */
    public static void removeCache(String uri) {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        Uri delUri = getUri(uri);
        imagePipeline.evictFromDiskCache(delUri);
        imagePipeline.evictFromMemoryCache(delUri);
    }

    public static Uri getUri(String uri) {
        return Uri.parse(uri);
    }

    public static ImageRequest getImageRequest(String uri) {
        return ImageRequest.fromUri(uri);
    }


    public interface IsImageFileCachedListener {

        void cached(boolean isCached);
    }

    public interface DatasourceBitmapListener {

        void onNewResultImpl(Bitmap bitmap, CloseableReference<CloseableImage> reference);
    }
}
