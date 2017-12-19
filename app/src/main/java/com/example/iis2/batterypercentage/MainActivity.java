package com.example.iis2.batterypercentage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textView;

    /*onCreate is the first method in the life cycle of an activity
    savedInstance passes data to super class,data is pull to store state of application
  * setContentView is used to set layout for the activity
  *R is a resource and it is auto generate file
  * activity_main assign an integer value*/
    //Declaring variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.battery);

        BroadcastReceiver batteryReciever = new BroadcastReceiver() {

            /*
         BroadcastReceiver is an Android component which allows you to register for system or
         application events.
         A receiver can be registered via the AndroidManifest.xml file
          The implementing class for a receiver extends the BroadcastReceiver class. If the event for which the broadcast receiver has registered happens,
          The onReceive() method of the receiver is called by the Android system.
         */
            @Override
            public void onReceive(Context context, Intent intent) {

                context.unregisterReceiver(this);
                int currentLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,-1);
                int level = -1;
                if (currentLevel >=0 && scale > 0){
                    level= (currentLevel * 100)/scale;
                }
                textView.setText("Battery remaining : "+level+"%");

            }
        };
        //IntentFilter specifies the types of intents to which a broadcast receiver can respond
        IntentFilter batteryFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReciever,batteryFilter);

    }
}
