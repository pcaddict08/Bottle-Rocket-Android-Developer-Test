package com.einzig.stores.helpers;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.einzig.stores.R;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class AlertHelper {
    public static void showToast(final Context context, final String string, final boolean warning, final int icon) {
        try {
            if (context instanceof Activity)
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (warning)
                                new StyleableToast.Builder(context)
                                        .text(string)
                                        .textColor(ContextCompat.getColor(context, R.color.white))
                                        .backgroundColor(ContextCompat.getColor(context, R.color.mediumred))
                                        .length(Toast.LENGTH_LONG).iconEnd(icon)
                                        .show();
                            else
                                Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
