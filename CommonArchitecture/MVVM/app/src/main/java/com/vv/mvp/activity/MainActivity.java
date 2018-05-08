package com.vv.mvp.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.vv.mvp.R;
import com.vv.mvp.databinding.ActivityMainBinding;
import com.vv.mvp.listener.IDownloadListener;
import com.vv.mvp.model.DownloadResult;
import com.vv.mvp.presenter.DownloadPresenter;

public class MainActivity extends BaseActivity implements IDownloadListener {
    private static final String TAG = "MainActivity";

    private ProgressDialog mProcessDlg;
    private DownloadPresenter mPresenter;

    private ActivityMainBinding mBinding;
    private DownloadResult mResult;

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
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
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
        mResult.result.set(result);
    }

    @Override
    public void onLoadFail(String error) {
        dismissProgress();
        mResult.result.set("load error: " + error);
    }
}
