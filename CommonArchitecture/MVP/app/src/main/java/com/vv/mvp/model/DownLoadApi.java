package com.vv.mvp.model;

import android.util.Log;

import com.google.gson.Gson;
import com.vv.mvp.utils.Const;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by vivian on 2017/4/8.
 * Singleton refers to
 * "http://www.jianshu.com/p/eb30a388c5fc" and
 * "https://github.com/francistao/LearningNotes/blob/master/Part3/Algorithm/剑指Offer/1.七种方式实现singleton模式.md"
 */

public enum DownLoadApi {
    INSTANCE;

    private static final String TAG = "DownLoadApi";

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

            return mGson.fromJson(response.body().charStream(), UrlConfig.class);
        } catch (Exception ex) {
            Log.w(TAG, "getConfig ex:", ex);
        }

        return null;
    }

}
