package com.demo.loader.demoloader.applist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class ApkListLoader extends AsyncTaskLoader<List<ApkEntity>> {
    private static final String TAG = "ApkListLoader";

    public ApkListLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<ApkEntity> loadInBackground() {
        return ApkTool.getLocalAppList(getContext().getPackageManager());
    }
}
