package com.demo.loader.mockdata;

import android.content.Context;
import android.os.CancellationSignal;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.os.OperationCanceledException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataLoader extends AsyncTaskLoader<List<MockEntity>> {
    private List<MockEntity> mData;

    public DataLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<MockEntity> loadInBackground() {
        Log.d("vv", "data loadInBackground");

        for (int i = 0; i < 10; i++) {
            SystemClock.sleep(1000);
            synchronized (this) {
                if (isLoadInBackgroundCanceled()) {
                    Log.d("vv", "isLoadInBackgroundCanceled ");
                    break;
                }
            }
        }

        if (mData == null) {
            mData = new ArrayList<>();
        }
        mockData();

        return mData;
    }

    private void mockData() {
        final int COUNT = 100;
        for (int i = 0; i < COUNT; i++) {
            mData.add(new MockEntity("name_" + i, "_" + i));
        }
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.d("vv", "data onStartLoading");
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
        Log.d("vv", "data onStopLoading");

    }

    @Override
    protected void onReset() {
        stopLoading();
        Log.d("vv", "data onReset");
    }
}
