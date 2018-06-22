package com.einzig.stores.helpers;

import android.util.Log;

import com.einzig.stores.Constants;

/**
 * Created by Steven Foskett on 6/21/2017.
 */

public class LogHelper {

    public static void log_debug(String stringToLog) {
        if (Constants.debug == 1)
            Log.d("SimpleDrying-debug", stringToLog);
    }
}
