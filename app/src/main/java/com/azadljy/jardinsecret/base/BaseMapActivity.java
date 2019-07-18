package com.azadljy.jardinsecret.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.amap.api.maps.MapView;
import com.azadljy.pleasantlibrary.lifecircle.MapLifeObserver;

public abstract class BaseMapActivity extends BaseActivity {

    protected MapView mMapView = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initContentView());
        initMapView();
        if (mMapView == null) {
            throw new NullPointerException("请在initMapView()方法中初始化 mMapView ");
        }
        mMapView.onCreate(savedInstanceState);
        getLifecycle().addObserver(new MapLifeObserver(mMapView));
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);

    }

    //初始化map View
    public abstract void initMapView();
    //初始化布局 View
    public abstract int initContentView();
}
