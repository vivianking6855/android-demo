package com.demo.loader.demoloader;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.demo.loader.demoloader.applist.ApkEntity;
import com.demo.loader.demoloader.applist.ApkListAdapter;
import com.demo.loader.demoloader.applist.ApkListLoader;
import com.demo.loader.demoloader.mockdata.DataListAdapter;
import com.demo.loader.demoloader.mockdata.DataLoader;
import com.demo.loader.demoloader.mockdata.MockEntity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Reference<MainActivity> mActivityRef;

    private RecyclerView mRecyclerView;
    private DataListAdapter mAdapter;
    private ApkListAdapter mApkAdapter;

    private final int TASK_DATA_ID = 100;
    private final int TASK_APK_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityRef = new WeakReference<>(this);

        initView();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(android.R.id.list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivityRef.get()));
        mRecyclerView.setAdapter(mAdapter = new DataListAdapter());
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                mActivityRef.get(), DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        getSupportLoaderManager().initLoader(TASK_DATA_ID, null, new DataLoaderCallback());
    }

    public void onChangeData(View view) {
        mRecyclerView.setAdapter(mApkAdapter = new ApkListAdapter());
        getSupportLoaderManager().initLoader(TASK_APK_ID, null, new ApkLoaderCallback());
    }

    class DataLoaderCallback implements LoaderManager.LoaderCallbacks<List<MockEntity>> {

        @Override
        public Loader<List<MockEntity>> onCreateLoader(int id, Bundle args) {
            Toast.makeText(mActivityRef.get(), "loading", Toast.LENGTH_SHORT).show();
            // Loader user app context in it's own
            return new DataLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<List<MockEntity>> loader, List<MockEntity> data) {
            Toast.makeText(mActivityRef.get(), "finish", Toast.LENGTH_SHORT).show();
            mAdapter.setData(data);
        }

        @Override
        public void onLoaderReset(Loader<List<MockEntity>> loader) {
            mAdapter.clearData();
        }
    }

    class ApkLoaderCallback implements LoaderManager.LoaderCallbacks<List<ApkEntity>>{

        @Override
        public Loader<List<ApkEntity>> onCreateLoader(int id, Bundle args) {
            Toast.makeText(mActivityRef.get(), "loading apk", Toast.LENGTH_SHORT).show();
            // Loader user app context in it's own
            return new ApkListLoader(MainActivity.this);
        }

        @Override
        public void onLoadFinished(Loader<List<ApkEntity>> loader, List<ApkEntity> data) {
            Toast.makeText(mActivityRef.get(), "finish apk", Toast.LENGTH_SHORT).show();
            mApkAdapter.setData(data);
        }

        @Override
        public void onLoaderReset(Loader<List<ApkEntity>> loader) {
            mApkAdapter.clearData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
