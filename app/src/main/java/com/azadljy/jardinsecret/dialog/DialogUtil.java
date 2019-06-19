package com.azadljy.jardinsecret.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.azadljy.jardinsecret.page.JardinActivity;
import com.azadljy.jardinsecret.R;

public class DialogUtil {

    public static void createSimpleDialog(final Context context) {
        Dialog mDialog = DialogManager.getInstance().createDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_simple, null);
        TextView textView = view.findViewById(R.id.tv_simple_name);
        textView.setText("test");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, JardinActivity.class));
                ((Activity) context).finish();
            }
        });
        mDialog.setContentView(view);
        mDialog.setTitle("dialog");
        mDialog.setCancelable(false);
    }

    public static Dialog createDialog(@NonNull final Context context) {
        return DialogManager.getInstance().createDialog(context);
    }

    public static Dialog createDialog(@NonNull final Context context, @StyleRes int themeResId) {
        return DialogManager.getInstance().createDialog(context, themeResId);
    }

    public static void dismiss(@NonNull Context context) {
        DialogManager.getInstance().dismiss(context);
    }


    public static void cancel(@NonNull Context context) {
        DialogManager.getInstance().cancel(context);
    }


    public static void show(@NonNull Context context) {
        DialogManager.getInstance().show(context);
    }
}
