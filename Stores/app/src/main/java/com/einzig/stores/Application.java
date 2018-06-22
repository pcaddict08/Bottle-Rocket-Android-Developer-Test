package com.einzig.stores;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.orm.SugarApp;

import java.net.InetAddress;


/*
 * Created by steve on 2/2/2017.
 */

public final class Application extends SugarApp {
    Activity currentActivity;

    public static Application get(Activity activity) {
        return (Application) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        MyActivityLifecycleCallbacks handler = new MyActivityLifecycleCallbacks(this);
        registerActivityLifecycleCallbacks(handler);
    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    private static final class MyActivityLifecycleCallbacks implements ActivityLifecycleCallbacks, ComponentCallbacks2 {

        Application application;
        private static boolean isInBackground = false;

        MyActivityLifecycleCallbacks(Application application) {
            this.application = application;
        }

        @Override
        public void onTrimMemory(int i) {
            if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
                isInBackground = true;
            }
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            Log.d("LISTENER", "onActivityCreated:" + activity.getLocalClassName());
        }

        public void onActivityDestroyed(Activity activity) {
            Log.d("LISTENER", "onActivityDestroyed:" + activity.getLocalClassName());
        }

        public void onActivityPaused(Activity activity) {
            Log.d("LISTENER", "onActivityPaused:" + activity.getLocalClassName());
        }

        public void onActivityResumed(Activity activity) {
            application.setCurrentActivity(activity);
            Log.d("LISTENER", "onActivityResumed:" + activity.getLocalClassName());
            if (isInBackground) {
                isInBackground = false;
            }
        }

        public void onActivitySaveInstanceState(Activity activity,
                                                Bundle outState) {
            Log.d("LISTENER", "onActivitySaveInstanceState:" + activity.getLocalClassName());
        }

        public void onActivityStarted(Activity activity) {
            Log.d("LISTENER", "onActivityStarted:" + activity.getLocalClassName());
        }

        public void onActivityStopped(Activity activity) {
            Log.d("LISTENER", "onActivityStopped:" + activity.getLocalClassName());
        }

        @Override
        public void onConfigurationChanged(Configuration configuration) {

        }

        @Override
        public void onLowMemory() {

        }
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
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