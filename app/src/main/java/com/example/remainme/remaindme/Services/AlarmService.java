package com.example.remainme.remaindme.Services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.remainme.remaindme.Activities.BaseActivity;
import com.example.remainme.remaindme.R;

public class AlarmService extends IntentService {

    private NotificationManager alarmNotifyManager;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        sendNotifications("Wake up! Wake up!");
    }

    private void sendNotifications(String msg) {
        alarmNotifyManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, new Intent(this, BaseActivity.class), 0);
        NotificationCompat.Builder alarmNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Alarm").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);

        alarmNotifyBuilder.setContentIntent(pendingIntent);
        alarmNotifyManager.notify(1, alarmNotifyBuilder.build());
        Log.d(">>>>AlarmService", "Notification Sent");

    }
}
