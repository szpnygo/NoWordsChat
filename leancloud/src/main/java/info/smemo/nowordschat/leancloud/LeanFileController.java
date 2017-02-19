package info.smemo.nowordschat.leancloud;

import android.support.annotation.NonNull;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.ProgressCallback;
import com.avos.avoscloud.SaveCallback;

import java.io.FileNotFoundException;

public class LeanFileController {

    public static AVFile createFile(String filename, String path) throws FileNotFoundException {
        return AVFile.withAbsoluteLocalPath(filename, path);
    }

    public static AVFile createFile(String filename, byte[] data) {
        return new AVFile(filename, data);
    }

    public static void uploadFile(@NonNull final AVFile file, @NonNull final UploadFileListener listener) {
        file.saveInBackground(new SaveCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    listener.success(file.getUrl());
                } else {
                    listener.error(e.getCode(), e.getMessage());
                }
            }
        }, new ProgressCallback() {
            @Override
            public void done(Integer integer) {
                listener.progress(integer);
            }
        });
    }

    public interface UploadFileListener {

        void success(String url);

        void error(int code, String message);

        void progress(int progress);

    }
}
