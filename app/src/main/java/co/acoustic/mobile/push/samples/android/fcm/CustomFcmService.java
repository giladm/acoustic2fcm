package co.acoustic.mobile.push.samples.android.fcm;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pushwoosh.Pushwoosh;
import com.pushwoosh.PushwooshFcmHelper;

import co.acoustic.mobile.push.sdk.api.MessagingApi;
import co.acoustic.mobile.push.sdk.api.fcm.FcmApi;

public class CustomFcmService extends FirebaseMessagingService {

    private static final String TAG = "CustomFcmService";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        if(PushwooshFcmHelper.isPushwooshMessage(remoteMessage)) {
            Log.i(TAG,"3rd party push service");
            PushwooshFcmHelper.onMessageReceived(getApplicationContext(), remoteMessage);
        } else if(FcmApi.isFcmMessage(remoteMessage)) {
            Log.i(TAG,"Acoustic push service");
            FcmApi.handleMceFcmMessage(getApplicationContext(), remoteMessage);
        } else {
            Log.i(TAG, "Non MCE or pushwoosh message received "+remoteMessage);
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        MessagingApi.reportToken(getApplicationContext(), s);
        PushwooshFcmHelper.onTokenRefresh(getApplicationContext(), s);
    }
}
