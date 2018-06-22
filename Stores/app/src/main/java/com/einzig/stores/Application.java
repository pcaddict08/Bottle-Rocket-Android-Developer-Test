package com.einzig.stores;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.orm.SugarApp;

public final class Application extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static boolean isInternetAvailable(Context context) {
        try {
            if (context == null) {
                return false;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}