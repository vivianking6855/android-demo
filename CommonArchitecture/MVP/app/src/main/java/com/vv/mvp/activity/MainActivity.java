package com.vv.mvp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.vv.mvp.R;
import com.vv.mvp.presenter.DownloadPresenter;
import com.vv.mvp.listener.IDownloadListener;

public class MainActivity extends BaseActivity implements IDownloadListener {
    private static final String TAG = "MainActivity";

    private ProgressDialog mProcessDlg;
    private TextView mTVResult;
    private DownloadPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        mPresenter = new DownloadPresenter();
        mPresenter.setIDownloadListener(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        mTVResult = (TextView) findViewById(R.id.tv_result);
        findViewById(R.id.tv_download).setOnClickListener(v -> {
            showProgress();
            mPresenter.start();
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

        mPresenter.destroy();
    }

    @Override
    public void onLoadStart() {
        showProgress();
    }

    @Override
    public void onLoadSuccess(String result) {
        dismissProgress();
        mTVResult.setText(result);
        Log.d(TAG,"onLoadSuccess");
    }

    @Override
    public void onLoadFail(String error) {
        dismissProgress();
        mTVResult.setText("load error: " + error);
    }
}
