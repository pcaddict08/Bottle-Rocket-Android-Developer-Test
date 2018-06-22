package com.einzig.stores.helpers;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class MiscHelper {

    public static boolean hasMapsInstalled(Context context) {
        try {
            String packageName = "com.google.android.apps.maps";
            int flag = 0;
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(packageName, flag);
            return appInfo.enabled;
        } catch (Exception e) {
            return false;
        }
    }
}
