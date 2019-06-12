package com.azadljy.jardinsecret.lifecircle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class NormalLifeObserver implements LifecycleObserver {

    public static final String TAG = "NormalLifeObserver";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create() {
        Log.e(TAG, "create: ");

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start() {
        Log.e(TAG, "start: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume() {
        Log.e(TAG, "resume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory() {
        Log.e(TAG, "destory: ");
    }

}