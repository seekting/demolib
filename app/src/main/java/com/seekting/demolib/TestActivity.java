package com.seekting.demolib;

import android.os.Bundle;
import android.util.Log;

import com.seekting.demo_lib.Demo;
import com.seekting.demo_lib.SubBaseActivity;
import com.seekting.demo_lib.SubDemo;

@Demo(title = "测试title", desc = "测试desc")
public class TestActivity extends SubBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SubDemo(title = "testSub", desc = "testSub desc")
    void testSub() {

        Log.d("seekting", "TestActivity.testSub()");
    }
}
