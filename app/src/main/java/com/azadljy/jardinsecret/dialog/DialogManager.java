package com.azadljy.jardinsecret.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import java.lang.ref.WeakReference;

public class DialogManager implements DialogInterface{
    public  String TAG = DialogManager.class.getSimpleName();
    private static DialogManager instance;


    private DialogManager() {

    }

    public static DialogManager getInstance() {
        if (instance == null) {
            instance = new DialogManager();
        }
        return instance;
    }



    public  Dialog mDialog;


    public  WeakReference<Context> currentContext;


    public  Dialog createDialog(@NonNull final Context context) {
        if (checkContext(context) && mDialog != null) {
            return mDialog;
        }
        currentContext = new WeakReference<>(context);
        mDialog = new Dialog(context);
        return mDialog;
    }

    public  Dialog createDialog(@NonNull final Context context, @StyleRes int themeResId) {
        if (checkContext(context) && mDialog != null) {
            context.setTheme(themeResId);
            return mDialog;
        }
        currentContext = new WeakReference<>(context);
        mDialog = new Dialog(context, themeResId);
        return mDialog;
    }


    public  void show(@NonNull Context context) {
        if (checkContext(context) && mDialog != null && !mDialog.isShowing())
            mDialog.show();
    }

    public  void dismiss(@NonNull Context context) {
        if (checkContext(context) && mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void cancel(@NonNull Context context) {
        if (checkContext(context) && mDialog != null) {
            mDialog.cancel();
        }
    }

    private  boolean checkContext(Context context) {
        if (context != null && currentContext != null && context == currentContext.get()) {
            return true;
        }
        return false;
    }

}
