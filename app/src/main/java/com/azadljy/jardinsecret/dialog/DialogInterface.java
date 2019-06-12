package com.azadljy.jardinsecret.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

public interface DialogInterface {
    void dismiss(@NonNull Context context);

    void cancel(@NonNull Context context);

    void show(@NonNull Context context);
}
