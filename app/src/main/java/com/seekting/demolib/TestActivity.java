package com.seekting.demolib;

import android.app.Activity;
import android.os.Bundle;

import com.seekting.demo_lib.Demo;

@Demo(title = "测试title", desc = "测试desc")
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
