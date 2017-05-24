package com.voobly.ratings.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.voobly.ratings.R;
import com.voobly.ratings.ui.base.factoryactivities.BaseActivity;

public class WebActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
    }
}
