package com.demo.loader.demoloader.mockdata;

import android.content.Context;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.os.OperationCanceledException;

import java.util.ArrayList;
import java.util.List;

public class DataLoader extends AsyncTaskLoader<List<MockEntity>> {
    private List<MockEntity> mData;

    CancellationSignal mCancellationSignal;

    public DataLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<MockEntity> loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled() || getContext() == null) {
                throw new OperationCanceledException();
            }
            mCancellationSignal = new CancellationSignal();
        }

        try {
            if (mData == null) {
                mData = new ArrayList<>();
            }
            mockData();
        } finally {
            synchronized (this) {
                mCancellationSignal = null;
            }
        }

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
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        stopLoading();
    }
}
