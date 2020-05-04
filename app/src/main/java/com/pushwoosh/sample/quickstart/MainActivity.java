package com.pushwoosh.sample.quickstart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.pushwoosh.Pushwoosh;

import org.json.JSONObject;

import java.util.logging.Logger;

import co.acoustic.mobile.push.sdk.api.MceApplication;
import co.acoustic.mobile.push.sdk.api.MceSdkConfiguration;
import co.acoustic.mobile.push.sdk.api.SdkInitLifecycleCallbacks;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}
}
