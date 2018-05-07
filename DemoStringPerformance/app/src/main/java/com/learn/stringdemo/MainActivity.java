package com.learn.stringdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "CodeDebug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testStringPerformance(View v){
        StateMan.sStop = false;
        TestString.testStringPerformance();
    }

    public void stopAll(View view) {
        StateMan.sStop = true;
    }
}
