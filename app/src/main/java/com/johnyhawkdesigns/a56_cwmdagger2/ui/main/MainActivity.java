package com.johnyhawkdesigns.a56_cwmdagger2.ui.main;

import android.os.Bundle;

import com.johnyhawkdesigns.a56_cwmdagger2.BaseActivity;
import com.johnyhawkdesigns.a56_cwmdagger2.R;

import androidx.annotation.Nullable;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
