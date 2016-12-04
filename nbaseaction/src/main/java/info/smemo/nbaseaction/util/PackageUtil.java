package info.smemo.nbaseaction.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class PackageUtil {

    public static boolean checkPermission(Context context, String permission, String packagename) {
        PackageManager pm = context.getPackageManager();
        return (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permission, packagename));
    }


    /**
     * 开启系统默认安装程序
     *
     * @param filePath
     */
    public static synchronized void openInstallAction(Context context,
                                                      String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(Uri.parse("file://" + file.toString()),
                    "application/vnd.android.package-archive");
            context.startActivity(i);
        }
    }

    /**
     * 获取APK信息
     *
     * @param context
     * @param packageName
     * @return
     */
    public static PackageInfo getPackage(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            return null;
        }
        return info;
    }

    /**
     * 获取系统安装的APK列表
     *
     * @param context
     * @return
     */
    public static List<PackageInfo> getSystemPackageList(Context context) {
        PackageManager manager = context.getPackageManager();
        List<PackageInfo> pkgList = manager.getInstalledPackages(PackageManager.GET_SIGNATURES);
        return pkgList;
    }

    /**
     * 判断APK是否已经安装
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean checkPackageInstalled(Context context,
                                                String packageName) {
        return getPackage(context, packageName) != null;
    }

    public static PackageInfo getPackageInfo(Context context, String packageName) {
        return getPackage(context, packageName);
    }

    /**
     * 判断APK与对应versionCode是否需要升级
     *
     * @param context
     * @param packageName
     * @param version
     * @return
     */
    public static boolean checkPackageUpdate(Context context,
                                             String packageName, int version) {
        return getPackage(context, packageName).versionCode < version;
    }

    /**
     * 通过比较一个APK文件路径判断是否需要升级
     *
     * @param context
     * @param packageName
     * @param path
     * @return
     */
    public static boolean checkPackageUpdateByPath(Context context,
                                                   String packageName, String path) {
        PackageInfo packageInfo = getPackageByPath(context, path);
        return (packageInfo != null) && checkPackageUpdate(context, packageName, packageInfo.versionCode);
    }

    /**
     * 通过比较一个APK文件判断是否需要升级
     *
     * @param context
     * @param packageName
     * @param file
     * @return
     */
    public static boolean checkPackageUpdateByFile(Context context,
                                                   String packageName, File file) {
        PackageInfo packageInfo = getPackageByFile(context, file);
        return (packageInfo != null) && checkPackageUpdate(context, packageName, packageInfo.versionCode);
    }

    /**
     * 获取APK信息通过文件路径
     *
     * @param context
     * @param filePath
     * @return
     */
    public static PackageInfo getPackageByPath(Context context, String filePath) {
        return getPackageByFile(context, new File(filePath));
    }

    /**
     * 获取APK信息通过文件
     *
     * @param context
     * @param apkFile
     * @return
     */
    public static PackageInfo getPackageByFile(Context context, File apkFile) {
        if (apkFile == null)
            return null;
        String name_s = apkFile.getName();
        if (!name_s.endsWith(".apk"))
            return null;
        String apk_path = apkFile.getPath();

        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(apk_path,
                PackageManager.GET_ACTIVITIES);
        return packageInfo;
    }

    public static PackageInfo getVersionInfo(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(),
                    0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo;
    }

    public static String getSDKChannel(Context context) {
        return null;
    }

    @SuppressLint("InlinedApi")
    public static Intent getShowInstalledAppDetails(Context context,
                                                    String packageName) {
        Intent intent = new Intent();
        final int apiLevel = Build.VERSION.SDK_INT;
        if (apiLevel >= 9) { // 2.3（ApiLevel 9）以上，使用SDK提供的接口
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", packageName, null);
            intent.setData(uri);
        } else { // 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）
            // 2.2和2.1中，InstalledAppDetails使用的APP_PKG_NAME不同。
            final String appPkgName = (apiLevel == 8 ? "pkg"
                    : "com.android.settings.ApplicationPkgName");
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings",
                    "com.android.settings.InstalledAppDetails");
            intent.putExtra(appPkgName, packageName);
        }
        return intent;
    }

    public static void startAPP(Context context, String appPackageName) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "没有安装", Toast.LENGTH_LONG).show();
        }
    }

}
