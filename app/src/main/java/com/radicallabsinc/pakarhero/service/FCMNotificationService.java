package com.radicallabsinc.pakarhero.service;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.radicallabsinc.pakarhero.PakarHeroApp;
import com.radicallabsinc.pakarhero.R;
import com.radicallabsinc.pakarhero.di.component.ApplicationComponent;

import java.util.Random;

import javax.inject.Inject;

public class FCMNotificationService extends FirebaseMessagingService {
    Handler handler;

    NotificationManager notificationManager;

    /*@Inject
    NotificationsManager notificationsManager;*/

    @Override
    public void onCreate() {
        super.onCreate();
        /*ApplicationComponent applicationComponent = ((PakarHeroApp) getApplication()).getApplicationComponent();
        DaggerUseCaseComponent.builder()
                .applicationComponent(applicationComponent)
                .build().inject(this);*/

        handler = new Handler();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e("isi remoteMessage",remoteMessage.toString());
        Log.e("detail message",remoteMessage.getNotification().getBody());

        int notificationId = new Random().nextInt(60000);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "channel")
                .setSmallIcon(R.drawable.logo_square_24dp)  //a resource for your custom small icon
                .setContentTitle(remoteMessage.getData().get("title")) //the "title" value you sent in your notification
                .setContentText(remoteMessage.getData().get("message")) //ditto
                .setAutoCancel(true)  //dismisses the notification on click
                .setSound(defaultSoundUri);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId /* ID of notification */, notificationBuilder.build());


        /*final String message[] = new String[1];
        if (remoteMessage != null && remoteMessage.getNotification() != null) {
            message[0] = "Notification title: " + remoteMessage.getNotification().getTitle() +
                    ", body: " + remoteMessage.getNotification().getBody() +
                    ", data: " + remoteMessage.getData();

//            notificationsManager.showNotification(remoteMessage);
        } else {
            message[0] = "NOTIFICATION IS NULL";
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(FCMNotificationService.this, message[0], Toast.LENGTH_LONG).show();
                }
            });
        }
        Log.i("FCM_TEST", message[0]);*/
    }
}
