package com.azadljy.jardinsecret.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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


    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void loge(String msg) {
        Log.e(TAG, msg);
    }

    public View getContentView() {
        return this.findViewById(android.R.id.content);
    }
}
