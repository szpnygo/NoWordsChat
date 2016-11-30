package info.smemo.nbaseaction.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import info.smemo.nbaseaction.bean.CommonJson;
import info.smemo.nbaseaction.bean.CommonJsonList;
import info.smemo.nbaseaction.http.HttpBuilder;
import info.smemo.nbaseaction.http.HttpUtil;
import info.smemo.nbaseaction.util.ThreadUtil;
import okhttp3.CacheControl;

import static info.smemo.nbaseaction.app.AppError.ERROR_DATA_ERROR;

public class NBaseAction extends HttpUtil {
    public static <T> HttpBuilder get(@NonNull Class<T> clazz, @NonNull String url, @Nullable HttpActionDataListener<T> dataListener) {
        return request(clazz, HttpType.GET, url, null, null, null, true, null, null, dataListener, null);
    }

    public static <T> HttpBuilder get(@NonNull Class<T> clazz, @NonNull String url, @Nullable HttpActionListListener<List<T>> listListener) {
        return request(clazz, HttpType.GET, url, null, null, null, true, null, null, null, listListener);
    }

    public static <T> HttpBuilder get(@NonNull Class<T> clazz, @NonNull String url, @Nullable HashMap<String, String> map,
                                      @Nullable HttpActionDataListener<T> dataListener) {
        return request(clazz, HttpType.GET, url, map, null, null, true, null, null, dataListener, null);
    }

    public static <T> HttpBuilder get(@NonNull Class<T> clazz, @NonNull String url, @Nullable HashMap<String, String> map,
                                      @Nullable HttpActionListListener<List<T>> listListener) {
        return request(clazz, HttpType.GET, url, map, null, null, true, null, null, null, listListener);
    }

    public static <T> HttpBuilder post(@NonNull Class<T> clazz, @NonNull String url, @Nullable HashMap<String, Object> map,
                                       @Nullable HttpActionDataListener<T> dataListener) {
        return request(clazz, HttpType.POST, url, null, map, null, true, null, null, dataListener, null);
    }

    public static <T> HttpBuilder post(@NonNull Class<T> clazz, @NonNull String url, @Nullable HashMap<String, Object> map,
                                       @Nullable HttpActionListListener<List<T>> listListener) {
        return request(clazz, HttpType.POST, url, null, map, null, true, null, null, null, listListener);
    }


    public static <T> HttpBuilder request(@NonNull final Class<T> clazz, @Nullable final HttpActionListListener<List<T>> listListener) {
        return request(clazz, HttpType.POST, null, null, null, null, true, null, null, null, listListener);

    }

    public static <T> HttpBuilder request(@NonNull final Class<T> clazz, @Nullable final HttpActionDataListener<T> dataListener) {
        return request(clazz, HttpType.POST, null, null, null, null, true, null, null, dataListener, null);
    }

    public static <T> HttpBuilder request(@NonNull final Class<T> clazz, @Nullable HttpType httpType, @Nullable String url,
                                          @Nullable HashMap<String, String> map, @Nullable HashMap<String, Object> datas, @Nullable HashMap<String, String> header, boolean isCookie,
                                          @Nullable CacheControl cacheControl, @Nullable final HttpDataAction dataAction,
                                          @Nullable final HttpActionDataListener<T> dataListener,
                                          @Nullable final HttpActionListListener<List<T>> listListener) {
        HttpBuilder builder = HttpUtil.newBuilder();
        builder.setHttpType(httpType)
                .setUrl(url)
                .setHeaderMap(header)
                .setGetMap(map)
                .setPostMap(datas)
                .setCookie(isCookie)
                .setCacheControl(cacheControl)
                .setHttpDataAction(dataAction)
                .setListener(new HttpDataListener() {
                    @Override
                    public void success(String response) {
                        try {

                            if (dataListener != null) {
                                final CommonJson<T> commonJson = fromJson(response, clazz);
                                ThreadUtil.newThreadMain(new ThreadUtil.ThreadRunnableMain() {
                                    @Override
                                    public void inMain() {
                                        dataListener.success(commonJson.data);
                                    }
                                });

                            } else if (listListener != null) {
                                final CommonJsonList<T> commonJsonList = fromJsonList(response, clazz);
                                ThreadUtil.newThreadMain(new ThreadUtil.ThreadRunnableMain() {
                                    @Override
                                    public void inMain() {
                                        listListener.success(commonJsonList.data);
                                    }
                                });
                            }
                        } catch (Exception e) {
                            if (null != dataListener)
                                dataListener.error(ERROR_DATA_ERROR, "NBaseAction HttpData:" + e.getMessage(), false);
                            if (null != listListener)
                                listListener.error(ERROR_DATA_ERROR, "NBaseAction HttpData:" + e.getMessage(), false);
                        }
                    }

                    @Override
                    public void error(final int code, final String message) {
                        ThreadUtil.newThreadMain(new ThreadUtil.ThreadRunnableMain() {
                            @Override
                            public void inMain() {
                                if (null != dataListener) dataListener.error(code, message, true);
                                if (null != listListener) listListener.error(code, message, true);
                            }
                        });
                    }
                });
        return builder;
    }


    public static HttpBuilder get(@NonNull String url, @Nullable HashMap<String, String> map, @NonNull final HttpActionStringListener listener) {
        return request(HttpType.GET, url, map, null, null, true, null, null, listener);
    }

    public static HttpBuilder post(@NonNull String url, @Nullable HashMap<String, Object> map, @NonNull final HttpActionStringListener listener) {
        return request(HttpType.POST, url, null, map, null, true, null, null, listener);
    }

    public static HttpBuilder request(@NonNull HttpType httpType, @NonNull String url,
                                      @Nullable HashMap<String, String> map, @Nullable HashMap<String, Object> datas, @Nullable HashMap<String, String> header, boolean isCookie,
                                      @Nullable CacheControl cacheControl, @Nullable final HttpDataAction dataAction,
                                      @NonNull final HttpActionStringListener listener) {
        HttpBuilder builder = HttpUtil.newBuilder();
        builder.setHttpType(httpType)
                .setUrl(url)
                .setHeaderMap(header)
                .setGetMap(map)
                .setPostMap(datas)
                .setCookie(isCookie)
                .setCacheControl(cacheControl)
                .setHttpDataAction(dataAction)
                .setListener(new HttpDataListener() {
                    @Override
                    public void success(String response) {
                        listener.success(response);
                    }

                    @Override
                    public void error(final int code, final String message) {
                        ThreadUtil.newThreadMain(new ThreadUtil.ThreadRunnableMain() {
                            @Override
                            public void inMain() {
                                listener.error(code, message, true);
                            }
                        });
                    }
                });
        return builder;
    }

    public static <T> T fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(CommonJson.class, clazz);
        return gson.fromJson(json, objectType);
    }

    public static <T> T fromJsonList(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(CommonJsonList.class, clazz);
        return gson.fromJson(json, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }


    public interface HttpActionDataListener<T> {
        void success(T response);

        //UI线程
        void error(int code, String message, boolean inMain);
    }

    public interface HttpActionListListener<T> {
        void success(T response);

        //UI线程
        void error(int code, String message, boolean inMain);
    }

    public interface HttpActionStringListener {
        void success(String response);

        //UI线程
        void error(int code, String message, boolean inMain);
    }


    public static boolean isMainThread() {
        return Thread.currentThread().getName().contains("main");
    }
}
