package com.example.remainme.remaindme.Libs;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

public class NotificationServices {

    private final String CHANNEL_ID = "Remainder_Notification";
    private Context context;
    private String OK;
    private String CANCEL;
    private String MESSAGE;
    private String TITLE;
    private int ICON;
    private String DISMISS;
    private View VIEW;
    private int notificationManagerId = 001;
    private int NOTIFICATION_IMPORTACNE;
    private int NOTIFICATION_PRIORITY;

    public NotificationServices(Context context) {
        this.context = context;
    }

    public NotificationServices(Context context, int notificationManagerId) {
        this.context = context;
        this.notificationManagerId = notificationManagerId;
    }


    public NotificationServices showOnlyNotify() {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(this.ICON).setContentTitle(this.TITLE)
                .setContentText(this.MESSAGE).setPriority(this.NOTIFICATION_PRIORITY)
                .setAutoCancel(false);

        buildNotify(builder);
        return this;
    }

    private void buildNotify(NotificationCompat.Builder builder) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(this.notificationManagerId, builder.build());
    }

    public NotificationServices setTitle(String title) {
        this.TITLE = title;
        return this;
    }

    public NotificationServices setMessage(String msg) {
        this.MESSAGE = msg;
        return this;
    }

    public NotificationServices setIcon(int icon) {
        this.ICON = icon;
        return this;
    }

    public NotificationServices setOK(String ok) {
        this.OK = ok;
        return this;
    }

    public NotificationServices setCancel(String cancel) {
        this.CANCEL = cancel;
        return this;
    }

    public NotificationServices setDismiss(String dismiss) {
        this.OK = dismiss;
        return this;
    }

    public NotificationServices setView(View view){
        this.VIEW = view;
        return this;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Remainder_Notification";
            String desc = "Include all Remainder Notifications";
            int importance = this.NOTIFICATION_IMPORTACNE;

            NotificationChannel notificationChannel = new NotificationChannel("Remainder_Notification", name, importance);
            notificationChannel.setDescription(desc);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public NotificationServices setImportance(int importance) {
        this.NOTIFICATION_IMPORTACNE = importance;
        /*switch (importance) {
            case 0:
                this.NOTIFICATION_IMPORTACNE = NotificationManager.IMPORTANCE_NONE;
                break;
            case 1:
                this.NOTIFICATION_IMPORTACNE = NotificationManager.IMPORTANCE_MIN;
                break;
            case 2:
                this.NOTIFICATION_IMPORTACNE = NotificationManager.IMPORTANCE_LOW;
                break;
            case 3:
                this.NOTIFICATION_IMPORTACNE = NotificationManager.IMPORTANCE_DEFAULT;
                break;
            case 4:
                this.NOTIFICATION_IMPORTACNE = NotificationManager.IMPORTANCE_HIGH;
                break;
            case 5:
                this.NOTIFICATION_IMPORTACNE = NotificationManager.IMPORTANCE_MAX;
                break;
            case -1000:
                this.NOTIFICATION_IMPORTACNE = NotificationManager.IMPORTANCE_UNSPECIFIED;
                break;
            default:
                this.NOTIFICATION_IMPORTACNE = NotificationManager.IMPORTANCE_DEFAULT;
                break;
        }*/
        return this;
    }

    public NotificationServices setPriority(int priority) {
        this.NOTIFICATION_PRIORITY =  priority;
        return this;
    }

}
