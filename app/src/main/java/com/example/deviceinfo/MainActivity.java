package com.example.deviceinfo;

import static androidx.core.content.ContextCompat.getSystemService;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.storage.StorageManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView manu, devMod, hw, prod, availMemr,usedMemr,totalMemr,availSto,usedSto,totalSto;
    public ActivityManager activityManager;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manu =findViewById(R.id.twManu);
        devMod = findViewById(R.id.twDevMod);
        hw = findViewById(R.id.twHw);
        prod = findViewById(R.id.twProd);

        availMemr = findViewById(R.id.twAvailMem);
        usedMemr = findViewById(R.id.twUsedMem);
        totalMemr = findViewById(R.id.twTotalMem);

        availSto = findViewById(R.id.twAvailSto);
        usedSto = findViewById(R.id.twUsedSto);
        totalSto = findViewById(R.id.twTotalSto);

        SystemInfo.setSystemInfo(manu,devMod,hw,prod);

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


        StorageStatsManager storageStatsManager = (StorageStatsManager) getSystemService(Context.STORAGE_STATS_SERVICE);
        StorageManager storageManager = (StorageManager) getSystemService(Context.STORAGE_SERVICE);

        StorageInfo storageInfo = new StorageInfo(storageManager,storageStatsManager);
        storageInfo.updateStorageInfo(availSto,usedSto,totalSto);


    }
}