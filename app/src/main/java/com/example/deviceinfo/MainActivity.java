package com.example.deviceinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView manu, devMod, hw, prod, availMemr,usedMemr,totalMemr;


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
        MemoryInfo.updateAvailableMemory(availMemr, usedMemr);

    }
}