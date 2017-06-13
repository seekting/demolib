package com.seekting.demolib;

import android.app.Activity;
import android.os.Bundle;

import com.seekting.demo_lib.MainActivity;

public class TestActivity extends Activity {

    static {

        MainActivity.putTitle(TestActivity.class, "xxx");
        MainActivity.putDesc(TestActivity.class, "xxx");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
