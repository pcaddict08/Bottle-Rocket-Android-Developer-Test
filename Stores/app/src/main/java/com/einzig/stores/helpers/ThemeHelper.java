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
}
