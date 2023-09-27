package com.example.deviceinfo;

import android.os.Build;
import android.widget.TextView;

public class SystemInfo {
    private static String manufacturer,deviceModel,hardware, product;

    public static void setSystemInfo(TextView manu, TextView devMod, TextView hw, TextView prod){
        manufacturer = Build.MANUFACTURER;
        manu.setText("Manufacturer: " + manufacturer);

        deviceModel = Build.MODEL;
        devMod.setText("Device Model: " + deviceModel);

        hardware = Build.HARDWARE;
        hw.setText("Hardware: " + hardware);

        product = Build.PRODUCT;
        prod.setText("Product: " + product);
    }








}
