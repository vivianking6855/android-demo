package com.vv.mvc.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.vv.mvc.R;
import com.vv.mvc.model.DownloadApi;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    private ProgressDialog mProcessDlg;
    private TextView mTVResult;

    // website
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mTVResult = (TextView) findViewById(R.id.tv_result);

        findViewById(R.id.tv_download).setOnClickListener(v -> {
            showProgress();
            DownloadApi engine = (DownloadApi) DownloadApi.INSTANCE;
            Disposable config = Observable.fromCallable(() -> engine.getConfig())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(urlConfig -> {
                                final String result = engine.getGson().toJson(urlConfig).toString();
                                Log.d(TAG, "" + result);
                                mTVResult.setText(result);
                            },
                            error -> {
                                Log.w(TAG, "okhttpConfigClick ex:", error);
                                dismissProgress();
                            },
                            () -> {
                                Log.d(TAG, "tv_download complete");
                                dismissProgress();
                            });
            compositeDisposable.add(config);
        });

    }

    @Override
    protected void loadData() {

    }

    public void showProgress() {
        if (mProcessDlg == null) {
            mProcessDlg = ProgressDialog.show(this, "download", "I'm loading, please wait...");
        }
    }

    public void dismissProgress() {
        if (mProcessDlg != null) {
            mProcessDlg.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // to avoid dlg leak
        dismissProgress();

        // to avoid okhttp leak
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
