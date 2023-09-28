package com.example.deviceinfo;

import static android.content.Context.BATTERY_SERVICE;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.TextView;

public class BatteryInfo extends BroadcastReceiver{

    float batteryTemp;
    int batteryHealth, level;
    TextView batLevel,batTemp,batHealth;
    String currentBatteryHealth = "Battery Health: ";

    public BatteryInfo(TextView batLevel, TextView batTemp, TextView batHealth) {
        this.batLevel = batLevel;
        this.batTemp = batTemp;
        this.batHealth = batHealth;
    }

    @Override
        public void onReceive(Context context, Intent intent) {
            BatteryManager bm = (BatteryManager)context.getSystemService(BATTERY_SERVICE);
            level = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            batteryTemp = (intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0)) / 10;
            batteryHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
            updateUi();
        }


    private void updateUi () {
        batLevel.setText("Battery Level Remaining: " + level + "%");

        batTemp.setText("Battery Temperature: " + batteryTemp + "°C");

        if(batteryHealth == BatteryManager.BATTERY_HEALTH_COLD){

            batHealth.setText(currentBatteryHealth+"Cold");
        }

        if(batteryHealth == BatteryManager.BATTERY_HEALTH_DEAD){

            batHealth.setText(currentBatteryHealth+"Dead");
        }

        if (batteryHealth == BatteryManager.BATTERY_HEALTH_GOOD){

            batHealth.setText(currentBatteryHealth+"Good");
        }

        if(batteryHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT){

            batHealth.setText(currentBatteryHealth+"OverHeat");
        }

        if (batteryHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE){

            batHealth.setText(currentBatteryHealth+" = Over voltage");
        }

        if (batteryHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN){

            batHealth.setText(currentBatteryHealth+"Unknown");
        }
        if (batteryHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE){

            batHealth.setText(currentBatteryHealth+"Unspecified Failure");
        }
    }

}
