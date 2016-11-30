package info.smemo.nbaseaction.http;

import java.lang.reflect.Field;
import java.util.List;

import info.smemo.nbaseaction.app.AppConstant;
import info.smemo.nbaseaction.base.NBaseAction;
import info.smemo.nbaseaction.http.annotation.HttpGetParameter;
import info.smemo.nbaseaction.http.annotation.HttpGetTarget;
import info.smemo.nbaseaction.http.annotation.HttpPostParameter;
import info.smemo.nbaseaction.http.annotation.HttpPostTarget;
import info.smemo.nbaseaction.http.annotation.HttpUnusedParam;
import info.smemo.nbaseaction.util.StringUtil;

public class BaseHttpBean implements AppConstant {

    public static final HttpUtil.HttpType HTTP_POST = HttpUtil.HttpType.POST;
    public static final HttpUtil.HttpType HTTP_GET = HttpUtil.HttpType.GET;
    public static final HttpUtil.HttpType HTTP_ERROR = HttpUtil.HttpType.ERROR;

    public String HTTP_URL;

    Class<? extends BaseHttpBean> clazz = this.getClass();

    public HttpUtil.HttpType HTTP_TYPE = HTTP_POST;

    public BaseHttpBean() {
        super();
    }

    public <T> void execute(final Class<T> clazz, final NBaseAction.HttpActionListListener<List<T>> listListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getHttpType();
                HttpBuilder builder = NBaseAction.request(clazz, listListener);
                builder.setUrl(HTTP_URL);
                try {
                    getRequestData(builder).execute();
                } catch (IllegalAccessException e) {
                    listListener.error(ERROR_DATA_ERROR, e.getMessage(), false);
                }
            }
        }).start();
    }

    public <T> void execute(final Class<T> clazz, final NBaseAction.HttpActionDataListener<T> dataListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getHttpType();
                HttpBuilder builder = NBaseAction.request(clazz, dataListener);
                builder.setUrl(HTTP_URL);
                try {
                    getRequestData(builder).execute();
                } catch (IllegalAccessException e) {
                    dataListener.error(ERROR_DATA_ERROR, e.getMessage(), false);
                }
            }
        }).start();
    }

    public <T> HttpBuilder request(Class<T> clazz, NBaseAction.HttpActionListListener<List<T>> listListener) {
        getHttpType();
        HttpBuilder builder = NBaseAction.request(clazz, listListener);
        builder.setUrl(HTTP_URL);
        try {
            return getRequestData(builder);
        } catch (IllegalAccessException e) {
            listListener.error(ERROR_DATA_ERROR, e.getMessage(), false);
        }
        return builder;
    }

    public <T> HttpBuilder request(Class<T> clazz, NBaseAction.HttpActionDataListener<T> dataListener) {
        getHttpType();
        HttpBuilder builder = NBaseAction.request(clazz, dataListener);
        builder.setUrl(HTTP_URL);
        try {
            return getRequestData(builder);
        } catch (IllegalAccessException e) {
            dataListener.error(ERROR_DATA_ERROR, e.getMessage(), false);
        }
        return builder;
    }

    public HttpBuilder getRequestData(HttpBuilder builder) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            HttpUnusedParam unusedParam = field.getAnnotation(HttpUnusedParam.class);
            if (unusedParam == null) {
                HttpPostParameter postParameter = field.getAnnotation(HttpPostParameter.class);
                HttpGetParameter getParameter = field.getAnnotation(HttpGetParameter.class);
                String key;
                if (postParameter != null) {
                    key = StringUtil.isEmpty(postParameter.value()) ? field.getName() : postParameter.value();
                    builder.addPost(key, field.get(this));
                } else if (getParameter != null) {
                    key = StringUtil.isEmpty(getParameter.value()) ? field.getName() : getParameter.value();
                    builder.addQuery(key, String.valueOf(field.get(this)));
                } else {
                    if (!field.getType().getClass().getName().endsWith("IncrementalChange") && !field.getName().equals("$change")) {
                        if (HTTP_TYPE == HTTP_POST) {
                            builder.addPost(field.getName(), field.get(this));
                        } else if (HTTP_TYPE == HTTP_GET) {
                            builder.addQuery(field.getName(), String.valueOf(field.get(this)));
                        }
                    }
                }
            }
        }
        return builder;
    }

    public void getHttpType() {
        HttpPostTarget postTarget = clazz.getAnnotation(HttpPostTarget.class);
        if (postTarget != null) {
            HTTP_URL = postTarget.value();
            HTTP_TYPE = HTTP_POST;
            return;
        }
        HttpGetTarget getTarget = clazz.getAnnotation(HttpGetTarget.class);
        if (getTarget != null) {
            HTTP_URL = getTarget.value();
            HTTP_TYPE = HTTP_GET;
            return;
        }
        HTTP_TYPE = HTTP_ERROR;
    }

}
