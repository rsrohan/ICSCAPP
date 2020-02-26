package com.example.icscapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    NotificationManager notificationManager ;
    private String id = "ICSC2020";
    static int notify_id = 0;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void showNotification(String title, String message){
        NotificationCompat.Builder builder;
        notify_id++;
        if(notificationManager == null){
            notificationManager = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        }

        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = notificationManager.getNotificationChannel(id);
            if(channel==null){
                channel = new NotificationChannel(id,title,importance);
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{100,200,300,400,500});
                notificationManager.createNotificationChannel(channel);
            }

            builder = new NotificationCompat.Builder(getApplicationContext(),id);

            builder.setContentTitle(title).setSmallIcon(R.drawable.jiitlogo).setContentText(message).setDefaults(Notification.DEFAULT_ALL).setAutoCancel(true).setVibrate(new long[] {100,200,300,400,500});
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.jiitlogo));
            Notification notification = builder.build();
            notificationManager.notify(notify_id,notification);
        }

        else {
            builder = new NotificationCompat.Builder(getApplicationContext(), id);
            builder.setContentTitle(title).setSmallIcon(R.drawable.jiitlogo).setContentText(message).setDefaults(Notification.DEFAULT_ALL).setAutoCancel(true).setVibrate(new long[] {100,200,300,400,500});
            builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.jiitlogo));
            Notification notification = builder.build();
            notificationManager.notify(notify_id,notification);

        }
    }
}
