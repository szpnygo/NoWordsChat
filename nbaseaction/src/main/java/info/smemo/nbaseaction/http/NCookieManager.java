package info.smemo.nbaseaction.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class NCookieManager {

    private static NCookieManager instance;

    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

    public static NCookieManager getInstance() {
        if (instance == null)
            instance = new NCookieManager();
        return instance;
    }

    public void deleteCookie(HttpUrl url) {
        List<Cookie> cookies = cookieStore.get(url);
        if (cookies != null)
            cookies.remove(url);

    }

    public CookieJar getCookieJar() {
        return mCookieJar;
    }

    CookieJar mCookieJar = new CookieJar() {
        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            cookieStore.put(url, cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    };
}
