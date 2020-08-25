package com.example.remainme.remaindme.Dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.example.remainme.remaindme.R;


public class AlertDialogServices {

    private Context context;
    private String OK;
    private String CANCEL;
    private String MESSAGE;
    private String TITLE;
    private int ICON;
    private String DISMISS;
    private View VIEW;
    private DialogListener dialogListener;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    public AlertDialogServices(Context context) {
        this.context = context;
    }

    public AlertDialogServices showOnlyMsg() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(this.TITLE);
        alertDialog.setMessage(this.MESSAGE);
        alertDialog.setIcon(this.ICON);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,this.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onDialogResponse(dialog,which);
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, this.CANCEL, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onDialogResponse(dialog,which);
            }
        });
        alertDialog.show();
        return this;
    }

    public AlertDialogServices showOnlyMsg(String ok, String cancel, String msg, String title) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onDialogResponse(dialog,which);
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onDialogResponse(dialog,which);
            }
        });
        alertDialog.show();
        return this;
    }

    public AlertDialogServices showOnlyMsg(String ok, String cancel, String msg, String title, int icon) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setIcon(icon);
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onDialogResponse(dialog,which);
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogListener.onDialogResponse(dialog,which);
            }
        });
        alertDialog.show();
        return this;
    }

    public AlertDialogServices showConfirmAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(this.TITLE).setMessage(this.MESSAGE).setIcon(this.ICON)
                .setPositiveButton(this.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.onDialogResponse(dialog,which);
                    }
                })
                .setNegativeButton(this.CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.onDialogResponse(dialog,which);
                    }
                })
                .setNeutralButton(this.DISMISS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.onDialogResponse(dialog,which);
                    }
                }).create().show();
        return this;
    }

    /*@SuppressLint("NewApi")
    public AlertDialogServices showCustomAlertDialog(){
        builder = new AlertDialog.Builder(context);
        builder.setTitle(this.TITLE).setMessage(this.MESSAGE).setIcon(this.ICON)
                .setView(this.VIEW)
                .setCancelable(false)
                .setPositiveButton(this.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.onDialogResponse(dialog,which);
                    }
                })
                .setNegativeButton(this.CANCEL, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.onDialogResponse(dialog,which);
                    }
                })
                .setNeutralButton(this.DISMISS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogListener.onDialogResponse(dialog,which);
                    }
                }).create().show();
        return this;
    }

    public AlertDialogServices hideAlertDialog(){
        if (alertDialog.isShowing()) {
            alertDialog.hide();
            alertDialog.cancel();
        }
        return this;
    }*/

    public AlertDialogServices setDialogResponseListner(DialogListener dialogListener){
        this.dialogListener = dialogListener;
        return this;
    }

    public AlertDialogServices setTitle(String title) {
        this.TITLE = title;
        return this;
    }

    public AlertDialogServices setMessage(String msg) {
        this.MESSAGE = msg;
        return this;
    }

    public AlertDialogServices setIcon(int icon) {
        this.ICON = icon;
        return this;
    }

    public AlertDialogServices setOK(String ok) {
        this.OK = ok;
        return this;
    }

    public AlertDialogServices setCancel(String cancel) {
        this.CANCEL = cancel;
        return this;
    }

    public AlertDialogServices setDismiss(String dismiss) {
        this.OK = dismiss;
        return this;
    }

    public AlertDialogServices setView(View view){
        this.VIEW = view;
        return this;
    }

    public interface DialogListener {
        void onDialogResponse(DialogInterface dialog, int which);
    }
}
