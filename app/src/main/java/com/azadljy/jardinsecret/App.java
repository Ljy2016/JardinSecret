package com.azadljy.jardinsecret;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


    public static Context getContext() {
        if (context == null) {
            throw new NullPointerException("Application 还未创建");
        }
        return context;
    }
}
