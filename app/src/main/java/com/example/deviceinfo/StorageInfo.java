package com.example.deviceinfo;
import android.app.usage.StorageStatsManager;
import android.os.Build;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.UUID;

public class StorageInfo {

    String stringUsed , stringAvail,  stringTotal;
    private StorageManager storageManager;
    private StorageStatsManager storageStatsManager;

    public StorageInfo(StorageManager storageManager, StorageStatsManager storageStatsManager){
        this.storageManager = storageManager;
        this.storageStatsManager = storageStatsManager;

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateStorageInfo(TextView availSto, TextView usedSto, TextView totalSto){

        if (storageManager == null || storageStatsManager == null) {
            return;
        }
        List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
        for (StorageVolume storageVolume : storageVolumes) {
            final String uuidStr = storageVolume.getUuid();
            final UUID uuid = uuidStr == null ? StorageManager.UUID_DEFAULT : UUID.fromString(uuidStr);
            try {
                float freeStorage = storageStatsManager.getFreeBytes(uuid);
                float totalStorage = storageStatsManager.getTotalBytes(uuid);
                freeStorage = freeStorage / 1073741824;
                totalStorage = totalStorage / 1073741824;
                float usedStorage = totalStorage - freeStorage;
                float formattedAvail = (float) (Math.floor(freeStorage * 10.0) / 10.0);
                float formattedUsed = (float) (Math.floor(usedStorage * 10.0) / 10.0);
                float formattedTotal = (float) (Math.floor(totalStorage * 10.0) / 10.0);

                 stringUsed = Float.toString(formattedUsed);
                 stringAvail = Float.toString(formattedAvail);
                 stringTotal = Float.toString(formattedTotal);

            } catch (Exception e) {
                stringUsed = "N/A";
                stringAvail = "N/A";
                stringTotal = "N/A";
            }

            totalSto.setText("Total Storage: " + stringTotal + " gb");
            availSto.setText("Available Storage: " + stringAvail + " gb");
            usedSto.setText("Used Storage: " + stringUsed + " gb");

        }
    }
}
