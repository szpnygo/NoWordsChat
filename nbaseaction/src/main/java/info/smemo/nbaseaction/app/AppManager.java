package info.smemo.nbaseaction.app;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.NoSuchElementException;
import java.util.Stack;


public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {

    }

    /**
     * 单一实例
     */
    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中压入的）
     */
    public Activity currentActivity() {
        if (activityStack == null) {
            return null;
        }
        try {
            Activity activity = activityStack.lastElement();
            return activity;
        } catch (NoSuchElementException exe) {
            if (activityStack.size() > 2) {
                return activityStack.get(activityStack.size() - 2);
            } else {
                System.exit(0);
                return null;
            }
        }
    }

    /**
     * 结束当前Activity（堆栈中压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        Activity finishAct = null;
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishAct = activity;
                break;
            }
        }
        finishActivity(finishAct);
    }

    public boolean activityRunning(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param cls
     * @return
     */
    public Activity getActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void finishAllActivity(Class<?> cls, boolean first) {
        Stack<Activity> tmpStack = new Stack<>();
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i) && activityStack.get(i).getClass().equals(cls)) {
                tmpStack.add(activityStack.get(i));
            }
        }
        if (tmpStack.size() <= 1) {
            return;
        }
        int num = 0;
        if (first)
            num = 1;
        for (int j = 0; j < tmpStack.size() - num; j++) {
            tmpStack.get(j).finish();
        }
        tmpStack.clear();
    }

    /**
     * finish掉所有的activity 除了传入的activity
     *
     * @param activity
     */
    public void finishActivitysExceptUi(Activity activity) {
        if (activity == null) {
            return;
        }
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i) && activity != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
        activityStack.add(activity);
    }

    public void finishActivitysExceptUi(Class<?> cls) {
        Activity activity = null;
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                if (!activityStack.get(i).getClass().equals(cls)) {
                    activityStack.get(i).finish();
                } else {
                    activity = activityStack.get(i);
                }
            }
        }
        activityStack.clear();
        if (activity != null)
            activityStack.add(activity);
    }

    /**
     * 应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}