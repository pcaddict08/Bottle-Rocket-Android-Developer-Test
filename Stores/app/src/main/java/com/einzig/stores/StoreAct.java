package com.einzig.stores;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.einzig.stores.helpers.ThemeHelper;

public class StoreAct extends AppCompatActivity {

    public AlertDialog alertdialog;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public int getBarHeight() {
        try {
            ActionBar ab = getSupportActionBar();
            if (ab != null)
                return ab.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Dialog progressDialog;

    public void showProgress(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog = ThemeHelper.getProgressDialog(StoreAct.this, string); //new ProgressDialog(this, R.style.AboutDialog);
                if (progressDialog != null) {
                    progressDialog.show();
                    progressDialog.setCancelable(false);
                }
            }
        });
    }

    public void dismissProgress() {
        if (progressDialog != null)
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
    }
}
