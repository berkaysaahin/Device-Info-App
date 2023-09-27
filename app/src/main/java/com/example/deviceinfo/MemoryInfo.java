package com.example.deviceinfo;

import android.app.ActivityManager;
import android.widget.TextView;

public class MemoryInfo {

    public ActivityManager activityManager;
    public ActivityManager.MemoryInfo memory;

    public long totalMemory;

    public MemoryInfo(ActivityManager activityManager){
        this.activityManager = activityManager;
        this.memory = new ActivityManager.MemoryInfo();
        this.
    }

    public long calculateTotalMemory(TextView totalMemr){
        totalMemory = memory.totalMem / 1048576L;
        String strTotal = Long.toString(totalMemory);

        totalMemr.setText("Total Memory: " + strTotal);
        return 0;
    }

    public void updateAvailableMemory(TextView availMemr, TextView usedMemr){

        activityManager.getMemoryInfo(memory);

        long availableMemory = memory.availMem / 1048576L;
        String strAvail = Long.toString(availableMemory);

        availMemr.setText("Available Memory: " + strAvail);
        long usedMemory = totalMemory - availableMemory;
        String strUsed = Long.toString(usedMemory);

        usedMemr.setText("Memory in Use: " + strUsed);
    }
}
