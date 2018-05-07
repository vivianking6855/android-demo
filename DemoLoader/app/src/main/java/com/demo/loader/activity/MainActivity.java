package com.demo.loader.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.demo.loader.R;
import com.demo.loader.common.Const;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoApkList(View view) {
        gotoPage(ApkListActivity.class);
    }

    public void gotoContact(View view) {
        gotoPage(ContactActivity.class);
    }

    public void gotoMockData(View view) {
        gotoPage(MockDataActivity.class);
    }

    private void gotoPage(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // destroy all tasks
        for (int id : Const.TASK_LIST){
            getSupportLoaderManager().destroyLoader(id);
        }
    }
}
