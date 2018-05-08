package com.vv.mvp.presenter;

import android.util.Log;

import com.vv.mvp.listener.IDownloadListener;
import com.vv.mvp.model.DownloadApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vivian on 2017/9/12.
 */

public class DownloadPresenter {
    private static final String TAG = "DownloadPresenter";

    private IDownloadListener mLoadListener;

    // website
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void setIDownloadListener(IDownloadListener listener) {
        mLoadListener = listener;
    }

    public void get() {

    }

    public void start() {
        if (mLoadListener != null) {
            mLoadListener.onLoadStart();
        }
        DownloadApi engine = (DownloadApi) DownloadApi.INSTANCE;
        Disposable config = Observable.fromCallable(() -> engine.getConfig())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(urlConfig -> {
                            final String result = engine.getGson().toJson(urlConfig).toString();
                            Log.d(TAG, "" + result);
                            if (mLoadListener != null) {
                                mLoadListener.onLoadSuccess(result);
                            }
                        },
                        error -> {
                            Log.w(TAG, "tv_download error:", error);
                            if (mLoadListener != null) {
                                mLoadListener.onLoadFail(error.toString());
                            }
                        },
                        () -> {
                            Log.d(TAG, "tv_download complete");
                        });
        compositeDisposable.add(config);
    }

    public void destroy() {
        // to avoid okhttp leak
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}
