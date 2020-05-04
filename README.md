Acoustic Android SDK with a 3rd party push provider
===================================================

This sample app demonstrates how to incorporate Acoustic Android SDK with a 3rd party push provider

1. Files to notice
	AndroidManifest.xml
	CustomApplication.java
	CustomFcmService.java
	CustomMessagingService.java

2. Copy Acoustic SDK file to ./app/libs folder (example acoustic-mobile-push-android-sdk-3.8.1.aar )
3. Copy MceConfig.json and modify it based on your appKey and server
 
AndroidManifest.xml add or modified Messaging Event:
```
<service
	android:name="co.acoustic.mobile.push.samples.android.fcm.CustomFcmService">
	<intent-filter>
		action android:name="com.google.firebase.MESSAGING_EVENT"/>
	</intent-filter>
</service>
```
