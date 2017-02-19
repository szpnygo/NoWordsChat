package info.smemo.nbaseaction.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;

import info.smemo.nbaseaction.base.NBaseCompatActivity;
import info.smemo.nbaseaction.util.ImageUtils;
import info.smemo.nbaseaction.util.PackageUtil;
import info.smemo.nbaseaction.util.PhotoUtil;


public class NPhotoActivity extends NBaseCompatActivity {

    //是否正在拍照中
    protected boolean isTakingPhoto = false;
    /**
     * 相机拍照裁剪文件
     */
    private File cameraFile;
    /**
     * 拍照后的图片大小，用于判断是否进行了裁剪
     */
    private Long imageSize = 0L;
    /**
     * 拍照后的文件路径
     */
    private String imagePath;
    /**
     * 开启相机
     */
    private final static int ACTION_TAKE_CAMERA = 11;
    /**
     * 开启图库
     */
    private final static int ACTION_TAKE_GALLERY = 12;
    private final static int ACTION_TAKE_GALLERY_CROP = 14;
    /**
     * 裁剪照片
     */
    private final static int ACTION_CUT_PHOTO = 13;

    protected boolean cropPhoto = true;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTION_TAKE_CAMERA) {
            // 拍照，判断拍照是否进行
            File file = new File(imagePath);
            //是否需要裁剪
            if (!cropPhoto) {
                takePhotoSuccess("file://" + file.getAbsolutePath(), file.getAbsolutePath());
                return;
            }
            // 保存图片大小
            imageSize = file.length();
            // 判断返回的文件是否存在
            if (file.exists()) {
                // 拍照后进行裁剪
                startActivityForResult(
                        PhotoUtil.startPhotoZoom(Uri.fromFile(cameraFile)),
                        ACTION_CUT_PHOTO);

            }
        } else if (requestCode == ACTION_TAKE_GALLERY) {
            if (data == null || data.getData() == null)
                return;
            //照片的原始资源地址
            Uri originalUri = data.getData();
            try {
                //使用ContentProvider通过URI获取原始图片
                Bitmap photo = ImageUtils.getThumbnail(this, originalUri, 800);
                if (photo != null) {
                    takePhotoSuccess(originalUri, getRealFilePath(data.getData()));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == ACTION_TAKE_GALLERY_CROP) {
            // 从图库中选择裁剪，判断裁剪是否进行
            if (cameraFile != null) {
                // 如果文件大小为0，删除该文件
                if (cameraFile.length() == 0) {
                    cameraFile.delete();
                } else {
                    takePhotoSuccess("file://" + cameraFile.getAbsolutePath(), cameraFile.getAbsolutePath());
                }
            }
        } else if (requestCode == ACTION_CUT_PHOTO) {
            // 拍照后，进行裁剪。判断是否拍照
            if (cameraFile != null) {
                // 如果文件大小为0，删除该文件
                if (cameraFile.length() == 0) {
                    cameraFile.delete();
                } else {
                    // 如果图片未进行裁剪
                    if (imageSize != cameraFile.length()) {
                        takePhotoSuccess("file://" + cameraFile.getAbsolutePath(), cameraFile.getAbsolutePath());
                    }
                }
            }
        } else {
            isTakingPhoto = false;
        }
    }

    public String getRealFilePath(Uri uri) {
        if (null == uri)
            return "";
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public void takePhotoSuccess(@NonNull Uri imageFile, @Nullable String path) {
        isTakingPhoto = false;
    }

    private void takePhotoSuccess(String imageFile, String imagePath) {
        takePhotoSuccess(Uri.parse(imageFile), imagePath);
        isTakingPhoto = false;
    }

    /**
     * 从相册中选择图片
     */
    protected void pickPhoto(boolean cropPhoto) {
        setCropPhoto(cropPhoto);
        if (!PackageUtil.checkPermission(this, "android.permission.CAMERA", this.getPackageName())) {
            checkPermission();
            return;
        }
        if (cropPhoto) {
            doCropPhotoAction();
        } else {
            doPickPhotoAction();
        }
    }

    /**
     * 拍摄图片
     */
    protected void takePhoto(boolean cropPhoto) {
        setCropPhoto(cropPhoto);
        if (!PackageUtil.checkPermission(this, "android.permission.CAMERA", this.getPackageName())) {
            checkPermission();
            return;
        }
        doTakePhotoAction();
    }

    protected static final int PERMISSION_CODE = 81;

    @TargetApi(Build.VERSION_CODES.M)
    protected void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PackageManager.PERMISSION_DENIED == checkSelfPermission(Manifest.permission.CAMERA)) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                    showMessage("提示", "授权允许调用摄像头和相册后可发送照片");
                    return;
                }
                showMessage("提示", "授权允许调用摄像头和相册后可发送照片,是否前往授权？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String list[] = {Manifest.permission.INTERNET};
                        ActivityCompat.requestPermissions(NPhotoActivity.this, list, PERMISSION_CODE);
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }


    /**
     * 直接从相册选择照片
     */
    private void doPickPhotoAction() {
        Intent openAlbumIntent = new Intent(Intent.ACTION_PICK);
        openAlbumIntent.setType("image/*");
        startActivityForResult(openAlbumIntent, ACTION_TAKE_GALLERY);
    }


    /**
     * 从图库进行图片裁剪，保存/
     */
    private void doCropPhotoAction() {
        // 创建文件存储目录
        File fileDir = new File(PHOTO_CACHE_PATH);
        try {
            // 文件目录
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            // 保存照片
            cameraFile = new File(fileDir, "cache.jpg");
            imagePath = cameraFile.getAbsolutePath();
            if (cameraFile.exists()) {
                cameraFile.delete();
            }
            // 保存的照片url
            Uri photoUri = Uri.fromFile(cameraFile);
            Intent intent = PhotoUtil
                    .getCropImageIntent(photoUri);
            // 打开图库选择
            startActivityForResult(intent, ACTION_TAKE_GALLERY_CROP);
            isTakingPhoto = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启照相并且裁剪
     */
    private void doTakePhotoAction() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 创建目录
        File fileDir = new File(PHOTO_CACHE_PATH);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        // 拍照后的路径
        cameraFile = new File(fileDir, "cache.jpg");
        if (cameraFile.exists()) {
            cameraFile.delete();
        }
        imagePath = cameraFile.getAbsolutePath();
        Uri imageCameraUri = Uri.fromFile(cameraFile);

        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                imageCameraUri);
        try {
            intent.putExtra("return-data", true);
            startActivityForResult(intent, ACTION_TAKE_CAMERA);
            isTakingPhoto = true;
        } catch (ActivityNotFoundException e) {
            // Do nothing for now
        }
    }

    public void setCropPhoto(boolean cropPhoto) {
        this.cropPhoto = cropPhoto;
    }
}
