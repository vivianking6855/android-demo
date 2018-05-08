package com.vv.mvp.listener;

/**
 * Created by vivian on 2017/9/12.
 */

public interface IDownloadListener {
    void onLoadStart();

    void onLoadSuccess(String result);

    void onLoadFail(String error);
}
