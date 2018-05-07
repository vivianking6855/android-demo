package com.demo.loader.applist;

import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.List;

public class ApkListLoader extends AsyncTaskLoader<List<ApkEntity>> {
    public ApkListLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<ApkEntity> loadInBackground() {
        Log.d("vv", "apk loadInBackground");
        SystemClock.sleep(1000*5);
        return ApkTool.getLocalAppList(getContext().getPackageManager());
    }

    @Override
    protected void onStartLoading() {
        Log.d("vv", "apk onStartLoading");
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
        Log.d("vv", "apk onStopLoading");
    }

    @Override
    protected void onReset() {
        stopLoading();
        Log.d("vv", "apk onReset");
    }
}
