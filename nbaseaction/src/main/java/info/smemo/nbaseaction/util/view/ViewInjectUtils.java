package info.smemo.nbaseaction.util.view;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewInjectUtils {

    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";
    private static final String METHOD_SET_CONTENTVIEW = "setContentView";

    public static void inject(Activity activity){
        injectContentView(activity);
        injectView(activity);
    }

    /**
     * 注解Activity setContentView
     * @param activity
     */
    public static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        // 查询类上是否存在ContentView注解
        AnnotationView contentView = clazz.getAnnotation(AnnotationView.class);
        if (contentView != null)// 存在
        {
            int contentViewLayoutId = contentView.value();
            try {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,
                        int.class);
                method.setAccessible(true);
                method.invoke(activity, contentViewLayoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注解生成
     *
     * @param activity
     */
    public static void injectView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //判断字段注解
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            AnnotationView view = field.getAnnotation(AnnotationView.class);
            if (view != null) {
                int viewId = view.value();
                if (viewId != -1) {
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID, int.class);
                        Object resView = method.invoke(activity, viewId);
                        field.setAccessible(true);
                        field.set(activity, resView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
