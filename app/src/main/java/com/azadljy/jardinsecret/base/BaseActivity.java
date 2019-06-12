package com.azadljy.jardinsecret.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

public class BaseActivity extends AppCompatActivity {
    public String TAG;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mContext = this;
    }
}
