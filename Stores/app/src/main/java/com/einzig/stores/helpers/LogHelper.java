package com.einzig.stores.helpers;

import android.util.Log;

import com.einzig.stores.Constants;

public class LogHelper {

    public static void log_debug(String stringToLog) {
        if (Constants.debug == 1)
            Log.d("Stores-debug", stringToLog);
    }
}
