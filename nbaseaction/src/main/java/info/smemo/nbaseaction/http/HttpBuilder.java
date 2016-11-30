package info.smemo.nbaseaction.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import info.smemo.nbaseaction.app.AppConstant;
import info.smemo.nbaseaction.util.StringUtil;
import info.smemo.nbaseaction.util.ThreadUtil;
import okhttp3.CacheControl;

public class HttpBuilder implements AppConstant {

    HttpUtil.HttpType httpType;
    String url;
    HashMap<String, Object> postMap;
    HashMap<String, String> getMap;
    HashMap<String, String> headerMap;
    boolean isCookie;
    CacheControl cacheControl;
    HttpUtil.HttpDataListener listener;
    HttpUtil.HttpDataAction httpDataAction;
    HttpUtil.ThreadType returnThread;


    public HttpBuilder() {
        httpType = HttpUtil.HttpType.POST;
        url = "";
        postMap = null;
        getMap = null;
        headerMap = null;
        isCookie = true;
        cacheControl = null;
        listener = null;
        httpDataAction = null;
        returnThread = HttpUtil.ThreadType.THREAD;
    }

    public HttpBuilder setHttpType(HttpUtil.HttpType httpType) {
        this.httpType = httpType;
        return this;
    }

    public HttpBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpBuilder setPostMap(HashMap<String, Object> postMap) {
        if (this.postMap != null) {
            Set<Map.Entry<String, Object>> entrySet = postMap.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                this.postMap.put(entry.getKey(), entry.getValue());
            }
            return this;
        }
        this.postMap = postMap;
        return this;
    }

    public HttpBuilder setGetMap(HashMap<String, String> getMap) {
        if (this.getMap != null) {
            Set<Map.Entry<String, String>> entrySet = getMap.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                this.getMap.put(entry.getKey(), entry.getValue());
            }
            return this;
        }
        this.getMap = getMap;
        return this;
    }

    public HttpBuilder setHeaderMap(HashMap<String, String> headerMap) {
        if (this.headerMap != null) {
            Set<Map.Entry<String, String>> entrySet = headerMap.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                this.headerMap.put(entry.getKey(), entry.getValue());
            }
            return this;
        }
        this.headerMap = headerMap;
        return this;
    }

    public HttpBuilder setCookie(boolean cookie) {
        isCookie = cookie;
        return this;
    }

    public HttpBuilder setCacheControl(CacheControl cacheControl) {
        this.cacheControl = cacheControl;
        return this;
    }

    public HttpBuilder setListener(HttpUtil.HttpDataListener listener) {
        this.listener = listener;
        return this;
    }

    public HttpBuilder setMainListener(final HttpUtil.HttpDataListener listener) {
        this.listener = new HttpUtil.HttpDataListener() {
            @Override
            public void success(final String response) {
                ThreadUtil.newThreadMain(new ThreadUtil.ThreadRunnableMain() {
                    @Override
                    public void inMain() {
                        listener.success(response);
                    }
                });
            }

            @Override
            public void error(int code, String message) {
                listener.error(code, message);
            }
        };
        return this;
    }

    public HttpBuilder setHttpDataAction(HttpUtil.HttpDataAction httpDataAction) {
        this.httpDataAction = httpDataAction;
        return this;
    }

    public HttpBuilder setHttpDataDirectlyAction() {
        this.httpDataAction = new HttpUtil.HttpDataAction() {
            @Override
            public void getData(String response, HttpUtil.HttpDataListener listener) {
                listener.success(response);
            }
        };
        return this;
    }

    public void execute(HttpUtil.HttpDataListener listener) {
        this.setListener(listener);
        doRequest();
    }

    public void execute() {
        doRequest();
    }

    private void doRequest() {
        if (listener == null)
            throw new RuntimeException("没有设置setListener");
        if (StringUtil.isEmpty(url))
            throw new RuntimeException("请求url为空");
        HttpUtil.request(this);
    }


    public HttpBuilder addHeader(String key, String value) {
        if (this.headerMap == null)
            this.headerMap = new HashMap<>();
        this.headerMap.put(key, value);
        return this;
    }

    public HttpBuilder addQuery(String key, String value) {
        if (this.getMap == null)
            this.getMap = new HashMap<>();
        this.getMap.put(key, value);
        return this;
    }

    public HttpBuilder addPost(String key, Object object) {
        if (this.postMap == null)
            this.postMap = new HashMap<>();
        this.postMap.put(key, object);
        return this;
    }

    public HttpBuilder addCookies(String key, String value) {
        if (this.headerMap == null)
            this.headerMap = new HashMap<>();
        String cookie = StringUtil.isEmpty(this.headerMap.get("Cookie")) ? "" : this.headerMap.get("Cookie");
        this.headerMap.put("Cookie", cookie + key + ":" + value + ";");
        return this;
    }

}
