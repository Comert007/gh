package com.ww.android.governmentheart.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

public class DialogUtils {
    private DialogUtils() {
        throw new RuntimeException();
    }


    public static final Dialog showDialog(Context context, String title, CharSequence msg, String positive,
                                          DialogInterface.OnClickListener posiListener,
                                          String cancel, DialogInterface.OnClickListener cancelListener, boolean cancelable) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setCancelable(cancelable)
                        .setPositiveButton(
                                positive, posiListener);
                if (!TextUtils.isEmpty(cancel)) {
                    builder.setNegativeButton(cancel, cancelListener);
                }
                return showColor(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static final Dialog showDialog(Context context, String title, CharSequence msg, String positive,
                                          DialogInterface.OnClickListener posiListener,
                                          String cancel, DialogInterface.OnClickListener cancelListener) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setCancelable(true)
                        .setPositiveButton(
                                positive, posiListener);
                if (!TextUtils.isEmpty(cancel)) {
                    builder.setNegativeButton(cancel, cancelListener);
                }
                return showColor(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static final Dialog showDialog(Context context, String title, CharSequence msg, String positive,
                                          DialogInterface.OnClickListener posiListener) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setPositiveButton(
                                positive, posiListener);
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                return showColor(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static final Dialog showTipDialog(Context context, String title, CharSequence msg, String positive,
                                          DialogInterface.OnClickListener posiListener,
                                          String cancel, DialogInterface.OnClickListener cancelListener) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setPositiveButton(
                                positive, posiListener);
                if (!TextUtils.isEmpty(cancel)) {
                    builder.setNegativeButton(cancel, cancelListener);
                }
                return showColor(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static final Dialog showTipDialog(Context context, String title, CharSequence msg, String positive,
                                          DialogInterface.OnClickListener posiListener) {
        try {
            if (!TextUtils.isEmpty(msg)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle(title)
                        .setMessage(msg)
                        .setPositiveButton(
                                positive, posiListener);
                return showColor(builder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Dialog showDialogList(Context context, String title, ListAdapter adapter,
                                        DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setAdapter(adapter, listener);
        return builder.show();
    }


    public static AlertDialog showColor(AlertDialog.Builder builder) {
        AlertDialog dialog = builder.show();
        Button btnPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        try {
            TextView title = (TextView) dialog.findViewById(android.support.v7.appcompat.R.id.alertTitle);
            title.setTextColor(Color.parseColor("#14191d"));
        } catch (Exception e) {
        }
        if (btnPositive != null) {
            btnPositive.setTextColor(Color.parseColor("#ff4283"));
        }

        Button btnNegative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (btnNegative != null) {//53a9ff
            btnNegative.setTextColor(Color.parseColor("#53a9ff"));
        }
        TextView textView = dialog.findViewById(android.R.id.message);
        if (textView != null) {
            textView.setTextColor(Color.parseColor("#535353"));
        }
        return dialog;
    }
}
