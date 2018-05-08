package com.vv.mvc.model;

import android.util.Log;

import com.google.gson.Gson;
import com.vv.mvc.utils.Const;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vivian on 2017/4/8.
 * Singleton refers to "http://www.jianshu.com/p/eb30a388c5fc"
 */

public enum DownloadApi {
    INSTANCE;
    private static final String TAG = "DownloadApi";

    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    public final Gson mGson = new Gson();

    public static Gson getGson() {
        return INSTANCE.mGson;
    }

    /**
     * get config sync through okhttp
     */
    public UrlConfig getConfig() {
        try {
            Request request = new Request.Builder()
                    .url(Const.URL_CONFIG)
                    .build();

            Response response = mOkHttpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                Log.w(TAG, "getConfig failed " + response);
                return null;
            }
            Log.w(TAG, "getConfig " + response.body());
            UrlConfig config = mGson.fromJson(response.body().charStream(), UrlConfig.class);
            return config;
        } catch (Exception ex) {
            Log.w(TAG, "getConfig ex:", ex);
        }

        return null;
    }

}
