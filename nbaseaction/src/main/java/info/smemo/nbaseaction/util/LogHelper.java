package info.smemo.nbaseaction.util;


import com.orhanobut.logger.Logger;

import info.smemo.nbaseaction.app.AppConstant;

public class LogHelper implements AppConstant {

    public static boolean isPrintLog = true;
    public static int METHOD_COUNT = 5;
    public static int METHOD_OFFSET = 1;

    public static void d(String tag, String message) {
        if (isPrintLog)
            return;
        Logger.init(tag).methodOffset(METHOD_OFFSET).methodCount(METHOD_COUNT);
        Logger.d(tag, message);
    }

    public static void d(String message) {
        if (isPrintLog)
            return;
        Logger.init(AppConstant.TAG).methodOffset(METHOD_OFFSET).methodCount(METHOD_COUNT);
        Logger.d(message);
    }

    public static void i(String tag, String message) {
        if (isPrintLog)
            return;
        Logger.init(tag).methodOffset(METHOD_OFFSET).methodCount(METHOD_COUNT);
        Logger.i(message);
    }

    public static void i(String message) {
        if (isPrintLog)
            return;
        Logger.init(AppConstant.TAG).methodOffset(METHOD_OFFSET).methodCount(METHOD_COUNT);
        Logger.i(message);
    }

    public static void w(String tag, String message) {
        if (isPrintLog)
            return;
        Logger.init(tag).methodOffset(METHOD_OFFSET).methodCount(METHOD_COUNT);
        Logger.w(message);
    }

    public static void w(String message) {
        if (isPrintLog)
            return;
        Logger.init(AppConstant.TAG).methodOffset(METHOD_OFFSET).methodCount(METHOD_COUNT);
        Logger.w(message);
    }

    public static void e(String tag, String message) {
        if (isPrintLog)
            return;
        Logger.init(tag).methodOffset(METHOD_OFFSET).methodCount(METHOD_COUNT);
        Logger.e(message);
    }

    public static void e(String message) {
        if (isPrintLog)
            return;
        Logger.init(AppConstant.TAG).methodOffset(METHOD_OFFSET).methodCount(METHOD_COUNT);
        Logger.e(message);
    }

}
