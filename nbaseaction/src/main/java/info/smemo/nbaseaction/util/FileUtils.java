package info.smemo.nbaseaction.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

    public static final long B = 1;
    public static final long KB = B * 1024;
    public static final long MB = KB * 1024;
    public static final long GB = MB * 1024;

    public static void saveBitmap(String filePath, byte[] bytes) {
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(bytes);
            fops.flush();
            fops.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     */
    public static String readFile(File file) throws IOException {
        String text = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            text = readTextInputStream(is);
        } finally {
            if (is != null)
                is.close();
        }
        return text;
    }

    /**
     * 从流中读取文件
     */
    public static String readTextInputStream(InputStream is) throws IOException {
        StringBuffer strbuffer = new StringBuffer();
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is));
            while ((line = reader.readLine()) != null) {
                strbuffer.append(line).append("\r\n");
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return strbuffer.toString();
    }

    /**
     * 将文本内容写入文件
     */
    public static void writeTextFile(File file, String str) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new FileOutputStream(file));
            out.write(str.getBytes());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 获取一个文件夹大小
     */
    public static long getFileSize(File f) {
        long size = 0;
        File listFiles[] = f.listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                size = size + getFileSize(listFiles[i]);
            } else {
                size = size + listFiles[i].length();
            }
        }
        return size;
    }

    /**
     * 格式化文件大小 带有单位
     */
    public static String formatFileSize(long size) {
        StringBuilder sb = new StringBuilder();
        String u = null;
        double tmpSize = 0;
        if (size < KB) {
            sb.append(size).append("B");
            return sb.toString();
        } else if (size < MB) {
            tmpSize = getSize(size, KB);
            u = "KB";
        } else if (size < GB) {
            tmpSize = getSize(size, MB);
            u = "MB";
        } else {
            tmpSize = getSize(size, GB);
            u = "GB";
        }
        return sb.append(twodot(tmpSize)).append(u).toString();
    }

    /**
     * 保留两位小数
     */
    @SuppressLint("DefaultLocale")
    public static String twodot(double d) {
        return String.format("%.2f", d);
    }

    public static double getSize(long size, long u) {
        return (double) size / (double) u;
    }

    /**
     * sd卡挂载且可用
     */
    public static boolean isSdCardMounted() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取手机在sdcard保存cache的地址
     */
    public static String getAbsolutePath(Context context) {
        File root = context.getCacheDir();
        // 返回手机端的绝对路径，当我们软件卸载，以及清理缓存时会被清理掉
        if (root != null)
            return root.getAbsolutePath();
        return null;
    }

    /**
     * 递归创建文件目录
     */
    public static void CreateDir(String path) {
        if (!isSdCardMounted())
            return;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                Log.e("hulutan", "error on creat dirs:" + e.getStackTrace());
            }
        }
    }


    /**
     * 添加到本地缓存当中
     */
    public void saveBitmap(String path, String name, Bitmap bitmap) {
        if (bitmap == null)
            return;
        // 如果sdcard不能使用
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_UNMOUNTED)) {
            return;
        }
        // 拼接图片要保存到sd卡的地址
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        File imgFile = new File(f, name);
        try {
            FileOutputStream fos = new FileOutputStream(imgFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
