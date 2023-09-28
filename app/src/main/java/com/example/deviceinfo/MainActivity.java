package com.example.deviceinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView manu, devMod, hw, prod, availMemr,usedMemr,totalMemr;
    public ActivityManager activityManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manu =findViewById(R.id.twManu);
        devMod = findViewById(R.id.twDevMod);
        hw = findViewById(R.id.twHw);
        prod = findViewById(R.id.twProd);

        SystemInfo.setSystemInfo(manu,devMod,hw,prod);

        availMemr = findViewById(R.id.twAvailMem);
        usedMemr = findViewById(R.id.twUsedMem);
        totalMemr = findViewById(R.id.twTotalMem);
        activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        MemoryInfo memoryInfo = new MemoryInfo(activityManager,totalMemr);


        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                memoryInfo.updateAvailableMemory(availMemr, usedMemr);
                handler.postDelayed(this,1000);
            }
        };
        handler.postDelayed(runnable,0);
    }
}