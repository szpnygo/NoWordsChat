package info.smemo.nowordschat.util;

import info.smemo.nbaseaction.util.TimeUtil;
import info.smemo.nowordschat.app.AppConstant;

public class TimeHelper implements AppConstant {

    public static String getTimePassStr(long time) {
        long timePass = System.currentTimeMillis() - time;
        if (timePass < MINUTE) {
            return "刚刚";
        } else if (timePass < 60 * MINUTE) {
            return ((int) (timePass / MINUTE)) + "分钟前";
        } else if (timePass < 6 * HOUR) {
            return ((int) (timePass / HOUR)) + "小时前";
        } else if (timePass < DAY) {
            return TimeUtil.getFormatTime(String.valueOf(time), "HH:mm");
        } else {
            return TimeUtil.getFormatTime(String.valueOf(time), "MM-dd HH:mm");
        }
    }
}
