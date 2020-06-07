package com.example.test;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FcmMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        if(remoteMessage.getData().size()>0){
            String title=remoteMessage.getData().get("title");
            String message=remoteMessage.getData().get("message");
            Intent intent=new Intent("com.example.test_FCM-MESSAGE");
            intent.putExtra("title",title);
            intent.putExtra("message",message);
            LocalBroadcastManager localBroadcastManager=LocalBroadcastManager.getInstance(this);
            localBroadcastManager.sendBroadcast(intent);
        }
    }

}
