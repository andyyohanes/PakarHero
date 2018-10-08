package com.radicallabsinc.pakarhero.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMTokenStateService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.e("FCM_TEST", "on token refresh: " + FirebaseInstanceId.getInstance().getToken());
    }
}
