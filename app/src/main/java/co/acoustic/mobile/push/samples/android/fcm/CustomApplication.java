package co.acoustic.mobile.push.samples.android.fcm;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.pushwoosh.Pushwoosh;
import com.pushwoosh.PushwooshFcmHelper;

import co.acoustic.mobile.push.sdk.api.MceApplication;
import co.acoustic.mobile.push.sdk.api.MceSdk;
import co.acoustic.mobile.push.sdk.api.MceSdkConfiguration;
import co.acoustic.mobile.push.sdk.api.MessagingApi;
import co.acoustic.mobile.push.sdk.api.SdkInitLifecycleCallbacks;
import co.acoustic.mobile.push.sdk.api.notification.NotificationsPreference;

import org.json.JSONObject;

public class CustomApplication extends Application {
    public static final String MCE_SAMPLE_NOTIFICATION_CHANNEL_ID = "mce_sample_channel";
    public static final String TAG = ">>>CustomFCMSample";

    @Override
    public void onCreate() {
        super.onCreate();
        // One option to do application 3rd party init stuff

        /**
        Pushwoosh.getInstance().registerForPushNotifications(result -> {
            if (result.isSuccess()) {
                Log.d(TAG, "3rd party registration with token: " + result.getData());
                // Acoustic registration
                startMceSdk();
            } else {
                Log.d(TAG, "Failed to register for push notifications:u " + result.getException().getMessage());
            }
        });
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(getApplicationContext());
        }
        startMceSdk();
    }
    private void startMceSdk() {

        MceApplication.init(this, new SdkInitLifecycleCallbacks() {
            @Override
            public void handleMetadata(Bundle metadata) {
                //metadata handling here
                Log.d(TAG,"metadat:"+metadata);
            }

            @Override
            public void onPluginActionLoad(JSONObject action) {
                //plugin action handling here
            }

            @Override
            public void onStart(MceSdkConfiguration configuration ) {
                Log.i(">>>CustomApplication:"," called right after the sdk start will be called. appKey:"+configuration.getAppKey());
                String token = Pushwoosh.getInstance().getPushToken();
                Log.d("onCreate token:",token);
            }

            @Override
            public void onSdkReinitializeNeeded(Context var1) {

            }
            @Override
            public     void onPluginNotificationTypeLoad(JSONObject var1){

            }


        });
    }
    @TargetApi(26)
    private static void createNotificationChannel(Context context) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = notificationManager.getNotificationChannel(MCE_SAMPLE_NOTIFICATION_CHANNEL_ID);
        if(channel == null) {

            CharSequence name = "Mce SDK notification channel";
            String description = "This is Mce SDK notification channel for the mce sdk";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            channel = new NotificationChannel(MCE_SAMPLE_NOTIFICATION_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationsPreference notificationsPreference = MceSdk.getNotificationsClient().getNotificationsPreference();
            notificationsPreference.setNotificationChannelId(context, MCE_SAMPLE_NOTIFICATION_CHANNEL_ID);
            notificationManager.createNotificationChannel(channel);
        }
    }

}