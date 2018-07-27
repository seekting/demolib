package com.seekting.demolib;

import android.app.Activity;
import android.os.Bundle;

import com.seekting.demo_lib.Demo;

@Demo(title = "ExcludeActivity", desc = "ExcludeActivity", exclude = true)
public class ExcludeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
