package com.einzig.stores.helpers;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.einzig.stores.R;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static com.einzig.stores.helpers.LogHelper.log_debug;

public class ThemeHelper {

    public static int getRandomColor_blue(int min, int max) {
        Random r = new Random();
        int redGreen = r.nextInt(max - min + 1) + min;
        int blue = 255;
        return Color.rgb(redGreen, redGreen, blue);
    }

    public static float getDensity(Context context) {
        float scale = context.getResources().getDisplayMetrics().density;
        return scale;
    }

    public static int convertDiptoPix(int dip, Context context) {
        float scale = getDensity(context);
        return (int) (dip * scale + 0.5f);
    }

    public static int convertPixtoDip(int pixel, Context context) {
        float scale = getDensity(context);
        return (int) ((pixel - 0.5f) / scale);
    }

    public static void initActionBar(ActionBar ab, String title) {
        if (ab != null) {
            setTitle(title, ab);
            ab.setTitle(title);
            ab.setLogo(R.mipmap.ic_launcher);
            ab.setDisplayUseLogoEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
            if (!title.equalsIgnoreCase("Stores"))
                ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static void setTitle(String title, ActionBar actionBar) {
        try {
            if (actionBar != null)
                actionBar.setTitle(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Dialog getProgressDialog(Context context, String title) {
        try {
            Dialog alertDialog = new Dialog(context);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            RelativeLayout dialog_progress = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
            TextView title_dialogprogress = dialog_progress.findViewById(R.id.title_dialogprogress);
            if (title_dialogprogress != null)
                title_dialogprogress.setText(title);
            alertDialog.setContentView(dialog_progress);
            Window window = alertDialog.getWindow();
            if (window != null)
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            return alertDialog;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void hideKeyboard(View v, Activity a) {
        try {
            InputMethodManager inputManager = (InputMethodManager)
                    a.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputManager != null)
                inputManager.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFormattedDate(long seconds) {
        try {
            long millis = seconds * 1000;
            log_debug("GOT DATE: " + millis);
            if (DateUtils.isToday(millis)) {
                return "Today at " + new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date(millis));
            } else if (new Interval(DateTime.now().minusDays(1).withTimeAtStartOfDay(), DateTime.now().withTimeAtStartOfDay()).contains(millis)) {
                return "Yesterday at " + new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date(millis));
            } else if (new Interval(DateTime.now().minusYears(1).withTimeAtStartOfDay(), DateTime.now().withTimeAtStartOfDay()).contains(millis)) {
                return new SimpleDateFormat("MM/dd hh:mm a", Locale.getDefault()).format(new Date(millis));
            } else
                return new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.getDefault()).format(new Date(millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "N/A";
    }
}
