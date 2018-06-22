package com.einzig.stores.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.einzig.stores.Application;
import com.einzig.stores.R;
import com.einzig.stores.StoreAct;
import com.einzig.stores.adapter.Adapter_Store;
import com.einzig.stores.async.ProcessJSON;
import com.einzig.stores.helpers.AlertHelper;
import com.einzig.stores.helpers.ThemeHelper;
import com.einzig.stores.objects.Store;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends StoreAct {

    @BindView(R.id.recyclerview_mainactivity)
    RecyclerView recyclerview_mainactivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ThemeHelper.initActionBar(getSupportActionBar(), "Stores");

        try {
            if (Application.isInternetAvailable(this)) {
                showProgress("Getting Stores...");
                Store.saveList(new ProcessJSON(new WeakReference<>(this)).execute().get());
            } else
                AlertHelper.showToast(this, "No Internet Connection, loading local cache.", true, R.drawable.ic_signal);
            List<Store> listFromDB = Store.listAll(Store.class);
            if (listFromDB.size() == 0)
                AlertHelper.showToast(this, "No Stores Found", true, R.drawable.ic_warning);
            recyclerview_mainactivity.setLayoutManager(new LinearLayoutManager(this));
            recyclerview_mainactivity.setAdapter(new Adapter_Store(listFromDB, this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_stores, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.refresh_storesmenu:
                finish();
                startActivity(getIntent());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
