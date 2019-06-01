package com.kys.lg.testscheduler;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class SplashActivity extends AppCompatActivity {

   private LinearLayout splash_layout;
   private FirebaseRemoteConfig mfirebaseRemoteConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_layout = (LinearLayout)findViewById(R.id.splash_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mfirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mfirebaseRemoteConfig.setDefaults(R.xml.default_config);

        FirebaseRemoteConfigSettings firebaseRemoteConfigSettings
                = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG).build();

        mfirebaseRemoteConfig.setConfigSettings(firebaseRemoteConfigSettings);

        mfirebaseRemoteConfig.fetch(0)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            mfirebaseRemoteConfig.activateFetched();

                        } else {

                        }
                        displayMessage();
                    }
                });

    }
        void displayMessage(){

        String splash_background = mfirebaseRemoteConfig.getString("splash_background");
        boolean caps= mfirebaseRemoteConfig.getBoolean("splash_message_caps");
        String splash_message= mfirebaseRemoteConfig.getString("splash_message");

        splash_layout.setBackgroundColor(Color.parseColor(splash_background));

        if(caps){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage(splash_message);
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.create().show();

        }
        else{
            Intent i = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}
